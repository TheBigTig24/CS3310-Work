package Zeroes;


import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class ZMain {

    private int iterations;
    private String outcome;
    
    public static void main(String[] args) {

        ZMain obj = new ZMain();

        // COMMAND LINE MUST BE IN THIS FORM
        // java ZMain.java [--newt, --sec, --hybrid] [--maxIter n] initP [initP2] polyFileName
        if (args.length == 0 || args.length == 1) {
            System.out.println("You literally don't have any arguments.");
        } else if (args.length == 2) {

            // args[0] = initP , args[1] = filename GOOD
            // bisection, default values
            float initP = Float.parseFloat(args[0]);
            String fileName = args[1];
            float[] polynomials = readFloatFile(0, fileName);
            float result = obj.performBisection(obj, polynomials, initP, initP+100, 10000, 1e-23f);
            writeFloatFile(result, obj.getIter(), obj.getOutcome());

        } else if (!args[0].equals("--newt") && !args[0].equals("--sec") && !args[0].equals("--hybrid") && !args[0].equals("--maxIter") && args.length == 2) {
            
            // args[0] = initP , args[1] = filename GOOD
            // java ZMain.java initP fileName
            float initP = Float.parseFloat(args[0]);
            String fileName = args[1];
            float[] polynomials = readFloatFile(0, fileName);
            float result = obj.performBisection(obj, polynomials, initP, initP+100, 10000, 1e-23f);
            writeFloatFile(result, obj.getIter(), obj.getOutcome());

        } else if (!args[0].equals("--newt") && !args[0].equals("--sec") && !args[0].equals("--hybrid") && !args[0].equals("--maxIter") && args.length == 3) {
            
            // args[0] = initP, args[1] = initP2 , args[2] = filename GOOD
            // java ZMain.java initP initP2 fileName
            float initP = Float.parseFloat(args[0]);
            float initP2 = Float.parseFloat(args[1]);
            String fileName = args[2];
            float[] polynomials = readFloatFile(0, fileName);
            float result = obj.performBisection(obj, polynomials, initP, initP2, 10000, 1e-23f);
            writeFloatFile(result, obj.getIter(), obj.getOutcome());

        } else if (!args[0].equals("--newt") && !args[0].equals("--sec") && !args[0].equals("--hybrid") && args[0].equals("--maxIter") && args.length == 4) {
            
            // args[1] = maxIter , args[2] = initP , args[3] = filename GOOD
            // java ZMain.java --maxIter n initP fileName
            int maxIter = Integer.parseInt(args[1]);
            float initP = Float.parseFloat(args[2]);
            String fileName = args[3];
            float[] polynomials = readFloatFile(0, fileName);
            float result = obj.performBisection(obj, polynomials, initP, initP+100, maxIter, 1e-23f);
            writeFloatFile(result, obj.getIter(), obj.getOutcome());

        } else if (!args[0].equals("--newt") && !args[0].equals("--sec") && !args[0].equals("--hybrid") && args[0].equals("--maxIter") && args.length == 5) {
            
            // args[1] = maxIter , args[2] = initP , args[3] = initP2 , args[4] = filename GOOD
            // java ZMain.java --maxIter n initP initP2 fileName
            int maxIter = Integer.parseInt(args[1]);
            float initP = Float.parseFloat(args[2]);
            float initP2 = Float.parseFloat(args[3]);
            String fileName = args[4];
            float[] polynomials = readFloatFile(0, fileName);
            float result = obj.performBisection(obj, polynomials, initP, initP2, maxIter, 1e-23f);
            writeFloatFile(result, obj.getIter(), obj.getOutcome());

        } else if (args[0].equals("--newt") && args.length == 3) {
            
            // do newtons , args[1] = initP , args[2] = filename GOOD
            // java ZMain.java --newt initP fileName
            float initP = Float.parseFloat(args[1]);
            String fileName = args[2];
            float[] polynomials = readFloatFile(0, fileName);
            float result = obj.performNewton(obj, polynomials, initP, 10000, .000001f, 1e-23f);
            writeFloatFile(result, obj.getIter(), obj.getOutcome());

        } else if (args[0].equals("--sec") && args.length == 3) {
            
            // do secant , args[1] = initP , args[2] = filename GOOD
            // java ZMain.java --sec initP fileName
            float initP = Float.parseFloat(args[1]);
            String fileName = args[2];
            float[] polynomials = readFloatFile(0, fileName);
            float result = obj.performSecant(obj, polynomials, initP, initP+1, 10000, 1e-23f);
            writeFloatFile(result, obj.getIter(), obj.getOutcome());

        } else if (args[0].equals("--sec") && args.length == 4) {

            float initP = Float.parseFloat(args[1]);
            float initP2 = Float.parseFloat(args[2]);
            String fileName = args[3];
            float[] polynomials = readFloatFile(0, fileName);
            float result = obj.performSecant(obj, polynomials, initP, initP2, 10000, 1e-23f);
            writeFloatFile(result, obj.getIter(), obj.getOutcome());

        } else if (args[0].equals("--hybrid") && args.length == 3) {
            
            // do hybrid , args[1] = initP , args[2] = filename  GOOD 
            // java ZMain.java --hybrid initP fileName
            float initP = Float.parseFloat(args[1]);
            String fileName = args[2];
            float[] polynomials = readFloatFile(0, fileName);
            float result = obj.performHybrid(obj, polynomials, initP, initP+100, 10000, 1e-23f, .000001f);
            writeFloatFile(result, obj.getIter(), obj.getOutcome());

        } else if (args[0].equals("--hybrid") && args.length == 4 ) {

            // hybrid with 2 initials GOOD
            // java ZMain.java --hybrid initP initP2 fileName
            float initP = Float.parseFloat(args[1]);
            float initP2 = Float.parseFloat(args[2]);
            String fileName = args[3];
            float[] polynomials = readFloatFile(0, fileName);
            float result = obj.performHybrid(obj, polynomials, initP, initP2, 10000, 1e-23f, .000001f);
            writeFloatFile(result, obj.getIter(), obj.getOutcome());

        } else if ( (args[0].equals("--newt") || !args[0].equals("--sec") || !args[0].equals("--hybrid")) && args[1].equals("--maxIter") && args.length == 5) {
            
            // do args[0] , args[2] = maxIter , args[3] = initP , args[4] = filename GOOD
            // java ZMain.java [--newt --sec --hybrid] --maxIter n initP fileName
            int maxIter = Integer.parseInt(args[2]);
            float initP = Float.parseFloat(args[3]);
            String fileName = args[4];
            float[] polynomials = readFloatFile(0, fileName);
            float result = 0;
            String functionType = args[0];
            if (functionType.equals("--newt")) {
                result = obj.performNewton(obj, polynomials, initP, maxIter, 1e-23f, .000001f);
            } else if (functionType.equals("--sec")) {
                result = obj.performSecant(obj, polynomials, initP, initP+100, maxIter, 1e-23f);
            } else {
                result = obj.performHybrid(obj, polynomials, initP, initP+100, maxIter, 1e-23f, .000001f);
            }
            writeFloatFile(result, obj.getIter(), obj.getOutcome());

        } else if ( (args[0].equals("--newt") || !args[0].equals("--sec") || !args[0].equals("--hybrid")) && args[1].equals("--maxIter") && args.length == 6 ) {
            
            // do args[0] , args[2] = maxIter , args[3] = initP , args[4] = initP2 , args[5] = filename GOOD
            // java ZMain.java [--newt --sec --hybrid] --maxIter n initP initP2 fileName
            int maxIter = Integer.parseInt(args[2]);
            float initP = Float.parseFloat(args[3]);
            float initP2 = Float.parseFloat(args[4]);
            String fileName = args[5];
            float[] polynomials = readFloatFile(0, fileName);
            float result = 0;
            String functionType = args[0];
            if (functionType.equals("--newt")) {
                result = obj.performNewton(obj, polynomials, initP, maxIter, 1e-23f, .000001f);
            } else if (functionType.equals("--sec")) {
                result = obj.performSecant(obj, polynomials, initP, initP2, maxIter, 1e-23f);
            } else {
                result = obj.performHybrid(obj, polynomials, initP, initP2, maxIter, 1e-23f, .000001f);
            }
            writeFloatFile(result, obj.getIter(), obj.getOutcome());

        } else {
            // Some other logic edge case i must've missed cuz its like 3 am
            System.out.println("Something went wrong.");
        }

    }

    // BISECTION FUNCTION
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
            obj.setIter(i+1);
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
        obj.setOutcome("Fail");
        return c;
    }

    // NEWTON'S FUNCTION
    private float performNewton(ZMain obj, float[] func, float x, int maxIter, float eps, float delta) {
        float fx = doFunction(func, x);

        for (int i = 0; i < maxIter; i++) {
            obj.setIter(i+1);
            float fd = doDerivative(func, x);

            if (Math.abs(fd) < delta) {
                // print(small slope)
                obj.setOutcome("Success");
                return x;
            }

            float d = fx / fd;
            x -= d;
            fx = doFunction(func, x);

            if (Math.abs(d) < eps) {
                // print(alg has converged after i+1 iterations)
                obj.setOutcome("Success");
                return x;
            }
        }
        // print(Max Iterations Reached)
        obj.setOutcome("Fail");
        return x;
    }

    // SECANT FUNCTION
    private float performSecant(ZMain obj, float[] func, float a, float b, int maxIter, float eps) {
        float fa = doFunction(func, a);
        float fb = doFunction(func, b);

        if ( Math.abs(fa) > Math.abs(fb) ) {
            float temp = fa;
            fa = fb;
            fb = temp;
            temp = a;
            a = b;
            b = temp;
        }

        for (int i = 0; i < maxIter; i++) {
            obj.setIter(i+1);

            if ( Math.abs(fa) > Math.abs(fb) ) {
                float temp = fa;
                fa = fb;
                fb = temp;
                temp = a;
                a = b;
                b = temp;
            }

            float d = (b - a) / (fb - fa);
            b = a;
            fb = fa;
            d = d * fa;

            if (Math.abs(d) < eps) {
                // print(alg has converged after i+1 iterations)
                obj.setOutcome("Success");
                return a;
            }

            a = a - d;
            fa = doFunction(func, a);
        }
        // print(max number of iterations reached)
        obj.setOutcome("Fail");
        return a;
    }

    // HYBRID (BISECTION THEN NEWTON)
    private float performHybrid(ZMain obj, float[] func, float a, float b, int maxIter, float eps, float delta) {
        float part1 = obj.performBisection(obj, func, a, b, maxIter, .001f);
        int holdIter = obj.getIter();
        float result = obj.performNewton(obj, func, part1, maxIter, eps, delta);
        obj.setIter(obj.getIter() + holdIter);
        return result;
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

    private static float doDerivative(float[] func, float x) {
        float result = 0;
        for (int i = 0; i < func.length - 1; i++) {
            result += (func[i] * (func.length - i - 1) ) * doPower(x, func.length - i - 2);
        }
        return result;
    }

    private static float doPower(float x, int exp) {
        float result = x;
        if (exp == 0) {
            return 1;
        }
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

    private static float[] readFloatFile(int power, String fileName) {
        try {
            File obj = new File(fileName);
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

    private static void writeFloatFile(float answer, int iter, String outcome) {
        try {
            FileWriter writer = new FileWriter("sys1.sol", false);
            writer.write(String.valueOf(answer) + " " + String.valueOf(iter) + " " + outcome);
            writer.close();
        } catch(Exception e) {
            System.out.println("WOMP WOMP: " + e);
        }
    }

}
