package cn.charlie166.web.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.charlie166.web.base.controller.BaseController;
import cn.charlie166.web.common.domain.dto.PageDTO;
import cn.charlie166.web.store.domain.dto.BookmarkDTO;
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
	* @Title: indexPage 
	* @Description: 书签列表(首页)页面
	* @return
	 */
	@RequestMapping(value = {"/page/index.do", "/", "/page.do", "/page/list.do", ""})
	public ModelAndView indexPage(){
		ModelAndView mv = new ModelAndView("bookmark/index");
		return mv;
	}
	
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
	* @Title: detailPage 
	* @Description: 书签详情页面
	* @param id 书签ID
	* @return
	 */
	@RequestMapping(value = "/page/{id}/detail.do")
	public ModelAndView detailPage(@PathVariable String id) {
		ModelAndView mav = new ModelAndView();
		BookmarkDTO dto = service.detail(id);
		mav.setViewName("bookmark/detail");
		mav.addObject("bk", dto);
		return mav;
	}
	
	/**
	* @Title: editPage 
	* @Description: 编辑页面
	* @param id 书签ID
	* @return
	 */
	@RequestMapping(value = "/page/{id}/edit.do")
	public ModelAndView editPage(@PathVariable String id) {
		ModelAndView mav = new ModelAndView();
		BookmarkDTO dto = service.detail(id);
		mav.setViewName("bookmark/edit");
		mav.addObject("bk", dto);
		return mav;
	}
	
	/**
	* @Title: addSubmit 
	* @Description: 新增提交
	* @param data
	* @return
	 */
	@RequestMapping(value = "/json/add.do")
	@ResponseBody
	public Long addSubmit(BookmarkDTO data){
		return service.addSubmit(data);
	}
	
	/**
	* @Title: editSubmit 
	* @Description: 修改提交
	* @param data
	* @return
	 */
	@RequestMapping(value = "/json/edit.do")
	@ResponseBody
	public Long editSubmit(BookmarkDTO data){
		return service.editSubmit(data);
	}
	
	/**
	* @Title: page 
	* @Description: 书签分页查询
	* @param condition 查询条件
	* @return
	 */
	@RequestMapping(value = "/json/pager.do")
	@ResponseBody
	public PageDTO<BookmarkDTO> page(PageDTO<BookmarkDTO> condition){
		return service.page(condition);
	}
}