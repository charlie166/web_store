package cn.charlie166.web.store.plugin.ueditor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import cn.charlie166.web.store.plugin.ueditor.define.ActionMap;
import cn.charlie166.web.store.plugin.ueditor.define.AppInfo;
import cn.charlie166.web.store.plugin.ueditor.define.BaseState;
import cn.charlie166.web.store.plugin.ueditor.define.State;
import cn.charlie166.web.store.plugin.ueditor.hunter.FileManager;
import cn.charlie166.web.store.plugin.ueditor.hunter.ImageHunter;
import cn.charlie166.web.store.plugin.ueditor.upload.Uploader;
import cn.charlie166.web.store.tools.StringUtils;

/**
* @ClassName: UeditorActionEntrance 
* @Description: 富文本编辑器UEDITOR请求入口
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2017年12月1日 
*
 */
public class UeditorActionEntrance {

	@Autowired
	private UeditorConfigManager configManager;
	
	/**请求类型**/
	private String actionType;
	/**当前请求**/
	private HttpServletRequest request;
	
	/**
	* @Title: exec 
	* @Description: 执行请求操作
	* @param action 请求类型
	* @param callback 回调方法名
	* @param param 请求参数
	* @return
	 */
	public String exec(String action, String callback, HttpServletRequest req){
		if(!StringUtils.hasContent(action)){
			return new BaseState(false, AppInfo.PARAM_ERROR).toJSONString();
		} else {
			this.actionType = action;
			this.request = req;
			if (StringUtils.hasContent(callback)) {
				if (!validCallbackName(callback)) {
					return new BaseState(false, AppInfo.ILLEGAL).toJSONString();
				}
				return callback + "(" + this.invoke() + ");";
			} else {
				return this.invoke();
			}
		}
	}
	
	/**
	* @Title: invoke 
	* @Description: 具体请求逻辑处理
	* @return
	 */
	private String invoke(){
		if (StringUtils.isNullOrTrimBlank(actionType) || !ActionMap.mapping.containsKey(actionType)) {
			return new BaseState(false, AppInfo.INVALID_ACTION).toJSONString();
		}
		if (this.configManager == null || UeditorConfigManager.CONFIG_MAP.isEmpty()) {
			return new BaseState(false, AppInfo.CONFIG_ERROR).toJSONString();
		}
		State state = null;
		int actionCode = ActionMap.getType(this.actionType);
		Map<String, Object> conf = null;
		switch (actionCode) {
			case ActionMap.CONFIG:
				return this.configManager.getSettingJsonString();
			case ActionMap.UPLOAD_IMAGE:
			case ActionMap.UPLOAD_SCRAWL:
			case ActionMap.UPLOAD_VIDEO:
			case ActionMap.UPLOAD_FILE:
				conf = this.configManager.getConfig(actionCode);
				state = new Uploader(request, conf).doExec();
				break;
			case ActionMap.CATCH_IMAGE:
				if(this.request == null){
					state = new BaseState(false, AppInfo.PARAM_ERROR);
				} else {
					conf = configManager.getConfig(actionCode);
					String[] list = this.request.getParameterValues((String)conf.get("fieldName"));;
					state = new ImageHunter(conf).capture(list);
				}
				break;
			case ActionMap.LIST_IMAGE:
			case ActionMap.LIST_FILE:
				conf = configManager.getConfig(actionCode);
				int start = this.getStartIndex();
				state = new FileManager(conf).listFile(start);
				break;
		}
		return state.toJSONString();
	}
	
	/**
	* @Title: validCallbackName 
	* @Description: callback参数验证
	* @param name 校验名称
	* @return
	 */
	private boolean validCallbackName (String name) {
		if (name.matches("^[a-zA-Z_]+[\\w0-9_]*$")) {
			return true;
		}
		return false;
	}
	
	/**
	* @Title: getStartIndex 
	* @Description:
	* @return
	 */
	private int getStartIndex() {
		try {
			if(this.request != null){
				String parameter = this.request.getParameter("start");
				return Integer.parseInt(parameter);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}