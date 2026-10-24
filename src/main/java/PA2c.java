import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PA2c {
    public static boolean canMultiply(int[][] first, int[][] second) {
        // TODO: Check whether first's column count equals second's row count.
        if (first == null || second == null || first.length == 0 || second.length == 0) {
            return false;
        }
        return first[0].length == second.length;
    }

    public static int[][] readMatrix(Scanner input) {
        // TODO: Read rows, columns, and all values in row-major order.
        if (!input.hasNextInt()) {
            return new int[0][0];
        }
        int rows = input.nextInt();
        int cols = input.nextInt();

        int[][] matrix = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                matrix[r][c] = input.nextInt();
            }
        }
        return matrix;
    }

    public static int[][] matrixMultiply(int[][] first, int[][] second) {
        // TODO: Compute and return the product. Assume the dimensions are valid.
        int rows1 = first.length;
        int cols1 = first[0].length;
        int cols2 = second[0].length;

        int[][] result = new int[rows1][cols2];
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                for (int k = 0; k < cols1; k++) {
                    result[i][j] += first[i][k] * second[k][j];
                }
            }
        }
        return result;
    }

    public static void printMatrix(int[][] matrix) {
        // TODO: Print rows to the terminal with spaces between values.
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                System.out.print(matrix[r][c]);
                if (c < matrix[r].length - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void writeMatrix(int[][] matrix, PrintWriter output) {
        // TODO: Write rows, columns, and values using the required file format.
        output.println(matrix.length);
        output.println(matrix[0].length);
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                output.print(matrix[r][c]);
                if (c < matrix[r].length - 1) {
                    output.print(" ");
                }
            }
            output.println();
        }
    }

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        // TODO: Prompt for both input paths and the result path.
        System.out.print("Enter path for matrix 1: ");
        String path1 = keyboard.nextLine();
        System.out.print("Enter path for matrix 2: ");
        String path2 = keyboard.nextLine();
        System.out.print("Enter path for result: ");
        String pathResult = keyboard.nextLine();

        Scanner file1Scanner = null;
        Scanner file2Scanner = null;
        PrintWriter fileOut = null;

        try {
            file1Scanner = new Scanner(new File(path1));
            file2Scanner = new Scanner(new File(path2));

            int[][] m1 = readMatrix(file1Scanner);
            int[][] m2 = readMatrix(file2Scanner);

            // TODO: Read and display both matrices with X and = between them.
            printMatrix(m1);
            System.out.println("X");
            printMatrix(m2);
            System.out.println("=");

            // TODO: Print "Bad matrix dimensions" when multiplication is impossible.
            if (!canMultiply(m1, m2)) {
                System.out.println("Bad matrix dimensions");
            } else {
                // TODO: Otherwise display and write the product.
                int[][] product = matrixMultiply(m1, m2);
                printMatrix(product);

                fileOut = new PrintWriter(new File(pathResult));
                writeMatrix(product, fileOut);
            }
        } catch (FileNotFoundException e) {
            // TODO: Print "Error opening file(s)" if an input cannot be opened or read.
            System.out.println("Error opening file(s)");
        } finally {
            if (file1Scanner != null) file1Scanner.close();
            if (file2Scanner != null) file2Scanner.close();
            if (fileOut != null) fileOut.close();
        }
    }
}
