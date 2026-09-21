public class MembershipFieldReachChecker {

    static class LibraryMember {
        private String membershipPin;
        String branchCode;
        protected double finesOwed;
        public String displayName;

        LibraryMember(String membershipPin, String branchCode, double finesOwed, String displayName) {
            this.membershipPin = membershipPin;
            this.branchCode = branchCode;
            this.finesOwed = finesOwed;
            this.displayName = displayName;
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
                    return (accessorContext.equals("SAME_CLASS") ||
                            accessorContext.equals("SAME_PACKAGE") ||
                            accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"))
                            ? "ALLOWED" : "DENIED";
                default:
                    throw new IllegalArgumentException("Unknown modifier: " + fieldModifier);
            }
        }

        static String summarizeByModifier(String[][] attempts) {
            String[] modifiers = {"private", "default", "protected", "public"};
            int[] allowedCounts = new int[modifiers.length];
            int[] deniedCounts = new int[modifiers.length];

            for (String[] attempt : attempts) {
                String modifier = attempt[0];
                String context = attempt[1];
                String result = classifyAccess(modifier, context);

                for (int i = 0; i < modifiers.length; i++) {
                    if (modifiers[i].equals(modifier)) {
                        if (result.equals("ALLOWED")) {
                            allowedCounts[i]++;
                        } else {
                            deniedCounts[i]++;
                        }
                        break;
                    }
                }
            }

            StringBuilder result = new StringBuilder();
            for (int i = 0; i < modifiers.length; i++) {
                if (i > 0) {
                    result.append(" | ");
                }
                result.append(modifiers[i]).append(": ").append(allowedCounts[i])
                      .append(" allowed / ").append(deniedCounts[i]).append(" denied");
            }
            return result.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(AccessChecker.classifyAccess("private", "SAME_CLASS"));
        System.out.println(AccessChecker.classifyAccess("protected", "DIFFERENT_PACKAGE"));

        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println(AccessChecker.summarizeByModifier(attempts));
    }
}
