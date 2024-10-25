package DI;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Estimation {
    
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        List<Float> list = readFile("func.pol");
        float[] test = new float[list.size()];
        for (int i = 0; i < test.length; i++) {
            test[i] = list.get(i);
        }

        System.out.println("type 'derivation' or 'integration': ");
        while (scnr.hasNext()) {
            String ans = scnr.nextLine();
            if (ans.equals("q")) {

                System.out.println("You have successfully quit the program.");
                System.exit(0);

            } else if (ans.equals("d")) {

                System.out.println("Enter float to evaluate at (x) : ");
                float x = scnr.nextFloat();

                System.out.println("Enter h value (h) (anything smaller than .0001f may cause float bit issues that cause imprecision): ");
                float h = scnr.nextFloat();

                System.out.println("Enter iterations (n) : ");
                int n = scnr.nextInt();

                float[][] d = derivate(test, x, h, n);

                displayMatrix(d);

            } else if (ans.equals("i")) {

                System.out.println("Enter first float (a) : ");
                float a = scnr.nextFloat();

                System.out.println("Enter second float (b) : ");
                float b = scnr.nextFloat();

                System.out.println("Enter iterations (n) : ");
                int n = scnr.nextInt();
                
                float[][] r = integrate(test, a, b, n);

                displayMatrix(r);

            } else {
                System.out.println("Not a valid input");
            }
            System.out.println("type 'derivation' or 'integration': ");
        }
        scnr.close();
    }

    public static float[][] integrate(float[] func, float a, float b, int n) {
        float[][] r = new float[n][n];
        
        float h = b - a;
        r[0][0] = (h / 2) * (solveFunc(func, a) + solveFunc(func, b));
        for (int i = 1; i < r.length; i++) {
            h /= 2;
            float sum = 0;
            for (int k = 1; k < Math.pow(2, i); k += 2) {
                sum += solveFunc(func, a + (k * h));
            }
            r[i][0] = (r[i-1][0] / 2) + (sum * h);
            for (int j = 1; j < i; j++) {
                r[i][j] = r[i][j-1] + ( (r[i][j-1] - r[i-1][j-1]) / (doPower(4, j) - 1) ); 
            }
        }

        return r;
    }

    public static float[][] derivate(float[] func, float x, float h, int n) {
        int i, j;
        float[][] D = new float[n][n];

        for (i = 0; i < n; i++) {
            D[i][0] = ( solveFunc(func, x + h) - solveFunc(func, x - h) ) / (2 * h);
            for (j = 1; j < i; j++) {
                D[i][j] = D[i][j-1] + ( (D[i][j-1] - D[i-1][j-1]) / (doPower(4, j) - 1) );
            }
            h /= 2;
        }

        return D;
    }

    private static float solveFunc(float[] func, float x) {
        float res = 0;
        for (int i = 0; i < func.length; i++) {
            res += func[i] * (doPower(x, func.length - i - 1));
        }
        return res;
    }

    private static float doPower(float x, float exp) {
        if (exp == 0) { return 1;}

        float res = x;
        while (exp > 1) {
            res *= x;
            exp--;
        }
        return res;
    }

    private static void displayMatrix(float[][] deezNuts) {
        float zero = 0f;
        for (int i = 0; i < deezNuts.length; i++) {
            for (int j = 0; j < deezNuts[i].length; j++) {
                if (deezNuts[i][j] != zero)
                    System.out.print(deezNuts[i][j] + " ");
            }
            System.out.println();
        }
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
}
