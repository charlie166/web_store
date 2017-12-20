package cn.charlie166.web.store.tools.json;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import cn.charlie166.web.store.constant.CustomException;
import cn.charlie166.web.store.constant.ExceptionCodes;
import cn.charlie166.web.store.tools.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class JacksonLocalDateDeserializer extends JsonDeserializer<LocalDate> {

	@Override
	public LocalDate deserialize(JsonParser jp, DeserializationContext context) throws IOException,
			JsonProcessingException {
		LocalDate localDate = null;
		String text = jp.getText();
		String pattern = "yyyy-MM-dd";
		try {
			/**只解析非空时间字符串**/
			if(StringUtils.hasContent(text)){
				localDate = LocalDate.parse(text, DateTimeFormatter.ofPattern(pattern));
			}
		} catch (Exception e) {
			throw CustomException.instance(ExceptionCodes.JSON_PARSE_ERROR, e);
		}
		return localDate;
	}
}
