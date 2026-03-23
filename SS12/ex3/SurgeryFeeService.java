package SS12.ex3;

import java.sql.*;

public class SurgeryFeeService {

    public static void fetchSurgeryFee(Connection connection, int surgeryId) {

        try {
            /*
             * - Gọi procedure rồi lấy OUT parameter ngay:
             *
             *   cstmt.setInt(1, 505);
             *   cstmt.execute();
             *   cstmt.getDouble(2);
             *
             * → Sẽ gây lỗi:
             *   "The column index is out of range"
             *
             * NGUYÊN NHÂN:
             * - JDBC không tự hiểu tham số nào là OUTPUT
             * - Nếu không khai báo, tất cả đều bị coi là INPUT
             */

            /*
             * CƠ CHẾ HOẠT ĐỘNG:
             * - Stored Procedure có thể có nhiều tham số IN / OUT / INOUT
             * - JDBC cần được "thông báo trước" tham số OUT:
             *   → thông qua registerOutParameter()
             */

            /*
             * LÝ DO PHẢI DÙNG registerOutParameter():
             *
             * 1. Xác định kiểu dữ liệu trả về
             *    - Ví dụ: DECIMAL, INT, VARCHAR...
             *    - Driver cần biết để mapping sang Java
             *
             * 2. Cấp phát bộ nhớ cho giá trị OUT
             *
             * 3. Nếu không đăng ký:
             *    - Khi gọi getDouble(2) → JDBC không tìm thấy
             *    → phát sinh lỗi index
             */

            /*
             * THỨ TỰ THỰC HIỆN CHUẨN:
             * 1. Gán giá trị cho IN parameter
             * 2. Đăng ký OUT parameter
             * 3. Thực thi procedure
             * 4. Lấy dữ liệu OUT
             */

            CallableStatement callableStmt =
                    connection.prepareCall("{call GET_SURGERY_FEE(?, ?)}");

            // Bước 1: truyền tham số đầu vào
            callableStmt.setInt(1, surgeryId);

            // Bước 2: khai báo tham số đầu ra (kiểu DECIMAL trong SQL)
            callableStmt.registerOutParameter(2, Types.DECIMAL);

            // Bước 3: thực thi stored procedure
            callableStmt.execute();

            // Bước 4: lấy kết quả trả về
            double fee = callableStmt.getDouble(2);

            System.out.println("Chi phí phẫu thuật: " + fee);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}