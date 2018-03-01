requirejs.config(jsConfig);
requirejs(["jquery", "base", "ue"], function($, base) {
	base.showLoading();
	/**在加载完成后执行**/
	$(function(){
		base.doSelfWork(function() {
			/**当前模块请求通用路径**/
			var thisModuleUrl = base.thisUrl + "bookmark/";
			/**书签内容表单**/
			var $form = $("#dataForm");
			/**保存按钮**/
			var $save = $("#submitBtn");
			/** 实例化编辑器 **/
			var ue = UE.getEditor("uecontainer", {
			});
			/**富文本编辑器初始化完成**/
			ue.ready(function() {
				base.closeLoading();
			});
			$form.find(":input").each(function(){
				var _v = $(this).attr("value");
				if(_v){
					$(this).val(_v);
				}
			});
			$save.off().on("click", function(){
				var param = $form.serializeObject();
				param.content = ue.getContent();
				base.post(thisModuleUrl + "json/edit.do", param, function(data){
					if(data.code == code.ok){
						base.alert("修改成功", function(){
							
						});
					} else {
						base.alert(data.msg);
					}
				});
			});
		});
	});
});