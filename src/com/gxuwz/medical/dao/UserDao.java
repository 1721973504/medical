package com.gxuwz.medical.dao;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gxuwz.medical.database.DbUtil;
import com.gxuwz.medical.domain.user.User;
import com.gxuwz.medical.domain.vo.Page;


public class UserDao extends BaseDao {
	
	private static final String  SQL_SELECT_USERID="select * from t_user where userid=?";

	@Override
	protected User handle(ResultSet rs) throws SQLException {
		try{
			User user=new User();
			user.setFullname(rs.getString("fullname"));
			user.setPwd(rs.getString("pwd"));
			user.setUserid(rs.getString("userid"));
			user.setAgencode(rs.getString("agencode"));
			user.setStatus(rs.getString("status"));
			
			return user;
		}catch(SQLException e){
			
			throw new SQLException("结果集转为实例失败!",e);
		}
	}
	/**
	 * 查找对应的记录并转为对象实例
	 * @param userid
	 * @return
	 * @throws SQLException
	 */
	public User get(String userid)throws SQLException{
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=DbUtil.getConn();
			pstmt=conn.prepareStatement(SQL_SELECT_USERID);
			int index=1;
			pstmt.setString(index, userid);
			rs=pstmt.executeQuery();
			return handle(rs);
		}catch(SQLException e){
			throw new SQLException("读取userID记录失败",e);
		}
		
	}
	/**
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public List<User> queryOjects(String sql,Object[]params)throws SQLException{
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<User> results=new ArrayList<User>();
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
				User user=handle(rs);
				results.add(user);
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
		List<User> datas=new ArrayList<User>();
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
				User user=handle(rs);
				datas.add(user);
			}
			//统计SQL对应记录数
			int total=count(sql, params);
			page=new Page(total, pageNo, size, datas);
			return page;
		}catch(SQLException e){
			e.printStackTrace();
			throw e;
		}
		
	}
	

}
