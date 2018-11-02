package cn.charlie166.web.tumblr.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.charlie166.web.tumblr.domain.po.Video;

/**
* @ClassName: TumblrDao 
* @Description: 汤伯乐数据库操作接口
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年11月2日 
*
 */
public interface TumblrDao {
	
	/**
	* @Title: selectVideoCount 
	* @Description: 查询视频条目数
	* @param v
	* @return
	 */
	public int selectVideoCount(@Param(value = "v") Video v);
	
	/**
	* @Title: selectVideoList 
	* @Description: 查询列表
	* @param v
	* @return
	 */
	public List<Video> selectVideoList(@Param(value = "v") Video v);
	
	/**
	* @Title: selectVideoListLimit 
	* @Description: 限制查询
	* @param v
	* @return
	 */
	public List<Video> selectVideoListLimit(@Param(value = "v") Video v, @Param(value = "start") int start, @Param(value = "size") int size);
	
	/**
	* @Title: seletVideoById 
	* @Description: 通过主键ID查询视频
	* @param id 主键ID
	* @return
	 */
	public Video seletVideoById(@Param(value = "id") String id);

}