package cn.charlie166.web.store.domain.dto;

import java.time.LocalDateTime;

/**
 * @description 代理自动配置(PAC)数据传输模型
 * @author <a href="mailto:charlie166@163.com">李阳</a> 
 * @version 1.0, 2017年12月18日
 * @see     
 * @since   domain 1.0
 */
public class PacDTO {

	/**域名地址**/
	private String domain;
	/**描述**/
	private String description;
	/**状态(1: 有效; 0: 无效)**/
	private Boolean flag;
	/**主键ID**/
	private Long id;
	/**创建时间**/
	private LocalDateTime createTime;
	
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getCreateTime() {
		return createTime;
	}
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
	
}