package com.gxuwz.medical.domain.medical;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import com.gxuwz.medical.database.DbUtil;
import com.gxuwz.medical.exception.DbException;

/**
 * 医疗机构管理类
 * @author 演示
 *
 */
public class Medical {
	/**
	 * 机构编码
	 */
	private String jgbm;
	/**
	 * 组织机构编码
	 */
	private String zzjgbm;
	/**
	 * 机构名称
	 */
	private String jgmc;
	/**
	 * 地区编码
	 */
	private String dqbm;
	/**
	 * 行政区域编码
	 */
	private String areacode;
	/**
	 * 隶属关系
	 */
	private String lsgx;
	
	 public String getLsgxName(){
	       String lsgxName="";
		   if(this.lsgx!=null){
				 try{
			      S201 s201=new S201(this.lsgx, "02");
			      lsgxName= s201.getItemname();
				 }catch(Exception e){
					 ;
				 }
		   }
		 return lsgxName;
	 }
	/**
	 * 机构级别
	 */
	private String jgjb;
	/**
	 * 申报定点类型
	 */
	private String sbddlx;
	/**
	 * 批准定点类型
	 */
	private String pzddlx;
	/**
	 * 所属经济类型
	 */
	private String ssjjlx;
	/**
	 * 卫生机构大类
	 */
	private String wsjgdl;
	/**
	 * 卫生机构小类
	 */
	private String wsjgxl;
	
	/**
	 * 主管单位
	 */
	private String zgdw;
	/**
	 * 开业时间
	 */
	private java.util.Date kysj;
	/**
	 * 法人代表
	 */
	private String frdb;
	/**
	 * 注册资金
	 */
	private double zczj;
	
	public Medical(){
		
	}
	/**
	 * 添加医疗机构
	 * @throws SQLException
	 */
	public void add()throws SQLException{
		Connection conn =null;
		try{
			//1:获得连接
			 conn=DbUtil.getConn();
			//2;保存到数据库
			conn.setAutoCommit(false);
			saveToDB(conn);
			conn.commit();
		}catch(SQLException e){
			conn.rollback();
			e.printStackTrace();
			throw e;
		}finally{
			DbUtil.close(conn);
		}
	}
	/**
	 * 全参构造函数
	 * @param jgbm
	 * @param zzjgbm
	 * @param jgmc
	 * @param dqbm
	 * @param areacode
	 * @param lsgx
	 * @param jgjb
	 * @param sbddlx
	 * @param pzddlx
	 * @param ssjjlx
	 * @param wsjgdl
	 * @param wsjgxl
	 * @param zgdw
	 * @param kysj
	 * @param frdb
	 * @param zczj
	 */
	public Medical(String jgbm, String zzjgbm, String jgmc, String dqbm,
			String areacode, String lsgx, String jgjb, String sbddlx,
			String pzddlx, String ssjjlx, String wsjgdl, String wsjgxl,
			String zgdw, Date kysj, String frdb, double zczj) {
		super();
		this.jgbm = jgbm;
		this.zzjgbm = zzjgbm;
		this.jgmc = jgmc;
		this.dqbm = dqbm;
		this.areacode = areacode;
		this.lsgx = lsgx;
		this.jgjb = jgjb;
		this.sbddlx = sbddlx;
		this.pzddlx = pzddlx;
		this.ssjjlx = ssjjlx;
		this.wsjgdl = wsjgdl;
		this.wsjgxl = wsjgxl;
		this.zgdw = zgdw;
		this.kysj = kysj;
		this.frdb = frdb;
		this.zczj = zczj;
	}
	private void saveToDB(Connection conn)throws SQLException{
		PreparedStatement pstmt=null;
		try{
			
			StringBuffer sqlBuff=new StringBuffer();
			sqlBuff.append("insert into t_medical( jgbm, zzjgbm, jgmc, dqbm, areacode, lsgx, jgjb, "
					+ "sbddlx, pzddlx, ssjjlx, wsjgdl, wsjgxl, zgdw, kysj , frdb,zczj)");
			sqlBuff.append("values");
			sqlBuff.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?,?)");
			String sql=sqlBuff.toString();
			pstmt=conn.prepareStatement(sql);
			//设置动态参数的值
			int index=1;
			pstmt.setString(index++, this.jgbm);
			pstmt.setString(index++, this.zzjgbm);
			pstmt.setString(index++, this.jgmc);
			pstmt.setString(index++, this.dqbm);
			pstmt.setString(index++, this.areacode);
			pstmt.setString(index++, this.lsgx);
			pstmt.setString(index++, this.jgjb);
			pstmt.setString(index++, this.sbddlx);
			pstmt.setString(index++, this.pzddlx);
			pstmt.setString(index++, this.ssjjlx);
			pstmt.setString(index++, this.wsjgdl);
			pstmt.setString(index++, this.wsjgxl);
			pstmt.setString(index++, this.zgdw);
			pstmt.setDate(index++, new java.sql.Date(this.kysj.getTime()));
			pstmt.setString(index++, this.frdb);
			pstmt.setDouble(index++, this.zczj);
			
			int count=pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			throw e;
		}finally{
			DbUtil.close(pstmt);
		}
		
	}
	
	public void edit()throws SQLException{
		Connection conn =DbUtil.getConn();
		try{
			 conn.setAutoCommit(false);
			 this.updateToDB(conn);
			 conn.commit();
		}catch(SQLException e){
			conn.rollback();
			throw e;
		}finally{
			DbUtil.close(conn);
		}
	}
	private void updateToDB(Connection conn)throws SQLException{
		PreparedStatement pstmt=null;
		try{
			//定义构造update SQL语句字符串变量sql
			String  sql="update t_medical set zzjgbm=?,jgmc=?,dqbm=?,areacode=?,lsgx=?,jgjb=?,sbddlx=?,pzddlx=?,ssjjlx=?,wsjgdl=?,wsjgxl=?,zgdw=?,kysj=?,frdb=?,zczj=? where jgbm=?";
			 //创建执行带动态参数的SQL的PreparedStatement pstmt
			 pstmt=conn.prepareStatement(sql);
			 //设置动态参数对应的值
			 int index=1;
			 pstmt.setString(index++, this.zzjgbm);
			 pstmt.setString(index++, this.jgmc);
			 pstmt.setString(index++, this.dqbm);
			 pstmt.setString(index++, this.areacode);
			 pstmt.setString(index++, this.lsgx);
			 pstmt.setString(index++, this.jgjb);
			 pstmt.setString(index++, this.sbddlx);
			 pstmt.setString(index++, this.pzddlx);
			 pstmt.setString(index++, this.ssjjlx);
			 pstmt.setString(index++, this.wsjgdl);
			 pstmt.setString(index++, this.wsjgxl);
			 pstmt.setString(index++, this.zgdw);
			 pstmt.setDate(index++, new java.sql.Date(this.kysj.getTime()));
			 pstmt.setString(index++, this.frdb);
			 pstmt.setDouble(index++, this.zczj);
				
			 pstmt.setString(index++, this.jgbm);
			int count=pstmt.executeUpdate();
			
		}catch(SQLException e){
			throw e;
		}finally{
			DbUtil.close(pstmt);
		}
		
	}
	public Medical(String id)throws Exception{
		this.jgbm =id;
		try{
			load();
		}catch(Exception e){
			e.printStackTrace();
			throw new DbException("无法找到ID="+this.jgbm+"对应信息");
		}
	}
	
	private void load()throws SQLException{
		Connection conn=DbUtil.getConn();
		 PreparedStatement pstmt=null;
		 ResultSet rs=null;
		try{
			
		 String sql="select * from t_medical where jgbm=?";
		 pstmt=conn.prepareStatement(sql);
		 pstmt.setString(1, this.jgbm);
		 rs=pstmt.executeQuery();
		 if(rs.next()){
			 this.jgbm=rs.getString("jgbm");
			 this.zzjgbm =rs.getString("zzjgbm");
			 this.jgmc =rs.getString("jgmc");
			 this.dqbm =rs.getString("dqbm");
			 this.areacode =rs.getString("areacode");
			 this.lsgx =rs.getString("lsgx");
			 this.jgjb =rs.getString("jgjb");
			 this.sbddlx =rs.getString("sbddlx");
			 this.pzddlx =rs.getString("pzddlx");
			 this.ssjjlx =rs.getString("ssjjlx");
			 this.wsjgdl =rs.getString("wsjgdl");
			 this.wsjgxl =rs.getString("wsjgxl");
			 this.zgdw =rs.getString("zgdw");
			 this.kysj =rs.getDate("kysj");
			 this.frdb =rs.getString("frdb");
			 this.zczj =rs.getDouble("zczj");
		 }
		}catch(SQLException e){
			throw e;
		}
		
	}
	
	public void del(String jgbm)throws SQLException{

		Connection conn=null;
       try{
    	   //1:对象属性赋值
    	    this.jgbm =jgbm;
    	    conn=DbUtil.getConn();
    	   //2；调用delFromDB方法
    	    delFromDB(conn);
       }catch(SQLException e){
    	   throw e;
       }finally{
    	   DbUtil.close(conn);
       }
		
	}
	private void delFromDB(Connection conn)throws SQLException{
		PreparedStatement pstmt=null;
		try{
			//定义构造插入SQL语句字符串变量sql
			String  sql="delete from t_medical where jgbm=?";
			 //创建执行带动态参数的SQL的PreparedStatement pstmt
			 pstmt=conn.prepareStatement(sql);
			 //设置动态参数对应的值
			 int index=1;
			 pstmt.setString(index++, this.jgbm);
		
			int count=pstmt.executeUpdate();
			
		}catch(SQLException e){
			throw e;
		}finally{
			DbUtil.close(pstmt);
			
		}
	}
	
	public String getJgbm() {
		return jgbm;
	}

	public void setJgbm(String jgbm) {
		this.jgbm = jgbm;
	}

	public String getZzjgbm() {
		return zzjgbm;
	}

	public void setZzjgbm(String zzjgbm) {
		this.zzjgbm = zzjgbm;
	}

	public String getJgmc() {
		return jgmc;
	}

	public void setJgmc(String jgmc) {
		this.jgmc = jgmc;
	}

	public String getDqbm() {
		return dqbm;
	}

	public void setDqbm(String dqbm) {
		this.dqbm = dqbm;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getLsgx() {
		return lsgx;
	}

	public void setLsgx(String lsgx) {
		this.lsgx = lsgx;
	}

	public String getJgjb() {
		return jgjb;
	}

	public void setJgjb(String jgjb) {
		this.jgjb = jgjb;
	}

	public String getSbddlx() {
		return sbddlx;
	}

	public void setSbddlx(String sbddlx) {
		this.sbddlx = sbddlx;
	}

	public String getPzddlx() {
		return pzddlx;
	}

	public void setPzddlx(String pzddlx) {
		this.pzddlx = pzddlx;
	}

	public String getSsjjlx() {
		return ssjjlx;
	}

	public void setSsjjlx(String ssjjlx) {
		this.ssjjlx = ssjjlx;
	}

	public String getWsjgdl() {
		return wsjgdl;
	}

	public void setWsjgdl(String wsjgdl) {
		this.wsjgdl = wsjgdl;
	}

	public String getWsjgxl() {
		return wsjgxl;
	}

	public void setWsjgxl(String wsjgxl) {
		this.wsjgxl = wsjgxl;
	}

	public String getZgdw() {
		return zgdw;
	}

	public void setZgdw(String zgdw) {
		this.zgdw = zgdw;
	}

	public java.util.Date getKysj() {
		return kysj;
	}

	public void setKysj(java.util.Date kysj) {
		this.kysj = kysj;
	}

	public String getFrdb() {
		return frdb;
	}

	public void setFrdb(String frdb) {
		this.frdb = frdb;
	}

	public double getZczj() {
		return zczj;
	}

	public void setZczj(double zczj) {
		this.zczj = zczj;
	}
	

	

}
