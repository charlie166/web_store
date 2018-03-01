package cn.charlie166.web.store.domain.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.charlie166.web.store.domain.enums.ParamCheckType;

/**
* @ClassName: ParamCheck 
* @Description: 标识是否校验参数
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年11月22日 
*
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamCheck {

	/**
	* @Title: type 
	* @Description: 设置校验数据类型. 是列表还是单个校验还是什么
	* @return
	 */
	ParamCheckType type() default ParamCheckType.SINGLE;
	
	/**
	* @Title: ignore 
	* @Description: 设置忽略校验的字段 
	* @return
	 */
	String [] ignore() default {};
}