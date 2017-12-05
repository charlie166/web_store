package cn.charlie166.web.store.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.charlie166.web.store.dao.PacDao;
import cn.charlie166.web.store.domain.po.PacModel;
import cn.charlie166.web.store.service.inter.PacService;

/**
* @ClassName: PacServiceImpl 
* @Description: PAC 服务接口实现类
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年12月5日 
*
 */
@Service
public class PacServiceImpl implements PacService {

	@Autowired
	private PacDao pacDao;

	@Override
	public int insertOne(PacModel pac) {
		return this.insertBatch(Arrays.asList(pac));
	}

	@Override
	public int insertBatch(List<PacModel> pacList) {
		return 0;
	}
}