package SS1;

import java.io.IOException;

public class ex4 {
    public static void saveToFile() throws IOException {
        throw new IOException("Lỗi khi ghi dữ liệu vào file");
    }
    public static void processUserData() throws IOException {
        saveToFile();
    }
    public static void main(String[] args) {
        try {
            processUserData();
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
        }
    }
}