package cn.charlie166.web.store.tools.json;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JacksonLocalDateTimeSerializer extends	JsonSerializer<LocalDateTime> {

	@Override
	public void serialize(LocalDateTime value, JsonGenerator gen,
			SerializerProvider serializers) throws IOException,
			JsonProcessingException {
		if (value != null) {
			String str = value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			gen.writeString(str);
		}
	}

	@Override
	public Class<LocalDateTime> handledType() {
		return LocalDateTime.class;
	}
}
