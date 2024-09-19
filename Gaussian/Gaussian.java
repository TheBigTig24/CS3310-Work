package Gaussian;

public class Gaussian {
        public static void main(String[] args) {
            double[][] test = {{3, 4, 3, 10}, {1, 5, -3, 7}, {6, 3, 7, 15}};
            printMatrix(test);
            System.out.println();

            double[] c = new double[test.length];

            test = FwdElimination(test, c);
            printMatrix(test);

            double[] solution = BackSub(test, c, new double[test.length]);
            printArray(solution);
        }

        private static double[][] FwdElimination(double[][] arr, double[] c) {
            for (int k = 0; k < arr.length - 1; k++) {
                for (int i = k + 1; i < arr.length; i++) {
                    double mult = arr[i][k] / arr[k][k];
                    for (int j = k; j < arr.length; j++) {
                        arr[i][j] = arr[i][j] - mult * arr[k][j];
                    }
                    c[i] = c[i] - mult * c[k];
                }
            }
            return arr;
        }

        private static double[] BackSub(double[][] arr, double[] c, double[] solution) {
            solution[solution.length - 1] = c[c.length - 1] / arr[arr.length - 1][arr[0].length - 1];
            for (int i = arr.length - 1; i > 0; i--) {
                double sum = c[i];
                for (int j = i + 1; j < arr[i].length; j++) {
                    sum = sum - arr[i][j] * solution[j];
                }
                solution[i] = sum / arr[i][i];
            }
            return solution;
        }

        private static void printMatrix(double[][] arr) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    System.out.print(arr[i][j] + "   ");
                }
                System.out.print("\n");
            }
            
        }

        private static void printArray(double[] arr) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }
