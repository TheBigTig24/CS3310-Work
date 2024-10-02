package Zeroes;


import java.io.File;
import java.util.Scanner;
import Zeroes.Bisection;

public class ZeroesMain {
    
    public static void main(String[] args) {

        float[] polynomials = readFloatFile(0);
        for (float f : polynomials) {
            System.out.print(f + " ");
        }

        Bisection bi = new Bisection();
        System.out.println("\n\nur mom is gay: " + bi.doFunction(polynomials, 1));
    }


    private static float[] readFloatFile(int power) {
        try {
            File obj = new File("func1.pol");
            Scanner reader = new Scanner(obj);
            if (reader.hasNextInt()) {
                power = reader.nextInt() + 1;
                reader.nextLine();
            }
            float[] arr = new float[power];
            for (int i = 0; i < power; i++) {
                if (reader.hasNextFloat()) {
                    arr[i] = reader.nextFloat();
                }
            }
            reader.close();
            return arr;
        } catch (Exception e) {
            System.out.println("i <3 hanni: " + e);
            return null;
        }
    }
}
