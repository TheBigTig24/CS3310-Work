package Zeroes;


import java.io.File;
import java.util.Scanner;

public class ZMain {

    private int iterations;
    private String outcome;
    
    public static void main(String[] args) {

        float[] polynomials = readFloatFile(0);
        for (float f : polynomials) {
            System.out.print(f + " ");
        }
        System.out.println();

        ZMain obj = new ZMain();
        float test = obj.performBisection(obj, polynomials, .5f, 2, 100000, .000001f);
        System.out.println("\n\nSKIBIDI GYATTTT OHIO RIZZ TEST: " + test + " ITER: " + obj.getIter() + " OUTCOMIUS: " + obj.getOutcome());
    }

    private float performBisection(ZMain obj, float[] func, float a, float b, int maxIter, float eps) {
        float fa = doFunction(func, a);
        float fb = doFunction(func, b);
        float c = 0;
        if (fa * fb >= 0) {
            obj.setOutcome("Fail");
            return -1;
        }

        float error = b - a;
        
        for (int i = 0; i < maxIter; i++) {
            obj.setIter(i);
            error /= 2;
            c = a + error;
            float fc = doFunction(func, c);
            if ( (Math.abs(error) < eps) || (fc == 0) ) {
                obj.setOutcome("Success");
                return c;
            }

            if (fa * fc < 0) {
                b = c;
            } else {
                a = c;
                fa = fc;
            }
            
        }
        obj.setOutcome("Success");
        return c;
    }




    // METHODS FOR FUN
    private static float doFunction(float[] func, float x) {
        float result = 0;
        for (int i = 0; i < func.length - 1; i++) {
            result += func[i] * doPower(x, func.length - i - 1);
        }
        result += func[func.length - 1];
        return result;
    }

    private static float doPower(float x, int exp) {
        float result = x;
        while (exp != 1) {
            result *= x;
            exp--;
        }
        return result;
    }


    // METHODS TO DO THE DIRTY WORK
    public ZMain() {
        this.iterations = 0;
        this.outcome = "";
    }

    private int getIter() {
        return this.iterations;
    }

    private void setIter(int num) {
        this.iterations = num;
    }

    private String getOutcome() {
        return this.outcome;
    }

    private void setOutcome(String word) {
        this.outcome = word;
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
