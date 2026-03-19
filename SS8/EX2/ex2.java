package SS8.EX2;

import java.util.Scanner;

interface TemperatureSensor {
    double getTemperatureCelsius();
}

class OldThermometer {
    public int getTemperatureFahrenheit() {
        return 78;
    }
}

class ThermometerAdapter implements TemperatureSensor {
    private OldThermometer oldThermometer;

    public ThermometerAdapter(OldThermometer oldThermometer) {
        this.oldThermometer = oldThermometer;
    }

    public double getTemperatureCelsius() {
        int f = oldThermometer.getTemperatureFahrenheit();
        return (f - 32) * 5.0 / 9;
    }
}

class SmartHomeFacade {
    private TemperatureSensor sensor;

    public SmartHomeFacade(TemperatureSensor sensor) {
        this.sensor = sensor;
    }

    public void leaveHome() {
        System.out.println("FACADE: Tắt đèn");
        System.out.println("FACADE: Tắt quạt");
        System.out.println("FACADE: Tắt điều hòa");
    }

    public void sleepMode() {
        System.out.println("FACADE: Tắt đèn");
        System.out.println("FACADE: Điều hòa set 28°C");
        System.out.println("FACADE: Quạt chạy tốc độ thấp");
    }

    public void getCurrentTemperature() {
        double temp = sensor.getTemperatureCelsius();
        System.out.printf("Nhiệt độ hiện tại: %.1f°C\n", temp);
    }
}

public class ex2 {
    public static void main(String[] args) {
        OldThermometer oldThermometer = new OldThermometer();
        TemperatureSensor sensor = new ThermometerAdapter(oldThermometer);
        SmartHomeFacade facade = new SmartHomeFacade(sensor);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Xem nhiệt độ");
            System.out.println("2. Rời nhà");
            System.out.println("3. Chế độ ngủ");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            int choice = sc.nextInt();

            if (choice == 1) {
                facade.getCurrentTemperature();
            } else if (choice == 2) {
                facade.leaveHome();
            } else if (choice == 3) {
                facade.sleepMode();
            } else if (choice == 0) {
                break;
            }
        }
    }
}