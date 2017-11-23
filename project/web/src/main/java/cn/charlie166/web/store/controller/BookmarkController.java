package cn.charlie166.web.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description 书签控制器
 * @author <a href="mailto:charlie166@163.com">李阳</a> 
 * @version 1.0, 2017年11月23日
 * @see     
 * @since   web 1.0
 */
@RequestMapping(value = "/bookmark")
@Controller
public class BookmarkController extends BaseController{

	/**
	 * @description 新增书签页面
	 * @author <a href="mailto:charlie166@163.com">李阳</a> 
	 * @return 
	 * @since web 1.0.0
	 */
	public ModelAndView addPage() {
		ModelAndView view = new ModelAndView("bookmark/add");
		return view;
	}
}