package cn.charlie166.web.weixin.domain.dto;

/**
* @ClassName: WeixinResponseBaseDTO 
* @Description: 微信服务器响应基础数据传输对象
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年3月29日 
*
 */
public class WeixinResponseBaseDTO {
	
	/**错误码**/
	private Integer errcode;
	/**错误信息**/
	private String errmsg;
	
	public Integer getErrcode() {
		return errcode;
	}
	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
	
}