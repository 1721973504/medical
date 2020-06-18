package com.gxuwz.medical.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gxuwz.medical.domain.area.Area;

/**
 * 行政区域管理控制模块
 * 
 * @author k88
 * 
 */
public class AreaServlet extends BaseServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String m = req.getParameter("m");
		if ("list".equals(m)) {
			process(req, resp, "/page/area/area_list.jsp");
		} else if ("input".equals(m)) {
			process(req, resp, "/page/area/area_add.jsp");
		} else if ("get".equals(m)) {
			process(req, resp, "/page/area/area_edit.jsp");
		} else if("add".equals(m)){
		    add(req, resp);
			process(req, resp, "/page/area/area_list.jsp");
		}else if("edit".equals(m)){
		    edit(req, resp);
			process(req, resp, "/page/area/area_list.jsp");
		}else if("del".equals(m)){
		    del(req, resp);
			process(req, resp, "/page/area/area_list.jsp");
		}else {
			error(req, resp);
		}
	}

	/**
	 * 添加行政区域
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void add(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			// 接收行政区域编码+行政区域名称，行政区域名称有可能出现中文乱码
			String areapid = req.getParameter("areacode");
			String areaname = req.getParameter("areaname");
			// 实例化Area，并调用添加子行政区域方法
			Area area = new Area();
			area.addArea(areapid, areaname);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	private void edit(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			// 接收行政区域编码+行政区域名称，行政区域名称有可能出现中文乱码
			String areacode = req.getParameter("areacode");
			String areaname = req.getParameter("areaname");
			Area area = new Area(areacode);
			area.editArea(areaname);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	private void del(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			// 接收行政区域编码+行政区域名称，行政区域名称有可能出现中文乱码
			String areacode = req.getParameter("id");
			Area area = new Area(areacode);
			area.delArea();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
}
