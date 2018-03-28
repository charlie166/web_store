package cn.charlie166.web.store.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.charlie166.web.base.service.impl.BaseServiceImpl;
import cn.charlie166.web.store.constant.CustomException;
import cn.charlie166.web.store.constant.ExceptionCodes;
import cn.charlie166.web.store.service.inter.AttachmentService;
import cn.charlie166.web.store.service.inter.QiniuService;
import cn.charlie166.web.store.tools.StringUtils;

/**
* @ClassName: AttachmentServiceImpl 
* @Description: 附件服务实现类
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年1月12日 
*
 */
@Service
public class AttachmentServiceImpl extends BaseServiceImpl implements AttachmentService {

	/**是否备份到七牛**/
	@Value(value = "${attachment.backup.qiniu:false}")
	private Boolean backupQiniu;
	
	/**配置的公共物理路径部分**/
	@Value(value = "${attachment.rootPath:null}")
	private String physicalPath;
	
	/**是否异步备份到七牛**/
	@Value(value = "${attachment.asynchronousQiniu:false}")
	private Boolean asynchronousQiniu;
	@Autowired
	private QiniuService qiniuService;
	
	@Override
	public File saveBinaryFile(byte[] data, String path) throws CustomException {
		File f = this.checkFile(path);
		try {
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f));
			bos.write(data);
			bos.flush();
			bos.close();
		} catch (IOException ioe) {
			throw CustomException.instance(ExceptionCodes.COMMON_IO_EXCEPTION, ioe);
		}
		this.backupToQiniu(f);
		return f;
	}

	@Override
	public String getPhysicalCommonPreffix() {
		String str = StringUtils.hasContent(physicalPath) ? physicalPath : "./";
		if(!str.endsWith("/"))
			str += "/";
		return str;
	}

	@Override
	public File saveFileByInputStream(InputStream is, String path, long maxSize) throws CustomException {
		try {
			File tmpFile = this.getTmpFile();
			FileUtils.copyInputStreamToFile(is, tmpFile);
			if (maxSize > 0 && tmpFile.length() > maxSize) {
				tmpFile.delete();
				throw CustomException.instance(ExceptionCodes.FILE_SIZE_EXCEED);
			}
			File targetFile = this.checkFile(path);
			FileUtils.moveFile(tmpFile, targetFile);
			/**异步备份，新开线程操作。并且暂无法处理异常**/
			if(this.asynchronousQiniu){
				new Thread(() -> {
					this.backupToQiniu(targetFile);
				}).start();
			} else {
				this.backupToQiniu(targetFile);
			}
			return targetFile;
		} catch (IOException e) {
			throw CustomException.instance(ExceptionCodes.COMMON_IO_EXCEPTION, e);
		}
	}
	
	@Override
	public File saveFileByInputStream(InputStream is, String path) throws CustomException {
		return this.saveFileByInputStream(is, path, -1);
	}
	
	/**
	* @Title: backupToQiniu 
	* @Description: 备份文件到七牛，不改变文件相对路径
	* @param file 需要备份的文件
	 */
	private void backupToQiniu(File file){
		if(this.backupQiniu != null && this.backupQiniu.booleanValue()){
			if(file != null && file.exists() && file.isFile()){
				String fileKey = this.getAbsolutePath(file);
				qiniuService.uploadFile(file, fileKey);
			}
		}
	}
	
	/**
	* @Title: getAbsolutePath 
	* @Description: 获取文件路径的相对路径，相对于公共附件物理路径前缀
	* @param file 要判断的文件
	* @return
	 */
	private String getAbsolutePath(File file){
		if(file != null && file.exists()){
			File dir = new File(physicalPath);
			if(dir.isDirectory()){
				String dirStr = dir.getAbsolutePath().replaceAll("\\\\+", "/");
				String fileStr = file.getAbsolutePath().replaceAll("\\\\+", "/");
				String s = fileStr.substring(fileStr.indexOf(dirStr) > -1 ? fileStr.indexOf(dirStr) + dirStr.length() : 0);
				/**如果是表示当前目录的字符串，则去掉**/
				if(s.startsWith("./") && s.length() > 2)
					s = s.substring(2);
				/**如果第一个为斜杠，去掉第一个***/
				if(s.startsWith("/") && s.length() > 1)
					s = s.substring(1);
				return s;
			}
		}
		return null;
	}

	/**
	* @Title: getTmpFile 
	* @Description: 获取一个临时文件
	* @return
	 */
	private File getTmpFile() {
		File tmpDir = FileUtils.getTempDirectory();
		String tmpFileName = (Math.random() * 10000 + "").replace(".", "");
		return new File(tmpDir, tmpFileName);
	}
	
	/**
	* @Title: checkFile 
	* @Description: 校验给定路径是否可以创建文件
	* @param path 路径
	* @return 指定路径文件对象
	 */
	private File checkFile(String path){
		if(!StringUtils.hasContent(path))
			throw CustomException.instance(ExceptionCodes.COMMON_PARAM_ABSENT);
		File f = new File(path);
		File parentPath = f.getParentFile();
		if ((!parentPath.exists()) && (!parentPath.mkdirs())) {
			throw CustomException.instance(ExceptionCodes.FILE_CREATE_FAIL);
		}
		if (!parentPath.canWrite()) {
			throw CustomException.instance(ExceptionCodes.FILE_PERMISSION_DENIED);
		}
		return f;
	}

	@Override
	public void saveString(String content, String relativePath) {
		if(content != null && StringUtils.hasContent(relativePath)){
			File f = this.checkFile(this.getPhysicalCommonPreffix() + relativePath);
			try {
				FileUtils.write(f, content, Charset.defaultCharset());
			} catch (IOException e) {
				throw CustomException.instance(ExceptionCodes.COMMON_IO_EXCEPTION, e);
			}
		}
	}

	@Override
	public String getString(String relativePath) {
		Path path = Paths.get(this.getPhysicalCommonPreffix() + relativePath);
		if(Files.exists(path)){
			try {
				return ByteBuffer.wrap(Files.readAllBytes(path)).toString();
			} catch (IOException e) {
				throw CustomException.instance(ExceptionCodes.COMMON_IO_EXCEPTION, e);
			}
		} else {
			throw CustomException.instance(ExceptionCodes.FILE_NOT_EXISTS);
		}
	}

	@Override
	public String getStringOrDefault(String relativePath, String defaultString) {
		Path path = Paths.get(this.getPhysicalCommonPreffix() + relativePath);
		if(Files.exists(path)){
			try {
				return new String(Files.readAllBytes(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return defaultString;
	}
}