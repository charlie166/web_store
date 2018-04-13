package cn.charlie166.web.ruizhuo.tool;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.charlie166.web.base.tool.SpringContextUtils;
import cn.charlie166.web.ruizhuo.service.inter.SystemDictionaryService;

import com.google.common.collect.Lists;

/**
* @ClassName: DictionaryMap 
* @Description: 数据字典映射工具类
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年4月13日 
*
 */
public class DictionaryMap {

	/**静态常量，用于保存所有数据字典**/
	private final static List<Map<String, Object>> dictList = Lists.newCopyOnWriteArrayList();
	/**是否应该加载数据字典值**/
	private static volatile boolean shouldLoad = true;
	
	/**
	* @Title: refresh 
	* @Description: 重新从数据库加载数据字典
	 */
	public static void refresh(){
		DictionaryMap.dictList.clear();
		SystemDictionaryService dictionaryService = SpringContextUtils.getBean(SystemDictionaryService.class);
		DictionaryMap.dictList.addAll(dictionaryService.getAll());
	}
	
	/**
	* @Title: empty 
	* @Description: 清空数据字典缓存
	 */
	public static void empty(){
		DictionaryMap.dictList.clear();
		DictionaryMap.shouldLoad = false;
	}
	
	/**
	* @Title: getDictByCode
	* @Description: 获取指定数据字典值
	* @param key 数据字典代码
	* @return
	 */
	public static List<Map<String, Object>> getDictByCode(String code){
		if(DictionaryMap.shouldLoad){
			DictionaryMap.refresh();
		}
		return DictionaryMap.dictList.stream().filter(one -> one != null && code.equals(one.get("")))
			.collect(Collectors.toList());
	}
	
}