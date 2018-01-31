package cn.charlie166.web.store.tools;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import cn.charlie166.web.store.constant.CustomException;
import cn.charlie166.web.store.constant.ExceptionCodes;

/**
* @ClassName: ClassUtils 
* @Description: 类操作工具类
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年11月23日 
*
 */
public class ClassUtils {

	/**
	* @Title: getAllField 
	* @Description: 获取指定类的所有变量
	* @param cls
	* @return
	 */
	public static Field[] getAllField(Class<?> cls){
		List<Field> fieldList = new ArrayList<Field>();
		if(cls != null){
			Field[] fields = cls.getDeclaredFields();
			if(fields != null && fields.length > 0){
				fieldList.addAll(Arrays.asList(fields));
			}
			Class<?> superClass = cls.getSuperclass();
			if(superClass != null && superClass != Object.class){
				fieldList.addAll(Arrays.asList(ClassUtils.getAllField(superClass)));
			}
		}
		return fieldList.toArray(new Field[fieldList.size()]);
	}
	
	/**
	* @Title: convertType 
	* @Description: 将原数据类型转换为指定类型输出
	* @param from 数据来源
	* @param toType 目标类型
	* @return
	* @throws CustomException
	 */
	public static <T> T convertType(Object from, Class<T> toType) throws CustomException{
		if(from == null || toType == null){
			throw CustomException.instance(ExceptionCodes.COMMON_PARAM_ABSENT);
		}
		try {
			try {
				Constructor<T> constructor = toType.getConstructor();
				if(constructor == null){
					throw CustomException.instance(ExceptionCodes.COMMON_DEFAULT_CONSTRUCTOR_ABSENT);
				}
			} catch (NoSuchMethodException | SecurityException e) {
				throw CustomException.instance(ExceptionCodes.COMMON_DEFAULT_CONSTRUCTOR_ABSENT, e);
			}
			T instance = toType.newInstance();
			BeanUtils.copyProperties(from, instance);
			return instance;
		} catch (IllegalAccessException | InstantiationException | BeansException e) {
			throw CustomException.instance(ExceptionCodes.COMMON_EXCEPTION, e);
		}
	}
}	