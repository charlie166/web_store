package cn.charlie166.web.store.tools;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import cn.charlie166.web.store.constant.CustomException;
import cn.charlie166.web.store.constant.ExceptionCodes;

/**
* @ClassName: HashUtils 
* @Description: 哈希工具类
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年3月28日 
*
 */
public class HashUtils {

	/**
	 * @Title: hash 
	 * @Description: 以默认编码哈希操作处理
	 * @param oriText 原文本
	 * @param hashType 哈希算法
	 * @return 处理后的字符串
	 */
	private static String hash(final String oriText, final String hashType){
		return HashUtils.hash(oriText, hashType, Charset.defaultCharset());
	}
	/**
	* @Title: hash 
	* @Description: 哈希操作处理
	* @param oriText 原文本
	* @param hashType 哈希算法
	* @param charset 编码
	* @return 处理后的字符串
	 */
	private static String hash(final String oriText, final String hashType, final Charset charset){
		if(StringUtils.hasContent(oriText) && StringUtils.hasContent(hashType)){
			try {
				MessageDigest md = MessageDigest.getInstance(hashType);
				md.update(ByteBuffer.wrap(oriText.getBytes(charset)));
				byte[] digest = md.digest();
				StringBuilder strBuilder = new StringBuilder();
				for(byte b : digest){
					String s = Integer.toHexString(0xff & b);
					if(s.length() == 1)
						strBuilder.append('0');
					strBuilder.append(s);
				}
				return strBuilder.toString();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		throw CustomException.instance(ExceptionCodes.COMMON_PARAM_ABSENT);
	}
	
	/**
	* @Title: sha1 
	* @Description: 对字符串进行SHA-1哈希处理
	* @param oriText 原字符串
	* @return 处理结果
	 */
	public static String sha1(String oriText){
		return HashUtils.hash(oriText, "SHA-1");
	}
	
	/**
	* @Title: sha256 
	* @Description: 对字符串进行SHA-256哈希处理
	* @param oriText 原字符串
	* @return 处理结果
	 */
	public static String sha256(String oriText){
		return HashUtils.hash(oriText, "SHA-256");
	}
}