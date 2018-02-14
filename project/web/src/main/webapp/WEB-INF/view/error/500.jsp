<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>内部错误</title>
	<link href="<c:url value="/css/base.css"/>" rel="stylesheet">
</head>
<body>
	<div class="centerContent">
		<h2 class="txtCenter">哦！你的请求执行出现异常了~</h2>
		<div>
			${error500}
		</div>
	</div>
</body>
</html>
