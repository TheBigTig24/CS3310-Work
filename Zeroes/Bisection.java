package Zeroes;

public class Bisection {
    
    private int iterations;
    private String outcome;

    public Bisection() {
        this.iterations = 0;
        this.outcome = "Fail";
    }

    public float doFunction(float[] func, float x) {
        float result = 0;
        for (int i = 0; i < func.length; i++) {
            result += func[i] * doPower(x, func.length - i);
        }
        return result;
    }

    public static float doPower(float x, int exp) {
        float result = x;
        while (exp != 0) {
            result *= x;
        }
        return result;
    }

    public float performBisection() {
        return 1;
    }
}
