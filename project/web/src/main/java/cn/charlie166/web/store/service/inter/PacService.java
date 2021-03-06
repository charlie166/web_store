package cn.charlie166.web.store.service.inter;

import java.util.List;

import cn.charlie166.web.store.constant.CustomException;
import cn.charlie166.web.store.domain.dto.PacDTO;
import cn.charlie166.web.store.domain.po.PacModel;

/**
* @ClassName: PacService
* @Description: PAC(代理自动配置)服务接口
* @company
* @author liyang
* @Email charlie166@163.com
* @date 2017年12月5日
*
 */
public interface PacService {

	/**
	* @Title: insertOne 
	* @Description: 添加一条PAC记录
	* @param pac
	* @return
	 */
	int insertOne(PacModel pac) throws CustomException;
	
	/**
	* @Title: insertBatch 
	* @Description: 批量添加PAC记录
	* @param pacList
	* @return 影响条数
	 */
	int insertBatch(List<PacModel> pacList) throws CustomException;
	
	/**
	 * @description 获取所有保存了的配置数据
	 * @author <a href="mailto:charlie166@163.com">李阳</a> 
	 * @return 
	 * @since web 1.0.0
	 */
	List<PacDTO> all();

	/**
	* @Title: add 
	* @Description: 新增一条记录
	* @param dto
	* @return
	 */
	PacDTO add(PacDTO dto);
	
	/**
	* @Title: online 
	* @Description: 获取在线PAC规则字符串
	* @return
	 */
	String online();
}