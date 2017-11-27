package cn.charlie166.web.store.plugin.ueditor.define;

/**
 * @description 处理状态接口
 * @author <a href="mailto:charlie166@163.com">李阳</a> 
 * @version 1.0, 2017年11月27日
 * @see     
 * @since   web 1.0
 */
public interface State {

	public boolean isSuccess();
	
	public void putInfo(String name, String val);
	
	public void putInfo(String name, long val);
	
	public String toJSONString();
}