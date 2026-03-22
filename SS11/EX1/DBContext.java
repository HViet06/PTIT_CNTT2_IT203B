package SS11.EX1;
//*
// Việc khởi tạo kết nối liên tục mà không đóng (close) hoặc không có cơ chế quản lý tập trung là một lỗi rất nghiêm trọng, đặc biệt trong hệ thống y tế cần chạy 24/7, vì các lý do sau:
//
//1. Rò rỉ tài nguyên (Resource Leak)
//Mỗi lần mở Connection sẽ chiếm:
//1 socket TCP
//bộ nhớ
//thread từ DB server
//Nếu không đóng → tài nguyên không được giải phóng
//Sau một thời gian → hệ thống hết tài nguyên
//
// Đây chính là nguyên nhân phổ biến gây lỗi:
//
//Communications link failure
//2. Hết connection pool / max connections
//
//Database (MySQL, PostgreSQL…) đều có giới hạn:
//
//max_connections ≈ 100 - 1000
//Nếu code mở liên tục mà không đóng:
//số connection active tăng dần
//đến ngưỡng → DB từ chối kết nối mới
//
// Hệ quả:
//
//hệ thống "treo"
//user không truy cập được
//3. Connection bị timeout nhưng không xử lý
//DB sẽ tự đóng connection "idle"
//Nhưng phía app vẫn giữ reference cũ
//
//Khi dùng lại:
//
//Communications link failure
//4. Không phù hợp với hệ thống 24/7
//
//Hệ thống bệnh viện yêu cầu:
//
//ổn định lâu dài
//không downtime
//xử lý liên tục
//
//Nhưng lỗi này gây:
//
//treo sau vài giờ
//phải restart hệ thống
//nguy hiểm trong môi trường thật (mất dữ liệu bệnh nhân, gián đoạn khám chữa)
//5. Thiết kế sai kiến trúc
//
//Việc tạo connection trực tiếp trong mỗi method:
//
//vi phạm nguyên tắc Single Responsibility
//khó kiểm soát lifecycle


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBContext {

    private static final String URL = "jdbc:mysql://localhost:3306/Hospital_DB?createDatabaseIfNotExist=true";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public static void getPatients() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();

            String sql = "SELECT * FROM patients";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (rs != null) rs.close();
            } catch (Exception e) {}

            try {
                if (stmt != null) stmt.close();
            } catch (Exception e) {}

            try {
                if (conn != null) conn.close();
            } catch (Exception e) {}
        }
    }
}
