package com.gxuwz.medical.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gxuwz.medical.domain.role.Role;
/**
 * 角色管理控制模块
 * @author 演示
 *
 */
public class RoleServlet extends BaseServlet {
  
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String m =req.getParameter("m");
		if("list".equals(m)){
			list(req, resp);
		}else if("add".equals(m)){
			add(req, resp);
		}else if("edit".equals(m)){
			edit(req, resp);
		}else if("input".equals(m)){
			process(req, resp, "/page/role/role_add.jsp");
		}else if("get".equals(m)){
			process(req, resp, "/page/role/role_edit.jsp");
		}else if("del".equals(m)){
			del(req, resp);
			list(req, resp);
		}
	}
	private void list(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		  process(req, resp, "/page/role/role_list.jsp");
	}
	private void edit(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		//1：接收页面参数
		String roleid=req.getParameter("roleid");
		String rolename=req.getParameter("rolename");
		//页面权限复选框勾选的值
		String fid=req.getParameter("fid");
		//2:实例化角色Role
		try{
			String[]menuids=null;
			if(fid!=null&&!"".equals(fid)){
				menuids=fid.split(",");
			}
		    Role role=new Role(roleid,rolename);
		    role.editRole(menuids);
		    list(req, resp);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void add(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		//1：接收页面参数
		String roleid=req.getParameter("roleid");
		String rolename=req.getParameter("rolename");
		//页面权限复选框勾选的值
		String fid=req.getParameter("fid");
		//2:实例化角色Role
		try{
		
			String[]menuids=null;
			if(fid!=null&&!"".equals(fid)){
				menuids=fid.split(",");
			}
		    Role role=new Role(roleid,rolename);
		    role.addRole(rolename, menuids);
		    list(req, resp);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void del(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		try{//1：接收页面参数
				String roleid=req.getParameter("roleid");
				//2:实例化角色Role
				Role role=new Role();
				//3：删除记录
				role.delRole(roleid);
		}catch(Exception e){
			e.printStackTrace(); 
		}
	}
    
}
