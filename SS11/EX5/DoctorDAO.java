package SS11.EX5;



import SS11.EX5.DBContext;
import SS11.EX5.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    public List<Doctor> getAllDoctors() {
        List<Doctor> list = new ArrayList<>();
        String sql = "SELECT * FROM Doctors";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Doctor(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("specialty")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addDoctor(Doctor d) {
        String sql = "INSERT INTO Doctors VALUES (?, ?, ?)";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, d.getId());
            ps.setString(2, d.getName());
            ps.setString(3, d.getSpecialty());

            return ps.executeUpdate() > 0;

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Lỗi: Trùng mã bác sĩ!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public void countBySpecialty() {
        String sql = "SELECT specialty, COUNT(*) as total FROM Doctors GROUP BY specialty";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean empty = true;

            while (rs.next()) {
                empty = false;
                System.out.println(
                        rs.getString("specialty") + " | Số lượng: " + rs.getInt("total")
                );
            }

            if (empty) {
                System.out.println("Không có dữ liệu.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}