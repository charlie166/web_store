package cn.charlie166.web.weixin.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

import cn.charlie166.web.store.constant.CustomException;
import cn.charlie166.web.store.constant.ExceptionCodes;
import cn.charlie166.web.store.tools.HashUtils;
import cn.charlie166.web.store.tools.JsonUtils;
import cn.charlie166.web.store.tools.StringUtils;
import cn.charlie166.web.store.tools.WebUtils;
import cn.charlie166.web.store.tools.XmlUtils;
import cn.charlie166.web.weixin.domain.dto.WeixinMsgDTO;
import cn.charlie166.web.weixin.domain.dto.WeixinServerValidateDTO;
import cn.charlie166.web.weixin.domain.dto.WeixinUserDTO;
import cn.charlie166.web.weixin.service.inter.WeixinHomeService;

/**
* @ClassName: HomeServiceImpl 
* @Description: 微信主页业务服务接口实现类
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年3月27日 
*
 */
@Service
public class WeixinHomeServiceImpl extends WeixinBaseServiceImpl implements WeixinHomeService {

	@Override
	public WeixinServerValidateDTO validateServer(WeixinServerValidateDTO dto) {
		if(dto != null){
			if(StringUtils.hasContent(dto.getSignature()) && StringUtils.hasContent(dto.getTimestamp()) &&
				StringUtils.hasContent(dto.getNonce())){
				List<String> list = Arrays.asList(dto.getNonce(), dto.getTimestamp(), token);
				Collections.sort(list);
				String sha1Val = HashUtils.sha1(list.stream().collect(Collectors.joining()));
				if(sha1Val.equals(dto.getSignature())){
					return dto;
				} else {
					throw CustomException.instance(ExceptionCodes.WEIXIN_SERVER_VALID_FAILED);
				}
			}
		}
		throw CustomException.instance(ExceptionCodes.COMMON_PARAM_ABSENT);
	}

	@Override
	public String handlerMsg(InputStream input) {
		try {
			StringWriter sw = new StringWriter();
			IOUtils.copy(input, sw, Charset.defaultCharset());
			logger.debug("接收到微信消息:" + sw.toString());
			WeixinMsgDTO msg = XmlUtils.toObject(sw.toString(), WeixinMsgDTO.class);
			if(msg != null){
				WeixinMsgDTO retMsg = this.createReturnMsg(msg);
				boolean hasContent = false;
				if(StringUtils.hasContent(msg.getEvent())){
					switch(msg.getEvent()){
						case "unsubscribe" : {/**用户取消关注**/
							logger.debug("微信用户[{}]取消订阅", msg.getFromUserName());
							break;
						}
						case "subscribe": {/**用户关注**/
							/**订阅号没有获取权限**/
							/*new Thread(() -> {
								this.handleSubscribeMsg(msg);
							}).start();*/
							retMsg.setMsgType("text");
							retMsg.setContent("终于等到你来关注了!");
							hasContent = true;
							break;
						}
					}
				} else {
					/**根据消息类型处理**/
					if(StringUtils.hasContent(msg.getMsgType())){
						switch(msg.getMsgType()){
							case "text": {/**文本消息**/
								retMsg.setMsgType("text");
								retMsg.setContent("不管你说什么，我都是回复球球");
								hasContent = true;
								break;
							}
						}
					}
				}
				/**默认消息回复**/
				if(!hasContent){
					retMsg.setMsgType("text");
					retMsg.setContent("❤球球");
				}
				return XmlUtils.fromObject(retMsg);
			}
			return "";
		} catch (IOException e) {
			throw CustomException.instance(ExceptionCodes.COMMON_IO_EXCEPTION, e);
		}
	}
	
	/**
	* @Title: createReturnMsg 
	* @Description: 创建返回消息对象
	* @param inMsg 来源消息数据
	 */
	private WeixinMsgDTO createReturnMsg(WeixinMsgDTO inMsg){
		WeixinMsgDTO retMsg = new WeixinMsgDTO();
		retMsg.setToUserName(inMsg.getFromUserName());
		retMsg.setFromUserName(inMsg.getToUserName());
		retMsg.setCreateTime(Instant.now().toEpochMilli());
		return retMsg;
	}
	
	/**
	* @Title: sendSubscribeMsg 
	* @Description: 处理用户订阅消息 ----订阅号没有获取用户信息的权限
	* @param msg 微信传过来的信息
	 */
	protected void handleSubscribeMsg(WeixinMsgDTO msg){
		WeixinUserDTO user = this.userInfo(msg.getFromUserName());
		if(user.getSubscribe() != null && user.getSubscribe().intValue() == 0){
			logger.debug("微信用户未订阅，无法获取到用户信息");
		}else {
			logger.debug("获取到用户信息: {}", JsonUtils.toJson(user));
			Map<String, Object> retMsg = Maps.newHashMap();
			retMsg.put("touser", user.getOpenid());
			retMsg.put("msgtype", "text");
			Map<String, Object> msgContent = Maps.newHashMap();
			StringBuilder welcomeMsg = new StringBuilder();
			welcomeMsg.append("终于等到你[").append(user.getNickname()).append("] 关注我了");
			msgContent.put("content", welcomeMsg.toString());
			retMsg.put("text", msgContent);
			StringBuilder thisUrl = new StringBuilder();
			thisUrl.append(this.getBaseUrl()).append("message/custom/send?access_token=").append(this.getAccessToken());
			try {
				WebUtils.weixinPost(thisUrl.toString(), retMsg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}