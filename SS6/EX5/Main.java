package SS6.EX5;

public class Main {

    public static void main(String[] args) {

        TicketPool roomA = new TicketPool("A", 10);
        TicketPool roomB = new TicketPool("B", 8);
        TicketPool roomC = new TicketPool("C", 12);

        TicketPool[] pools = {roomA, roomB, roomC};

        Thread timeoutManager =
                new Thread(new TimeoutManager(pools));

        timeoutManager.start();

        for (int i = 1; i <= 5; i++) {

            Thread counter =
                    new Thread(new BookingCounter(
                            "Quầy " + i, pools));

            counter.start();
        }
    }
}
