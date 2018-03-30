package cn.charlie166.web.weixin.domain.dto;

/**
* @ClassName: WeixinTokenDTO 
* @Description: 获取微信token数据传输对象
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年3月28日 
*
 */
public class WeixinTokenDTO extends WeixinResponseBaseDTO {

	/**获取到的凭证**/
	private String access_token;
	/**凭证有效时间, 单位: 秒**/
	private Long expires_in;
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public Long getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}
	
}