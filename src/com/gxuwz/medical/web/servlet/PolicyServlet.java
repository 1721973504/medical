package com.gxuwz.medical.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.gxuwz.medical.domain.policy.Policy;
public class PolicyServlet extends BaseServlet {
  
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		String  m=req.getParameter("m");//动作类型参数
		if("list".equals(m)){
			process(req, response, "/page/policy/policy_list.jsp");
		}else if("input".equals(m)){
			process(req, response, "/page/policy/policy_add.jsp");
		}else if("get".equals(m)){
			process(req, response, "/page/policy/policy_edit.jsp");
		}else if("add".equals(m)){
			add(req, response);
			process(req, response, "/page/policy/policy_list.jsp");
		}else if("edit".equals(m)){
		    edit(req, response);
			process(req, response, "/page/policy/policy_list.jsp");
		}else if("del".equals(m)){
		    del(req, response);
			process(req, response, "/page/policy/policy_list.jsp");
		}else {
			error(req, response);
		}
	}
	private void add(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		//1：接收页面参数
		String policyYear=req.getParameter("policyYear");
		String policyMoney=req.getParameter("policyMoney");
		String proportion=req.getParameter("proportion");
		//页面权限复选框勾选的值
		String fid=req.getParameter("fid");
		//2:实例化角色policy
		    Policy policy=new Policy(policyYear,policyMoney,proportion);
		try{
			policy.addPolicy(policyYear,policyMoney,proportion);
	        
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void edit(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException{
		try{
		//1：接收页面参数
		String policyYear=request.getParameter("policyYear");
		String policyMoney=request.getParameter("policyMoney");
		String proportion=request.getParameter("proportion");
		String fid=request.getParameter("fid");
		Policy policy=new Policy(policyYear);
		policy.editPolicy(policyMoney,proportion);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void del(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		     //1：接收页面参数
			String policyYear=req.getParameter("id");
			//2:实例化角色policy
			Policy policy=new Policy();
			//3：删除记录
			try{
				policy.delPolicy(policyYear);
		}catch(Exception e){
			e.printStackTrace(); 
		}
	}
    
}
