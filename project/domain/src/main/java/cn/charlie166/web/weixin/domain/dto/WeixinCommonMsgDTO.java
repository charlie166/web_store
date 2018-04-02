package cn.charlie166.web.weixin.domain.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
* @ClassName: WeixinCommonMsgDTO 
* @Description: 微信消息公共数据结构
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年4月2日 
*
 */
@JacksonXmlRootElement(localName = "xml")
public class WeixinCommonMsgDTO extends WeixinResponseBaseDTO {

	/**开发者微信号**/
	@JacksonXmlProperty(localName = "ToUserName")
	private String toUserName;
	/**发送方帐号(一个OpenID)**/
	@JacksonXmlProperty(localName = "FromUserName")
	private String fromUserName;
	/**	消息创建时间 （整型）**/
	@JacksonXmlProperty(localName = "CreateTime")
	private Long createTime;
	/**消息类型**/
	@JacksonXmlProperty(localName = "MsgType")
	private String msgType;
	/**事件**/
	@JacksonXmlProperty(localName = "Event")
	private String event;
	@JacksonXmlProperty(localName = "EventKey")
	private String eventKey;
	
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getEventKey() {
		return eventKey;
	}
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
}