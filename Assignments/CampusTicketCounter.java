import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Q3. The Campus Premiere Ticket Counter
 * Seat is abstract with its own getPrice(), so a new seat category needs no
 * change to Show or Booking. Show tracks which seats are already booked;
 * cancelling a booking releases all its seats back to the Show.
 */
public class CampusTicketCounter {

    static final int MAX_SEATS_PER_BOOKING = 6;

    static class Customer {
        final String name;
        Customer(String name) { this.name = name; }
    }

    static abstract class Seat {
        final String code;
        Seat(String code) { this.code = code; }
        abstract double getPrice();
    }

    static class RegularSeat extends Seat {
        RegularSeat(String code) { super(code); }
        double getPrice() { return 150; }
    }

    static class PremiumSeat extends Seat {
        PremiumSeat(String code) { super(code); }
        double getPrice() { return 250; }
    }

    static class ReclinerSeat extends Seat {
        ReclinerSeat(String code) { super(code); }
        double getPrice() { return 400; }
    }

    static class Show {
        final String name;
        private final Map<String, Seat> seatsByCode = new HashMap<>();
        private final Map<String, Boolean> bookedStatus = new HashMap<>();

        Show(String name) { this.name = name; }

        void addSeat(Seat seat) {
            seatsByCode.put(seat.code, seat);
            bookedStatus.put(seat.code, false);
        }

        boolean isBooked(String code) { return bookedStatus.getOrDefault(code, false); }
        Seat getSeat(String code) { return seatsByCode.get(code); }
        void markBooked(String code) { bookedStatus.put(code, true); }
        void release(String code) { bookedStatus.put(code, false); }
    }

    static class Booking {
        final Customer customer;
        final Show show;
        final List<Seat> seats;
        final double total;

        Booking(Customer customer, Show show, List<Seat> seats) {
            this.customer = customer;
            this.show = show;
            this.seats = seats;
            this.total = seats.stream().mapToDouble(Seat::getPrice).sum();
        }

        void cancel() {
            for (Seat seat : seats) {
                show.release(seat.code);
            }
            StringBuilder codes = new StringBuilder();
            for (int i = 0; i < seats.size(); i++) {
                codes.append(seats.get(i).code);
                if (i < seats.size() - 1) codes.append(", ");
            }
            System.out.println(customer.name + "'s booking cancelled. Seats " + codes + " released.");
        }
    }

    static Booking book(Customer customer, Show show, String... seatCodes) {
        if (seatCodes.length > MAX_SEATS_PER_BOOKING) {
            System.out.println("Cannot book more than " + MAX_SEATS_PER_BOOKING + " seats in one booking.");
            return null;
        }
        for (String code : seatCodes) {
            if (show.isBooked(code)) {
                System.out.println("Seat " + code + " is already booked for this show.");
                return null;
            }
        }
        List<Seat> seats = new ArrayList<>();
        for (String code : seatCodes) {
            Seat seat = show.getSeat(code);
            show.markBooked(code);
            seats.add(seat);
        }
        Booking booking = new Booking(customer, show, seats);

        StringBuilder codes = new StringBuilder();
        for (int i = 0; i < seats.size(); i++) {
            codes.append(seats.get(i).code);
            if (i < seats.size() - 1) codes.append(", ");
        }
        System.out.println("Booking confirmed for " + customer.name + ": " + codes
                + ". Total: \u20B9" + String.format("%.2f", booking.total) + ".");
        return booking;
    }

    public static void main(String[] args) {
        Show sevenPm = new Show("7 PM Show");
        sevenPm.addSeat(new RegularSeat("A1"));
        sevenPm.addSeat(new RegularSeat("A2"));
        sevenPm.addSeat(new PremiumSeat("F5"));
        sevenPm.addSeat(new ReclinerSeat("R1"));

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Booking ashaBooking = book(asha, sevenPm, "A1", "A2", "F5");
        book(ravi, sevenPm, "A2");
        book(ravi, sevenPm, "R1");
        ashaBooking.cancel();
        book(neha, sevenPm, "A2");
    }
}
