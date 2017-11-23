package cn.charlie166.web.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.charlie166.web.store.dao.BookmarkDao;
import cn.charlie166.web.store.dao.DemoDao;
import cn.charlie166.web.store.domain.po.Bookmark;
import cn.charlie166.web.store.service.inter.DemoService;

/**
* @ClassName: DemoServiceImpl 
* @Description: 演示用服务实现类
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年11月15日 
*
 */
@Service
public class DemoServiceImpl implements DemoService {

	@Autowired
	private DemoDao demoDao;
	
	@Autowired
	private BookmarkDao bookmarkDao;
	
	@Override
	public int selectDemoCount() {
		Bookmark bm = new Bookmark();
		bm.setTitle("哈热个人公司的方法个是");
		bookmarkDao.selectCount(bm);
		return demoDao.selectDemoCount();
	}

}