import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Q4. Hotel Booking System
 * Room is abstract with its own pricing rule. A room rejects any reservation
 * overlapping an active one; cancellation is only allowed before a deadline.
 */
public class HotelBookingSystem {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("MMM d", Locale.ENGLISH);

    static class Customer {
        final String name;
        Customer(String name) { this.name = name; }
    }

    static abstract class Room {
        final int number;
        private final List<Reservation> reservations = new ArrayList<>();

        Room(int number) { this.number = number; }

        abstract String category();
        abstract double calculatePrice(int nights);

        String label() { return category() + " Room " + number; }

        boolean isAvailable(LocalDate start, LocalDate end) {
            for (Reservation r : reservations) {
                if (r.isActive() && r.start.isBefore(end) && start.isBefore(r.end)) {
                    return false;
                }
            }
            return true;
        }

        void addReservation(Reservation r) { reservations.add(r); }
    }

    static class StandardRoom extends Room {
        StandardRoom(int n) { super(n); }
        String category() { return "Standard"; }
        double calculatePrice(int nights) { return 100.0 * nights; }
    }

    static class DeluxeRoom extends Room {
        DeluxeRoom(int n) { super(n); }
        String category() { return "Deluxe"; }
        double calculatePrice(int nights) { return 180.0 * nights; }
    }

    static class Suite extends Room {
        Suite(int n) { super(n); }
        String category() { return "Suite"; }
        double calculatePrice(int nights) { return 300.0 * nights + 75.0; } // flat amenities fee
    }

    static class Reservation {
        final Customer customer;
        final Room room;
        final LocalDate start, end;
        private boolean cancelled = false;

        Reservation(Customer customer, Room room, LocalDate start, LocalDate end) {
            this.customer = customer;
            this.room = room;
            this.start = start;
            this.end = end;
        }

        boolean isActive() { return !cancelled; }
        LocalDate cancellationDeadline() { return start.minusDays(1); }
        String period() { return start.format(FMT) + "-" + end.getDayOfMonth(); }
        double price() { return room.calculatePrice((int) ChronoUnit.DAYS.between(start, end)); }

        boolean cancel(LocalDate today) {
            if (!today.isBefore(cancellationDeadline())) {
                System.out.println("Cannot cancel: the cancellation deadline has passed.");
                return false;
            }
            cancelled = true;
            System.out.println("Reservation for " + customer.name + ", " + room.label()
                    + " (" + period() + ") cancelled successfully.");
            return true;
        }
    }

    static void checkAvailability(Room room, LocalDate start, LocalDate end) {
        String range = start.format(FMT) + " to " + end.format(FMT);
        System.out.println(room.label() + (room.isAvailable(start, end)
                ? " is available from " : " is not available from ") + range + ".");
    }

    static Reservation reserve(Customer customer, Room room, LocalDate start, LocalDate end) {
        if (!room.isAvailable(start, end)) {
            System.out.println(room.label() + " is not available from "
                    + start.format(FMT) + " to " + end.format(FMT) + ".");
            return null;
        }
        Reservation r = new Reservation(customer, room, start, end);
        room.addReservation(r);
        System.out.println("Reservation confirmed for " + customer.name + ", " + room.label()
                + " (" + r.period() + "). Price: $" + String.format("%.2f", r.price()) + ".");
        return r;
    }

    public static void main(String[] args) {
        Room standard101 = new StandardRoom(101);
        Room deluxe201 = new DeluxeRoom(201);
        Customer a = new Customer("Customer A");
        Customer b = new Customer("Customer B");
        Customer c = new Customer("Customer C");
        LocalDate today = LocalDate.of(2024, 12, 20);

        checkAvailability(standard101, LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 5));
        Reservation resA = reserve(a, standard101, LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 5));
        reserve(b, standard101, LocalDate.of(2025, 1, 3), LocalDate.of(2025, 1, 7));
        resA.cancel(today);
        reserve(c, deluxe201, LocalDate.of(2025, 2, 10), LocalDate.of(2025, 2, 12));
    }
}
