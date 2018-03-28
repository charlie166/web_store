package cn.charlie166.web.weixin.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cn.charlie166.web.store.constant.CustomException;
import cn.charlie166.web.store.constant.ExceptionCodes;
import cn.charlie166.web.store.tools.HashUtils;
import cn.charlie166.web.store.tools.StringUtils;
import cn.charlie166.web.weixin.domain.dto.WeixinServerValidateDTO;
import cn.charlie166.web.weixin.service.inter.WeixinHomeService;

/**
* @ClassName: HomeServiceImpl 
* @Description: 微信主页业务服务接口实现类
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年3月27日 
*
 */
@Service
public class WeixinHomeServiceImpl extends WeixinBaseServiceImpl implements WeixinHomeService {

	@Override
	public WeixinServerValidateDTO validateServer(WeixinServerValidateDTO dto) {
		if(dto != null){
			if(StringUtils.hasContent(dto.getSignature()) && StringUtils.hasContent(dto.getTimestamp()) &&
				StringUtils.hasContent(dto.getNonce())){
				List<String> list = Arrays.asList(dto.getNonce(), dto.getTimestamp(), token);
				Collections.sort(list);
				String sha1Val = HashUtils.sha1(list.stream().collect(Collectors.joining()));
				if(sha1Val.equals(dto.getSignature())){
					return dto;
				} else {
					throw CustomException.instance(ExceptionCodes.WEIXIN_SERVER_VALID_FAILED);
				}
			}
		}
		throw CustomException.instance(ExceptionCodes.COMMON_PARAM_ABSENT);
	}
	
}