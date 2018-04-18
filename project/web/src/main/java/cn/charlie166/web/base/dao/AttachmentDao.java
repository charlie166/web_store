package cn.charlie166.web.base.dao;

import java.util.List;

import cn.charlie166.web.common.domain.annotation.ParamCheck;
import cn.charlie166.web.common.domain.po.Attachment;

/**
* @ClassName: AttachmentDao 
* @Description: 附件信息操作接口
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年4月18日 
*
 */
public interface AttachmentDao {

	/**
	* @Title: insertOne
	* @Description: 保存一条附件信息记录
	* @param atta 附件信息
	* @return 影响行数
	 */
	int insertOne(@ParamCheck Attachment atta);
	
	/**
	* @Title: selectList 
	* @Description: 根据条件查询附件信息列表, 不分页
	* @param condition 查询条件
	* @return
	 */
	List<Attachment> selectList(Attachment condition);
	
	/**
	* @Title: deleteById 
	* @Description: 通过主键删除附件信息
	* @param id 主键ID
	* @return
	 */
	int deleteById(long id);
}