package cn.charlie166.web.store.tools.json;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JacksonLocalTimeSerializer extends JsonSerializer<LocalTime> {

	/**时间默认格式**/
	private static final String DEFAULT_PATTERN = "HH:mm:ss";
	
	private String pattern = JacksonLocalTimeSerializer.DEFAULT_PATTERN;
	
	/**
	* @description 默认格式构造方法	HH:mm:ss
	 */
	public JacksonLocalTimeSerializer() {
		this(JacksonLocalTimeSerializer.DEFAULT_PATTERN);
	}
	
	/**
	* @description 指定格式构造方法
	* @param pattern 时间格式
	 */
	public JacksonLocalTimeSerializer(String pattern) {
		this.pattern = pattern;
	}
	
	@Override
	public void serialize(LocalTime value, JsonGenerator gen,
			SerializerProvider serializers) throws IOException,
			JsonProcessingException {
		if (value != null) {
			String val = value.format(DateTimeFormatter.ofPattern(this.pattern));
			gen.writeString(val);
		}
	}

	@Override
	public Class<LocalTime> handledType() {
		return LocalTime.class;
	}
}
