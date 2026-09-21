public class BookCopyCirculationGuard {

    static class BookInventory {
        private int copiesTotal;
        private int copiesAvailable;

        BookInventory(int copiesTotal) {
            this.copiesTotal = copiesTotal;
            this.copiesAvailable = copiesTotal;
        }

        void checkOut() {
            if (copiesAvailable <= 0) {
                return;
            }
            copiesAvailable--;
        }

        void checkIn() {
            if (copiesAvailable >= copiesTotal) {
                return;
            }
            copiesAvailable++;
        }

        int getCopiesAvailable() {
            return copiesAvailable;
        }
    }

    public static void main(String[] args) {
        BookInventory b = new BookInventory(3);
        b.checkOut();
        b.checkOut();
        b.checkOut();
        b.checkOut();
        System.out.println(b.getCopiesAvailable());

        b.checkIn();
        b.checkIn();
        b.checkIn();
        b.checkIn();
        System.out.println(b.getCopiesAvailable());
    }
}
