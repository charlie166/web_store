package cn.charlie166.web.store.tools;

/**
* @ClassName: ExceptionCodes 
* @Description: 异常错误码
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年11月17日 
*
 */
public class ExceptionCodes {

	/**通用----缺少必要参数***/
	public static final String COMMON_PARAM_ABSENT = "paramAbsent";
	
	/**文件----创建目录出现异常***/
	public static final String FILE_CREATE_DIRECTORY = "createDirectoryException";
	/**文件----创建文件出现异常***/
	public static final String FILE_CREATE_FILE = "createFileException";
	/**文件----复制文件出现异常***/
	public static final String FILE_COPY_FILE = "createFileException";
	/**文件----文件不存在**/
	public static final String FILE_NOT_EXISTS = "notExists";
	/**文件----目录不存在或不是目录**/
	public static final String FILE_NOT_DIRECTORY = "notDirectory";
	
	/**校验----校验不通过**/
	public static final String CHECK_FAILED = "checkFailed";
	/**校验----下标超过参数长度**/
	public static final String CHECK_PARAM_LENGTH_EXCEED = "paramLengthExceed";
}