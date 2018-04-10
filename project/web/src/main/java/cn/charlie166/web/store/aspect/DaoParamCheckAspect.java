package cn.charlie166.web.store.aspect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import cn.charlie166.web.common.domain.annotation.ParamCheck;
import cn.charlie166.web.common.domain.annotation.StringCheck;
import cn.charlie166.web.common.domain.enums.ParamCheckType;
import cn.charlie166.web.store.constant.CustomException;
import cn.charlie166.web.store.constant.ExceptionCodes;
import cn.charlie166.web.store.tools.ClassUtils;
import cn.charlie166.web.store.tools.StringUtils;

/**
* @ClassName: DaoParamCheckAspect 
* @Description: 数据库操作参数校验切面. 这个切面仅用于校验值是否满足数据库操作，暂不支持场景定值校验
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年11月22日 
*
 */
@Aspect
@Component
public class DaoParamCheckAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	* @Title: justCheck 
	* @Description: 切所有dao接口的所有方法
	* @param jp
	 * @throws CustomException 
	 */
	@Before("execution(* cn.charlie166.web.store.dao..*.*(..))")
	public void justCheck(JoinPoint jp) throws CustomException{
		Signature signature = jp.getSignature();
		Object[] args = jp.getArgs();
		/**必须要有传参才能校验**/
		if(signature instanceof MethodSignature && args.length > 0){
			MethodSignature ms = (MethodSignature) signature;
			Method method = ms.getMethod();
			Parameter[] parameters = method.getParameters();
			int index = 0;
			for(Parameter p: parameters){
				if(index >= args.length){
					throw CustomException.instance(ExceptionCodes.CHECK_PARAM_LENGTH_EXCEED);
				}
				/**当前变量参数值**/
				Object currentParam = args[index ++];
				ParamCheck[] annotations = p.getAnnotationsByType(ParamCheck.class);
				for(ParamCheck pc: annotations){
					/**参数为单个校验对象**/
					if(pc.type() == ParamCheckType.SINGLE) {
						Field[] allFields = ClassUtils.getAllField(p.getType());
						for(Field f: allFields){
							/**必须为字符串类型**/
							if(f.getType() == String.class){
								StringCheck[] annotationsByType = f.getAnnotationsByType(StringCheck.class);
								if(annotationsByType.length > 0){
									/**这个注解目前就处理第一个**/
									StringCheck sc1 = annotationsByType[0];
									this.checkField(f, sc1, currentParam, pc);
								}
							}
						}
					} else if(pc.type() == ParamCheckType.LIST) {/**校验参数为列表**/
						/**参数必须为列表**/
						Class<?> pType = p.getType();
						if(pType == List.class) {
							/**当前参数是列表**/
							@SuppressWarnings("unchecked")
							List<Object> listParam = (List<Object>) currentParam;
							if(!CollectionUtils.isEmpty(listParam)){
								try {
									Type parameterizedType = p.getParameterizedType();
									Field field = parameterizedType.getClass().getDeclaredField("actualTypeArguments");
									field.setAccessible(true);
									Type[] types = (Type[]) field.get(parameterizedType);
									if(types != null && types.length > 0){
										String clsName = types[0].getTypeName();
										Class<?> forName = Class.forName(clsName);
										Field[] allFields = ClassUtils.getAllField(forName);
										for(Field f: allFields){
											/**必须为字符串类型**/
											if(f.getType() == String.class){
												StringCheck[] annotationsByType = f.getAnnotationsByType(StringCheck.class);
												if(annotationsByType.length > 0){
													/**这个注解目前就处理第一个**/
													StringCheck sc1 = annotationsByType[0];
													for(Object obj: listParam){
														this.checkField(f, sc1, obj, pc);
													}
												}
											}
										}
									}
								} catch (NoSuchFieldException | SecurityException | IllegalAccessException | ClassNotFoundException e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		}
	}
	
	/**
	* @Title: checkField 
	* @Description: 变量校验
	* @param f 校验的变量
	* @param sc 校验规则
	* @param obj 参数
	* @param ParamCheck 校验注解
	 * @throws CustomException 
	 */
	private void checkField(Field f, StringCheck sc, Object obj, ParamCheck pc) throws CustomException{
		try {
			if(this.checkThisField(f, pc)){
				f.setAccessible(true);
				String thisVal = (String) f.get(obj);
				/**首先判断是否校验有内容**/
				if(sc.mustHasContent()){
					if(!StringUtils.hasContent(thisVal)){
						throw CustomException.instance(ExceptionCodes.CHECK_FAILED, sc.mustHasContentTip());
					}
				}
				/**判断长度***/
				if(thisVal != null){
					if(sc.minLength() >= 0){
						if(thisVal.trim().length() < sc.minLength()){
							throw CustomException.instance(ExceptionCodes.CHECK_FAILED,
									String.format(sc.minLengthTip(), sc.minLength()));
						}
					}
					if(sc.maxLength() >= 0){
						if(thisVal.trim().length() > sc.maxLength()){
							throw CustomException.instance(ExceptionCodes.CHECK_FAILED,
									String.format(sc.maxLengthTip(), sc.maxLength()));
						}
					}
				}
			}
		} catch (IllegalArgumentException
				| IllegalAccessException e) {
			logger.error(String.format("%s中的变量%s无法获取", obj.getClass().getName(), f.getName()), e);
		}
	}
	
	/**
	* @Title: checkThisField 
	* @Description: 是否校验此成员
	* @param f 成员数据
	* @param pc 注解信息
	* @return
	 */
	private boolean checkThisField(Field f, ParamCheck pc){
		if(f != null && pc != null){
			String[] ignore = pc.ignore();
			if(ignore != null && ignore.length > 0){
				String name = f.getName();
				if(StringUtils.hasContent(name)){
					for(String s: ignore){
						if(name.trim().equals(s))
							return false;
					}
				}
			}
		}
		return true;
	}
}