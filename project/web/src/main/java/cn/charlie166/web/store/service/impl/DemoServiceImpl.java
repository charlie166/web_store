package cn.charlie166.web.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.charlie166.web.store.dao.DemoDao;
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
	
	@Override
	public int selectDemoCount() {
		return demoDao.selectDemoCount();
	}

}