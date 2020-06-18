package com.gxuwz.medical.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.flow.InsideSubRoutineFlowContext;

import com.gxuwz.medical.domain.family.Family;

/**
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException
 *
 */
public class FamilyServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(req, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		String  m=req.getParameter("m");//动作类型参数
		if("list".equals(m)){
			process(req, response, "/page/family/family_list.jsp");
		}else if("input".equals(m)){
			process(req, response, "/page/family/family_add.jsp");
		}else if("get".equals(m)){
			process(req, response, "/page/family/family_edit.jsp");
		}else if("add".equals(m)){
			add(req, response);
			process(req, response, "/page/family/family_list.jsp");
		}else if("edit".equals(m)){
		    edit(req, response);
			process(req, response, "/page/family/family_list.jsp");
		}else if("del".equals(m)){
		    del(req, response);
			process(req, response, "/page/family/family_list.jsp");
		}else {
			error(req, response);
		}
	}
	/**
	 * 添加
	 * @param req
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void add(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException{
		 //接收页面传递过来的参数
		 String familycode=req.getParameter("familycode");
		 String countycode=req.getParameter("countycode");
		 String towncode=req.getParameter("towncode");
		 String villagecode=req.getParameter("villagecode");
		 String groupcode=req.getParameter("groupcode");
		 String residence=req.getParameter("residence");
		 String holderName=req.getParameter("holderName");
		 String idcard=req.getParameter("idcard");
		 String familyNum=req.getParameter("familyNum");
		 String AgricultureNum=req.getParameter("AgricultureNum");
		 String address=req.getParameter("address");
		 String time=req.getParameter("time");
		 String registrarName=req.getParameter("registrarName");		
		 //调用添加方法
		 Family family =new Family(familycode,countycode,towncode,villagecode,groupcode,
					residence,holderName,idcard,familyNum,AgricultureNum,address,time,registrarName);
		 try{	 
			 family.addFamily(familycode,countycode,towncode,villagecode,groupcode,
						residence,holderName,idcard,familyNum,AgricultureNum,address,time,registrarName);
		 }catch(Exception e){
				e.printStackTrace();
		 }
		 
	}
	private void edit(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException{
		 try{	
			 String familycode=req.getParameter("familycode");
			 String holderName=req.getParameter("holderName");
			 String familyNum=req.getParameter("familyNum");
			 String AgricultureNum=req.getParameter("AgricultureNum");
			 String address=req.getParameter("address");
			 String time=req.getParameter("time");
			 String registrarName=req.getParameter("registrarName");	
			 //有可能出现中文乱码
			 Family family = new Family(familycode);
			 family.editFamily(holderName,familyNum,AgricultureNum,address,time,registrarName);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	private void del(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		
			 String familycode = req.getParameter("id");
			 Family family = new Family();			
		try {
				 family.delFamily(familycode);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
}