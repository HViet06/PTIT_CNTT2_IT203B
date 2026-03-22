package SS11.EX5;



import SS11.EX5.DoctorDAO;
import SS11.EX5.Doctor;

import java.util.List;

public class DoctorService {
    private DoctorDAO dao = new DoctorDAO();

    public void showDoctors() {
        List<Doctor> list = dao.getAllDoctors();

        if (list.isEmpty()) {
            System.out.println("Không có bác sĩ.");
            return;
        }

        for (Doctor d : list) {
            System.out.println(d.getId() + " | " + d.getName() + " | " + d.getSpecialty());
        }
    }

    public void addDoctor(Doctor d) {
        if (d.getId().isEmpty() || d.getName().isEmpty()) {
            System.out.println("Dữ liệu không hợp lệ!");
            return;
        }

        if (dao.addDoctor(d)) {
            System.out.println("Thêm thành công!");
        }
    }

    public void statistic() {
        dao.countBySpecialty();
    }
}
