public class MovieTicketFieldVisibilityChecker {

    static class MovieTicket {
        private String seatNumber;
        String screenId;
        protected double ticketPrice;
        public String movieTitle;

        MovieTicket(String seatNumber, String screenId, double ticketPrice, String movieTitle) {
            this.seatNumber = seatNumber;
            this.screenId = screenId;
            this.ticketPrice = ticketPrice;
            this.movieTitle = movieTitle;
        }
    }

    static class AccessChecker {

        static String classifyAccess(String fieldModifier, String accessorContext) {
            switch (fieldModifier) {
                case "public":
                    return "ALLOWED";
                case "private":
                    return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";
                case "default":
                    return (accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE"))
                            ? "ALLOWED" : "DENIED";
                case "protected":
                    return (accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE"))
                            ? "ALLOWED" : "DENIED";
                default:
                    throw new IllegalArgumentException("Unknown modifier: " + fieldModifier);
            }
        }

        static String summarizeBatch(String[][] attempts) {
            int allowed = 0;
            int denied = 0;
            for (String[] attempt : attempts) {
                if (classifyAccess(attempt[0], attempt[1]).equals("ALLOWED")) {
                    allowed++;
                } else {
                    denied++;
                }
            }
            return "Allowed: " + allowed + " | Denied: " + denied;
        }
    }

    public static void main(String[] args) {
        System.out.println(AccessChecker.classifyAccess("private", "SAME_CLASS"));
        System.out.println(AccessChecker.classifyAccess("protected", "DIFFERENT_PACKAGE"));

        String[][] attempts = {
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println(AccessChecker.summarizeBatch(attempts));
    }
}
