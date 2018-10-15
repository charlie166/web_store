package cn.charlie166.web.store.tools;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
* @ClassName: SelfEncryptionUtils 
* @Description: 自定义加密/解密工具方法----对class进行字节加解密
* @deprecated 这个暂时没什么用处
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年5月29日 
*
 */
@Deprecated
public class SelfEncryptionUtils {

	private static final String PREFFIX_STR = "com.rzkjsoft.cms.basic.constants";
	
	/**
	* @Title: shouldHandle 
	* @Description: 当前class是否需要处理
	* @param clsName
	* @return
	 */
	private static boolean shouldHandle(String clsName){
		if(StringUtils.hasContent(clsName)){
			String checkPreffix = SelfEncryptionUtils.PREFFIX_STR.replaceAll("\\.", "/");
			if(!checkPreffix.endsWith("/"))
				checkPreffix += "/";
			/**必须以此开头且是class文件**/
			if(clsName.startsWith(checkPreffix) && clsName.endsWith(".class")){
				return true;
			}
		}
		return false;
	}
	
	public static void encryptionCls(String jarFilePath){
		if(StringUtils.hasContent(jarFilePath)){
			Path jarPath = Paths.get(jarFilePath);
			if(Files.exists(jarPath) && Files.isRegularFile(jarPath) && Files.isReadable(jarPath)){
				String string = jarPath.getName(jarPath.getNameCount() - 1).toString();
				String outJarFileNameStr = string.substring(0, string.lastIndexOf(".") == -1 ? string.length() : string.lastIndexOf(".")) + "_"
					 + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")) + ".jar";
				Path outJarFilePath = Paths.get(jarPath.getParent().normalize().toString(), outJarFileNameStr);
				try(JarFile jarFile = new JarFile(jarPath.toFile(), true);
					ZipOutputStream zOut = new ZipOutputStream(Files.newOutputStream(outJarFilePath), Charset.defaultCharset())) {
					jarFile.stream().filter(one -> one != null)
						.forEach(one -> {
							try{
								String name = one.getName();
								/**混淆数值**/
								int plusValue = 0;
								if(SelfEncryptionUtils.shouldHandle(name)){
									System.out.println("handle:" + name);
									plusValue = 1;
								}
								InputStream is = jarFile.getInputStream(one);
								ZipEntry entry = new ZipEntry(name);
								zOut.putNextEntry(entry);
								int count;
								byte data[] = new byte[2048];
								while ((count = is.read(data, 0, 2048)) != -1) {
									for(int i = 0; i < count; i++){
										int v = data[i] & 0xFF;
										int storeInteger = v  + plusValue;
										if(storeInteger == 256){
											storeInteger = 0;
										}
										data[i] = Integer.valueOf(storeInteger).byteValue();
									}
									zOut.write(data, 0, count);
								}
							} catch (IOException e) {
								e.printStackTrace();
							}
						});
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		SelfEncryptionUtils.encryptionCls("F:/charlie/documents/vs/docs/rzkjsoft-cms-1.0.jar");
		System.out.println("done:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
	}
}