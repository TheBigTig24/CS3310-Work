import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Program2 {
    
    public static void main(String[] args) {

        // Generate and Display Set
        Set<Integer> set = generateRandom();
        printSet(set);

        // Generate K
        Random random = new Random();
        int k = random.nextInt(set.size());

        // Run Procedure 1 Function
        long start = System.nanoTime();
        int answer = select(k, set);
        long end = System.nanoTime();

        // Display Procedure 1 Results 
        System.out.println("\nProcedure 1 Results: ");
        displayResults(k, answer, end - start);

        // Run Procedure 2 Function
        start = System.nanoTime();
        answer = select2(k, set);
        end = System.nanoTime();

        // Display Procedure 2 Function
        System.out.println("\nProcedure 2 Results: ");
        displayResults(k, answer, end - start);
        
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

    private static int select2(int k, Set<Integer> set) {
        if (set.size() < 50) {
            // Sort set and get Kth Largest Element
            List<Integer> list = new ArrayList<>(set);
            Collections.sort(list);
            return list.get(k - 1);
        } else {
            // Convert set to array
            int[] arr = new int[set.size()];
            int index = 0;
            for (int num : set) {
                arr[index] = num;
                index++;
            }

            // Partition Array to Sequences of 5
            List<List<Integer>> superList = new ArrayList<>();
            for (int i = 0; i < arr.length; i += 5) {
                int j = i;
                List<Integer> temp = new ArrayList<>();
                while (j < i+5 && j < arr.length) {
                    temp.add(arr[j]);
                    j++;
                }
                superList.add(temp);
            }

            // Create Set of Medians of Lists
            Set<Integer> medians = new HashSet<>();
            for (List<Integer> miniList : superList) {
                medians.add(findMedian(miniList));
            }

            int m = select2(medians.size() / 2, medians);

            // Partition Elements Smaller and Larger than m into Sets
            Set<Integer> s1 = new HashSet<>();
            Set<Integer> s3 = new HashSet<>();

            for (int num : set) {
                if (num < m) {
                    s1.add(num);
                } else if (num > m) {
                    s3.add(num);
                }
            }

            // Perform Recursion
            if (s1.size() >= k) {
                return select2(k, s1);
            } else {
                if (s1.size() + 1 >= k) {
                    return m;
                } else {
                    return select2(k - s1.size() - 1, s3);
                }
            }

        }
    }

    private static int findMedian(List<Integer> list) {
        Collections.sort(list);
        if (list.size() % 2 == 0) {
            return (list.get(list.size() / 2) + list.get((list.size() / 2) - 1)) / 2;
        } else {
            return list.get(list.size() / 2);
        }
    }




    private static Set<Integer> generateRandom() {
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        int iterations = random.nextInt(1000) + 25;
        System.out.println("\n\nITERATIONS OF GENERATION: " + iterations + "\n");

        for (int i = 0; i < iterations; i++) {
           set.add(random.nextInt(10000));
        }
        return set;
    }

    private static void printSet(Set<Integer> set) {
        for (int num : set) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // private static void printArr(int[] arr) {
    //     for (int num : arr) {
    //         System.out.print(num + " ");
    //     }
    //     System.out.println();
    // }

    private static void displayResults(int k, int answer, long CPUTime) {
        System.out.println("\nK: " + k + "\nANSWER: " + answer + "\nCPU TIME: " + CPUTime);
    }
}
