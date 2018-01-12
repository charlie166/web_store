package cn.charlie166.web.store.domain.third;

/**
* @ClassName: QiniuRet 
* @Description: 七牛上传返回数据
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年1月12日 
*
 */
public class QiniuRet {

	/**文件保存在空间中的资源名**/
	private String key;
	
	/****/
	private String hash;
	
	/**上传的目标空间名**/
	private String bucket;
	
	/**文件大小，单位为字节**/
	private Integer fsize;
	
	/**文件名**/
	private String name;
	
	/**资源类型**/
	private String mime;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public Integer getFsize() {
		return fsize;
	}

	public void setFsize(Integer fsize) {
		this.fsize = fsize;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}
}
