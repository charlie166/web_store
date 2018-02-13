<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>书签详情-${bk.title}</title>
		<link href="<c:url value="/css/base.css"/>" rel="stylesheet">
		<link href="<c:url value="/js/lib/layer/theme/default/layer.css"/>" rel="stylesheet">
	</head>
	<body>
		${bk.content}
	</body>
	<script src="<c:url value="/js/lib/require.js"/>" data-main="<c:url value="/js/i/bookmark/detail"/>"></script>
</html>
