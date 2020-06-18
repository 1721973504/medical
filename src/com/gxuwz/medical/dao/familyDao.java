package com.gxuwz.medical.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.gxuwz.medical.domain.family.Family;
import com.gxuwz.medical.exception.DbException;
/**
 * 查询
 * @author
 *
 */
public class familyDao extends GenericDao<Family> {

	@Override
	protected Family handle(ResultSet rs) throws SQLException {
		try{
			String familycode=rs.getString("familycode");
			String countycode=rs.getString("countycode");
			String towncode=rs.getString("towncode");
		    String villagecode=rs.getString("villagecode");
			String groupcode=rs.getString("groupcode");
			String residence=rs.getString("residence");
			String holderName=rs.getString("holderName");
			String idcard=rs.getString("idcard");
			String familyNum=rs.getString("familyNum");
			String AgricultureNum=rs.getString("AgricultureNum");
			String address=rs.getString("address");
			String time=rs.getString("time");
			String registrarName=rs.getString("registrarName");
			  Family entity=new Family(familycode,countycode,towncode,villagecode,groupcode,
						residence,holderName,idcard,familyNum,AgricultureNum,address,time,registrarName);
		    return entity;
	    }catch(SQLException e){
		
		    throw new SQLException("结果集转为实例失败!",e);
	    }
   }

		public List<Family>findAll()throws DbException{
			try{
			  String sql="select * from t_family where 1=1";
			  Object[]params={};
			  return super.queryOjects(sql, params);
			}catch(SQLException e){
				
				throw new DbException("查找所有参合家庭档案失败",e);
			}
		}
		/**
		 * 按级别查找参合家庭档案列表
		 * @param grades
		 * @return
		 * @throws DbException
		 */
		public List<Family>findListByGrade(int[]grades)throws DbException{
			try{
				String sql="select * from t_family where 1=1";
				if(grades!=null){
					String grade="(1";
					for(int g:grades){
						grade=grade+","+g;
					}
					grade+=")";
					sql+=" and grade in "+grade;
				}
				Object[]params={};
				
				return super.queryOjects(sql, params);
			}catch(SQLException e){
				throw new DbException("按级别查找参合家庭档案失败!",e);
			}
			
			
		}

	}

