package com.gxuwz.medical.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.flow.InsideSubRoutineFlowContext;

import com.gxuwz.medical.domain.area.Area;
import com.gxuwz.medical.domain.institution.Institution;

/**
 * 农合经办机构管理控制处理模块
 * @author 演示
 *
 */
public class InstitutionServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String  m=request.getParameter("m");//动作类型参数
		if("list".equals(m)){
			process(request, response, "/page/institution/institution_list.jsp");
		}else if("input".equals(m)){
			process(request, response, "/page/institution/institution_add.jsp");
		}else if("get".equals(m)){
			process(request, response, "/page/institution/institution_edit.jsp");
		}else if("add".equals(m)){
			add(request, response);
			process(request, response, "/page/institution/institution_list.jsp");
		}
	}
	/**
	 * 添加机构方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		 //接收页面传递过来的参数
		 String areacode=request.getParameter("areacode");
		 String agencode=request.getParameter("agencode");
		 String agenname=request.getParameter("agenname");//有可能出现中文乱码
		 System.out.println("agenname:"+agenname);
		 System.out.println("agencode:"+agencode);
		 //实例化Institution类
		 Institution institution =new Institution();
		 int grade=areacode.length()==6?1:2;
		 
		 System.out.println("grade:"+grade);
		 //调用添加方法
		 try{
			 Area area =new Area(areacode);
			 institution.addInst(area, agencode, agenname, grade);
		 }catch(Exception e){
			 error(request, response);
		 }
		 
	}

}
