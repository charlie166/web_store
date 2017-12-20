package cn.charlie166.web.store.tools.json;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JacksonLocalTimeSerializer extends JsonSerializer<LocalTime> {

	@Override
	public void serialize(LocalTime value, JsonGenerator gen,
			SerializerProvider serializers) throws IOException,
			JsonProcessingException {
		if (value != null) {
			String val = value.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
			gen.writeString(val);
		}
	}

	@Override
	public Class<LocalTime> handledType() {
		return LocalTime.class;
	}
}
