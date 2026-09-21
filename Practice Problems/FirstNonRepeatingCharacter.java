public class FirstNonRepeatingCharacter {

    static void findFirstNonRepeatingChar(String text) {
        int[] frequency = new int[256];
        for (int i = 0; i < text.length(); i++) {
            frequency[text.charAt(i)]++;
        }

        for (int i = 0; i < text.length(); i++) {
            if (frequency[text.charAt(i)] == 1) {
                System.out.println("First Non-Repeating Character: '" + text.charAt(i) + "'");
                return;
            }
        }
        System.out.println("No Non-Repeating Character Found");
    }

    public static void main(String[] args) {
        findFirstNonRepeatingChar("swiss");
        findFirstNonRepeatingChar("aabbcc");
    }
}
