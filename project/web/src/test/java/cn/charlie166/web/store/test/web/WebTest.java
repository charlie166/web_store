package cn.charlie166.web.store.test.web;

import org.junit.Test;

import cn.charlie166.web.store.constant.CustomException;
import cn.charlie166.web.store.test.ParentTest;
import cn.charlie166.web.store.tools.WebUtils;

/**
* @ClassName: WebTest 
* @Description: 网络单元测试类
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年12月7日 
*
 */
public class WebTest extends ParentTest {

	/**
	* @Title: testGetContent 
	* @Description: 获取网页内容
	 */
	@Test
	public void testGetContent(){
		String url = "https://charlie166.ddns.net/";
		try {
			logger.debug("结果：\r\n" + WebUtils.getContentByLink(url));
		} catch (CustomException e) {
			e.printStackTrace();
		}
	}
}