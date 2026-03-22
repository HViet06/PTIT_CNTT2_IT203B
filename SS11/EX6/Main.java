package SS11.EX6;

import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        AppointmentRepository repo = new AppointmentRepository();

        // Thêm
        repo.addAppointment(new Appointment(
                "Nguyen Van A",
                Date.valueOf("2026-03-25"),
                "Dr. Tran",
                "Pending"
        ));

        // Sửa (giả sử id = 1)
        repo.updateAppointment(new Appointment(
                1,
                "Nguyen Van A Updated",
                Date.valueOf("2026-03-26"),
                "Dr. Tran",
                "Confirmed"
        ));

        // Lấy theo ID
        Appointment a = repo.getAppointmentById(1);
        if (a != null) {
            System.out.println("Tên bệnh nhân: " + a.getPatientName());
        }

        // Lấy tất cả
        List<Appointment> list = repo.getAllAppointments();
        for (Appointment ap : list) {
            System.out.println(ap.getId() + " - " + ap.getPatientName());
        }

        // Xóa
        repo.deleteAppointment(1);
    }
}