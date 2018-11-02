package cn.charlie166.web.tumblr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.charlie166.web.base.controller.BaseController;
import cn.charlie166.web.common.domain.dto.PageDTO;
import cn.charlie166.web.tumblr.domain.dto.VideoDTO;
import cn.charlie166.web.tumblr.service.TumblrService;

/**
* @ClassName: TumblrController 
* @Description: tumblr请求控制器
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年11月2日 
*
 */
@RequestMapping(value = "/tumblr")
@Controller
public class TumblrController extends BaseController {

	@Autowired
	private TumblrService service;
	
	/**
	* @Title: indexPage 
	* @Description: 列表页面
	* @return
	 */
	@RequestMapping(value = {"/page/index.do", "/", "/page.do", "/page/list.do", ""})
	public ModelAndView indexPage(){
		ModelAndView mv = new ModelAndView("tumblr/index");
		return mv;
	}
	
	/**
	* @Title: page 
	* @Description: 分页查询
	* @param condition 查询条件
	* @return
	 */
	@RequestMapping(value = "/json/pager.do")
	@ResponseBody
	public PageDTO<VideoDTO> page(PageDTO<VideoDTO> condition){
		return service.page(condition);
	}
}