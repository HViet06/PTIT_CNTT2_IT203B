package SS11;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static void main(String[] args) {
        // Thông tin kết nối
        String url = "jdbc:mysql://localhost:3306/demo_session11?createDatabaseIfNotExist=true";
        String user = "root";
        String pass = "12345678";

        try {
            // Thiết lập kết nối
            Connection conn = DriverManager.getConnection(url, user, pass);
            if (conn != null) {
                System.out.println("Kết nối thành công tới MySQL!");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối: " + e.getMessage());
        }
    }


}