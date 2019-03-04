import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class CountingSort {

    /**
     * Use counting sort to sort non-negative integer array A.
     * Runtime: TODO
     *
     * k: maximum element in array A
     */
    static int findMax(int[] A){
        int max = A[0];
        for (int i = 0; i < A.length; i++){
            if (A[i] > max){
                max = A[i];
            }
        }
        return max;
    }

    static void countingSort(int[] A) {


        int max = findMax(A);
        int[] temp = new int[max+1];
        for (int i = 0; i < A.length; i++){
            temp[A[i]] = temp[A[i]]+1;
        }

        int count = 0;
        for (int index = 0; index < temp.length; index++){
            for (int j = 0; j < temp[index]; j++){
                A[count] = index;
                count++;
            }
        }


    }

}
