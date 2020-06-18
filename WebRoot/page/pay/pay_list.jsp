<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/base.jsp"%>
<% List<Pay> SearchMessage=(List<Pay>)request.getAttribute("SearchMessage"); %>
<% 
  AreaDao areaDao=new AreaDao();
  int[]grade={1}; 
  List<Area> areaList=areaDao.findListByGrade(grade);
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<base href="<%=basePath%>">

<title>参合缴费登记列表</title>
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

<script type="text/javascript">
    function doPrint() {
        window.print();
    }
 
    $(function () {
        //打印,连接自带打印功能
        $("#btnPrint").bind("click", function (event) {
            doPrint();
        });
    });
</script>
</head>
<body>
		<div class="panel admin-panel">
			<div class="panel-head">
				<strong class="icon-reorder">参合缴费登记信息列表</strong>
			</div>
			<div class="padding border-bottom">
				<ul class="search" style="padding-left:10px;">
					<li><a class="button border-main icon-plus-square-o"
						href="<%=path%>/system/PayServlet?m=input"> 添加参合缴费登记</a></li>
					<form action="<%=path%>/system/PayServlet?m=searchs" method="post" >
				<ul class="search" style="padding-left:10px;">
				<li> 年份：<input type="text" name="PayYear" class="input" style="width:180px; line-height:17px;display:inline-block">
				 行政区域：<select id="areacode" name="areacode" class="input"style="width:180px; line-height:17px;display:inline-block">
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
				 时间：<input type="text" name="PayTime" class="input" style="width:180px; line-height:17px;display:inline-block" onclick="WdatePicker({firstDayOfWeek:7})">
				 姓名：<input type="text" name="Name" class="input" style="width:180px; line-height:17px;display:inline-block"></li> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  <input type="submit" class="button border-main" value="查询"/>
				</ul>
				</form>
				</ul>
				
			</div>
			<form method="post" action="<%=path%>/system/ExportExcelServlet" id="listform">
			<td align="center" colspan="2">
			<input class="button border-main icon-plus-square-o" type="submit" style="float:right" value="导出到Excel"></td>
			<table class="table table-hover text-center">
				<tr>	<th width="50">  </th>
					<th width="50" style="text-align:center;">&nbsp;</th>		
					<th>参合证号</th>
					<th>行政区域</th>
					<th>参合发票号</th>
					<th>缴费人</th>
					<th>缴费金额</th>
					<th>缴费年度</th>
					<th>缴费时间</th>
					<th>操作员</th>
					<th>操作</th>
				</tr>
				<%
					String pageNo = request.getParameter("pageNo");
							int p = pageNo == null ? 1 : Integer.parseInt(pageNo);
							PayDao dao = new PayDao();							
							String sql = "select * from t_pay where 1=1 ";							
							Object[] params = {};
							Page pageBean = dao.queryOjectsByPage(sql, params, p,
									Constant.ROW);							
							
								%>				
				<%
						 if(SearchMessage!=null&&!SearchMessage.isEmpty()){
					for(Pay pay : SearchMessage){
			%>
				<tr>
					<td>  </td>
					<td><input type="checkbox" name="ids"
						value="<%=pay.getParticipationid()%>" /></td>
					<td align="center" name="Participationid"><%=pay.getParticipationid()%></td>
					<td name="areacode"><%=pay.getAreacode() %></td>
					<td name="InvoiceNum"><%=pay.getInvoiceNum() %></td>
					<td name="Name"><%=pay.getName() %></td>
					<td name="PayMoney"><%=pay.getPayMoney() %></td>
					<td name="PayYear"><%=pay.getPayYear() %></td>
					<td name="PayTime"><%=pay.getPayTime() %></td>
					<td name="registrarName"><%=pay.getRegistrarName() %></td>		
					<td><div class="button-group">
								<button id="btnPrint" class="button bg-main icon-check-square-o"
							type="button">打印</button>
						</div></td>	
				</tr>
				<%
					}
					}else{
					for (int i = 0; i < pageBean.getDatas().size(); i++) {
					 Pay model= (Pay) pageBean.getDatas().get(i);
				%>
				<tr>
				<td><%=(i+1) %></td>
					<td><input type="checkbox" name="ids"
						value="<%=model.getParticipationid()%>" /></td>
					<td align="center" name="Participationid"><%=model.getParticipationid()%></td>
					<td name="areacode"><%=model.getAreacode() %></td>
					<td name="InvoiceNum"><%=model.getInvoiceNum() %></td>
					<td name="Name"><%=model.getName() %></td>
					<td name="PayMoney"><%=model.getPayMoney() %></td>
					<td name="PayYear"><%=model.getPayYear() %></td>
					<td name="PayTime"><%=model.getPayTime() %></td>
					<td name="registrarName"><%=model.getRegistrarName() %></td>		
					<td>
							<div class="button-group">
								<button id="btnPrint" class="button bg-main icon-check-square-o"
							type="button">打印</button>
						</div></td>		
				</tr>
				<%			}		
				}				%>
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
