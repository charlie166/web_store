package cn.charlie166.web.store.test.pac;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.charlie166.web.store.domain.po.PacModel;
import cn.charlie166.web.store.service.inter.DemoService;
import cn.charlie166.web.store.service.inter.PacService;
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
	@Autowired
	private PacService pacService;
	
	@Test
	public void test(){
		logger.debug(String.format("测试demo结果:$s", demoService.selectDemoCount()));
	}
	
	@Test
	public void testBatchInsertPac() {
		PacModel pm1 = new PacModel();
		pm1.setDomain("cdn-www.activiti.org");
		PacModel pm2 = new PacModel();
		pm2.setDomain("pinterest.com");
		try {
			pacService.insertBatch(Arrays.asList(pm1, pm2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}