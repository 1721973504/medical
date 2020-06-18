package com.gxuwz.medical.domain.pay;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.core.parser.ParseException;

import com.gxuwz.medical.database.DbUtil;
import com.gxuwz.medical.exception.DbException;

public class Pay {
	//参合缴费信息包括参合证号、参合发票号、缴费金额、缴费年度、缴费时间、操作员
	
	private String Participationid;//参合证号
	private String InvoiceNum;//参合发票号
	private String PayMoney;//缴费金额
	private String PayYear;//缴费年度
	private String PayTime;//缴费时间
	private String registrarName;//操作员
	private String areacode;
	private String Name;
	private int status;	
	private Pay parent;
	
	public Pay(){
		
	}
	public Pay(String Participationid)throws DbException{
		try{
			this.Participationid=Participationid;
			loadDB(Participationid);
		}catch(SQLException e){
			throw new DbException("加载id="+Participationid+"失败", e);
		}
		
	}
	public Pay(String Participationid,String areacode,String InvoiceNum,String Name,String PayMoney,String PayYear,String PayTime,String registrarName){
		super();
		this.Participationid=Participationid;
		this.areacode=areacode;
		this.InvoiceNum=InvoiceNum;
		this.Name=Name;
		this.PayMoney=PayMoney;
		this.PayYear=PayYear;
		this.PayTime=PayTime;
		this.registrarName=registrarName;
	}
	
	public void addPay(String Participationid,String areacode,String Name,String InvoiceNum,String PayMoney,String PayYear,String PayTime,String registrarName)throws Exception{
		Connection conn=null;
		try{
			//初始化属性
			this.parent=new Pay(Participationid);
			this.Participationid=createParticipation();
			this.areacode=areacode;		
			this.InvoiceNum=PayYear+createParticipation();		
			this.PayMoney=PayMoney;
			this.PayYear=PayYear;
			this.PayTime=PayTime;
			this.registrarName=registrarName;	
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
	 * 添加
	 * @param connection
	 * @throws SQLException
	 */
	private void saveToDB(Connection connection)throws SQLException{
		PreparedStatement pstmt=null;
		try{

			StringBuffer sqlBuff=new StringBuffer();
			sqlBuff.append("insert into t_pay(Participationid,areacode,InvoiceNum,Name,PayMoney,PayYear,PayTime,registrarName)");
			sqlBuff.append("values(?,?,?,?,?,?,?,?)");
			String sql=sqlBuff.toString();
			pstmt=connection.prepareStatement(sql);
			//依次设置动态参数的值，注意顺序
			int index=1;
			pstmt.setString(index++, this.Participationid);
			pstmt.setString(index++, this.areacode);
			pstmt.setString(index++, this.InvoiceNum);
			pstmt.setString(index++, this.Name);
			pstmt.setString(index++, this.PayMoney);
			pstmt.setString(index++, this.PayYear);
			pstmt.setString(index++, this.PayTime);
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
	private void loadDB(String Participationid)throws SQLException{
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=DbUtil.getConn();
			pstmt=conn.prepareStatement("select * from t_pay where Participationid=?");
			int index=1;
			pstmt.setString(index, this.Participationid);
			rs=pstmt.executeQuery();
			if(rs.next()){
				this.Participationid=rs.getString("Participationid");
				this.areacode=rs.getString("areacode");
				this.InvoiceNum=rs.getString("InvoiceNum");
				this.Name=rs.getString("Name");
				this.PayMoney=rs.getString("PayMoney");
				this.PayYear=rs.getString("PayYear");
				this.PayTime=rs.getString("PayTime");
				this.registrarName=rs.getString("registrarName");				
			}
		}catch(SQLException e){
			throw e;
		}finally{
			DbUtil.close(rs, pstmt, conn);
		}
	}  
	private String createParticipation()throws SQLException{
				Connection conn =null;
				PreparedStatement pstmt=null;
				ResultSet rs=null;
				String areacode=this.getAreacode();
				String sql="select max(Participationid) from t_pay where areacode like'"+areacode+"%'" ;
			
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
					  maxcode=this.getAreacode()+no;
					}else{
						String no=String.format("%04d", number);
						maxcode=this.getAreacode()+no;
					}
					return maxcode;
				}catch(SQLException e){
					
					throw e;
				}finally{
					DbUtil.close(rs, pstmt, conn);
				}
			}
	
	public String getParticipationid() {
		return Participationid;
	}
	public void setParticipationid(String participationid) {
		Participationid = participationid;
	}
	public String getInvoiceNum() {
		return InvoiceNum;
	}
	public void setInvoiceNum(String invoiceNum) {
		InvoiceNum = invoiceNum;
	}
	public String getPayMoney() {
		return PayMoney;
	}
	public void setPayMoney(String payMoney) {
		PayMoney = payMoney;
	}
	public String getPayYear() {
		return PayYear;
	}
	public void setPayYear(String payYear) {
		PayYear = payYear;
	}
	public String getPayTime() {
		return PayTime;
	}
	public void setPayTime(String payTime) {
		PayTime = payTime;
	}
	public String getRegistrarName() {
		return registrarName;
	}
	public void setRegistrarName(String registrarName) {
		this.registrarName = registrarName;
	}
	public String getAreacode() {
		return areacode;
	}
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}
	public Pay getParent() {
		return parent;
	}
	public void setParent(Pay parent) {
		this.parent = parent;
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String Name) {
		this.Name = Name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
		
	
}
