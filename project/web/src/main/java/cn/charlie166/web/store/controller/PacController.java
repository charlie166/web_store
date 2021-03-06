package cn.charlie166.web.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.charlie166.web.base.controller.BaseController;
import cn.charlie166.web.store.domain.dto.PacDTO;
import cn.charlie166.web.store.service.inter.PacService;

/**
 * @description 代理自动配置控制器
 * @author <a href="mailto:charlie166@163.com">李阳</a> 
 * @version 1.0, 2017年12月18日
 * @see     
 * @since   web 1.0
 */
@Controller
@RequestMapping(value = "/pac")
public class PacController extends BaseController {

	@Autowired
	private PacService service;
	
	/**
	 * @description 获取所有地址数据
	 * @author <a href="mailto:charlie166@163.com">李阳</a> 
	 * @return 
	 * @since web 1.0.0
	 */
	@RequestMapping(value = "/all.do")
	@ResponseBody
	public List<PacDTO> all(){
		return service.all();
	}
	
	/**
	* @Title: insert 
	* @Description: 新增一条记录
	* @param dto domain:域名，必需
	* @return
	 */
	@RequestMapping(value = "/add.do")
	@ResponseBody
	public PacDTO insert(@RequestBody PacDTO dto){
		return service.add(dto);
	}
	
	/**
	* @Title: online 
	* @Description: 获取在线PAC数据
	* @return
	 */
	@RequestMapping(value = "/online.do")
	@ResponseBody
	public String online(){
		return service.online();
	}
}