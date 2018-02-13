<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>内部错误</title>
</head>
<body>
	<div style="width: 260px; margin: 10px auto;">
		<h2>哦！你的请求执行出现异常了~</h2>
		<div style="text-align: center;">
			${msg}
		</div>
	</div>
</body>
</html>
