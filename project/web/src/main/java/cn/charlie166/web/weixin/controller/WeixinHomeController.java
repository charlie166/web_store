package cn.charlie166.web.weixin.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.charlie166.web.base.controller.BaseController;
import cn.charlie166.web.common.domain.annotation.DirectReturn;
import cn.charlie166.web.weixin.domain.dto.WeixinServerValidateDTO;
import cn.charlie166.web.weixin.service.inter.WeixinHomeService;

/**
* @ClassName: HomeController 
* @Description: 微信主页请求的控制器
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年3月27日 
*
 */
@RequestMapping(value = "/weixin/home")
@Controller
public class WeixinHomeController extends BaseController {

	@Autowired
	private WeixinHomeService service;
	
	/**
	* @Title: serverValidate 
	* @Description: 微信服务器校验接口
	* @param dto
	* @return
	 */
	@RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
	@ResponseBody
	@DirectReturn
	public String serverValidate(WeixinServerValidateDTO dto){
		dto = service.validateServer(dto);
		logger.debug("原样返回微信: {}", dto.getEchostr());
		return dto.getEchostr();
	}
	
	/**
	* @Title: msg 
	* @Description: 微信消息
	* @param msg
	* @return
	 * @throws IOException 
	 */
	@RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
	@ResponseBody
	@DirectReturn
	public Object msg() throws IOException{
		String msg = service.handlerMsg(this.request.getInputStream());
		logger.debug("微信消息返回: {}", msg);
		return msg;
	}
	
	/**
	* @Title: getAccessToken 
	* @Description: 获取微信访问凭证
	* @return
	 */
	@RequestMapping(value = "/token")
	@ResponseBody
	public Object getAccessToken(){
		return service.getAccessToken();
	}
	
	/**
	* @Title: refreshMenu 
	* @Description: 刷新微信菜单
	* @return
	 */
	@RequestMapping(value = "/menu")
	@ResponseBody
	public Boolean refreshMenu(){
		service.refreshMenu();
		return Boolean.TRUE;
	}
}