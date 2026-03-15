package SS6.EX6;

import java.util.*;
import java.util.concurrent.*;

public class CinemaSystem {

    private List<Room> rooms = new ArrayList<>();
    private List<BookingCounter> counters = new ArrayList<>();

    private ExecutorService executor;

    public void start(int roomCount, int ticketsPerRoom, int counterCount) {

        executor = Executors.newFixedThreadPool(counterCount);

        for (int i = 0; i < roomCount; i++) {

            char roomName = (char) ('A' + i);
            rooms.add(new Room(String.valueOf(roomName), ticketsPerRoom));
        }

        for (int i = 1; i <= counterCount; i++) {

            BookingCounter counter =
                    new BookingCounter("Quầy " + i, rooms);

            counters.add(counter);

            executor.submit(counter);
        }

        System.out.println("Đã khởi tạo hệ thống với "
                + roomCount + " phòng, "
                + (roomCount * ticketsPerRoom)
                + " vé, "
                + counterCount + " quầy");
    }

    public void stop() {

        for (BookingCounter c : counters) {
            c.stop();
        }

        executor.shutdownNow();

        System.out.println("Đã tạm dừng tất cả quầy bán vé.");
    }

    public void stats() {

        System.out.println("\n=== THỐNG KÊ HIỆN TẠI ===");

        int revenue = 0;

        for (Room r : rooms) {

            int sold = r.soldCount();

            System.out.println("Phòng "
                    + r.getName()
                    + ": Đã bán "
                    + sold
                    + "/"
                    + r.capacity()
                    + " vé");

            revenue += sold * 250000;
        }

        System.out.println("Tổng doanh thu: "
                + revenue + " VNĐ");
    }

    public void detectDeadlock() {
        new DeadlockDetector().run();
    }
}
