package cn.charlie166.web.store.dao;

import org.apache.ibatis.annotations.Param;

import cn.charlie166.web.store.domain.annotation.ParamCheck;
import cn.charlie166.web.store.domain.po.Bookmark;

/**
* @ClassName: BookmarkDao 
* @Description: 书签数据库操作接口
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年11月22日 
*
 */
public interface BookmarkDao {

	/**
	* @Title: insertOne 
	* @Description: 保存一条书签记录
	* @return
	 */
	public int insertOne(@ParamCheck Bookmark bookmark);
	
	/**
	* @Title: updateById 
	* @Description: 根据ID更新数据
	* @param bookmark
	* @return
	 */
	public int updateById(@ParamCheck(ignore = "content") Bookmark bookmark);
	
	/**
	* @Title: selectCount 
	* @Description: 查询条目数
	* @param bookmark
	* @return
	 */
	public int selectCount(Bookmark bookmark);
	
	/**
	* @Title: seletById 
	* @Description: 通过主键ID查询书签
	* @param id 主键ID
	* @return
	 */
	public Bookmark seletById(@Param(value = "id") long id);
}