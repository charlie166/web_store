package cn.charlie166.web.store.tools;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

import org.apache.taglibs.standard.resources.Resources;
import org.apache.taglibs.standard.util.UrlUtil;
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
	
	 /**
    * @Title: resolveCustomUrl 
    * @Description: 自定义处理地址
    * @param url 标签内设置的地址值
    * @param context 标签内设置的上下文
    * @param pageContext 
    * @return
    * @throws JspException
     */
    public static String resolveCustomUrl(String url, String context, HttpServletRequest request) throws JspException {
        /*** don't touch absolute URLs ***/
        if (UrlUtil.isAbsoluteUrl(url)) {
            return url;
        }
        String proxyContext = request.getHeader("Proxy-context");
        if (context == null) {
            if (url.startsWith("/")) {
                return WebUtils.dealUrl(proxyContext, (request.getContextPath() + url));
            } else {
                return WebUtils.dealUrl(proxyContext, url);
            }
        } else {
        	/**此处的url 和  context 必须以斜杠开头**/
        	if(!context.startsWith("/"))
        		context = "/" + context;
        	if(!url.startsWith("/")){
        		url = "/" + url;
        	}
            if (!context.startsWith("/") || !url.startsWith("/")) {
                throw new JspTagException(Resources.getMessage("IMPORT_BAD_RELATIVE"));
            }
            if (context.endsWith("/") && url.startsWith("/")) {
                // Don't produce string starting with '//', many
                // browsers interpret this as host name, not as
                // path on same host. Bug 22860
                // Also avoid // inside the url. Bug 34109
                return WebUtils.dealUrl(proxyContext, (context.substring(0, context.length() - 1) + url));
            } else {
                return WebUtils.dealUrl(proxyContext, (context + url));
            }
        }
    }
    
    /**
     * @Title: dealUrl 
     * @Description: 处理代理地址
     * @param proxyContext 代理的上下文
     * @param url 相对路径地址
     * @return
      */
     public static String dealUrl(String proxyContext, String url){
     	if(StringUtils.hasContent(url)){
     		if(proxyContext == null)
     			proxyContext = "";
     		/**替换到链接中的斜杠及反斜杠**/
     		proxyContext = proxyContext.replaceAll("\\\\+", "/").replaceAll("^/+$", "/");
     		url = url.replaceAll("\\\\+", "/").replaceAll("^/+$", "/");
     		if(StringUtils.hasContent(proxyContext) && !proxyContext.startsWith("/"))
     			proxyContext = "/" + proxyContext;
     		if(StringUtils.hasContent(proxyContext) && proxyContext.endsWith("/"))
     			proxyContext = proxyContext.substring(0, proxyContext.length() - 1);
     		return proxyContext + (url.startsWith("/") ? "" : "/") + url;
     	}
     	return "/";
     }
     
     /**
      * @Title: handleSessionId 
      * @Description: 处理掉链接中的jsessionid
      * @param url
       */
      public static String wipeSessionId(String url){
      	/**去掉链接中的jsessionid**/
          String s = ";jsessionid=";
          if(StringUtils.hasContent(url) && url.contains(s)){
          	int startIndex = url.indexOf(s);
          	if(startIndex > 0){
          		/**链接中的主要分隔符**/
          		char [] ca = {',', ';', '&', '?'};
          		int endIndex = url.indexOf(".", startIndex);
          		for(char c: ca){
          			endIndex = url.indexOf(c, startIndex);
          		}
          		/**如果未找到特殊分隔符，以字符串最后结尾为分割**/
          		if(endIndex == -1){
          			endIndex = url.length();
          		}
          		if(endIndex > startIndex){
          			url = url.substring(0, startIndex) + url.substring(endIndex);
          		}
          	}
          }
          return url;
      }
}