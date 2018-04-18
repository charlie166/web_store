package cn.charlie166.web.store.plugin.ueditor.upload;

import java.io.File;
import java.io.InputStream;

import cn.charlie166.web.base.service.inter.AttachmentService;
import cn.charlie166.web.base.tool.SpringContextUtils;
import cn.charlie166.web.store.constant.CustomException;
import cn.charlie166.web.store.constant.ExceptionCodes;
import cn.charlie166.web.store.plugin.ueditor.define.AppInfo;
import cn.charlie166.web.store.plugin.ueditor.define.BaseState;
import cn.charlie166.web.store.plugin.ueditor.define.State;
import cn.charlie166.web.store.tools.StringUtils;

public class StorageManager {

	/**
	* @Title: saveBinaryFile 
	* @Description: 保存二进制文件
	* @param data 文件字节数组
	* @param path 保存路径
	* @return 操作状态结果
	 */
	public static State saveBinaryFile(byte[] data, String path) {
		State storageState = new BaseState(false, AppInfo.UNKONWN_ERROR);
		/**获取附件操作服务BEAN**/
    	AttachmentService service = SpringContextUtils.getBean(AttachmentService.class);
		try {
			File storeFile = service.saveBinaryFile(data, path);
			storageState = new BaseState(true);
			storageState.putInfo("size", storeFile.length());
			storageState.putInfo("title", storeFile.getName());
		} catch (CustomException ce) {
			/**根据错误码处理**/
			if(StringUtils.hasContent(ce.getCode())){
				switch(ce.getCode()){
					case ExceptionCodes.FILE_SIZE_EXCEED: {
						storageState = StorageManager.getFalseState(AppInfo.MAX_SIZE);
						break;
					}
					case ExceptionCodes.COMMON_PARAM_ABSENT: {
						storageState = StorageManager.getFalseState(AppInfo.UNKONWN_ERROR);
						break;
					}
					case ExceptionCodes.COMMON_IO_EXCEPTION: {
						storageState = StorageManager.getFalseState(AppInfo.IO_ERROR);
						break;
					}
					case ExceptionCodes.FILE_CREATE_FAIL: {
						storageState = StorageManager.getFalseState(AppInfo.FAILED_CREATE_FILE);
						break;
					}
					case ExceptionCodes.FILE_PERMISSION_DENIED: {
						storageState = StorageManager.getFalseState(AppInfo.PERMISSION_DENIED);
						break;
					}
				}
			}
		}
		return storageState;
	}
	
	/**
	* @Title: saveByInputStrram 
	* @Description: 保存文件流到文件
	* @param is 文件流
	* @param path 保存路径
	* @return 状态信息
	 */
	public static State saveByInputStream(InputStream is, String path){
		return StorageManager.saveByInputStream(is, path, -1);
	}
	
	/**
	* @Title: saveByInputStrram 
	* @Description: 保存输入流到文件
	* @param is 输入文件流
	* @param path 保存路径
	* @param maxSize 文件限制大小
	* @return 状态信息
	 */
	public static State saveByInputStream(InputStream is, String path, long maxSize){
		State storageState = new BaseState(false, AppInfo.UNKONWN_ERROR);
		/**获取附件操作服务BEAN**/
    	AttachmentService service = SpringContextUtils.getBean(AttachmentService.class);
		try {
			File storeFile = service.saveFileByInputStream(is, path, maxSize);
			storageState = new BaseState(true);
			storageState.putInfo("size", storeFile.length());
			storageState.putInfo("title", storeFile.getName());
		} catch (CustomException ce) {
			/**根据错误码处理**/
			if(StringUtils.hasContent(ce.getCode())){
				switch(ce.getCode()){
					case ExceptionCodes.FILE_SIZE_EXCEED: {
						storageState = StorageManager.getFalseState(AppInfo.MAX_SIZE);
						break;
					}
					case ExceptionCodes.COMMON_PARAM_ABSENT: {
						storageState = StorageManager.getFalseState(AppInfo.UNKONWN_ERROR);
						break;
					}
					case ExceptionCodes.COMMON_IO_EXCEPTION: {
						storageState = StorageManager.getFalseState(AppInfo.IO_ERROR);
						break;
					}
					case ExceptionCodes.FILE_CREATE_FAIL: {
						storageState = StorageManager.getFalseState(AppInfo.FAILED_CREATE_FILE);
						break;
					}
					case ExceptionCodes.FILE_PERMISSION_DENIED: {
						storageState = StorageManager.getFalseState(AppInfo.PERMISSION_DENIED);
						break;
					}
				}
			}
		}
		return storageState;
	}
	
	/**
	* @Title: getFalseState 
	* @Description: 获取一个表示失败的状态信息
	* @param code 状态值
	* @return
	 */
	private static State getFalseState(int code){
		return new BaseState(false, code);
	}
}