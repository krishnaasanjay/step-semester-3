import java.util.ArrayList;
import java.util.List;

/**
 * Q5. Payment Processing for a Shopping System
 * Order depends only on the PaymentMethod interface, so new payment methods
 * plug in without changing order or payment-initiation logic. A failed
 * payment never marks an order Paid.
 */
public class PaymentProcessing {

    static class Product {
        final String name;
        final double price;
        Product(String name, double price) { this.name = name; this.price = price; }
    }

    interface PaymentMethod {
        String name();
        boolean processPayment(double amount);
    }

    static class CreditCardPayment implements PaymentMethod {
        public String name() { return "Credit Card"; }
        public boolean processPayment(double amount) { return true; }
    }

    static class PayPalPayment implements PaymentMethod {
        private final boolean accountFunded;
        PayPalPayment(boolean accountFunded) { this.accountFunded = accountFunded; }
        public String name() { return "PayPal"; }
        public boolean processPayment(double amount) { return accountFunded; }
    }

    static class BankTransferPayment implements PaymentMethod {
        public String name() { return "Bank Transfer"; }
        public boolean processPayment(double amount) { return amount < 10000; }
    }

    static class Customer {
        final String name;
        Customer(String name) { this.name = name; }
    }

    static class Order {
        enum Status { PENDING, PAID }

        private final String id;
        private final List<Product> items = new ArrayList<>();
        private Status status = Status.PENDING;

        Order(Customer customer, String id) {
            this.id = id;
            System.out.println("Order created for " + customer.name + ".");
        }

        Status getStatus() { return status; }

        void addProduct(Product product, int qty) {
            for (int i = 0; i < qty; i++) items.add(product);
        }

        double total() { return items.stream().mapToDouble(p -> p.price).sum(); }

        void pay(PaymentMethod method) {
            if (items.isEmpty()) {
                System.out.println("Cannot process payment for an empty order.");
                return;
            }
            System.out.println("Payment initiated via " + method.name() + " for Order " + id + ".");
            if (method.processPayment(total())) {
                status = Status.PAID;
                System.out.println("Payment for Order " + id + " successful. Order status: Paid.");
            } else {
                System.out.println("Payment for Order " + id + " failed. Order status: Pending.");
            }
        }
    }

    public static void main(String[] args) {
        Product a = new Product("Product A", 250);
        Product b = new Product("Product B", 400);
        Product c = new Product("Product C", 300);

        Order orderX = new Order(new Customer("Customer X"), "X");
        orderX.addProduct(a, 2);
        orderX.addProduct(b, 1);
        orderX.pay(new CreditCardPayment());

        Order orderY = new Order(new Customer("Customer Y"), "Y");
        orderY.pay(new CreditCardPayment());

        Order orderZ = new Order(new Customer("Customer Z"), "Z");
        orderZ.addProduct(c, 1);
        orderZ.pay(new PayPalPayment(false));
    }
}
