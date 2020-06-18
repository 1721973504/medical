package com.gxuwz.medical.domain.area;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gxuwz.medical.database.DbUtil;
import com.gxuwz.medical.exception.DbException;

/**
 * 行政区域模块类
 * 
 * @author 演示
 * 
 */
public class Area  {
	/**
	 * 乡镇区域编码:县、镇、村、组
	 */
	private String areacode;
	/**
	 * 上一级
	 */
	private Area  parent;
	
	/**
	 * 行政区域名称
	 */
	private String areaname;
	/**
	 * 行政区域级别：1表示县级，2表示镇级，3表示村，4表示组
	 */
	private int grade;

	public Area(){
		
	}
	
	public Area(String areaid)throws SQLException{
		try{
		this.areacode=areaid;
		loadDB(areaid);
	    }catch(SQLException e){
	    	throw e;
	    }
		
	}
	
	public Area getParent() {
		return parent;
	}

	public void setParent(Area parent) {
		this.parent = parent;
	}
	
	/**
	 * 添加子一级行政区域
	 * @param areapid-所属行政区域
	 * @param areaname
	 * @throws Exception
	 */
	public void addArea(String areapid,String areaname)throws Exception{
		Connection conn=null;
		try{
			//加载父节点ID的对象
			this.parent=new Area(areapid);
			this.areaname=areaname;
			this.grade=this.parent.getGrade()+1;
			//自动编号
			this.areacode=createAreaid();
			conn =DbUtil.getConn();
			//开启手动提交事务
			conn.setAutoCommit(false);
			//保存到数据库
			saveToDB(conn);
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
	public void editArea(String areaname)throws Exception{
		Connection conn=null;
		try{
			this.areaname=areaname;
			conn =DbUtil.getConn();
			//开启手动提交事务
			conn.setAutoCommit(false);
			//保存到数据库
			editToDB(conn);
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
	public void delArea()throws Exception{
		Connection conn=null;
		try{
			conn =DbUtil.getConn();
			//开启手动提交事务
			conn.setAutoCommit(false);
			//保存到数据库
			delFromDB(conn);
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
	private void saveToDB(Connection conn)throws SQLException{

		  PreparedStatement pstmt=null;
		  try{
			  StringBuffer sqlBuff=new StringBuffer("insert into t_area(areacode,areaname,grade)");
			  sqlBuff.append("values(?,?,?)");
			  pstmt=conn.prepareStatement(sqlBuff.toString());
			  pstmt.setString(1, this.areacode);
			  pstmt.setString(2, this.areaname);
			  pstmt.setInt(3, this.grade);
			  pstmt.executeUpdate(); 
		  }catch(SQLException e){
			  throw e;
		  }finally{
			  DbUtil.close(pstmt);
		  } 
}
	private void editToDB(Connection conn)throws SQLException{

		  PreparedStatement pstmt=null;
		  try{
			  StringBuffer sqlBuff=new StringBuffer("update t_area set areaname=?,grade=? where areacode=?");
			  pstmt=conn.prepareStatement(sqlBuff.toString());
			  pstmt.setString(1, this.areaname);
			  pstmt.setInt(2, this.grade);
			  pstmt.setString(3, this.areacode);
			  pstmt.executeUpdate(); 
		  }catch(SQLException e){
			  throw e;
		  }finally{
			  DbUtil.close(pstmt);
		  } 
}
	private void delFromDB(Connection conn)throws SQLException{

		  PreparedStatement pstmt=null;
		  try{
			  StringBuffer sqlBuff=new StringBuffer("delete from t_area where areacode like '"+this.areacode+"%' and grade>=?");
			  pstmt=conn.prepareStatement(sqlBuff.toString()); 
			  pstmt.setInt(1, this.grade);
			  pstmt.executeUpdate(); 
		  }catch(SQLException e){
			  throw e;
		  }finally{
			  DbUtil.close(pstmt);
		  } 
}
	/**
	 * 加载行政区域信息
	 * @param areaid
	 * @throws SQLException
	 */
	private void loadDB(String areaid)throws SQLException{
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=DbUtil.getConn();
			pstmt=conn.prepareStatement("select * from t_area where areacode=?");
			int index=1;
			pstmt.setString(index, this.areacode);
			rs=pstmt.executeQuery();
			if(rs.next()){
				this.areacode=rs.getString("areacode");
				this.areaname=rs.getString("areaname");
				this.grade=rs.getByte("grade");
				
			}
		}catch(SQLException e){
			throw e;
		}finally{
			DbUtil.close(rs, pstmt, conn);
		}
	}
	  /**
     * 自动生成行政区域编号
     * @return
     * @throws SQLException
     */
    private String createAreaid()throws SQLException{
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String areapid=this.parent.getAreacode();
		int grade=this.parent.getGrade()+1;
		String sql="select max(areacode) from t_area where areacode like'"+areapid+"%' and grade="+grade ;
	
		try{
			conn=DbUtil.getConn();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			String maxcode="";
			Integer number=1;
			if(rs.next()){
			   maxcode=rs.getString(1);
			}
			
			if(maxcode!=null){
			  int beginIndex=maxcode.length()-2;
			  String no=maxcode.substring(beginIndex);
			  number=Integer.parseInt(no);
			  ++number;
			  //使用0补足位数
			  no=String.format("%02d", number);
			  maxcode=this.parent.getAreacode()+no;
			}else{
				String no=String.format("%02d", number);
				maxcode=this.parent.getAreacode()+no;
			}
			return maxcode;
		}catch(SQLException e){
			
			throw e;
		}finally{
			DbUtil.close(rs, pstmt, conn);
		}
	}

	

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}
	public List<Area> children()throws Exception{
		List<Area> areaList=new ArrayList<Area>();
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=DbUtil.getConn();
			pstmt=conn.prepareStatement("select * from t_area where areacode like'?%' and grade=?");
			int index=1;
			pstmt.setString(index++, this.areacode);
			pstmt.setInt(index, this.grade+1);
			rs=pstmt.executeQuery();
	
			while(rs.next()){
				Area area=new Area();
				area.setAreacode(rs.getString("areaid"));
				area.setAreaname(rs.getString("areapid"));
				area.setAreaname(rs.getString("areaname"));
				area.setGrade(rs.getInt("grade"));
				areaList.add(area);
			
			}
			
		}catch(SQLException e){
			
			throw e;
		}finally{
			DbUtil.close(rs, pstmt, conn);
		}
	
		return areaList;
	}
	public static void main(String[]args)throws Exception{
		String number="01";
		System.out.println(Integer.parseInt(number));
	
		System.out.println(String.format("%02d", Integer.parseInt(number)));
	}

}
