package com.gxuwz.medical.domain.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gxuwz.medical.database.DbUtil;
import com.gxuwz.medical.domain.role.Role;
import com.gxuwz.medical.exception.UserNotFoundException;

/**
 * 用户信息实体类
 * 
 * @author 演示
 * 
 */
public class User {

	/**
	 * 工号
	 */
	private String userid;
	/**
	 * 密码
	 */
	private String pwd;
	/**
	 * 姓名
	 */
	private String fullname;
	
	private String status;
	/**
	 * 所在农合机构编码
	 */
	private String agencode;
	/**
	 * 用户对应角色列表
	 */
	private Set<Role> roles;

	public User() {

	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @param id
	 * @throws Exception
	 */
	private void load(String userid) throws UserNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DbUtil.getConn();
			pstmt = conn
					.prepareStatement("select * from t_user where userid=?");
			int index = 1;
			pstmt.setString(index, userid);
			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				this.fullname = rs.getString("fullname");
				this.pwd = rs.getString("pwd");
				this.userid=rs.getString("userid");
				this.agencode=rs.getString("agencode");
				this.status =rs.getString("status");
			}

		} catch (SQLException e) {
			throw new UserNotFoundException("User with id " + userid
					+ " could not be loaded from the database.");
		} finally {
			DbUtil.close(rs, pstmt, conn);
		}
	}

	public User(String userid, String pwd, String fullname, String status,
			String agencode) {
		super();
		this.userid = userid;
		this.pwd = pwd;
		this.fullname = fullname;
		this.status = status;
		this.agencode = agencode;
	}
	public String getAgencode() {
		return agencode;
	}
	public void setAgencode(String agencode) {
		this.agencode = agencode;
	}
	
	 /**
	    * 删除角色信息
	    * @param roleid
	    * @throws SQLException
	    */
	  public void delUser(String userid)throws SQLException{
		   Connection conn=null;
		   this.userid =userid;
		   try{
			   conn =DbUtil.getConn();
			   //1：开启手动提交事务
			   conn.setAutoCommit(false);
			   //2:删除用户信息
			   deleteFromDB(conn);
			   //3:删除关联角色信息
			   unbindRole(conn);
			   //4：提交事务
			   conn.commit();
		   }catch(SQLException e){
			   conn.rollback();
			   throw e;
		   }finally{
			   DbUtil.close(conn);
		   }
	  }
	  /**
	   * 删除用户信息
	   * @param conn
	   * @throws SQLException
	   */
	  private void deleteFromDB(Connection conn)throws SQLException{

		  PreparedStatement pstmt=null;
		  try{
			  StringBuffer sqlBuff=new StringBuffer("delete from t_user where userid=?");
			  pstmt=conn.prepareStatement(sqlBuff.toString());
			  pstmt.setString(1, this.userid);

			  pstmt.executeUpdate(); 
		  }catch(SQLException e){
			  throw new SQLException("Failed to delete record from table !", e);
		  }finally{
			  DbUtil.close(pstmt);
		  }
	  
	   
	}

	/**
	 * 
	 * @param userid
	 * @param pwd
	 * @throws UserNotFoundException
	 */
	public User(String userid, String pwd) throws UserNotFoundException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DbUtil.getConn();
			pstmt = conn
					.prepareStatement("select * from t_user where userid=? and pwd=?");
			int index = 1;
			pstmt.setString(index++, userid);
			pstmt.setString(index, pwd);
			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				this.fullname = rs.getString("fullname");
				this.pwd = rs.getString("pwd");
				this.userid = rs.getString("userid");
			} else {
				throw new UserNotFoundException("User with id " + userid
						+ " could not be loaded from the database.");
			}
		} catch (SQLException e) {

			throw new UserNotFoundException("User with id " + userid
					+ " could not be loaded from the database.");

		} finally {
			DbUtil.close(rs, pstmt, conn);
		}
	}
	/**
	 * 
	 * @param roleids
	 * @throws SQLException
	 */
     public void addUser(String[]roleids)throws SQLException{
    	 Connection conn=null;
    	try{
    		conn=DbUtil.getConn();
    		conn.setAutoCommit(false);
    		//1:保存用户信息
    		saveToDB(conn);
    		//2:建立角色关联
    		if(roleids!=null){
    			for(String roleid:roleids){
        			bindRole(conn, roleid);
        		}
    		}
    		//提交事务
    		conn.commit();
    		
    	}catch(SQLException e){
    		conn.rollback();
    		throw e;
    	}finally{
    		DbUtil.close(conn);
    	}
     }
     /**
	   * 建立角色关联
	   * @param conn
	   * @param roleid
	   * @throws SQLException
	   */
	  private void bindRole(Connection conn,String roleid)throws SQLException{
		  PreparedStatement pstmt=null;
		  try{
			  StringBuffer sqlBuff=new StringBuffer("insert into t_user_role(userid,roleid)");
			  sqlBuff.append("values(?,?)");
			  pstmt=conn.prepareStatement(sqlBuff.toString());
			  pstmt.setString(1, this.userid);
			  pstmt.setString(2, roleid);
			  pstmt.executeUpdate(); 
		  }catch(SQLException e){
			  throw new SQLException("Failed to bind to Role!", e);
		  }finally{
			  DbUtil.close(pstmt);
		  }
	  }
	  
	  /**
	   * 编辑用户信息
	   * @param roleids
	   * @throws SQLException
	   */
	  public void editUser(String[]roleids)throws SQLException{
		   Connection conn=null;
		   try{
			   conn =DbUtil.getConn();
			   //1：开启手动提交事务
			   conn.setAutoCommit(false);
			   //2:更新用户信息到数据库
			   eidtToDB(conn);
			   //3：删除原有角色关联
			   unbindRole(conn);
			   //4:循环关联菜单信息
			   for(String roleid:roleids){
				   bindRole(conn, roleid);
			   }
			   //4：提交事务
			   conn.commit();
		   }catch(SQLException e){
			   conn.rollback();
			   throw e;
		   }finally{
			   DbUtil.close(conn);
		   }
	  }
	  /**
	   * 解除User与Role关联
	   * @param conn
	   * @throws SQLException
	   */
	  private void unbindRole(Connection conn) throws SQLException{

		  PreparedStatement pstmt=null;
		  try{
			  StringBuffer sqlBuff=new StringBuffer("delete from t_user_role where userid=?");
			  pstmt=conn.prepareStatement(sqlBuff.toString());
			  pstmt.setString(1, this.userid);
			  pstmt.executeUpdate(); 
		  }catch(SQLException e){
			  throw new SQLException("Failed to unbind to Role !", e);
		  }finally{
			  DbUtil.close(pstmt);
		  }
	  
	}
	  private void eidtToDB(Connection conn)throws SQLException{

		  PreparedStatement pstmt=null;
		  try{
			  StringBuffer sqlBuff=new StringBuffer("update t_user t set t.fullname=?,t.pwd=?,agencode=?,status=?  where t.userid=?");
			  pstmt=conn.prepareStatement(sqlBuff.toString());
			  pstmt.setString(1, this.fullname);
			  pstmt.setString(2, this.pwd);
			  pstmt.setString(3, this.agencode);
			  pstmt.setString(4, this.status);
			  pstmt.setString(5, this.userid);
			  
			  pstmt.executeUpdate(); 
		  }catch(SQLException e){
			  throw new SQLException("Failed to update t_user  !", e);
		  }finally{
			  DbUtil.close(pstmt);
		  }
		  }
     private void saveToDB(Connection conn)throws SQLException{

		  PreparedStatement pstmt=null;
		  try{
			  StringBuffer sqlBuff=new StringBuffer();
			  sqlBuff.append("insert into t_user");
			  sqlBuff.append("(userid,fullname,pwd,agencode,status)");
			  sqlBuff.append("values(?,?,?,?,?)");
			  pstmt=conn.prepareStatement(sqlBuff.toString());
			  pstmt.setString(1, this.userid);
			  pstmt.setString(2, this.fullname);
			  pstmt.setString(3, this.pwd);
			  pstmt.setString(4, this.agencode);
			  pstmt.setString(5, this.status);
			  pstmt.executeUpdate(); 
		  }catch(SQLException e){
			  throw e;
		  }finally{
			  DbUtil.close(pstmt);
		  } 
}
	public User(String userid) throws UserNotFoundException {

		load(userid);
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	private List<String> getRoleids() {

		List<String> roleids = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DbUtil.getConn();
			pstmt = conn
					.prepareStatement("select roleid from t_user_role where userid=?");
			int index = 1;
			pstmt.setString(index++, userid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				roleids.add(rs.getString(1));
			}
		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			DbUtil.close(rs, pstmt, conn);
		}
		return roleids;
	}

	public Set<Role> getRoles() {
		try {
			if (roles == null) {
				roles = new HashSet<Role>();
				List<String> roleids = getRoleids();
				for (String roleid : roleids) {
					Role role = new Role(roleid);
					roles.add(role);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roles;
	}

}
