import java.util.Scanner;

public class PA2a {
    public static boolean isNumeric(String number) {
        // TODO: Return true only when every character is a digit.
        if (number == null || number.length() == 0) {
            return false;
        }
        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (ch < '0' || ch > '9') {
                return false;
            }
        }
        return true;
    }

    public static boolean hasValidPrefix(String number) {
        // TODO: Check for prefixes 4, 5, 37, or 6.
        if (number.startsWith("4") || number.startsWith("5") || number.startsWith("6")) {
            return true;
        }
        if (number.startsWith("37")) {
            return true;
        }
        return false;
    }

    public static int getDigit(int number) {
        // TODO: Return number when it is one digit; otherwise return the sum of its digits.
        if (number < 10) {
            return number;
        }
        int firstDigit = number % 10;
        int secondDigit = number / 10;
        return firstDigit + secondDigit;
    }

    public static int sumOfDoubleEvenPlace(String number) {
        // TODO: Double every second digit from right to left and add the results.
        int sum = 0;
        for (int i = number.length() - 2; i >= 0; i -= 2) {
            int digit = number.charAt(i) - '0';
            int doubled = digit * 2;
            sum += getDigit(doubled);
        }
        return sum;
    }

    public static int sumOfOddPlace(String number) {
        // TODO: Add the digits in odd places from right to left.
        int sum = 0;
        for (int i = number.length() - 1; i >= 0; i -= 2) {
            int digit = number.charAt(i) - '0';
            sum += digit;
        }
        return sum;
    }
    public static boolean isValid(String number) {
        // TODO: Verify length, numeric input, prefix, and the Luhn checksum.
        if (number.length() < 13 || number.length() > 16) {
            return false;
        }
        if (!isNumeric(number)) {
            return false;
        }
        if (!hasValidPrefix(number)) {
            return false;
        }

        int totalSum = sumOfDoubleEvenPlace(number) + sumOfOddPlace(number);
        if (totalSum % 10 == 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // TODO: Prompt for a card number and print the correct status message.
        // Validate in this order: too short, too long, non-numeric, invalid prefix, Luhn result.
        System.out.print("Enter a credit card number: ");
        String number = input.nextLine();

        if (number.length() < 13) {
            System.out.println("Status: Input is too short");
        } else if (number.length() > 16) {
            System.out.println("Status: Input is too long");
        } else if (!isNumeric(number)) {
            System.out.println("Status: Non-numeric input");
        } else if (!hasValidPrefix(number)) {
            System.out.println("Status: Invalid prefix");
        } else if (isValid(number)) {
            System.out.println("Status: card is valid");
        } else {
            System.out.println("Status: card is invalid");
        }
    }
}
