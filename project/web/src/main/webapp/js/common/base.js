/**
 * 基础js
 * 
 * **/
/**扩展字符串的startsWith方法**/
if (!String.prototype.startsWith) {
	(function() {
		'use strict'; // needed to support `apply`/`call` with `undefined`/`null`
		var defineProperty = (function() {
			// IE 8 only supports `Object.defineProperty` on DOM elements
			try {
				var object = {};
				var $defineProperty = Object.defineProperty;
				var result = $defineProperty(object, object, object) && $defineProperty;
			} catch(error) {}
			return result;
		}());
		var toString = {}.toString;
		var startsWith = function(search) {
			if (this == null) {
				throw TypeError();
			}
			var string = String(this);
			if (search && toString.call(search) == '[object RegExp]') {
				throw TypeError();
			}
			var stringLength = string.length;
			var searchString = String(search);
			var searchLength = searchString.length;
			var position = arguments.length > 1 ? arguments[1] : undefined;
			// `ToInteger`
			var pos = position ? Number(position) : 0;
			if (pos != pos) { // better `isNaN`
				pos = 0;
			}
			var start = Math.min(Math.max(pos, 0), stringLength);
			// Avoid the `indexOf` call if no match is possible
			if (searchLength + start > stringLength) {
				return false;
			}
			var index = -1;
			while (++index < searchLength) {
				if (string.charCodeAt(start + index) != searchString.charCodeAt(index)) {
					return false;
				}
			}
			return true;
		};
		if (defineProperty) {
			defineProperty(String.prototype, 'startsWith', {
				'value': startsWith,
				'configurable': true,
				'writable': true
			});
	    } else {
	    	String.prototype.startsWith = startsWith;
	    }
	}());
}
/**扩展字符串的endsWith方法***/
if (!String.prototype.endsWith) {
	(function() {
		'use strict'; // needed to support `apply`/`call` with `undefined`/`null`
		var defineProperty = (function() {
			// IE 8 only supports `Object.defineProperty` on DOM elements
			try {
				var object = {};
				var $defineProperty = Object.defineProperty;
				var result = $defineProperty(object, object, object) && $defineProperty;
			} catch(error) {}
			return result;
		}());
		var toString = {}.toString;
		var endsWith = function(search) {
			if (this == null) {
				throw TypeError();
			}
			var string = String(this);
			if (search && toString.call(search) == '[object RegExp]') {
				throw TypeError();
			}
			var stringLength = string.length;
			var searchString = String(search);
			var searchLength = searchString.length;
			var pos = stringLength;
			if (arguments.length > 1) {
				var position = arguments[1];
				if (position !== undefined) {
					// `ToInteger`
					pos = position ? Number(position) : 0;
					if (pos != pos) { // better `isNaN`
						pos = 0;
					}
				}
			}
			var end = Math.min(Math.max(pos, 0), stringLength);
			var start = end - searchLength;
			if (start < 0) {
				return false;
			}
			var index = -1;
			while (++index < searchLength) {
				if (string.charCodeAt(start + index) != searchString.charCodeAt(index)) {
					return false;
				}
			}
			return true;
		};
		if (defineProperty) {
			defineProperty(String.prototype, 'endsWith', {
				'value': endsWith,
				'configurable': true,
				'writable': true
			});
		} else {
			String.prototype.endsWith = endsWith;
		}
	}());
}
/**扩展时间的格式化方法**/
if(!Date.prototype.format){
	/***对Date的扩展，将 Date 转化为指定格式的String
	* 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
	* 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
	* 例子： 
	* (new Date()).format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
	* (new Date()).format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
	* **/ 
	Date.prototype.format = function (fmt) {
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
	}
}
/**
 * 一些基本的常用的功能方法封装
 */
define(["jquery", "codes", "layer"], function ($, codes){
	/**当前加载中动画索引**/
	var currentLoadingIndex;
	/**发送请求方法 url: 请求地址; param: 请求参数; succCall: 成功回调方法**/
	function sendRequest(url, param, succCall){
		/**显示加载中动画**/
		var postLoadingIndex = layer.load(1);
		$.ajax({
			url: url,
			method: "POST",
			dataType: "json",
			data: param,
			headers: {},
			success: function(data, textStatus, jqXHR){
				/**请求完成，关闭加载动画**/
				if(postLoadingIndex)
					layer.close(postLoadingIndex);
				if(data){
					if(data.code == codes.ok){
						if($.isFunction(succCall)){
							succCall.call(this, data.content);
						} else {
							layer.msg("回调方法参数错误");
						}
					} else {
						layer.alert(data.msg ? data.msg : "操作失败");
					}
				} else {
					layer.alert("没有获取到返回数据");
				}
			},
			error: function(jqXHR, textStatus, errorThrown){
				/**请求完成，关闭加载动画**/
				if(postLoadingIndex)
					layer.close(postLoadingIndex);
			}
		});
	}
	/**分页查询数据  page: 查询页数; pageSize: 查询每页条数; url: 分页查询地址; param: 查询条件; succCall: 查询成功回调**/
	function pageQuery(page, pageSize, url, param, succCall){
		
	}
	return {
		/**   当前工程的访问路径，含上下文. 返回如: http://xxx.com:123/web/   **/
		thisUrl: base_context_url,
		/**公共操作执行完后调用**/
		doSelfWork: function (func){/**在公共操作执行完成后执行自定义操作. func: 自定义要作的操作**/
			$("body").show();
			if($.isFunction(func)){
				func.call(this);
			}
		},
		closeAllLoading: function(){/**关闭所有加载中动画**/
			layer.closeAll("loading");
		},
		closeLoading: function(){/**关闭当前加载中动画**/
			if(currentLoadingIndex){
				layer.close(currentLoadingIndex);
			}
		},
		showLoading: function(){/**显示加载中动画**/
			currentLoadingIndex = layer.load(1);
		},
		msg: function(msg){/**消息提示**/
			if(msg)
				layer.msg(msg);
		},
		alert: function(msg, callbak){/**消息弹窗. msg: 消息提示内容; callbak: 弹窗关闭回调**/
			if(msg){
				if($.isFunction(callbak)){
					layer.alert(msg, function(thisIndex){
						layer.close(thisIndex);
					});
				} else {
					layer.alert(msg);  
				}
			}
		},
		/**校验链接是否有效，异步校验	  url: 校验地址; callbak: 校验结果回调.**/
		checkLink: function(url, callbak){
			if($.isFunction(callbak)){
				if(url){
					$.get(url, {})
						.done(function(data) {
							console.debug(data);
					});
					/*$.get({
						url: url,
						processData: false,
						dataType: "text html",
						statusCode: {
							200: function(){
								console.debug("200-url:" + url);
								callbak.call(this, true);
							},
							404: function(){
								console.debug("404-url:" + url);
								callbak.call(this, true);
							}
						},
						error: function(jq, ts, e){
							console.debug("b-url:" + url);
							console.debug("status :" + jq.status);
							console.debug(e);
							callbak.call(this, false);
						},
						complete: function(jq, ts){
							console.debug("d-url:" + url);
							console.debug("status :" + jq.status);
							console.debug(ts);
							callbak.call(this, false);
						}
					});*/
				} else {
					console.debug("a-url:" + url);
					callbak.call(this, false);
				}
			}
		},
		post: function(url, param, succCall){/**post请求**/
			if(!url)
				return false;
			var p = {};
			if($.isFunction(param)){
				succCall = param;
			} else {
				p = $.extend({}, param);
			}
			/**发送请求**/
			sendRequest(url, p, succCall);
		}
	}
});