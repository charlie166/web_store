package cn.charlie166.web.store.tools;

import java.util.Random;

import cn.charlie166.web.store.constant.CustomException;
import cn.charlie166.web.store.constant.ExceptionCodes;

/**
* @ClassName: TextUtils 
* @Description: 文字操作工具类
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年11月21日 
*
 */
public class TextUtils {

	/**UTF-8编码，汉字最小的编码**/
	public static final int MIN_VALUE = 19968;
	/**UTF-8编码，汉字最大的编码**/
	public static final int MAX_VALUE = 40869;
	
	/**
	* @Title: randomHan 
	* @Description: 随机生成一个中文字符
	* @return 生成的字符
	 * @throws CustomException 
	 */
	public static String randomHan() throws CustomException {
		return TextUtils.randomHan(1);
	}
	
	/****
	* @Title: randomHan 
	* @Description: 随机产生指定个数的汉字字符串
	* @param size 汉字数目
	* @return 生成的汉字字符串
	 * @throws CustomException 
	 */
	public static String randomHan(int size) throws CustomException {
		if(size >= 0){
			StringBuilder str = new StringBuilder();
			Random random = new Random();
			for(int i = 0; i < size; i++){
				int ri = random.nextInt(TextUtils.MAX_VALUE - TextUtils.MIN_VALUE + 1) + TextUtils.MIN_VALUE;
				String s = String.format("%s", String.valueOf((char) ri));
				str.append(s);
			}
			return str.toString();
		}
		throw CustomException.instance(ExceptionCodes.COMMON_PARAM_ABSENT);
	}
	
	/**
	* @Title: randomEnglish 
	* @Description: 随机生成一个字母. 区分大小写
	* @return 生成的字母
	* @throws CustomException
	 */
	public static String randomEnglish() throws CustomException {
		return TextUtils.randomEnglish(1);
	}
	
	/**
	* @Title: randomEnglish 
	* @Description: 随机产生指定个数的英文字符，区分大小写
	* @param size 字符数
	* @return 生成的随机字符串
	 * @throws CustomException 
	 */
	public static String randomEnglish(int size) throws CustomException {
		if(size >= 0){
			StringBuilder str = new StringBuilder();
			Random random = new Random();
			for(int i = 0; i < size; i++){
				int ri = random.nextInt(52);
				if(ri <= 25){
					str.append((char) (ri + 65));
				} else {
					str.append((char) (ri + 71));
				}
			}
			return str.toString();
		}
		throw CustomException.instance(ExceptionCodes.COMMON_PARAM_ABSENT);
	}
	
	/**
	 * @description 转换为Unicode编码
	 * @author <a href="mailto:charlie166@163.com">李阳</a> 
	 * @param input
	 * @return 
	 * @since tools 1.0.0
	 */
	public static String toUnicode(String input) {
		StringBuilder builder = new StringBuilder();
		char[] chars = input.toCharArray();
		for (char ch : chars) {
			if (ch < 256) {
				builder.append(ch);
			} else {
				builder.append("\\u" +  Integer.toHexString(ch& 0xffff));
			}
		}
		return builder.toString();
	}
}