<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/base.jsp"%>
<%
 String id=request.getParameter("id");
 Medical model =new Medical(id);
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
			<strong><span class="icon-pencil-square-o"></span>修改慢性病息</strong>
		</div>
		<div class="body-content">
			<form id="form-add" method="post" class="form-x"
				action="<%=path%>/system/MedicalServlet?m=edit">
				
				<div class="form-group">
					<div class="label">
						<label>机构编码：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" readonly="readonly" value="<%=model.getJgbm() %>" name="jgbm"
							data-validate="required:请输入机构编码" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>组织机构名称：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" readonly="readonly" value="<%=model.getZzjgbm() %>" name="zzjgbm"
							data-validate="required:请输入疾病名称" />
						<div class="tips"></div>
					</div>
				</div>

	<div class="form-group">
					<div class="label">
						<label>机构名称：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=model.getJgmc() %>" name="jgmc"
							data-validate="required:请输入拼音码" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>地区编码：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" readonly="readonly" value="<%=model.getDqbm() %>" name="dqbm"
							data-validate="required:请输入五笔码" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>行政区域编码：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" readonly="readonly" value="<%=model.getAreacode() %>" name="areacode"
							data-validate="required:请输入五笔码" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>隶属关系：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=model.getLsgx() %>" name="lsgx"
							data-validate="required:请输入五笔码" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>机构级别：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" readonly="readonly" value="<%=model.getJgjb() %>" name="jgjb"
							data-validate="required:请输入五笔码" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>申请定点类型：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" readonly="readonly" value="<%=model.getSbddlx() %>" name="sbddlx"
							data-validate="required:请输入五笔码" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>批准定点类型：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" readonly="readonly" value="<%=model.getPzddlx() %>" name="pzddlx"
							data-validate="required:请输入五笔码" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>所属经济类型：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" readonly="readonly" value="<%=model.getSsjjlx() %>" name="ssjjlx"
							data-validate="required:请输入五笔码" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>卫生机构大类：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=model.getWsjgdl() %>" name="wsjgdl"
							data-validate="required:请输入五笔码" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>卫生机构小类：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=model.getWsjgxl() %>" name="wsjgxl"
							data-validate="required:请输入五笔码" />
						<div class="tips"></div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="label">
						<label>主管单位：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" readonly="readonly" value="<%=model.getZgdw() %>" name="zgdw"
							data-validate="required:请输入五笔码" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>开业时间：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=model.getKysj() %>" name="kysj"
							data-validate="required:请输入五笔码" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>法人代表：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=model.getFrdb() %>" name="frdb"
							data-validate="required:请输入五笔码" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>注册资金：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="<%=model.getZczj() %>" name="zczj"
							data-validate="required:请输入五笔码" />
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
