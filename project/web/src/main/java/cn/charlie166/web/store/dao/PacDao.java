package cn.charlie166.web.store.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.charlie166.web.store.domain.annotation.ParamCheck;
import cn.charlie166.web.store.domain.enums.ParamCheckType;
import cn.charlie166.web.store.domain.po.PacModel;

/**
* @ClassName: PacDao 
* @Description: PAC数据库操作接口
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年12月5日 
*
 */
public interface PacDao {

	/**
	* @Title: batchInsert 
	* @Description: 批量添加PAC记录
	* @param list
	* @return
	 */
	public int batchInsert(@ParamCheck(type = ParamCheckType.LIST) @Param(value = "list") List<PacModel> list);
	
	/**
	* @Title: selectList 
	* @Description: 查询所有列表
	* @return
	 */
	public List<PacModel> selectAll();
}