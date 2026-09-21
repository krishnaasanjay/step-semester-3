public class AccountBatchPayments {

    static class FeeAccount {
    }

    static class HostelFeeAccount extends FeeAccount {
    }

    int hostelCount = 0;
    int dayScholarCount = 0;

    void processPayment(FeeAccount account, double amount) {
        if (account instanceof HostelFeeAccount) {
            System.out.println("Paid in two installments (hostel account)");
            hostelCount++;
        } else {
            System.out.println("Paid in one go (day-scholar account)");
            dayScholarCount++;
        }
    }

    public static void main(String[] args) {
        FeeAccount[] accounts = {
            new HostelFeeAccount(), new HostelFeeAccount(), new FeeAccount(), new FeeAccount()
        };
        double amount = 60000;

        AccountBatchPayments processor = new AccountBatchPayments();
        for (FeeAccount account : accounts) {
            processor.processPayment(account, amount);
        }

        System.out.println("Hostel accounts processed: " + processor.hostelCount +
                " | Day-scholar accounts processed: " + processor.dayScholarCount);
    }
}
