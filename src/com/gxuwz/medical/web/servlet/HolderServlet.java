package com.gxuwz.medical.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gxuwz.medical.dao.PeasantDao;
import com.gxuwz.medical.domain.peasant.Peasant;

/**
 * 户主管理请求处理模块类
 * @author 演示
 *
 */
public class HolderServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String m=req.getParameter("m");
	    if("list".equals(m)){
	      process(req, resp, "/page/family/holder/holder_list.jsp");
	    }else if("search".equals(m)){
		  process(req, resp, "/page/family/holder/holder_search.jsp");
		}	else if("searchs".equals(m)){
			this.searchs(req, resp);
			}	
	}
	private void searchs(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{		
		 String name=(String)req.getParameter("name");
	
		
		 PeasantDao dao = new PeasantDao();
		 String sql = "select * from t_peasant where code=01 and name ='";
         sql += name + "'";
	    //创建一个列表，调用dao的SearchMessage
		List<Peasant> SearchName = dao.SearchName(name);  
	    //保存查询的信息用setAttribute将值给SearchName    
	    req.setAttribute("SearchName", SearchName);
	    //转发请求  
	    req.getRequestDispatcher("/page/pay/pay_add.jsp").forward(req, resp);  

		}
	
}
