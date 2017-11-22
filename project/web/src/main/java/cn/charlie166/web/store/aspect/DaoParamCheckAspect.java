package cn.charlie166.web.store.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
* @ClassName: DaoParamCheckAspect 
* @Description: 数据库操作参数校验切面
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年11月22日 
*
 */
@Aspect
@Component
public class DaoParamCheckAspect {

	/**
	* @Title: justCheck 
	* @Description: 切所有dao接口的所有方法
	* @param jp
	 */
	@Before("execution(* cn.charlie166.web.store.dao..*.*(..))")
	public void justCheck(JoinPoint jp){
		Object[] args = jp.getArgs();
		for(Object obj: args){
			System.out.println("cls:" + obj.getClass().getName());
		}
	}
}