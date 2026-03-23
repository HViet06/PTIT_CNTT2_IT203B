package SS12.ex1;

import java.sql.*;

public class DoctorLogin {

    public static boolean login(Connection conn, String doctorCode, String password) {
        try {

            /*
             * PHÂN TÍCH
             *
             * Nếu sử dụng nối chuỗi:
             *   "SELECT * FROM Doctors WHERE code = '" + code + "' AND pass = '" + pass + "'"
             *
             * → Dữ liệu người dùng được gắn trực tiếp vào câu SQL
             *
             * Trường hợp nguy hiểm:
             *   password = ' OR '1'='1
             *
             * → Câu SQL sau khi ghép:
             *   SELECT * FROM Doctors
             *   WHERE code = 'abc' AND pass = '' OR '1'='1'
             *
             * → Điều kiện '1'='1' luôn đúng → bỏ qua xác thực → đăng nhập trái phép
             */

            /*
             * GIẢI PHÁP: SỬ DỤNG PREPAREDSTATEMENT
             *
             * 1. CƠ CHẾ BIÊN DỊCH TRƯỚC (PRE-COMPILED):
             *    - Câu SQL được gửi lên DB dưới dạng:
             *        SELECT * FROM Doctors WHERE code = ? AND pass = ?
             *    - Database sẽ:
             *        + Phân tích cú pháp (parse)
             *        + Kiểm tra hợp lệ (validate)
             *        + Tạo execution plan
             *    - Quá trình này diễn ra TRƯỚC khi có dữ liệu đầu vào
             *
             * 2. TÁCH BIỆT CÂU LỆNH VÀ DỮ LIỆU:
             *    - doctorCode, password chỉ được truyền vào sau
             *    - JDBC coi chúng là dữ liệu thuần (không phải SQL)
             *
             * 3. NGĂN CHẶN SQL INJECTION:
             *    - Nếu nhập: ' OR '1'='1
             *    - Giá trị sẽ được hiểu là:
             *        "' OR '1'='1"
             *    - Chỉ là một chuỗi bình thường, KHÔNG làm thay đổi logic SQL
             *
             * → Nhờ đó không thể phá vỡ điều kiện WHERE
             */

            // Câu SQL an toàn với placeholder
            String sql = "SELECT * FROM Doctors WHERE code = ? AND pass = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            // Gán giá trị cho tham số
            pstmt.setString(1, doctorCode);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            // Nếu có bản ghi thì đăng nhập thành công
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}