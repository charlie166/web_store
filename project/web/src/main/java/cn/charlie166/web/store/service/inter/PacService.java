package cn.charlie166.web.store.service.inter;

import java.util.List;

import cn.charlie166.web.store.constant.CustomException;
import cn.charlie166.web.store.domain.po.PacModel;

/**
* @ClassName: PacService 
* @Description: PAC服务接口
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
	public int insertOne(PacModel pac) throws CustomException;
	
	/**
	* @Title: insertBatch 
	* @Description: 批量添加PAC记录
	* @param pacList
	* @return
	 */
	public int insertBatch(List<PacModel> pacList) throws CustomException;
}