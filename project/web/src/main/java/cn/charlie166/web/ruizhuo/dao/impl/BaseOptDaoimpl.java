package cn.charlie166.web.ruizhuo.dao.impl;

import java.util.List;
import java.util.Map;
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

}