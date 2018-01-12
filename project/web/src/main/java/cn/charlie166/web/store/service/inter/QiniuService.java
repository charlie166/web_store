package cn.charlie166.web.store.service.inter;

import java.io.File;

import cn.charlie166.web.store.domain.third.QiniuRet;

/**
* @ClassName: QiniuService 
* @Description: 七牛服务接口
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年1月12日 
*
 */
public interface QiniuService {

	/**
	* @Title: getUploadToken 
	* @Description: 获取上传凭证
	* @return
	 */
	public String getUploadToken();
	
	/**
	* @Title: uploadFile 
	* @Description: 上传文件到七牛
	* @param file
	* @return 返回七牛访问相对路径
	 */
	public String uploadFile(File file);
	
	/**
	* @Title: uploadFile 
	* @Description: 上传文件到七牛
	* @param file 上传文件
	* @param key 文件路径名称
	 */
	public void uploadFile(File file, String key);
	
	/**
	* @Title: deleteFile 
	* @Description: 删除七牛存储文件
	* @param key 要删除的文件路径名称
	 */
	public void deleteFile(String key);
	
	/**
	* @Title: callback 
	* @Description: 七牛上传回调处理
	* @param back
	* @return
	 */
	public QiniuRet callback(QiniuRet back);
}