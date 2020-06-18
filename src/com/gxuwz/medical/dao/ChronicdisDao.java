package com.gxuwz.medical.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import  com.gxuwz.medical.domain.chronicdis.Chronicdis;
import com.gxuwz.medical.exception.DbException;
/**
 * 慢性病信息查询DAO类
 * @author 演示
 *
 */
public class ChronicdisDao extends GenericDao<Chronicdis> {

	@Override
	protected Chronicdis handle(ResultSet rs) throws SQLException {
		
		Chronicdis entity=new Chronicdis();
		
		entity.setIllcode(rs.getString("illcode"));//疾病编码
		entity.setIllname(rs.getString("illname"));//疾病名称
		entity.setPycode(rs.getString("pycode"));//拼音码
		entity.setWbcode(rs.getString("wbcode"));//五笔码
		
		return entity;
	}


	public List<Chronicdis>findAll()throws DbException{
		try{
		  String sql="select * from t_chronicdis where 1=1";
		  Object[]params={};
		  return super.queryOjects(sql, params);
		}catch(SQLException e){
			
			throw new DbException("查找失败",e);
		}
	}
	
}
