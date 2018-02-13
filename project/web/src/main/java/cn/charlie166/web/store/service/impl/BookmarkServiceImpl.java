package cn.charlie166.web.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class BookmarkServiceImpl implements BookmarkService {

	@Autowired
	private BookmarkDao bookmarkDao;

	@Override
	public long addSubmit(Bookmark bookmark) throws CustomException {
		if(bookmark == null) {
			throw CustomException.instance(ExceptionCodes.COMMON_PARAM_ABSENT);
		}
		if(!StringUtils.hasContent(bookmark.getTitle())) {
			throw CustomException.instance(ExceptionCodes.BOOKMARK_NEED_TITLE);
		}
		if(!HtmlUtils.hasContent(bookmark.getContent())) {
			throw CustomException.instance(ExceptionCodes.BOOKMARK_NEED_CONTENT);
		}
		bookmarkDao.insertOne(bookmark);
		return bookmark.getId() != null ? bookmark.getId() : -1;
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
}