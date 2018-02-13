package cn.charlie166.web.store.domain.dto;


/**
* @ClassName: BookmarkDTO 
* @Description: 书签传输对象
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年2月13日 
*
 */
public class BookmarkDTO extends BaseDTO {

	/**书签标题**/
	private String title;
	/**引用地址**/
	private String link;
	/**注释说明**/
	private String commentary;
	/**书签内容**/
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
	public String getCommentary() {
		return commentary;
	}
	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}