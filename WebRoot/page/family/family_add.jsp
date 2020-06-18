<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/base.jsp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<% 
  //实例化AreaDao
  AreaDao areaDao=new AreaDao();
  int[]gradeone={1};
  int[]gradetwo={2};
  int[]gradethree={3};
  int[]gradefour={4};
     
  List<Area> countyList=areaDao.findListByGrade(gradeone);
  List<Area> townList=areaDao.findListByGrade(gradetwo);
  List<Area> villageList=areaDao.findListByGrade(gradethree);
  List<Area> groupList=areaDao.findListByGrade(gradefour);

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
			<strong><span class="icon-pencil-square-o"></span>增加家庭档案信息</strong>
		</div>
		<div class="body-content">
			<form id="form-add" method="post" class="form-x"
				action="<%=path%>/system/FamilyServlet?m=add">				
				<div class="form-group">
					<div class="label">
						<label>所属县级：</label>
					</div>
					<div class="field">
						<select id="countycode" name="countycode" class="input w50">
							<%
						  for(Area c:countyList){ 
						
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
						<label>所属乡镇：</label>
					</div>
					<div class="field">
						<select id="towncode" name="towncode" class="input w50">
							<%
						  for(Area t:townList){ 
						
						  %>
							<option value="<%=t.getAreacode()%>">
								<%
						  
						  for(int i=0;i<t.getGrade();i++){
							 if(i==0){
								 out.print("|-"); 
							 }else{
								 out.print("-");
							 }
							  
						  } %>
								<%=t.getAreaname() %></option>
							<%} %>

						</select>


						<div class="tips"></div>
					</div>

				</div>
				<div class="form-group">
					<div class="label">
						<label>所属村：</label>
					</div>
					<div class="field">
						<select id="villagecode" name="villagecode" class="input w50">
							<%
						  for(Area v:villageList){ 
						
						  %>
							<option value="<%=v.getAreacode()%>">
								<%
						  
						  for(int i=0;i<v.getGrade();i++){
							 if(i==0){
								 out.print("|-"); 
							 }else{
								 out.print("-");
							 }
							  
						  } %>
								<%=v.getAreaname() %></option>
							<%} %>
						</select>
						<div class="tips"></div>
					</div>

				</div>
				<div class="form-group">
					<div class="label">
						<label>所属组：</label>
					</div>
					<div class="field">
						<select id="familycode" name="groupcode" class="input w50">
						
							<%
						  for(Area g:groupList){ 
						
						  %>
							<option value="<%=g.getAreacode()%>">
								<%
						  
						  for(int i=0;i<g.getGrade();i++){
							 if(i==0){
								 out.print("|-"); 
							 }else{
								 out.print("-");
							 }
							  
						  } %>
								<%=g.getAreaname() %></option>
							<%} %>

						</select>


						<div class="tips"></div>
					</div>

				</div>
				<div class="form-group">
					<div class="label">
						<label>户属性：</label>
					</div>
					<div class="field">
							<select id="residence" name="residence" class="input w50">
							<option value="0">选择是否农业户口</option>
							<option value="农业户口">农业户口</option>
							<option value="非农业户口">非农业户口</option>						
							</select>
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>户主姓名：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="" name="holderName"
							data-validate="required:户主姓名" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>身份证号：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="" name="idcard"
							data-validate="required:请输入身份证号" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>家庭人口数：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="" name="familyNum"
							data-validate="required:请输入家庭人口数" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>农业人口数：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="" name="AgricultureNum"
							data-validate="required:请输入农业人口数" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>家庭住址：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="" name="address"
							data-validate="required:请输入家庭住址" />
						<div class="tips"></div>
					</div>
				</div>
				<%
		       	 	Date d = new Date();
		       	 	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			    	String time = df.format(d);
	            %>
	
				<div class="form-group">
					<div class="label">
						<label>创建档案时间：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=time %>" name="time"
							data-validate="required:请输入创建档案时间" readonly="readonly" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>登记员：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=request.getSession().getAttribute("username")%>" name="registrarName"
							data-validate="required:请输入登记员" readonly="readonly"  />
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
