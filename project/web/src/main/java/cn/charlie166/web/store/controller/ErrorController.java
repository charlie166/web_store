package cn.charlie166.web.store.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.charlie166.web.store.constant.KeyConstant;
import cn.charlie166.web.store.tools.StringUtils;

/**
* @ClassName: ErrorController 
* @Description: 错误相关控制器
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年11月15日 
*
 */
@RequestMapping(value = "/error")
@Controller
public class ErrorController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	* @Title: page404 
	* @Description: 404未找到的页面跳转
	* @return
	 */
	@RequestMapping(value = "/404.do")
	public ModelAndView page404(){
		StringBuffer url = this.request.getRequestURL();
		logger.debug(String.format("404了[%s]", url));
		ModelAndView view = new ModelAndView("error/404");
		return view;
	}
	
	/**
	* @Title: page500 
	* @Description: 服务器内部错误页面
	* @return
	 */
	@RequestMapping(value = "/500.do")
	public ModelAndView page500(){
		ModelAndView view = new ModelAndView("error/500");
		String ec = this.getExceptionCode();
		view.addObject(KeyConstant.ERROR_500_MSG, StringUtils.hasContent(ec) ? ec : "");
		return view;
	}
	
}