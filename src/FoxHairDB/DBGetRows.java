package FoxHairDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/*
 * 테이블과 속성을 매개변수로 받아 해당 속성에 있는 모든 값을 String Vector 값으로 반환 하는 함수 
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
	 * 특정 속성에 특정 값이 들어있는 테이블에 한 열을 검색하여 해당 열의 특정 속성 값이 무엇인지 문자열 형태로 반환 하는 함수 
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
	 * 특정 속성에 특정 값이 들어있는 테이블에 한 열을 검색하여 해당 열의 특정 속성 값이 무엇인지 문자열 형태로 반환 하는 함수 (위의 함수와 매개변수가 다르다 ) - 오버로딩 
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
