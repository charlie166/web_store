package cn.charlie166.web.store.tools;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
	* @param from 数据来源.
	* @param toType 目标类型
	* @return
	* @throws CustomException
	 */
	public static <T> T convertType(Object from, Class<T> toType) throws CustomException{
		if(toType == null){
			throw CustomException.instance(ExceptionCodes.COMMON_PARAM_ABSENT);
		}
		if(from == null)
			return null;
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
	
	/**
	* @Title: convertType 
	* @Description: 从MAP中取值转换为指定对象
	* @param from 数据来源MAP, 键值必须是字符串
	* @param toType 转换结果类型
	* @return
	* @throws CustomException
	 */
	public static <T> T convertType(Map<String, Object> from, Class<T> toType) throws CustomException{
		if(toType == null){
			throw CustomException.instance(ExceptionCodes.COMMON_PARAM_ABSENT);
		}
		if(from == null)
			return null;
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
			Field[] allField = ClassUtils.getAllField(toType);
			return instance;
		} catch (IllegalAccessException | InstantiationException | BeansException e) {
			throw CustomException.instance(ExceptionCodes.COMMON_EXCEPTION, e);
		}
	}
	
	/**
	* @Title: convertTypeOfList 
	* @Description: 列表的数据类型转换
	* @param fromList 来源列表
	* @param toType 转换目标类型
	* @return 转换结果
	* @throws CustomException
	 */
	public static <T> List<T> convertTypeOfList(List<? extends Object> fromList, Class<T> toType) throws CustomException {
		if(toType == null){
			throw CustomException.instance(ExceptionCodes.COMMON_PARAM_ABSENT);
		}
		List<T> retList = new ArrayList<T>(fromList == null ? 0 : fromList.size());
		try {
			if(fromList != null && fromList.size() > 0){
				try {
					Constructor<T> constructor = toType.getConstructor();
					if(constructor == null){
						throw CustomException.instance(ExceptionCodes.COMMON_DEFAULT_CONSTRUCTOR_ABSENT);
					}
				} catch (NoSuchMethodException | SecurityException e) {
					throw CustomException.instance(ExceptionCodes.COMMON_DEFAULT_CONSTRUCTOR_ABSENT, e);
				}
				for(Object obj : fromList){
					T instance = toType.newInstance();
					if(obj == null)
						continue;
					BeanUtils.copyProperties(obj, instance);
					retList.add(instance);
				}
			}
		} catch (IllegalAccessException | InstantiationException | BeansException e) {
			throw CustomException.instance(ExceptionCodes.COMMON_EXCEPTION, e);
		}
		return retList;
	}
}	