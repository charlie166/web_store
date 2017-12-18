package cn.charlie166.web.store.domain.dto;

import java.time.LocalDateTime;

import lombok.Getter;

/**
 * @description 代理自动配置(PAC)数据传输模型
 * @author <a href="mailto:charlie166@163.com">李阳</a> 
 * @version 1.0, 2017年12月18日
 * @see     
 * @since   domain 1.0
 */
public class PacDTO {

	/**域名地址**/
	private @Getter String domain;
	/**描述**/
	private String description;
	/**状态(1: 有效; 0: 无效)**/
	private Boolean flag;
	/**主键ID**/
	private Long id;
	/**创建时间**/
	private LocalDateTime createTime;
}