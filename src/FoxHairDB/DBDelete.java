package FoxHairDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * 테이블, 칼럼, 값 을 매개변수로 받아 delete from 테이블 where 칼럼 = 값  명령어를 수행 해 주는 함수 
 * */
public class DBDelete {
	public static void DBDelete(Connection conn, String table, String Type, String delV) {
		PreparedStatement pstm = null;
		String sql = "delete from "+ table +" where "+ Type +" = '"+delV+"'";
		System.out.println(sql);
		try {
			pstm = conn.prepareStatement(sql);
			pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
