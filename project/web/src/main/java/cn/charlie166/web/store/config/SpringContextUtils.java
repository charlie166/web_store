package cn.charlie166.web.store.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
* @ClassName: SpringContextUtils 
* @Description: spring上下文工具类
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年3月16日 
*
 */
public class SpringContextUtils implements ApplicationContextAware {

	/****/
	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContextUtils.applicationContext = applicationContext;
	}

	/**
	* @Title: getBean 
	* @Description: 获取指定类型的bean对象
	* @param requiredType bean类型
	* @return
	* @throws BeansException
	 */
	public static <T> T getBean(Class<T> requiredType) throws BeansException {
		return SpringContextUtils.applicationContext.getBean(requiredType);
	}
}