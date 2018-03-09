package cn.charlie166.web.store.domain.dto;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
* @ClassName: PageDTO 
* @Description: 分页数据传输对象
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年3月7日 
*
 */
@JsonInclude(value = Include.NON_NULL)
public class PageDTO<T> {

	/**查询页码，默认第一页**/
	private int current = 1;
	/**每页查询条数，默认20**/
	private int pageSize = 20;
	/**查询偏移量**/
	private int start = 0;
	/**查询条件**/
	private Map<String, Object> condition = new HashMap<String, Object>();
	/**查询结果总数**/
	private int total = 0;
	/**查询结果页数**/
	private int pages = 0;
	/**查询结果列表**/
	private List<T> records;
	
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getStart() {
		this.start = (this.getCurrent() - 1) * 20;
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public Map<String, Object> getCondition() {
		return condition;
	}
	public void setCondition(Map<String, Object> condition) {
		this.condition = condition;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPages() {
		if(this.getTotal() > 0){
			int _p = this.getTotal() / this.getPageSize();
			this.pages = this.getTotal() % this.getPageSize() == 0 ? _p : _p + 1;
		}
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public List<T> getRecords() {
		return records == null ? Collections.emptyList() : this.records;
	}
	public void setRecords(List<T> records) {
		this.records = records;
	}
}