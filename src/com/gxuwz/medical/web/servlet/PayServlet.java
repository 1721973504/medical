package com.gxuwz.medical.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gxuwz.medical.dao.PayDao;
import com.gxuwz.medical.domain.pay.Pay;

/**
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException
 *
 */
public class PayServlet extends BaseServlet {

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
			process(req, response, "/page/pay/pay_list.jsp");
		}else if("get".equals(m)){
			process(req, response, "/page/pay/pay_print.jsp");
		}else if("input".equals(m)){
			process(req, response, "/page/pay/pay_add.jsp");
		}else if("add".equals(m)){
			add(req, response);
			process(req, response, "/page/pay/pay_list.jsp");
		}else if("searchs".equals(m)){
			this.searchs(req, response);
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
		 String Participationid=req.getParameter("Participationid");
		 String areacode=req.getParameter("areacode");
		 String InvoiceNum=req.getParameter("InvoiceNum");
		 String Name=(String)req.getParameter("Name");
		 String PayMoney=req.getParameter("PayMoney");
		 String PayYear=req.getParameter("PayYear");
		 String PayTime=req.getParameter("PayTime");
		 String registrarName=req.getParameter("registrarName");		
		 //调用添加方法
		 Pay pay =new Pay(Participationid,areacode,InvoiceNum,Name,PayMoney,PayYear,PayTime,registrarName);
		 try{	 
			 pay.addPay(Participationid,areacode,InvoiceNum,Name,PayMoney,PayYear,PayTime,registrarName);
		 }catch(Exception e){
				e.printStackTrace();
		 }
		
	}
	private void searchs(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
		 String areacode=req.getParameter("areacode");
		 String Name=(String)req.getParameter("Name");
		 String PayYear=req.getParameter("PayYear");
		 String PayTime=req.getParameter("PayTime");
		
		PayDao dao = new PayDao();
		String sql = "select * from t_pay where PayYear like '"+PayYear+"' AND areacode like '"+areacode+"' AND PayTime like '"+PayTime+"'AND Name like '"+Name+"'";
         
	    //创建一个列表，调用dao的SearchMessage
		List<Pay> SearchMessage = dao.SearchMessage(PayYear,areacode,PayTime,Name);  
	    //保存查询的信息用setAttribute将值给SearchName    
	    req.setAttribute("SearchMessage", SearchMessage);
	    //转发请求  
	    req.getRequestDispatcher("/page/pay/pay_list.jsp").forward(req, resp);  

		}
	
}
