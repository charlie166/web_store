package cn.charlie166.web.base.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.charlie166.web.base.service.inter.AttachmentService;
import cn.charlie166.web.common.domain.dto.AttachmentDTO;
import cn.charlie166.web.store.controller.BaseController;
import cn.charlie166.web.store.tools.StringUtils;

/**
* @ClassName: AttachmentController 
* @Description: 附件控制器
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年4月18日 
*
 */
@RequestMapping(value = "/attachment")
@Controller
public class AttachmentController extends BaseController {

	@Autowired
	private AttachmentService service;
	
	/**
	* @Title: upload 
	* @Description: 文件上传
	* @return 成功后的文件相对路径
	 */
	@RequestMapping(value = "/upload")
	@ResponseBody
	public Object upload(@RequestParam(value = "file") MultipartFile file, @RequestParam Map<String, Object> params){
		return service.saveFromMultipartFile(file, params);
	}
	
	/**
	 * 添加@ResponseBody注解. 不然会进入ModelAndView
	* @throws UnsupportedEncodingException 
	 * @Title: download 
	* @Description: 附件下载
	 */
	@RequestMapping(value = "/download/**")
	@ResponseBody
	public void download() throws UnsupportedEncodingException{
		String uri = this.request.getRequestURI();
		String relative = StringUtils.getStrFrom(URLDecoder.decode(uri, "UTF-8"), "attachment/download/");
		AttachmentDTO dto = service.getFileOfPath(relative);
		this.responseFile(dto.getFile(), dto.getOriginalName());
	}
	
	/**
	* @Title: delete 
	* @Description: 删除附件
	* @return 删除结果
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/delete/**")
	@ResponseBody
	public Boolean delete() throws UnsupportedEncodingException{
		String uri = this.request.getRequestURI();
		String relative = StringUtils.getStrFrom(URLDecoder.decode(uri, "UTF-8"), "attachment/delete/");
		return service.deleteFileByPath(relative);
	}
	
	/**
	* @Title: deleteByInfoId 
	* @Description: 根据附件信息主键ID删除附件及记录
	* @param id 主键ID
	* @return
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Boolean deleteByInfoId(@RequestParam(value = "id", required = true) Long id){
		return service.deleteFileById(id);
	}
}