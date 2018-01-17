<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>新增书签</title>
		<link href="<c:url value="/css/base.css"/>" rel="stylesheet">
    	<script type="text/javascript" src="<c:url value="/js/lib/jquery-3.2.1.min.js"/>"></script>
    	<script type="text/javascript" src="<c:url value="/js/common/base.js"/>"></script>
		<!-- 配置文件 -->
    	<script type="text/javascript" src="<c:url value="/js/lib/ueditor/ueditor.config.js"/>"></script>
    	<!-- 编辑器源码文件 -->
    	<script type="text/javascript" src="<c:url value="/js/lib/ueditor/ueditor.all.min.js"/>"></script>
    	<script type="text/javascript" src="<c:url value="/js/i/bookmark/add.js"/>"></script>
	</head>
	<body>
		<div class="centerContent">
			<form id="dataForm">
				<table>
					<tbody>
						<tr>
							<td>
								标题
							</td>
							<td>
								<input type="text" name="title">
							</td>
						</tr>
						<tr>
							<td>
								引用地址
							</td>
							<td>
								<input type="text" name="link">
							</td>
						</tr>
						<tr>
							<td>
								备注说明
							</td>
							<td>
								<input type="text" name="commentary">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								内容
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="txtCenter" style="width: 98%; margin: 10px auto;">
									<!-- 加载编辑器的容器 -->
								    <script id="uecontainer" type="text/plain" style="width: 600px; height: 500px;">
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
</html>
