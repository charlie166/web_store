package cn.charlie166.web.common.domain.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @ClassName: StringCheck 
* @Description: 用于注解校验字符串是否合法. 这些校验都是去掉头部和尾部的空白字符进行
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年11月22日 
*
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StringCheck {

	/**是否必须存在内容***/
	boolean mustHasContent() default false;
	/**内容校验提示**/
	String mustHasContentTip() default "内容不能为空";
	/**最大允许长度. 负数表示不校验**/
	int maxLength() default -1;
	/**超过允许长度时的提示**/
	String maxLengthTip() default "长度不能超过%s";
	/**最小允许长度. 负数表示不校验**/
	int minLength() default -1;
	/**长度小于指定值时提示**/
	String minLengthTip() default "长度不能小于%s";
}	