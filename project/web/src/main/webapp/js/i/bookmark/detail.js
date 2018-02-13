requirejs.config(jsConfig);
requirejs(["jquery", "base"], function($, base) {
	/**在加载完成后执行**/
	$(function(){
		base.doSelfWork(function() {
			/**当前模块请求通用路径**/
			var thisModuleUrl = base.thisUrl + "bookmark/";
		});
	});
});