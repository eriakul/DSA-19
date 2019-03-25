import java.util.Arrays;

public class Problems {

    public static int leastSum(int[] A) {
        if (A.length == 1){
            return A[0];
        }
        else if (A.length == 0){
            return 0;
        }
        Arrays.radixSort(A);
        System.out.println(Arrays.toString(A));

        StringBuilder solution1 = new StringBuilder();
        StringBuilder solution2 = new StringBuilder();
        for (int i = 0; i<A.length; i++){
            if (i%2 == 0) {
                solution1.append(Character.forDigit(A[i], 10));
            }
            else{
                solution2.append(Character.forDigit(A[i], 10));
            }

        }
        System.out.println(solution1);
        return Integer.parseInt(solution1.toString()) + Integer.parseInt(solution2.toString());
    }

}
