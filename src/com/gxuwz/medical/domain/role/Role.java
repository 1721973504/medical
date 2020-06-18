package com.gxuwz.medical.domain.role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gxuwz.medical.database.DbUtil;
import com.gxuwz.medical.domain.menu.Menu;
import com.gxuwz.medical.exception.RoleNotFoundException;
/**
 * 角色类
 * @author 演示
 *
 */
public class Role {
	/**
	 * 角色编号
	 */
	private String roleid;
	/**
	 * 角色名称
	 */
	private String roleName;
    /**
     * 角色对应权限列表
     */
	private Set<Menu> menus;
	
	public Role(){
		
	}
	public List<String> findAll(String roleid) throws SQLException{
		this.roleid = roleid;
		Connection conn = DbUtil.getConn();
		List<Menu> list = new ArrayList<Menu>();
		List<String> list1 = new ArrayList<String>();
		try {
			list = findAllToDb(conn);
			for(int i=0;i<list.size();i++) {
				list1.add(list.get(i).getMenuid());
			}
		}catch (Exception e) {
			System.out.println("错误"+e);
		}finally {
			conn.close();
		}
		return list1;
	}
	private List<Menu> findAllToDb(Connection conn) throws SQLException{
		List<Menu> list = new ArrayList<Menu>();
		
		PreparedStatement pstmt = null;
		try {
			String sql = "select * from t_role_menu where roleid=?";
			pstmt = conn.prepareStatement(sql);
			int index=1; 
			pstmt.setString(index++, this.roleid);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Menu menu = new Menu();
				menu.setMenuid(rs.getString("menuid"));
				list.add(menu);
			}
		}catch (Exception e) {
			System.out.println("错误"+e);
		}finally {
			pstmt.close();
		}
		return list;
	}

	/**
	 * 
	 * @param roleid
	 */
	public Role(String roleid)throws RoleNotFoundException{
		
		load(roleid);
	}

	public Role(String roleid,String rolename){
		
		this.roleid =roleid;
		this.roleName =rolename;
	}
	/**
	 * 添加角色信息
	 * @param rolename
	 * @param menuids
	 * @throws SQLException
	 */
   public void addRole(String rolename,String[]menuids)throws SQLException{
	   Connection conn=null;
	   this.roleName =rolename;
	   try{
		   conn =DbUtil.getConn();
		   //1：开启手动提交事务
		   conn.setAutoCommit(false);
		   //2:保存角色信息到数据库
		   saveToDB(conn);
		   //3:循环关联菜单信息
		   if(menuids!=null){
		   for(String m:menuids){
			   bindMenu(conn, m);
		   }}
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
	 * 编辑角色信息
	 * @param rolename
	 * @param menuids
	 * @throws SQLException
	 */
  public void editRole(String[]menuids)throws SQLException{
	   Connection conn=null;
	   try{
		   conn =DbUtil.getConn();
		   //1：开启手动提交事务
		   conn.setAutoCommit(false);
		   //2:更新角色信息到数据库
		   eidtToDB(conn);
		   //3：删除原由角色关联
		   unbindMenu(conn);
		   //4:循环关联菜单信息
		   for(String m:menuids){
			   bindMenu(conn, m);
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
    * 删除角色信息
    * @param roleid
    * @throws SQLException
    */
  public void delRole(String roleid)throws SQLException{
	   Connection conn=null;
	   this.roleid =roleid;
	   try{
		   conn =DbUtil.getConn();
		   //1：开启手动提交事务
		   conn.setAutoCommit(false);
		   //2:删除角色信息
		   deleteFromDB(conn);
		   //3:删除关联菜单信息
		   unbindMenu(conn);
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
   * 删除角色信息
   * @param conn
   * @throws SQLException
   */
  private void deleteFromDB(Connection conn)throws SQLException{

	  PreparedStatement pstmt=null;
	  try{
		  StringBuffer sqlBuff=new StringBuffer("delete from t_role where roleid=?");
		  pstmt=conn.prepareStatement(sqlBuff.toString());
		  pstmt.setString(1, this.roleid);

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
			  StringBuffer sqlBuff=new StringBuffer("insert into t_role(roleid,rolename)");
			  sqlBuff.append("values(?,?)");
			  pstmt=conn.prepareStatement(sqlBuff.toString());
			  pstmt.setString(1, this.roleid);
			  pstmt.setString(2, this.roleName);
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
			  StringBuffer sqlBuff=new StringBuffer("update t_role t set t.rolename=?  where t.roleid=?");
			  pstmt=conn.prepareStatement(sqlBuff.toString());
			  pstmt.setString(1, this.roleName);
			  pstmt.setString(2, this.roleid);
			  pstmt.executeUpdate(); 
		  }catch(SQLException e){
			  throw new SQLException("Failed to update t_role  !", e);
		  }finally{
			  DbUtil.close(pstmt);
		  }
		  }
	  /**
	   * 建立关联
	   * @param conn
	   * @param menuid
	   * @throws SQLException
	   */
	  private void bindMenu(Connection conn,String menuid)throws SQLException{
		  PreparedStatement pstmt=null;
		  try{
			  StringBuffer sqlBuff=new StringBuffer("insert into t_role_menu(roleid,menuid)");
			  sqlBuff.append("values(?,?)");
			  pstmt=conn.prepareStatement(sqlBuff.toString());
			  pstmt.setString(1, this.roleid);
			  pstmt.setString(2, menuid);
			  pstmt.executeUpdate(); 
		  }catch(SQLException e){
			  throw new SQLException("Failed to bind to menu!", e);
		  }finally{
			  DbUtil.close(pstmt);
		  }
	  }
	  /**
	   * 删除关联
	   * @param conn
	   * @param menuid
	   * @throws SQLException
	   */
	  private void unbindMenu(Connection conn)throws SQLException{
		  PreparedStatement pstmt=null;
		  try{
			  StringBuffer sqlBuff=new StringBuffer("delete from t_role_menu where roleid=?");
			  pstmt=conn.prepareStatement(sqlBuff.toString());
			  pstmt.setString(1, this.roleid);
			  pstmt.executeUpdate(); 
		  }catch(SQLException e){
			  throw new SQLException("Failed to unbind to menu!", e);
		  }finally{
			  DbUtil.close(pstmt);
		  }
	  }
	/**
	 * Load the role data from the database.
	 * @param id
	 * @throws RoleNotFoundException
	 */
	private void load(String roleid)throws RoleNotFoundException {

		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=DbUtil.getConn();
			pstmt=conn.prepareStatement("select * from t_role where roleid=?");
			int index=1;
			pstmt.setString(index, roleid);
			rs=pstmt.executeQuery();
			if(rs.next()){
				this.roleid=rs.getString("roleid");
				this.roleName =rs.getString("rolename");
			}else{
				throw new RoleNotFoundException("Role with id "
	                    + roleid + " could not be loaded from the database.");
			}
			
		}catch(SQLException e){
			throw new RoleNotFoundException("Role with id "
                    + roleid + " could not be loaded from the database.",e);
		}finally{
			DbUtil.close(rs, pstmt, conn);
		}
	
	}
	
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	private List<String> getMenuids() {

		List<String> menuids = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DbUtil.getConn();
			pstmt = conn
					.prepareStatement("select menuid from t_role_menu where roleid=?");
			int index = 1;
			pstmt.setString(index++, roleid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				menuids.add(rs.getString(1));
			}
		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			DbUtil.close(rs, pstmt, conn);
		}
		return menuids;
	}


	public Set<Menu> getMenus() {
		try {
			if (menus == null) {
				menus= new HashSet<Menu>();
				List<String> menuids = getMenuids();
				for (String menuid : menuids) {
					Menu menu = new Menu(menuid);
					menus.add(menu);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menus;
	}
	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}

}
