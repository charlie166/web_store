package cn.charlie166.web.store.plugin.ueditor.define;

import java.util.HashMap;
import java.util.Map;

import cn.charlie166.web.store.constant.UeditorConstant;

/**
 * @description 定义请求action类型
 * @author <a href="mailto:charlie166@163.com">李阳</a> 
 * @version 1.0, 2017年11月27日
 * @see     
 * @since   web 1.0
 */
public class ActionMap {
	
	public static final Map<String, Integer> mapping;
	// 获取配置请求
	public static final int CONFIG = 0;
	public static final int UPLOAD_IMAGE = 1;
	public static final int UPLOAD_SCRAWL = 2;
	public static final int UPLOAD_VIDEO = 3;
	public static final int UPLOAD_FILE = 4;
	public static final int CATCH_IMAGE = 5;
	public static final int LIST_FILE = 6;
	public static final int LIST_IMAGE = 7;
	
	static {
		mapping = new HashMap<String, Integer>(){
			private static final long serialVersionUID = 1L;
			{
				put(UeditorConstant.CONFIG, ActionMap.CONFIG);
				put(UeditorConstant.UPLOAD_IMAGE, ActionMap.UPLOAD_IMAGE);
				put(UeditorConstant.UPLOAD_SCRAWL, ActionMap.UPLOAD_SCRAWL);
				put(UeditorConstant.UPLOAD_VIDEO, ActionMap.UPLOAD_VIDEO);
				put(UeditorConstant.UPLOAD_FILE, ActionMap.UPLOAD_FILE);
				put(UeditorConstant.CATCH_IMAGE, ActionMap.CATCH_IMAGE);
				put(UeditorConstant.LIST_FILE, ActionMap.LIST_FILE);
				put(UeditorConstant.LIST_IMAGE, ActionMap.LIST_IMAGE);
			}
		};
	}
	
	public static int getType ( String key ) {
		return ActionMap.mapping.get( key );
	}
}