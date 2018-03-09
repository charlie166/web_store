package cn.charlie166.web.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.charlie166.web.store.service.inter.DemoService;

/**
 * @description 首页请求控制器
 * @author <a href="mailto:charlie166@163.com">李阳</a> 
 * @version 1.0, 2017年11月14日
 * @see     
 * @since   web 1.0
 */
@Controller
public class HomeController extends BaseController {

	@Autowired
	private DemoService demoService;
	
	/**
	 * @description
	 * @author <a href="mailto:charlie166@163.com">李阳</a> 
	 * @return 
	 * @since web 1.0.0
	 */
	@RequestMapping(value = {"/home/welcome.do", "/"})
	public ModelAndView homePage() {
		ModelAndView view = new ModelAndView("index/home");
		view.addObject("demoCount", Integer.valueOf(demoService.selectDemoCount()));
		return view;
	}
	
}