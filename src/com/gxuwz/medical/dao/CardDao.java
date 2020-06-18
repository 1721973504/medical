package com.gxuwz.medical.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.gxuwz.medical.database.DbUtil;
import com.gxuwz.medical.domain.card.Card;
import com.gxuwz.medical.domain.pay.Pay;
/**
 * @author 演示
 *
 */
public class CardDao extends GenericDao<Card> {

	@Override
	protected Card handle(ResultSet rs) throws SQLException {
		Card entity=new Card();
		entity.setMedicalCard(rs.getString("MedicalCard"));
		entity.setCardNum(rs.getString("cardNum"));
		entity.setName(rs.getString("name"));
		entity.setIdcard(rs.getString("idcard"));
		entity.setMedicalName(rs.getString("MedicalName"));
		entity.setStartTime(rs.getString("StartTime"));
		entity.setEndTime(rs.getString("EndTime"));
		return entity;
	}
	
	//查询，用身份证号+疾病名称+就诊时间在慢性病证表中判断是否存在记录
	public List<Card> SearchMessage(String idcard,String MedicalName,String Time) {  
	  	  
        Connection conn = null;  
        Statement stmt = null;  
        List<Card> classList= new ArrayList<Card>();  
        try {  
            // 获取连接  
        	conn=DbUtil.getConn();
            // 整理一条SQL语句  
            String sql = "select * from t_card where idcard like '"+idcard+"' AND MedicalName like '"+MedicalName+"' AND '"+Time+"'>=StartTime AND '"+Time+"'<=EndTime";
            // 创建执行sql的对象 
            stmt = conn.createStatement();  
            //执行sql语句  
            ResultSet rs =stmt.executeQuery(sql);  
            //遍历结果集  
            while(rs.next()){

            	Card card=handle(rs);
				classList.add(card);		
			}
            return classList;
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return classList;  }
}
