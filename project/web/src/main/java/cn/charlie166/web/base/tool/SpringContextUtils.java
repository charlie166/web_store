package cn.charlie166.web.base.tool;

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
		/**这种方式可以直接获取到web的应用上下文***/
//		T bean = ContextLoader.getCurrentWebApplicationContext().getBean(requiredType);
		T bean = SpringContextUtils.applicationContext.getBean(requiredType);
		return bean;
	}
}