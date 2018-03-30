package cn.charlie166.web.weixin.service.inter;

import java.io.InputStream;

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
	
	/**
	* @Title: handlerMsg 
	* @Description: 处理微信消息事件
	* @param input 消息输入流
	* @return 返回需要回复的消息. 返回空字符串表示不需要回复或不能及时回复，避免微信服务器重发消息
	 */
	String handlerMsg(InputStream input);
}