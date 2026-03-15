package SS6.EX5;

public class Ticket {

    private String ticketId;
    private String roomName;

    private boolean isSold;
    private boolean isHeld;
    private boolean isVIP;

    private long holdExpiryTime;
    private String heldBy;

    public Ticket(String id, String room) {
        this.ticketId = id;
        this.roomName = room;
    }

    public String getTicketId() {
        return ticketId;
    }

    public boolean isSold() {
        return isSold;
    }

    public boolean isHeld() {
        return isHeld;
    }

    public void hold(String counterName, boolean vip) {
        this.isHeld = true;
        this.isVIP = vip;
        this.heldBy = counterName;
        this.holdExpiryTime = System.currentTimeMillis() + 5000;
    }

    public void sell() {
        this.isHeld = false;
        this.isSold = true;
    }

    public void release() {
        this.isHeld = false;
        this.heldBy = null;
        this.isVIP = false;
    }

    public boolean isExpired() {
        return isHeld && System.currentTimeMillis() > holdExpiryTime;
    }

    public String getHeldBy() {
        return heldBy;
    }
}