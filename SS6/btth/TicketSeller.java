package SS6.btth;

class TicketSeller implements Runnable {

    private String name;
    private TicketPool pool;
    private int soldCount = 0;

    public TicketSeller(String name, TicketPool pool) {
        this.name = name;
        this.pool = pool;
    }

    public int getSoldCount() {
        return soldCount;
    }

    @Override
    public void run() {
        while (true) {
            Ticket t = pool.sellTicket();

            if (t == null) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    break;
                }
                continue;
            }

            soldCount++;
            System.out.println(name + " đã bán vé " + t.getId());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}