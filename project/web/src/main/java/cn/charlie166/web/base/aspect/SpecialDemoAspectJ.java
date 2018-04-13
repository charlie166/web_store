package cn.charlie166.web.base.aspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.charlie166.web.ruizhuo.service.inter.SystemDictionaryService;


/**
* @ClassName: RequestMappingAspectJ 
* @Description: 具体控制器请求切面
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年4月13日 
*
 */
@Aspect
public class SpecialDemoAspectJ {

	@Autowired
	private SystemDictionaryService dictService;

	/**
	* @Title: aroundRequestMapping 
	* @Description: 切 DemoController 控制器所有方法且有 RequestMapping注解的
	* @param pjp
	* @param requestMapping
	* @return
	* @throws Throwable
	 */
	@Around("@annotation(requestMapping) && within(cn.charlie166.web.base.controller.DemoController)")
	public Object aroundRequestMapping(ProceedingJoinPoint pjp, RequestMapping requestMapping) throws Throwable {
		Object ret = pjp.proceed();
		/****/
		dictService.reset();
		return ret;
	}
	
}
