package FoxHairDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;
//import javax.xml.crypto.Data;

public class DBSelect
{
	public static DefaultTableModel select(Connection conn,String txtSearch,String Type,DefaultTableModel model)
	{
		DefaultTableModel m = model;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {

			String quary = "SELECT * FROM customer Where "+Type+"='"+txtSearch+"'"  ;

			pstm = conn.prepareStatement(quary);
			rs = pstm.executeQuery();
			

			while(rs.next()){
				m.addRow(new Object[] {rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)});
			}

		} catch (SQLException sqle) {
			System.out.println("SELECT������ ���� �߻�");
			sqle.printStackTrace();
		}  
		//            finally{
		//                // DB ������ �����Ѵ�.
		//                try{
		//                    if ( rs != null ){rs.close();}   
		//                    if ( pstm != null ){pstm.close();}   
		//                    if ( conn != null ){conn.close(); }
		//                }catch(Exception e){
		//                    throw new RuntimeException(e.getMessage());
		//                }
		//                
		//            }
		return m;
	}

	public static String selectCol(Connection conn,String Type ,String Search,String outCol) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		System.out.println("Select Search : " + Search);
		String output="";
		try {

			String quary = "SELECT * FROM customer Where "+Type+"='"+Search+"'"  ;
			System.out.println( "SELECT * FROM customer Where "+Type+"='"+Search+"'" );
			pstm = conn.prepareStatement(quary);
			rs = pstm.executeQuery();
			output = rs.getString(outCol);
			

		} catch (SQLException sqle) {
			System.out.println("SELECT������ ���� �߻�");
			sqle.printStackTrace();
		}  
		
		return output;
	}}

