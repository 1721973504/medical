<%@page import="com.gxuwz.medical.domain.medical.Medical"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/base.jsp"%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
<base href="<%=basePath%>">

<title>医疗机构列表</title>
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
				<strong class="icon-reorder">医疗机构信息列表</strong>
			</div>
			<div class="padding border-bottom">
				<ul class="search" style="padding-left:10px;">
					<li><a class="button border-main icon-plus-square-o"
						href="<%=path%>/system/MedicalServlet?m=input"> 添加医疗机构</a></li>
					<li>搜索：</li>
					<li><input type="text" placeholder="请输入搜索关键字" name="keywords"
						class="input"
						style="width:250px; line-height:17px;display:inline-block" /> <a
						href="javascript:void(0)" class="button border-main icon-search"
						onclick="changesearch()"> 搜索</a></li>
				</ul>
			</div>
			<table class="table table-hover text-center">
				<tr>
					<th width="50">序号</th>
					<th width="50" style="text-align:center;"><input
						type="checkbox" name="checkAll" value="-1"></th>
					<th>机构编码</th>
					<th>组织机构编号</th>
					<th>地区编码</th>
					<th>行政区域编码</th>
					<th>隶属关系</th>
					<th>机构级别</th>
					<th>申报定点类型</th>
					<th>批准定点类型</th>
					<th>所属经济类型</th>
					<th>卫生机构大类</th>
					<th>卫生机构小类</th>
					<th>主办（管）单位</th>
					<th>开业/成立时间</th>
					<th>法定代表人</th>
					<th>注册资金</th>
					<th>操作</th>
				</tr>
				<%
					String pageNo = request.getParameter("pageNo");
					int p = pageNo == null ? 1 : Integer.parseInt(pageNo);
					MedicalDao dao = new MedicalDao();
					String sql = "select * from t_medical where 1=1 ";
					Object[] params = {};
					Page pageBean = dao.queryOjectsByPage(sql, params, p, Constant.ROW);
				%>
				<%
					for (int i = 0; i < pageBean.getDatas().size(); i++) {

						Medical model = (Medical) pageBean.getDatas().get(i);
				%>
				<tr>

					<td>序号</td>
					<td><input type="checkbox" name="checkAll"
						value="<%=model.getJgbm()%>"></td>
					<td><%=model.getJgbm()%></td>
					<td><%=model.getZzjgbm()%></td>
					<td><%=model.getDqbm()%></td>
					<td><%=model.getAreacode()%></td>
					<td><%=model.getLsgx()%></td>
					<td><%=model.getJgjb()%></td>
					<td><%=model.getSbddlx()%></td>
					<td><%=model.getPzddlx()%></td>
					<td><%=model.getSsjjlx()%></td>
					<td><%=model.getWsjgdl()%></td>
					<td><%=model.getWsjgxl()%></td>
					<td><%=model.getZgdw()%></td>
					<td><%=model.getKysj()%></td>
					<td><%=model.getFrdb()%></td>
					<td><%=model.getZczj()%></td>
					<td><div class="button-group">
							<a class="button border-main"
								href="<%=path%>/system/MedicalServlet?m=get&id=<%=model.getJgbm()%>"><span
								class="icon-edit"></span> 修改</a> <a class="button border-red"
								href="<%=path%>/system/MedicalServlet?m=del&id=<%=model.getJgbm()%>"><span
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
					href="<%=path%>/system/MedicalServlet?m=list&pageNo=<%=pageBean.prePage()%>">上一页</a>
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
				<a href="<%=path%>/system/MedicalServlet?m=list&pageNo=<%=num%>"><%=num%></a>
				<%
					}
					}
				%>
				<%
					if (pageBean.hasNext()) {
				%>
				<a
					href="<%=path%>/system/MedicalServlet?m=list&pageNo=<%=pageBean.lastPage()%>">下一页</a>
				<%
					} else {
				%>
				<a href="javascript:void(0)">下一页</a>
				<%
					}
				%>
				<a
					href="<%=path%>/system/MedicalServlet?m=list&pageNo=<%=pageBean.getPages()%>">尾页</a>
			</div>
		</div>
	</form>

</body>
</html>
