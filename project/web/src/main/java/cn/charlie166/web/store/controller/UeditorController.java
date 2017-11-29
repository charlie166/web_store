package cn.charlie166.web.store.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.charlie166.web.store.plugin.ueditor.ActionEnter;

/**
* @ClassName: UeditorController 
* @Description: 富文本编辑器控制器
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年11月29日 
*
 */
@Controller
@RequestMapping(value = "/ueditor")
public class UeditorController extends BaseController {

	/**
	* @Title: handleAction 
	* @Description:
	* @param req
	* @param resp
	* @throws IOException
	 */
	@RequestMapping(value = "/action.do")
	public void handleAction(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		req.setCharacterEncoding("UTF-8");
		resp.setHeader("Content-Type" , "text/html");
		String rootPath = req.getServletContext().getRealPath("/");
		try (PrintWriter pw = resp.getWriter()) {
			pw.write(new ActionEnter(req, rootPath).exec());
		}
	}
}