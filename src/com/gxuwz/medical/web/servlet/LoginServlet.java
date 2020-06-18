package com.gxuwz.medical.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gxuwz.medical.domain.menu.Menu;
import com.gxuwz.medical.domain.role.Role;
import com.gxuwz.medical.domain.user.User;
import com.gxuwz.medical.exception.UserNotFoundException;
/**
 * 登录验证控制模块
 * @author 演示
 *
 */

public class LoginServlet extends BaseServlet {

	private static final long serialVersionUID = 7279138098299110478L;
	//private static final Logger LOG = LogManager.getLogger(LoginServlet.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			login(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String path="login.jsp";
		List<String> list = new ArrayList<String>();
		//LOG.info("用户登录");
		try{
			String userid=request.getParameter("userid");
			String pwd   =request.getParameter("pwd");
			String fullname   =request.getParameter("fullname");
			User user=new User(userid,pwd);
			request.getSession().setAttribute("fullname", user.getFullname());
			for(Role role:user.getRoles()){
				Role role2 = new Role();
				list = role2.findAll(role.getRoleid());
				for(Menu menu:role.getMenus()){
					String name=menu.getMenuid();
					String value=menu.getMenuid();
					request.getSession().setAttribute(name, value);
				}
			}
			request.getSession().setAttribute("listRight", list);
			path="index.jsp";
		}catch(UserNotFoundException e){
			//LOG.info(e.getMessage(),e);
			path="login.jsp";
		}
		 //跳转页面
		process(request, response, path);
		
	}


}
