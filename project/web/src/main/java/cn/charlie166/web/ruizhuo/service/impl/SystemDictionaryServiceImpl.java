package cn.charlie166.web.ruizhuo.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.charlie166.web.base.service.impl.BaseServiceImpl;
import cn.charlie166.web.ruizhuo.service.inter.SystemDictionaryService;
import cn.charlie166.web.ruizhuo.tool.DictionaryMap;

/**
* @ClassName: SystemDictionaryServiceImpl 
* @Description: 系统数据字典服务实现
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年4月13日 
*
 */
@Service
public class SystemDictionaryServiceImpl extends BaseServiceImpl implements SystemDictionaryService {

	@Override
	public void reset() {
		logger.debug("reset....");
		DictionaryMap.refresh();
	}

	@Override
	public List<Map<String, Object>> getAll() {
		return Collections.emptyList();
	}

}