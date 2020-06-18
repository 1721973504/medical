package com.gxuwz.medical.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gxuwz.medical.database.DbUtil;
import com.gxuwz.medical.domain.apply.Apply;
import com.gxuwz.medical.domain.card.Card;
/**
 * 查询
 * @author
 *
 */
public class ApplyDao extends GenericDao<Apply> {
	@Override
	protected Apply handle(ResultSet rs) throws SQLException {
		try{
			String InvoiceNum=rs.getString("InvoiceNum");
			String MedicalName=rs.getString("MedicalName");
			String idcard=rs.getString("idcard");
			String Name=rs.getString("Name");
			String total=rs.getString("total");
			String amount=rs.getString("amount");
			String Time=rs.getString("Time");
			String CreatTime=rs.getString("CreatTime");
			String areacode=rs.getString("areacode");
			Apply entity=new Apply(InvoiceNum,MedicalName,idcard,Name,total,amount,Time,CreatTime,areacode);
		    return entity;
	    }catch(SQLException e){
		
		    throw new SQLException("结果集转为实例失败!",e);
	    }
   }
	//行政区域+姓名+报销时间段+未审核
	public List<Apply> SearchCheck(String areacode,String Name,String startTime,String endTime) {  
	  	  
        Connection conn = null;  
        Statement stmt = null;  
        List<Apply> classList= new ArrayList<Apply>();  
        try {  
            // 获取连接  
        	conn=DbUtil.getConn();
            // 整理一条SQL语句  
            String sql = "select * from t_apply where Name like '"+Name+"' AND CreatTime<='"+endTime+"' AND '"+startTime+"'<=CreatTime AND"
					+ " InvoiceNum not in(select InvoiceNum from t_applycheck) AND"
					+ " areacode in(select areacode from t_pay where Name like '"+Name+"')";
            // 创建执行sql的对象 
            stmt = conn.createStatement();  
            //执行sql语句  
            ResultSet rs =stmt.executeQuery(sql);  
            //遍历结果集  
            while(rs.next()){

            	Apply apply=handle(rs);
				classList.add(apply);		
			}
            return classList;
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return classList;  
        }
	//行政区域+姓名+报销时间段+已审核+未汇款
		public List<Apply> SearchPay(String areacode,String Name,String startTime,String endTime) {  
		  	  
	        Connection conn = null;  
	        Statement stmt = null;  
	        List<Apply> classList= new ArrayList<Apply>();  
	        try {  
	            // 获取连接  
	        	conn=DbUtil.getConn();
	            // 整理一条SQL语句  
	            String sql = "select * from t_applycheck where Name like '"+Name+"' AND CreatTime<='"+endTime+"' AND '"+startTime+"'<=CreatTime AND"
						+ " InvoiceNum not in(select InvoiceNum from t_applypay) AND"
						+ " areacode like '"+areacode+"' ";
	            // 创建执行sql的对象 
	            stmt = conn.createStatement();  
	            //执行sql语句  
	            ResultSet rs =stmt.executeQuery(sql);  
	            //遍历结果集  
	            while(rs.next()){

	            	Apply apply=handle(rs);
					classList.add(apply);		
				}
	            return classList;
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }  
	        return classList;  
	        }
		//行政区域+姓名+报销时间段+病种+统计方式（报销名单明细+汇总情况）
				public List<Apply> SearchMedical(String areacode,String Name,String startTime,String endTime,String MedicalName) {  
				  	  
			        Connection conn = null;  
			        Statement stmt = null;  
			        List<Apply> classList= new ArrayList<Apply>();  
			        try {  
			            // 获取连接  
			        	conn=DbUtil.getConn();
			            // 整理一条SQL语句  
			            String sql = "select * from t_apply where Name like '"+Name+"' AND CreatTime<='"+endTime+"' AND '"+startTime+"'<=CreatTime AND"				
								+ " areacode like '"+areacode+"' AND MedicalName like '"+MedicalName+"' ";
			            // 创建执行sql的对象 
			            stmt = conn.createStatement();  
			            //执行sql语句  
			            ResultSet rs =stmt.executeQuery(sql);  
			            //遍历结果集  
			            while(rs.next()){

			            	Apply apply=handle(rs);
							classList.add(apply);		
						}
			            return classList;
			        } catch (SQLException e) {  
			            e.printStackTrace();  
			        }  
			        return classList;  
			        }
				//行政区域+姓名+报销时间段+统计方式（报销名单明细+汇总情况）
				public List<Apply> SearchAreacode(String areacode,String Name,String startTime,String endTime) {  				  	  
			        Connection conn = null;  
			        Statement stmt = null;  
			        List<Apply> classList= new ArrayList<Apply>();  
			        try {  
			            // 获取连接  
			        	conn=DbUtil.getConn();
			            // 整理一条SQL语句  
			        	 String sql = "select * from t_apply where Name like '"+Name+"' AND CreatTime<='"+endTime+"' AND '"+startTime+"'<=CreatTime AND"				
									+ " areacode like '"+areacode+"' ";				            
			            // 创建执行sql的对象 
			            stmt = conn.createStatement();  
			            //执行sql语句  
			            ResultSet rs =stmt.executeQuery(sql);  
			            //遍历结果集  
			            while(rs.next()){

			            	Apply apply=handle(rs);
							classList.add(apply);		
						}
			            return classList;
			        } catch (SQLException e) {  
			            e.printStackTrace();  
			        }  
			        return classList;  
			        }
		
	
}

