<%@page import="com.gxuwz.medical.domain.menu.Menu"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/base.jsp"%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
<base href="<%=basePath%>">

<title>菜单列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery-1.4.4.min.js"></script>
<script src="js/admin.js"></script>
</head>
<body>
	<form method="post" action="" id="listform">
		<div class="panel admin-panel">
			<div class="panel-head">
				<strong class="icon-reorder">菜单信息列表</strong>
			</div>
			<div class="padding border-bottom">
				<ul class="search" style="padding-left:10px;">
					<li><a class="button border-main icon-plus-square-o"
						href="<%=path%>/system/MenuServlet?m=input"> 添加菜单</a></li>
					<li>搜索：</li>
					<li><input type="text" placeholder="请输入搜索关键字" name="keywords"
						class="input"
						style="width:250px; line-height:17px;display:inline-block" /> <a
						href="javascript:void(0)" class="button border-main icon-search"
						onclick="changesearch()"> 搜索</a></li>
				</ul>
			</div>
			<table class="table table-hover text-center">
				<tr>	<th width="50">序号</th>
					<th width="50" style="text-align:center;">&nbsp;</th>
					<th>菜单编号</th>
					<th>上一级菜</th>
					<th>菜单名称</th>
				    <th>菜单级别</th>
					<th>访问URL</th>
					<th>操作</th>
				</tr>
				<%
					String pageNo = request.getParameter("pageNo");
					int p = pageNo == null ? 1 : Integer.parseInt(pageNo);
					MenuDao menuDao = new MenuDao();
					String sql = "select * from t_menu where 1=1 ";
					Object[] params = {};
					Page pageBean = menuDao.queryOjectsByPage(sql, params, p,
							Constant.ROW);
				%>
				<%
					for (int i = 0; i < pageBean.getDatas().size(); i++) {

						Menu model = (Menu) pageBean.getDatas().get(i);
				%>
				<tr>
				<td><%=(i+1) %></td>
					<td><input type="checkbox" name="ids"
						value="<%=model.getMenuid()%>" /></td>
					<td align="left"><%=model.getMenuid()%></td>
					<td><%=model.getParent()!=null?model.getParent().getMenuname():"-" %></td>
					<td><%=model.getMenuname()%></td>
					 <td><%=model.getLevel() %>级</td>
					<td align="left"><%=model.getUrl() %></td>
					<td><div class="button-group">
							<a class="button border-main"
								href="<%=path%>/system/MenuServlet?m=get&id=<%=model.getMenuid()%>"><span
								class="icon-edit"></span> 修改</a> <a class="button border-red"
								href="<%=path%>/system/MenuServlet?m=del&id=<%=model.getMenuid()%>"><span
								class="icon-trash-o"></span> 删除</a>
						</div></td>
				</tr>
				<%
					}
				%>
			</table>
			<div class="pagelist">
				<%
					if (pageBean.hasPre()) {
				%>
				<a
					href="<%=path%>/system/MenuServlet?m=list&pageNo=<%=pageBean.prePage()%>">上一页</a>
				<%
					} else {
				%>
				<a href="javascript:void(0)">上一页</a>
				<%
					}
				%>
				<%
					List<Integer> linkNums = pageBean.linkLumbers();
					for (int num : linkNums) {
					if (pageBean.isCurrent(num)) {
				%>
				<span class="current"><%=num%></span>
				<%
					} else {
				%>
				<a href="<%=path%>/system/MenuServlet?m=list&pageNo=<%=num%>"><%=num%></a>
				<%
					}
					}
				%>
				<%
					if (pageBean.hasNext()) {
				%>
				<a
					href="<%=path%>/system/MenuServlet?m=list&pageNo=<%=pageBean.lastPage()%>">下一页</a>
				<%
					} else {
				%>
				<a href="javascript:void(0)">下一页</a>
				<%
					}
				%>
				<a
					href="<%=path%>/system/MenuServlet?m=list&pageNo=<%=pageBean.getPages()%>">尾页</a>
			</div>
		</div>
	</form>

</body>
</html>
