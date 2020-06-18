<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="p" uri="com.gxuwz.permissions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>慢性病报销系统V1.0</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery-1.4.4.min.js"></script>
</head>
<body style="background-color:#f2f9fd;">
	<div class="header bg-main">
		<div class="logo margin-big-left fadein-top">
			<h1>
				<img src="../images/logo.jpg" class="radius-circle rotate-hover"
					height="50" alt="" />慢性病报销系统
			</h1>
		</div>
		<div class="head-l">
			<a href="" target="_blank" style="color:#FFF"><span
				class="icon-user"></span> 欢迎 xx</a>&nbsp;&nbsp;<a
				class="button button-little bg-green" href="" target="_blank"><span
				class="icon-home"></span> 首页</a> &nbsp;&nbsp;<a
				class="button button-little bg-red" href="LogoutServlet"><span
				class="icon-power-off"></span> 退出登录</a>
		</div>
	</div>
	<div class="leftnav">
		<div class="leftnav-title">
			<strong><span class="icon-list"></span>菜单列表</strong>
		</div>

         <p:HasAnyPermission menu="F0102,F0101,F0103,F0104,F0105,F0107,F0106,F0108">
		<h2>
			<span class="icon-user"></span>系统设置
		</h2>
		<ul>
				<p:HasAnyPermission right="F0102">
					<li><a href="<%=path%>/system/RoleServlet?m=list"
						target="right"><span class="icon-caret-right"></span>角色管理</a></li>
				</p:HasAnyPermission>
				<p:HasAnyPermission right="F0101">
					<li><a href="<%=path%>/system/UserServlet?m=list"
						target="right"><span class="icon-caret-right"></span>用户管理</a></li>
				</p:HasAnyPermission>
				<p:HasAnyPermission right="F0103">
					<li><a href="<%=path%>/system/MenuServlet?m=list"
						target="right"><span class="icon-caret-right"></span>权限管理</a></li>
				</p:HasAnyPermission>
				<p:HasAnyPermission right="F0104">
					<li><a href="<%=path%>/system/AreaServlet?m=list"
						target="right"><span class="icon-caret-right"></span>行政区域管理</a></li>
				</p:HasAnyPermission>
				<p:HasAnyPermission right="F0105">
					<li><a href="<%=path%>/system/InstitutionServlet?m=list"
						target="right"><span class="icon-caret-right"></span>农合机构管理</a></li>
				</p:HasAnyPermission>
				<p:HasAnyPermission right="F0107">
					<li><a href="<%=path%>/system/ChronicdisServlet?m=list"
						target="right"><span class="icon-caret-right"></span>慢病分类管理</a></li>
				</p:HasAnyPermission>
				<p:HasAnyPermission right="F0106">
					<li><a href="<%=path%>/system/MedicalServlet?m=list"
						target="right"><span class="icon-caret-right"></span>医疗机构管理</a></li>
				</p:HasAnyPermission>
				<p:HasAnyPermission right="F0108">
					<li><a href="<%=path%>/system/PolicyServlet?m=list" target="right"><span
							class="icon-caret-right"></span>慢病政策设置</a></li>
				</p:HasAnyPermission>
			</ul>
		</p:HasAnyPermission>
		<p:HasAnyPermission menu="F0201,F0202,F0203,F0204,F0205">
		<h2>
			<span class="icon-user"></span>业务办理
		</h2>
		<ul style="display:block">
				<p:HasAnyPermission right="F0201">
					<li><a href="<%=path%>/system/FamilyServlet?m=list"
						target="right"><span class="icon-caret-right"></span>家庭档案管理</a></li>
				</p:HasAnyPermission>
				<p:HasAnyPermission right="F0202">
					<li><a href="<%=path%>/system/PeasantServlet?m=list"
						target="right"><span class="icon-caret-right"></span>农民档案管理</a></li>
				</p:HasAnyPermission>
				<p:HasAnyPermission right="F0203">
					<li><a href="<%=path%>/system/PayServlet?m=list"
						target="right"><span class="icon-caret-right"></span>参合缴费登记</a></li>
				</p:HasAnyPermission>
				<p:HasAnyPermission right="F0204">
					<li><a href="<%=path%>/system/CardServlet?m=list"
						target="right"><span class="icon-caret-right"></span>慢病证管理</a></li>
				</p:HasAnyPermission>
				<p:HasAnyPermission right="F0205">
					<li><a href="<%=path%>/system/ApplyServlet?m=input"
						target="right"><span class="icon-caret-right"></span>慢病报销</a></li>
				</p:HasAnyPermission>

			</ul>
		</p:HasAnyPermission>
		<p:HasAnyPermission menu="F0301">
		<h2>
			<span class="icon-user"></span>统计报表
		</h2>
		<ul>
			<li><a href="<%=path%>/system/ApplyServlet?m=show" target="right"><span
					class="icon-caret-right"></span>慢性病报销情况</a></li>
		</ul>
		</p:HasAnyPermission>

	</div>
	<script type="text/javascript">
		$(function() {
			$(".leftnav h2").click(function() {
				$(this).next().slideToggle(200);
				$(this).toggleClass("on");
			})
			$(".leftnav ul li a").click(function() {
				$("#a_leader_txt").text($(this).text());
				$(".leftnav ul li a").removeClass("on");
				$(this).addClass("on");
			})
		});
	</script>
	<ul class="bread">
		<li><a href="{:U('Index/info')}" target="right" class="icon-home">
				首页</a></li>
		<li><a href="##" id="a_leader_txt">欢迎界面</a></li>
		<li><b>当前语言：</b><span style="color:red;">中文</php></span>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
	</ul>
	<div class="admin">
		<iframe scrolling="auto" rameborder="0" src="welcome.html"
			name="right" width="100%" height="100%"></iframe>
	</div>
</body>
</html>
