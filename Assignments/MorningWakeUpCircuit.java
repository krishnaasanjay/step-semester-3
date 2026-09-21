public class MorningWakeUpCircuit {

    interface Ringable {
        String ring();
    }

    static class AlarmClock implements Ringable {
        String time;

        public AlarmClock(String time) {
            this.time = time;
        }

        @Override
        public String ring() {
            return "Alarm ringing for " + time;
        }
    }

    static class Doorbell implements Ringable {
        String location;

        public Doorbell(String location) {
            this.location = location;
        }

        @Override
        public String ring() {
            return "Doorbell ringing at " + location;
        }
    }

    static void ringAll(Ringable[] devices) {
        for (Ringable device : devices) {
            System.out.println(device.ring());
        }
    }

    public static void main(String[] args) {
        AlarmClock a = new AlarmClock("7:00 AM");
        System.out.println(a.ring());

        Doorbell d = new Doorbell("Front Door");
        System.out.println(d.ring());

        ringAll(new Ringable[]{a, d});
    }
}
