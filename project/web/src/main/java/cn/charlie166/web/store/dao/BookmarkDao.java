package cn.charlie166.web.store.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.charlie166.web.common.domain.annotation.ParamCheck;
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
	public int selectCount(@Param(value = "bk") Bookmark bookmark);
	
	/**
	* @Title: selectList 
	* @Description: 查询列表
	* @param bookmark
	* @return
	 */
	public List<Bookmark> selectList(@Param(value = "bk") Bookmark bookmark);
	
	/**
	* @Title: selectListLimit 
	* @Description: 限制查询
	* @param bookmark
	* @return
	 */
	public List<Bookmark> selectListLimit(@Param(value = "bk") Bookmark bookmark, @Param(value = "start") int start, @Param(value = "size") int size);
	
	/**
	* @Title: seletById 
	* @Description: 通过主键ID查询书签
	* @param id 主键ID
	* @return
	 */
	public Bookmark seletById(@Param(value = "id") long id);
	
}