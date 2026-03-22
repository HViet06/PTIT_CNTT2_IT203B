package SS11.EX5;
// 1. Trùng mã bác sĩ (Primary Key)
// → SQLIntegrityConstraintViolationException

// 2. Nhập chuyên khoa quá dài
// → Data too long for column

// 3. Bỏ trống tên hoặc mã
// → dữ liệu không hợp lệ (validate fail)

// 4. Kết nối DB lỗi
// → Communications link failure

// 5. Nhập ký tự đặc biệt (SQL Injection nếu dùng Statement)
// → sai dữ liệu / lộ dữ liệu

// 6. Không có dữ liệu khi thống kê
// → cần xử lý hiển thị "Không có dữ liệu"

public class Doctor {
    private String id;
    private String name;
    private String specialty;

    public Doctor() {}

    public Doctor(String id, String name, String specialty) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getSpecialty() { return specialty; }

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
}
