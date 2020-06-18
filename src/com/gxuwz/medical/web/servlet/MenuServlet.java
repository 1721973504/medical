package com.gxuwz.medical.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.gxuwz.medical.domain.menu.Menu;
import com.gxuwz.medical.domain.menu.MenuTree;

/**
 * 权限树
 * 
 * @author 演示
 * 
 */
public class MenuServlet extends BaseServlet {

	private static final long serialVersionUID = -6080124219956648275L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String m = req.getParameter("m");
		if ("tree".equals(m)) {
			tree(req, resp);
		} else if ("list".equals(m)) {
			process(req, resp, "/page/menu/menu_list.jsp");
		} else if ("input".equals(m)) {
			process(req, resp, "/page/menu/menu_add.jsp");
		} else if ("add".equals(m)) {
			add(req, resp);
		} else if ("edit".equals(m)) {
			edit(req, resp);
		} else if ("del".equals(m)) {
			del(req, resp);
		} else if ("get".equals(m)) {
			process(req, resp, "/page/menu/menu_edit.jsp");
		}
	}

	/**
	 * 构造不同权限树
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void tree(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String treeID = req.getParameter("treeID");
		String roleid = req.getParameter("roleid");
		try {
			MenuTree tree = null;
			if (roleid == null || "".equals(roleid)) {
				tree = new MenuTree(treeID);
			} else {
				tree = new MenuTree(treeID, roleid);
			}

			String respBody = JSON.toJSONString(tree.getChildNodeList());
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			out.print(respBody);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加子一级菜单
	 * 
	 * @param request
	 * @param response
	 * @param path
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 包含ID+level组合的参数值
			String chooseid = request.getParameter("chooseid");
			String menupid = chooseid.split("-")[0];
			String plevel = chooseid.split("-")[1];
			String menuname = request.getParameter("menuname");
			String url = request.getParameter("url");
			// 实例化Menu
			int level = Integer.parseInt(plevel);
			Menu menu = new Menu(menuname, url);
			// 添加到父节点+级别
			menu.addMenu(menupid, level);
			process(request, response, "/page/menu/menu_list.jsp");
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	protected void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 包含ID+level组合的参数值
			String menuid = request.getParameter("menuid");
			String menuname = request.getParameter("menuname");
			String url = request.getParameter("url");
			// 实例化Menu
			Menu menu = new Menu(menuid);
			// 修改信息
			menu.editMenu(menuname, url);

			process(request, response, "/page/menu/menu_list.jsp");
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	protected void del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String menuid = request.getParameter("id");
			// 实例化Menu
			Menu menu = new Menu();
			// 删除信息
			menu.delMenu(menuid);
			process(request, response, "/page/menu/menu_list.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
