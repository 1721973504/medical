<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/base.jsp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.gxuwz.medical.tools.DateUtil"%>
<%
	String idcard = request.getParameter("idcard");
	String name = request.getParameter("Name");
	Peasant pmodel = new Peasant(idcard); 
%>
<% 
  AreaDao areaDao=new AreaDao();
  int[]grade={1}; 
  List<Area> areaList=areaDao.findListByGrade(grade);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<link rel="stylesheet" type="text/css" href="css/styles.css">
<link rel="stylesheet" type="text/css" href="css/admin.css">
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/admin.js"></script>
<link rel="stylesheet" href="//apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css">
  <script src="//apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
  <script src="//apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/third/My97DatePicker/WdatePicker.js"></script>
  <link rel="stylesheet" href="jqueryui/style.css">
<script type="text/javascript">
	$(document).ready(function() {
		//重新绑定表单提交
		$("#add_btn").bind("click", function() {
			$('form').submit();
		});
	});
</script>
 
</head>

<body>

	<div class="panel admin-panel">
		<div class="panel-head" id="add">
			<strong><span class="icon-pencil-square-o"></span>增加报销登记信息</strong>
		</div>
		<div style="">
		<div class="panel admin-panel">
		
			<div class="padding border-bottom">
				<form action="<%=path%>/system/ApplyServlet?m=searchs" method="post" class="form-x" style="padding-left:100px;">
			<div class="form-group"><ul class="search" style="padding-left:10px;">
				<li> 疾病名称：<input type="text" name="MedicalName" value="" class="input" style="width:250px; line-height:17px;display:inline-block">
				 就诊时间：<input type="text" name="Time" class="input" style="width:200px; line-height:17px;display:inline-block" onclick="WdatePicker({firstDayOfWeek:7})" >
				 姓名：<input type="text" name="Name" class="input" value="" style="width:200px; line-height:17px;display:inline-block"></li> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 身份证号：<input type="text" name="idcard" class="input" value="" style="width:250px; line-height:17px;display:inline-block"></li> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  <input type="submit" class="button border-main" value="查询"/>
				</ul>
				</div>
			</form>	
			</div>
			
				<table class="table table-hover text-center">				
				<%
					String pageNo = request.getParameter("pageNo");
					int p = pageNo == null ? 1 : Integer.parseInt(pageNo);
					CardDao dao = new CardDao();
					String sql = "select * from t_card where 1=1 ";
					Object[] params = {};
					Page pageBean = dao.queryOjectsByPage(sql, params, p,
							Constant.ROW);
				%>
				<%
				List<Card> SearchMessage=(List<Card>)request.getAttribute("SearchMessage");
				if(SearchMessage!=null&&!SearchMessage.isEmpty()){
					for(Card model : SearchMessage){
			%>								
					<td>与慢性病证信息一致,请填总金额</td>	
				<%
				String id = request.getParameter("idcard");
				String MedicalName = request.getParameter("MedicalName");
				String Time = request.getParameter("Time");				
				%>
				          　		
	        <div style="position:absolute;margin-top: 50px">
			<ul class="search" style="padding-left:10px;">
				<li>当年报销比例：${proportion}<input type="hidden" name="proportion" id="proportion" value="${proportion}"/></li>
				<li id="amount"></li>
				</ul>	
				<ul>
				
				<form id="form-add" method="post" action="<%=path%>/system/ApplyServlet?m=add">		
				<ul class="search" style="padding-left:10px;">
			
						<li>	
						行政区域：<select id="areacode" name="areacode" class="input"style="width:250px; line-height:17px;display:inline-block">
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
						医院发票号:			
						<input type="text" class="input" name="InvoiceNum" value="yy201906060001" data-validate="required:请输入户主姓名" style="width:150px; line-height:17px;display:inline-block">																
						医疗总费用：	
						<input type="text" class="input" name="total" id="d0"value="" style="width:150px; line-height:17px;display:inline-block" data-validate="required:请输入缴费金额">			
						报销费用：
						<input  type="text"  id='d1' class="input" name="amount" value="" style="width:150px; line-height:17px;display:inline-block">		
						<input type="hidden" class="input" value="<%=name%>" name="Name" style="width:150px; line-height:17px;display:inline-block" data-validate="required:请输入缴费年度">				
						<input type="hidden" class="input" value="<%=id%>" name="idcard" style="width:150px; line-height:17px;display:inline-block" data-validate="required:请输入缴费时间">	
						<input type="hidden" class="input" value="<%=MedicalName%>" name="MedicalName" style="width:150px; line-height:17px;display:inline-block" data-validate="required:请输入缴费时间">
						<input type="hidden" class="input" value="<%=Time%>" name="Time" style="width:150px; line-height:17px;display:inline-block" data-validate="required:请输入缴费时间">
						<%
		       	 	Date d = new Date();
		       	 	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			    	String CreatTime = df.format(d);
	            %>
				<input type="hidden" class="input" value="<%=CreatTime%>" name="CreatTime" style="width:150px; line-height:17px;display:inline-block" data-validate="required:请输入缴费时间">
						
<script>  
	var input = document.getElementById('d0')
	var proportion=$("#proportion").val();			
	var div = document.getElementById('d1');  
	input.oninput = function(){  
		div.value = input.value*proportion; //总金额*比例    
	}  
  
</script>  		
					
				</li>
						<button id="add_btn" class="button bg-main icon-check-square-o" type="button">提交</button>
					
			</ul>
				</form></ul>
			</div>																		
				<%}
					}else{					
				%>			
					<td>无效信息,请核查信息</td>													
				<%}					
				%>
			</table>
		</div>

				
			
</body>
</html>
