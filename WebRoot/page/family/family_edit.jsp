<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/base.jsp"%>
<%
String familycode = request.getParameter("id");
Family model = new Family(familycode);
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
			<strong><span class="icon-pencil-square-o"></span>修改家庭档案信息</strong>
		</div>
		<div class="body-content">
			<form id="form-add" method="post" class="form-x"
				action="<%=path%>/system/FamilyServlet?m=edit">
			   <div class="form-group">
					<div class="label">
						<label>家庭编号：</label>
					</div>
					<div class="field">
					<input type="text" class="input w50" value="<%=model.getFamilycode() %>" name="familycode"
							data-validate="required:请输入家庭编号"  readonly="readonly" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>县级编号：</label>
					</div>
					<div class="field">
					<input type="text" class="input w50" value="<%=model.getCountycode() %>" name="countycode"
							data-validate="required:请输入县级编号"  readonly="readonly" />
						<div class="tips"></div>
					</div>

				</div>
				<div class="form-group">
					<div class="label">
						<label>乡镇编号：</label>
					</div>
					<div class="field">
					<input type="text" class="input w50" value="<%=model.getTowncode() %>" name="towncode"
							data-validate="required:请输入乡镇编号"  readonly="readonly" />
						<div class="tips"></div>
					</div>

				</div>
				<div class="form-group">
					<div class="label">
						<label>村编号：</label>
					</div>
					<div class="field">
					<input type="text" class="input w50" value="<%=model.getVillagecode() %>" name="villagecode"
							data-validate="required:请输入村编号"  readonly="readonly" />
						<div class="tips"></div>
					</div>

				</div>
				<div class="form-group">
					<div class="label">
						<label>组编号：</label>
					</div>
					<div class="field">
					<input type="text" class="input w50" value="<%=model.getGroupcode() %>" name="groupcode"
							data-validate="required:请输入组编号"  readonly="readonly" />
						<div class="tips"></div>
					</div>

				</div>
				
				<div class="form-group">
					<div class="label">
						<label>户属性：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=model.getResidence() %>" name="residence"
							data-validate="required:请输入户属性" readonly="readonly"/>
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>户主姓名：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=model.getHolderName() %>" name="holderName"
							data-validate="required:请输入户主姓名" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>家庭人口数：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=model.getFamilyNum() %>" name="familyNum"
							data-validate="required:请输入家庭人口数" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>农业人口数：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=model.getAgricultureNum() %>" name="AgricultureNum"
							data-validate="required:请输入农业人口数" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>家庭住址：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=model.getAddress() %>" name="address"
							data-validate="required:请输入家庭住址" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>创建档案时间时间：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=model.getTime() %>" name="time"
							data-validate="required:请输入创建档案时间时间"  readonly="readonly"/>
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>登记员：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=model.getRegistrarName() %>" name="registrarName"
							data-validate="required:请输入登记员" readonly="readonly" />
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
