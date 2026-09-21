public class TypingSpeedTestAccuracyChecker {

    static void checkTypingAccuracy(String original, String typed) {
        int total = original.length();
        int matched = 0;
        int firstMismatchPos = -1;

        for (int i = 0; i < total; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatchPos == -1) {
                firstMismatchPos = i + 1;
            }
        }

        double accuracy = (matched / (double) total) * 100;

        StringBuilder result = new StringBuilder();
        result.append("Matched: ").append(matched).append("/").append(total);
        result.append(String.format(" | Accuracy: %.2f%%", accuracy));

        if (firstMismatchPos == -1) {
            result.append(" | No Mismatches");
        } else {
            char originalChar = original.charAt(firstMismatchPos - 1);
            char typedChar = typed.charAt(firstMismatchPos - 1);
            result.append(" | First Mismatch at position ").append(firstMismatchPos)
                  .append(" ('").append(originalChar).append("' vs '").append(typedChar).append("')");
        }

        System.out.println(result);
    }

    public static void main(String[] args) {
        checkTypingAccuracy("hello world", "hello worlt");
        checkTypingAccuracy("coding", "coding");
    }
}
