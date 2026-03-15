package SS6.EX1;

public class BookingCounter implements Runnable {
    private String counterName;
    private TicketPool first;
    private TicketPool second;
    public BookingCounter(String name, TicketPool first, TicketPool second) {
        this.counterName = name;
        this.first = first;
        this.second = second;
    }
    public void sellCombo() {
        synchronized (first) {
            String ticket1 = null;

            if (first.hasTicket()) {
                ticket1 = first.getTicket();
                System.out.println(counterName + " lấy vé " + ticket1);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (second) {

                String ticket2 = null;

                if (second.hasTicket()) {
                    ticket2 = second.getTicket();
                }

                if (ticket1 != null && ticket2 != null) {
                    System.out.println(counterName + " bán combo: "
                            + ticket1 + " & " + ticket2);
                } else {
                    if (ticket1 != null) {
                        first.returnTicket(ticket1);
                    }
                    System.out.println(counterName + " bán combo thất bại");
                }
            }
        }
    }

    @Override
    public void run() {
        sellCombo();
    }
}