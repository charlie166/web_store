requirejs.config(jsConfig);
requirejs(["jquery", "base", "ue"], function($) {
	/**书签内容表单**/
	var $form = $("#dataForm");
	/**保存按钮**/
	var $save = $("#submitBtn");
	/** 实例化编辑器 **/
	var ue = UE.getEditor("uecontainer", {
	});
	ue.ready(function() {
	});
	$save.off().on("click", function(){
		var param = $form.serializeObject();
//		param.content = ue.getAllHtml();
		param.content = ue.getContent();
		console.debug(param);
		/*$.ajax({
			url: "../json/submit.do",
			method: "POST",
			dataType: "json",
			data: param,
			success: function(data, s){
				console.debug(data);
			}
		});*/
	});
});