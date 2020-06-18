package com.gxuwz.medical.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gxuwz.medical.domain.user.User;

/**
 * 用户管理模块
 * @author 演示
 *
 */
public class UserServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String m = req.getParameter("m");
		if ("list".equals(m)) {
			process(req, resp, "/page/user/user_list.jsp");
		}else if ("input".equals(m)) {
			process(req, resp, "/page/user/user_add.jsp");
		}else if ("get".equals(m)) {
			process(req, resp, "/page/user/user_edit.jsp");
		}else if ("add".equals(m)) {
			add(req, resp);
		}else if ("edit".equals(m)) {
			edit(req, resp);
		}else if ("del".equals(m)) {
			del(req, resp);
		}else{
			error(req, resp);
		}  
	}
	protected void add(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String userid = req.getParameter("userid");
		String pwd = req.getParameter("pwd");
		String fullname = req.getParameter("fullname");
		String agencode = req.getParameter("agencode");
		String status ="1";//默认正常
		String[] roleids = req.getParameterValues("roleids");
		//1:实例化User
		User user =new User(userid, pwd, fullname, status, agencode);
		//2:调用方法
		try{
			user.addUser(roleids);
			process(req, resp, "/page/user/user_list.jsp");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	protected void edit(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String userid = req.getParameter("userid");
		String pwd = req.getParameter("pwd");
		String fullname = req.getParameter("fullname");
		String agencode = req.getParameter("agencode");
		String status ="1";//默认正常
		String[] roleids = req.getParameterValues("roleids");
		//1:实例化User
		User user =new User(userid, pwd, fullname, status, agencode);
		//2:调用方法
		try{
			user.editUser(roleids);
			process(req, resp, "/page/user/user_list.jsp");
		}catch(Exception e){
			e.printStackTrace();
		}	
		
	}
	protected void del(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String userid = req.getParameter("id");
		//1:实例化User
		User user =new User();
		//2:调用方法
		try{
			System.out.print(userid);
			user.delUser(userid);
			process(req, resp, "/page/user/user_list.jsp");
		}catch(Exception e){
			e.printStackTrace();
		}	
		
	}

}
