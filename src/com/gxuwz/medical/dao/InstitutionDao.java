package com.gxuwz.medical.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.gxuwz.medical.domain.area.Area;
import com.gxuwz.medical.domain.chronicdis.Chronicdis;
import com.gxuwz.medical.domain.institution.Institution;
import com.gxuwz.medical.exception.DbException;
/**
 * 农合经办机构查询Dao类
 * @author 演示
 *
 */
public class InstitutionDao extends GenericDao<Institution> {

	@Override
	protected Institution handle(ResultSet rs) throws SQLException {
		String areacode=rs.getString("areacode");
		String agencode=rs.getString("agencode");
		String agenname=rs.getString("agenname");
		int  grade =rs.getInt("grade");
		Area area=new Area(areacode);
		Institution entity=new Institution(area, agencode, agenname, grade);
		return entity;
	}



public List<Institution>findAll()throws DbException{
	try{
	  String sql="select * from t_institution where 1=1";
	  Object[]params={};
	  return super.queryOjects(sql, params);
	}catch(SQLException e){
		
		throw new DbException("查找失败",e);
	}
}
}
