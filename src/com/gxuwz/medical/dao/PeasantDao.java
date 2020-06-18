package com.gxuwz.medical.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.gxuwz.medical.database.DbUtil;
import com.gxuwz.medical.domain.peasant.Peasant;
import com.gxuwz.medical.exception.DbException;
/**
 * 户主查询DAO类
 * @author 演示
 *
 */
public class PeasantDao extends GenericDao<Peasant> {

	@Override
	protected Peasant handle(ResultSet rs) throws SQLException {
		Peasant entity=new Peasant();
		entity.setFamilycode(rs.getString("familycode"));
		entity.setCardNum(rs.getString("cardNum"));
		entity.setMedicalNum(rs.getString("MedicalNum"));
		entity.setCode(rs.getString("code"));
		entity.setName(rs.getString("name"));
		entity.setRelations(rs.getString("Relations"));
		entity.setIdcard(rs.getString("idcard"));
		entity.setSex(rs.getString("sex"));
		entity.setHealthy(rs.getString("healthy"));
		entity.setNation(rs.getString("nation"));
		entity.setEducation(rs.getString("education"));
		entity.setAge(rs.getString("age"));
		entity.setBirthday(rs.getString("birthday"));
		entity.setAttribute(rs.getString("attribute"));
		entity.setAgricultural(rs.getString("agricultural"));
		entity.setProfession(rs.getString("profession"));
		entity.setWorkunit(rs.getString("workunit"));
		entity.setPhone(rs.getString("phone"));
		entity.setAddress(rs.getString("address"));
		entity.setEmail(rs.getString("email"));
		entity.setPolicyYear(rs.getInt("policyYear"));
		return entity;
	}
	public List<Peasant> SearchName(String Name) {  
	  	  
        Connection conn = null;  
        Statement stmt = null;  
        List<Peasant> classList= new ArrayList<Peasant>();  
        try {  
            // 获取连接  
        	conn=DbUtil.getConn();
            // 整理一条SQL语句  
            String sql = "select * from t_peasant where code=01 and name ='";
            sql += Name + "'";
            // 创建执行sql的对象 
            stmt = conn.createStatement();  
            //执行sql语句  
            ResultSet rs =stmt.executeQuery(sql);  
            //遍历结果集  
            while(rs.next()){

				Peasant peasant=handle(rs);
				classList.add(peasant);		
			}
            return classList;
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return classList;  
    }
	
	public List<Peasant> Search(String name) {  
	  	  
        Connection conn = null;  
        Statement stmt = null;  
        List<Peasant> classList= new ArrayList<Peasant>();  
        try {  
            // 获取连接  
        	conn=DbUtil.getConn();
            // 整理一条SQL语句  
            String sql = "select * from t_peasant where  name ='";
            sql += name + "'";
            // 创建执行sql的对象 
            stmt = conn.createStatement();  
            //执行sql语句  
            ResultSet rs =stmt.executeQuery(sql);  
            //遍历结果集  
            while(rs.next()){

				Peasant peasant=handle(rs);
				classList.add(peasant);		
			}
            return classList;
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return classList;  
    }
	/*
	 * 用农合证号+身份证号+年度的方式查询参合登记表
	 */
	public List<Peasant> SearchPeople(String cardNum,String idcard,int policyYear) {  
	  	  
        Connection conn = null;  
        Statement stmt = null;  
        List<Peasant> classList= new ArrayList<Peasant>();  
        try {  
            // 获取连接  
        	conn=DbUtil.getConn();
            // 整理一条SQL语句  
            String sql = "select * from t_peasant where cardNum like '"+cardNum+"' AND idcard like '"+idcard+"' AND policyYear like '"+policyYear+"'";
            // 创建执行sql的对象 
            stmt = conn.createStatement();  
            //执行sql语句  
            ResultSet rs =stmt.executeQuery(sql);  
            //遍历结果集  
            while(rs.next()){

            	Peasant peasant=handle(rs);
				classList.add(peasant);		
			}
            return classList;
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return classList;  
    }
	
	public List<Peasant>findAll()throws DbException{
		try{
		  String sql="select * from t_peasant where 1=1";
		  Object[]params={};
		  return super.queryOjects(sql, params);
		}catch(SQLException e){
			
			throw new DbException("查找失败",e);
		}
	}
	/**
	 * 根据姓名模糊查询个人记录，考虑同名的情况
	 * @param name
	 * @param areacode
	 * @return
	 * @throws SQLException
	 */
	public List<Peasant> findByKeyWord(String name,String areacode)throws Exception{
		try{
			String sql="select * from t_peasant where familycode like'"
		             +areacode+"%' and name like'%"+name+"%'";
		    Object[]params=null;
			List<Peasant> peasants=super.queryOjects(sql, params);
			return peasants;
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	/**
	 * 根据家庭编号+年度查找未缴费人员列表
	 * @param famicode
	 * @param payYear
	 * @return
	 * @throws Exception
	 */
	public List<Peasant>findUnpayList(String familycode,int payYear)throws Exception{
		try{
			String sql="select * from t_peasant where familycode=? and "
					+ "cardNum not in"
					+ "(select cardNum from t_pay where and runyear=?)";
		    Object[]params={familycode,familycode,payYear};
			List<Peasant> persons=super.queryOjects(sql, params);
			return persons;
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	/**
	 * 根据名字查找户主信息
	 * @param name
	 * @param areacode
	 * @return
	 * @throws Exception
	 */
	public List<Peasant> findHostByName(String name,String areacode)throws Exception{
		try{
			String sql="select * from t_peasant where code=01 and familycode like'"
		             +areacode+"%' and name like'%"+name+"%'";
		    Object[]params=null;
			List<Peasant> persons=super.queryOjects(sql, params);
			return persons;
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
}

