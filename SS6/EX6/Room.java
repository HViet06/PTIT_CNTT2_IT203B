package SS6.EX6;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private String name;
    private List<Ticket> tickets = new ArrayList<>();

    public Room(String name, int capacity) {

        this.name = name;

        for (int i = 1; i <= capacity; i++) {
            tickets.add(new Ticket(name + "-" + i));
        }
    }

    public synchronized Ticket sellTicket() {

        for (Ticket t : tickets) {

            if (!t.isSold()) {
                t.sell();
                return t;
            }
        }

        return null;
    }

    public int soldCount() {

        int count = 0;

        for (Ticket t : tickets) {
            if (t.isSold()) count++;
        }

        return count;
    }

    public int capacity() {
        return tickets.size();
    }

    public String getName() {
        return name;
    }
}
