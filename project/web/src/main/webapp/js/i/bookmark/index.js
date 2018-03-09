requirejs.config(jsConfig);
requirejs(["jquery", "base"], function($, base) {
	base.showLoading();
	/**在加载完成后执行**/
	$(function(){
		/**绘制列表数据 $div: 列表展示区域对象; rows: 列表数据**/
		function drawListContent($div, rows){
			$div.empty();
			if($.isArray(rows)){
				$.each(rows, function(i, item){
					var $tr = $("<tr>").addClass("listItem").data("item", item);
					$tr.append($("<td>").addClass("").html(item.title));
					var $a = $("<a>").attr("href", "javascript: void(0);").html(item.link);
					base.checkLink(item.link, function(b){
						if(b){
							$a.attr("target", "_blank");
						} else {
							$a.off().on("click", function(){
								base.msg("链接不可用");
								return false;
							}).attr("title", "链接不可用");
						}
					});
					$tr.append($("<td>").addClass("").append($a));
					$tr.append($("<td>").addClass("").html(item.createTime));
					$div.append($tr);
				});
			}
		}
		base.doSelfWork(function() {
			/**当前模块请求通用路径**/
			var thisModuleUrl = base.thisUrl + "bookmark/";
			/**分页查询地址**/
			var page_url = thisModuleUrl + "json/pager.do";
			/**列表展示区域对象**/
			var $list = $(".pageList");
			base.post(page_url, {}, function(data){
				drawListContent($list.find("tbody"), data.records);
				$.get("https://ksmx.me/letsencrypt-ssl-https/", function(data){
					console.debug(data);
				});
			});
		});
	});
});