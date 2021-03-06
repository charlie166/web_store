package cn.charlie166.web.ruizhuo.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.charlie166.web.ruizhuo.dao.inter.BaseOptDao;
import cn.charlie166.web.store.tools.StringUtils;

/**
* @ClassName: BaseOptDaoimpl 
* @Description: 基础数据库操作实现类
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年4月12日 
*
 */
@Repository
public class BaseOptDaoimpl implements BaseOptDao {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionFactory sf;
	
	@Override
	public int baseInsert(Map<String, Object> row, String tableName, List<String> ignoreField) {
		if(StringUtils.hasContent(tableName) && row != null && row.size() > 0){
			/**有效的字段列表**/
			List<String> fieldList = row.keySet().stream().filter(one -> StringUtils.hasContent(one) &&
					(ignoreField == null || !ignoreField.contains(one))).collect(Collectors.toList());
			/**字段字符串**/
			String fieldStr = fieldList.stream().collect(Collectors.joining(", "));
			String valStr = fieldList.stream().map(one -> {return "#{" + one + "}";})
				.collect(Collectors.joining(", "));
			StringBuilder sql = new StringBuilder();
			sql.append(" INSERT INTO ").append(tableName).append("(").append(fieldStr)
				.append(") VALUES (").append(valStr).append(")");
			row.put("_sql", sql.toString());
			logger.debug("执行sql:" + sql);
			return sf.openSession().insert("cn.charlie166.web.store.dao.DemoDao.directInsertBySql", row);
		}
		return 0;
	}
	
	@Override
	public int baseBatchInsert(List<Map<String, Object>> rows, String tableName, List<String> ignoreField){
		if(StringUtils.hasContent(tableName) && rows != null && rows.size() > 0){
			/**获取字段**/
			Set<String> fieldSet = rows.stream().collect(() -> new HashSet<String>(), (set, item) -> set.addAll(item.keySet().stream().filter(one -> StringUtils.hasContent(one) &&
				(ignoreField == null || !ignoreField.contains(one))).collect(Collectors.toSet())), (one, two) -> one.addAll(two));
			/**每次执行插入的条数**/
			int everySize = 30;
			if(rows.size() <= everySize){/**一次性添加**/
				this.batchInsertOnce(rows, tableName, fieldSet);
			} else {
				/**当前计数插入索引**/
				int index = 0;
				do {
					int endIndex = index + everySize > rows.size() ? rows.size() : (index + everySize);
					this.batchInsertOnce(rows.subList(index, endIndex), tableName, fieldSet);
					index += everySize;
				} while (index  <= rows.size());
			}
		}
		return 0;
	}
	
	/**
	* @Title: batchInsertOnce 
	* @Description: 直接插入列表所有数据，不会截取列表插入
	* @param rows 列表数据
	* @param tableName 目标表名
	* @param fieldSet 表内字段集合
	* @return
	 */
	private int batchInsertOnce(List<Map<String, Object>> rows, String tableName, Set<String> fieldSet){
		if(rows != null && rows.size() > 0){
			List<String> valList = new ArrayList<String>(rows.size());
			Map<String, Object> param = new HashMap<String, Object>();
			/**字段字符串**/
			String fieldStr = fieldSet.stream().collect(Collectors.joining(", "));
			for(int i = 0; i < rows.size(); i++){
				final String s = "item_" + i;
				String valStr = fieldSet.stream().map(one -> {return "#{" + s + "." + one + "}";})
					.collect(Collectors.joining(", "));
				valList.add("(" + valStr + ")");
				param.put(s, rows.get(i));
			}
			if(valList.size() > 0){
				StringBuilder sql = new StringBuilder();
				sql.append(" INSERT INTO ").append(tableName).append("(").append(fieldStr)
					.append(") VALUES ").append(valList.stream().collect(Collectors.joining(", ")));
				param.put("_sql", sql.toString());
				logger.debug("执行sql:" + sql);
			}
		}
		return 0;
	}

	@Override
	public int baseUpdateById(Map<String, Object> row, String idName,
			String tableName, List<String> ignoreField, boolean ignoreEmptyStr) {
		if(StringUtils.hasContent(tableName) && StringUtils.hasContent(idName) &&
			row != null && row.containsKey(idName) && row.get(idName) != null){
			/**有效的字段列表**/
			List<String> fieldList = row.keySet().stream().filter(one -> StringUtils.hasContent(one) &&
				(ignoreField == null || !ignoreField.contains(one) && !idName.equals(one))).collect(Collectors.toList());
			List<String> fieldSetList = new ArrayList<String>();
			for(int i = 0; i < fieldList.size(); i++){
				String f = fieldList.get(i);
				Object val = row.get(f);
				if(val != null){
					if(!ignoreEmptyStr || (ignoreEmptyStr && StringUtils.hasContent(val))){
						fieldSetList.add(f + " = #{" + f + "}");
					}
				}
			}
			if(fieldSetList.size() > 0){
				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE ").append(tableName).append(" SET ");
				sql.append(fieldSetList.stream().collect(Collectors.joining(", ")));
				sql.append(" WHERE ").append(idName).append(" = #{").append(idName).append("}");
				row.put("_sql", sql.toString());
				logger.debug("执行sql:" + sql);
				return sf.openSession().insert("cn.charlie166.web.store.dao.DemoDao.directUpdateSql", row);
			}
		}
		return 0;
	}

	@Override
	public int baseUpdateById(Map<String, Object> row, String idName,
			String tableName, List<String> ignoreField) {
		return this.baseUpdateById(row, idName, tableName, ignoreField, true);
			
	}

	@Override
	public int baseUpdateById(Map<String, Object> row, String idName,
			String tableName) {
		return this.baseUpdateById(row, idName, tableName, Collections.emptyList());
	}

	@Override
	public int baseUpdateById(Map<String, Object> row, String idName,
			String tableName, boolean ignoreEmptyStr) {
		return this.baseUpdateById(row, idName, tableName, Collections.emptyList(), ignoreEmptyStr);
	}

}