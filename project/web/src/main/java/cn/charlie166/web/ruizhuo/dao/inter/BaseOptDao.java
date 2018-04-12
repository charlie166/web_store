package cn.charlie166.web.ruizhuo.dao.inter;

import java.util.List;
import java.util.Map;

/**
* @ClassName: BaseOptDao 
* @Description: 基本操作数据库接口
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年4月12日 
*
 */
public interface BaseOptDao {

	/**
	* @Title: baseInsert 
	* @Description: 基本插入数据操作
	* @param row 一条数据
	* @param tableName 目标表名
	* @param ignoreField 需要排除的字段
	* @return
	 */
	public int baseInsert(Map<String, Object> row, String tableName, List<String> ignoreField);
}