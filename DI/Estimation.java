package DI;

import java.util.Scanner;

public class Estimation {
    
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);


        System.out.println("type 'derivation' or 'integration': ");
        while (scnr.hasNext()) {
            String ans = scnr.nextLine();
            if (ans.equals("q")) {
                System.out.println("You have successfully quit the program.");
                System.exit(0);
            } else if (ans.equals("derivation")) {

                float[] test = {1,-3,5};
                float x = 3.5467f;
                float h = .0001f;
                float[][] d = new float[5][5];

                d = derivate(test, x, h, d, 0);

                for (int i = 0; i < d.length; i++) {
                    for (int j = 0; j < d[i].length; j++) {
                        System.out.print(d[i][j] + " ");
                    }
                System.out.println();
                }

            } else if (ans.equals("integration")) {

            } else {
                System.out.println("Not a valid input");
            }
            System.out.println("type 'derivation' or 'integration': ");
        }
        scnr.close();
    }

    public static float integrate(float[] func, float a, float b, float[][] r) {
        
        float h = b - a;
        r[0][0] = (h / 2) * (solveFunc(func, a) + solveFunc(func, b));
        for (int i = 0; i < r.length; i++) {
            h /= 2;
            float sum = 0;
            for (int k = 0; k < Math.pow(2, i); k += 2) {
                sum += solveFunc(func, a + (k * h));
            }
            r[i][0] = (r[i-1][0] / 2) + (sum * h);
            for (int j = 0; j < i; j++) {
                r[i][j] = r[i][j-1] + ( (r[i][j-1] - r[i-1][j-1]) / (doPower(4, j) - 1) ); 
            }
        }

        return r[r.length - 1][r.length - 1];
    }

    public static float[][] derivate(float[] func, float x, float h, float[][] D, int iter) {
        int i, j;

        for (i = 0; i < D.length; i++) {
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
}
