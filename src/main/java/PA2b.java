import java.util.Scanner;

public class PA2b {
    public static boolean hasSequence(int[] values, int length) {
        // TODO: Return true if the array contains consecutive equal values of this length.
        if (length <= 0) {
            return true;
        }
        if (values == null || values.length < length) {
            return false;
        }

        int currentRun = 1;
        for (int i = 1; i < values.length; i++) {
            if (currentRun >= length) {
                return true;
            }
            if (values[i] == values[i - 1]) {
                currentRun++;
            } else {
                currentRun = 1;
            }
        }
        return currentRun >= length;
    }

    public static int maxConsecutiveLength(int[] values) {
        // TODO: Return the longest run of equal values. Return 0 for an empty array.
        if (values == null || values.length == 0) {
            return 0;
        }

        int maxRun = 1;
        int currentRun = 1;

        for (int i = 1; i < values.length; i++) {
            if (values[i] == values[i - 1]) {
                currentRun++;
            } else {
                if (currentRun > maxRun) {
                    maxRun = currentRun;
                }
                currentRun = 1;
            }
        }

        if (currentRun > maxRun) {
            maxRun = currentRun;
        }
        return maxRun;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // TODO: Read the number of values, then the values, and print the maximum length.
        System.out.print("Enter the number of values: ");
        int count = input.nextInt();

        int[] data = new int[count];
        System.out.print("Enter the values: ");
        for (int i = 0; i < count; i++) {
            data[i] = input.nextInt();
        }

        int maxLen = maxConsecutiveLength(data);
        System.out.println("The maximum length of consecutive values is " + maxLen + ".");
    }
}
