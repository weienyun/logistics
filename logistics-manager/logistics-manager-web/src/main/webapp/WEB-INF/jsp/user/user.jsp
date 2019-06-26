<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="/${pageContext.request.contextPath}">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
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
				<li><a href="user/goAddOrUpdate"><span><img
							src="/images/t01.png" /></span>添加</a></li>
				<li><a href="javascript:void()" onclick="updateUser()"><span><img
							src="/images/t02.png" /></span>修改</a></li>
				<li><a href="javascript:void()" onclick="deleteManyUser()"><span><img 
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
					<th>编号</th>
					<th>用户名称</th>
					<th>用户邮箱</th>
					<th>联系电话</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="userId">
				<c:forEach items="${pageModel.list}" var="user">
					<tr>
						<td><input name="" type="checkbox" value="${user.userId}" /></td>
						<td>${user.userId}</td>
						<td>${user.userName}</td>
						<td>${user.email}</td>
						<td>${user.phone}</td>
						<td><a
							href="user/goAddOrUpdate?select=1&userId=${user.userId}"
							class="tablelink">查看</a> <a href="javascript:void()"
							onclick="deleteUser(${user.userId})" class="tablelink"> 删除</a></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>


		<div class="pagin">
			<form id="pager" action="/user/queryPage">
				<input type="hidden" id="pageSize" name="pageSize" value="${pageModel.pageSize }">
				<input type="hidden" id="pageNum" name="pageNum" value="${pageModel.pageNum }">
			</form>
			<jsp:include page="/pageBar.jsp"></jsp:include>
		</div>

	</div>
	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
		function deleteUser(userId){
			if(window.confirm("确认删除这个用户！")){
				location.href="/user/deleteUser?userId="+userId;
			}
		}
		function updateUser(){
			var arr = new Array();
			$("#userId :checkbox[checked]").each(function(i){
					arr[i] = $(this).val();
				});
			if (arr.length==1) {
				location.href="/user/goAddOrUpdate?userId="+arr[0];
			}else {
				alert("需要选择一个要修改的用户");
			}
		}
		function deleteManyUser(){
			var arr = new Array();
			$("#userId :checkbox[checked]").each(function(i){
				arr[i]=$(this).val();
			})
			if(arr.length>0){
				location.href="/user/deleteUser?userId="+arr;
			}else{
				alert("请选中至少一个修改的！");
			}
		}
	</script>
	<div style="display: none">
		<script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540'
			language='JavaScript' charset='gb2312'></script>
	</div>