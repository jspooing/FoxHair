package FoxHairDB;
import java.sql.*;

public class DBLogin
{
    public static Connection dbConn;
    
        public static Connection getConnection(String user,String pw)
        {
        	Connection conn = null;
            try {
               
                String url = "jdbc:oracle:thin:@localhost:1521:XE";
                                           //@ ip�ּ�:��Ʈ��ȣ:DB�̸�
                Class.forName("oracle.jdbc.driver.OracleDriver");        
                conn = DriverManager.getConnection(url, user, pw);
                
                System.out.println("Database�� ����Ǿ����ϴ�.\n");
                
            } catch (ClassNotFoundException cnfe) {
                System.out.println("DB ����̹� �ε� ���� :"+cnfe.toString());
            } catch (SQLException sqle) {
                System.out.println("DB ���ӽ��� : "+sqle.toString());
            } catch (Exception e) {
                System.out.println("Unkonwn error");
                e.printStackTrace();
            }
            return conn;     
        }
}

