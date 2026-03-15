package SS6.EX1;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private String roomName;
    private Queue<String> tickets = new LinkedList<>();
    public TicketPool(String roomName, int total) {
        this.roomName = roomName;
        for (int i = 1; i <= total; i++) {
            tickets.add(roomName + "-" + String.format("%03d", i));
        }
    }
    public String getTicket() {
        return tickets.poll();
    }
    public void returnTicket(String ticket) {
        tickets.add(ticket);
    }

    public boolean hasTicket() {
        return !tickets.isEmpty();
    }
    public String getRoomName() {
        return roomName;
    }
}