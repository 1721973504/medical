package com.gxuwz.medical.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.gxuwz.medical.dao.PeasantDao;
import com.gxuwz.medical.domain.peasant.Peasant;
import com.gxuwz.medical.domain.vo.AutocompleteModel;
import com.gxuwz.medical.tools.DateUtil;

/**
 * 农民档案管理请求处理模块类
 * @author 演示
 *
 */
public class PeasantServlet extends BaseServlet {
	private PeasantDao peasantDao;
	@Override
	public void init() throws ServletException {
		peasantDao=new PeasantDao();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String m=req.getParameter("m");//请求处理动作类型:list:显示列表；get：根据ID读取记录;input:新增数据;add:保存新记录；edit：更新记录
	    try{
	    	if("list".equals(m)){
	      process(req, resp, "/page/peasant/peasant_list.jsp");
	    }else if("input".equals(m)){
		  process(req, resp, "/page/peasant/peasant_add.jsp");
		}else if("get".equals(m)){
			  process(req, resp, "/page/peasant/peasant_edit.jsp");
		} 
		else if("add".equals(m)){
			this.add(req, resp);
		}else if("edit".equals(m)){
			this.edit(req, resp);
		}else if("del".equals(m)){
			this.del(req, resp);
		}else if("find".equals(m)){
			this.find(req, resp);
		}else if("searchs".equals(m)){
			this.searchs(req, resp);
		}	
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
	
	private void searchs(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
		String cardNum=req.getParameter("cardNum");
		String idcard=req.getParameter("idcard");
		int policyYear=DateUtil.getYear(new java.util.Date());
		PeasantDao dao = new PeasantDao();
		String sql = "select * from t_peasant where cardNum like '"+cardNum+"' AND idcard like '"+idcard+"' AND policyYear like '"+policyYear+"'";
         
	    //创建一个列表，调用dao
		List<Peasant> SearchPeople = dao.SearchPeople(cardNum,idcard,policyYear);  
	    //保存查询的信息用setAttribute将值给SearchName    
	    req.setAttribute("SearchPeople", SearchPeople);
	    //转发请求  
	    req.getRequestDispatcher("/page/apply/apply_list.jsp").forward(req, resp);  

		}
	/**
	 * 根据姓名查找个人信息列表
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	private void find(HttpServletRequest req, HttpServletResponse resp)
			throws Exception{
		String town =req.getParameter("town");
		String q =req.getParameter("q");
		//插件请求方式是GET，采用转码方法处理中文乱码
		String name =new String(q.getBytes("ISO8859-1"),"utf-8");
	    List<Peasant> peasant=peasantDao.findByKeyWord(name, town);
	    //将查询结果列表转换为自动填充数据模型列表
	    List<AutocompleteModel> itemList=new ArrayList<AutocompleteModel>();
		for(Peasant m:peasant){
			String itemId=m.getCardNum();
			String itemName =m.getName();
			String sex="1".equals(m.getSex())?"男":"女";
			String otherName=sex+"-"+m.getAddress();
			AutocompleteModel item=new AutocompleteModel(itemId, itemName,otherName);
			itemList.add(item);
		}
		//转换为JSON的格式
	    String respBody = JSON.toJSONString(itemList);
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(respBody);
		out.flush();
		out.close();
	} 
	private void add(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{

  	  //1：接收参数
	  String familycode=req.getParameter("familycode");
	  String cardNum =req.getParameter("cardNum");
	  String MedicalNum=req.getParameter("MedicalNum");
  	  String code=req.getParameter("familycode");
  	  String name=req.getParameter("name");
  	  String Relations=req.getParameter("Relations");
  	  String idcard=req.getParameter("idcard");
  	  String sex=req.getParameter("sex");
  	  String healthy=req.getParameter("healthy");
  	  String nation=req.getParameter("nation");
  	  String education=req.getParameter("education");
  	  String age=req.getParameter("age");
  	  String birthday=req.getParameter("birthday");
  	  String attribute=req.getParameter("attribute");
  	  String agricultural=req.getParameter("agricultural");
  	  String profession=req.getParameter("profession");
  	  String workunit=req.getParameter("workunit");
  	  String phone=req.getParameter("phone");
  	  String address=req.getParameter("address");
  	  String email=req.getParameter("email");
  	  int policyYear=Integer.parseInt(req.getParameter("policyYear"));
  	  //2:构造新农民档案信息对象
  	  Peasant model=new Peasant();
  	  //3：调用保存的方法
  	  try{
  		  model.add(familycode,cardNum,MedicalNum,code,name,Relations,idcard,sex,healthy,nation, education, age, birthday, attribute, agricultural, profession,
  				 workunit, phone, address,email,policyYear); 
  		process(req, resp, "/page/peasant/peasant_list.jsp");
  	  }catch(Exception e){
  		  error(req, resp);
  	  }
	
  
	}
	private void edit(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{

	  	  //1：接收参数
		 String familycode=req.getParameter("familycode");
		  String cardNum=req.getParameter("cardNum");
		  String MedicalNum=req.getParameter("MedicalNum");
	  	  String code=req.getParameter("code");
	  	  String name=req.getParameter("name");
	  	  String Relations=req.getParameter("Relations");
	  	  String idcard=req.getParameter("idcard");
	  	  String sex=req.getParameter("sex");
	  	  String healthy=req.getParameter("healthy");
	  	  String nation=req.getParameter("nation");
	  	  String education=req.getParameter("education");
	  	  String age=req.getParameter("age");
	  	  String birthday=req.getParameter("birthday");
	  	  String attribute=req.getParameter("attribute");
	  	  String agricultural=req.getParameter("agricultural");
	  	  String profession=req.getParameter("profession");
	  	  String workunit=req.getParameter("workunit");
	  	  String phone=req.getParameter("phone");
	  	  String address=req.getParameter("address");
	  	  String email=req.getParameter("email");
	  	  System.out.println("查看"+req.getParameter("policyYear"));
	  	  int policyYear=1;
	  	  System.out.println("email:"+email);
	  	  //2:构造新农民档案信息对象
	  	  Peasant model=new Peasant(familycode,cardNum,MedicalNum,code,name,Relations,idcard,sex,healthy,nation,education,
  					age,birthday,attribute,agricultural,profession,workunit,phone,address,email,policyYear);
	  	  //3：调用保存的方法
	  	  try{
	  		  model.edit();
	  		process(req, resp, "/page/peasant/peasant_list.jsp");
	  	  }catch(Exception e){
	  		  error(req, resp);
	  	  }
		
	  
		}
	private void del(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{

	  	  //1：接收参数
	  	  String idcard=req.getParameter("id");
	  	  
	  	  //2:构造新农民档案信息对象
	  	  Peasant model=new Peasant();
	  	  try{
	  		  model.del(idcard);
	  		process(req, resp, "/page/peasant/peasant_list.jsp");
	  	  }catch(Exception e){
	  		  error(req, resp);
	  	  }
		}
}
