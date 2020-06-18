<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/base.jsp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<% 
  AreaDao areaDao=new AreaDao();
  int[]grade={1}; 
  List<Area> areaList=areaDao.findListByGrade(grade);
  Pay pay = new Pay();
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
  <link rel="stylesheet" href="jqueryui/style.css">
<script type="text/javascript">
	$(document).ready(function() {
		//重新绑定表单提交
		$("#add_btn").bind("click", function() {
			$('form').submit();
		});
	});
</script>
 <script>
		function count()
			{
				var nums = 0;
				var chk = document.getElementsByName('ids');
				for (var i = 0; i < chk.length; i++) {
				if(chk[i].checked){
				nums++;
				}
			}
			document.getElementById('PayMoney').value = nums*100;
			}
	</script>
	
</head>

<body>
<div style="float: left">
	<div class="panel admin-panel">
		<div class="panel-head" id="add">
			<strong><span class="icon-pencil-square-o"></span>增加参合缴费登记信息</strong>
		</div>
		<div style="">
		<div class="panel admin-panel">
		
			<div class="padding border-bottom">
			
				<form action="<%=path%>/system/HolderServlet?m=searchs" method="post" class="form-x" style="padding-left:100px;width: 925px;">
			<div class="form-group">
				<p>姓名：<input type="text" name="name" class="input" style="width:250px; line-height:17px;display:inline-block"><input type="submit" class="button border-main" value="查询"></p> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				
				</div>
			</form>	
			</div>
			<form method="post" action="" id="listform">
				<table class="table table-hover text-center">
					<tr>
					<th >&nbsp;</th>
					<th >&nbsp;</th>
					<th>家庭编号</th>
					<th>农合证号</th>
					<th>医疗证卡号</th>
					<th>姓名</th>
					<th>身份证号</th>
					<th>性别</th>		
					<th>状态</th>
					<th>家庭成员</th>					
				</tr>
				<%
				List<Peasant> SearchName=(List<Peasant>)request.getAttribute("SearchName");
				if(SearchName!=null&&!SearchName.isEmpty()){
					for(Peasant model : SearchName){
			%>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td><%=model.getFamilycode()%></td>
					<td><%=model.getCardNum()%></td>
					<td><%=model.getMedicalNum()%></td>					
					<td ><%= model.getName() %></td>
					<td><%=model.getIdcard() %></td>
					<td><%=model.getSex()%></td>
					<td>&nbsp;</td>
					<td><div class="button-group">					      
							<a class="button border-main"
								href="<%=path%>/system/HolderServlet?m=searchs&id=<%=model.getFamilycode()%>&name=<%=model.getName() %>"><span
								class="icon-edit"></span>家庭成员</a>
						</div></td>	
				</tr>
				<%
					}
				}
			%>
			</table>
		</div>
	</form>
	</hr>
	<div class="panel admin-panel">
	<div class="padding border-bottom">
    <table class="table table-hover text-center">
				
				
					
				<%
					String pageNo = request.getParameter("pageNo");
					int p = pageNo == null ? 1 : Integer.parseInt(pageNo);
					PeasantDao dao = new PeasantDao();
					String familycode = request.getParameter("id");
					Family model = new Family(familycode);
					String sql = "select * from t_peasant where familycode="+familycode+" and status=0";
					Object[] params = {};
					Page pageBean = dao.queryOjectsByPage(sql, params, p,
							Constant.ROW);
				%>
				<%
					for (int i = 0; i < pageBean.getDatas().size(); i++) {

						Peasant  pmodel= (Peasant) pageBean.getDatas().get(i);
						
				%>
				<tr>
				<td><%=(i+1) %></td>
				
					<!-- 命为复选框，进行选择 -->
					<td><input type="checkbox" name="ids"
						value="<%=pmodel.getIdcard()%>"  onClick="count()""/></td>
					<td><%=model.getFamilycode()%></td>
					<td><%=pmodel.getCardNum()%></td>
					<td><%=pmodel.getMedicalNum()%></td>					
					<td><%=pmodel.getName() %></td>					
					<td><%=pmodel.getIdcard() %></td>
					<td><%=pmodel.getSex()%></td>
					<td><%=pmodel.getStatus()==1?"已缴费":"未缴费"%></td>
					<td>&nbsp;</td>
				</tr>
				<%
					}
				%>                　		
			    </table>
			</div>
		</div>
</div>
		</div>	
		</div>
	<div style="position:absolute;margin-top: 20px;margin-left: 1000px">
			<form id="form-add" method="post"
				action="<%=path%>/system/PayServlet?m=add">			
				<div class="form-group">
					<div class="label">
						<label>所属地区：</label>
					</div>
					<div class="field">
							<select id="areacode" name="areacode" class="input" style="width:250px; line-height:17px;display:inline-block" >
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
						<div class="tips"></div>
					</div>
				</div>		
				
				<div class="form-group">
					<div class="label">
						<label>户主姓名:</label>
					</div>
					<div class="field">	
									
						<%String Name=model.getHolderName(); %>	
									
						<input type="text" class="input" name="Name" value="<%=Name %>" 
							data-validate="required:请输入户主姓名" style="width:250px; line-height:17px;display:inline-block" >										
					<div class="tips"></div>
					</div>
				</div>		
				
					<div class="form-group">
					<div class="label">
						<label>缴费金额：</label>
					</div>
					<div class="field">	
									
						<input type="text" class="input"  name="PayMoney"  id="PayMoney"  value=" " style="width:250px; line-height:17px;display:inline-block" 
							data-validate="required:请输入缴费金额"/>
				<div class="tips"></div>
					</div>
				</div>					
				<div class="form-group">
					<div class="label">
						<label>缴费年度：</label>
					</div>
					<div class="field">
				<%
		       	 	Date da = new Date();
		       	 	SimpleDateFormat dfy = new SimpleDateFormat("yyyy");
			    	String year = dfy.format(da);
	            %>
				
						
					
						<input type="text" class="input" value="<%=year %>" name="PayYear"style="width:250px; line-height:17px;display:inline-block" 
							data-validate="required:请输入缴费年度" />
				<div class="tips"></div>
					</div>
				</div>		
				<div class="form-group">
					<div class="label">
						<label>	缴费时间：</label>
					</div>
					<div class="field">	
				<%
		       	 	Date d = new Date();
		       	 	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			    	String time = df.format(d);
	            %>
	
				
					
						<input type="text" class="input" value="<%=time %>" name="PayTime"style="width:250px; line-height:17px;display:inline-block" 
							data-validate="required:请输入缴费时间"/>
						
						<div class="tips"></div>
					</div>
				<div class="form-group">
					<div class="label">
						<label>操作员:</label>
					</div>
					<div class="field">		
						
					
						<input type="text" class="input" value="<%=request.getSession().getAttribute("fullname")%>" name="registrarName"
							data-validate="required:请输入登记员"  style="width:250px; line-height:17px;display:inline-block"  />
						
			<div class="tips"></div>
					</div>
				</div>		
						<button id="add_btn" class="button bg-main icon-check-square-o"
							type="button">提交</button>
					
			</ul>
				</form>
				</ul>
			</div>
</body>
</html>
