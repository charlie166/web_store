package cn.charlie166.web.store.tools;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
* @ClassName: FileUtils 
* @Description: 文件操作工具类
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年11月17日 
*
 */
public class FileUtils {

	/**
	 * @Title: storeFile 
	 * @Description: 以UTF-8编码保存数据到文件.如果指定文件存在，则覆盖
	 * @param path 文件保存路径
	 * @param content 需要保存的内容
	 * @throws CustomException 操作异常 
	 */
	public static void storeFile(String path, String content) throws CustomException{
		FileUtils.storeFile(path, content, StandardCharsets.UTF_8);
	}
	
	/**
	* @Title: storeFile 
	* @Description: 保存数据到文件.如果指定文件存在，则覆盖
	* @param path 文件保存路径
	* @param content 需要保存的内容
	* @param cs 编码
	 * @throws CustomException 操作异常 
	 */
	public static void storeFile(String path, String content, Charset cs) throws CustomException{
		if(StringUtils.hasContent(path)){
			FileUtils.storeFile(Paths.get(path), content, cs);
		}
	}
	
	/**
	 * @Title: storeFile 
	 * @Description: 以UTF-8编码保存字符串到文件.如果指定文件存在，则覆盖
	 * @param path 文件路径
	 * @param content 字符串内容
	 * @throws CustomException 操作异常 
	 */
	public static void storeFile(Path path, String content) throws CustomException {
		FileUtils.storeFile(path, content, StandardCharsets.UTF_8);
	}
	/**
	* @Title: storeFile 
	* @Description: 保存字符串到文件.如果指定文件存在，则覆盖
	* @param path 文件路径
	* @param content 字符串内容
	* @param cs 编码
	 * @throws CustomException 操作异常 
	 */
	public static void storeFile(Path path, String content, Charset cs) throws CustomException {
		if(path != null && StringUtils.hasContent(content)){
			if(!path.isAbsolute()){
				path = path.normalize();
			}
			/**文件父目录必须存在**/
			if(!Files.exists(path.getParent())){
				try {
					Files.createDirectory(path.getParent());
				} catch (IOException e) {
					throw CustomException.instance(ExceptionCodes.FILE_CREATE_DIRECTORY, e);
				}
			}
			/**创建文件**/
			if(!Files.exists(path)){
				try {
					path = Files.createFile(path);
				} catch (IOException e) {
					throw CustomException.instance(ExceptionCodes.FILE_CREATE_FILE, e);
				}
			}
			/**必须为文件**/
			if(!Files.isDirectory(path)){
				try {
					ByteArrayInputStream contentInput = new ByteArrayInputStream(content.getBytes(cs));
					Files.copy(contentInput, path, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					throw CustomException.instance(ExceptionCodes.FILE_COPY_FILE, e);
				}
			}
		}
	}
	
	public static void readFile(Path path){
		
	}
	
	public static void main(String[] args) {
		Path path = Paths.get("F:/upload/charlie166");
		try {
			FileUtils.storeFile(path, "rrfs肉肉肉放撒施我\r\n水电工额俄方");
		} catch (CustomException e) {
			System.out.println("异常码：" + e.getCode());
			e.printStackTrace();
		}
		System.out.println("done...");
	}
}