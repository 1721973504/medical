<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/base.jsp"%>
<% 
  //实例化AreaDao
  AreaDao areaDao=new AreaDao();
  int[]grades={1,2};
  List<Area> areaList=areaDao.findListByGrade(grades);

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
			<strong><span class="icon-pencil-square-o"></span>增加经办机构编码信息</strong>
		</div>
		<div class="body-content">
			<form id="form-add" method="post" class="form-x"
				action="<%=path%>/system/InstitutionServlet?m=add">
				<div class="form-group">
					<div class="label">
						<label>所属地区：</label>
					</div>
					<div class="field">
						<select id="areacode" name="areacode" class="input w50">
							<%
						  for(Area m:areaList){ 
						
						  %>
							<option value="<%=m.getAreacode()%>">
								<%
						  
						  for(int i=0;i<m.getGrade();i++){
							 if(i==0){
								 out.print("|-"); 
							 }else{
								 out.print("-");
							 }
							  
						  } %>
								<%=m.getAreaname() %></option>
							<%} %>

						</select>


						<div class="tips"></div>
					</div>

				</div>
				<div class="form-group">
					<div class="label">
						<label>经办机构编码：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="" name="agencode"
							data-validate="required:请输入经办机构编码" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>经办机构编码名称：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="" name="agenname"
							data-validate="required:请输入经办机构名称" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>级别：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="" name="grade"
							data-validate="required:请输入经办机构名称" />
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
