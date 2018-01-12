package cn.charlie166.web.store.plugin.ueditor;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import cn.charlie166.web.store.plugin.ueditor.define.ActionMap;
import cn.charlie166.web.store.tools.CustomPropertyPlaceholder;
import cn.charlie166.web.store.tools.JsonUtils;
import cn.charlie166.web.store.tools.StringUtils;

import com.google.common.collect.Sets;

/**
* @ClassName: UeditorConfigManager 
* @Description: 富文本配置管理器
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年11月29日 
*
 */
public class UeditorConfigManager {
	
	/**ueditor配置前缀**/
	public static final String UE_PROP_PREFFIX = "ue.";
	/**配置信息***/
	public static final Map<String, String> CONFIG_MAP = new HashMap<String, String>();
	/**不返回前端的配置键**/
	public static final Set<String> FILTER_KEYS = Sets.newHashSet("rootPath");
	/**配置中，值为数组的键名**/
	public static final Set<String> KEYS_OF_ARRAY = Sets.newHashSet("imageAllowFiles", "catcherLocalDomain", "catcherAllowFiles", "videoAllowFiles",
		"fileAllowFiles", "imageManagerAllowFiles", "fileManagerAllowFiles");
	/**需要处理通用访问前缀的键**/
	public static final Set<String> VIEW_PREFFIX = Sets.newHashSet("imageUrlPrefix", "scrawlUrlPrefix", "snapscreenUrlPrefix",
		"catcherUrlPrefix", "videoUrlPrefix", "fileUrlPrefix", "imageManagerUrlPrefix", "fileManagerUrlPrefix");
	/**涂鸦上传filename定义**/
	private final static String SCRAWL_FILE_NAME = "scrawl";
	/**远程图片抓取filename定义***/
	private final static String REMOTE_FILE_NAME = "remote";
	
	@Autowired
	private CustomPropertyPlaceholder propertyConfigurer;
	
	@PostConstruct
	public void postConstruct(){
		Map<String, String> map = propertyConfigurer.getAllFields();
		/**通用添加的前缀**/
		final String commonPreffix = map.containsKey("ue.commonPreffix") ? map.get("ue.commonPreffix").toString() : "";
		map.forEach((k, v) -> {
			if(k != null && k.toString().startsWith(UE_PROP_PREFFIX)){
				String key = k.toString().substring(UE_PROP_PREFFIX.length());
				String val = v.toString();
				if(UeditorConfigManager.VIEW_PREFFIX.contains(key)){
					val = commonPreffix + v;
				}
				UeditorConfigManager.CONFIG_MAP.put(key, val);
			}
		});
		/**ue使用的键名. 本地存储的物理路径前缀处理，优先使用附件配置的。**/
		String key = "rootPath";
		/**附件配置的物理路径前缀键名**/
		String key_atta = "attachment.rootPath";
		/**如果附件配置有值，优先使用附件配置值**/
		if(StringUtils.hasContent(map.get(key_atta))){
			UeditorConfigManager.CONFIG_MAP.put(key, map.get(key_atta));
		}
		/**未设置此值，默认使用当前路径**/
		if(!StringUtils.hasContent(UeditorConfigManager.CONFIG_MAP.get(key))){
			UeditorConfigManager.CONFIG_MAP.put(key, "./");
		}
	}
	
	/***
	* @Title: hasKey 
	* @Description: 判断配置是否包含指定键
	* @param key
	* @return
	 */
	private boolean hasKey(String key){
		if(StringUtils.hasContent(key)){
			return UeditorConfigManager.CONFIG_MAP.containsKey(key);
		}
		return false;
	}
	
	/**
	* @Title: getStringOfConfig 
	* @Description: 获取指定键的字符串值
	* @param key
	* @return
	 */
	private String getStringOfConfig(String key){
		if(this.hasKey(key))
			return UeditorConfigManager.CONFIG_MAP.get(key);
		return null;
	}
	
	/**
	* @Title: getLongOfConfig 
	* @Description: 获取指定键值的长整形数字
	* @param key 指定键
	* @return
	 */
	private Long getLongOfConfig(String key){
		String str = this.getStringOfConfig(key);
		if(str != null){
			if(str.matches("[+-]?\\d+")){
				return Long.valueOf(str);
			}
		}
		return null;
	}
	
	/**
	* @Title: getIntegerOfConfig 
	* @Description: 获取指定键的整型数
	* @param key
	* @return
	 */
	private Integer getIntegerOfConfig(String key){
		String str = this.getStringOfConfig(key);
		if(str != null){
			if(str.matches("[+-]?\\d+")){
				return Integer.valueOf(str);
			}
		}
		return null;
	}
	
	/**
	* @Title: getStringArrayOfConfig 
	* @Description: 获取指定键的字符串数组。配置表示的数组必须以英文逗号分割
	* @param key
	* @return
	 */
	private String[] getStringArrayOfConfig(String key){
		String str = this.getStringOfConfig(key);
		if(str != null && UeditorConfigManager.KEYS_OF_ARRAY.contains(key)){
			String split_symbol = ",";
			List<String> list = Arrays.asList(str.split(split_symbol)).stream().filter(one -> one != null).map(one -> one.trim()).collect(Collectors.toList());
			return list.toArray(new String[list.size()]);
		}
		return new String[0];
	}
	
	/**
	* @Title: getSettingJsonString 
	* @Description: 获取可以返回前端的JSON字符串
	* @return
	 */
	public String getSettingJsonString(){
		Map<String, Object> map = new HashMap<String, Object>();
		if(!UeditorConfigManager.CONFIG_MAP.isEmpty()){
			UeditorConfigManager.CONFIG_MAP.forEach((k, v) -> {
				/**过滤掉不返回的**/
				if(!UeditorConfigManager.FILTER_KEYS.contains(k)){
					/**如果是数组的，转换为数组类型返回**/
					if(UeditorConfigManager.KEYS_OF_ARRAY.contains(k)){
						map.put(k, this.getStringArrayOfConfig(k));
					} else {
						map.put(k, v);
					}
				}
			});
			return JsonUtils.toJson(map);
		}
		return "";
	}
	
	/***
	* @Title: getConfig 
	* @Description: 获取配置信息
	* @param type 操作
	* @return
	 */
	public Map<String, Object> getConfig (int type) {
		Map<String, Object> conf = new HashMap<String, Object>();
		String savePath = null;
		switch (type) {
			case ActionMap.UPLOAD_FILE:
				conf.put("isBase64", "false");
				conf.put("maxSize", this.getLongOfConfig("fileMaxSize"));
				conf.put("allowFiles", this.getStringArrayOfConfig("fileAllowFiles"));
				conf.put("fieldName", this.getStringOfConfig("fileFieldName"));
				savePath = this.getStringOfConfig("filePathFormat");
				break;
			case ActionMap.UPLOAD_IMAGE:
				conf.put("isBase64", "false");
				conf.put("maxSize", this.getLongOfConfig("imageMaxSize"));
				conf.put("allowFiles", this.getStringArrayOfConfig("imageAllowFiles"));
				conf.put("fieldName", this.getStringOfConfig("imageFieldName"));
				savePath = this.getStringOfConfig("imagePathFormat");
				break;
			case ActionMap.UPLOAD_VIDEO:
				conf.put("maxSize", this.getLongOfConfig("videoMaxSize"));
				conf.put("allowFiles", this.getStringArrayOfConfig("videoAllowFiles"));
				conf.put("fieldName", this.getStringOfConfig("videoFieldName"));
				savePath = this.getStringOfConfig("videoPathFormat");
				break;
			case ActionMap.UPLOAD_SCRAWL:
				conf.put("filename", UeditorConfigManager.SCRAWL_FILE_NAME);
				conf.put("maxSize", this.getLongOfConfig("scrawlMaxSize"));
				conf.put("fieldName", this.getStringOfConfig("scrawlFieldName"));
				conf.put("isBase64", "true");
				savePath = this.getStringOfConfig("scrawlPathFormat");
				break;
			case ActionMap.CATCH_IMAGE:
				conf.put("filename", UeditorConfigManager.REMOTE_FILE_NAME);
				conf.put("filter", this.getStringArrayOfConfig("catcherLocalDomain"));
				conf.put("maxSize", this.getLongOfConfig("catcherMaxSize"));
				conf.put("allowFiles", this.getStringArrayOfConfig("catcherAllowFiles"));
				conf.put("fieldName", this.getStringOfConfig("catcherFieldName") + "[]" );
				savePath = this.getStringOfConfig("catcherPathFormat");
				break;
			case ActionMap.LIST_IMAGE:
				conf.put("allowFiles", this.getStringArrayOfConfig("imageManagerAllowFiles"));
				conf.put("dir", this.getStringOfConfig("imageManagerListPath"));
				conf.put("count", this.getIntegerOfConfig("imageManagerListSize"));
				break;
			case ActionMap.LIST_FILE:
				conf.put("allowFiles", this.getStringArrayOfConfig("fileManagerAllowFiles"));
				conf.put("dir", this.getStringOfConfig("fileManagerListPath"));
				conf.put("count", this.getStringOfConfig("fileManagerListSize"));
				break;
		}
		conf.put("savePath", savePath);
//		conf.put("rootPath", this.rootPath);
		return conf;
	}
	
	/**
	* @Title: getRootPath 
	* @Description: 获取保存附件的根路径
	* @return
	 */
    public static String getRootPath() {
    	String rootPath = "./";
    	if(UeditorConfigManager.CONFIG_MAP.containsKey("rootPath")){
    		String s = UeditorConfigManager.CONFIG_MAP.get("rootPath");
    		if(StringUtils.hasContent(s)){
    			rootPath = UeditorConfigManager.CONFIG_MAP.get("rootPath");
    		}
    	}
    	if(!rootPath.endsWith("/") && !rootPath.endsWith("\\")){
    		rootPath += File.separatorChar;
    	}
        return rootPath;
    }
}