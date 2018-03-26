package cn.charlie166.web.store.constant;

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
	/**通用----数据不存在**/
	public static final String COMMON_DATA_ABSENT = "noData";
	/**通用----操作失败**/
	public static final String COMMON_FAILED = "operationFailed";
	/**通用----IO异常**/
	public static final String COMMON_IO_EXCEPTION = "io";
	/**通用----出现异常**/
	public static final String COMMON_EXCEPTION = "exception";
	/**通用----新增失败**/
	public static final String COMMON_INSERT_FAIL = "insertFail";
	/**通用----没有默认构造方法**/
	public static final String COMMON_DEFAULT_CONSTRUCTOR_ABSENT = "needDefaultConstructor";
	
	/**文件----创建目录出现异常***/
	public static final String FILE_CREATE_DIRECTORY = "createDirectoryException";
	/**文件----创建文件出现异常***/
	public static final String FILE_CREATE_FAIL = "createFileException";
	/**文件----复制文件出现异常***/
	public static final String FILE_COPY_FILE = "copyFileException";
	/**文件----文件不存在**/
	public static final String FILE_NOT_EXISTS = "notExists";
	/**文件----目录不存在或不是目录**/
	public static final String FILE_NOT_DIRECTORY = "notDirectory";
	/**文件----(读写)权限不足**/
	public static final String FILE_PERMISSION_DENIED = "permissionDenied";
	/**文件----文件大小超过限制**/
	public static final String FILE_SIZE_EXCEED = "sizeExceed";
	
	/**校验----校验不通过**/
	public static final String CHECK_FAILED = "checkFailed";
	/**校验----下标超过参数长度**/
	public static final String CHECK_PARAM_LENGTH_EXCEED = "paramLengthExceed";
	
	/**PAC----域名已存在**/
	public static final String PAC_DOMAIN_EXISTS = "domainExists";
	/**PAC----域名必需**/
	public static final String PAC_DOMAIN_NEED = "domainNeed";
	
	/**网络----需要指明协议**/
	public static final String WEB_NEED_PROTOCAL = "needProtocol";
	
	/**JSON----解析出错**/
	public static final String JSON_PARSE_ERROR = "jsonParseError";
	
	/**书签----需要书签内容**/
	public static final String BOOKMARK_NEED_CONTENT = "bookmarkNeedContent";
	/**书签----标题必需**/
	public static final String BOOKMARK_NEED_TITLE = "bookmarkNeedTitle";
}