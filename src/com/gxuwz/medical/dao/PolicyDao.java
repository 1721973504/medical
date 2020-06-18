package com.gxuwz.medical.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gxuwz.medical.database.DbUtil;
import com.gxuwz.medical.domain.policy.Policy;
import com.gxuwz.medical.domain.vo.Page;


public class PolicyDao extends BaseDao {
	
	public PolicyDao(){
		
	}
	

	@Override
	protected Policy handle(ResultSet rs) throws SQLException {
		try{
			Policy policy=new Policy();
			policy.setPolicyYear(rs.getString("policyYear"));
			policy.setPolicyMoney(rs.getString("policyMoney"));
			policy.setProportion(rs.getString("proportion"));
			return policy;
		}catch(SQLException e){
			
			throw new SQLException("结果集转为实例失败!",e);
		}
	}
	/**
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public List<Policy> queryOjects(String sql,Object[]params)throws SQLException{
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Policy> results=new ArrayList<Policy>();
		try{
			conn=DbUtil.getConn();
			pstmt=conn.prepareStatement(sql);
			int index=1;
			if(params!=null){
			  for(Object param:params){
				  if(param instanceof String){
					  pstmt.setString(index++, (String)param);
				  }
				  if(param instanceof Integer){
					  pstmt.setInt(index++, (Integer)param);
				  }
				  if(param instanceof Float){
					  pstmt.setFloat(index++, (Float)param);
				  }
			  }
			}
			rs=pstmt.executeQuery();
			while(rs.next()){

				Policy Policy=handle(rs);
				results.add(Policy);
			
			}
			return results;
		}catch(SQLException e){
			throw new SQLException("执行SQL["+sql+"]失败",e);
		}
		
	}
	
/**
 * 分页
 * @param sql
 * @param params
 * @param page
 * @param size
 * @return
 * @throws SQLException
 */
	public Page queryOjectsByPage(String sql,Object[]params,int pageNo,int size)throws SQLException{
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Page page=null;
		List<Policy> datas=new ArrayList<Policy>();
		try{
			int start=(pageNo-1)*size;
			conn=DbUtil.getConn();
			pstmt=conn.prepareStatement(sql+" limit "+start+","+size);
			int index=1;
			if(params!=null){
			  for(Object param:params){
				  if(param instanceof String){
					  pstmt.setString(index++, (String)param);
				  }
				  if(param instanceof Integer){
					  pstmt.setInt(index++, (Integer)param);
				  }
				  if(param instanceof Float){
					  pstmt.setFloat(index++, (Float)param);
				  }
			  }
			}
			rs=pstmt.executeQuery();
			while(rs.next()){
				Policy Policy=handle(rs);
				datas.add(Policy);
			}
			//统计SQL对应记录数
			int total=count(sql, params);
			page=new Page(total, pageNo, size, datas);
			return page;
		}catch(SQLException e){
			throw new SQLException("执行SQL["+sql+"]失败",e);
		}finally{
			DbUtil.close(rs, pstmt, conn);
		}
		
	}
	

}
