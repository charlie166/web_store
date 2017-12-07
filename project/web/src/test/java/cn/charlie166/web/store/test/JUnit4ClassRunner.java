package cn.charlie166.web.store.test;

import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
* @ClassName: JUnit4ClassRunner 
* @Description: 自定义单元测试，设置自定义日志配置
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年12月7日 
*
 */
public class JUnit4ClassRunner extends SpringJUnit4ClassRunner {

	static {
		System.setProperty("log4j.configurationFile", "classpath:config/log4j2.xml");
//		System.setProperty("log4j2.debug", "true");
	}
	
	public JUnit4ClassRunner(Class<?> clazz) throws InitializationError {
		super(clazz);
	}

}	