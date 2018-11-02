package cn.charlie166.web.tumblr.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.charlie166.web.common.domain.dto.PageDTO;
import cn.charlie166.web.store.constant.CustomException;
import cn.charlie166.web.store.constant.ExceptionCodes;
import cn.charlie166.web.store.tools.ClassUtils;
import cn.charlie166.web.store.tools.StringUtils;
import cn.charlie166.web.tumblr.dao.TumblrDao;
import cn.charlie166.web.tumblr.domain.dto.VideoDTO;
import cn.charlie166.web.tumblr.domain.po.Video;
import cn.charlie166.web.tumblr.service.TumblrService;

/**
* @ClassName: TumblrServiceImpl 
* @Description: 汤伯乐服务实现类
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年11月2日 
*
 */
@Service
public class TumblrServiceImpl implements TumblrService {

	@Autowired
	private TumblrDao dao;
	
	@Override
	public VideoDTO detail(String id) throws CustomException {
		if(StringUtils.isInteger(id)){
			Video v = dao.seletVideoById(id);
			if(v != null){
				VideoDTO dto = ClassUtils.convertType(v, VideoDTO.class);
				if(dto != null)
					return dto;
			}
		}
		throw CustomException.instance(ExceptionCodes.COMMON_DATA_ABSENT);
	}

	@Override
	public PageDTO<VideoDTO> page(PageDTO<VideoDTO> page) {
		Map<String, Object> condition = page.getCondition();
		Video v = ClassUtils.convertType(condition, Video.class);
		int total = dao.selectVideoCount(v);
		if(total > 0){
			page.setTotal(total);
			List<Video> list = dao.selectVideoListLimit(v, page.getStart(), page.getPageSize());
			page.setRecords(ClassUtils.convertTypeOfList(list, VideoDTO.class));
		}
		return page;
	}

}