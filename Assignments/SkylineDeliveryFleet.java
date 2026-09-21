public class SkylineDeliveryFleet {

    static abstract class Drone {
        public abstract String fly();
    }

    interface Trackable {
        String getLocation();
    }

    static class DeliveryDrone extends Drone implements Trackable {
        String id;

        public DeliveryDrone(String id) {
            this.id = id;
        }

        @Override
        public String fly() {
            return id + " flying to deliver package";
        }

        @Override
        public String getLocation() {
            return id + " at Sector 4";
        }
    }

    static class ScoutDrone extends Drone {
        String id;

        public ScoutDrone(String id) {
            this.id = id;
        }

        @Override
        public String fly() {
            return id + " flying on scout patrol";
        }
    }

    static class GroundRobot implements Trackable {
        String id;

        public GroundRobot(String id) {
            this.id = id;
        }

        @Override
        public String getLocation() {
            return id + " at Sector 4";
        }
    }

    static String getLocationIfTrackable(Object o) {
        if (o instanceof Trackable) {
            Trackable trackable = (Trackable) o;
            return trackable.getLocation();
        }
        return "Tracking not available";
    }

    public static void main(String[] args) {
        DeliveryDrone d = new DeliveryDrone("DR-1");
        System.out.println(getLocationIfTrackable(d));

        ScoutDrone s = new ScoutDrone("SC-1");
        System.out.println(getLocationIfTrackable(s));

        GroundRobot g = new GroundRobot("GR-1");
        System.out.println(getLocationIfTrackable(g));
    }
}
