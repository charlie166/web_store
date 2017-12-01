package cn.charlie166.web.store.tools;

import com.google.gson.Gson;

/**
* @ClassName: JsonUtils 
* @Description: JSON工具类
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年12月1日 
*
 */
public class JsonUtils {

	/**
	* @Title: toJson 
	* @Description: 将对象转为JSON字符串
	* @param obj
	* @return
	 */
	public static String toJson(Object obj){
		if(obj != null){
			Gson gson = new Gson();
			return gson.toJson(obj);
		}
		return "";
	}
	
	/**
	* @Title: fromJson 
	* @Description: 将JSON字符串转换为指定数据类型
	* @param json JSON字符串
	* @param cls 指定类型
	* @return
	 */
	public static <T> T fromJson(String json, Class<T> cls){
		if(StringUtils.hasContent(json) && cls != null){
			Gson gson = new Gson();
			return gson.fromJson(json, cls);
		}
		return null;
	}
}