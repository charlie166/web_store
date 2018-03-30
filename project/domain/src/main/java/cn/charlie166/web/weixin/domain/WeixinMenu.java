package cn.charlie166.web.weixin.domain;

import java.util.List;

/**
* @ClassName: WeixinMenu 
* @Description: 微信菜单对象
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年3月29日 
*
 */
public class WeixinMenu {

	/**一级菜单, 不能超过3个**/
	private List<WeixinMenu> button;
	/**二级菜单, 不能超过5个**/
	private List<WeixinMenu> sub_button;
	/**菜单的响应动作类型**/
	private String type;
	/**菜单标题**/
	private String name;
	/**菜单KEY值，用于消息接口推送，不超过128字节**/
	private String key;
	/**网页 链接，用户点击菜单可打开链接，不超过1024字节。 type为miniprogram时，不支持小程序的老版本客户端将打开本url**/
	private String url;
	/**调用新增永久素材接口返回的合法media_id**/
	private String media_id;
	/**小程序的appid（仅认证公众号可配置）**/
	private String appid;
	/**小程序的页面路径**/
	private String pagepath;
	
	
	public List<WeixinMenu> getButton() {
		return button;
	}
	public void setButton(List<WeixinMenu> button) {
		this.button = button;
	}
	public List<WeixinMenu> getSub_button() {
		return sub_button;
	}
	public void setSub_button(List<WeixinMenu> sub_button) {
		this.sub_button = sub_button;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMedia_id() {
		return media_id;
	}
	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getPagepath() {
		return pagepath;
	}
	public void setPagepath(String pagepath) {
		this.pagepath = pagepath;
	}
	
}