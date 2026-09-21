public class PremiumLoyaltyDiscountLateFeeLedger {

    static class GymMember {
        String memberId;
        int monthlyFee;
        private int[] lateFees = new int[10];
        private int lateFeeCount = 0;

        public GymMember(String memberId, int monthlyFee) {
            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
        }

        protected void chargeLateFee(int amount) {
            lateFees[lateFeeCount] = amount;
            lateFeeCount++;
        }

        int[] getLateFeeHistory() {
            int[] copy = new int[lateFeeCount];
            for (int i = 0; i < lateFeeCount; i++) {
                copy[i] = lateFees[i];
            }
            return copy;
        }

        int getTotalLateFees() {
            int total = 0;
            for (int i = 0; i < lateFeeCount; i++) {
                total += lateFees[i];
            }
            return total;
        }
    }

    static class PremiumMember extends GymMember {
        String trainerName;

        public PremiumMember(String memberId, int monthlyFee, String trainerName) {
            super(memberId, monthlyFee);
            this.trainerName = trainerName;
        }

        @Override
        protected void chargeLateFee(int amount) {
            super.chargeLateFee(amount / 2);
        }
    }

    public static void main(String[] args) {
        PremiumMember p = new PremiumMember("MEM5", 2000, "Coach Riya");
        p.chargeLateFee(200);
        System.out.println(p.getTotalLateFees());

        int[] history = p.getLateFeeHistory();
        history[0] = 999;
        System.out.println(java.util.Arrays.toString(p.getLateFeeHistory()));
    }
}
