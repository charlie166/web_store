package cn.charlie166.web.store.domain.po;

import cn.charlie166.web.store.domain.annotation.StringCheck;

/**
* @ClassName: Bookmark 
* @Description: 书签实体类.对应数据库
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年11月22日 
*
 */

public class Bookmark extends BaseEntity {

	/**书签标题**/
	@StringCheck(maxLength = 100, maxLengthTip = "标题长度不能超过%s", mustHasContent = true)
	private String title;
	/**引用地址**/
	@StringCheck(maxLength = 255, maxLengthTip = "地址长度不能超过%s")
	private String link;
	/**书签内容**/
	@StringCheck(mustHasContent = true, mustHasContentTip = "内容必须")
	private String content;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}