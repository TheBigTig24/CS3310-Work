package Gaussian;


import java.io.File;
import java.util.Scanner;
public class Gaussian {
        public static void main(String[] args) {

            double[][] arr = readFile(0);
            printMatrix(arr);

            double[] constants = readConstants(arr.length);
            printArray(constants);

            if (args.length == 0) {

                long start = System.nanoTime();
                double[] sol = naiveGaussian(arr, constants, new double[arr.length]);
                printArray(sol);
                long end = System.nanoTime();
                long CPUTime = end - start;
                System.out.println("Naive Gaussian Time: " + CPUTime + "\n");

            } else if (args[0].equals("--spp")) {

                int[] ind = new int[arr.length];
                for (int i = 0; i < ind.length; i++) {
                    ind[i] = i;
                }
                long start = System.nanoTime();
                double[] sol = SPPElimination(arr, constants, ind);
                printArray(sol);
                long end = System.nanoTime();
                long CPUTime = end - start;
                System.out.println("SPP Gaussian Time: " + CPUTime + "\n");

            } else if (args[0].equals("--double")) {

                System.out.println("lei go ma zhen hai tai fay");

            }
            
        }

        private static double[] naiveGaussian(double[][] arr, double[] c, double[] solution) {
            // Forward Elimination
            for (int k = 0; k < arr.length - 1; k++) {
                for (int i = k + 1; i < arr.length; i++) {
                    // get factor to scale other rows
                    double mult = arr[i][k] / arr[k][k];
                    for (int j = k; j < arr.length; j++) {
                        // scale system of equations
                        arr[i][j] = arr[i][j] - mult * arr[k][j];
                    }
                    // scale constants
                    c[i] = c[i] - mult * c[k];
                }
            }

            // Back Substitution
            solution[solution.length - 1] = c[c.length - 1] / arr[arr.length - 1][arr[0].length - 1];
            for (int i = arr.length - 2; i >= 0; i--) {
                double sum = c[i];
                for (int j = i + 1; j < arr[i].length; j++) {
                    sum = sum - arr[i][j] * solution[j];
                }
                solution[i] = sum / arr[i][i];
            }
            return solution;
        }

        private static double[] SPPElimination(double[][] arr, double[] c, int[] ind) {
            double[] scaling = new double[arr.length];

            for (int i = 0; i < arr.length; i++) {
                double smax = 0;
                for (int j = 0; j < arr.length; j++) {
                    smax = Math.max(smax, Math.abs(arr[i][j]));
                }
                scaling[i] = smax;
            }

            for (int k = 0; k < arr.length - 1; k++) {
                double rmax = 0;
                int maxInd = k;
                for (int i = k; i < arr.length; i++) {
                    double r = Math.abs(arr[ind[i]][k] / scaling[ind[i]]);
                    if (r > rmax) {
                        rmax = r;
                        maxInd = i;
                    }
                }
                int temp = ind[maxInd];
                ind[maxInd] = ind[k];
                ind[k] = temp;
                for (int i = k + 1; i < arr.length; i++) {
                    double mult = arr[ind[i]][k] / arr[ind[k]][k];
                    for (int j = k + 1; j < arr.length; j++) {
                        arr[ind[i]][j] = arr[ind[i]][j] - mult * arr[ind[k]][j];
                    }
                    c[ind[i]] = c[ind[i]] - mult * c[ind[k]];
                }
            }

            // Back Substitution
            double[] solution = new double[arr.length];
            double sum = 0;
            solution[arr.length - 1] = c[ind[arr.length - 1]] / arr[ind[arr.length - 1]][arr.length - 1];
            for (int i = arr.length - 2; i >= 0; i--) {
                sum = c[ind[i]];
                for (int j = i + 1; j < arr.length; j++) {
                    sum = sum - arr[ind[i]][j] * solution[j];
                }
                solution[i] = sum / arr[ind[i]][i];
            }
            return solution;
        }




        private static double[][] readFile(int length) {
            try {
                File obj = new File("sys1.lin");
                Scanner reader = new Scanner(obj);
                if (reader.hasNextInt()) {
                    length = reader.nextInt();
                    reader.nextLine();
                }
                double[][] arr = new double[length][length];
                for (int i = 0; i < length; i++) {
                    for (int j = 0; j < length; j++) {
                        if (reader.hasNextDouble()) {
                            arr[i][j] = reader.nextDouble();
                        }
                    }
                }
                reader.close();
                return arr;
            } catch (Exception e) {
                System.out.println("i <3 hanni: " + e);
                return null;
            }
        }

        private static double[] readConstants(int length) {
            try {
                File obj = new File("sys1.lin");
                Scanner reader = new Scanner(obj);
                if (reader.hasNextInt()) {
                    length = reader.nextInt();
                    reader.nextLine();
                }
                double[] arr = new double[length];
                for (int i = 0; i < length; i++) {
                    reader.nextLine();
                }
                for (int i = 0; i < length; i++) {
                    if (reader.hasNextDouble()) {
                        arr[i] = reader.nextDouble();
                    }
                }
                reader.close();
                return arr;
            } catch (Exception e) {
                System.out.println("i <3 hanni: " + e);
                return null;
            }
        }

        private static void printMatrix(double[][] arr) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    System.out.print(arr[i][j] + "   ");
                }
                System.out.print("\n");
            }
            System.out.println();
        }

        private static void printArray(double[] arr) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }
