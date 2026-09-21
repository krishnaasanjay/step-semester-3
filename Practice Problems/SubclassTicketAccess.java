public class SubclassTicketAccess {

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
    }

    public static void main(String[] args) {
        System.out.println(AccessChecker.classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
        System.out.println(AccessChecker.classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
    }
}
