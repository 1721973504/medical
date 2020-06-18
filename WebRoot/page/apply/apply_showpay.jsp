<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<base href="<%=basePath%>">
 
<title>汇款档案列表</title>
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
				<strong class="icon-reorder">汇款档案信息列表
				<a href="<%=path%>/system/ApplyServlet?m=addcheck" target="right"><span
					class="icon-caret-right"></span> 慢病报销审核</a>
			<a href="<%=path%>/system/ApplyServlet?m=list"target="right"><span
					class="icon-caret-right"></span> 慢病报销</a></strong>
			</div>			
			<table class="table table-hover text-center">
					<tr><th>序号</th>
					<th >&nbsp;</th>
					<th>医院发票号</th>
					<th>疾病名称</th>
					<th>身份证号</th>
					<th>姓名</th>
					<th>医疗总费用</th>
					<th>报销金额</th>
					<th>就诊时间</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<%
					String pageNo = request.getParameter("pageNo");
					int p = pageNo == null ? 1 : Integer.parseInt(pageNo);
					ApplyDao dao = new ApplyDao();
					String sql = "select * from t_applypay where 1=1 ";
					Object[] params = {};
					Page pageBean = dao.queryOjectsByPage(sql, params, p,
							Constant.ROW);
				%>
				<%
					for (int i = 0; i < pageBean.getDatas().size(); i++) {

						Apply  model= (Apply) pageBean.getDatas().get(i);
						
				%>
				<tr>
				<td><%=(i+1) %></td>
					<td><input type="checkbox" name="ids"
						value="<%=model.getInvoiceNum()%>" /></td>
					<td><%=model.getInvoiceNum()%></td>					
					<td><%=model.getMedicalName()%></td>
					<td><%=model.getIdcard() %></td>
					<td><%=model.getName() %></td>
					<td><%=model.getTotal() %></td>
					<td><%=model.getAmount() %></td>
					<td><%=model.getTime() %></td>
					<td>已汇款</td>
					<td><div class="button-group">					      
							<a class="button border-main"
								href="<%=path%>/system/ApplyServlet?m=del&id=<%=model.getInvoiceNum()%>"><span
								class="icon-edit"></span> 删除</a> 
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
					href="<%=path%>/system/ApplyServlet?m=list&pageNo=<%=pageBean.prePage()%>">上一页</a>
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
				<a href="<%=path%>/system/ApplyServlet?m=list&pageNo=<%=num%>"><%=num%></a>
				<%
					}
					}
				%>
				<%
					if (pageBean.hasNext()) {
				%>
				<a
					href="<%=path%>/system/ApplyServlet?m=list&pageNo=<%=pageBean.lastPage()%>">下一页</a>
				<%
					} else {
				%>
				<a href="javascript:void(0)">下一页</a>
				<%
					}
				%>
				<a
					href="<%=path%>/system/ApplyServlet?m=list&pageNo=<%=pageBean.getPages()%>">尾页</a>
			</div>
		</div>
	</form>

</body>
</html>
