package Interpolation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Interpol {
    
    public static void main(String[] args) {

        // read in from file
        List<Float> coords = readFile("points.lin");
        float[] xs = new float[coords.size() / 2];
        float[] ys = new float[coords.size() / 2];
        for (int i = 0; i < coords.size() - (coords.size()/2); i++) {
            xs[i] = coords.get(i);
            ys[i] = coords.get(i + (coords.size()/2));
        }
        printCoords(xs, ys);

        System.out.println("If thou could register a value, it would be most valued: ");
        Scanner scnr = new Scanner(System.in);
        float valued = scnr.nextFloat();

        // get them mf coefficients
        float[] cs = new float[xs.length];
        cs = getCoeffs(xs, ys, cs);

        for (double d : cs) {
            System.out.print(d + " ");
        }

        // do da nootin 
        float z = 0;
        z = performNewton(xs, cs, z);
    }

    private static float performNewton(float[] xs, float[] cs, float z) {

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
        System.out.println();
    }
}

