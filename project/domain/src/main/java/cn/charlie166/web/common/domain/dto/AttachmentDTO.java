package cn.charlie166.web.common.domain.dto;

import java.nio.file.Path;

/**
* @ClassName: Attachment 
* @Description: 附件传输数据对象
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年4月18日 
*
 */
public class AttachmentDTO extends BaseDTO {

	/**所属模块**/
	private String module;
	/**用于标识属于模块哪个类型。如用户的头像和证件照. 这个一般在业务中自己定义值**/
	private Integer moduleType;
	/**文件相对路径，用于定位到文件**/
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
	private String suffix;
	/**文件大小。单位:Byte**/
	private Long size;
	/**对应文件**/
	private Path file;
	
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
	public Path getFile() {
		return file;
	}
	public void setFile(Path file) {
		this.file = file;
	}
	
}