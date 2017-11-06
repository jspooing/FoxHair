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


class DBSelect
{
        public static void main(String[] args)
        {
            Connection conn = null;
            PreparedStatement pstm = null;
            ResultSet rs = null;
            
            try {
          
                String quary = "SELECT * FROM student";
                
                conn = DBLogin.getConnection("htetmyet","1234");
                pstm = conn.prepareStatement(quary);
                rs = pstm.executeQuery();
                
                
                System.out.println("ID NAME DPT P_NUMBER");
                System.out.println("=====================");
                
                while(rs.next()){
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    String dpt = rs.getString(3);
                    String pnum = rs.getString(4);
                    
                    String result = id+" "+name+" "+dpt+" "+pnum;
                    System.out.println(result);
                }
                
            } catch (SQLException sqle) {
                System.out.println("SELECT������ ���� �߻�");
                sqle.printStackTrace();
                
            }finally{
                // DB ������ �����Ѵ�.
                try{
                    if ( rs != null ){rs.close();}   
                    if ( pstm != null ){pstm.close();}   
                    if ( conn != null ){conn.close(); }
                }catch(Exception e){
                    throw new RuntimeException(e.getMessage());
                }
                
            }
        }
    }

