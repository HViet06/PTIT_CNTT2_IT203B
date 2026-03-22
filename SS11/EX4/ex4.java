package SS11.EX4;

import java.sql.*;

public class ex4 {
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

            // 2. Input bệnh nhân (ví dụ)
            String patientName = "Nguyen Van A";

            // ✔ Lọc input để tránh SQL Injection
            String safeInput = patientName
                    .replace("'", "")
                    .replace("--", "")
                    .replace(";", "");

            // 3. Query
            stmt = conn.createStatement();
            String sql = "SELECT * FROM Patients WHERE full_name = '" + safeInput + "'";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("Tên: " + rs.getString("full_name"));
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
