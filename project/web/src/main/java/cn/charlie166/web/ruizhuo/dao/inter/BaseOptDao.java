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

	/**
	* @Title: baseUpdateById 
	* @Description: 通过ID更新一条数据
	* @param row 数据
	* @param idName 条件中ID的字段名称
	* @param tableName 表名
	* @param ignoreField 忽略的字段
	* @param ignoreEmptyStr 是否忽略空白字符串
	* @return
	 */
	public int baseUpdateById(Map<String, Object> row, String idName, String tableName, List<String> ignoreField, boolean ignoreEmptyStr);
	
	/**
	* @Title: baseUpdateById 
	* @Description: 通过ID更新一条数据, 忽略空白字符串
	* @param row 数据
	* @param idName 条件中ID的字段名称
	* @param tableName 表名
	* @param ignoreField 忽略的字段
	* @return
	 */
	public int baseUpdateById(Map<String, Object> row, String idName, String tableName, List<String> ignoreField);
	
	/**
	* @Title: baseUpdateById 
	* @Description: 通过ID更新一条数据, 忽略空白字符串
	* @param row 数据
	* @param idName 条件中ID的字段名称
	* @param tableName 表名
	* @param ignoreEmptyStr 是否忽略空白字符串
	* @return
	 */
	public int baseUpdateById(Map<String, Object> row, String idName, String tableName, boolean ignoreEmptyStr);
	
	/**
	* @Title: baseUpdateById 
	* @Description: 通过ID更新一条数据, 忽略空白字符串
	* @param row 数据
	* @param idName 条件中ID的字段名称
	* @param tableName 表名
	* @return
	 */
	public int baseUpdateById(Map<String, Object> row, String idName, String tableName);
}