package com.gxuwz.medical.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gxuwz.medical.database.DbUtil;
import com.gxuwz.medical.domain.menu.Menu;
import com.gxuwz.medical.domain.vo.Page;

/**
 * 菜单管理DAO
 * @author 演示
 *
 */
public class MenuDao extends BaseDao {

	@Override
	protected Menu handle(ResultSet rs) throws SQLException {
		Menu menu=new Menu();
		menu.setMenuid(rs.getString("menuid"));
		menu.setMenupid(rs.getString("menupid"));
		menu.setMenuname(rs.getString("menuname"));
		menu.setUrl(rs.getString("url"));
		menu.setLevel(rs.getInt("level"));
		return menu;
		
	}
	/**
	 * 分页
	 * @param sql
	 * @param params
	 * @param page
	 * @param size
	 * @return
	 * @throws SQLException
	 */
		public Page queryOjectsByPage(String sql,Object[]params,int pageNo,int size)throws SQLException{
			Connection conn =null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			Page page=null;
			List<Menu> datas=new ArrayList<Menu>();
			try{
				int start=(pageNo-1)*size;
				conn=DbUtil.getConn();
				pstmt=conn.prepareStatement(sql+" limit "+start+","+size);
				int index=1;
				if(params!=null){
				  for(Object param:params){
					  if(param instanceof String){
						  pstmt.setString(index++, (String)param);
					  }
					  if(param instanceof Integer){
						  pstmt.setInt(index++, (Integer)param);
					  }
					  if(param instanceof Float){
						  pstmt.setFloat(index++, (Float)param);
					  }
				  }
				}
				rs=pstmt.executeQuery();
				while(rs.next()){
					Menu m=handle(rs);
					datas.add(m);
				}
				//统计SQL对应记录数
				int total=count(sql, params);
				page=new Page(total, pageNo, size, datas);
				return page;
			}catch(SQLException e){
				throw new SQLException("执行SQL["+sql+"]失败",e);
			}
			
		}
		

}
