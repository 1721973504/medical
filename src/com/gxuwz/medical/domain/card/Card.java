package com.gxuwz.medical.domain.card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gxuwz.medical.database.DbUtil;
import com.gxuwz.medical.domain.area.Area;
import com.gxuwz.medical.exception.DbException;

public class Card {
	//慢病证信息包括农合证号、慢病证号、身份证号、疾病名称、起始时间、终止时间。
	private String cardNum;//农合证号
	private String MedicalCard;
	private String name;	
	private String idcard;
	private String MedicalName;
	private String StartTime;
	private String EndTime;
	
	private Card  parent;
	public Card(){
		
	}
	
	public Card(String cardNum,String MedicalCard,String name,String idcard,String MedicalName,String StartTime,String EndTime){
		this.cardNum=cardNum;
		this.MedicalCard=MedicalCard;
		this.name=name;
		this.idcard=idcard;
		this.MedicalName=MedicalName;
		this.StartTime=StartTime;
		this.EndTime=EndTime;
	}
	
	
	
	public Card(String id)throws Exception{
		this.MedicalCard =id;
		try{
			load();
		}catch(Exception e){
			e.printStackTrace();
			throw new DbException("无法找到ID="+this.MedicalCard+"对应信息");
		}
	}
	
	private void load()throws SQLException{
		Connection conn=DbUtil.getConn();
		 PreparedStatement pstmt=null;
		 ResultSet rs=null;
		try{			
		 String sql="select * from t_card where MedicalCard=?";
		 pstmt=conn.prepareStatement(sql);
		 pstmt.setString(1, this.MedicalCard);
		 rs=pstmt.executeQuery();
		 if(rs.next()){
			 this.cardNum=rs.getString("cardNum");
			 this.MedicalCard=rs.getString("MedicalCard");
			 this.name =rs.getString("name");
			 this.idcard =rs.getString("idcard");
			 this.MedicalName =rs.getString("MedicalName");
			 this.StartTime =rs.getString("StartTime");
			 this.EndTime =rs.getString("EndTime");
			 
		 }
		}catch(SQLException e){
			throw e;
		}
		
	}
	

	/**
	 * 添加
	 */
	public void add(String cardNum,String MedicalCard,String name,String idcard,String MedicalName,String StartTime,String EndTime)throws Exception{
		Connection conn=null;
		try{
			this.parent=new Card(MedicalCard);
			this.cardNum=cardNum;
			this.MedicalCard=createMedicalCard();
			this.name=name;
			this.idcard=idcard;
			this.MedicalName=MedicalName;
			this.StartTime=StartTime;
			this.EndTime=EndTime;
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
	
	private void saveToDB(Connection conn)throws SQLException{
		PreparedStatement pstmt=null;
		try{
			//定义构造插入SQL语句字符串变量sql
			String  sql="insert into t_card(MedicalCard,cardNum,name,idcard,MedicalName,StartTime,EndTime)values(?,?,?,?,?,?,?)";
			 //创建执行带动态参数的SQL的PreparedStatement pstmt
			 pstmt=conn.prepareStatement(sql);
			 //设置动态参数对应的值
			 int index=1;
			 pstmt.setString(index++, this.MedicalCard);		
			 pstmt.setString(index++, this.cardNum);		
			 pstmt.setString(index++, this.name);		
			 pstmt.setString(index++, this.idcard);			 
			 pstmt.setString(index++, this.MedicalName);
			 pstmt.setString(index++, this.StartTime);
			 pstmt.setString(index++, this.EndTime);
			 int count=pstmt.executeUpdate();
			
		}catch(SQLException e){
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
			String  sql="update t_card set cardNum=?,name=?,idcard=?,MedicalName=?,StartTime=?,EndTime=? where MedicalCard=?";
			 //创建执行带动态参数的SQL的PreparedStatement pstmt
			 pstmt=conn.prepareStatement(sql);
			 //设置动态参数对应的值
			 int index=1;				
			 pstmt.setString(index++, this.cardNum);		
			 pstmt.setString(index++, this.name);		
			 pstmt.setString(index++, this.idcard);			 
			 pstmt.setString(index++, this.MedicalName);
			 pstmt.setString(index++, this.StartTime);
			 pstmt.setString(index++, this.EndTime);
			 pstmt.setString(index++, this.MedicalCard);	
			int count=pstmt.executeUpdate();		 
		}catch(SQLException e){
			throw e;
		}finally{
			DbUtil.close(pstmt);
		}
		
	}
	
	public void del(String MedicalCard)throws SQLException{

		Connection conn=null;
       try{
    	   //1:对象属性赋值
    	    this.MedicalCard =MedicalCard;
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
			String  sql="delete from t_card where MedicalCard=?";
			 //创建执行带动态参数的SQL的PreparedStatement pstmt
			 pstmt=conn.prepareStatement(sql);
			 //设置动态参数对应的值
			 int index=1;
			 pstmt.setString(index++, this.MedicalCard);
		
			int count=pstmt.executeUpdate();
			
		}catch(SQLException e){
			throw e;
		}finally{
			DbUtil.close(pstmt);
			
		}		
	}
	
	/**
     * 自动生成编号
     * @return
     * @throws SQLException
     */
    private String createMedicalCard()throws SQLException{
    		Connection conn =null;
    		PreparedStatement pstmt=null;
    		ResultSet rs=null;
    		String MedicalCard=this.getCardNum();
    		String sql="select max(MedicalCard) from t_card where cardNum like'"+MedicalCard+"' " ;
    	
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
    			  maxcode=this.getCardNum()+no;
    			}else{
    				String no=String.format("%02d", number);
    				maxcode=this.getCardNum()+no;
    			}
    			return maxcode;
    		}catch(SQLException e){
    			
    			throw e;
    		}finally{
    			DbUtil.close(rs, pstmt, conn);
    		}
    	}
	
	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getMedicalCard() {
		return MedicalCard;
	}

	public void setMedicalCard(String medicalCard) {
		MedicalCard = medicalCard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getMedicalName() {
		return MedicalName;
	}

	public void setMedicalName(String medicalName) {
		MedicalName = medicalName;
	}

	public String getStartTime() {
		return StartTime;
	}

	public void setStartTime(String startTime) {
		StartTime = startTime;
	}

	public String getEndTime() {
		return EndTime;
	}

	public void setEndTime(String endTime) {
		EndTime = endTime;
	}

	public Card getParent() {
		return parent;
	}

	public void setParent(Card parent) {
		this.parent = parent;
	}

	public static void main(String[]args)throws Exception{
		String number="01";
		System.out.println(Integer.parseInt(number));
	
		System.out.println(String.format("%02d", Integer.parseInt(number)));
	}

	
}
