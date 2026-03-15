package SS6.EX5;

import java.util.Random;

public class BookingCounter implements Runnable {

    private String name;
    private TicketPool[] pools;
    private Random random = new Random();

    public BookingCounter(String name, TicketPool[] pools) {
        this.name = name;
        this.pools = pools;
    }

    @Override
    public void run() {

        try {

            while (true) {

                boolean vip = random.nextInt(5) == 0;

                TicketPool pool =
                        pools[random.nextInt(pools.length)];

                Ticket ticket =
                        pool.holdTicket(name, vip);

                if (ticket != null) {

                    Thread.sleep(3000);

                    if (random.nextBoolean()) {
                        pool.sellHeldTicket(ticket, name);
                    }
                }

                Thread.sleep(500);

            }

        } catch (InterruptedException e) {
            System.out.println(name + " dừng hoạt động");
        }
    }
}