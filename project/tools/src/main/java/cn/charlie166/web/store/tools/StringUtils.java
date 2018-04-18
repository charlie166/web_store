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
	
	/**
	* @Title: hasContent 
	* @Description: 将对象转换为字符串判断是否有内容
	* @param obj
	* @return
	 */
	public static boolean hasContent(Object obj){
		return obj != null && StringUtils.hasContent(obj.toString());
	}
	
	/**
	* @Title: getLineSeparator 
	* @Description: 获取换行符
	* @return
	 */
	public static String getLineSeparator(){
		String string = System.getProperty("line.separator");
		return StringUtils.hasContent(string) ? string : "\r\n";
	}
	
	/**
	* @Title: contains 
	* @Description: 是否包含指定字符串
	* <p>A <code>null</code> String will return <code>false</code>.</p>
     *
     * <pre>
     * StringUtils.contains(null, *)     = false
     * StringUtils.contains(*, null)     = false
     * StringUtils.contains("", "")      = true
     * StringUtils.contains("abc", "")   = true
     * StringUtils.contains("abc", "a")  = true
     * StringUtils.contains("abc", "z")  = false
     * </pre>
	* @param str 
	* @param searchStr 查找的字符串
	* @return
	 */
	public static boolean contains(String str, String searchStr) {
		if (str == null || searchStr == null) {
			return false;
		}
		return str.indexOf(searchStr) >= 0;
    }
	
	/***
	* @Title: getStrFrom 
	* @Description: 从给定字符串中截取substr字符串后面的部分. 
	* ("a123qwe", "123") >> "qwe"
	* ("a123qwe", "vrf") >> ""
	* ("a123qwea12vrr", "a1") >> "3qwea12vrr" 
	* @param str 被截取字符串
	* @param substr 查找字符串
	* @return
	 */
	public static String getStrFrom(String str, String substr){
		if(StringUtils.hasContent(str) && StringUtils.hasContent(substr)){
			if(str.length() > substr.length()){
				int index = str.indexOf(substr);
				if(index > 0){
					return str.substring(index + substr.length());
				}
			}
		}
		return "";
	}
	
	/**
	* @Title: isInteger 
	* @Description: 校验给定字符串是整型
	* @param s 需要判断的字符串
	* @return
	 */
	public static boolean isInteger(String s){
		if(StringUtils.hasContent(s)){
//			return s.matches("[+-]?\\d+");
			try {
				Integer.parseInt(s);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}
}