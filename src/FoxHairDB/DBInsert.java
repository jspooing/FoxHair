package FoxHairDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * ���̺� �����͸� �����ϴ� �Լ� 
 * */
public class DBInsert {
	public static void DBInsert(Connection conn,String table,String[] cols,String[] v) {
		PreparedStatement pstm = null;
		String sql = "insert into " + table+"(";
		for(int i=0; i < cols.length;i++) {
			sql= sql+ cols[i];
			if(i!=cols.length-1)
				sql=sql+",";
		}
		try {
			//������ ���̺��� �ԷµǴ� �������� ���°� �ٸ��� ������ if ������ �����Ͽ� ���̺��� Ư���� �°� �Է� �� �� �ֵ��� �Ͽ���. 
			if(table.equals("customer")) {
			sql = sql + ") values(?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, Integer.parseInt(v[0]));
			pstm.setString(2, v[1]);
			pstm.setString(3, v[2]);
			pstm.setInt(4, Integer.parseInt(v[3]));}
			else if(table.equals("reserve")) {
				sql = sql + ") values(?,TO_DATE(?,'YYYY-MM-DD HH24:MI:SS'),?,?)";
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1, Integer.parseInt(v[0]));
				pstm.setString(2,v[1]);
				pstm.setString(3, v[2]);
				pstm.setInt(4, Integer.parseInt(v[3]));
			}
			else if(table.equals("product")) {
				sql = sql + ",stock) values(?,?,?,?,?,?,?)";
				pstm =conn.prepareStatement(sql);
				pstm.setInt(1, Integer.parseInt(v[0]));
				pstm.setString(2,v[1]);
				pstm.setInt(3,Integer.parseInt(v[2]));
				pstm.setInt(4, Integer.parseInt(v[3]));
				pstm.setString(5, v[4]);
				pstm.setString(6, v[5]);
				pstm.setInt(7, 0);
			}
			else if(table.equals("supplier")) {
				sql = sql + ") values(?,?,?)";
				pstm =conn.prepareStatement(sql);
				pstm.setString(1, v[0]);
				pstm.setString(2, v[1]);
				pstm.setString(3, v[2]);
			}
			else if(table.equals("trans_action")) {
				sql = sql + ") values(?,?,TO_DATE(?,'YYYY-MM-DD HH24:MI:SS'),?)";
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1,Integer.parseInt(v[0]));
				pstm.setInt(2, Integer.parseInt(v[1]));
				pstm.setString(3, v[2]);
				pstm.setString(4, v[3]);
			}
			else if(table.equals("sale")) {
				sql = sql + ") values(?,?,?,?)";
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1, Integer.parseInt(v[0]));
				pstm.setInt(2, Integer.parseInt(v[1]));
				pstm.setInt(3, Integer.parseInt(v[2]));
				pstm.setInt(4, Integer.parseInt(v[3]));
				
			}
			else if(table.equals("styling")) {
				sql = sql + ") values(?,?,?,?)";
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1, Integer.parseInt(v[0]));
				pstm.setString(2, v[1]);
				pstm.setString(3, v[2]);
				pstm.setInt(4, Integer.parseInt(v[3]));
			}
			System.out.println(sql);
			pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//���̺� �����͸� �Է� �� �� pk �� ��ġ�� �ʵ��� �ϱ� ���Ͽ� ���� ū �� �� ���ϴ� �Լ��̴�. 
	public static int getMaxNum(Connection conn,String table,String col) {
		int maxNum=0;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select max("+col+") from "+ table;
		try {
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			rs.next();
			maxNum = rs.getInt("max("+ col +")");
			System.out.println("maxNum = " + maxNum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return maxNum;
	}
}
