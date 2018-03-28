package cn.charlie166.web.store.service.inter;

import java.io.File;
import java.io.InputStream;

import cn.charlie166.web.store.constant.CustomException;

/**
* @ClassName: AttachmentService 
* @Description: 附件操作服务接口
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年1月12日 
*
 */
public interface AttachmentService {

	/**
	* @Title: saveBinaryFile 
	* @Description: 保存二进制文件
	* @param data 文件数据
	* @param path 保存路径，此处为全物理路径。能直接定位到文件的
	* @return 保存后的文件对象
	 */
	public File saveBinaryFile(byte[] data, String path) throws CustomException;
	
	/**
	* @Title: saveString 
	* @Description: 保存文本内容到文件， 如果文件存在，则覆盖文件内容
	* @param content 需要保存的内容. 可以为空字符串
	* @param relativePath 文件相对路径
	 */
	void saveString(String content, String relativePath);
	
	/**
	* @Title: getString 
	* @Description: 从指定路径文件获取文本内容
	* @param relativePath 相对路径
	* @return 文本内容
	 */
	String getString(String relativePath);
	
	/**
	* @Title: getStringOrDefault 
	* @Description: 从指定文件获取文本内容，如果文件不存在或者出现异常，均返回默认字符串
	* @param relativePath 文件相对路径
	* @param defaultString 默认文本内容
	* @return 读取到的文本内容
	 */
	String getStringOrDefault(String relativePath, String defaultString);
	
	/**
	* @Title: getPhysicalCommonPreffix 
	* @Description: 获取物理保存路径共同部分. 未配置返回当前路径{./}
	* 如: F:/upload/ 	或	/home/xxx/upload/	或	./
	* @return 保存路径
	 */
	public String getPhysicalCommonPreffix();
	
	/**
	* @Title: saveFileByInputStream 
	* @Description: 通过输入流保存文件
	* @param is 文件流
	* @param path 保存路径， 此处为全路径，能具体到文件的
	* @param maxSize 保存限制大小，大于0时有效
	* @return 保存后的文件对象
	 */
	public File saveFileByInputStream(InputStream is, String path, long maxSize) throws CustomException;
	
	/**
	* @Title: saveFileByInputStream 
	* @Description: 通过输入流保存文件
	* @param is 文件流
	* @param path 保存路径，能指定到具体文件
	* @return 保存后的文件对象
	 */
	public File saveFileByInputStream(InputStream is, String path) throws CustomException;
}