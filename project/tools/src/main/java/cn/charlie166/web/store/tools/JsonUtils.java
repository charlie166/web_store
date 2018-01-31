package cn.charlie166.web.store.tools;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import cn.charlie166.web.store.tools.json.JacksonLocalDateDeserializer;
import cn.charlie166.web.store.tools.json.JacksonLocalDateSerializer;
import cn.charlie166.web.store.tools.json.JacksonLocalDateTimeDeserializer;
import cn.charlie166.web.store.tools.json.JacksonLocalDateTimeSerializer;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
* @ClassName: JsonUtils 
* @Description: JSON工具类
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年12月1日 
*
 */
public class JsonUtils {

	/**
	* @Title: createMapper 
	* @Description: 通过指定的{@link com.fasterxml.jackson.databind.Module Module}创建{@link com.fasterxml.jackson.databind.ObjectMapper ObjectMapper}
	* @param module
	* @return
	 */
	public static ObjectMapper createMapper(Module module){
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.registerModule(module);
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		return objectMapper;
	}
	
	/**
	* @Title: createMapper 
	* @Description: 创建一个默认{@link com.fasterxml.jackson.databind.ObjectMapper ObjectMapper}
	* @return
	 */
	public static ObjectMapper createMapper(){
		return JsonUtils.createMapper(JsonUtils.createModule());
	}
	
	/**
	* @Title: createModule 
	* @Description: 创建一个默认的简单 {@link com.fasterxml.jackson.databind.module.SimpleModule SimpleModule}
	* @return
	 */
	public static SimpleModule createModule(){
		SimpleModule module = new SimpleModule();
		module.addSerializer(LocalDateTime.class, new JacksonLocalDateTimeSerializer());
		module.addDeserializer(LocalDateTime.class, new JacksonLocalDateTimeDeserializer());
		module.addSerializer(LocalDate.class, new JacksonLocalDateSerializer());
		module.addDeserializer(LocalDate.class, new JacksonLocalDateDeserializer());
		return module;
	}
	
	/**
	* @Title: toJson 
	* @Description: 将对象转为JSON字符串
	* @param obj 需要转换的对象
	* @return JSON字符串
	 */
	public static String toJson(Object obj){
		if(obj != null){
			ObjectMapper objectMapper = JsonUtils.createMapper();
			try {
				return objectMapper.writeValueAsString(obj);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return "{}";
	}
	
	/**
	* @Title: fromJson 
	* @Description: 将JSON字符串转换为指定数据类型
	* @param json JSON字符串
	* @param cls 指定类型
	* @return
	 */
	public static <T> T fromJson(String json, Class<T> cls){
		if(StringUtils.hasContent(json) && cls != null){
			ObjectMapper objectMapper = JsonUtils.createMapper();
			try {
				T value = objectMapper.readValue(json, cls);
				return value;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}