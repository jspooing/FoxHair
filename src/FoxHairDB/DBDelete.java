package FoxHairDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * ���̺�, Į��, �� �� �Ű������� �޾� delete from ���̺� where Į�� = ��  ��ɾ ���� �� �ִ� �Լ� 
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
