public class StudentDiscountFineLedger {

    static class LibraryMember {
        String memberId;
        int borrowLimit;
        private int[] fines = new int[10];
        private int fineCount = 0;

        public LibraryMember(String memberId, int borrowLimit) {
            this.memberId = memberId;
            this.borrowLimit = borrowLimit;
        }

        protected void chargeFine(int amount) {
            fines[fineCount] = amount;
            fineCount++;
        }

        int[] getFineHistory() {
            int[] copy = new int[fineCount];
            for (int i = 0; i < fineCount; i++) {
                copy[i] = fines[i];
            }
            return copy;
        }

        int getTotalFine() {
            int total = 0;
            for (int i = 0; i < fineCount; i++) {
                total += fines[i];
            }
            return total;
        }
    }

    static class StudentMember extends LibraryMember {
        String course;

        public StudentMember(String memberId, int borrowLimit, String course) {
            super(memberId, borrowLimit);
            this.course = course;
        }

        @Override
        protected void chargeFine(int amount) {
            super.chargeFine(amount / 2);
        }
    }

    public static void main(String[] args) {
        StudentMember s = new StudentMember("STU5", 3, "CSE");
        s.chargeFine(100);
        System.out.println(s.getTotalFine());

        int[] history = s.getFineHistory();
        history[0] = 999;
        System.out.println(java.util.Arrays.toString(s.getFineHistory()));
    }
}
