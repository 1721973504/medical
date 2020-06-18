package com.gxuwz.medical.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gxuwz.medical.dao.PeasantDao;
import com.gxuwz.medical.domain.card.Card;
import com.gxuwz.medical.domain.peasant.Peasant;
/**
 * 
 * @author
 *
 */
public class CardServlet extends BaseServlet {

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
	      process(req, resp, "/page/card/card_list.jsp");
	    }else if("input".equals(m)){
		  process(req, resp, "/page/card/card_add.jsp");
		}else if("get".equals(m)){
			  process(req, resp, "/page/card/card_edit.jsp");
		} 
		else if("add".equals(m)){
			this.add(req, resp);
		}else if("edit".equals(m)){
			this.edit(req, resp);
		}else if("del".equals(m)){
			this.del(req, resp);
		}else if("searchs".equals(m)){
			this.searchs(req, resp);
		}	
	
	}
	
	private void add(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{

  	  //1：接收参数
	  String MedicalCard=req.getParameter("MedicalCard");
	  System.out.println(MedicalCard);
	  String cardNum =req.getParameter("cardNum");
	  System.out.println(cardNum);
  	  String name=req.getParameter("name");
  	  System.out.println(name);
  	  String idcard=req.getParameter("idcard");
  	  System.out.println(idcard);
  	  String MedicalName=req.getParameter("MedicalName");
  	  System.out.println(MedicalName);
  	  String StartTime=req.getParameter("StartTime");
  	  System.out.println(StartTime);
  	  String EndTime=req.getParameter("EndTime");
  	  System.out.println(EndTime);
  	  //2:构造新农民档案信息对象
  	  Card model=new Card();
  	  //3：调用保存的方法
  	  try{
  		  model.add(MedicalCard,cardNum,name,idcard,MedicalName,StartTime,EndTime);
  		process(req, resp, "/page/card/card_list.jsp");
  	  }catch(Exception e){
  		  error(req, resp);
  	  }
	
  
	}
	private void edit(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{

	  	  //1：接收参数
		  String MedicalCard=req.getParameter("MedicalCard");
		  String cardNum =req.getParameter("cardNum");
	  	  String name=req.getParameter("name");
	  	  String idcard=req.getParameter("idcard");
	  	  String MedicalName=req.getParameter("MedicalName");
	  	  String StartTime=req.getParameter("StartTime");
	  	  String EndTime=req.getParameter("EndTime");
	  	  //2:构造新农民档案信息对象
	  	  Card model=new Card(MedicalCard,cardNum,name,idcard,MedicalName,StartTime,EndTime);
	  	  //3：调用保存的方法
	  	  try{
	  		  model.edit();
	  		process(req, resp, "/page/card/card_list.jsp");
	  	  }catch(Exception e){
	  		  error(req, resp);
	  	  }
		
	  
		}
	private void del(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{

	  	  //1：接收参数
	  	  String MedicalCard=req.getParameter("id");
	  	  
	  	  //2:构造新农民档案信息对象
	  	  Card model=new Card();
	  	  try{
	  		  model.del(MedicalCard);
	  		process(req, resp, "/page/card/card_list.jsp");
	  	  }catch(Exception e){
	  		  error(req, resp);
	  	  }
		}
	private void searchs(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
		String name=req.getParameter("name");
		
		PeasantDao dao = new PeasantDao();
		String sql = "select * from t_peasant where and name ='";
	    sql += name + "'";
	    //创建一个列表，调用dao的SearchName
		List<Peasant> Search = dao.Search(name);  
	    //保存查询的信息用setAttribute将值给SearchName    
	    req.setAttribute("Search", Search);
	    //转发请求  
	    req.getRequestDispatcher("/page/card/card_add.jsp").forward(req, resp);  

		}
}
