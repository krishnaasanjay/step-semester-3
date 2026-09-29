import java.util.HashMap;
import java.util.Map;

/**
 * Q1. The Hostel Laundry Queue
 * WashType is an interface so a new type (e.g. Delicate) can be added
 * without touching WashingMachine or the booking workflow. A machine
 * guards its own busy/free status - no outside code can flip it directly.
 */
public class HostelLaundryQueue {

    interface WashType {
        String name();
        int durationMinutes();
        double charge();
    }

    static class QuickWash implements WashType {
        public String name() { return "Quick"; }
        public int durationMinutes() { return 30; }
        public double charge() { return 20; }
    }

    static class NormalWash implements WashType {
        public String name() { return "Normal"; }
        public int durationMinutes() { return 45; }
        public double charge() { return 30; }
    }

    static class HeavyWash implements WashType {
        public String name() { return "Heavy"; }
        public int durationMinutes() { return 60; }
        public double charge() { return 45; }
    }

    static class Student {
        final String name;
        Student(String name) { this.name = name; }
    }

    static class WashCycle {
        final Student student;
        final WashType washType;
        WashCycle(Student student, WashType washType) {
            this.student = student;
            this.washType = washType;
        }
    }

    static class WashingMachine {
        final String id;
        private boolean busy = false;
        private WashCycle currentCycle;

        WashingMachine(String id) { this.id = id; }

        boolean isBusy() { return busy; }

        boolean startWash(Student student, WashType washType) {
            if (busy) {
                System.out.println("Machine " + id + " is currently busy.");
                return false;
            }
            currentCycle = new WashCycle(student, washType);
            busy = true;
            System.out.println(washType.name() + " wash started on " + id + " for " + student.name
                    + " (" + washType.durationMinutes() + " min). Charge: \u20B9"
                    + String.format("%.2f", washType.charge()) + ".");
            return true;
        }

        void completeCycle() {
            if (!busy) return;
            busy = false;
            currentCycle = null;
            System.out.println(id + " cycle completed. " + id + " is now free.");
        }
    }

    public static void main(String[] args) {
        Map<String, WashingMachine> machines = new HashMap<>();
        machines.put("M1", new WashingMachine("M1"));
        machines.put("M2", new WashingMachine("M2"));

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        machines.get("M1").startWash(asha, new QuickWash());
        machines.get("M1").startWash(ravi, new HeavyWash());
        machines.get("M2").startWash(ravi, new HeavyWash());
        machines.get("M1").completeCycle();
        machines.get("M1").startWash(neha, new NormalWash());
    }
}
