<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>新增书签</title>
		<link href="<c:url value="/css/base.css"/>" rel="stylesheet">
		<link href="<c:url value="/js/lib/layer/theme/default/layer.css"/>" rel="stylesheet">
	</head>
	<body>
		<div class="centerContent">
			<form id="dataForm" class="contentForm">
				<table>
					<tbody>
						<tr>
							<td class="tenth">
								标题
							</td>
							<td>
								<input type="text" name="title">
							</td>
						</tr>
						<tr>
							<td class="tenth">
								引用地址
							</td>
							<td>
								<input type="text" name="link">
							</td>
						</tr>
						<tr>
							<td class="tenth">
								备注说明
							</td>
							<td>
								<input type="text" name="commentary">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="txtCenter">
									内容
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="" style="">
									<!-- 加载编辑器的容器 -->
								    <script id="uecontainer" type="text/plain">
						    		</script>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="txtCenter">
									<button id="submitBtn" type="button">提交</button>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</body>
	<script src="<c:url value="/js/lib/require.js"/>" data-main="<c:url value="/js/i/bookmark/add"/>"></script>
</html>
