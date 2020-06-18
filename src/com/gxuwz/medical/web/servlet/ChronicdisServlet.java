package com.gxuwz.medical.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gxuwz.medical.domain.chronicdis.Chronicdis;

/**
 * 慢病管理请求处理模块类
 * @author 演示
 *
 */
public class ChronicdisServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String m=req.getParameter("m");//请求处理动作类型:list:显示列表；get：根据ID读取记录;input:新增数据;add:保存新记录；edit：更新记录
	    if("list".equals(m)){
	      process(req, resp, "/page/chronicdis/chronicdis_list.jsp");
	    }else if("input".equals(m)){
		  process(req, resp, "/page/chronicdis/chronicdis_add.jsp");
		}else if("get".equals(m)){
			  process(req, resp, "/page/chronicdis/chronicdis_edit.jsp");
		} 
		else if("add".equals(m)){
			this.add(req, resp);
		}else if("edit".equals(m)){
			this.edit(req, resp);
		}else if("del".equals(m)){
			this.del(req, resp);
		}
	
	}
	
	private void add(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{

  	  //1：接收参数
  	  String illcode=req.getParameter("illcode");
  	  String illname=req.getParameter("illname");
  	  String pycode=req.getParameter("pycode");
  	  String wbcode=req.getParameter("wbcode");
  	  //2:构造新慢病信息对象
  	  Chronicdis model=new Chronicdis(illcode, illname, pycode, wbcode);
  	  //3：调用保存的方法
  	  try{
  		  model.add();
  		process(req, resp, "/page/chronicdis/chronicdis_list.jsp");
  	  }catch(Exception e){
  		  error(req, resp);
  	  }
	
  
	}
	private void edit(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{

	  	  //1：接收参数
	  	  String illcode=req.getParameter("illcode");
	  	  String illname=req.getParameter("illname");
	  	  String pycode=req.getParameter("pycode");
	  	  String wbcode=req.getParameter("wbcode");
	  	  //2:构造新慢病信息对象
	  	  Chronicdis model=new Chronicdis(illcode, illname, pycode, wbcode);
	  	  //3：调用保存的方法
	  	  try{
	  		  model.edit();
	  		process(req, resp, "/page/chronicdis/chronicdis_list.jsp");
	  	  }catch(Exception e){
	  		  error(req, resp);
	  	  }
		
	  
		}
	private void del(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{

	  	  //1：接收参数
	  	  String illcode=req.getParameter("id");
	  	  
	  	  //2:构造新慢病信息对象
	  	  Chronicdis model=new Chronicdis();
	  	  try{
	  		  model.del(illcode);
	  		process(req, resp, "/page/chronicdis/chronicdis_list.jsp");
	  	  }catch(Exception e){
	  		  error(req, resp);
	  	  }
		
	  
		}

}
