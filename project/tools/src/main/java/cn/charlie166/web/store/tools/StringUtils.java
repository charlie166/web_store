package cn.charlie166.web.store.tools;

/**
 * @description 字符串工具类
 * @author <a href="mailto:charlie166@163.com">李阳</a> 
 * @version 1.0, 2017年11月11日
 * @see     
 * @since   tools 1.0
 */
public class StringUtils {

	/**
	 * @description 校验字符串是否为null或空字符串
	 * @author <a href="mailto:charlie166@163.com">李阳</a> 
	 * @param str
	 * @return 
	 * @since tools 1.0.0
	 */
	public static boolean isNullOrTrimBlank(String str) {
		return str == null || "".equals(str.trim());
	}
	
	/**
	 * @description 判断字符串是否有内容
	 * @author <a href="mailto:charlie166@163.com">李阳</a> 
	 * @param str
	 * @return 
	 * @since tools 1.0.0
	 */
	public static boolean hasContent(String str) {
		return !StringUtils.isNullOrTrimBlank(str);
	}
}