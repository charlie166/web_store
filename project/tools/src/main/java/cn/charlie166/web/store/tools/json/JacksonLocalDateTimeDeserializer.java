package cn.charlie166.web.store.tools.json;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import cn.charlie166.web.store.constant.CustomException;
import cn.charlie166.web.store.constant.ExceptionCodes;
import cn.charlie166.web.store.tools.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class JacksonLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

	/**时间默认格式**/
	private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	private String pattern = JacksonLocalDateTimeDeserializer.DEFAULT_PATTERN;
	
	/**
	 * @description 默认格式构造方法		yyyy-MM-dd HH:mm:ss
	 */
	public JacksonLocalDateTimeDeserializer() {
		this(JacksonLocalDateTimeDeserializer.DEFAULT_PATTERN);
	}
	
	/**
	* @description 指定格式构造方法
	* @param pattern 时间格式
	 */
	public JacksonLocalDateTimeDeserializer(String pattern) {
		this.pattern = pattern;
	}
	
	@Override
	public LocalDateTime deserialize(JsonParser jp,
			DeserializationContext context) throws IOException,
			JsonProcessingException {
		LocalDateTime localDatetime = null;
		String text = jp.getText();
		try {
			/**只解析非空时间字符串**/
			if(StringUtils.hasContent(text)){
				localDatetime = LocalDateTime.parse(text, DateTimeFormatter.ofPattern(pattern));
			}
		} catch (Exception e) {
			throw CustomException.instance(ExceptionCodes.JSON_PARSE_ERROR, e);
		}
		return localDatetime;
	}
}
