<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/base.jsp" %>
<%
String policyYear=request.getParameter("id");
Policy model = new Policy(policyYear);
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
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>修改慢性病</strong></div>
  <div class="body-content">
    <form id="form-add" method="post" class="form-x" action="<%=path%>/system/PolicyServlet?m=edit">  
      <input id="fid" name="fid" value="" type="hidden"/>
      <div class="form-group">
        <div class="label">
          <label>年度：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="<%=model.getPolicyYear() %>" readonly="readonly" name="policyYear" data-validate="required:请输入年度" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>封顶线：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="<%=model.getPolicyMoney() %>" name="policyMoney" data-validate="required:请输入封顶线" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>报销比例：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="<%=model.getProportion() %>" name="proportion" data-validate="required:请输入报销比例" />
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