package SS6.btth;

import java.util.*;

public class TicketPool {
    private Queue<Ticket> tickets = new LinkedList<>();
    private String roomName;
    private int counter = 1;
    public TicketPool(String roomName, int intialTickets){
        this.roomName = roomName;
        for (int i = 0; i<intialTickets; i++){
            tickets.add(new Ticket(generatedId()));
        }
    }
    private String generatedId(){
        return roomName + "-" + String.format("%03d", counter++);
    }
    public synchronized Ticket sellTicket(){
        return tickets.poll();
    }
    public synchronized void addTickets(int count){
        for (int i =0; i<count; i++){
            tickets.add(new Ticket(generatedId()));
        }
        System.out.println("Nha cung cap: Da them " + count + "ve vao phong" + roomName);
    }
    public synchronized int remainingTickets(){
        return tickets.size();
    }
}
