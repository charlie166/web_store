package cn.charlie166.web.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

	/**
	* @Title: page404 
	* @Description: 404未找到的页面跳转
	* @return
	 */
	@RequestMapping(value = "/404.do")
	public ModelAndView page404(){
		ModelAndView view = new ModelAndView("error/404");
		return view;
	}
}