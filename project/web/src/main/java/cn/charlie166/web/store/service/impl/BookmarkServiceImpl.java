package cn.charlie166.web.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import cn.charlie166.web.store.dao.BookmarkDao;
import cn.charlie166.web.store.service.inter.BookmarkService;

/**
* @ClassName: BookmarkServiceImpl 
* @Description: 书签操作服务实现类
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年11月22日 
*
 */
public class BookmarkServiceImpl implements BookmarkService {

	@Autowired
	private BookmarkDao bookmarkDao;
}