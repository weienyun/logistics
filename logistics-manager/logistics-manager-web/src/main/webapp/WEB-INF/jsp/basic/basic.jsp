<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="/${pageContext.request.contextPath}">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>数据字典</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
	});
</script>


</head>


<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">数据表</a></li>
			<li><a href="#">基本内容</a></li>
		</ul>
	</div>

	<div class="rightinfo">

		<div class="tools">

			<ul class="toolbar">
				<li><a href="basic/goAddOrUpdate"><span><img
							src="/images/t01.png" /></span>添加</a></li>
				<li><a href="javascript:void()" "><span><img
							src="/images/t02.png" /></span>修改</a></li>
				<li><a href="javascript:void()" ><span><img
							src="/images/t03.png" /></span>删除</a></li>
				<li><a href="javascript:void()"><span><img
							src="/images/t04.png" /></span>统计</a></li>
			</ul>


			<ul class="toolbar1">
				<li><span><img src="/images/t05.png" /></span>设置</li>
			</ul>

		</div>


		<table class="tablelist">
			<thead>
				<tr>
					<th><input name="" type="checkbox" value="" checked="checked" /></th>
					<th>编号<i class="sort"><img src="/images/px.gif" /></i></th>
					<th>基础数据</th>
					<th>父节点</th>
					<th>描述信息</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list }" var="basic">
					<!-- 显示父类数据  并将name改为红色 -->

					<c:if test="${empty basic.parentId }">
						<tr>
							<td><input name="" type="checkbox" value="" /></td>
							<td>${basic.baseId }</td>
							<td style="color: red;font-weight: bold;">${basic.baseName }</td>
							<td>${basic.parentId }</td>
							<td>${basic.baseDesc }</td>
							<td><a href="/basic/goAddOrUpdate?baseId=${basic.baseId }"
								class="tablelink">修改</a> <a href="javascript:void(0)"
								onclick="deleteBasic(${basic.baseId})" class="tablelink"> 删除</a></td>
						</tr>
					</c:if>
					<!-- 显示相对父类下的子类数据  再次循环将small.parentId eq basic.baseId选出-->
					<c:forEach items="${list }" var="small">
						<c:if test="${small.parentId eq basic.baseId }">
							<tr>
								<td><input name="" type="checkbox" value="" /></td>
								<td style="padding-left: 20px;">${small.baseId }</td>
								<td style="padding-left: 20px;">${small.baseName }</td>
								<td>${small.parentId }</td>
								<td>${small.baseDesc }</td>
								<td><a href="/basic/goAddOrUpdate?baseId=${small.baseId }"
									class="tablelink">修改</a> <a href="javascript:void(0)"
									onclick="deleteBasic(${small.baseId})" class="tablelink"> 删除</a></td>
							</tr>
						</c:if>
					</c:forEach>
				</c:forEach>
			</tbody>
		</table>


		<div class="pagin">
			<form id="pager" action="/user/queryPage">
				<input type="hidden" id="pageSize" name="pageSize"
					value="${pageModel.pageSize }"> <input type="hidden"
					id="pageNum" name="pageNum" value="${pageModel.pageNum }">
			</form>
			<jsp:include page="/pageBar.jsp"></jsp:include>
		</div>

	</div>
	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
		function deleteBasic(baseId){
			if(window.confirm("确认删除这个用户！")){
				location.href="/basic/deleteBasic?baseId="+baseId;
			}
		}
	
	</script>
	<div style="display: none">
		<script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540'
			language='JavaScript' charset='gb2312'></script>
	</div>