package FoxHairDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;

public class DBSelect
{
	
	/*
	 * ���̺��� DefaultTableModel�� �ҷ����� �Լ��̴�. �ڹ� ���̺� ǥ�� �ϱ� ���� ����� ����. 
	 * */
	public static DefaultTableModel select(Connection conn,String table ,String txtSearch,String Type,DefaultTableModel model)
	{
		DefaultTableModel m = model;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String quary = null;

		try {
			//�� ���� ���α׷����� date���� �˻��ϴ� Ư���� ��츦 if������ ó�� �Ͽ���.
			if(!Type.equals("ta_date")) {
			quary = "SELECT * FROM "+table+" Where "+Type+"='"+txtSearch+"'"  ;
			}
			else {
				//���� �˻��� �� ��� �˻� ��¥�� �Ͽ� 00 ���� �־� ���� �˻��� �����Ͽ� �Լ� ���ο��� if������ ó���Ѵ�. 
				if(!txtSearch.substring(txtSearch.length()-2,txtSearch.length()).equals("00")) {
				quary = "SELECT * FROM "+table+" Where "+Type+">=to_date('"+txtSearch+"','yyyy-mm-dd')"  ;
				quary +=" and "+Type+"<to_date('"+txtSearch+ "','yyyy-mm-dd')+1 order by ta_num";
				}
				else {
					txtSearch = txtSearch.substring(0,7);
					System.out.println(txtSearch);
					quary = "SELECT * FROM "+table+" Where "+Type+">=to_date('"+txtSearch+"','yyyy-mm')"  ;
					quary +=" and "+Type+"<add_months(to_date('"+txtSearch+ "','yyyy-mm'),1) order by ta_num";
				}
			}
			//�ƹ��͵� �Է����� �ʰų� *�� ������ �˻� ��ư�� ���� ��� �ش� ���̺��� ��� ���� ��� �Ѵ�. 
			if(txtSearch.equals("*")||txtSearch.equals("")) {
				quary = "select * from "+table+" order by "+ Type;
			}
			pstm = conn.prepareStatement(quary);
			rs = pstm.executeQuery();

			if(table.equals("customer")){
				while(rs.next()){

					m.addRow(new Object[] {rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)});
				}
			}
			else if(table.equals("product")) {
				while(rs.next()) {
					m.addRow(new Object[] {rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getInt(7)});
				}}
			else if(table.equals("staff")) {
				while(rs.next()) {
					m.addRow(new Object[] {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getInt(6)});
				}}
			else if(table.equals("trans_action")) {
				while(rs.next()) {
					m.addRow(new Object[] {rs.getInt(1),rs.getInt(2),rs.getDate(3),rs.getString(4)});
				}}
			

		} catch (SQLException sqle) {
			System.out.println("SELECT������ ���� �߻�");
			sqle.printStackTrace();
		}  
		return m;
	}

	/*
	 * Ư�� ������ �����ϴ� ������ �ϳ��� �Ӽ����� ��� �ϴ� �Լ��̴�. 
	 * */
	public static String selectCol(Connection conn,String Type ,String Search,String outCol) {
		PreparedStatement pstm= null;
		ResultSet rs = null;
		
		String output="";
		try {

			String quary = "SELECT * FROM customer Where "+Type+"='"+Search+"'"  ;
			pstm = conn.prepareStatement(quary);
			rs = pstm.executeQuery();
			rs.next();
			output = rs.getString(outCol);


		} catch (SQLException sqle) {
			System.out.println("SELECT������ ���� �߻�");
			sqle.printStackTrace();
		}  

		return output;
	}

	/*
	 * select �Լ��� join�� �߰��� �Լ��̴� ���̺� �ΰ��� ���� �Ͽ� ���� �˻� �Ͽ� ��� �Ѵ�. 
	 * */
	public static DefaultTableModel selectJoin(Connection conn,String table1,String table2 ,
			String joinType , String[] Cols, String txtSearch ,String srcType ,DefaultTableModel model)
	{
		DefaultTableModel m = model;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {

			String quary = "SELECT ";
			for(int i=0 ; i < Cols.length; i++) {
				quary +=Cols[i];
				if(i != Cols.length-1)quary+=",";
			}
			quary += " from "+table1+","+table2;
			quary += " where "+table1+"."+joinType+"="+table2+"."+joinType;

			if(!(txtSearch.equals("*")||txtSearch.equals(""))) {
				if(srcType.equals("r_date")) {
					
					
					quary +=" and "+srcType+">=to_date('"+txtSearch+ "','yyyy-mm-dd ')";
					quary +=" and "+srcType+"<to_date('"+txtSearch+ "','yyyy-mm-dd ')+1";
				}
				else {
					quary +=" and "+srcType+"='"+txtSearch+ "' ";
				}
				quary += " order by " + srcType;
			}
			else {

				quary += " order by " + srcType;
			}
			System.out.println(quary);
			pstm = conn.prepareStatement(quary);
			rs = pstm.executeQuery();

			if(table1.equals("reserve")&&table2.equals("customer")) {
				while(rs.next()) {
					m.addRow(new Object[] {rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)});
				}
			}

			return m;

		} catch (SQLException sqle) {
			System.out.println("SELECT������ ���� �߻�");
			sqle.printStackTrace();
		}  

		return m;
	}


}

