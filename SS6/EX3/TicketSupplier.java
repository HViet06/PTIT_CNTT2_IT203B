package SS6.EX3;

public class TicketSupplier implements Runnable {

    private TicketPool roomA;
    private TicketPool roomB;

    private int supplyCount;
    private int interval;
    private int rounds;

    public TicketSupplier(TicketPool roomA, TicketPool roomB,
                          int supplyCount, int interval, int rounds) {

        this.roomA = roomA;
        this.roomB = roomB;
        this.supplyCount = supplyCount;
        this.interval = interval;
        this.rounds = rounds;
    }

    @Override
    public void run() {

        try {

            for (int i = 0; i < rounds; i++) {

                Thread.sleep(interval);

                roomA.addTickets(supplyCount);
                roomB.addTickets(supplyCount);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
