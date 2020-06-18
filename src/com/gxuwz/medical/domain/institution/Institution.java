package com.gxuwz.medical.domain.institution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gxuwz.medical.database.DbUtil;
import com.gxuwz.medical.domain.area.Area;
import com.gxuwz.medical.exception.DbException;

/**
 * 农合机构管理类
 * @author 演示
 *
 */
public class Institution {
	
	/**
	 * 行政区域编码，类标识符
	 */
	private Area area;
	/**
	 * 经办机构编码
	 */
	private String agencode;
	/**
	 * 经办机构名称
	 */
	private String agenname;
	/**
	 * 经办机构级别，区别于行政区域级别，1表示县级农合办，2表示镇级农合点
	 */
	private int grade;
	
	
	public Institution(){
		
	}
	/**
	 * 实例化对象
	 * @param areacode
	 * @throws DbException
	 */
	public Institution(Area area)throws DbException{
		  if(area==null){
			  throw new IllegalStateException("行政区域编码不能空");
		  }
		try{
			this.area=area;
			loadDB();
	    }catch(SQLException e){
	    	throw new DbException("加载id="+this.area.getAreacode()+"农合经办机构记录失败", e);
	    }
	}
	
	public Institution(Area area, String agencode, String agenname, int grade) {
		super();
		this.area = area;
		this.agencode = agencode;
		this.agenname = agenname;
		this.grade = grade;
	}
	/**
	 * 
	 * @param areaid
	 * @throws SQLException
	 */
	private void loadDB()throws SQLException{
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=DbUtil.getConn();
			pstmt=conn.prepareStatement("select * from t_institution where areacode=?");
			int index=1;
			pstmt.setString(index, this.area.getAreacode());
			rs=pstmt.executeQuery();
			if(rs.next()){
				this.area=new Area(rs.getString("areacode"));
				this.agencode=rs.getString("agencode");
				this.agenname=rs.getString("agenname");
				this.grade=rs.getByte("grade");
			}
		}catch(SQLException e){
			throw e;
		}finally{
			DbUtil.close(rs, pstmt, conn);
		}
	}
	/**
	 * 添加农合经办点
	 * @param areacode
	 * @param agencode
	 * @param agenname
	 * @param grade
	 */
	public void addInst(Area area,String agencode,String agenname,int grade)throws Exception{
		Connection conn=null;
		try{
			//初始化属性
			this.area=area;
			this.agencode=agencode;
			this.agenname=agenname;
			this.grade =grade;
		   //保存导数据库
			conn=DbUtil.getConn();
			conn.setAutoCommit(false);
			//调用插入数据库私有方法
			this.saveToDB(conn);
			conn.commit();
		}catch(Exception e){
			conn.rollback();
			throw e;
		}finally{
			DbUtil.close(conn);
		}
		
	}
	/**
	 * 插入记录
	 * @param connection
	 * @throws SQLException
	 */
	private void saveToDB(Connection connection)throws SQLException{
		PreparedStatement pstmt=null;
		try{

			StringBuffer sqlBuff=new StringBuffer();
			sqlBuff.append("insert into t_institution(areacode,agencode,agenname,grade)");
			sqlBuff.append("values(?,?,?,?)");
			String sql=sqlBuff.toString();
			pstmt=connection.prepareStatement(sql);
			//依次设置动态参数的值，注意顺序
			System.out.println("测试"+grade+"qqq"+agencode+"qqq"+agenname+"qq"+area.getAreacode());
			int index=1;
			pstmt.setString(index++, this.area.getAreacode());//行政区域编码 
			pstmt.setString(index++, this.agencode);
			pstmt.setString(index++, this.agenname);
			pstmt.setInt(index++, this.grade);
			//以executeUpdate()执行SQL
			pstmt.executeUpdate();
		}catch(SQLException e){
			System.out.println("报错信息"+e);
			throw e;
		}finally{
			//只关闭PrepareStatement资源
			if(pstmt!=null){
				pstmt.close();
			}
			
		}
		
	}
	
	public String getAgencode() {
		return agencode;
	}
	public void setAgencode(String agencode) {
		this.agencode = agencode;
	}
	public String getAgenname() {
		return agenname;
	}
	public void setAgenname(String agenname) {
		this.agenname = agenname;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}

}
