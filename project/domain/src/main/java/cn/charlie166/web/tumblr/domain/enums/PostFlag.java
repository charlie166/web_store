package cn.charlie166.web.tumblr.domain.enums;

/**
* @ClassName: PostFrom 
* @Description: 来源位置
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年11月2日 
*
 */
public enum PostFlag {
	
	LIKES("喜欢的"), DASHBOARD("主页");
	
	private String description;
	
	private PostFlag(String s) {
		this.description = s;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}