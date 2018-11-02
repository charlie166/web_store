package cn.charlie166.web.common.domain.dto;

/**
* @ClassName: MsgDTO 
* @Description: JSON请求返回对象
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年3月1日 
* 
* @param <T>
 */
public class MsgDTO<T> {

	/**响应码**/
	private int code;
	/**返回消息**/
	private String msg;
	/**返回消息内容**/
	private T content;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getContent() {
		return content;
	}
	public void setContent(T content) {
		this.content = content;
	}
	
}