package cn.charlie166.web.ruizhuo.service.inter;

import java.util.List;
import java.util.Map;

import cn.charlie166.web.base.service.inter.BaseService;

/**
* @ClassName: SystemDictionaryService 
* @Description: 系统数据字典服务
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年4月13日 
*
 */
public interface SystemDictionaryService extends BaseService{

	/**
	* @Title: reset 
	* @Description: 重置(清空)数据字典
	 */
	public void reset();
	
	/**
	* @Title: getAll 
	* @Description: 获取所有可用数据字典
	* @return
	 */
	public List<Map<String, Object>> getAll();
}