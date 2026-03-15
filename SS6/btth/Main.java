package SS6.btth;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        TicketPool roomA = new TicketPool("A", 10);
        TicketPool roomB = new TicketPool("B", 10);

        TicketSeller seller1 = new TicketSeller("Quầy 1", roomA);
        TicketSeller seller2 = new TicketSeller("Quầy 2", roomB);

        TicketSupplier supplier =
                new TicketSupplier(roomA, roomB, 3, 3000, 3);

        Thread t1 = new Thread(seller1);
        Thread t2 = new Thread(seller2);
        Thread t3 = new Thread(supplier);

        t1.start();
        t2.start();
        t3.start();

        t3.join();

        Thread.sleep(5000);

        t1.interrupt();
        t2.interrupt();

        t1.join();
        t2.join();

        System.out.println("Kết thúc chương trình");
        System.out.println("Quầy 1 bán được: " + seller1.getSoldCount() + " vé");
        System.out.println("Quầy 2 bán được: " + seller2.getSoldCount() + " vé");
        System.out.println("Vé còn lại phòng A: " + roomA.remainingTickets());
        System.out.println("Vé còn lại phòng B: " + roomB.remainingTickets());
    }
}