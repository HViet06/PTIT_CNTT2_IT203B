package SS6.EX2;

public class TicketSupplier implements Runnable {

    private TicketPool pool;

    public TicketSupplier(TicketPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {

        try {

            while (true) {

                Thread.sleep(5000);

                pool.addTickets(3);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
