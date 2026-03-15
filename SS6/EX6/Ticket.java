package SS6.EX6;

public class Ticket {

    private String id;
    private boolean sold;

    public Ticket(String id) {
        this.id = id;
        this.sold = false;
    }

    public String getId() {
        return id;
    }

    public boolean isSold() {
        return sold;
    }

    public void sell() {
        sold = true;
    }
}