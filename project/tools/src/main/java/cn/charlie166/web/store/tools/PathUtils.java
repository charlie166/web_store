package cn.charlie166.web.store.tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import cn.charlie166.web.store.constant.CustomException;
import cn.charlie166.web.store.constant.ExceptionCodes;

/**
* @ClassName: PathUtils 
* @Description: 新的文件操作工具类
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年4月18日 
*
 */
public class PathUtils {

	/***
	* @Title: getPathOfFile 
	* @Description: 创建文件, 不存在路径时会去创建
	* @param p 文件路径
	* @return
	 * @throws IOException 
	 */
	public static Path getPathOfFile(String p) throws IOException{
		if(!StringUtils.hasContent(p))
			throw CustomException.instance(ExceptionCodes.COMMON_PARAM_ABSENT);
		Path path = Paths.get(p);
		if(!Files.exists(path.getParent())){
			Files.createDirectories(path.getParent());
		}
		if(!Files.exists(path))
			Files.createFile(path);
		return path;
	}
}