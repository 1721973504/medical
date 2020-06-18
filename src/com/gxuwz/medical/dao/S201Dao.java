package com.gxuwz.medical.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.gxuwz.medical.domain.medical.*;
import java.util.List;
/**
 * 基础数据查询DAO类
 * @author 演示
 *
 */
public class S201Dao extends GenericDao<S201> {

	@Override
	protected S201 handle(ResultSet rs) throws SQLException {
		S201 entity=new S201();
		entity.setId(rs.getInt("id"));
		entity.setItemcode(rs.getString("itemcode"));
		entity.setItemname(rs.getString("itemname"));
		entity.setType(rs.getString("type"));
		return entity;
	}
	
	public List<S201> findListByType(String type)throws SQLException{
	
		String sql="select * from s201 where type=?";
		Object[]params={type};
		return super.queryOjects(sql, params);
	}

}
