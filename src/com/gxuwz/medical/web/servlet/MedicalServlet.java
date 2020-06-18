package com.gxuwz.medical.web.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gxuwz.medical.domain.area.Area;
import com.gxuwz.medical.domain.chronicdis.Chronicdis;
import com.gxuwz.medical.domain.institution.Institution;
import com.gxuwz.medical.domain.medical.Medical;

/**
 *医疗机构管理控制处理模块
 * @author 演示
 *
 */
public class MedicalServlet extends BaseServlet {

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
			process(request, response, "/page/medical/medical_list.jsp");
		}else if("input".equals(m)){
			process(request, response, "/page/medical/medical_add.jsp");
		}else if("get".equals(m)){
			process(request, response, "/page/medical/medical_edit.jsp");
		}else if("add".equals(m)){
			add(request, response);
			process(request, response, "/page/medical/medical_list.jsp");
		}
		else if("add".equals(m)){
			this.add(request, response);
		}else if("edit".equals(m)){
			this.edit(request, response);
		}else if("del".equals(m)){
			this.del(request, response);
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
		String jgbm=request.getParameter("jgbm");
		String zzjgbm=request.getParameter("zzjgbm");
		String jgmc=request.getParameter("jgmc");
		String dqbm=request.getParameter("dqbm");
		String areacode=request.getParameter("areacode");
		String lsgx=request.getParameter("lsgx");
		String jgjb=request.getParameter("jgjb");
		String sbddlx=request.getParameter("sbddlx");
		String pzddlx=request.getParameter("pzddlx");
		String ssjjlx=request.getParameter("ssjjlx");
		String wsjgdl=request.getParameter("wsjgdl");
		System.out.println(wsjgdl);
		String wsjgxl=request.getParameter("wsjgxl");
		String zgdw=request.getParameter("zgdw");
		String kysjstr=request.getParameter("kysj");//日期
		String frdb=request.getParameter("frdb");
		String zczjnum=request.getParameter("zczj");//数值
		System.out.println("jgjb:"+jgjb);
		Date  kysj=new java.util.Date();
		
		double zczj=Double.parseDouble(zczjnum);

		 //调用添加方法
		 try{
			 Medical model=new Medical(jgbm, zzjgbm, jgmc, dqbm, areacode, lsgx, jgjb, sbddlx, pzddlx, ssjjlx, wsjgdl, wsjgxl, zgdw, kysj, frdb, zczj);
			 model.add();
		 }catch(Exception e){
			 
			 error(request, response);
		 }
		 
	}

	private void edit(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{

	  	  //1：接收参数
		String jgbm=request.getParameter("jgbm");
		String zzjgbm=request.getParameter("zzjgbm");
		String jgmc=request.getParameter("jgmc");
		String dqbm=request.getParameter("dqbm");
		String areacode=request.getParameter("areacode");
		String lsgx=request.getParameter("lsgx");
		System.out.println("lsgx:"+lsgx);
		String jgjb=request.getParameter("jgjb");
		System.out.println("jgjb:"+jgjb);
		String sbddlx=request.getParameter("sbddlx");
		String pzddlx=request.getParameter("pzddlx");
		String ssjjlx=request.getParameter("ssjjlx");
		String wsjgdl=request.getParameter("wsjgdl");
		String wsjgxl=request.getParameter("wsjgxl");
		String zgdw=request.getParameter("zgdw");
		String kysjstr=request.getParameter("kysj");//日期
		String frdb=request.getParameter("frdb");
		String zczjnum=request.getParameter("zczj");//数值
		
		Date  kysj=new java.util.Date();
		
		double zczj=Double.parseDouble(zczjnum);
		System.out.println("zczj:"+zczj);
	  	  //2:构造新慢病信息对象
	  	Medical model=new Medical(jgbm, zzjgbm, jgmc, dqbm, areacode, lsgx, jgjb, sbddlx, pzddlx, ssjjlx, wsjgdl, wsjgxl, zgdw, kysj, frdb, zczj);
	  	  //3：调用保存的方法
	  	  try{
	  		  model.edit();
	  		process(request, response, "/page/medical/medical_list.jsp");
	  	  }catch(Exception e){
	  		  error(request, response);
	  	  }
		
	  
		}
	private void del(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{

	  	  //1：接收参数
	  	  String jgbm=request.getParameter("id");
	  	  
	  	  //2:构造新慢病信息对象
	  	Medical model=new Medical();
	  	  try{
	  		  model.del(jgbm);
	  		process(request, response, "/page/medical/medical_list.jsp");
	  	  }catch(Exception e){
	  		  error(request, response);
	  	  }
		
	  
		}

	
}
