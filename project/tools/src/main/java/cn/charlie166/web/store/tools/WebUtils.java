package cn.charlie166.web.store.tools;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cn.charlie166.web.store.constant.CustomException;
import cn.charlie166.web.store.constant.ExceptionCodes;

/**
* @ClassName: WebUtils 
* @Description: 网络工具类
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年12月7日 
*
 */
public class WebUtils {

	/**
	* @Title: getContentByLink 
	* @Description: 获取指定链接地址的内容
	* @param url 链接
	* @return
	 */
	public static String getContentByLink(String url) throws CustomException {
		if(StringUtils.hasContent(url)){
			if(!url.startsWith("http://") && !url.startsWith("https://")){
				throw CustomException.instance(ExceptionCodes.WEB_NEED_PROTOCAL);
			}
			try {
				Document doc = Jsoup.connect(url).get();
				return doc.toString();
			} catch (IOException e) {
				throw CustomException.instance(ExceptionCodes.COMMON_IO_EXCEPTION, e);
			}
		}
		throw CustomException.instance(ExceptionCodes.COMMON_PARAM_ABSENT);
	}
}