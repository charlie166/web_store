package cn.charlie166.web.store.test.pac;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.charlie166.web.store.constant.CustomException;
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
		logger.debug(String.format("测试demo结果:%s", demoService.selectDemoCount()));
	}
	
	/**
	* @Title: testBatchInsertPac 
	* @Description: 测试批量添加
	 */
	@Test
	public void testBatchInsertPac() {
		PacModel pm1 = new PacModel();
		pm1.setDomain("cdn-www.activiti.org");
		pm1.setFlag(Boolean.TRUE);
		PacModel pm2 = new PacModel();
		pm2.setFlag(Boolean.TRUE);
		pm2.setDomain("pinterest.com");
		try {
			pacService.insertBatch(Arrays.asList(pm1, pm2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	* @Title: testBatchInsertFromFile 
	* @Description: 测试读取PAC文件批量添加
	 */
	@Test
	public void testBatchInsertFromFile(){
		Path path = Paths.get("F:/charlie/documents/ss/Shadowsocks-4.0.4/pac.txt");
		if(path.isAbsolute()){
			try {
				List<String> strList = Files.lines(path).filter(s -> s != null && s.trim().startsWith("\"")).collect(Collectors.toList());
				Set<String> strSet = strList.stream().map(s -> {
					int firstIndex = s.indexOf("\"");
					return s.substring(firstIndex + 1, s.indexOf("\"", firstIndex + 1));
				}).collect(Collectors.toSet());
				String domainRegex = "^(?=^.{3,255}$)[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+$";
				List<PacModel> list = strSet.stream().map(one -> {
					PacModel pm = new PacModel();
					pm.setDomain(one);
					pm.setFlag(one.matches(domainRegex) ? Boolean.TRUE : Boolean.FALSE);
					return pm;
				}).collect(Collectors.toList());
				pacService.insertBatch(list);
			} catch (CustomException | IOException e) {
				e.printStackTrace();
			}
		}
	}
}