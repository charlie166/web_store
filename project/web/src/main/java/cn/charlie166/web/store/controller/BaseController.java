package cn.charlie166.web.store.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;

import cn.charlie166.web.store.constant.CustomException;

/**
 * @description 基础控制器，所有控制器均需要继承此类
 * @author <a href="mailto:charlie166@163.com">李阳</a> 
 * @version 1.0, 2017年11月14日
 * @see     
 * @since   web 1.0
 */
public class BaseController {

	/**
	 * @description 统一异常处理方法
	 * @author <a href="mailto:charlie166@163.com">李阳</a> 
	 * @param exception 
	 * @since web 1.0.0
	 */
	@ExceptionHandler
	public void exceptionHandler(Exception exception) {
		if(exception instanceof CustomException){
		} else {
		}
		exception.printStackTrace();
	}
}