package SS12.ex4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

/**
 * Demo các cách INSERT dữ liệu vào database:
 * 1. Dùng Statement (cách chưa tối ưu)
 * 2. Dùng PreparedStatement (tối ưu hơn)
 * 3. Dùng Batch Insert (tối ưu nhất khi dữ liệu lớn)
 */
public class InsertResultsExample {

    /**
     * Lớp mô phỏng dữ liệu cần insert
     */
    static class ResultItem {
        private String value;

        public ResultItem(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public static void main(String[] args) throws Exception {

        // Kết nối tới MySQL
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/testdb",
                "root",
                "12345678"
        );

        // Danh sách dữ liệu mẫu
        List<ResultItem> dataList = List.of(
                new ResultItem("A"),
                new ResultItem("B"),
                new ResultItem("C")
        );

        // CÁCH 1: DÙNG STATEMENT (KHÔNG TỐI ƯU)
        /*
         * Nhược điểm:
         * - Mỗi lần lặp đều tạo Statement mới → tốn tài nguyên
         * - Database phải phân tích (parse) và tối ưu câu lệnh nhiều lần
         * - Hiệu năng kém khi dữ liệu lớn
         * - Dễ bị SQL Injection do nối chuỗi trực tiếp
         */
        for (ResultItem item : dataList) {
            String query = "INSERT INTO Results(data) VALUES('" + item.getValue() + "')";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        }

        // CÁCH 2: DÙNG PREPAREDSTATEMENT (TỐI ƯU HƠN)

        /*
         * Ưu điểm:
         * - Chỉ tạo 1 PreparedStatement duy nhất
         * - Database chỉ parse câu lệnh 1 lần
         * - Thay đổi dữ liệu bằng tham số → nhanh hơn
         * - Tránh được SQL Injection
         */
        String sql = "INSERT INTO Results(data) VALUES(?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for (ResultItem item : dataList) {
            preparedStatement.setString(1, item.getValue());
            preparedStatement.executeUpdate();
        }
        preparedStatement.close();
        // Đóng kết nối
        connection.close();
    }
}