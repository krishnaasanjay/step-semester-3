public class CanteenPaymentDispatch {

    static class Payment {
        double pay(double amount) {
            System.out.println("Paid (cash): Rs " + amount);
            return amount;
        }
    }

    static class CardPayment extends Payment {
        double payWithProcessingFee(double amount) {
            double totalCharged = amount * 1.02;
            System.out.println("Charged (card, incl. fee): Rs " + totalCharged);
            return totalCharged;
        }
    }

    double totalCollected = 0;

    void processTransaction(Payment payment, double amount) {
        if (payment instanceof CardPayment) {
            CardPayment cardPayment = (CardPayment) payment;
            totalCollected += cardPayment.payWithProcessingFee(amount);
        } else {
            totalCollected += payment.pay(amount);
        }
    }

    public static void main(String[] args) {
        Payment[] payments = {
            new CardPayment(), new Payment(), new CardPayment(), new Payment(), new CardPayment()
        };
        double[] amounts = {100, 50, 200, 75, 120};

        CanteenPaymentDispatch dispatcher = new CanteenPaymentDispatch();
        for (int i = 0; i < payments.length; i++) {
            dispatcher.processTransaction(payments[i], amounts[i]);
        }

        System.out.println("Total Collected: Rs " + dispatcher.totalCollected);
    }
}
