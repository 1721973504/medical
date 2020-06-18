package com.gxuwz.medical.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gxuwz.medical.dao.ApplyDao;
import com.gxuwz.medical.dao.CardDao;
import com.gxuwz.medical.domain.apply.Apply;
import com.gxuwz.medical.domain.apply.Applyperiod;
import com.gxuwz.medical.domain.card.Card;
import com.gxuwz.medical.tools.DateUtil;

/**
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException
 *
 */
public class ApplyServlet extends BaseServlet {
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
			process(req, response, "/page/apply/apply_list.jsp");
		}else if("get".equals(m)){
			process(req, response, "/page/apply/apply_print.jsp");
		}else if("input".equals(m)){
			process(req, response, "/page/apply/apply_add.jsp");
		}else if("add".equals(m)){
			try {
				add(req, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			process(req, response, "/page/apply/apply_show.jsp");
		}else if("addcheck".equals(m)){											
			process(req, response, "/page/apply/apply_check.jsp");
		}else if("addchecks".equals(m)){
			try {
				this.addchecks(req, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		process(req, response, "/page/apply/apply_showcheck.jsp");
		}else if("addpay".equals(m)){											
			process(req, response, "/page/apply/apply_pay.jsp");
		}else if("addpays".equals(m)){
			try {
				this.addpays(req, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		process(req, response, "/page/apply/apply_showpay.jsp");
		}else if("searchs".equals(m)){
			try {
				this.searchs(req, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("searchcheck".equals(m)){			
				try {
					this.searchcheck(req, response);
				} catch (Exception e) {					
					e.printStackTrace();
				}			
		}else if("searchpay".equals(m)){			
			try {
				this.searchpay(req, response);
			} catch (Exception e) {				
				e.printStackTrace();
			}		
		}else if("searchmedical".equals(m)){			
			try {
				this.searchmedical(req, response);
			} catch (Exception e) {				
				e.printStackTrace();
			}		
		}else if("searchareacode".equals(m)){			
			try {
				this.searchareacode(req, response);
			} catch (Exception e) {				
				e.printStackTrace();
			}		
		}else if("searchtotal".equals(m)){											
			process(req, response, "/page/apply/apply_statistic.jsp");
		}else if("del".equals(m)){
			this.del(req, response);
		}else if("show".equals(m)){
			process(req, response, "/page/apply/apply_show.jsp");
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
			throws Exception {
		 //接收页面传递过来的参数
		 String InvoiceNum=req.getParameter("InvoiceNum");
		 String MedicalName=req.getParameter("MedicalName");
		 String idcard=req.getParameter("idcard");
		 String Name=req.getParameter("Name"); 	
		 String total=req.getParameter("total");
		 String amount=req.getParameter("amount");
		 String Time=req.getParameter("Time");	
		 String CreatTime=req.getParameter("CreatTime");	
		 String areacode=req.getParameter("areacode");	
		 //调用添加方法
		 Apply apply =new Apply();
		//年份，默认为当前系统时间对应的年份
		 int policyYear=DateUtil.getYear(new java.util.Date());
	    //时间设置
		 Applyperiod applyperiod=new Applyperiod(policyYear);
	    
	    try {
	    	apply.addApply(InvoiceNum,MedicalName,idcard,Name,total,amount,Time,CreatTime,areacode);
	    } catch (Exception e) {
			e.printStackTrace();
			}
	}
	
	private void addchecks(HttpServletRequest request, HttpServletResponse response)throws Exception {
		 //接收页面传递过来的参数
		 String InvoiceNum=request.getParameter("InvoiceNum");
		 String MedicalName=request.getParameter("MedicalName");
		 String idcard=request.getParameter("idcard");
		 String Name=request.getParameter("Name"); 	
		 String total=request.getParameter("total");
		 String amount=request.getParameter("amount");
		 String Time=request.getParameter("Time");	
		 String CreatTime=request.getParameter("CreatTime");	
		 String areacode=request.getParameter("areacode");	
		 //调用添加方法
		 Apply apply =new Apply();
		//年份，默认为当前系统时间对应的年份
		 int policyYear=DateUtil.getYear(new java.util.Date());
	    //时间设置
		 Applyperiod applyperiod=new Applyperiod(policyYear);	  	   
	     apply.addApplyCheck(InvoiceNum,MedicalName,idcard,Name,total,amount,Time,CreatTime,areacode);
	}
	private void addpays(HttpServletRequest request, HttpServletResponse response)throws Exception {
		 //接收页面传递过来的参数
		 String InvoiceNum=request.getParameter("InvoiceNum");
		 String MedicalName=request.getParameter("MedicalName");
		 String idcard=request.getParameter("idcard");
		 String Name=request.getParameter("Name"); 	
		 String total=request.getParameter("total");
		 String amount=request.getParameter("amount");
		 String Time=request.getParameter("Time");	
		 String CreatTime=request.getParameter("CreatTime");	
		 String areacode=request.getParameter("areacode");	
		 //调用添加方法
		 Apply apply =new Apply();
		//年份，默认为当前系统时间对应的年份
		 int policyYear=DateUtil.getYear(new java.util.Date());
	    //时间设置
		 Applyperiod applyperiod=new Applyperiod(policyYear);	  	   
	     apply.addApplyPay(InvoiceNum,MedicalName,idcard,Name,total,amount,Time,CreatTime,areacode);
	}
	
	private void searchs(HttpServletRequest req,HttpServletResponse resp)throws Exception{
		 String idcard=req.getParameter("idcard");
		 String MedicalName=req.getParameter("MedicalName");
		 int policyYear=DateUtil.getYear(new java.util.Date());
		 String Time=req.getParameter("Time");
		 Applyperiod applyperiod = new Applyperiod(policyYear);
		 req.setAttribute("proportion", applyperiod.getProportion());
		
		 CardDao dao = new CardDao();
		 String sql = "select * from t_card where idcard like '"+idcard+"' AND MedicalName like '"+MedicalName+"' AND '"+Time+"'>=StartTime AND '"+Time+"'<=EndTime";
	    //创建一个列表，调用dao的SearchMessage
		List<Card> SearchMessage = dao.SearchMessage(idcard,MedicalName,Time);  
	    //保存查询的信息用setAttribute将值给SearchName    
		
	    req.setAttribute("SearchMessage", SearchMessage);
	   
	    //转发请求  
	    req.getRequestDispatcher("/page/apply/apply_add.jsp").forward(req, resp);  

		}
	
	
	private void searchcheck(HttpServletRequest request,HttpServletResponse resp)throws Exception{
		 String Name=request.getParameter("Name"); 
		 String endTime=request.getParameter("endTime");
		 int policyYear=DateUtil.getYear(new java.util.Date());
		 String startTime=request.getParameter("startTime");
		 String areacode=request.getParameter("areacode");
		 Applyperiod applyperiod = new Applyperiod(policyYear);
		 request.setAttribute("proportion", applyperiod.getProportion());
		
		 ApplyDao dao = new ApplyDao();
		 List<Apply> SearchCheck = dao.SearchCheck(areacode,Name,startTime,endTime);  
	    //保存查询的信息用setAttribute将值给SearchName    
		
		request.setAttribute("SearchCheck", SearchCheck);
	   
	    //转发请求  
		request.getRequestDispatcher("/page/apply/apply_check.jsp").forward(request, resp);  

		}
	private void searchpay(HttpServletRequest request,HttpServletResponse resp)throws Exception{
		 String Name=request.getParameter("Name"); 
		 String endTime=request.getParameter("endTime");
		 int policyYear=DateUtil.getYear(new java.util.Date());
		 String startTime=request.getParameter("startTime");
		 String areacode=request.getParameter("areacode");
		 Applyperiod applyperiod = new Applyperiod(policyYear);
		 request.setAttribute("proportion", applyperiod.getProportion());
		
		 ApplyDao dao = new ApplyDao();
		 String sql = "select * from t_applycheck where Name like '"+Name+"' AND CreatTime<='"+endTime+"' AND '"+startTime+"'<=CreatTime AND"
					+ " InvoiceNum not in(select InvoiceNum from t_applycheck) AND"
					+ " areacode like '"+areacode+"' ";
		List<Apply> SearchPay = dao.SearchPay(areacode,Name,startTime,endTime);  
	    //保存查询的信息用setAttribute将值给SearchName    
		
		request.setAttribute("SearchPay", SearchPay);
	   
	    //转发请求  
		request.getRequestDispatcher("/page/apply/apply_pay.jsp").forward(request, resp);  

		}
	private void searchmedical(HttpServletRequest request,HttpServletResponse resp)throws Exception{
		 String Name=request.getParameter("Name"); 
		 String endTime=request.getParameter("endTime");
		 int policyYear=DateUtil.getYear(new java.util.Date());
		 String startTime=request.getParameter("startTime");
		 String areacode=request.getParameter("areacode");
		 String MedicalName=request.getParameter("MedicalName");
		 Applyperiod applyperiod = new Applyperiod(policyYear);
		 request.setAttribute("proportion", applyperiod.getProportion());
		
		 ApplyDao dao = new ApplyDao();
		 String sql = "select * from t_apply where Name like '"+Name+"' AND CreatTime<='"+endTime+"' AND '"+startTime+"'<=CreatTime AND"				
					+ " areacode like '"+areacode+"' AND MedicalName like '"+MedicalName+"' ";
         
		List<Apply> SearchMedical = dao.SearchMedical(areacode,Name,startTime,endTime,MedicalName);  
	    //保存查询的信息用setAttribute将值给SearchName    
		
		request.setAttribute("SearchMedical", SearchMedical);
	   
	    //转发请求  
		request.getRequestDispatcher("/page/apply/apply_statistic.jsp").forward(request, resp);  

		}
	private void searchareacode(HttpServletRequest request,HttpServletResponse resp)throws Exception{
		 String Name=request.getParameter("Name"); 
		 String endTime=request.getParameter("endTime");
		 int policyYear=DateUtil.getYear(new java.util.Date());
		 String startTime=request.getParameter("startTime");
		 String areacode=request.getParameter("areacode");
		 Applyperiod applyperiod = new Applyperiod(policyYear);
		 request.setAttribute("proportion", applyperiod.getProportion());
		
		 ApplyDao dao = new ApplyDao();
		 String sql = "select * from t_apply where Name like '"+Name+"' AND CreatTime<='"+endTime+"' AND '"+startTime+"'<=CreatTime AND"				
					+ " areacode like '"+areacode+"' ";	
		List<Apply> SearchAreacode = dao.SearchAreacode(areacode,Name,startTime,endTime);  
	    //保存查询的信息用setAttribute将值给SearchName    
		
		request.setAttribute("SearchAreacode", SearchAreacode);
	   
	    //转发请求  
		request.getRequestDispatcher("/page/apply/apply_statistic.jsp").forward(request, resp);  

		}
	private void del(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{

	  	  //1：接收参数
	  	  String InvoiceNum=req.getParameter("id");
	  	  
	  	  //2:构造新农民档案信息对象
	  	  Apply model=new Apply();
	  	  try{
	  		  model.del(InvoiceNum);
	  		process(req, resp, "/page/apply/apply_showpay.jsp");
	  	  }catch(Exception e){
	  		  error(req, resp);
	  	  }
		}
	
}
