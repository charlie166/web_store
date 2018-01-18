package cn.charlie166.web.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.charlie166.web.store.domain.po.Bookmark;
import cn.charlie166.web.store.service.inter.BookmarkService;

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

	@Autowired
	private BookmarkService service;
	
	/**
	 * @description 新增书签页面
	 * @author <a href="mailto:charlie166@163.com">李阳</a> 
	 * @return 
	 * @since web 1.0.0
	 */
	@RequestMapping(value = "/page/add.do")
	public ModelAndView addPage() {
		ModelAndView view = new ModelAndView("bookmark/add");
		return view;
	}
	
	/**
	* @Title: addSubmit 
	* @Description: 新增提交
	* @param data
	* @return
	 */
	@RequestMapping(value = "/json/submit.do")
	@ResponseBody
	public Long addSubmit(Bookmark data){
		return service.addSubmit(data);
	}
}