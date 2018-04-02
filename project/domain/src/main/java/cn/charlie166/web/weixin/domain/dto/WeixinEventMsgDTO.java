package cn.charlie166.web.weixin.domain.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
* @ClassName: WeixinEventMsgDTO 
* @Description: 微信事件消息数据结构----按钮等推送的事件消息
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年4月2日 
*
 */
public class WeixinEventMsgDTO extends WeixinCommonMsgDTO {

	/***地理位置消息**********/
	@JacksonXmlProperty(localName = "SendLocationInfo")
	private WeixinLocationDTO SendLocationInfo;
	
	public WeixinLocationDTO getSendLocationInfo() {
		return SendLocationInfo;
	}
	public void setSendLocationInfo(WeixinLocationDTO sendLocationInfo) {
		SendLocationInfo = sendLocationInfo;
	}
	
}