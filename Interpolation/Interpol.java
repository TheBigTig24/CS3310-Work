package Interpolation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Interpol {
    
    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        /* 
         * Possible Command Line Options
         * 
         * java Interpol.java  
         *      - Applies Newton's Interpolation to Coordinates in 'points.lin'
         *      - Type Float Values to Evaluate at Certain Points
         * 
         * java Interpol.java --random
         *      - Generates Random Data Set of Custom Size Before Applying Newton's Interpolation
         *      - Type Float Values to Evaluate at Certain Points
         */

        if (args.length == 0) {

            // Read vals from file to float arrays for use
            List<Float> coords = readFile("points.lin");
            float[] xs = new float[coords.size() / 2];
            float[] ys = new float[coords.size() / 2];
            for (int i = 0; i < coords.size() - (coords.size()/2); i++) {
                xs[i] = coords.get(i);
                ys[i] = coords.get(i + (coords.size()/2));
            }

            System.out.println("HERE ARE YOUR HONDA ACCORD-INATES: ");
            printCoords(xs, ys);

            System.out.println("If thou couldst enter a value, it shal be evaluated anon, otherwise type 'q' to quit: ");

            while(!scnr.hasNextFloat()) {
                String text = scnr.next();
                if (text.equals("q")) {
                    System.out.println("You have successfully quit the program.");
                    System.exit(0);
                } else {
                    System.out.println("If thou couldst enter a value, it shal be evaluated anon, otherwise type 'q' to quit: ");
                }
            }

            float valued = scnr.nextFloat();

            // get them mf coefficients
            float[] cs = new float[xs.length];
            cs = getCoeffs(xs, ys, cs);

            // do da nootin 
            float answer = performNewton(xs, ys, cs, valued);
            System.out.println("\nAnswer: " + answer);

        } else if (args.length == 1 && args[0].equals("--random")) {

            // make random data set bruv
            System.out.println("MAKE A CUSTOM DATA SET OF SIZE: ");
            int amt = scnr.nextInt();
            float[] randomXs = generateRandomXs(amt);
            float[] randomYs = generateRandomYs(amt);
            printCoords(randomXs, randomYs);

            System.out.println("HEY! WHAT POINT WOULD YOU LIKE TO EVALUATE THIS RANDOM FUNCTION AT?");
            float val = scnr.nextFloat();

            float[] newCs = new float[amt];
            newCs = getCoeffs(randomXs, randomYs, newCs);

            long start = System.nanoTime();
            float a2 = performNewton(randomXs, randomYs, newCs, val);
            long end = System.nanoTime();
            long CPUTime = end - start;

            System.out.println("\n\nf(" + val + ") = " + a2 +  "\nNumber of Inputs Given: " + amt + "\nTime Taken for Operation: " + CPUTime);

        } else {
            System.out.println("Bad choice buddy.");
        }
        scnr.close();
    }



    private static float performNewton(float[] xs, float[] ys, float[] cs, float z) {
        int len = xs.length - 1;
        float res = cs[len];

        for (int i = len - 1; i >= 0; i--) {
            res = res * (z - xs[i]) + cs[i];
        }
        return res;
    }

    private static float[] getCoeffs(float[] xs, float[] ys, float[] cs) {
        int len = xs.length;
        for (int i = 0; i < len; i++) {
            cs[i] = ys[i];
        }

        for (int j = 1; j < len; j++) {
            for (int i = len - 1; i >= j; i--) {
                cs[i] = (cs[i] - cs[i - 1]) / (xs[i] - xs[i - j]);
            }
        }

        return cs;
    }



    private static float[] generateRandomXs(int amt) {
        float[] xs = new float[amt];
        Random rndm = new Random();
        for (int i = 0; i < amt; i++) {
            xs[i] = rndm.nextFloat(100 + 100) - 100;
        }
        return xs;
    }

    private static float[] generateRandomYs(int amt) {
        float[] ys = new float[amt];
        Random rndm = new Random();
        for (int i = 0; i < amt; i++) {
            ys[i] = rndm.nextFloat(100 + 100) - 100;
        }
        return ys;
    }



    private static List<Float> readFile(String fileName) {
        List<Float> list = new ArrayList<>();
        try {
            File obj = new File(fileName);
            Scanner reader = new Scanner(obj);
            while (reader.hasNextDouble()) {
                list.add(reader.nextFloat());
            }
            reader.close();
            return list;
        } catch (Exception e) {
            System.out.println("i <3 hanni: " + e);
            return null;
        }
    }

    private static void printCoords(float[] xs, float[] ys) {
        for (int i = 0; i < xs.length; i++) {
            System.out.print("(" + xs[i] + ", " + ys[i] + ") , ");
        }
        System.out.println("\n");
    }
}

