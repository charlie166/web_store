package cn.charlie166.web.store.plugin.ueditor.upload;

import java.util.Base64;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.charlie166.web.store.plugin.ueditor.PathFormat;
import cn.charlie166.web.store.plugin.ueditor.UeditorConfigManager;
import cn.charlie166.web.store.plugin.ueditor.define.AppInfo;
import cn.charlie166.web.store.plugin.ueditor.define.BaseState;
import cn.charlie166.web.store.plugin.ueditor.define.FileType;
import cn.charlie166.web.store.plugin.ueditor.define.State;

public final class Base64Uploader {

	public static State save(HttpServletRequest request, Map<String, Object> conf) {
	    String filedName = (String) conf.get("fieldName");
		String fileName = request.getParameter(filedName);
		byte[] data = decode(fileName);
		long maxSize = ((Long) conf.get("maxSize")).longValue();
		if (!validSize(data, maxSize)) {
			return new BaseState(false, AppInfo.MAX_SIZE);
		}
		String suffix = FileType.getSuffix("JPG");
		String savePath = PathFormat.parse((String) conf.get("savePath"), (String) conf.get("filename"));
		savePath = savePath + suffix;
		String rootPath = UeditorConfigManager.getRootPath();
		String physicalPath = rootPath + savePath;
		State storageState = StorageManager.saveBinaryFile(data, physicalPath);
		if (storageState.isSuccess()) {
			storageState.putInfo("url", PathFormat.format(savePath));
			storageState.putInfo("type", suffix);
			storageState.putInfo("original", "");
		}
		return storageState;
	}

	private static byte[] decode(String content) {
		return Base64.getDecoder().decode(content);
	}

	private static boolean validSize(byte[] data, long length) {
		return data.length <= length;
	}
}