<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>书签</title>
		<link href="<c:url value="/css/base.css"/>" rel="stylesheet">
		<link href="<c:url value="/js/lib/layer/theme/default/layer.css"/>" rel="stylesheet">
	</head>
	<body>
		<div class="centerContent">
			<!-- 列表 -->
			<div class="pageList">
				<table>
					<thead>
						<tr>
							<td>
								标题
							</td>
							<td>
								引用链接
							</td>
							<td>
								添加时间
							</td>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
			<!-- 分页页码 -->
			<div class="pageInfo">
			</div>
		</div>
	</body>
	<script src="<c:url value="/js/lib/require.js"/>" data-main="<c:url value="/js/i/bookmark/index"/>"></script>
</html>
