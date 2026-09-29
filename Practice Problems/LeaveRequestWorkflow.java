import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 * Q2. Employee Leave Request Workflow
 * Each employee type owns its leave-limit rule; LeaveRequest guards its own
 * state transitions (Pending -> Approved/Rejected only, never back).
 */
public class LeaveRequestWorkflow {

    enum Status {
        PENDING, APPROVED, REJECTED;
        String label() { return name().charAt(0) + name().substring(1).toLowerCase(); }
    }

    static abstract class Employee {
        final String name;
        Employee(String name) { this.name = name; }

        abstract int maxLeaveDays();

        LeaveRequest submitLeave(LocalDate start, LocalDate end) {
            int days = (int) ChronoUnit.DAYS.between(start, end) + 1;
            if (days > maxLeaveDays()) {
                System.out.println("Leave request denied for " + name + ": exceeds the "
                        + maxLeaveDays() + "-day limit for this employee type.");
                return null;
            }
            LeaveRequest request = new LeaveRequest(this, start, end);
            System.out.println("Leave request submitted for " + name + " (" + request.period()
                    + "). Status: " + request.getStatus().label() + ".");
            return request;
        }
    }

    static class FullTimeEmployee extends Employee {
        FullTimeEmployee(String name) { super(name); }
        int maxLeaveDays() { return 20; }
    }

    static class PartTimeEmployee extends Employee {
        PartTimeEmployee(String name) { super(name); }
        int maxLeaveDays() { return 5; }
    }

    static class Contractor extends Employee {
        Contractor(String name) { super(name); }
        int maxLeaveDays() { return 3; }
    }

    static class LeaveRequest {
        private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("MMM d", Locale.ENGLISH);
        final Employee employee;
        final LocalDate start, end;
        private Status status = Status.PENDING;

        LeaveRequest(Employee employee, LocalDate start, LocalDate end) {
            this.employee = employee;
            this.start = start;
            this.end = end;
        }

        Status getStatus() { return status; }

        String period() {
            return start.format(FMT) + "-" + end.getDayOfMonth();
        }

        // The only door for status changes: Pending -> Approved/Rejected, nothing else.
        boolean changeStatus(Status target) {
            if (status != Status.PENDING || target == Status.PENDING) {
                System.out.println("Cannot change leave request status from "
                        + status.label() + " to " + target.label() + ".");
                return false;
            }
            status = target;
            return true;
        }
    }

    static class Manager {
        final String name;
        Manager(String name) { this.name = name; }

        void review(LeaveRequest request, boolean approve) {
            Status target = approve ? Status.APPROVED : Status.REJECTED;
            if (request.changeStatus(target)) {
                System.out.println(request.employee.name + "'s leave request (" + request.period() + ") "
                        + target.label().toLowerCase() + ". Status: " + target.label() + ".");
            }
        }
    }

    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("John");
        Employee jane = new PartTimeEmployee("Jane");
        Manager alice = new Manager("Alice");
        Manager bob = new Manager("Bob");

        LeaveRequest johnReq = john.submitLeave(LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 5));
        alice.review(johnReq, true);

        LeaveRequest janeReq = jane.submitLeave(LocalDate.of(2025, 2, 10), LocalDate.of(2025, 2, 11));
        bob.review(janeReq, false);

        johnReq.changeStatus(Status.PENDING);
    }
}
