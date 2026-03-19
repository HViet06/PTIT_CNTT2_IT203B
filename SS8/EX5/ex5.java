package SS8.EX5;

import java.util.*;

interface Command {
    void execute();
}

interface Observer {
    void update(int temperature);
}

interface Subject {
    void attach(Observer o);
    void notifyObservers();
}

class Light {
    private boolean on = true;

    public void off() {
        on = false;
        System.out.println("Đèn: Tắt");
    }

    public String getState() {
        return on ? "Bật" : "Tắt";
    }
}

class Fan implements Observer {
    private String speed = "Tắt";

    public void setLow() {
        speed = "Thấp";
        System.out.println("Quạt: Chạy tốc độ thấp");
    }

    public void update(int temperature) {
        if (temperature > 30) {
            speed = "Mạnh";
            System.out.println("Quạt: Nhiệt độ cao, chạy tốc độ mạnh");
        }
    }

    public String getState() {
        return speed;
    }
}

class AirConditioner implements Observer {
    private int temperature = 25;

    public void setTemperature(int temp) {
        temperature = temp;
        System.out.println("Điều hòa: Nhiệt độ = " + temp);
    }

    public void update(int t) {
        if (t > 30) {
            System.out.println("Điều hòa: Nhiệt độ = " + temperature);
        }
    }

    public String getState() {
        return "Nhiệt độ = " + temperature;
    }
}

class TemperatureSensor implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private int temperature;

    public void attach(Observer o) {
        observers.add(o);
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

class SleepModeCommand implements Command {
    private Light light;
    private Fan fan;
    private AirConditioner ac;

    public SleepModeCommand(Light light, Fan fan, AirConditioner ac) {
        this.light = light;
        this.fan = fan;
        this.ac = ac;
    }

    public void execute() {
        System.out.println("SleepMode: Tắt đèn");
        System.out.println("SleepMode: Điều hòa set 28°C");
        System.out.println("SleepMode: Quạt tốc độ thấp");

        light.off();
        ac.setTemperature(28);
        fan.setLow();
    }
}

public class ex5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner ac = new AirConditioner();
        TemperatureSensor sensor = new TemperatureSensor();

        sensor.attach(fan);
        sensor.attach(ac);

        Command sleepCommand = new SleepModeCommand(light, fan, ac);

        while (true) {
            System.out.println("\n1. Chế độ ngủ");
            System.out.println("2. Thay đổi nhiệt độ");
            System.out.println("3. Xem trạng thái");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            int choice = sc.nextInt();

            if (choice == 1) {
                sleepCommand.execute();
            } else if (choice == 2) {
                System.out.print("Nhập nhiệt độ: ");
                int t = sc.nextInt();
                sensor.setTemperature(t);
            } else if (choice == 3) {
                System.out.println("Đèn: " + light.getState());
                System.out.println("Quạt: " + fan.getState());
                System.out.println("Điều hòa: " + ac.getState());
            } else if (choice == 0) {
                break;
            }
        }
    }
}