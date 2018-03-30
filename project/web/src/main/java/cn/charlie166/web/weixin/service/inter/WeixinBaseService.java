package cn.charlie166.web.weixin.service.inter;

import cn.charlie166.web.base.service.inter.BaseService;
import cn.charlie166.web.weixin.domain.dto.WeixinUserDTO;

/**
* @ClassName: WeixinBaseService 
* @Description: 微信基础服务接口
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年3月28日 
*
 */
public interface WeixinBaseService extends BaseService {

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
	
	/**
	* @Title: refreshMenu 
	* @Description: 刷新微信菜单
	 */
	void refreshMenu();
	
	/**
	* @Title: userInfo 
	* @Description: 获取用户信息
	* @param openId 用户openId
	* @return 用户信息对象
	 */
	WeixinUserDTO userInfo(String openId);
}