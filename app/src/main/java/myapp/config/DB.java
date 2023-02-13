package myapp.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    
    public static Connection getConnection(){  // 커넥션을 리턴
        // Connection conn = null;
		// Statement stmt = null;
		// ResultSet rs;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/metadb","root","1234");
            System.out.println("DB 연결성공");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();;
        }
        return null;
    }
    public static void main(String[] args) {
        getConnection();
    }
}
