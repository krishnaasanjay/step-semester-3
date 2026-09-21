public class TalkingToyBox {

    static abstract class Toy {
        private static int counter = 1000;
        private final String toyId;

        public Toy() {
            counter++;
            this.toyId = "TOY-" + counter;
        }

        public abstract String makeSound();

        String getToyId() {
            return toyId;
        }
    }

    static class ToyCar extends Toy {
        String name;

        public ToyCar(String name) {
            super();
            this.name = name;
        }

        @Override
        public String makeSound() {
            return name + ": Vroom vroom!";
        }
    }

    static class ToyRobot extends Toy {
        String name;

        public ToyRobot(String name) {
            super();
            this.name = name;
        }

        @Override
        public String makeSound() {
            return name + ": Beep boop!";
        }
    }

    public static void main(String[] args) {
        ToyCar c = new ToyCar("Speedster");
        System.out.println(c.makeSound());

        ToyRobot r = new ToyRobot("Bolt");
        System.out.println(r.makeSound());

        System.out.println(c.getToyId());
        System.out.println(r.getToyId());
    }
}
