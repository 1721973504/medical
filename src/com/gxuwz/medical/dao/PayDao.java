package com.gxuwz.medical.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gxuwz.medical.database.DbUtil;
import com.gxuwz.medical.domain.medical.Medical;
import com.gxuwz.medical.domain.pay.Pay;
import com.gxuwz.medical.domain.peasant.Peasant;
import com.gxuwz.medical.exception.DbException;
/**
 * 查询
 * @author
 *
 */
public class PayDao extends GenericDao<Pay> {
	@Override
	protected Pay handle(ResultSet rs) throws SQLException {
		try{
			String Participationid=rs.getString("Participationid");
			String areacode=rs.getString("areacode");
			String InvoiceNum=rs.getString("InvoiceNum");
			String Name=rs.getString("Name");
			String PayMoney=rs.getString("PayMoney");
		    String PayYear=rs.getString("PayYear");
			String PayTime=rs.getString("PayTime");
			String registrarName=rs.getString("registrarName");
			  Pay entity=new Pay(Participationid,areacode,InvoiceNum,Name,PayMoney,PayYear,PayTime,registrarName);
		    return entity;
	    }catch(SQLException e){
		
		    throw new SQLException("结果集转为实例失败!",e);
	    }
   }
	public List<Pay> SearchMessage(String PayYear,String areacode,String PayTime,String Name) {  
	  	  
        Connection conn = null;  
        Statement stmt = null;  
        List<Pay> classList= new ArrayList<Pay>();  
        try {  
            // 获取连接  
        	conn=DbUtil.getConn();
            // 整理一条SQL语句  
            String sql = "select * from t_pay where PayYear like '"+PayYear+"' AND areacode like '"+areacode+"' AND PayTime like '"+PayTime+"'AND Name like '"+Name+"'";
            // 创建执行sql的对象 
            stmt = conn.createStatement();  
            //执行sql语句  
            ResultSet rs =stmt.executeQuery(sql);  
            //遍历结果集  
            while(rs.next()){

				Pay pay=handle(rs);
				classList.add(pay);		
			}
            return classList;
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return classList;  
    }
	
	
	public List<Pay>findYear(String PayYear)throws DbException{
		try{
		  String sql="select * from t_money  money where PayYear ='";
            sql += PayYear + "'";
		  Object[]params={};
		  return super.queryOjects(sql, params);
		}catch(SQLException e){		
			throw new DbException("查找失败",e);
		}
	}
}

