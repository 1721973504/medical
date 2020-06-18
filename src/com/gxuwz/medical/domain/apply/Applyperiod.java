package com.gxuwz.medical.domain.apply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gxuwz.medical.database.DbUtil;
import com.gxuwz.medical.exception.PayperiodUnfoundException;
import com.gxuwz.medical.tools.DateUtil;


public class Applyperiod {
	
	private int policyYear;
	
	private String proportion;
	
	private String policyMoney;
	
	
	
	
	
	public Applyperiod(int policyYear)throws PayperiodUnfoundException{
	   if(policyYear==0){
		  throw new IllegalArgumentException("the param policyYear can not be 0") ;
	   }else{
		   this.policyYear=policyYear;
		   try{
		      load();
		   }catch(SQLException e){
			   throw new PayperiodUnfoundException(policyYear+"年度的缴费标准未设置！",e);
		   } 
	   }
	}
	/**
	 * 
	 * @throws SQLException
	 */
	private void load()throws SQLException{
		//定义数据库连接实例变量
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		try{
			 conn=DbUtil.getConn();
			 String sql="select * from t_policy where policyYear=?";
			 pstmt =conn.prepareStatement(sql);
			 int index=1;
			 //依次设置动态参数对应的值
			 pstmt.setInt(index, this.policyYear);
			 rs=pstmt.executeQuery();
			 //执行next()方法
			 rs.next();
			 this.policyYear=rs.getInt("policyYear");
			 this.proportion=rs.getString("proportion");
			 this.policyMoney=rs.getString("policyMoney");
		}catch(SQLException e){
			e.printStackTrace();
			throw new SQLException("failed load ID="+this.policyYear+" record from database!", e);
		}
	}
	public int getPolicyYear() {
		return policyYear;
	}
	public void setPolicyYear(int policyYear) {
		this.policyYear = policyYear;
	}
	public String getProportion() {
		return proportion;
	}
	public void setProportion(String proportion) {
		this.proportion = proportion;
	}
	public String getPolicyMoney() {
		return policyMoney;
	}
	public void setPolicyMoney(String policyMoney) {
		this.policyMoney = policyMoney;
	}
	
	

}
