package cn.charlie166.web.store.tools;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
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

	private static final String PROXY_HOST = "t.charlie166.xyz";
	
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
    * @param request 请求 
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
	
	/***
	* @Title: isPageRequest 
	* @Description: 判断请求是否为页面的请求
	* @param request
	* @return
	 */
	public static boolean isPageRequest(HttpServletRequest request){
		if(request != null){
			String header = request.getHeader("accept");
			return StringUtils.hasContent(header) && header.startsWith("text/html");
		}
		return false;
	}
	
	/**
	* @Title: weixinGet 
	* @Description: 微信GET请求
	* @param url 请求地址, 完整的地址
	* @return 请求响应
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static HttpResponse weixinGet(String url) throws IOException{
		if(!StringUtils.hasContent(url)){
			throw CustomException.instance(ExceptionCodes.COMMON_PARAM_ABSENT);
		}
		HttpGet hg = new HttpGet(url);
		HttpHost proxy = new HttpHost(PROXY_HOST, 8888);
		RequestConfig requestConfig = RequestConfig.custom().setProxy(proxy).build();
		hg.setConfig(requestConfig);
		HttpClient hc = HttpClientBuilder.create().build();
		HttpResponse httpResponse = hc.execute(hg);
		return httpResponse;
	}
	
	/**
	* @Title: weixinPost 
	* @Description: 微信post请求
	* @param url 请求地址，完整地址
	* @param body 发送数据
	* @return 响应结果
	* @throws IOException
	 */
	public static HttpResponse weixinPost(String url, Object body) throws IOException{
		if(!StringUtils.hasContent(url)){
			throw CustomException.instance(ExceptionCodes.COMMON_PARAM_ABSENT);
		}
		HttpPost hp = new HttpPost(url);
		HttpHost proxy = new HttpHost(PROXY_HOST, 8888);
		RequestConfig requestConfig = RequestConfig.custom().setProxy(proxy).build();
		hp.setConfig(requestConfig);
		StringEntity se = new StringEntity(JsonUtils.toJson(body), Charset.defaultCharset());
		se.setContentType("application/json");
		hp.setEntity(se);
		HttpClient hc = HttpClientBuilder.create().build();
		return hc.execute(hp);
	}
}