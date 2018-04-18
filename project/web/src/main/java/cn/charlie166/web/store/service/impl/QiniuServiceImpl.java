package cn.charlie166.web.store.service.impl;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import cn.charlie166.web.base.service.impl.BaseServiceImpl;
import cn.charlie166.web.common.domain.third.QiniuResponse;
import cn.charlie166.web.common.domain.third.QiniuRet;
import cn.charlie166.web.store.service.inter.QiniuService;
import cn.charlie166.web.store.tools.CustomFileUtils;
import cn.charlie166.web.store.tools.JsonUtils;
import cn.charlie166.web.store.tools.StringUtils;

/**
* @ClassName: QiniuServiceImpl 
* @Description: 七牛服务实现类
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年1月12日 
*
 */
@Service
public class QiniuServiceImpl extends BaseServiceImpl implements QiniuService {
	
	@Value(value = "${qiniu.access.key}")
	private String accessKey;
	@Value(value = "${qiniu.secret.key}")
	private String secretKey;
	@Value(value = "${qiniu.bucket}")
	private String bucket;
	@Value(value = "${qiniu.callback.url:null}")
	private String callbackUrl;
	@Value(value = "${qiniu.url.preffix:}")
	private String qiniuPreffix;
	
	@Override
	public String getUploadToken() {
		StringMap putPolicy = new StringMap();
		Auth auth = Auth.create(accessKey, secretKey);
		if(StringUtils.hasContent(callbackUrl) &&
			(callbackUrl.startsWith("http://") || callbackUrl.startsWith("https://"))){
			putPolicy.put("callbackUrl", callbackUrl);
			putPolicy.put("callbackBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize),\"name\":$(fname),\"mime\":$(mimeType)}");
			putPolicy.put("callbackBodyType", "application/json");
		}
		putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize),\"name\":$(fname),\"mime\":$(mimeType)}");
		putPolicy.put("saveKey", "$(year)/$(mon)/$(day)/$(mimeType)/$(etag)$(ext)");
		long expireSeconds = 3600 * 2;
		String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
		logger.debug("七牛上传凭证:" + upToken);
		return upToken;
	}
	
	@Override
	public String uploadFile(File file) {
		/***默认不指定key的情况下，以文件内容的hash值作为文件名***/
		String filename = file.getName();
		String suffix = CustomFileUtils.getSuffix(filename);
		/**处理新的文件名**/
		String newName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/HH/mmssSSS")) + Thread.currentThread().getId() +
			filename.hashCode() + (StringUtils.hasContent(suffix) ? ("." + suffix) : "");
		this.uploadFile(file, newName);
		return newName;
	}

	@Override
	public void uploadFile(File file, String key) {
		if(file.exists() && file.isFile()){
			/**构造一个带指定Zone对象的配置类**/
			Configuration cfg = new Configuration(this.getThisZone());
			UploadManager uploadManager = new UploadManager(cfg);
			try {
				String upToken = this.getUploadToken();
			    Response response = uploadManager.put(file, key, upToken);
			    /**解析上传成功的结果**/
			    QiniuResponse putRet = JsonUtils.fromJson(response.bodyString(), QiniuResponse.class);
			    logger.debug("七牛上传返回:" + JsonUtils.toJson(putRet));
			} catch (QiniuException ex) {
			    Response r = ex.response;
			    logger.error("七牛异常{" + r.toString() + "}", ex);
			}
		}
	}

	@Override
	public void deleteFile(String key) {
		if(StringUtils.hasContent(key)){
			Configuration cfg = new Configuration(this.getThisZone());
			Auth auth = Auth.create(accessKey, secretKey);
			BucketManager bucketManager = new BucketManager(auth, cfg);
			try {
				bucketManager.delete(bucket, key);
			} catch (QiniuException ex) {
				logger.error("七牛异常{" + ex.response.toString() + "}", ex);
			}
		}
	}

	@Override
	public QiniuRet callback(QiniuRet back) {
		logger.debug("七牛回调参数:" + JsonUtils.toJson(back));
		return back;
	}
	
	/**
	* @Title: getThisZone 
	* @Description: 获取当前使用的七牛域名配置
	* @return
	 */
	private Zone getThisZone(){
		return Zone.zone0();
	}

	@Override
	public String getQiniuPreffix() {
		return this.qiniuPreffix;
	}
}