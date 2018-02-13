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
			$save.off().on("click", function(){
				var param = $form.serializeObject();
//				param.content = ue.getAllHtml();
				param.content = ue.getContent();
				console.debug(param);
				/*$.ajax({
					url: thisModuleUrl + "json/submit.do",
					method: "POST",
					dataType: "json",
					data: param,
					success: function(data, s){
						console.debug(data);
					}
				});*/
				base.post(thisModuleUrl + "json/submit.do", param, function(data){
					console.debug(data);
				});
			});
		});
	});
});