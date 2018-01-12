package cn.charlie166.web.store.domain.third;

/**
* @ClassName: QiniuResponse 
* @Description: 七牛上传请求返回数据结构
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年1月12日 
*
 */
public class QiniuResponse {

	/**响应码**/
	private Integer code;
	
	/**相应消息**/
	private String message;
	
	/**上传返回数据**/
	private QiniuRet content;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public QiniuRet getContent() {
		return content;
	}

	public void setContent(QiniuRet content) {
		this.content = content;
	}
}