package cn.charlie166.web.tumblr.service;

import cn.charlie166.web.common.domain.dto.PageDTO;
import cn.charlie166.web.store.constant.CustomException;
import cn.charlie166.web.tumblr.domain.dto.VideoDTO;

/**
* @ClassName: TumblrService 
* @Description: 服务接口
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年11月2日 
*
 */
public interface TumblrService {

	/**
	* @Title: detail 
	* @Description: 视频详情
	* @param id 视频ID
	* @return
	* @throws CustomException
	 */
	public VideoDTO detail(String id) throws CustomException;
	
	/**
	* @Title: page 
	* @Description: 分页查询
	* @param page 查询条件
	* @return
	 */
	PageDTO<VideoDTO> page(PageDTO<VideoDTO> page);
}