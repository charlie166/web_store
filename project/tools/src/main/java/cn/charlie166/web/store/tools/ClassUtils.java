package cn.charlie166.web.store.tools;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}	