package cn.charlie166.web.store.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.charlie166.web.base.service.impl.BaseServiceImpl;
import cn.charlie166.web.common.domain.dto.PageDTO;
import cn.charlie166.web.store.constant.CustomException;
import cn.charlie166.web.store.constant.ExceptionCodes;
import cn.charlie166.web.store.dao.BookmarkDao;
import cn.charlie166.web.store.domain.dto.BookmarkDTO;
import cn.charlie166.web.store.domain.po.Bookmark;
import cn.charlie166.web.store.service.inter.BookmarkService;
import cn.charlie166.web.store.tools.ClassUtils;
import cn.charlie166.web.store.tools.HtmlUtils;
import cn.charlie166.web.store.tools.StringUtils;

/**
* @ClassName: BookmarkServiceImpl 
* @Description: 书签操作服务实现类
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年11月22日 
*
 */
@Service
public class BookmarkServiceImpl extends BaseServiceImpl implements BookmarkService {

	@Autowired
	private BookmarkDao bookmarkDao;

	@Override
	public long addSubmit(BookmarkDTO bk) throws CustomException {
		Bookmark bookmark = ClassUtils.convertType(bk, Bookmark.class);
		this.commonCheck(bookmark);
		bookmarkDao.insertOne(bookmark);
		if(bookmark.getId() != null)
			return bookmark.getId();
		else {
			throw CustomException.instance(ExceptionCodes.COMMON_FAILED);
		}
	}

	@Override
	public BookmarkDTO detail(String id) throws CustomException {
		if(StringUtils.isInteger(id)){
			Bookmark bk = bookmarkDao.seletById(Long.valueOf(id));
			if(bk != null){
				BookmarkDTO dto = ClassUtils.convertType(bk, BookmarkDTO.class);
				if(dto != null)
					return dto;
			}
		}
		throw CustomException.instance(ExceptionCodes.COMMON_DATA_ABSENT);
	}

	@Override
	public long editSubmit(BookmarkDTO bk) throws CustomException {
		Bookmark bookmark = ClassUtils.convertType(bk, Bookmark.class);
		this.commonCheck(bookmark);
		int result = bookmarkDao.updateById(bookmark);
		if(result > 0){
			return bookmark.getId();
		} else {
			throw CustomException.instance(ExceptionCodes.COMMON_FAILED);
		}
	}
	
	/**
	* @Title: commonCheck 
	* @Description: 共用校验
	* @param bookmark
	 */
	private void commonCheck(Bookmark bookmark){
		if(bookmark == null) {
			throw CustomException.instance(ExceptionCodes.COMMON_PARAM_ABSENT);
		}
		/**存在ID时，为修改**/
		if(bookmark.getId() != null && bookmark.getId().longValue() > 0){
			if(!StringUtils.hasContent(bookmark.getTitle()) ||
				!HtmlUtils.hasContent(bookmark.getContent()) ||
				!HtmlUtils.hasContent(bookmark.getCommentary()) ||
				!HtmlUtils.hasContent(bookmark.getLink())){
				throw CustomException.instance(ExceptionCodes.COMMON_PARAM_ABSENT);
			}
		} else {
			if(!StringUtils.hasContent(bookmark.getTitle())) {
				throw CustomException.instance(ExceptionCodes.BOOKMARK_NEED_TITLE);
			}
			if(!HtmlUtils.hasContent(bookmark.getContent())) {
				throw CustomException.instance(ExceptionCodes.BOOKMARK_NEED_CONTENT);
			}
		}
	}

	@Override
	public PageDTO<BookmarkDTO> page(PageDTO<BookmarkDTO> page) {
		Map<String, Object> condition = page.getCondition();
		Bookmark bk = ClassUtils.convertType(condition, Bookmark.class);
		int total = bookmarkDao.selectCount(bk);
		if(total > 0){
			page.setTotal(total);
			List<Bookmark> list = bookmarkDao.selectListLimit(bk, page.getStart(), page.getPageSize());
			page.setRecords(ClassUtils.convertTypeOfList(list, BookmarkDTO.class));
		}
		return page;
	}
}