package cn.charlie166.web.store.tools;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
* @ClassName: CustomPropertyPlaceholder 
* @Description: 自定义属性文件读取
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年1月12日 
*
 */
public class CustomPropertyPlaceholder extends PropertyPlaceholderConfigurer {

	private static final Map<String, String> propertyMap = new HashMap<String, String>();

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		for(Entry<Object, Object> entry: props.entrySet()){
			CustomPropertyPlaceholder.propertyMap.put(entry.getKey().toString(), entry.getValue().toString());
		}
	}
	
	/**
	* @Title: getStringValue 
	* @Description: 获取指定键的字符串值
	* @param key 如果不存在对应键，则返回null
	 */
	public static String getStringValue(String key){
		return CustomPropertyPlaceholder.propertyMap.get(key);
	}
	
	/**
	* @Title: getAllFields 
	* @Description: 返回所有的属性设置
	* @return
	 */
	public Map<String, String> getAllFields(){
		return CustomPropertyPlaceholder.propertyMap;
	}
}