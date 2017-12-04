<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>新增书签</title>
		<link href="<c:url value="/css/base.css"/>" type="text/css">
    	<script type="text/javascript" src="<c:url value="/js/lib/jquery-3.2.1.min.js"/>"></script>
		<!-- 配置文件 -->
    	<script type="text/javascript" src="<c:url value="/js/lib/ueditor/ueditor.config.js"/>"></script>
    	<!-- 编辑器源码文件 -->
    	<script type="text/javascript" src="<c:url value="/js/lib/ueditor/ueditor.all.min.js"/>"></script>
    	<!-- 实例化编辑器 -->
	   	<script type="text/javascript">
		   	$(function(){
		       	var ue = UE.getEditor("uecontainer", {
		       	});
		       	ue.ready(function() {
		       	});
		   	});
	   	</script>
	</head>
	<body>
		<div class="txtCenter" style="width: 800px; margin: 10px auto;">
			<!-- 加载编辑器的容器 -->
		    <script id="uecontainer" name="content" type="text/plain" style="width: 600px; height: 500px;">
        		这里写你的初始化内容啊啊啊啊啊啊啊啊啊啊啊啊啊
    		</script>
		</div>
	</body>
</html>
