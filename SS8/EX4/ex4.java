package SS8.EX4;

import java.util.*;

interface Observer {
    void update(int temperature);
}

interface Subject {
    void attach(Observer o);
    void detach(Observer o);
    void notifyObservers();
}

class TemperatureSensor implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private int temperature;

    public void attach(Observer o) {
        observers.add(o);
    }

    public void detach(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(temperature);
        }
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        System.out.println("Cảm biến: Nhiệt độ = " + temperature);
        notifyObservers();
    }
}

class Fan implements Observer {
    public void update(int temperature) {
        if (temperature < 20) {
            System.out.println("Quạt: Nhiệt độ thấp, tự động TẮT");
        } else if (temperature <= 25) {
            System.out.println("Quạt: Nhiệt độ bình thường, chạy trung bình");
        } else {
            System.out.println("Quạt: Nhiệt độ cao, chạy tốc độ mạnh");
        }
    }
}

class Humidifier implements Observer {
    public void update(int temperature) {
        System.out.println("Máy tạo ẩm: Điều chỉnh độ ẩm cho nhiệt độ " + temperature);
    }
}

public class ex4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        TemperatureSensor sensor = new TemperatureSensor();
        Fan fan = new Fan();
        Humidifier humidifier = new Humidifier();

        while (true) {
            System.out.println("\n1. Đăng ký quạt");
            System.out.println("2. Đăng ký máy tạo ẩm");
            System.out.println("3. Set nhiệt độ");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            int choice = sc.nextInt();

            if (choice == 1) {
                sensor.attach(fan);
                System.out.println("Quạt: Đã đăng ký nhận thông báo");
            } else if (choice == 2) {
                sensor.attach(humidifier);
                System.out.println("Máy tạo ẩm: Đã đăng ký");
            } else if (choice == 3) {
                System.out.print("Nhập nhiệt độ: ");
                int temp = sc.nextInt();
                sensor.setTemperature(temp);
            } else if (choice == 0) {
                break;
            }
        }
    }
}