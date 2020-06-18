<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/base.jsp"%>
<%
String idcard=request.getParameter("id");
Peasant model = new Peasant(idcard);
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
			<strong><span class="icon-pencil-square-o"></span>修改信息</strong>
		</div>
		<div class="body-content">
			<form id="form-add" method="post" class="form-x"
				action="<%=path%>/system/PeasantServlet?m=edit">
				<div class="form-group">
					<div class="label">
						<label>家庭编号：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" readonly="readonly" value="<%=model.getFamilycode() %>" name="familycode"
							data-validate="required:请输入家庭编号" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>农合证号：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" readonly="readonly" value="<%=model.getCardNum() %>" name="cardNum"
							data-validate="required:请输入农合证号" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>医疗证卡号：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" readonly="readonly" value="<%=model.getMedicalNum() %>" name="MedicalNum"
							data-validate="required:请输入医疗证卡号" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group"> 
					<div class="label">
						<label>户内编号：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=model.getCode() %>"  readonly="readonly" name="code"
							data-validate="required:请输入户内编号" />
						<div class="tips"></div>
					</div>
				</div>

				<div class="form-group">
					<div class="label">
						<label>姓名：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=model.getName() %>" name="name"
							data-validate="required:请输入姓名" />
						<div class="tips"></div>
					</div>
				</div>			
				<div class="form-group">
					<div class="label">
						<label>与户主关系：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=model.getRelations() %>" name="Relations"
							data-validate="required:请输入与户主关系" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>身份证号：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=model.getIdcard() %>"  readonly="readonly" name="idcard"
							data-validate="required:请输入身份证号" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>性别：</label>
					</div>
					<div class="field">			
							<select id="sex" name="sex" value="<%=model.getSex() %>"  class="input w50">
							<option value="0">选择性别</option>
							<option value="男">男</option>
							<option value="女">女</option>						
							</select>
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>健康状况：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=model.getHealthy() %>" name="healthy"
							data-validate="required:请输入健康状况" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>民族：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=model.getNation() %>" name="nation"
							data-validate="required:请输入民族" />
						<div class="tips"></div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="label">
						<label>文化程度：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=model.getEducation() %>" name="education"
							data-validate="required:请输入文化程度" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>年龄：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=model.getAge() %>" name="age"
							data-validate="required:请输入年龄" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>出生日期：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=model.getBirthday() %>" name="birthday"
							data-validate="required:请输入出生日期" />
						<div class="tips"></div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="label">
						<label>人员属性：</label>
					</div>
					<div class="field">
							<select id="attribute" name="attribute" value="<%=model.getAttribute() %>"  class="input w50">
							<option value="0">选择人员属性</option>
							<option value="正式职工">正式职工</option>
							<option value="合同工">合同工</option>
							<option value="兼职人员">兼职人员</option>
							<option value="临时工">临时工</option>
							</select>
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>是否农业户口：</label>
					</div>
					<div class="field">
							<select id="agricultural" name="agricultural" value="<%=model.getAgricultural() %>"  class="input w50">
						
							<option value="农业户口">农业户口</option>
							<option value="非农业户口">非农业户口</option>						
							</select>
						<div class="tips"></div>
					</div>
				</div>	
				<div class="form-group">
					<div class="label">
						<label>职业：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=model.getProfession() %>" name="profession"
							data-validate="required:请输入婚姻状况" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>工作单位：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=model.getWorkunit() %>" name="workunit"
							data-validate="required:请输入工作单位" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>联系电话：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=model.getPhone() %>" name="phone"
							data-validate="required:请输入联系电话" />
						<div class="tips"></div>
					</div>
				</div>
  			  	<div class="form-group">
					<div class="label">
						<label>常住地址：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=model.getAddress() %>" name="address"
							data-validate="required:请输入常住地址址" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>联系方式：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=model.getEmail() %>" name="email"
							data-validate="required:请输入联系方式" />
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
