package com.gxuwz.medical.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gxuwz.medical.database.DbUtil;

public abstract class BaseDao {
	/**
	 * 定义抽象方法实现ResultSet结果集转换为对应的Class类型实例
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	protected abstract Object handle(ResultSet rs)throws SQLException;
	/**
	 * 统计记录数
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public int count(String sql,Object[]params)throws SQLException{
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		int c=0;
		try{
			conn=DbUtil.getConn();
			String sql_count="select count(*) from ("+sql+")as temp";
			pstmt=conn.prepareStatement(sql_count);
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
				if(rs.next()){
					 c=rs.getInt(1);
				}
				return c;
		}catch(SQLException e){
			throw new SQLException("执行COUNTSQL失败",e);
		}finally{
			DbUtil.close(rs, pstmt, conn);
		}
	}

}
