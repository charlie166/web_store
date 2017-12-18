package cn.charlie166.web.store.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import cn.charlie166.web.store.constant.CustomException;
import cn.charlie166.web.store.constant.ExceptionCodes;
import cn.charlie166.web.store.dao.PacDao;
import cn.charlie166.web.store.domain.dto.PacDTO;
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
	public int insertOne(PacModel pac) throws CustomException {
		return this.insertBatch(Arrays.asList(pac));
	}

	@Override
	@Transactional
	public int insertBatch(List<PacModel> pacList) throws CustomException {
		if(!CollectionUtils.isEmpty(pacList)) {
			String domainRegex = "^(?=^.{3,255}$)[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+$";
			/**已经保存了的域名**/
			Set<String> domainSet = pacDao.selectAll().stream().filter(one -> one != null && StringUtils.hasContent(one.getDomain()))
				.map(one -> one.getDomain()).distinct().collect(Collectors.toSet());
			for(PacModel pm: pacList) {
				if(pm != null) {
					/**校验是否为有效域名**/
					if(StringUtils.hasContent(pm.getDomain())) {
						if(!pm.getDomain().matches(domainRegex)) {
							pm.setFlag(Boolean.FALSE);
						}
						if(domainSet.contains(pm.getDomain())){
							throw CustomException.instance(ExceptionCodes.PAC_DOMAIN_EXISTS, String.format("域名%s已存在", pm.getDomain()));
						} else {
							domainSet.add(pm.getDomain());
						}
					}
				}
			}
			return pacDao.batchInsert(pacList);
		} else {
			return 0;
		}
	}

	@Override
	public List<PacDTO> all() {
		return null;
	}
}