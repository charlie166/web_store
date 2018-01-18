package cn.charlie166.web.store.service.inter;

import cn.charlie166.web.store.constant.CustomException;
import cn.charlie166.web.store.domain.po.Bookmark;

/**
* @ClassName: BookmarkService 
* @Description: 书签操作服务接口
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年11月22日 
*
 */
public interface BookmarkService {

	/**
	* @Title: addSubmit 
	* @Description: 新增提交
	* @param bokmark
	* @return 新增后的id
	 */
	public long addSubmit(Bookmark bookmark) throws CustomException;
}