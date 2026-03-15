package SS6.EX5;

public class TimeoutManager implements Runnable {

    private TicketPool[] pools;

    public TimeoutManager(TicketPool[] pools) {
        this.pools = pools;
    }

    @Override
    public void run() {

        try {

            while (true) {

                for (TicketPool pool : pools) {
                    pool.releaseExpiredTickets();
                }

                Thread.sleep(1000);

            }

        } catch (InterruptedException e) {
            System.out.println("TimeoutManager dừng");
        }
    }
}