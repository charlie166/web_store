package cn.charlie166.web.weixin.domain.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
* @ClassName: WeixinLocationDTO 
* @Description: 微信位置信息数据结构
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年4月2日 
*
 */
public class WeixinLocationDTO {

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
	/****/
	@JacksonXmlProperty(localName = "Poiname")
	private String poiname;
	
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
	public String getPoiname() {
		return poiname;
	}
	public void setPoiname(String poiname) {
		this.poiname = poiname;
	}
	
	
}