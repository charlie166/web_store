package cn.charlie166.web.store.test.pac;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.charlie166.web.store.service.inter.DemoService;
import cn.charlie166.web.store.test.ParentTest;

/**
* @ClassName: TestOfPac 
* @Description: PAC 接口测试类
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年12月5日 
*
 */
public class TestOfPac extends ParentTest {

	@Autowired
	private DemoService demoService;
	
	@Test
	public void test(){
		logger.debug(String.format("测试demo结果:$s", demoService.selectDemoCount()));
	}
}