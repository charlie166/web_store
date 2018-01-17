$(function(){
	/**书签内容表单**/
	var $form = $("#dataForm");
	/**保存按钮**/
	var $save = $("#submitBtn");
	$save.off().on("click", function(){
		var param = $form.serializeObject();
		$.ajax({
			url: "../json/submit.do",
			method: "POST",
			dataType: "json",
			data: param,
			success: function(data, s){
				console.debug(data);
			}
		});
	});
});