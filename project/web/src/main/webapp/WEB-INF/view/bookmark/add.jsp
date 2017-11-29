<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>新增书签</title>
		<link href="<c:url value="/css/base.css"/>" type="text/css">
		<!-- 配置文件 -->
    	<script type="text/javascript" src="<c:url value="/js/lib/ueditor/ueditor.config.js"/>"></script>
    	<!-- 编辑器源码文件 -->
    	<script type="text/javascript" src="<c:url value="/js/lib/ueditor/ueditor.all.js"/>"></script>
    	<!-- 实例化编辑器 -->
    	<script type="text/javascript">
        	var ue = UE.getEditor("uecontainer", {
        		
        	});
    	</script>
	</head>
	<body>
		<div class="txtCenter">
			<!-- 加载编辑器的容器 -->
		    <script id="uecontainer" name="content" type="text/plain">
        		这里写你的初始化内容啊啊啊啊啊啊啊啊啊啊啊啊啊
    		</script>
		</div>
	</body>
</html>
