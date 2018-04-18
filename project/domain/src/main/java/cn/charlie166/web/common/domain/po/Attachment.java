package cn.charlie166.web.common.domain.po;

import cn.charlie166.web.common.domain.annotation.StringCheck;

/**
* @ClassName: Attachment 
* @Description: 附件
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年4月18日 
*
 */
public class Attachment extends BaseEntity {

	/**所属模块**/
	@StringCheck(maxLength = 64)
	private String module;
	/**用于标识属于模块哪个类型。如用户的头像和证件照. 这个一般在业务中自己定义值**/
	private Integer moduleType;
	/**文件相对路径，用于定位到文件**/
	@StringCheck(mustHasContent = true, mustHasContentTip = "附件地址必须", maxLength = 128)
	private String path;
	/**相关数据ID**/
	private Long info;
	/**附件唯一标识**/
	private String uuid;
	/**原文件名**/
	private String originalName;
	/**排序值**/
	private Integer sort;
	/**文件格式。不包含英文点(如:zip;rar)**/
	@StringCheck(maxLength = 10)
	private String suffix;
	/**文件大小。单位:Byte**/
	private Long size;
	
	public Attachment(){
	}
	
	public Attachment(String path, String originalName, String suffix){
		this.path = path;
		this.originalName = originalName;
		this.suffix = suffix;
		this.sort = 1;
	}
	
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public Integer getModuleType() {
		return moduleType;
	}
	public void setModuleType(Integer moduleType) {
		this.moduleType = moduleType;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Long getInfo() {
		return info;
	}
	public void setInfo(Long info) {
		this.info = info;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	
	/**
	* @Title: of 
	* @Description: 创建附件信息对象
	* @param path 附件相对路径
	* @param originalName 附件原名称
	* @param suffix 附件格式
	* @return
	 */
	public static Attachment of(String path, String originalName, String suffix){
		return new Attachment(path, originalName, suffix);
	}
}