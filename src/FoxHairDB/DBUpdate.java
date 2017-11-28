package FoxHairDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * 테이블에서 값을 바꾸는 함수 이다. 
 * */
public class DBUpdate {
	public static void DBupdate(Connection conn, String table, String searchType, String searchV, String insertType, String insertV) {
		PreparedStatement pstm = null;
		
		String sql = "update "+table+" set " + insertType + "='"+insertV+"' where " +searchType+"='"+searchV+"'"; 
		try {
			System.out.println(sql);
			pstm=conn.prepareStatement(sql);
			pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
