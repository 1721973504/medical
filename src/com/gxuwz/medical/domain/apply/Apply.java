package com.gxuwz.medical.domain.apply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gxuwz.medical.database.DbUtil;
import com.gxuwz.medical.domain.pay.Pay;
import com.gxuwz.medical.exception.DbException;
import com.gxuwz.medical.exception.PayperiodUnfoundException;
import com.gxuwz.medical.tools.DateUtil;


public class Apply {
	//疾病名称、医疗总费用、医院发票号、就诊时间为必填项.用身份证号+疾病名称+就诊时间在慢性病证表中判断是否存在记录
	private int policyYear;	
	private String proportion;
	private String amount;//报销费用
	private String total;//医疗总费用
	private String Name;
	private String InvoiceNum;
	private String idcard;
	private String Time;
	private String MedicalName;
	private String CreatTime;
	private String areacode;
	public Apply(){
		
	}
	public Apply(String InvoiceNum)throws DbException{
		try{
			this.InvoiceNum=InvoiceNum;
			loadDB(InvoiceNum);
		}catch(SQLException e){
			throw new DbException("加载id="+InvoiceNum+"失败", e);
		}
		
	}
	public Apply(String InvoiceNum,String MedicalName,String idcard,String Name,String total,String amount,String Time,String CreatTime,String areacode){
		super();
		this.InvoiceNum=InvoiceNum;
		this.MedicalName=MedicalName;
		this.idcard=idcard;
		this.Name=Name;
		this.total=total;
		this.amount=amount;
		this.Time=Time;
		this.CreatTime=CreatTime;
		this.areacode=areacode;
	}
	
	public void addApply(String InvoiceNum,String MedicalName,String idcard,String Name,String total,String amount,String Time,String CreatTime,String areacode)throws Exception{
		Connection conn=null;
		try{
			
			this.InvoiceNum=InvoiceNum;
			this.MedicalName=MedicalName;
			this.idcard=idcard;
			this.Name=Name;
			this.total=total;
			this.amount=amount;
			this.Time=Time;
			this.CreatTime=CreatTime;
			this.areacode=areacode;
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
			sqlBuff.append("insert into t_apply(InvoiceNum,MedicalName,idcard,Name,total,amount,Time,CreatTime,areacode)");
			sqlBuff.append("values(?,?,?,?,?,?,?,?,?)");
			String sql=sqlBuff.toString();
			pstmt=connection.prepareStatement(sql);
			//依次设置动态参数的值，注意顺序
			int index=1;
			pstmt.setString(index++, this.InvoiceNum);
			pstmt.setString(index++, this.MedicalName);
			pstmt.setString(index++, this.idcard);
			pstmt.setString(index++, this.Name);
			pstmt.setString(index++, this.total);
			pstmt.setString(index++, this.amount);
			pstmt.setString(index++, this.Time);
			pstmt.setString(index++, this.CreatTime);
			pstmt.setString(index++, this.areacode);
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
	/**
	 * 审核
	 * @param InvoiceNum
	 * @throws SQLException
	 */
	public void addApplyCheck(String InvoiceNum,String MedicalName,String idcard,String Name,String total,String amount,String Time,String CreatTime,String areacode)throws Exception{
		Connection conn=null;
		try{
			
			this.InvoiceNum=InvoiceNum;
			this.MedicalName=MedicalName;
			this.idcard=idcard;
			this.Name=Name;
			this.total=total;
			this.amount=amount;
			this.Time=Time;
			this.CreatTime=CreatTime;
			this.areacode=areacode;
		   //保存导数据库
			conn=DbUtil.getConn();
			conn.setAutoCommit(false);
			//调用插入数据库私有方法
			this.saveToCheck(conn);
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
	private void saveToCheck(Connection connection)throws SQLException{
		PreparedStatement pstmt=null;
		try{

			StringBuffer sqlBuff=new StringBuffer();
			sqlBuff.append("insert into t_applycheck(InvoiceNum,MedicalName,idcard,Name,total,amount,Time,CreatTime,areacode)");
			sqlBuff.append("values(?,?,?,?,?,?,?,?,?)");
			String sql=sqlBuff.toString();
			pstmt=connection.prepareStatement(sql);
			//依次设置动态参数的值，注意顺序
			int index=1;
			pstmt.setString(index++, this.InvoiceNum);
			pstmt.setString(index++, this.MedicalName);
			pstmt.setString(index++, this.idcard);
			pstmt.setString(index++, this.Name);
			pstmt.setString(index++, this.total);
			pstmt.setString(index++, this.amount);
			pstmt.setString(index++, this.Time);
			pstmt.setString(index++, this.CreatTime);
			pstmt.setString(index++, this.areacode);
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
	/**
	 * 支付
	 * @param InvoiceNum
	 * @throws SQLException
	 */
	public void addApplyPay(String InvoiceNum,String MedicalName,String idcard,String Name,String total,String amount,String Time,String CreatTime,String areacode)throws Exception{
		Connection conn=null;
		try{
			
			this.InvoiceNum=InvoiceNum;
			this.MedicalName=MedicalName;
			this.idcard=idcard;
			this.Name=Name;
			this.total=total;
			this.amount=amount;
			this.Time=Time;
			this.CreatTime=CreatTime;
			this.areacode=areacode;
		   //保存导数据库
			conn=DbUtil.getConn();
			conn.setAutoCommit(false);
			//调用插入数据库私有方法
			this.saveToPay(conn);
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
	private void saveToPay(Connection connection)throws SQLException{
		PreparedStatement pstmt=null;
		try{

			StringBuffer sqlBuff=new StringBuffer();
			sqlBuff.append("insert into t_applypay(InvoiceNum,MedicalName,idcard,Name,total,amount,Time,CreatTime,areacode)");
			sqlBuff.append("values(?,?,?,?,?,?,?,?,?)");
			String sql=sqlBuff.toString();
			pstmt=connection.prepareStatement(sql);
			//依次设置动态参数的值，注意顺序
			int index=1;
			pstmt.setString(index++, this.InvoiceNum);
			pstmt.setString(index++, this.MedicalName);
			pstmt.setString(index++, this.idcard);
			pstmt.setString(index++, this.Name);
			pstmt.setString(index++, this.total);
			pstmt.setString(index++, this.amount);
			pstmt.setString(index++, this.Time);
			pstmt.setString(index++, this.CreatTime);
			pstmt.setString(index++, this.areacode);
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
	public void del(String InvoiceNum)throws SQLException{

		Connection conn=null;
       try{
    	   //1:对象属性赋值
    	    this.InvoiceNum =InvoiceNum;
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
			String  sql="delete from t_applypay where InvoiceNum=?";
			 //创建执行带动态参数的SQL的PreparedStatement pstmt
			 pstmt=conn.prepareStatement(sql);
			 //设置动态参数对应的值
			 int index=1;
			 pstmt.setString(index++, this.InvoiceNum);
		
			int count=pstmt.executeUpdate();
			
		}catch(SQLException e){
			throw e;
		}finally{
			DbUtil.close(pstmt);
			
		}		
	}
	private void loadDB(String InvoiceNum)throws SQLException{
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=DbUtil.getConn();
			pstmt=conn.prepareStatement("select * from t_apply where InvoiceNum=?");
			int index=1;
			pstmt.setString(index, this.InvoiceNum);
			rs=pstmt.executeQuery();
			if(rs.next()){
				this.InvoiceNum=rs.getString("InvoiceNum");
				this.MedicalName=rs.getString("MedicalName");
				this.idcard=rs.getString("idcard");
				this.Name=rs.getString("Name");
				this.total=rs.getString("total");
				this.amount=rs.getString("amount");
				this.Time=rs.getString("Time");		
				this.CreatTime=rs.getString("CraetTime");	
				this.areacode=rs.getString("areacode");	
			}
		}catch(SQLException e){
			throw e;
		}finally{
			DbUtil.close(rs, pstmt, conn);
		}
	}  
	
	
	public int getPolicyYear() {
		return policyYear;
	}
	public void setPolicyYear(int policyYear) {
		this.policyYear = policyYear;
	}
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getInvoiceNum() {
		return InvoiceNum;
	}
	public void setInvoiceNum(String invoiceNum) {
		InvoiceNum = invoiceNum;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
	public String getMedicalName() {
		return MedicalName;
	}
	public void setMedicalName(String medicalName) {
		MedicalName = medicalName;
	}
	public String getCreatTime() {
		return CreatTime;
	}
	public void setCreatTime(String creatTime) {
		CreatTime = creatTime;
	}
	public String getAreacode() {
		return areacode;
	}
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}
	
	
	
}
