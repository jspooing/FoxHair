package FoxHairDB;
import java.sql.*;

public class DBLogin
{
        //로그인 함수이다. 아이디와 비밀번호를 받아와 DB에 접속 한다. 
        public static Connection getConnection(String user,String pw)
        {
        	Connection conn = null;
            try {
               
                String url = "jdbc:oracle:thin:@localhost:1521:xe";
                                           //@ ip주소:포트번호:DB이름
                Class.forName("oracle.jdbc.driver.OracleDriver");        
                conn = DriverManager.getConnection(url, user, pw);
                
                System.out.println("Database에 연결되었습니다.\n");
                
            } catch (ClassNotFoundException cnfe) {
                System.out.println("DB 드라이버 로딩 실패 :"+cnfe.toString());
            } catch (SQLException sqle) {
                System.out.println("DB 접속실패 : "+sqle.toString());
            } catch (Exception e) {
                System.out.println("Unkonwn error");
                e.printStackTrace();
            }
            return conn;     
        }
}

