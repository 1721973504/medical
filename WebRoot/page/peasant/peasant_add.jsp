<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/base.jsp"%>
<%
String idcard=request.getParameter("id");
Peasant model = new Peasant(idcard);
familyDao familydao=new familyDao();
List<Family> familyList=familydao.findAll();
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
			<strong><span class="icon-pencil-square-o"></span>增加参合农民信息</strong>
		</div>
		<div class="body-content">
			<form id="form-add" method="post" class="form-x"
				action="<%=path%>/system/PeasantServlet?m=add">
				 <div class="form-group">
					<div class="label">
						<label>家庭编号：</label>
					</div>
					<div class="field">
					<select id="code" name="familycode" class="input w50">
							<%
						  for(Family f:familyList){ 
						
						  %>
							<option value="<%=f.getFamilycode() %>">
								
								<%=f.getFamilycode() %></option>
							<%} %>
						</select>
					<div class="tips"></div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="label">
						<label>医疗证卡号：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="" name="MedicalNum"
							data-validate="required:请输入医疗证卡号" />
						<div class="tips"></div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="label">
						<label>姓名：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="" name="name"
							data-validate="required:请输入姓名" />
						<div class="tips"></div>
					</div>
				</div>
  			   <div class="form-group">
					<div class="label">
						<label>与户主关系：</label>
					</div>
					<div class="field">
							<select id="Relations" name="Relations" class="input w50">
							<option value="0">请选择与户主关系</option>
							<option value="户主">户主</option>
							<option value="配偶">配偶</option>	
							<option value="子">子</option>	
							<option value="女">女</option>	
							<option value="父">父</option>	
							<option value="母">母</option>	
							<option value="孙子">孙子</option>	
							<option value="孙女">孙女</option>		
							<option value="兄">兄</option>	
							<option value="弟">弟</option>	
							<option value="姐">姐</option>	
							<option value="妹">妹</option>					
							</select>
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
						<label>性别：</label>
					</div>
					<div class="field">
						<select id="sex" name="sex" class="input w50">
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
							<select id="healthy" name="healthy" class="input w50">
							<option value="0">请选择健康状况</option>
							<option value="优秀">优秀</option>
							<option value="良好">良好</option>	
							<option value="差">差</option>						
							</select>
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>民族：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="" name="nation"
							data-validate="required:请输入民族" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>文化程度：</label>
					</div>
					<div class="field">
							<select id="education" name="education" class="input w50">
							<option value="0">请选择文化程度</option>
							<option value="小学">小学</option>
							<option value="初中">初中</option>	
							<option value="高中">高中</option>	
							<option value="中专">中专</option>	
							<option value="大专">大专</option>	
							<option value="本科">本科</option>	
							<option value="硕士">硕士</option>	
							<option value="博士">博士</option>						
							</select>
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>年龄：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="" name="age"
							data-validate="required:请输入年龄" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>出生日期：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="" name="birthday"
							data-validate="required:请输入出生日期" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>人员属性：</label>
					</div>
					<div class="field">
							<select id="attribute" name="attribute" class="input w50">
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
							<select id="agricultural" name="agricultural" class="input w50">
							<option value="0">选择是否农业户口</option>
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
						<input type="text" class="input w50" value="" name="profession"
							data-validate="required:请输入职业" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>工作单位：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="" name="workunit"
							data-validate="required:请输入工作单位" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>联系电话：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="" name="phone"
							data-validate="required:请输入联系电话" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>常住地址：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="" name="address"
							data-validate="required:请输入常住地址" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>联系方式：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="" name="email"
							data-validate="required:请输入联系方式" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>年度：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="" name="policyYear"
							data-validate="required:请输入年度" />
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
