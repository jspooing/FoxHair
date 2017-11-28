package FoxHairDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/*
 * ���̺�� �Ӽ��� �Ű������� �޾� �ش� �Ӽ��� �ִ� ��� ���� String Vector ������ ��ȯ �ϴ� �Լ� 
 * */
public class DBGetRows {
	public static Vector getRows(Connection conn, String table, String Cols) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select "+Cols+ " from "+ table;
		System.out.println(sql);
		Vector<String> v = new Vector<String>();
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next())
			v.add(rs.getString(1));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0; i < v.size(); i ++) {
			System.out.print(v.get(i)+" ");
		}
		return v;
	}
	
	
	/*
	 * Ư�� �Ӽ��� Ư�� ���� ����ִ� ���̺� �� ���� �˻��Ͽ� �ش� ���� Ư�� �Ӽ� ���� �������� ���ڿ� ���·� ��ȯ �ϴ� �Լ� 
	 * */
	public static String getRowValue(Connection conn, String table, String Cols , String scType, int scValue) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select "+Cols+ " from "+ table + " where "+scType+"="+scValue;
		System.out.println(sql);
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			rs.next();
			return rs.getString(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	
	/*
	 * Ư�� �Ӽ��� Ư�� ���� ����ִ� ���̺� �� ���� �˻��Ͽ� �ش� ���� Ư�� �Ӽ� ���� �������� ���ڿ� ���·� ��ȯ �ϴ� �Լ� (���� �Լ��� �Ű������� �ٸ��� ) - �����ε� 
	 * */
	public static String getRowValue(Connection conn, String table, String cols, String scType, String string) {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select "+cols+ " from "+ table + " where "+scType+"='"+string+"'";
		System.out.println(sql);
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			rs.next();
			return rs.getString(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
		
	}

}
