package cn.charlie166.web.store.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import cn.charlie166.web.store.constant.CustomException;
import cn.charlie166.web.store.constant.ExceptionCodes;

/**
* @ClassName: ZipUtils 
* @Description: 压缩工具类. 支持格式为zip
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年4月10日 
*
 */
public class ZipUtils {

	/**
	* @Title: compressDirectory 
	* @Description: 压缩文件夹
	* @param dir 需要压缩的文件夹
	* @return 压缩后的文件
	 */
	public static Path compressDirectory(String dir){
		if(StringUtils.hasContent(dir)){
			Path path = Paths.get(dir);
			if(Files.exists(path) && Files.isDirectory(path)){
				String zipFileName = String.valueOf(System.currentTimeMillis()) + "_" + Thread.currentThread().getId() + ".zip";
				String parentPathStr = path.getParent().normalize().toString();
				Path zipPath = Paths.get(parentPathStr, zipFileName);
				try(ZipOutputStream zOut = new ZipOutputStream(Files.newOutputStream(zipPath), Charset.defaultCharset())) {
					SimpleFileVisitor<Path> sfv = new SimpleFileVisitor<Path>(){
						@Override
						public FileVisitResult visitFile(Path file,
								BasicFileAttributes attrs) throws IOException {
							InputStream is = Files.newInputStream(file);
							ZipEntry entry = new ZipEntry(file.normalize().toString().substring(parentPathStr.length() + 1));
							zOut.putNextEntry(entry);
							int count;
					        byte data[] = new byte[2048];
					        while ((count = is.read(data, 0, 2048)) != -1) {
					            zOut.write(data, 0, count);
					        }
							return super.visitFile(file, attrs);
						}
						
					};
					Files.walkFileTree(path, sfv);
					return zipPath;
				} catch (IOException e) {
					throw CustomException.instance(ExceptionCodes.COMMON_IO_EXCEPTION, e);
				}
			}else {
				throw CustomException.instance(ExceptionCodes.FILE_NOT_DIRECTORY);
			}
		}
		throw CustomException.instance(ExceptionCodes.COMMON_PARAM_ABSENT);
	}
	
	/**
	* @Title: decompress 
	* @Description: 解压zip到当前文件夹下
	* @param zip 压缩文件
	* @return 解压后的文件夹
	 */
	public static Path decompress(String zip){
		return ZipUtils.decompress(zip, null);
	}
	
	/**
	* @Title: decompress 
	* @Description: 解压zip
	* @param zip 压缩文件
	* @param dir 解压后文件夹	为空或空字符串则解压至当前文件夹下
	* @return 解压后的文件夹
	 */
	public static Path decompress(String zip, String dir){
		if(StringUtils.hasContent(zip)){
			Path zipPath = Paths.get(zip);
			if(Files.exists(zipPath) && Files.isRegularFile(zipPath)){
				if(zipPath.normalize().toString().endsWith("zip")){
					Path dirPath = Paths.get(StringUtils.hasContent(dir) ? dir : zipPath.getParent().normalize().toString());
					try {
						if(!Files.exists(dirPath)){
							Files.createDirectory(dirPath);
						}
						if(Files.exists(dirPath) && Files.isDirectory(dirPath)){
							try(ZipFile zipFile = new ZipFile(zipPath.toString())){
								Enumeration<? extends ZipEntry> entries = zipFile.entries();
								while(entries.hasMoreElements()){
									ZipEntry element = entries.nextElement();
									boolean isDir = element.isDirectory();
									Path outPath = Paths.get(dirPath.normalize().toString(), element.getName());
									if(!Files.exists(outPath)){
										if(isDir)
											Files.createDirectory(outPath);
										else 
											Files.createFile(outPath);
									}
									/**具体文件才进行此操作**/
									if(!isDir){
										try(InputStream is = zipFile.getInputStream(element);
												OutputStream os = Files.newOutputStream(outPath)){
											byte[] buf = new byte[2048];
											int len;
											while((len = is.read(buf))>0){
												os.write(buf, 0, len);
											}
										}
									}
								}
							}
							return dirPath;
						} else {
							throw CustomException.instance(ExceptionCodes.FILE_NOT_DIRECTORY);
						}
					} catch (IOException e) {
						throw CustomException.instance(ExceptionCodes.COMMON_IO_EXCEPTION, e);
					}
				} else {
					throw CustomException.instance(ExceptionCodes.FILE_NOT_SUPPORT);
				}
			} else {
				throw CustomException.instance(ExceptionCodes.FILE_NOT_EXISTS);
			}
		}
		throw CustomException.instance(ExceptionCodes.COMMON_PARAM_ABSENT);
	}
	
	public static void main(String[] args) {
//		Path resultPath = ZipUtils.compressDirectory("F:/charlie/documents/rui/research/middle_search_bar");
//		System.out.println("done:" + resultPath);
		Path out = ZipUtils.decompress("F:/charlie/documents/rui/test/middle_search_bar.zip");
		System.out.println("done:" + out);
	}
}