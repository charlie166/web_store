package cn.charlie166.web.store.service.impl;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import cn.charlie166.web.base.service.impl.BaseServiceImpl;
import cn.charlie166.web.store.constant.CacheConstant;
import cn.charlie166.web.store.constant.CustomException;
import cn.charlie166.web.store.constant.ExceptionCodes;
import cn.charlie166.web.store.dao.PacDao;
import cn.charlie166.web.store.domain.dto.PacDTO;
import cn.charlie166.web.store.domain.po.PacModel;
import cn.charlie166.web.store.service.inter.PacService;
import cn.charlie166.web.store.tools.ClassUtils;
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
@CacheConfig(cacheNames = CacheConstant.PAC)
public class PacServiceImpl extends BaseServiceImpl implements PacService {

	@Autowired
	private PacDao pacDao;

	@CacheEvict(allEntries = true)
	@Override
	public int insertOne(PacModel pac) throws CustomException {
		return this.insertBatch(Arrays.asList(pac));
	}

	@CacheEvict(allEntries = true)
	@Override
	@Transactional
	public int insertBatch(List<PacModel> pacList) throws CustomException {
		if(!CollectionUtils.isEmpty(pacList)) {
			String domainRegex = "^(?=^.{3,255}$)[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+$";
			/**已经保存了的域名**/
			Set<String> domainSet = pacDao.selectAll().stream().filter(one -> one != null && StringUtils.hasContent(one.getDomain()))
				.map(one -> one.getDomain()).distinct().collect(Collectors.toSet());
			LocalDateTime now = LocalDateTime.now();
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
					} else {
						throw CustomException.instance(ExceptionCodes.PAC_DOMAIN_NEED);
					}
					/**默认为当前时间**/
					if(pm.getCreateTime() == null)
						pm.setCreateTime(now);
				}
			}
			return pacDao.batchInsert(pacList);
		} else {
			return 0;
		}
	}

	@Cacheable(key = CacheConstant.PAC_ALL)
	@Override
	public List<PacDTO> all() {
		List<PacModel> all = pacDao.selectAll();
		return all.stream().map(one -> {
			try {
				return this.toDto(one);
			} catch (Exception e) {
				logger.error(String.format("转换数据类型出现异常，忽略ID为%S的数据", one.getId()), e);
				return null;
			}
		}).filter(one -> one != null).collect(Collectors.toList());
	}
	
	@CacheEvict(allEntries = true)
	@Override
	public PacDTO add(PacDTO dto) {
		if(dto == null)
			throw CustomException.instance(ExceptionCodes.COMMON_PARAM_ABSENT);
		if(StringUtils.isNullOrTrimBlank(dto.getDomain()))
			throw CustomException.instance(ExceptionCodes.PAC_DOMAIN_NEED);
		/**默认为有效**/
		if(dto.getFlag() == null)
			dto.setFlag(Boolean.TRUE);
		PacModel model = this.toModel(dto);
		int result = this.insertOne(model);
		if(result > 0){
			return this.toDto(model);
		} else {
			throw CustomException.instance(ExceptionCodes.COMMON_INSERT_FAIL);
		}
	}
	
	@Cacheable(key = CacheConstant.PAC_ONLINE)
	@Override
	public String online() {
		StringBuilder str = new StringBuilder();
		List<PacDTO> all = this.all();
		String lineSeparator = StringUtils.getLineSeparator();
		all.stream().filter(one -> one.getFlag() != null && one.getFlag().booleanValue())
			.forEach(one -> {
				str.append(one.getDomain()).append(lineSeparator);
			});
		byte[] b = Base64.getEncoder().encode(str.toString().getBytes(StandardCharsets.UTF_8));
		return str.length() > 0 ? new String(b, StandardCharsets.UTF_8) : str.toString();
	}
	
	/**
	* @Title: toModel 
	* @Description: 将数据转换为model类型
	* @param dto
	* @return
	 */
	private PacModel toModel(PacDTO dto){
		return ClassUtils.convertType(dto, PacModel.class);
	}
	
	/**
	* @Title: convertToDto 
	* @Description: 转换为DTO数据模型
	* @param pm
	* @return
	 * @throws CustomException 
	 */
	private PacDTO toDto(PacModel pm) throws CustomException{
		return ClassUtils.convertType(pm, PacDTO.class);
	}
}