import java.util.HashMap;
import java.util.Map;

/**
 * Q1. Vehicle Rental System
 * Vehicle is abstract; each category supplies its own pricing rule, so new
 * categories can be added without touching RentalService.
 */
public class VehicleRentalSystem {

    static class Customer {
        final String name;
        Customer(String name) { this.name = name; }
    }

    static abstract class Vehicle {
        private final String name;
        private boolean available = true;

        Vehicle(String name) { this.name = name; }

        String getName() { return name; }
        boolean isAvailable() { return available; }
        void markRented() { available = false; }
        void markReturned() { available = true; }

        abstract double calculateCharge(int days);
    }

    static class Sedan extends Vehicle {
        Sedan(String name) { super(name); }
        double calculateCharge(int days) { return 40.0 * days; }
    }

    static class Suv extends Vehicle {
        Suv(String name) { super(name); }
        double calculateCharge(int days) { return 60.0 * days; }
    }

    static class Truck extends Vehicle {
        Truck(String name) { super(name); }
        double calculateCharge(int days) { return 90.0 * days + 50.0; } // flat loading fee
    }

    static class Rental {
        final Vehicle vehicle;
        final Customer customer;
        final int days;
        final double charge;

        Rental(Vehicle vehicle, Customer customer, int days) {
            this.vehicle = vehicle;
            this.customer = customer;
            this.days = days;
            this.charge = vehicle.calculateCharge(days);
        }
    }

    static class RentalService {
        private final Map<Vehicle, Rental> activeRentals = new HashMap<>();

        Rental rent(Customer customer, Vehicle vehicle, int days) {
            if (!vehicle.isAvailable()) {
                System.out.println(vehicle.getName() + " is currently unavailable.");
                return null;
            }
            Rental rental = new Rental(vehicle, customer, days);
            vehicle.markRented();
            activeRentals.put(vehicle, rental);
            System.out.println(vehicle.getName() + " rented successfully by " + customer.name
                    + ". Rental charge: $" + String.format("%.2f", rental.charge) + ".");
            return rental;
        }

        void returnVehicle(Vehicle vehicle) {
            Rental rental = activeRentals.remove(vehicle);
            if (rental == null) {
                System.out.println(vehicle.getName() + " is not currently rented.");
                return;
            }
            vehicle.markReturned();
            System.out.println(vehicle.getName() + " returned by " + rental.customer.name + ".");
        }
    }

    public static void main(String[] args) {
        RentalService service = new RentalService();
        Customer c1 = new Customer("Customer 1");
        Customer c2 = new Customer("Customer 2");
        Customer c3 = new Customer("Customer 3");
        Vehicle sedanA = new Sedan("Sedan A");
        Vehicle suvB = new Suv("SUV B");

        service.rent(c1, sedanA, 3);
        service.rent(c2, sedanA, 2);
        service.returnVehicle(sedanA);
        service.rent(c3, suvB, 5);
    }
}
