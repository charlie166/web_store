package cn.charlie166.web.store.constant;

/**
* @ClassName: CustomException 
* @Description: 自定义异常
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年11月17日 
*
 */
public class CustomException extends Exception {

	private static final long serialVersionUID = 1L;

	/**异常错误码**/
	private String code;
	
	public CustomException(String code){
		super();
		this.code = code;
	}
	
	public CustomException(String code, String message){
		super(message);
		this.code = code;
	}
	
	public CustomException(String code, Throwable throwable){
		super(throwable);
		this.code = code;
	}
	public CustomException(String code, String message, Throwable throwable){
		super(message, throwable);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public static CustomException instance(String code){
		return new CustomException(code);
	}
	
	public static CustomException instance(String code, String message){
		return new CustomException(code, message);
	}
	
	public static CustomException instance(String code, Throwable throwable){
		return new CustomException(code, throwable);
	}
	
	public static CustomException instance(String code, String message, Throwable throwable){
		return new CustomException(code, message, throwable);
	}
}