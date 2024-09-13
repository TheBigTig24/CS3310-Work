import java.util.Random;

public class Program1 {

    public static void main(String[] args) {

        /*  Generates Random Array
         *  100 <= array.length <= 110
         *  Array values range between -100 to 100
         */ 
        int aLength = (int) (Math.random() * 10) + 100;
        int[] randomArr = new int[aLength];
        randomArr = generateValues(randomArr);

        // Prints Length of Randomly Generated Array
        System.out.println("ARRAY LENGTH: " + randomArr.length + "\n");

        // Tests Exhaustive Approach
        long start = System.nanoTime();
        int[] alg1 = maxSubSum1(randomArr);
        long end = System.nanoTime();
        long CPUTime = end - start;
        printInfo(alg1, CPUTime, "Exhaustive");
        
        // Tests Optimized Exhaustive Approach
        start = System.nanoTime();
        int[] alg2 = maxSubSum2(randomArr);
        end = System.nanoTime();
        CPUTime = end - start;
        printInfo(alg2, CPUTime, "Optimized Exhaustive");

        // Tests Recursive Approach
        start = System.nanoTime();
        int[] hanni = wrapperRec(randomArr, 0, randomArr.length - 1, 0);
        end = System.nanoTime();
        CPUTime = end - start;
        printInfo(hanni, CPUTime, "Recursive");

        // Tests Dynamic Programming Approach
        start = System.nanoTime();
        int[] alg4 = maxSubSum4(randomArr);
        end = System.nanoTime();
        CPUTime = end - start;
        printInfo(alg4, CPUTime, "Dynamic Programming");
    }

    // Exhaustive Algorithm
    public static int[] maxSubSum1(int[] arr) {
        int[] results = new int[3];
        results[2] = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int currSum = 0;
                for (int k = i; k < arr.length; k++) {
                    currSum += arr[k];

                    if (currSum > results[2]) {
                        results[2] = currSum;
                        results[1] = k;
                        results[0] = j;
                    }
                }
            }
        }
        
        return results;

    }

    // Optimized Exhaustive Algorithm
    public static int[] maxSubSum2(int[] arr) {
        int[] results = new int[3];
        results[2] = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            int currSum = 0;
            for (int j = i; j < arr.length; j++) {
                currSum += arr[j];
                if (currSum > results[2]) {
                    results[2] = currSum;
                    results[1] = j;
                    results[0] = i;
                }
            }
        }

        return results;
    }

    // Recursive Algorithm
    public static int[] rec(int[] arr, int left, int right, int returnLeft) {
        if (left == right) {
            return new int[] {arr[left], left, right, (arr[left] >= arr[returnLeft]) ? left : returnLeft};
        }
            

        int center = (left + right) / 2;
        int[] maxLeftSum = rec(arr, left, center, returnLeft);
        int[] maxRightSum = rec(arr, center + 1, right, returnLeft);

        int maxLeftBorderSum = Integer.MIN_VALUE, leftBorderSum = 0;
        for (int i = center; i >= left; i--) {
            leftBorderSum += arr[i];
            if (leftBorderSum > maxLeftBorderSum) {
                maxLeftBorderSum = leftBorderSum;
            }
        } 

        int maxRightBorderSum = Integer.MIN_VALUE, rightBorderSum = 0, rightBoundIndex = -1;
        for (int i = center + 1; i <= right; i++) {
            rightBorderSum += arr[i];
            if (rightBorderSum > maxRightBorderSum) {
                maxRightBorderSum = rightBorderSum;
                rightBoundIndex = i;
            }
        } 
        int[] tempArr = {maxLeftBorderSum + maxRightBorderSum, left, right, rightBoundIndex};

        if (maxLeftSum[0] >= maxRightSum[0] && maxLeftSum[0] >= tempArr[0]) {
            return maxLeftSum;
        } else if (maxRightSum[0] >= maxLeftSum[0] && maxRightSum[0] >= tempArr[0]) {
            return maxRightSum;
        } else {
            return tempArr;
        }
    }

    // Dynamic Programming Algorithm
    public static int[] maxSubSum4(int[] arr) {
        int[] results = new int[3];
        int[] maxSum = new int[arr.length];
        maxSum[0] = arr[0];
        results[2] = maxSum[0];
        for (int i = 1; i < arr.length; i++) {
            maxSum[i] = Math.max(maxSum[i-1] + arr[i], arr[i]);
            if (maxSum[i] > results[2]) {
                results[2] = maxSum[i];
                results[1] = i;
            }
        }

        int reverseSum = 0;
        for (int i = results[1]; i >= 0; i--) {
            reverseSum += arr[i];
            if (reverseSum == results[2]) {
                results[0] = i;
                break;
            }
        }

        return results;
    }

    // Wraps Recursive Alg with Backwards Iteration
    public static int[] wrapperRec(int[] arr, int left, int right, int returnLeft) {
        int[] getMaxSubarraySum = rec(arr, left, right, returnLeft);
        return new int[] {iterateBackwards(arr, getMaxSubarraySum[3], getMaxSubarraySum[0]), getMaxSubarraySum[3], getMaxSubarraySum[0]};
    }


    // Helper Method
    public static int iterateBackwards(int[] arr, int startingIndex, int target) {
        int sum = 0;
        for (int i = startingIndex; i >= 0; i--) {
            sum += arr[i];
            if (sum == target) {
                return i;
            }
        }
        return -1;
    }

    // Prints information for each algorithm
    public static void printInfo(int[] results, long CPUTime, String name) {
        System.out.println(name + "\nLeft Boundary Index: " + results[0] + "\nRight Boundary Index: " + results[1] + "\nMax Subarray Sum: " + results[2] + "\nTime Taken: " + CPUTime + "\n");
    }
    
    // Generates random values for an array
    public static int[] generateValues(int[] arr) {
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100 + 100) - 100;
        }
        return arr;
    }

    // Prints array for testing purposes
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}