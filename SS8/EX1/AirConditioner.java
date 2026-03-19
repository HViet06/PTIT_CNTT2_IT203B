package SS8.EX1;

class AirConditioner implements Device {

    @Override
    public void turnOn() {
        System.out.println("Điều hòa: Bật làm mát.");
    }

    @Override
    public void turnOff() {
        System.out.println("Điều hòa: Tắt.");
    }
}