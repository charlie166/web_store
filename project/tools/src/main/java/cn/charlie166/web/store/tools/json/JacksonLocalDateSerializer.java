package cn.charlie166.web.store.tools.json;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JacksonLocalDateSerializer extends JsonSerializer<LocalDate> {

	/**日期默认格式**/
	private static final String DEFAULT_PATTERN = "yyyy-MM-dd";
	
	private String pattern = JacksonLocalDateSerializer.DEFAULT_PATTERN;
	
	/**
	* @description 默认日期格式构造方法		yyyy-MM-dd
	 */
	public JacksonLocalDateSerializer() {
		this(JacksonLocalDateSerializer.DEFAULT_PATTERN);
	}
	
	/**
	* @description 指定格式构造方法
	* @param pattern 日期格式
	 */
	public JacksonLocalDateSerializer(String pattern) {
		this.pattern = pattern;
	}
	
	@Override
	public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException,
			JsonProcessingException {
		if (value != null) {
			String str = value.format(DateTimeFormatter.ofPattern(this.pattern));
			gen.writeString(str);
		}
	}

	@Override
	public Class<LocalDate> handledType() {
		return LocalDate.class;
	}
}
