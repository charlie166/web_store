package cn.charlie166.web.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description 首页请求控制器
 * @author <a href="mailto:charlie166@163.com">李阳</a> 
 * @version 1.0, 2017年11月14日
 * @see     
 * @since   web 1.0
 */
@RequestMapping(value = "/home")
@Controller
public class HomeController extends BaseController {

	/**
	 * @description
	 * @author <a href="mailto:charlie166@163.com">李阳</a> 
	 * @return 
	 * @since web 1.0.0
	 */
	@RequestMapping(value = "/welcome.do")
	public ModelAndView homePage() {
		ModelAndView view = new ModelAndView("index/home");
		return view;
	}
}