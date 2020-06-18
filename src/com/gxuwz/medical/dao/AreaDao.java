package com.gxuwz.medical.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.gxuwz.medical.domain.area.Area;
import com.gxuwz.medical.exception.DbException;
/**
 * 行政区域查询接口
 * @author 1007065435@qq.com
 *
 */
public class AreaDao extends GenericDao<Area> {
	
	@Override
	protected Area handle(ResultSet rs) throws SQLException {
		try{
			Area model=new Area();
			model.setAreacode(rs.getString("areacode"));
			model.setAreaname(rs.getString("areaname"));
			model.setGrade(rs.getInt("grade"));
			return model;
		}catch(SQLException e){
			
			throw new SQLException("结果集转为实例失败!",e);
		}
	}
	
	public List<Area>findAll()throws DbException{
		try{
		  String sql="select * from t_area where 1=1";
		  Object[]params={};
		  return super.queryOjects(sql, params);
		}catch(SQLException e){
			
			throw new DbException("查找所有行政区域失败",e);
		}
	}
	/**
	 * 按级别查找行政区域列表
	 * @param grades
	 * @return
	 * @throws DbException
	 */
	public List<Area>findListByGrade(int[]grades)throws DbException{
		try{
			String sql="select * from t_area where 1=1";
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
			throw new DbException("按级别查找行政区域失败!",e);
		}
		
		
	}

}
