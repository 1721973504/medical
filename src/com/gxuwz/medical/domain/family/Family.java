package com.gxuwz.medical.domain.family;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.gxuwz.medical.database.DbUtil;
import com.gxuwz.medical.domain.peasant.Peasant;
import com.gxuwz.medical.exception.DbException;

/**
 * 参合家庭档案管理
 * @author zcl
 *
 */

public class Family {
//	县级编号、乡镇编号、村编号、组编号
//	家庭编号、户属性、户主姓名、家庭人口数、农业人口数、家庭住址
//	创建档案时间时间、登记员。
	private String familycode;
	private String countycode;
	private String towncode;
	private String villagecode;
	private String groupcode;
	private String residence;//户属性（非农业，农业）
	private String holderName;
	private String idcard;
	private String familyNum;
	private String AgricultureNum;//农业人口数
	private String address;
	private String time;
	private String registrarName;//登记员
	private Family  parent;
	public Family(){
		
	}
	public Family(String familycode)throws DbException{
		try{
			this.familycode=familycode;
			loadDB(familycode);
		}catch(SQLException e){
			throw new DbException("加载id="+familycode+"失败", e);
		}
		
	}
	public Family(String familycode,String countycode,String towncode,String villagecode,
			String groupcode,String residence,String holderName,String idcard,String familyNum,String AgricultureNum,
			String address,String time,String registrarName){
		super();
		this.familycode=familycode;
		this.countycode=countycode;
		this.towncode=towncode;
		this.villagecode=villagecode;
		this.groupcode=groupcode;
		this.residence=residence;
		this.holderName=holderName;
		this.idcard=idcard;
		this.familyNum=familyNum;
		this.AgricultureNum=AgricultureNum;
		this.address=address;
		this.time=time;
		this.registrarName=registrarName;

	}
	/**
	 * 添加
	 * @param familycode
	 * @param countycode
	 * @param towncode
	 * @param villagecode
	 * @param groupcode
	 * @param residence
	 * @param holderName
	 * @param familyNum
	 * @param AgricultureNum
	 * @param address
	 * @param time
	 * @param registrarName
	 * @throws Exception
	 */
	public void addFamily(String familycode,String countycode,String towncode,String villagecode,
			String groupcode,String residence,String holderName,String idcard,String familyNum,String AgricultureNum,
			String address,String time,String registrarName)throws Exception{
		Connection conn=null;
		try{
			//初始化属性
			this.parent=new Family(familycode);
			this.countycode=countycode;
			this.towncode=towncode;
			this.villagecode=villagecode;
			this.groupcode=groupcode;
			this.residence=residence;
			this.holderName=holderName;
			this.idcard=idcard;
			this.familyNum=familyNum;
			this.AgricultureNum=AgricultureNum;
			this.address=address;
			this.time=time;
			this.registrarName=registrarName;	
			this.familycode=createfamilycode();
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
	public void editFamily(String holderName,String familyNum,String AgricultureNum,
			String address,String time,String registrarName)throws Exception{
		Connection conn=null;
		try{
			this.holderName=holderName;
			this.familyNum=familyNum;
			this.AgricultureNum=AgricultureNum;
			this.address=address;
			this.time=time;
			this.registrarName=registrarName;
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
	public void delFamily(String familycode)throws Exception{
		Connection conn=null;
		this.familycode = familycode;
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
	/**
	 * 添加
	 * @param connection
	 * @throws SQLException
	 */
	private void saveToDB(Connection connection)throws SQLException{
		PreparedStatement pstmt=null;
		try{

			StringBuffer sqlBuff=new StringBuffer();
			sqlBuff.append("insert into t_family(familycode,countycode,towncode,villagecode,groupcode,"
					+ "residence,holderName,idcard,familyNum,AgricultureNum,address,time,registrarName)");
			sqlBuff.append("values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			String sql=sqlBuff.toString();
			pstmt=connection.prepareStatement(sql);
			//依次设置动态参数的值，注意顺序
			int index=1;
			pstmt.setString(index++, this.familycode);
			pstmt.setString(index++, this.countycode);
			pstmt.setString(index++, this.towncode);
			pstmt.setString(index++, this.villagecode);
			pstmt.setString(index++, this.groupcode);
			pstmt.setString(index++, this.residence);
			pstmt.setString(index++, this.holderName);
			pstmt.setString(index++, this.idcard);
			pstmt.setString(index++, this.familyNum);
			pstmt.setString(index++, this.AgricultureNum);
			pstmt.setString(index++, this.address);
			pstmt.setString(index++, this.time);
			pstmt.setString(index++, this.registrarName);
			//以executeUpdate()执行SQL
			pstmt.executeUpdate();
		}catch(SQLException e){
			throw e;
		}finally{
			//只关闭PrepareStatement资源
			if(pstmt!=null){
				pstmt.close();
			}
		}
	}
	private void editToDB(Connection conn)throws SQLException{

		  PreparedStatement pstmt=null;
		  try{
			  StringBuffer sqlBuff=new StringBuffer("update t_family set countycode=?,towncode=?,villagecode=?,groupcode=?,residence=?,holderName=?,idcard=?,familyNum=?,AgricultureNum=?,address=?,time=?,registrarName=? where familycode=?");
			    pstmt=conn.prepareStatement(sqlBuff.toString());
				pstmt.setString(1, this.countycode);
				pstmt.setString(2, this.towncode);
				pstmt.setString(3, this.villagecode);
				pstmt.setString(4, this.groupcode);
				pstmt.setString(5, this.residence);
				pstmt.setString(6, this.holderName);
				pstmt.setString(7, this.idcard);
				pstmt.setString(8, this.familyNum);
				pstmt.setString(9, this.AgricultureNum);
				pstmt.setString(10, this.address);
				pstmt.setString(11, this.time);
				pstmt.setString(12, this.registrarName);
				pstmt.setString(13, this.familycode);
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
			  StringBuffer sqlBuff=new StringBuffer("delete from t_family where familycode=?");
				  pstmt=conn.prepareStatement(sqlBuff.toString());
				  pstmt.setString(1, this.familycode);
				  pstmt.executeUpdate(); 
			  }catch(SQLException e){
				  throw new SQLException("Failed to delete record from table !", e);
			  }finally{
				  DbUtil.close(pstmt);
			  }
	}
	private void loadDB(String familycode)throws SQLException{
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=DbUtil.getConn();
			pstmt=conn.prepareStatement("select * from t_family where familycode=?");
			int index=1;
			pstmt.setString(index, this.familycode);
			rs=pstmt.executeQuery();
			if(rs.next()){
				this.familycode=rs.getString("familycode");
				this.countycode=rs.getString("countycode");
				this.towncode=rs.getString("towncode");
				this.villagecode=rs.getString("villagecode");
				this.groupcode=rs.getString("groupcode");
				this.residence=rs.getString("residence");
				this.holderName=rs.getString("holderName");
				this.idcard=rs.getString("idcard");
				this.familyNum=rs.getString("familyNum");
				this.AgricultureNum=rs.getString("AgricultureNum");
				this.address=rs.getString("address");
				this.time=rs.getString("time");
				this.registrarName=rs.getString("registrarName");				
			}
		}catch(SQLException e){
			throw e;
		}finally{
			DbUtil.close(rs, pstmt, conn);
		}
	}  
	 private String createfamilycode()throws SQLException{
			Connection conn =null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String familycode=this.getGroupcode();
			String sql="select max(familycode) from t_family where groupcode like'"+familycode+"%'" ;
		
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
				  no=String.format("%04d", number);
				  maxcode=this.getGroupcode()+no;
				}else{
					String no=String.format("%04d", number);
					maxcode=this.getGroupcode()+no;
				}
				return maxcode;
			}catch(SQLException e){
				
				throw e;
			}finally{
				DbUtil.close(rs, pstmt, conn);
			}
		}
	
	public String getCountycode() {
		return countycode;
	}
	public void setCountycode(String countycode) {
		this.countycode = countycode;
	}
	public String getTowncode() {
		return towncode;
	}
	public void setTowncode(String towncode) {
		this.towncode = towncode;
	}
	public String getVillagecode() {
		return villagecode;
	}
	public void setVillagecode(String villagecode) {
		this.villagecode = villagecode;
	}
	public String getGroupcode() {
		return groupcode;
	}
	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}
	public String getFamilycode() {
		return familycode;
	}
	public void setFamilycode(String familycode) {
		this.familycode = familycode;
	}
	public String getResidence() {
		return residence;
	}
	public void setResidence(String residence) {
		this.residence = residence;
	}
	public String getHolderName() {
		return holderName;
	}
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	public String getFamilyNum() {
		return familyNum;
	}
	public void setFamilyNum(String familyNum) {
		this.familyNum = familyNum;
	}
	public String getAgricultureNum() {
		return AgricultureNum;
	}
	public void setAgricultureNum(String agricultureNum) {
		AgricultureNum = agricultureNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getRegistrarName() {
		return registrarName;
	}
	public void setRegistrarName(String registrarName) {
		this.registrarName = registrarName;
	}
	
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	
	
	public Family getParent() {
		return parent;
	}
	public void setParent(Family parent) {
		this.parent = parent;
	}
	
	public static void main(String[]args)throws Exception{
		String number="01";
		System.out.println(Integer.parseInt(number));
	
		System.out.println(String.format("%02d", Integer.parseInt(number)));
	}

}
