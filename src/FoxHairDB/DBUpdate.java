package FoxHairDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * ���̺��� ���� �ٲٴ� �Լ� �̴�. 
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
