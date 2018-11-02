package cn.charlie166.web.store.domain.dto;

import cn.charlie166.web.common.domain.dto.BaseDTO;

/**
 * @description 代理自动配置(PAC)数据传输模型
 * @author <a href="mailto:charlie166@163.com">李阳</a> 
 * @version 1.0, 2017年12月18日
 * @see     
 * @since   domain 1.0
 */
public class PacDTO extends BaseDTO {

	/**域名地址**/
	private String domain;
	/**描述**/
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