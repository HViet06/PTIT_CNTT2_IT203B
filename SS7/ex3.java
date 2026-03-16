package SS7;

interface PaymentMethod {}
interface CODPayable extends PaymentMethod {
    void processCOD(double amount);
}
interface CardPayable extends PaymentMethod {
    void processCreditCard(double amount);
}
interface EWalletPayable extends PaymentMethod {
    void processMomo(double amount);
}
class CODPayment implements CODPayable {

    @Override
    public void processCOD(double amount) {
        System.out.println("Xử lý thanh toán COD: " + amount + " - Thành công");
    }
}
class CreditCardPayment implements CardPayable {

    @Override
    public void processCreditCard(double amount) {
        System.out.println("Xử lý thanh toán thẻ tín dụng: " + amount + " - Thành công");
    }
}

class MomoPayment implements EWalletPayable {

    @Override
    public void processMomo(double amount) {
        System.out.println("Xử lý thanh toán MoMo: " + amount + " - Thành công");
    }
}

class PaymentProcessor {
    public void processPayment(PaymentMethod payment, double amount) {
        if (payment instanceof CODPayable) {
            ((CODPayable) payment).processCOD(amount);
        }
        else if (payment instanceof CardPayable) {
            ((CardPayable) payment).processCreditCard(amount);
        }
        else if (payment instanceof EWalletPayable) {
            ((EWalletPayable) payment).processMomo(amount);
        }
    }
}


public class ex3 {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();
        PaymentMethod cod = new CODPayment();
        processor.processPayment(cod, 500000);
        PaymentMethod card = new CreditCardPayment();
        processor.processPayment(card, 1000000);
        PaymentMethod momo = new MomoPayment();
        processor.processPayment(momo, 750000);
        PaymentMethod payment = new CreditCardPayment();
        processor.processPayment(payment, 300000);
        payment = new MomoPayment();
        processor.processPayment(payment, 300000);
    }
}