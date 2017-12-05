package cn.charlie166.web.store.domain.po;

import cn.charlie166.web.store.domain.annotation.StringCheck;

/**
* @ClassName: PacModel 
* @Description: PAC 数据模型
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年12月5日 
*
 */
public class PacModel extends BaseEntity {

	/**域名地址**/
	@StringCheck(maxLength = 255, maxLengthTip = "域名长度不能超过%s")
	private String domain;
	/**描述**/
	@StringCheck(maxLength = 255, maxLengthTip = "描述不能超过%s")
	private String description;
	/**状态(1: 有效; 0: 无效)**/
	private Boolean flag;
	
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
	
		this.description = description;
	}
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	
	
}