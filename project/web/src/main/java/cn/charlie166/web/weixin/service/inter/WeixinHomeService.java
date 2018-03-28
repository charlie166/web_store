package cn.charlie166.web.weixin.service.inter;

import cn.charlie166.web.weixin.domain.dto.WeixinServerValidateDTO;

/**
* @ClassName: HomeService 
* @Description: 微信主页服务接口
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年3月27日 
*
 */
public interface WeixinHomeService extends WeixinBaseService {

	/**
	* @Title: validateServer 
	* @Description: 校验消息是否来自微信服务器
	* @param dto
	* @return
	 */
	public WeixinServerValidateDTO validateServer(WeixinServerValidateDTO dto);
}