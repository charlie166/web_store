package cn.charlie166.web.tumblr.domain.po;

import java.time.LocalDateTime;

import cn.charlie166.web.tumblr.domain.enums.PostFlag;

/**
* @ClassName: Video 
* @Description: 视频对象
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年11月2日 
*
 */
public class Video {


	/**视频ID**/
	private String id;
	/**发布视频博客的标题**/
	private String title;
	/**发布视频博客的描述**/
	private String description;
	/**视频地址**/
	private String url;
	/**是否已下载到本地**/
	private Boolean local;
	/**本地文件路径. 相对路径**/
	private String path;
	/**博客发布时间**/
	private LocalDateTime publish;
	/**记录保存时间**/
	private LocalDateTime storeTime;
	/**标识从哪里来的. **/
	private PostFlag flag;
	/**文件大小**/
	private Long size;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Boolean getLocal() {
		return local;
	}
	public void setLocal(Boolean local) {
		this.local = local;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public LocalDateTime getPublish() {
		return publish;
	}
	public void setPublish(LocalDateTime publish) {
		this.publish = publish;
	}
	public LocalDateTime getStoreTime() {
		return storeTime;
	}
	public void setStoreTime(LocalDateTime storeTime) {
		this.storeTime = storeTime;
	}
	public PostFlag getFlag() {
		return flag;
	}
	public void setFlag(PostFlag flag) {
		this.flag = flag;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}

}