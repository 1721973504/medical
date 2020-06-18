package com.gxuwz.medical.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 抽象基类
 * 
 * @author 演示
 * 
 */
public abstract class BaseServlet extends HttpServlet {

	private static final long serialVersionUID = 8196938314965941620L;

	protected void process(HttpServletRequest request,
			HttpServletResponse response, String path) throws ServletException,
			IOException {

		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);

	}
	protected void error(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
		rd.forward(request, response);

	}
}
