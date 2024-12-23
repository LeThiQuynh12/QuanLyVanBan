package QuanLyCongVan.Controllers;

import java.sql.Connection;
import java.sql.DriverManager;


public class KetNoiCSDL {
    protected Connection conn=null;
    public KetNoiCSDL() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String username = "sa";
            String password = "11101978";
            String dbUrl = "jdbc:sqlserver://localhost:1433;databaseName=QUANLYCONGVAN;encrypt=false";
            conn = DriverManager.getConnection(dbUrl, username, password);
            System.out.println("Kết nối thành công");
        } catch (Exception ex) {
            System.out.println("Lỗi kết nối: " + ex.getMessage());
            ex.printStackTrace(); // In chi tiết lỗi để dễ dàng xác định
        }
    }

        public Connection getConnection() {
        return conn;
    }
}
