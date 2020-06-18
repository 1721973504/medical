<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/base.jsp"%>
<% List<Apply> SearchPay=(List<Apply>)request.getAttribute("SearchPay"); %>
<% 
  AreaDao areaDao=new AreaDao();
  int[]grade={1}; 
  List<Area> areaList=areaDao.findListByGrade(grade);
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<base href="<%=basePath%>">

<title>慢病报销汇款</title>
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
<script type="text/javascript" src="${pageContext.request.contextPath }/third/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<script type="text/javascript">
	$(document).ready(function() {
		//重新绑定表单提交
		$("#add_btn").bind("click", function() {
			$('form').submit();
		});
	});
</script>
		<div class="panel admin-panel">
			<div class="panel-head">
				<strong class="icon-reorder">慢病报销汇款
				<a href="<%=path%>/system/ApplyServlet?m=addcheck" target="right"><span
					class="icon-caret-right"></span> 慢病报销审核</a>
			<a href="<%=path%>/system/ApplyServlet?m=list"target="right"><span
					class="icon-caret-right"></span> 慢病报销</a></strong>
			</div>
			<div class="padding border-bottom">
				<ul class="search" style="padding-left:10px;">	
			
					<form action="<%=path%>/system/ApplyServlet?m=searchpay" method="post" >
				<ul class="search" style="padding-left:10px;">
				<li> 行政区域：<select id="areacode" name="areacode" class="input"style="width:250px; line-height:17px;display:inline-block">
						<%
						  for(Area c:areaList){ 
						
						  %>
						  <option value="<%=c.getAreacode()%>">
						  <%
						  
						  for(int i=0;i<c.getGrade();i++){
							 if(i==0){
								 out.print("|-"); 
							 }else{
								 out.print("-");
							 }
							  
						  } %>
						  <%=c.getAreaname() %></option>
						<%} %>
							
						</select>
						姓名：<input type="text" name="Name" class="input" style="width:250px; line-height:17px;display:inline-block" >
				 首时间：<input type="text" name="startTime" class="input" style="width:250px; line-height:17px;display:inline-block"onclick="WdatePicker({firstDayOfWeek:7})"></li> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				尾时间：<input type="text" name="endTime" class="input" style="width:250px; line-height:17px;display:inline-block"onclick="WdatePicker({firstDayOfWeek:7})"></li> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						  <input type="submit" class="button border-main" value="查询"/>
				</ul>
				</form>
				</ul>
				
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
					<th>报销提交时间</th>
					<th>操作</th>
				</tr>
					<%
					String pageNo = request.getParameter("pageNo");
					int p = pageNo == null ? 1 : Integer.parseInt(pageNo);
					ApplyDao dao = new ApplyDao();
					String sql = "select * from t_apply where 1=1 ";
					Object[] params = {};
					Page pageBean = dao.queryOjectsByPage(sql, params, p,
							Constant.ROW);
				%>
				<%
					
						 if(SearchPay!=null&&!SearchPay.isEmpty()){
					for(Apply model : SearchPay){
						
				%>
				<form action="<%=path%>/system/ApplyServlet?m=addpays" method="post" >
				<tr>
				<td> </td>
				
					<td><input type="checkbox" name="ids"
						value="<%=model.getInvoiceNum()%>" /></td>																										
					<td><%=model.getInvoiceNum()%></td>									<input type="hidden" class="input" name="InvoiceNum" value="<%=model.getInvoiceNum() %>" >			
					<td><%=model.getMedicalName()%></td>								<input type="hidden" class="input" value="<%=model.getMedicalName()%>" name="MedicalName" >							
					<td><%=model.getIdcard() %></td>									<input type="hidden" class="input" value="<%=model.getIdcard()%>" name="idcard" >						
					<td><%=model.getName() %></td>										<input type="hidden" class="input" value="<%=model.getName()%>" name="Name" >	
					<td><%=model.getTotal() %></td>										<input type="hidden" class="input" name="total"value="<%=model.getTotal() %>" >								
					<td><%=model.getAmount() %></td>									<input  type="hidden" class="input" name="amount" value="<%=model.getAmount() %>" >	
					<td><%=model.getTime() %></td>										<input type="hidden" class="input" value="<%=model.getTime()%>" name="Time" >			
					<td><%=model.getCreatTime() %></td>									<input type="hidden" class="input" value="<%=model.getCreatTime()%>" name="CreatTime">
																						<input type="hidden" name="areacode" value="<%=model.getAreacode() %>" class="input">																									
					<td><button id="add_btn" class="button bg-main icon-check-square-o" type="button">汇款</button>				</td>
				</tr>
				
				<%}
					}
				%>
				</form>
			</table>
			<div class="pagelist">
				<%
					if (pageBean.hasPre()) {
				%>
				<a
					href="<%=path%>/system/PayServlet?m=list&pageNo=<%=pageBean.prePage()%>">上一页</a>
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
				<a href="<%=path%>/system/PayServlet?m=list&pageNo=<%=num%>"><%=num%></a>
				<%
					}
					}
				%>
				<%
					if (pageBean.hasNext()) {
				%>
				<a
					href="<%=path%>/system/PayServlet?m=list&pageNo=<%=pageBean.lastPage()%>">下一页</a>
				<%
					} else {
				%>
				<a href="javascript:void(0)">下一页</a>
				<%
					}
				%>
				<a
					href="<%=path%>/system/PayServlet?m=list&pageNo=<%=pageBean.getPages()%>">尾页</a>
			</div>
		</div>
	</form>

</body>
</html>
