package cn.charlie166.web.store.tools;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import cn.charlie166.web.store.constant.CustomException;
import cn.charlie166.web.store.constant.ExceptionCodes;

/**
* @ClassName: XmlUtils 
* @Description: 	XML工具类
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年3月29日 
*
 */
public class XmlUtils {

	/**
	* @Title: toObject 
	* @Description: 从xml输入流解析为pojo对象
	* @param input 来源输入流
	* @param cls 返回对象类型
	* @return 解析结果
	 */
	public static <T> T toObject(InputStream input, Class<T> cls){
		if(StringUtils.hasContent(input) && cls != null){
			XmlMapper mapper = new XmlMapper();
			try {
				return mapper.readValue(input, cls);
			} catch (IOException e) {
				e.printStackTrace();
				throw CustomException.instance(ExceptionCodes.COMMON_XML_PARSE_EXCEPTION, e);
			}
		}
		throw CustomException.instance(ExceptionCodes.COMMON_PARAM_ABSENT);
	}
	
	/**
	* @Title: toObject 
	* @Description: 将xml字符串转换为pojo对象
	* @param input xml字符串数据
	* @param cls 返回对象类型
	* @return 解析结果
	 */
	public static <T> T toObject(String input, Class<T> cls){
		if(StringUtils.hasContent(input) && cls != null){
			XmlMapper mapper = new XmlMapper();
			try {
				return mapper.readValue(input, cls);
			} catch (IOException e) {
				e.printStackTrace();
				throw CustomException.instance(ExceptionCodes.COMMON_XML_PARSE_EXCEPTION, e);
			}
		}
		throw CustomException.instance(ExceptionCodes.COMMON_PARAM_ABSENT);
	}
	
	/***
	* @Title: fromObject 
	* @Description: 将对象转换为xml字符串
	* @param obj 需要转换的对象
	* @return 转换结果字符串
	 */
	public static String fromObject(Object obj){
		if(obj != null){
			XmlMapper mapper = new XmlMapper();
			try {
				return mapper.writeValueAsString(obj);
			} catch (IOException e) {
				e.printStackTrace();
				throw CustomException.instance(ExceptionCodes.COMMON_XML_PARSE_EXCEPTION, e);
			}
		}
		throw CustomException.instance(ExceptionCodes.COMMON_PARAM_ABSENT);
	}
	
}