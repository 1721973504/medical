package com.gxuwz.medical.domain.peasant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gxuwz.medical.database.DbUtil;
import com.gxuwz.medical.domain.area.Area;
import com.gxuwz.medical.exception.DbException;

public class Peasant {
//	家庭编号、农合证号、医疗证卡号、户内编号、姓名、与户主关系、身份证号、性别、
//	健康状况、民族、文化程度、年龄、出生日期、人员属性、是否农村户口、职业、工作单位、联系电话、常住地址址、联系方式等
	private String familycode;
	private String cardNum;//农合证号
	private String MedicalNum;//医疗证卡号
	private String code;//户内编号
	private String name;
	private String Relations;//与户主关系
	private String idcard;
	private String sex;
	private String healthy;
	private String nation;
	private String education;
	private String age;
	private String birthday;
	private String attribute;//人员属性分为正式职工、合同工、兼职人员、临时工
	private String agricultural;
	private String profession;
	private String workunit;
	private String phone;
	private String address;//常住地址
	private String email;//联系方式
	private int status;
	private int grade;
	private int policyYear;
	
	private Peasant  parent;
	public Peasant(){
		
	}
	
	public Peasant(String familycode,String cardNum,String MedicalNum,String code,String name,String Relations,String idcard,String sex,
			String healthy,String nation,String education,String age,String birthday,String attribute,String agricultural,String profession,
			String workunit,String phone,String address,String email,int policyYear){
		this.familycode=familycode;
		this.cardNum=cardNum;
		this.MedicalNum=MedicalNum;
		this.code=code;
		this.name=name;
		this.Relations=Relations;
		this.idcard=idcard;
		this.sex=sex;
		this.healthy=healthy;
		this.nation=nation;
		this.education=education;
		this.age=age;
		this.birthday=birthday;
		this.attribute=attribute;
		this.agricultural=agricultural;
		this.profession=profession;
		this.workunit=workunit;
		this.phone=phone;	
		this.address=address;
		this.email=email;
		this.policyYear=policyYear;
	}
	
	
	
	public Peasant(String id)throws Exception{
		this.idcard =id;
		try{
			load();
		}catch(Exception e){
			e.printStackTrace();
			throw new DbException("无法找到ID="+this.idcard+"对应信息");
		}
	}
	
	private void load()throws SQLException{
		Connection conn=DbUtil.getConn();
		 PreparedStatement pstmt=null;
		 ResultSet rs=null;
		try{			
		 String sql="select * from t_peasant where idcard=?";
		 pstmt=conn.prepareStatement(sql);
		 pstmt.setString(1, this.idcard);
		 rs=pstmt.executeQuery();
		 if(rs.next()){
			 this.familycode=rs.getString("familycode");
			 this.cardNum=rs.getString("cardNum");
			 this.MedicalNum=rs.getString("MedicalNum");
			 this.code=rs.getString("code");
			 this.name =rs.getString("name");
			 this.Relations=rs.getString("Relations");
			 this.idcard =rs.getString("idcard");
			 this.sex =rs.getString("sex");
			 this.healthy =rs.getString("healthy");
			 this.nation =rs.getString("nation");
			 this.education =rs.getString("education");
			 this.age =rs.getString("age");
			 this.birthday =rs.getString("birthday");
			 this.attribute =rs.getString("attribute");
			 this.agricultural =rs.getString("agricultural");
			 this.profession=rs.getString("phone");
			 this.workunit =rs.getString("workunit");
			 this.phone =rs.getString("phone");
			 this.address =rs.getString("address");
			 this.email =rs.getString("email");
		 }
		}catch(SQLException e){
			throw e;
		}
		
	}
	

	/**
	 * 添加
	 */
	public void add(String familycode,String cardNum,String MedicalNum,String code,String name,String Relations,String idcard,String sex,
			String healthy,String nation,String education,String age,String birthday,String attribute,String agricultural,String profession,
			String workunit,String phone,String address,String email,int policyYear)throws Exception{
		Connection conn=null;
		try{
			//加载父节点ID的对象
			this.parent=new Peasant(code);
			this.familycode=familycode;
			this.MedicalNum=MedicalNum;
			this.grade=this.parent.getGrade()+1;
			this.code=createCode();
			this.name=name;
			this.Relations=Relations;
			this.idcard=idcard;
			this.sex=sex;
			this.healthy=healthy;
			this.nation=nation;
			this.education=education;
			this.age=age;
			this.birthday=birthday;
			this.attribute=attribute;
			this.agricultural=agricultural;
			this.profession=profession;
			this.workunit=workunit;
			this.phone=phone;	
			this.address=address;
			this.email=email;
			this.policyYear=policyYear;
			
			//自动编号
			this.cardNum=familycode+createCode();
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
			String  sql="insert into t_peasant(idcard,familycode,cardNum,MedicalNum,code,name,Relations,sex,healthy,"
					+ "nation,education,age,birthday,attribute,agricultural,profession,workunit,phone,address,email,grade)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			 //创建执行带动态参数的SQL的PreparedStatement pstmt
			 pstmt=conn.prepareStatement(sql);
			 //设置动态参数对应的值
			 int index=1;
			 pstmt.setString(index++, this.idcard);
			 pstmt.setString(index++, this.familycode);
			 pstmt.setString(index++, this.cardNum);
			 pstmt.setString(index++, this.MedicalNum);
			 pstmt.setString(index++, this.code);
			 pstmt.setString(index++, this.name);
			 pstmt.setString(index++, this.Relations);
			 pstmt.setString(index++, this.sex); 
			 pstmt.setString(index++, this.healthy);
			 pstmt.setString(index++, this.nation);
			 pstmt.setString(index++, this.education);
			 pstmt.setString(index++, this.age); 	
			 pstmt.setString(index++, this.birthday); 
			 pstmt.setString(index++, this.attribute);
			 pstmt.setString(index++, this.agricultural);
			 pstmt.setString(index++, this.profession); 
			 pstmt.setString(index++, this.workunit);
			 pstmt.setString(index++, this.phone);
			 pstmt.setString(index++, this.address);
			 pstmt.setString(index++, this.email);
			 pstmt.setInt(index++, this.grade);
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
			String  sql="update t_peasant set familycode=?,cardNum=?,MedicalNum=?,code=?,name=?,Relations=?,sex=?,healthy=?,"
					+ "nation=?,education=?,age=?,birthday=?,attribute=?,agricultural=?,profession=?,workunit=?,phone=?,address=?,email=? where idcard=?";
			 //创建执行带动态参数的SQL的PreparedStatement pstmt
			 pstmt=conn.prepareStatement(sql);
			 //设置动态参数对应的值
			 int index=1;
			 pstmt.setString(index++, this.familycode);
			 pstmt.setString(index++, this.cardNum);
			 pstmt.setString(index++, this.MedicalNum);
			 pstmt.setString(index++, this.code);
			 pstmt.setString(index++, this.name);
			 pstmt.setString(index++, this.Relations);
			 pstmt.setString(index++, this.sex); 
			 pstmt.setString(index++, this.healthy);
			 pstmt.setString(index++, this.nation);
			 pstmt.setString(index++, this.education);
			 pstmt.setString(index++, this.age); 	
			 pstmt.setString(index++, this.birthday); 
			 pstmt.setString(index++, this.attribute);
			 pstmt.setString(index++, this.agricultural);
			 pstmt.setString(index++, this.profession); 
			 pstmt.setString(index++, this.workunit);
			 pstmt.setString(index++, this.phone);
			 pstmt.setString(index++, this.address);
			 pstmt.setString(index++, this.email);
			 pstmt.setString(index++, this.idcard);	
			int count=pstmt.executeUpdate();		 
		}catch(SQLException e){
			throw e;
		}finally{
			DbUtil.close(pstmt);
		}
		
	}
	
	public void del(String idcard)throws SQLException{

		Connection conn=null;
       try{
    	   //1:对象属性赋值
    	    this.idcard =idcard;
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
			String  sql="delete from t_peasant where idcard=?";
			 //创建执行带动态参数的SQL的PreparedStatement pstmt
			 pstmt=conn.prepareStatement(sql);
			 //设置动态参数对应的值
			 int index=1;
			 pstmt.setString(index++, this.idcard);
		
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
    private String createCode()throws SQLException{
    		Connection conn =null;
    		PreparedStatement pstmt=null;
    		ResultSet rs=null;
    		String code=this.getFamilycode();
    		int grade=this.parent.getGrade()+1;
    		String sql="select max(code) from t_peasant where familycode like'"+code+"' " ;
    	
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
    			  maxcode=no;
    			}else{
    				String no=String.format("%02d", number);
    				maxcode=no;
    			}
    			return maxcode;
    		}catch(SQLException e){
    			
    			throw e;
    		}finally{
    			DbUtil.close(rs, pstmt, conn);
    		}
    	}


    private void holder(String familycode)throws SQLException{
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=DbUtil.getConn();
			pstmt=conn.prepareStatement("select * from t_peasant where familycode=? and code=01 ");
			int index=1;
			pstmt.setString(index, this.familycode);
			rs=pstmt.executeQuery();
			if(rs.next()){
				 this.familycode=rs.getString("familycode");
				 this.cardNum=rs.getString("cardNum");
				 this.MedicalNum=rs.getString("MedicalNum");
				 this.code=rs.getString("code");
				 this.name =rs.getString("name");
				 this.Relations=rs.getString("Relations");
				 this.idcard =rs.getString("idcard");
				 this.sex =rs.getString("sex");
				 this.healthy =rs.getString("healthy");
				 this.nation =rs.getString("nation");
				 this.education =rs.getString("education");
				 this.age =rs.getString("age");
				 this.birthday =rs.getString("birthday");
				 this.attribute =rs.getString("attribute");
				 this.agricultural =rs.getString("agricultural");
				 this.profession=rs.getString("phone");
				 this.workunit =rs.getString("workunit");
				 this.phone =rs.getString("phone");
				 this.address =rs.getString("address");
				 this.email =rs.getString("email");			
			}
		}catch(SQLException e){
			throw e;
		}finally{
			DbUtil.close(rs, pstmt, conn);
		}
	}  
	public String getFamilycode() {
		return familycode;
	}

	public void setFamilycode(String familycode) {
		this.familycode = familycode;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getMedicalNum() {
		return MedicalNum;
	}

	public void setMedicalNum(String medicalNum) {
		MedicalNum = medicalNum;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelations() {
		return Relations;
	}

	public void setRelations(String relations) {
		Relations = relations;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getHealthy() {
		return healthy;
	}

	public void setHealthy(String healthy) {
		this.healthy = healthy;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getAgricultural() {
		return agricultural;
	}

	public void setAgricultural(String agricultural) {
		this.agricultural = agricultural;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getWorkunit() {
		return workunit;
	}

	public void setWorkunit(String workunit) {
		this.workunit = workunit;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public Peasant getParent() {
		return parent;
	}

	public void setParent(Peasant parent) {
		this.parent = parent;
	}
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
	
	public int getPolicyYear() {
		return policyYear;
	}

	public void setPolicyYear(int policyYear) {
		this.policyYear = policyYear;
	}

	public static void main(String[]args)throws Exception{
		String number="01";
		System.out.println(Integer.parseInt(number));
	
		System.out.println(String.format("%02d", Integer.parseInt(number)));
	}

	
}
