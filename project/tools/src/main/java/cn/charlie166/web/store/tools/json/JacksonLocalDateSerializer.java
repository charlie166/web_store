package cn.charlie166.web.store.tools.json;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JacksonLocalDateSerializer extends JsonSerializer<LocalDate> {

	@Override
	public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException,
			JsonProcessingException {
		if (value != null) {
			String str = value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			gen.writeString(str);
		}
	}

	@Override
	public Class<LocalDate> handledType() {
		return LocalDate.class;
	}
}
