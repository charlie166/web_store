package cn.charlie166.web.store.domain.dto;

/**
* @ClassName: BasePage 
* @Description: 基本分页查询条件
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年12月6日 
*
 */
public abstract class BasePage {

	public static final int DEFAULT_PAGE = 1;
	public static final int DEFAULT_SIZE = 20;
	
	/**当前查询页码**/
	private int page = DEFAULT_PAGE;
	/**分页条数**/
	private int size = DEFAULT_SIZE;
	/**查询偏移量**/
	private int offset = 0;
	
	public int getPage() {
		if(this.page <= 0)
			this.page = DEFAULT_PAGE;
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		if(this.size <= 0)
			this.size = DEFAULT_SIZE;
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getOffset() {
		this.offset = (this.getPage() - 1) * this.getSize();
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	
}