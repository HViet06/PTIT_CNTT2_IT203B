package SS11.EX3;
// executeUpdate() trả về số dòng (row) bị ảnh hưởng bởi câu lệnh SQL
// - = 1 (hoặc >0): cập nhật thành công (có tồn tại bed_id)
// - = 0: KHÔNG có dòng nào bị ảnh hưởng → bed_id không tồn tại

// Lỗi hiện tại:
// Code không kiểm tra giá trị trả về → luôn in "thành công"
// → gây hiểu nhầm cho y tá dù DB không thay đổi

// 👉 Cách xử lý đúng:
// Lấy kết quả từ executeUpdate()
// Nếu == 0 → báo "Mã giường không tồn tại"
// Nếu > 0 → báo "Cập nhật thành công"
import java.sql.*;

public class ex3 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            String url = "jdbc:mysql://localhost:3306/Hospital_DB";
            String user = "root";
            String password = "12345678";
            conn = DriverManager.getConnection(url, user, password);

            String sql = "UPDATE Beds SET Bed_status = 'Occupied' WHERE Bed_id = ?";
            stmt = conn.prepareStatement(sql);

            int inputId = 5;
            stmt.setInt(1, inputId);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Đã cập nhật giường bệnh thành công!");
            } else {
                System.out.println("Lỗi: Mã giường không tồn tại!");
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}

