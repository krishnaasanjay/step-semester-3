public class SeatBookingEncapsulationGuard {

    static class CineScreen {
        private int seatsTotal;
        private int seatsAvailable;
        private boolean validConstruction;

        CineScreen(int seatsTotal) {
            if (seatsTotal <= 0) {
                this.validConstruction = false;
                return;
            }
            this.seatsTotal = seatsTotal;
            this.seatsAvailable = seatsTotal;
            this.validConstruction = true;
        }

        void bookSeat() {
            if (seatsAvailable <= 0) {
                return;
            }
            seatsAvailable--;
        }

        void cancelBooking() {
            if (seatsAvailable >= seatsTotal) {
                return;
            }
            seatsAvailable++;
        }

        int getSeatsAvailable() {
            return seatsAvailable;
        }
    }

    public static void main(String[] args) {
        CineScreen rejected = new CineScreen(0);
        System.out.println(rejected.validConstruction ? "construction accepted" : "construction rejected");

        CineScreen c = new CineScreen(2);
        c.bookSeat();
        c.bookSeat();
        c.bookSeat();
        System.out.println(c.getSeatsAvailable());

        c.cancelBooking();
        c.cancelBooking();
        c.cancelBooking();
        System.out.println(c.getSeatsAvailable());
    }
}
