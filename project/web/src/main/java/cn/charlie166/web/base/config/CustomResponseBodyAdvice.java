package cn.charlie166.web.base.config;

import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import cn.charlie166.web.common.domain.annotation.DirectReturn;
import cn.charlie166.web.common.domain.dto.MsgDTO;
import cn.charlie166.web.store.constant.ResponseCodes;

/**
 * @description 自定义响应体切面
 * @author <a href="mailto:charlie166@163.com">李阳</a> 
 * @version 1.0, 2017年12月27日
 * @see     
 * @since   web 1.0
 */
@Order(value = Ordered.LOWEST_PRECEDENCE - 100)
@ControllerAdvice
public class CustomResponseBodyAdvice implements ResponseBodyAdvice<Object> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return returnType.getMethodAnnotation(ResponseBody.class) != null &&
			returnType.getMethodAnnotation(DirectReturn.class) == null;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		MsgDTO<Object> dto = new MsgDTO<Object>();
		dto.setCode(ResponseCodes.OK);
		dto.setContent(body);
		return dto;
	}

}
