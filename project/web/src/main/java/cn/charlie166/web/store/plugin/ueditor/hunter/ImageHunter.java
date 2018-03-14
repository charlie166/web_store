package cn.charlie166.web.store.plugin.ueditor.hunter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.charlie166.web.store.plugin.ueditor.PathFormat;
import cn.charlie166.web.store.plugin.ueditor.define.AppInfo;
import cn.charlie166.web.store.plugin.ueditor.define.BaseState;
import cn.charlie166.web.store.plugin.ueditor.define.MIMEType;
import cn.charlie166.web.store.plugin.ueditor.define.MultiState;
import cn.charlie166.web.store.plugin.ueditor.define.State;
import cn.charlie166.web.store.plugin.ueditor.upload.StorageManager;

/**
 * @description 图片抓取
 * @author <a href="mailto:charlie166@163.com">李阳</a> 
 * @version 1.0, 2017年11月27日
 * @see     
 * @since   web 1.0
 */
public class ImageHunter {

	private String filename = null;
	private String savePath = null;
	private String rootPath = null;
	private List<String> allowTypes = null;
	private long maxSize = -1;
	private List<String> filters = null;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public ImageHunter(Map<String, Object> conf) {
		this.filename = (String)conf.get("filename");
		this.savePath = (String)conf.get("savePath");
		this.rootPath = (String)conf.get("rootPath");
		this.maxSize = (Long)conf.get("maxSize");
		this.allowTypes = Arrays.asList((String[])conf.get("allowFiles"));
		this.filters = Arrays.asList((String[])conf.get("filter"));
		
	}
	
	public State capture(String[] list) {
		MultiState state = new MultiState(true);
		for ( String source : list ) {
			state.addState( captureRemoteData( source ) );
		}
		return state;
	}

	public State captureRemoteData(String urlStr) {
		try {
			CloseableHttpClient hc = HttpClientBuilder.create().build();
			HttpGet get = new HttpGet(urlStr);
			get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:55.0) Gecko/20100101 Firefox/55.0");
			get.setHeader("Accept", "*/*");
			try {
				String host = get.getURI().getHost();
				if (!validHost(host)) {
					return new BaseState( false, AppInfo.PREVENT_HOST);
				}
				CloseableHttpResponse resp = hc.execute(get);
				StatusLine statusLine = resp.getStatusLine();
				if(statusLine != null){
					logger.error("请求[{}]返回状态码: [{}]", urlStr, statusLine.getStatusCode());
					if(statusLine.getStatusCode() == HttpStatus.SC_OK){
						HttpEntity httpEntity = resp.getEntity();
						Header contentType = httpEntity.getContentType();
						String suffix = MIMEType.getSuffix(contentType.getValue());
						if (!validFileType(suffix)) {
							return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
						}
						InputStream inputStream = httpEntity.getContent();
						long contentLength = httpEntity.getContentLength();
						if (!validFileSize(contentLength)) {
							return new BaseState(false, AppInfo.MAX_SIZE);
						}
						String savePath = this.getPath(this.savePath, this.filename, suffix);
						String physicalPath = this.rootPath + savePath;
						State state = StorageManager.saveByInputStream(inputStream, physicalPath);
						if (state.isSuccess()) {
							state.putInfo("url", PathFormat.format(savePath));
							state.putInfo("source", urlStr);
						}
						return state;
					} else {
						return new BaseState( false, AppInfo.CONNECTION_ERROR );
					}
				} else {
					logger.error("请求{}返回状态行为空", urlStr);
					return new BaseState( false, AppInfo.CONNECTION_ERROR );
				}
			} catch (IOException e) {
				e.printStackTrace();
				return new BaseState( false, AppInfo.CONNECTION_ERROR );
			}
		} catch (Exception e) {
			return new BaseState(false, AppInfo.REMOTE_FAIL);
		}
		
	}
	
	private String getPath(String savePath, String filename, String suffix ) {
		return PathFormat.parse(savePath + suffix, filename);
	}
	
	private boolean validHost(String hostname) {
		return !filters.contains( hostname );
	}
	
	private boolean validFileType(String type) {
		return this.allowTypes.contains(type);
	}
	
	private boolean validFileSize(long size) {
		return size < this.maxSize;
	}
}