<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="/">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">表单</a></li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>基本信息</span>
		</div>
		<!-- 如果采用GET方式，那么action url中参数都会被丢弃，提交时候只会把form中的数据拼接在url向服务器提交；但是POST的方式则不会这样，它会按照action指定的url进行提交数据，包含url后面跟着的参数和参数值。 -->
		<form action="user/addOrUpdateUser?userId=${user.userId}" method="post">
			<ul class="forminfo">
				<li><label>用户名称</label><input name="user.userName" type="text"
					class="dfinput" value="${user.userName}" /><i>不能超过30个字符</i></li>
				<li><label>真实姓名</label><input name="user.realName" type="text"
					class="dfinput" value="${user.realName}" /><i>多个关键字用,隔开</i></li>
				<!-- 添加用户显示密码，修改查看不显示 -->
				<c:if test="${empty user.password}">
					<li><label>用户密码${dcks}</label><input name="user.password"
						type="text" class="dfinput" value="${user.password}" /><i>多个关键字用,隔开</i></li>
				</c:if>
				<li><label>邮箱地址</label><cite><input name="user.email"
						type="text" class="dfinput" value="${user.email}" />
						<li><label>联系电话</label><input name="user.phone" type="text"
							class="dfinput" value="${user.phone}" /></li>

						<li><label>用户角色</label> 
						<c:forEach items="${list}" var="role">
								<!-- 
				                        每循环一次 判断取出来的角色编号在不在用户具有的角色集合中
				                        在就设置flag=true
				                        不在就设置flag=false
				                     -->
								<c:set var="flag" value="false"></c:set>
								<c:forEach items="${roles}" var="r">
									<c:if test="${r.roleId eq role.roleId}">
									<c:set var="flag" value="true"></c:set>
									</c:if>
								</c:forEach>
								<input name="rolesId" type="checkbox" ${flag eq true?'checked':'' }
									value="${role.roleId}">${role.roleName}
									<c:set var="flag" value="false"></c:set> 
	    				</c:forEach></li> <c:choose>
							<c:when test="${select eq 1}">
								<li><label>&nbsp;</label><a href="user/query"><input
										name="" type="button" class="btn" value="返回" /></a></li>
							</c:when>
							<c:otherwise>
								<li><label>&nbsp;</label><input name="" type="submit"
									class="btn" value="确认保存" /></li>
							</c:otherwise>
						</c:choose>
			</ul>
		</form>

	</div>
	<div style="display: none">
		<script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540'
			language='JavaScript' charset='gb2312'></script>
	</div>
</body>
</html>
