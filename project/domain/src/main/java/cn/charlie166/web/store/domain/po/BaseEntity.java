package cn.charlie166.web.store.domain.po;

import java.time.LocalDateTime;

/**
* @ClassName: BaseEntity 
* @Description: 实体基础属性
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年11月22日 
*
 */
public class BaseEntity {

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