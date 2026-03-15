package SS6.EX6;

import java.util.List;
import java.util.Random;

public class BookingCounter implements Runnable {

    private String name;
    private List<Room> rooms;
    private volatile boolean running = true;
    private Random random = new Random();

    public BookingCounter(String name, List<Room> rooms) {
        this.name = name;
        this.rooms = rooms;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {

        System.out.println(name + " bắt đầu bán vé...");

        while (running) {

            Room room = rooms.get(random.nextInt(rooms.size()));

            Ticket ticket = room.sellTicket();

            if (ticket != null) {
                System.out.println(name + " bán vé " + ticket.getId());
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}