<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<base href="<%=basePath%>">
 
<title>农民档案列表</title>
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
				<strong class="icon-reorder">农民档案信息列表</strong>
			</div>
			<div class="padding border-bottom">
				<ul class="search" style="padding-left:10px;">
					<li><a class="button border-main icon-plus-square-o"
						href="<%=path%>/system/PeasantServlet?m=input"> 添加农民信息</a></li>
					<li>搜索：</li>
					<li><input type="text" id="lastname" placePeasant="请输入搜索关键字" name="keywords"
						class="input"
						style="width:250px; line-height:17px;display:inline-block" /> <a
						href="javascript:void(0)" class="button border-main icon-search"
						onclick="changesearch()"> 搜索</a></li>						
				</ul>
			</div>
			<table class="table table-hover text-center">
					<tr><th>序号</th>
					<th >&nbsp;</th>
					<th>家庭编号</th>
					<th>农合证号</th>
					<th>医疗证卡号</th>
					<th>户内编号</th>
					<th>姓名</th>
					<th>与户主关系</th>
					<th>身份证号</th>
					<th>性别</th>
					<th>健康状况</th>
					<th>民族</th>
					<th>文化程度</th>
					<th>年龄</th>
					<th>出生日期</th>
					<th>人员属性</th>
					<th>是否农业户口</th>
					<th>职业</th>
					<th>工作单位</th>
					<th>联系电话</th>
					<th>常住地址</th>
					<th>联系方式</th>
					<th>操作</th>
				</tr>
				<%
					String pageNo = request.getParameter("pageNo");
					int p = pageNo == null ? 1 : Integer.parseInt(pageNo);
					PeasantDao dao = new PeasantDao();
					String sql = "select * from t_peasant where 1=1 ";
					Object[] params = {};
					Page pageBean = dao.queryOjectsByPage(sql, params, p,
							Constant.ROW);
				%>
				<%
					for (int i = 0; i < pageBean.getDatas().size(); i++) {

						Peasant  model= (Peasant) pageBean.getDatas().get(i);
						
				%>
				<tr>
				<td><%=(i+1) %></td>
					<td><input type="checkbox" name="ids"
						value="<%=model.getIdcard()%>" /></td>
					<td><%=model.getFamilycode()%></td>
					<td><%=model.getCardNum()%></td>
					<td><%=model.getMedicalNum()%></td>
					<td><%=model.getCode()%></td>
					<td><%=model.getName() %></td>
					<td><%=model.getRelations()%></td>
					<td><%=model.getIdcard() %></td>
					<td><%=model.getSex()%></td>
					<td><%=model.getHealthy()%></td>
					<td><%=model.getNation()%></td>
					<td><%=model.getEducation()%></td>
					<td><%=model.getAge()%></td>
					<td><%=model.getBirthday()%></td>
					<td><%=model.getAttribute()%></td>
					<td><%=model.getAgricultural()%></td>
					<td><%=model.getProfession()%></td>
					<td><%=model.getWorkunit()%></td>
					<td><%=model.getPhone()%></td>
					<td><%=model.getAddress()%></td>
					<td><%=model.getEmail()%></td>
					<td><div class="button-group">					      
							<a class="button border-main"
								href="<%=path%>/system/PeasantServlet?m=get&id=<%=model.getIdcard()%>"><span
								class="icon-edit"></span> 修改</a> <a class="button border-red"
								href="<%=path%>/system/PeasantServlet?m=del&id=<%=model.getIdcard()%>"><span
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
					href="<%=path%>/system/PeasantServlet?m=list&pageNo=<%=pageBean.prePage()%>">上一页</a>
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
				<a href="<%=path%>/system/PeasantServlet?m=list&pageNo=<%=num%>"><%=num%></a>
				<%
					}
					}
				%>
				<%
					if (pageBean.hasNext()) {
				%>
				<a
					href="<%=path%>/system/PeasantServlet?m=list&pageNo=<%=pageBean.lastPage()%>">下一页</a>
				<%
					} else {
				%>
				<a href="javascript:void(0)">下一页</a>
				<%
					}
				%>
				<a
					href="<%=path%>/system/PeasantServlet?m=list&pageNo=<%=pageBean.getPages()%>">尾页</a>
			</div>
		</div>
	</form>

</body>
</html>
