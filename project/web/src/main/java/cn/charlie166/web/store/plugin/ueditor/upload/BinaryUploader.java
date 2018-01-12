package cn.charlie166.web.store.plugin.ueditor.upload;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import cn.charlie166.web.store.plugin.ueditor.PathFormat;
import cn.charlie166.web.store.plugin.ueditor.UeditorConfigManager;
import cn.charlie166.web.store.plugin.ueditor.define.AppInfo;
import cn.charlie166.web.store.plugin.ueditor.define.BaseState;
import cn.charlie166.web.store.plugin.ueditor.define.FileType;
import cn.charlie166.web.store.plugin.ueditor.define.State;

public class BinaryUploader {

	private static Logger logger = LoggerFactory.getLogger(BinaryUploader.class);
	
	public static final State save(HttpServletRequest request, Map<String, Object> conf) {
		boolean isAjaxUpload = request.getHeader("X_Requested_With") != null;
		if (!ServletFileUpload.isMultipartContent(request)) {
			return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
		}
		ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
        if (isAjaxUpload) {
            upload.setHeaderEncoding("UTF-8");
        }
        State retState = new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
        try {
			if(request instanceof MultipartRequest) {
				MultipartRequest req =	(MultipartRequest)request;
				Map<String, MultipartFile> map = req.getFileMap();
				if(map != null && !map.isEmpty()){
					Collection<MultipartFile> cmf = map.values();
					if(cmf != null && cmf.size() > 0){
						String savePath = (String) conf.get("savePath");
						for(MultipartFile mf : cmf){
							String originFileName = mf.getOriginalFilename();
							String suffix = FileType.getSuffixByFilename(originFileName);
							originFileName = originFileName.substring(0, originFileName.length() - suffix.length());
							savePath = savePath + suffix;
							long maxSize = ((Long) conf.get("maxSize")).longValue();
							if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
								return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
							}
							savePath = PathFormat.parse(savePath, originFileName);
				            String rootPath = UeditorConfigManager.getRootPath();
				            String physicalPath = rootPath + savePath;
							InputStream is = mf.getInputStream();
							State storageState = StorageManager.saveByInputStream(is, physicalPath, maxSize);
							is.close();
							if (storageState.isSuccess()) {
								storageState.putInfo("url", PathFormat.format(savePath));
								storageState.putInfo("type", suffix);
								storageState.putInfo("original", originFileName + suffix);
							}
							return storageState;
						}
					}
				}
			}
		} catch (Exception e) {
			BinaryUploader.logger.error("上传出现异常", e);
			retState = new BaseState(false, AppInfo.SERVER_ERROR);
		}
		return retState;
	}
	
	private static boolean validType(String type, String[] allowTypes) {
		List<String> list = Arrays.asList(allowTypes);
		return list.contains(type);
	}
}