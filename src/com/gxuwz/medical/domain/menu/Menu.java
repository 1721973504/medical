package com.gxuwz.medical.domain.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gxuwz.medical.database.DbUtil;
import com.gxuwz.medical.exception.MenuException;

/**
 * 权限实体类
 * @author 演示
 * 
 */
public class Menu {
	
	private static final String SELECT_PARENT_MENU="select * from t_menu where menupid=? order by menuid asc";
	private static final String SELECT_SELF_MENU="select * from t_menu where menuid=? order by menuid asc";
	private static final String SELECT_ROLE_MENU="select * from t_role_menu where menuid=? and roleid=?";
	/**
	 * 权限编号
	 */
	private String menuid;
	/**
	 * 权限上一级编号
	 */
	private String menupid;
	/**
	 * 上一级菜单对象
	 */
	private Menu parent;
	
	private int level;//菜单级数
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Menu getParent() {
		try{
			if(this.parent==null){
				this.parent=new Menu(this.menupid);
			}
		}catch(MenuException e){
			e.printStackTrace();
		}
		return this.parent;
	}

	/**
	 * 权限编号
	 */
	private String menuname;
	
	private String url;
	
	public Menu(){
		
	}
	/**
	 * 构造菜单节点实例
	 * @param menuid
	 * @throws MenuException
	 */
	public Menu(String menuid)throws MenuException{
		this.menuid=menuid;
		this.menuname="";
		if(!"0".equals(menuid)){
		    loadDB(menuid);
		}
	}
	
	public  Menu(String menuname,String url)throws MenuException{
		this.menuname=menuname;
		this.url =url;
	}
	
	/**
	    * 删除菜单
	    * @param menuid
	    * @throws SQLException
	    */
	  public void delMenu(String menuid)throws SQLException{
		   Connection conn=null;
		   this.menuid =menuid;
		   try{
			   conn =DbUtil.getConn();
			   //1：开启手动提交事务
			   conn.setAutoCommit(false);
			   //2:删除菜单信息
			   deleteFromDB(conn);
			   //3:删除关联角色
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
	 * 新增子菜单
	 * @throws MenuException
	 */
	public  void addMenu(String menupid,int plevel)throws SQLException{
		Connection conn=null;
		this.menupid=menupid;
		this.level=plevel+1;
		try{
			conn =DbUtil.getConn();
			conn.setAutoCommit(false);
			//生成菜单编号
			this.menuid=createMenuid();
		    //保存到数据库
			saveToDB(conn);
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
			  StringBuffer sqlBuff=new StringBuffer("delete from t_menu where menuid=?");
			  pstmt=conn.prepareStatement(sqlBuff.toString());
			  pstmt.setString(1, this.menuid);

			  pstmt.executeUpdate(); 
		  }catch(SQLException e){
			  throw new SQLException("Failed to delete record from table !", e);
		  }finally{
			  DbUtil.close(pstmt);
		  }
	  
	   
	}
	
	/**
	 * 修改菜单
	 * @param menuname
	 * @param url
	 * @throws SQLException
	 */
	public  void editMenu(String menuname,String url)throws SQLException{
		Connection conn=null;
		this.menuname =menuname;
		this.url =url;
		try{
			conn =DbUtil.getConn();
			conn.setAutoCommit(false);
		    //保存到数据库
			editToDB(conn);
			conn.commit();
		}catch(SQLException e){
			conn.rollback();
			throw e;
		}finally{
			DbUtil.close(conn);
		}
	}
	/**
	 * 解除Menu与Role关联
	 * @param conn
	 * @throws SQLException
	 */
	public void unbindRole(Connection conn) throws SQLException{

		  PreparedStatement pstmt=null;
		  try{
			  StringBuffer sqlBuff=new StringBuffer("delete from t_role_menu where menuid=?");
			  pstmt=conn.prepareStatement(sqlBuff.toString());
			  pstmt.setString(1, this.menuid);
			  pstmt.executeUpdate(); 
		  }catch(SQLException e){
			  throw new SQLException("Failed to unbind to Role !", e);
		  }finally{
			  DbUtil.close(pstmt);
		  }
	  
	}
	
	private void saveToDB(Connection conn)throws SQLException{

		  PreparedStatement pstmt=null;
		  try{
			  StringBuffer sqlBuff=new StringBuffer("insert into t_menu(menuid,menuname,menupid,url,level)");
			  sqlBuff.append("values(?,?,?,?,?)");
			  pstmt=conn.prepareStatement(sqlBuff.toString());
			  pstmt.setString(1, this.menuid);
			  pstmt.setString(2, this.menuname);
			  pstmt.setString(3, this.menupid);
			  pstmt.setString(4, this.url);
			  pstmt.setInt(5, this.level);
			  pstmt.executeUpdate(); 
		  }catch(SQLException e){
			  throw e;
		  }finally{
			  DbUtil.close(pstmt);
		  } 
 }
	/**
	 * 
	 * @param conn
	 * @throws SQLException
	 */
	private void editToDB(Connection conn)throws SQLException{

		  PreparedStatement pstmt=null;
		  try{
			  StringBuffer sqlBuff=new StringBuffer("update t_menu t set t.menuname=?,t.url=? where t.menuid=?");
			  pstmt=conn.prepareStatement(sqlBuff.toString());

			  pstmt.setString(1, this.menuname);
			  pstmt.setString(2, this.url);
			  pstmt.setString(3, this.menuid);
			  pstmt.executeUpdate(); 
		  }catch(SQLException e){
			  throw new SQLException("Failed to update t_menu !", e);
		  }finally{
			  DbUtil.close(pstmt);
		  } 
}
    public boolean hasRole(String roleid)throws MenuException{

		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=DbUtil.getConn();
			pstmt=conn.prepareStatement(SELECT_ROLE_MENU);
			int index=1;
			pstmt.setString(index++, this.menuid);
			pstmt.setString(index, roleid);
			rs=pstmt.executeQuery();
			return rs.next();
		}catch(SQLException e){
			
			throw new MenuException("Failed to query roleid="+roleid+"   from database!",e);
		}finally{
			DbUtil.close(rs, pstmt, conn);
		}
	
    	
    }
    /**
     * 自动生成菜单编号
     * @return
     * @throws SQLException
     */
    private String createMenuid()throws SQLException{
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select max(menuid) from t_menu where menupid=?";
		try{
			conn=DbUtil.getConn();
			pstmt=conn.prepareStatement(sql);
			int index=1;
			pstmt.setString(index, this.menupid);
			rs=pstmt.executeQuery();
			String maxcode="";
			if(rs.next()){
			   maxcode=rs.getString(1);
			}
			if(maxcode!=null){
			  int beginIndex=maxcode.length()-2;
			  String no=maxcode.substring(beginIndex);
			  Integer number=Integer.parseInt(no);
			  ++number;
			  //使用0补足位数
			  no=String.format("%02d", number);
			  maxcode=this.menupid+no;
			}else{
				maxcode=this.menupid+"01";
			}
			return maxcode;
		}catch(SQLException e){
			
			throw new SQLException("创建父节点ID"+this.menuid+"失败!",e);
		}finally{
			DbUtil.close(rs, pstmt, conn);
		}
	}
	/**
     * 加载菜单编号对应的记录信息
     * @param menuid
     * @throws MenuException
     */
	private void loadDB(String menuid)throws MenuException{
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=DbUtil.getConn();
			pstmt=conn.prepareStatement(SELECT_SELF_MENU);
			int index=1;
			pstmt.setString(index, menuid);
			rs=pstmt.executeQuery();
			if(rs.next()){
				this.menuid=rs.getString("menuid");
				this.menuname=rs.getString("menuname");
				this.menupid =rs.getString("menupid");
				this.url =rs.getString("url");
				this.level=rs.getInt("level");
				
			}
		}catch(SQLException e){
			
			throw new MenuException("Failed to load id"+menuid+" from database!",e);
		}finally{
			DbUtil.close(rs, pstmt, conn);
		}
	}
	/**
	 * 获取子一级菜单集合
	 * @return
	 */
	public List<Menu> children()throws MenuException{
		List<Menu> menuList=new ArrayList<Menu>();
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=DbUtil.getConn();
			pstmt=conn.prepareStatement(SELECT_PARENT_MENU);
			int index=1;
			pstmt.setString(index, this.menuid);
			rs=pstmt.executeQuery();
	
			while(rs.next()){
				Menu menu=new Menu();
				menu.setMenuid(rs.getString("menuid"));
				menu.setMenupid(rs.getString("menupid"));
				menu.setMenuname(rs.getString("menuname"));
				menu.setUrl(rs.getString("url"));
				menu.setLevel(rs.getInt("level"));
				menuList.add(menu);
			
			}
			
		}catch(SQLException e){
			
			throw new MenuException("查找下一级菜单集合失败",e);
		}finally{
			DbUtil.close(rs, pstmt, conn);
		}
	
		return menuList;
	}
	
	
	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getMenupid() {
		return menupid;
	}

	public void setMenupid(String menupid) {
		this.menupid = menupid;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	

}
