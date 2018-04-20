package cn.charlie166.web.store.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.common.collect.Maps;

import cn.charlie166.web.base.tool.SpringContextUtils;
import cn.charlie166.web.store.constant.CustomException;
import cn.charlie166.web.store.constant.ExceptionCodes;
import cn.charlie166.web.store.constant.KeyConstant;
import cn.charlie166.web.store.constant.ResponseCodes;
import cn.charlie166.web.store.domain.dto.MsgDTO;
import cn.charlie166.web.store.tools.JsonUtils;
import cn.charlie166.web.store.tools.MsgPropertyPlaceholder;
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
		logger.error(String.format("请求[%s]出现异常", url), exception);
		this.response.setCharacterEncoding("UTF-8");
		MsgDTO<Object> msg = new MsgDTO<Object>();
		msg.setCode(ResponseCodes.FAIL);
		/**用于异常的视图解析**/
		InternalResourceViewResolver beanResolver = SpringContextUtils.getBean(InternalResourceViewResolver.class);
		HashMap<String, Object> map = Maps.newHashMap();
		String viewName = "";
		if(exception instanceof CustomException){
			CustomException ce = (CustomException) exception;
			if(ExceptionCodes.COMMON_DATA_ABSENT.equals(ce.getCode())){
				viewName = "error/404";
			}
			map.put(KeyConstant.CUSTOM_EXCEP_CODE, ce.getCode());
//			msg.setContent(ce.getCode());
			msg.setMsg(StringUtils.hasContent(ce.getMessage()) ? ce.getMessage() : MsgPropertyPlaceholder.getStringValue(ce.getCode()));
		} else {/**其他异常**/
			viewName = "error/500";
			msg.setMsg(exception.getMessage());
			map.put(KeyConstant.ERROR_500_MSG, msg.getMsg());
		}
		boolean shouldReturnJson = true;
		/**如果是页面请求出现异常，导向错误提示页面**/
		if(WebUtils.isPageRequest(this.request)){
			try {
				if(StringUtils.isNullOrTrimBlank(viewName)){
					viewName = "error/500";
				}
				beanResolver.resolveViewName(viewName, Locale.getDefault()).render(map, this.request, this.response);
				shouldReturnJson = false;
			} catch (Exception e) {
				e.printStackTrace();
				msg.setMsg(e.getMessage());
			}
		}
		if (shouldReturnJson) {/**其他请求返回JSON数据**/
			try (PrintWriter writer = this.response.getWriter()) {
				writer.write(JsonUtils.toJson(msg));
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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
	
	/**
	* @Title: responseFile 
	* @Description: 响应文件流
	* @param path 文件路径
	* @param responseName 文件名称
	 */
	protected void responseFile(Path path, String responseName){
		if(path != null && Files.exists(path) && Files.isRegularFile(path)){
			if(Files.isReadable(path)){
				try {
					final String userAgent = this.request.getHeader("USER-AGENT");
					this.response.setContentType("application/octet-stream");
					String showName = StringUtils.hasContent(responseName) ? responseName : path.getFileName().toString();
					StringBuilder headStr = new StringBuilder("attachment; filename=\"");
					if (StringUtils.contains(userAgent, "Mozilla")) {/** google,火狐浏览器 ***/
						headStr.append(new String(showName.getBytes(Charset.defaultCharset()), "ISO8859-1"));
					} else {/**	IE 及  其他 ****/
						headStr.append(URLEncoder.encode(showName, "UTF-8"));
					}
					this.response.setHeader("Content-Disposition", headStr.append("\"").toString());;
					ServletOutputStream os = this.response.getOutputStream();
					Files.copy(path, os);
					os.flush();
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw CustomException.instance(ExceptionCodes.COMMON_IO_EXCEPTION, e);
				}
			}
		}
	}
}