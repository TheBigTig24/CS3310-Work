package Program3;

import java.util.Random;

public class Prog3 {
    
    public static void main(String[] args) {
        int[][] matrix = generateMatrix();
        printMatrix(matrix);
        printRecursive(matrix, matrix.length - 1, matrix[0].length - 1);
    }


    // its time complexity is nlogn
    private static void printRecursive(int[][] A, int row, int col) {
        if (row == 0 && col == 0) {
            // done
        } else if (row == 0) {
            printRecursive(A, row, col - 1);
            System.out.println("Right");
        } else if (col == 0) {
            printRecursive(A, row - 1, col);
            System.out.println("Down");
        } else {
            int max = Math.max(A[row - 1][col - 1], A[row - 1][col]);
            max = Math.max(max, A[row][col - 1]);
            if (max == A[row - 1][col - 1]) {
                printRecursive(A, row - 1, col - 1);
                System.out.println("Diagonal");
            } else if (max == A[row - 1][col]) {
                printRecursive(A, row - 1, col);
                System.out.println("Down");
            } else {
                printRecursive(A, row, col - 1);
                System.out.println("Right");
            }
        }
    }

    /*
     * Generates Random Matrix
     *  - generated row is between 4 and 10
     *  - generated col is between 4 and 10
     *  - generated values are between 0 and 20
     */
    private static int[][] generateMatrix() {
        Random rand = new Random();
        int row = rand.nextInt(10 - 4) + 4;
        int col = rand.nextInt(10 - 4) + 4;

        int[][] randomMatrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                randomMatrix[i][j] = rand.nextInt(20);
            }
        }
        return randomMatrix;
    }
    

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
