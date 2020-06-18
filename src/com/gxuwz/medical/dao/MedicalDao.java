package com.gxuwz.medical.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.gxuwz.medical.domain.medical.Medical;

public class MedicalDao extends GenericDao<Medical> {

	@Override
	protected Medical handle(ResultSet rs) throws SQLException {
		
		Medical entity =new Medical();
		entity.setJgbm(rs.getString("jgbm"));
		entity.setZzjgbm(rs.getString("zzjgbm"));
		entity.setJgmc(rs.getString("jgmc"));
		entity.setDqbm(rs.getString("dqbm"));
		entity.setAreacode(rs.getString("areacode"));
		entity.setLsgx(rs.getString("lsgx"));
		entity.setJgjb(rs.getString("jgjb"));
		entity.setSbddlx(rs.getString("sbddlx"));
		entity.setPzddlx(rs.getString("pzddlx"));
		entity.setSsjjlx(rs.getString("ssjjlx"));
		entity.setWsjgdl(rs.getString("wsjgdl"));
		entity.setWsjgxl(rs.getString("wsjgxl"));
		entity.setZgdw(rs.getString("zgdw"));
		entity.setKysj(rs.getDate("kysj"));
		entity.setFrdb(rs.getString("frdb"));
		entity.setZczj(rs.getDouble("zczj"));
		return entity;

	}

}
