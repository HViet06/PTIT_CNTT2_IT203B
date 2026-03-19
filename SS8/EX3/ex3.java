package SS8.EX3;

import java.util.*;

interface Command {
    void execute();
    void undo();
}

class Light {
    public void on() {
        System.out.println("Đèn: Bật");
    }

    public void off() {
        System.out.println("Đèn: Tắt");
    }
}

class Fan {
    public void on() {
        System.out.println("Quạt: Bật");
    }

    public void off() {
        System.out.println("Quạt: Tắt");
    }
}

class AirConditioner {
    private int temperature = 25;

    public void setTemperature(int temp) {
        this.temperature = temp;
        System.out.println("Điều hòa: Nhiệt độ = " + temp);
    }

    public int getTemperature() {
        return temperature;
    }
}

class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.on();
    }

    public void undo() {
        light.off();
    }
}

class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.off();
    }

    public void undo() {
        light.on();
    }
}

class FanOnCommand implements Command {
    private Fan fan;

    public FanOnCommand(Fan fan) {
        this.fan = fan;
    }

    public void execute() {
        fan.on();
    }

    public void undo() {
        fan.off();
    }
}

class FanOffCommand implements Command {
    private Fan fan;

    public FanOffCommand(Fan fan) {
        this.fan = fan;
    }

    public void execute() {
        fan.off();
    }

    public void undo() {
        fan.on();
    }
}

class ACSetTemperatureCommand implements Command {
    private AirConditioner ac;
    private int newTemp;
    private int oldTemp;

    public ACSetTemperatureCommand(AirConditioner ac, int newTemp) {
        this.ac = ac;
        this.newTemp = newTemp;
    }

    public void execute() {
        oldTemp = ac.getTemperature();
        ac.setTemperature(newTemp);
    }

    public void undo() {
        ac.setTemperature(oldTemp);
    }
}

class RemoteControl {
    private Map<Integer, Command> buttons = new HashMap<>();
    private Stack<Command> history = new Stack<>();

    public void setCommand(int slot, Command command) {
        buttons.put(slot, command);
        System.out.println("Đã gán cho nút " + slot);
    }

    public void pressButton(int slot) {
        Command command = buttons.get(slot);
        if (command != null) {
            command.execute();
            history.push(command);
        } else {
            System.out.println("Nút chưa được gán");
        }
    }

    public void undo() {
        if (!history.isEmpty()) {
            Command command = history.pop();
            System.out.print("Undo: ");
            command.undo();
        } else {
            System.out.println("Không có gì để undo");
        }
    }
}

public class ex3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner ac = new AirConditioner();

        RemoteControl remote = new RemoteControl();

        while (true) {
            System.out.println("\n1. Gán nút");
            System.out.println("2. Nhấn nút");
            System.out.println("3. Undo");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Nhập số nút: ");
                int slot = sc.nextInt();

                System.out.println("1. Bật đèn");
                System.out.println("2. Tắt đèn");
                System.out.println("3. Bật quạt");
                System.out.println("4. Tắt quạt");
                System.out.println("5. Set điều hòa");

                int type = sc.nextInt();

                if (type == 1) {
                    remote.setCommand(slot, new LightOnCommand(light));
                } else if (type == 2) {
                    remote.setCommand(slot, new LightOffCommand(light));
                } else if (type == 3) {
                    remote.setCommand(slot, new FanOnCommand(fan));
                } else if (type == 4) {
                    remote.setCommand(slot, new FanOffCommand(fan));
                } else if (type == 5) {
                    System.out.print("Nhập nhiệt độ: ");
                    int temp = sc.nextInt();
                    remote.setCommand(slot, new ACSetTemperatureCommand(ac, temp));
                }

            } else if (choice == 2) {
                System.out.print("Nhập nút: ");
                int slot = sc.nextInt();
                remote.pressButton(slot);

            } else if (choice == 3) {
                remote.undo();

            } else if (choice == 0) {
                break;
            }
        }
    }
}