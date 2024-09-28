import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Program2 {
    
    public static void main(String[] args) {

        Set<Integer> set = generateRandom();
        printSet(set);
        Random random = new Random();
        int k = random.nextInt(set.size());
        System.out.println("\nK: " + k);
        System.out.println("\nANSWER: " + (select(k, set)));
        
        int[] arr = new int[set.size()];
        int index = 0;
        for (int num : set) {
            arr[index] = num;
            index++;
        }
        Arrays.sort(arr);
        printArr(arr);
    }

    private static int select(int k, Set<Integer> set) {
        if (set.size() == 1) {
            for (int num : set) {
                return num;
            }
            return -69;
        } else {
            int half = set.size() / 2;
            int item = 0;
            for (int num : set) {
                if (half == 1) {
                    item = num;
                }
                half--;
            }
            Set<Integer> s1 = new HashSet<>();
            Set<Integer> s3 = new HashSet<>();
            for (int num : set) {
                if (num < item) {
                    s1.add(num);
                } else if (num > item) {
                    s3.add(num);
                }
            }
            if (s1.size() >= k) {
                return select(k, s1);
            } else {
                if (s1.size() + 1 >= k) {
                    return item;
                } else {
                    return select(k - s1.size() - 1, s3);
                }
            }
        }
    }



    private static Set<Integer> generateRandom() {
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        int iterations = random.nextInt(200) + 25;
        System.out.println("\n\nITERATIONS OF GENERATION: " + iterations + "\n");

        for (int i = 0; i < iterations; i++) {
           set.add(random.nextInt(200));
        }
        return set;
    }

    private static void printSet(Set<Integer> set) {
        for (int num : set) {
            System.out.print(num + " ");
        }
    }

    private static void printArr(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
