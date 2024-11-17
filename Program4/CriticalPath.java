package Program4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CriticalPath {
    
    public static void main(String[] args) {

        // create adjacency matrix
        int[][] A = new int[13][13];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                A[i][j] = 0;
            }
        }

        // add predecessors
        A[0][1] = 1;
        A[0][2] = 1;
        A[0][3] = 1;
        A[1][4] = 1;
        A[1][5] = 1;
        A[2][5] = 1;
        A[3][5] = 1;
        A[4][6] = 1;
        A[5][6] = 1;
        A[5][7] = 1;
        A[3][8] = 1;
        A[6][9] = 1;
        A[7][9] = 1;
        A[8][10] = 1;
        A[8][11] = 1;
        A[9][12] = 1;
        A[10][12] = 1;
        A[11][12] = 1;

        // create task duration list
        int[] T = {2,4,5,9,3,2,1,10,11,6,9,8,7};

        //get critical path
        List<Integer> theBlessedPath = findPath(A, T);

        // print critical path with some cool arrows
        for (int i = 0; i < theBlessedPath.size() - 1; i++) {
            System.out.print(theBlessedPath.get(i) + " --> ");
        }
        System.out.println(theBlessedPath.get(theBlessedPath.size() - 1));
        
    }

    private static List<Integer> findPath(int[][] A, int[] T) {
        
        // create earliest start and earliest finish arrays
        int[] ES = new int[A.length];
        int[] EF = new int[A.length];
        EF[0] = T[0];
        ES[0] = 0;
        for (int i = 1; i < A.length; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < A.length; j++) {
                if (A[j][i] == 1)
                    max = Math.max(max, EF[j]);
            }
            ES[i] = max;
            EF[i] = T[i] + ES[i];
        }

        // create latest start and latest finish arrays
        int[] LS = new int[A.length];
        int[] LF = new int[A.length];
        LS[LS.length - 1] = EF[EF.length - 1] - T[T.length - 1];
        LF[LF.length - 1] = EF[EF.length - 1];

        for (int i = A.length - 2; i >= 0; i--) {
            int min = Integer.MAX_VALUE;
            for (int j = A.length - 1; j >= 0; j--) {
                if (A[i][j] == 1) {
                    min = Math.min(min, LS[j]);
                }
            }
            LF[i] = min;
            LS[i] = LF[i] - T[i];
        }

        // calculate slack and add to result list
        List<Integer> daPath = new ArrayList<>();
        for (int i = 0; i < T.length; i++) {
            if (LF[i] - EF[i] == 0) {
                daPath.add(i);
            }
        }
        
        return daPath;
    }

    // print adjacency matrix for testing if needed
    private static void printA(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                System.out.print(A[i][j] + "  ");
            }
            System.out.println();
        }
    }

    // print one of the arrays for testing if needed
    private static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}