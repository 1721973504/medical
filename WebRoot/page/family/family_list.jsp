<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/base.jsp"%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
<base href="<%=basePath%>">

<title>参合家庭档案列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery-1.4.4.min.js"></script>
<script src="js/admin.js"></script>
<script src="https://upcdn.b0.upaiyun.com/libs/jquery/jquery-2.0.2.min.js"></script>
  <!-- 查询 -->
  <script type="text/javascript">
    $(document).ready(function(){
    	$("#lastname").keyup(
    		function(){
    			$("table tr:gt(0)").hide();
    			var $dd = $("table tr:gt(0)").filter(":contains('"+$.trim($("#lastname").val())+"')");
    			$dd.show();
    		}
    	)
    })
  </script>
</head>
<body>
	<form method="post" action="" id="listform">
		<div class="panel admin-panel">
			<div class="panel-head">
				<strong class="icon-reorder">参合家庭档案信息列表</strong>
			</div>
			<div class="padding border-bottom">
				<ul class="search" style="padding-left:10px;">
					<li><a class="button border-main icon-plus-square-o"
						href="<%=path%>/system/FamilyServlet?m=input"> 添加参合家庭档案</a></li>
					<li>搜索：</li>
					<li><input type="text" id="lastname" placeholder="请输入搜索关键字" name="keywords"
						class="input"
						style="width:250px; line-height:17px;display:inline-block" /> <a
						href="javascript:void(0)" class="button border-main icon-search"
						onclick="changesearch()"> 搜索</a></li>
				</ul>
			</div>
			<table class="table table-hover text-center">
				<tr>	<th width="50">序号</th>
					<th width="50" style="text-align:center;">&nbsp;</th>			
					<th>参合家庭编号</th>
					<th>县级编号</th>
					<th>乡镇编号</th>
					<th>村编号</th>
					<th>组编号</th>
					<th>户属性</th>
					<th>户主姓名</th>
					<th>家庭人口数</th>
					<th>农业人口数</th>
					<th>家庭住址</th>
					<th>创建档案时间</th>
					<th>登记员</th>
					<th>操作</th>
					<th>户主信息</th>
				</tr>
				<%
					String pageNo = request.getParameter("pageNo");
							int p = pageNo == null ? 1 : Integer.parseInt(pageNo);
							familyDao dao = new familyDao();
							String sql = "select * from t_family where 1=1 ";
							Object[] params = {};
							Page pageBean = dao.queryOjectsByPage(sql, params, p,
									Constant.ROW);
				%>
				<%
					for (int i = 0; i < pageBean.getDatas().size(); i++) {

						Family  model= (Family) pageBean.getDatas().get(i);
						
				%>
				<tr>
				<td><%=(i+1) %></td>
					<td><input type="checkbox" name="ids"
						value="<%=model.getFamilycode()%>" /></td>
					<td align="center"><%=model.getFamilycode()%></td>
					<td><%=model.getCountycode() %></td>
					<td><%=model.getTowncode() %></td>
					<td><%=model.getVillagecode() %></td>
					<td><%=model.getGroupcode() %></td>
					<td><%=model.getResidence() %></td>
					<td><%=model.getHolderName() %></td>
					<td><%=model.getFamilyNum() %></td>
					<td><%=model.getAgricultureNum() %></td>
					<td><%=model.getAddress() %></td>
					<td><%=model.getTime() %></td>
					<td><%=model.getRegistrarName() %></td>
					<td><div class="button-group">
							<a class="button border-main"
								href="<%=path%>/system/FamilyServlet?m=get&id=<%=model.getFamilycode()%>"><span
								class="icon-edit"></span> 修改</a> <a class="button border-red"
								href="<%=path%>/system/FamilyServlet?m=del&id=<%=model.getFamilycode()%>"><span
								class="icon-trash-o"></span> 删除</a>
						</div></td>
				 	<td><div class="button-group">
							<a class="button border-main"
								href="<%=path%>/system/HolderServlet?m=list&id=<%=model.getFamilycode()%>"><span
								class="icon-edit"></span>管理户主信息</a>
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
					href="<%=path%>/system/FamilyServlet?m=list&pageNo=<%=pageBean.prePage()%>">上一页</a>
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
				<a href="<%=path%>/system/FamilyServlet?m=list&pageNo=<%=num%>"><%=num%></a>
				<%
					}
					}
				%>
				<%
					if (pageBean.hasNext()) {
				%>
				<a
					href="<%=path%>/system/FamilyServlet?m=list&pageNo=<%=pageBean.lastPage()%>">下一页</a>
				<%
					} else {
				%>
				<a href="javascript:void(0)">下一页</a>
				<%
					}
				%>
				<a
					href="<%=path%>/system/FamilyServlet?m=list&pageNo=<%=pageBean.getPages()%>">尾页</a>
			</div>
		</div>
	</form>

</body>
</html>
