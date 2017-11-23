package cn.charlie166.web.store.dao;

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
	
	public int selectCount(@ParamCheck Bookmark bookmark);
}