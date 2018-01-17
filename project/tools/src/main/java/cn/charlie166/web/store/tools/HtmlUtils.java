package cn.charlie166.web.store.tools;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @description 网页标签内容工具类
 * @author <a href="mailto:charlie166@163.com">李阳</a> 
 * @version 1.0, 2018年1月17日
 * @see     
 * @since   tools 1.0
 */
public class HtmlUtils {

	/**
	 * @description 判断给定html标签字符串有内容
	 * @author <a href="mailto:charlie166@163.com">李阳</a> 
	 * @param html
	 * @return 
	 * @since tools 1.0.0
	 */
	public static boolean hasContent(String html) {
		if(StringUtils.hasContent(html)) {
			Document doc = Jsoup.parse(html);
			return StringUtils.hasContent(doc.text());
		}
		return false;
	}
}