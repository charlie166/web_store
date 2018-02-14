package cn.charlie166.web.store.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cn.charlie166.web.store.constant.CustomException;
import cn.charlie166.web.store.constant.KeyConstant;
import cn.charlie166.web.store.tools.StringUtils;
import cn.charlie166.web.store.tools.WebUtils;

/**
 * @description 基础控制器，所有控制器均需要继承此类
 * @author <a href="mailto:charlie166@163.com">李阳</a> 
 * @version 1.0, 2017年11月14日
 * @see     
 * @since   web 1.0
 */
public class BaseController {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	protected HttpServletRequest request;
	@Autowired
	protected HttpServletResponse response;
	
	/**
	 * @description 统一异常处理方法
	 * @author <a href="mailto:charlie166@163.com">李阳</a> 
	 * @param exception 
	 * @since web 1.0.0
	 */
	@ExceptionHandler
	public void exceptionHandler(Exception exception) {
		String url = this.request.getRequestURI().toString();
		if(exception instanceof CustomException){
			CustomException ce = (CustomException) exception;
			this.request.setAttribute(KeyConstant.CUSTOM_EXCEP_CODE, ce.getCode());
		} else {
		}
		/**如果是页面请求出现异常，导向错误提示页面**/
		if(url.contains("/page/")){
			this.response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			/*try {
				this.response.sendRedirect(this.getBaseUrl() + "error/500.do");
			} catch (IOException e) {
				e.printStackTrace();
			}*/
		}
//		exception.printStackTrace();
	}
	
	/**
	* @Title: getExceptionCode 
	* @Description: 从当前请求提取错误码信息
	* @return
	 */
	protected String getExceptionCode(){
		Object attr = this.request.getAttribute(KeyConstant.CUSTOM_EXCEP_CODE);
		if(StringUtils.hasContent(attr))
			return attr.toString();
		return "";
	}
	
	/**
	* @Title: getBaseUrl 
	* @Description: 获取工程路径    
	* @return 如 http://xxx.com:8080/web/
	 */
	protected String getBaseUrl(){
		try {
			return WebUtils.resolveCustomUrl("/", null, request);
		} catch (JspException e) {
			e.printStackTrace();
			/**出现异常，返回斜杠***/
			return "/";
		}
	}
}