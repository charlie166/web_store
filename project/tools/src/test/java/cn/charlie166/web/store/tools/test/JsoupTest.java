package cn.charlie166.web.store.tools.test;

import java.io.IOException;
import java.net.URLDecoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import cn.charlie166.web.store.constant.CustomException;
import cn.charlie166.web.store.tools.FileUtils;

/**
* @ClassName: JsoupTest 
* @Description: JSOUP工具测试类
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年11月16日 
*
 */
public class JsoupTest {

	@Test
	public void testHtml(){
		try {
			Document doc = Jsoup.connect("https://item.jd.com/5089273.html").get();
			try {
				FileUtils.storeFile("F:/upload/test.html", doc.toString());
			} catch (CustomException e) {
				e.printStackTrace();
			}
			System.out.println("title:" + doc.title());
			Elements newsHeadlines = doc.select("#mp-itn b a");
			for (Element headline : newsHeadlines) {
				String linkTitle = headline.attr("title");
				String absUrl = headline.absUrl("href");
				String string = String.format("%s\t%s", linkTitle, URLDecoder.decode(absUrl, "UTF-8"));
				System.out.println(string);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}