public class ReverseCustomerName {

    static String reverseCustomerName(String customerName) {
        char[] chars = customerName.toCharArray();
        StringBuilder reversed = new StringBuilder();
        for (int i = chars.length - 1; i >= 0; i--) {
            reversed.append(chars[i]);
        }
        return reversed.toString();
    }

    public static void main(String[] args) {
        String customerName = "Sunil";
        String reversedName = reverseCustomerName(customerName);
        System.out.println("Original Name: " + customerName);
        System.out.println("Reversed Name: " + reversedName);
    }
}
