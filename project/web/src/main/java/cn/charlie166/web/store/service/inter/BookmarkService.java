package cn.charlie166.web.store.service.inter;

import cn.charlie166.web.base.service.inter.BaseService;
import cn.charlie166.web.store.constant.CustomException;
import cn.charlie166.web.store.domain.dto.BookmarkDTO;
import cn.charlie166.web.store.domain.dto.PageDTO;

/**
* @ClassName: BookmarkService 
* @Description: 书签操作服务接口
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年11月22日 
*
 */
public interface BookmarkService extends BaseService {

	/**
	* @Title: addSubmit 
	* @Description: 新增提交
	* @param bokmark
	* @return 新增后的id
	 */
	public long addSubmit(BookmarkDTO bookmark) throws CustomException;
	
	/**
	* @Title: editSubmit 
	* @Description: 修改提交
	* @param bookmark 必须存在ID
	* @return
	* @throws CustomException
	 */
	public long editSubmit(BookmarkDTO bookmark) throws CustomException;
	
	/**
	* @Title: detail 
	* @Description: 书签详情
	* @param id 书签ID
	* @return
	* @throws CustomException
	 */
	public BookmarkDTO detail(String id) throws CustomException;
	
	/**
	* @Title: page 
	* @Description: 书签分页查询
	* @param page 查询条件
	* @return
	 */
	PageDTO<BookmarkDTO> page(PageDTO<BookmarkDTO> page);
}