<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/base.jsp"%>
<%
ChronicdisDao chronicdisDao=new ChronicdisDao();
List<Chronicdis> chronicdisList=chronicdisDao.findAll(); 
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
<script type="text/javascript" src="${pageContext.request.contextPath }/third/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/admin.js"></script>
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
			<strong><span class="icon-pencil-square-o"></span>增加参合慢性病证信息</strong>
		</div>
		<div class="padding border-bottom">
				<form action="<%=path%>/system/CardServlet?m=searchs" method="post" class="form-x" style="padding-left:100px;">
			<div class="form-group">
				<p >姓名：<input type="text" name="name" class="input" style="width:250px; line-height:17px;display:inline-block"><button  type="submit" class="button border-main" value="">查询</button></p> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				
				</div>
			</form>	
			</div>
			
				<%
				List<Peasant> Search=(List<Peasant>)request.getAttribute("Search");
				if(Search!=null&&!Search.isEmpty()){
				%>
				
					<%
					
					for(Peasant model : Search){
			%>
				<form method="post" action="" id="listform">
				<table class="table table-hover text-center">
					<tr>
						<th>常住地址</th>
						<th>姓名</th>
						<th>身份证号</th>
						<th>选择</th>
					</tr>				
						<td> <%=model.getAddress() %></td>						
						<td><%=model.getName() %></td>
						<td><%=model.getIdcard() %></td>
						<td><div class="button-group">					      
							<a class="button border-main"
								href="<%=path%>/system/CardServlet?m=searchs&cardNum=<%=model.getCardNum()%>&name=<%=model.getName() %>&idcard=<%=model.getIdcard() %>">
								<span class="icon-edit"></span>选择</a>
						</div></td>	
						</table>	</form>						
  			   <%
					}
				}
			%>

		<div class="body-content">
			<form id="form-add" method="post" class="form-x"
				action="<%=path%>/system/CardServlet?m=add">
		       
				 <div class="form-group">
					<div class="label">
						<label>农合证号：</label>
					</div>
					<div class="field">
				     <input type="text" class="input w50" value="<%=request.getParameter("cardNum")%>" name="MedicalCard"
							data-validate="required:请输入农合证号" />
							
					<div class="tips"></div>
					</div>
				</div>
				

				
				<div class="form-group">
					<div class="label">
						<label>姓名：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=request.getParameter("name")%>" name="name"
							data-validate="required:请输入姓名" />
						<div class="tips"></div>
					</div>
				</div>
  			   
				<div class="form-group">
					<div class="label">
						<label>身份证号：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=request.getParameter("idcard")%>" name="idcard"
							data-validate="required:请输入身份证号" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>疾病名称：</label>
					</div>
					<div class="field">
							<select type="text" class="input w50" value="" name="MedicalName"
							data-validate="required:请输入疾病名称">
							<%
						  for(Chronicdis c:chronicdisList){ 
						
						  %>
							<option value="<%=c.getIllname() %>">
								
								<%=c.getIllname() %></option>
							<%} %>
						</select>
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>起始时间：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="" name="StartTime"onclick="WdatePicker({firstDayOfWeek:7})"
							data-validate="required:请输入起始时间" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>终止时间：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="" name="EndTime"onclick="WdatePicker({firstDayOfWeek:7})"
							data-validate="required:请输入终止时间" />
						<div class="tips"></div>
					</div>
				</div>
				
			
				<div class="form-group">
					<div class="label">
						<label></label>
					</div>
					<div class="field">
						<button id="add_btn" class="button bg-main icon-check-square-o"
							type="button">提交</button>
					</div>
				</div>
			</form>
		</div>
	</div>

</body>
</html>
