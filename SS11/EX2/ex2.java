package SS11.EX2;

import java.sql.*;

public class ex2 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 1. Kết nối tới DB Hospital_DB
            String url = "jdbc:mysql://localhost:3306/Hospital_DB?createDatabaseIfNotExist=true";
            String user = "root";
            String password = "12345678";
            conn = DriverManager.getConnection(url, user, password);

            // 2. Tạo statement và query
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT medicine_name, stock FROM Pharmacy");

            boolean isEmpty = true;
            while (rs.next()) {
                isEmpty = false;
                System.out.println(
                        "Thuốc: " + rs.getString("medicine_name") +
                                " | Tồn kho: " + rs.getInt("stock")
                );
            }
            if (isEmpty) {
                System.out.println("Không có thuốc nào trong kho.");
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}
