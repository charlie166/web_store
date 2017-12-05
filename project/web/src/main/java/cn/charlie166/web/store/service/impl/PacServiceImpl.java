package cn.charlie166.web.store.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cn.charlie166.web.store.dao.PacDao;
import cn.charlie166.web.store.domain.po.PacModel;
import cn.charlie166.web.store.service.inter.PacService;
import cn.charlie166.web.store.tools.StringUtils;

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
		if(!CollectionUtils.isEmpty(pacList)) {
			String domainRegex = "^(?=^.{3,255}$)[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+$";
			for(PacModel pm: pacList) {
				if(pm != null) {
					/**校验是否为有效域名**/
					if(StringUtils.hasContent(pm.getDomain())) {
						if(!pm.getDomain().matches(domainRegex)) {
							pm.setFlag(Boolean.FALSE);
						}
					}
				}
			}
			return pacDao.batchInsert(pacList);
		} else {
			return 0;
		}
	}
}