package SS6.EX5;

import java.util.ArrayList;
import java.util.List;

public class TicketPool {

    private String roomName;
    private List<Ticket> tickets = new ArrayList<>();

    public TicketPool(String roomName, int capacity) {

        this.roomName = roomName;

        for (int i = 1; i <= capacity; i++) {
            String id = roomName + "-" + String.format("%03d", i);
            tickets.add(new Ticket(id, roomName));
        }
    }

    public synchronized Ticket holdTicket(String counterName, boolean vip) {

        for (Ticket t : tickets) {

            if (!t.isSold() && !t.isHeld()) {

                t.hold(counterName, vip);

                System.out.println(counterName +
                        ": Đã giữ vé " + t.getTicketId() +
                        (vip ? " (VIP)" : "") +
                        ". Vui lòng thanh toán trong 5s");

                return t;
            }
        }

        return null;
    }

    public synchronized void sellHeldTicket(Ticket ticket, String counterName) {

        if (ticket != null && ticket.isHeld() &&
                counterName.equals(ticket.getHeldBy())) {

            ticket.sell();

            System.out.println(counterName +
                    ": Thanh toán thành công vé " +
                    ticket.getTicketId());
        }
    }

    public synchronized void releaseExpiredTickets() {

        for (Ticket t : tickets) {

            if (t.isExpired()) {

                System.out.println(
                        "TimeoutManager: Vé " +
                                t.getTicketId() +
                                " hết hạn giữ, đã trả lại kho"
                );

                t.release();
            }
        }
    }
}