package cn.charlie166.web.common.domain.dto;

import java.time.LocalDateTime;

/**
* @ClassName: BaseDTO 
* @Description: 基础数据传输对象
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年2月13日 
*
 */
public class BaseDTO {

	/**主键ID**/
	private Long id;
	/**创建时间**/
	private LocalDateTime createTime;
	
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