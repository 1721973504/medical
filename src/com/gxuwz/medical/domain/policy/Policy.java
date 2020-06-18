package com.gxuwz.medical.domain.policy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gxuwz.medical.database.DbUtil;

import com.gxuwz.medical.exception.DbException;

/**
 * 慢性病政策设置
 * @author 演示
 *
 */
public class Policy {
private String policyYear;
private String policyMoney;
private String proportion;

public String getPolicyYear() {
	return policyYear;
}
public void setPolicyYear(String policyYear) {
	this.policyYear = policyYear;
}
public String getPolicyMoney() {
	return policyMoney;
}
public void setPolicyMoney(String pulicyMoney) {
	this.policyMoney = pulicyMoney;
}
public String getProportion() {
	return proportion;
}
public void setProportion(String proportion) {
	this.proportion = proportion;
}
public Policy(){
	
}
/**
 * 
 * @param policyYear
 */
public Policy(String policyYear)throws DbException{
	try{
		this.policyYear =policyYear;
		load(policyYear);
    }catch(SQLException e){
    	throw new DbException("加载id="+policyYear+"行政区域记录失败", e);
    }
}

public Policy(String policyYear,String policyMoney,String proportion){
	super();
	this.policyYear =policyYear;
	this.policyMoney =policyMoney;
	this.proportion =proportion;
}
/**
 * 添加慢性病政策信息
 * @param proportion
 * @throws SQLException
 */
public void addPolicy(String policyYear,String policyMoney,String proportion)throws SQLException{
   Connection conn=null;
   this.policyYear =policyYear;
   this.policyMoney =policyMoney;
   this.proportion =proportion;
   try{
	   conn =DbUtil.getConn();
	   //1：开启手动提交事务
	   conn.setAutoCommit(false);
	   //2:保存慢性病政策信息到数据库
	   saveToDB(conn);		  
	   //3：提交事务
	   conn.commit();
   }catch(SQLException e){
	   conn.rollback();
	   throw e;
   }finally{
	   DbUtil.close(conn);
   }
}
/**
 * 编辑慢性病政策信息
 * @param proportion
 * @throws SQLException
 */
public void editPolicy(String policyMoney,String proportion)throws SQLException{
   Connection conn=null;
   this.policyMoney =policyMoney;
   this.proportion =proportion;
   try{
	   conn =DbUtil.getConn();
	   //1：开启手动提交事务
	   conn.setAutoCommit(false);
	   //2:更新慢性病政策信息到数据库
	   eidtToDB(conn);
	   //3：提交事务
	   conn.commit();
   }catch(SQLException e){
	   conn.rollback();
	   throw e;
   }finally{
	   DbUtil.close(conn);
   }
}
/**
* 删除慢性病政策信息
* @param policyYear
* @throws SQLException
*/
public void delPolicy(String policyYear)throws Exception{
   Connection conn=null;
   this.policyYear =policyYear;
   try{
		conn =DbUtil.getConn();
		//开启手动提交事务
		conn.setAutoCommit(false);
		//保存到数据库
		deleteFromDB(conn);
		//提交事务
		conn.commit();
	}catch (Exception e) {
		if(conn!=null){
			conn.rollback();
		}
		throw e;
	}finally{
		DbUtil.close(conn);
	}
}
/**
* 删除慢性病政策信息
* @param conn
* @throws SQLException
*/
private void deleteFromDB(Connection conn)throws SQLException{
  PreparedStatement pstmt=null;
  try{
	  StringBuffer sqlBuff=new StringBuffer("delete from t_policy where policyYear=?");
	  pstmt=conn.prepareStatement(sqlBuff.toString());
	  pstmt.setString(1, this.policyYear);
	  pstmt.executeUpdate(); 
  }catch(SQLException e){
	  throw new SQLException("Failed to delete record from table !", e);
  }finally{
	  DbUtil.close(pstmt);
  }


}

private void saveToDB(Connection conn)throws SQLException{

	  PreparedStatement pstmt=null;
	  try{
		  StringBuffer sqlBuff=new StringBuffer("insert into t_policy(policyYear,policyMoney,proportion)");
		  sqlBuff.append("values(?,?,?)");
		  pstmt=conn.prepareStatement(sqlBuff.toString());
		  pstmt.setString(1, this.policyYear);
		  pstmt.setString(2, this.policyMoney);
		  pstmt.setString(3, this.proportion);
		  pstmt.executeUpdate(); 
	  }catch(SQLException e){
		  throw new SQLException("Failed to insert into table !", e);
	  }finally{
		  DbUtil.close(pstmt);
	  } 
}
private void eidtToDB(Connection conn)throws SQLException{

	  PreparedStatement pstmt=null;
	  try{
		  StringBuffer sqlBuff=new StringBuffer("update t_policy set policyMoney=?,proportion=?  where policyYear=?");
		  pstmt=conn.prepareStatement(sqlBuff.toString());
		  pstmt.setString(1, this.policyMoney);
		  pstmt.setString(2, this.proportion);
		  pstmt.setString(3, this.policyYear);
		  pstmt.executeUpdate(); 
	  }catch(SQLException e){
		  throw new SQLException("Failed to update t_policy  !", e);
	  }finally{
		  DbUtil.close(pstmt);
	  }
	  }
 
/**
 * Load the policy data from the database.
 * @param id
 * @throws policyNotFoundException
 */
private void load(String policyYear)throws SQLException{
	Connection conn =null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	try{
		conn=DbUtil.getConn();
		pstmt=conn.prepareStatement("select * from t_policy where policyYear=?");
		int index=1;
		pstmt.setString(index, this.policyYear);
		rs=pstmt.executeQuery();
		if(rs.next()){
			this.policyYear=rs.getString("policyYear");
			this.policyMoney=rs.getString("policyMoney");
			this.proportion =rs.getString("proportion");
		}
	}catch(SQLException e){
		throw new SQLException("Failed to load id"+policyYear+" from database!",e);
	}finally{
		DbUtil.close(rs, pstmt, conn);
	}

}

}

