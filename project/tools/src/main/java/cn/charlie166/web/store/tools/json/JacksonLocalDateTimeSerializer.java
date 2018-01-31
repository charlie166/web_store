package cn.charlie166.web.store.tools.json;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JacksonLocalDateTimeSerializer extends	JsonSerializer<LocalDateTime> {

	/**时间默认格式**/
	private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	private String pattern = JacksonLocalDateTimeSerializer.DEFAULT_PATTERN;
	
	/**
	* @description 默认格式构造方法	yyyy-MM-dd HH:mm:ss
	 */
	public JacksonLocalDateTimeSerializer() {
		this(JacksonLocalDateTimeSerializer.DEFAULT_PATTERN);
	}
	
	/**
	* @description 指定格式构造方法
	* @param pattern 时间格式
	 */
	public JacksonLocalDateTimeSerializer(String pattern) {
		this.pattern = pattern;
	}
	
	@Override
	public void serialize(LocalDateTime value, JsonGenerator gen,
			SerializerProvider serializers) throws IOException,
			JsonProcessingException {
		if (value != null) {
			String str = value.format(DateTimeFormatter.ofPattern(this.pattern));
			gen.writeString(str);
		}
	}

	@Override
	public Class<LocalDateTime> handledType() {
		return LocalDateTime.class;
	}
}
