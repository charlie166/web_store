package cn.charlie166.web.weixin.domain.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
* @ClassName: WeixinMsgDTO 
* @Description: 微信消息数据对象
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年3月29日 
*
 */
@JacksonXmlRootElement(localName = "xml")
public class WeixinMsgDTO extends WeixinResponseBaseDTO {

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
	/**消息id，64位整型**/
	@JacksonXmlProperty(localName = "MsgId")
	private Long msgId;
	/**文本消息内容**/
	@JacksonXmlProperty(localName = "Content")
	private String content;
	
	/***地理位置消息**********/
	/**地理位置维度**/
	@JacksonXmlProperty(localName = "Location_X")
	private Double location_X;
	/**地理位置经度**/
	@JacksonXmlProperty(localName = "Location_Y")
	private Double location_Y;
	/**地图缩放大小**/
	@JacksonXmlProperty(localName = "Scale")
	private Integer scale;
	/**地理位置信息**/
	@JacksonXmlProperty(localName = "Label")
	private String label;
	
	/***链接消息******/
	/**消息标题**/
	@JacksonXmlProperty(localName = "Title")
	private String title;
	/**消息描述**/
	@JacksonXmlProperty(localName = "Description")
	private String description;
	/**消息链接**/
	@JacksonXmlProperty(localName = "Url")
	private String url;
	
	/***小视频消息, 视频消息*******/
	/**视频消息媒体id，可以调用多媒体文件下载接口拉取数据**/
	@JacksonXmlProperty(localName = "MediaId")
	private String mediaId;
	/**视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据**/
	@JacksonXmlProperty(localName = "ThumbMediaId")
	private String thumbMediaId;
	
	
	/**语音消息, 语音文件变量为mediaId**/
	/**语音格式：amr**/
	@JacksonXmlProperty(localName = "Format")
	private String format;
	/**语音识别结果，UTF8编码**/
	@JacksonXmlProperty(localName = "Recognition")
	private String recognition;
	
	/**图片消息, 媒体变量为mediaId******/
	/**图片链接（由系统生成）**/
	@JacksonXmlProperty(localName = "PicUrl")
	private String picUrl;
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getMsgId() {
		return msgId;
	}
	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}
	public Double getLocation_X() {
		return location_X;
	}
	public void setLocation_X(Double location_X) {
		this.location_X = location_X;
	}
	public Double getLocation_Y() {
		return location_Y;
	}
	public void setLocation_Y(Double location_Y) {
		this.location_Y = location_Y;
	}
	public Integer getScale() {
		return scale;
	}
	public void setScale(Integer scale) {
		this.scale = scale;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getRecognition() {
		return recognition;
	}
	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
}