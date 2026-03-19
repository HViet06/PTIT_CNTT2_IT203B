package SS8.EX1;

class Fan implements Device {

    @Override
    public void turnOn() {
        System.out.println("Quạt: Bật.");
    }

    @Override
    public void turnOff() {
        System.out.println("Quạt: Tắt.");
    }
}
