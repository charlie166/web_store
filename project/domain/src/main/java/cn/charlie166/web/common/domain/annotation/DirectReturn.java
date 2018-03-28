package cn.charlie166.web.common.domain.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @ClassName: DirectReturn 
* @Description: 用于标识直接返回，不作任何处理
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年3月28日 
*
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DirectReturn {

}