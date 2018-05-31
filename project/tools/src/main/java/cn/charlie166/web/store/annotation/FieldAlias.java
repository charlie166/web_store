package cn.charlie166.web.store.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @ClassName: FieldAlias 
* @Description: 变量别名
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年4月10日 
*
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface FieldAlias {

	/***
	* @Title: value 
	* @Description: 变量别名. 为空或空字符串则表示为定义的变量名，相当于没定义别名
	* @return
	 */
	String value() default "";
}