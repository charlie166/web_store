package cn.charlie166.web.weixin.service.inter;

/**
* @ClassName: WeixinBaseService 
* @Description: 微信基础服务接口
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年3月28日 
*
 */
public interface WeixinBaseService {

	/**
	* @Title: getBaseUrl 
	* @Description: 获取微信请求基础链接地址
	* @return
	 */
	String getBaseUrl();
	
	/**
	* @Title: getAccessToken 
	* @Description: 获取访问令牌
	* @return
	 */
	String getAccessToken();
}