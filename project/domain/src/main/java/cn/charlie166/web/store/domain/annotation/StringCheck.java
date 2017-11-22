package cn.charlie166.web.store.domain.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @ClassName: StringCheck 
* @Description: 用于注解校验字符串是否合法
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年11月22日 
*
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StringCheck {

	/**是否必须存在内容***/
	boolean mustHasContent() default false;
	/**最大允许长度. 负数表示不校验**/
	int maxLength() default -1;
	/**最小允许长度. 负数表示不校验**/
	int minLength() default -1;
}	