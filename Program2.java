import java.util.Random;

public class Program2 {
    
    public static void main(String[] args) {
        int[] arr = new int[100];
        arr = generateRandom(arr);
        printArr(arr);
    }



    private static int[] generateRandom(int[] arr) {
        Random random = new Random();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(200 + 100) - 100;
        }
        return arr;
    }

    private static void printArr(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
