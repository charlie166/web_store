package cn.charlie166.web.store.test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @description 项目单元测试父类
 * @author <a href="mailto:charlie166@163.com">李阳</a> 
 * @version 1.0, 2017年11月11日
 * @see     
 * @since   web 1.0
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring/applicationContext-dao.xml", "classpath:config/spring/applicationContext-tx.xml"})
public class ParentTest extends AbstractJUnit4SpringContextTests {

	@Before
	public void beforeTest(){
		System.setProperty("log4j.configurationFile", "classpath:config/log4j2.xml");
		System.setProperty("log4j2.debug", "true");
	}
}