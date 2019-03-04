import java.util.LinkedList;

public class Problems {

    static void sortNumsBetween100s(int[] A) {
        for (int i = 0; i < A.length; i++){
            A[i] = A[i] + 100;
        }
        CountingSort.countingSort(A);
        for (int i = 0; i < A.length; i++){
            A[i] = A[i] - 100;
        }
    }

    /**
     * @param n the character number, 0 is the rightmost character
     * @return
     */
    private static int getNthCharacter(String s, int n) {
        return s.charAt(s.length() - 1 - n) - 'a';
    }


    /**
     * Use counting sort to sort the String array according to a character
     *
     * @param n The digit number (where 0 is the least significant digit)
     *
     *
     *
     */
    static int findMax(String[] A, int n){
        int max =getNthCharacter(A[0], n);
        for (int i = 0; i < A.length; i++){
            if (getNthCharacter(A[i], n) > max){
                max = getNthCharacter(A[i], n);
            }
        }
        return max;
    }
    static void countingSortByCharacter(String[] A, int n) {

        int max = findMax(A,n);
        LinkedList<String>[] L = new LinkedList[max+1];
        for (int i = 0; i < max+1; i++)
            L[i] = new LinkedList<>();

        for (String string : A) {
            int temp = getNthCharacter(string, n);
            L[temp].add(string);
        }
        int j = 0;
        for (LinkedList list : L) {
            for (Object entry: list){
                A[j] = (String) entry;
                j++;
            }
        }
    }

    /**
     * @param stringLength The length of each of the strings in S
     */
    static void sortStrings(String[] S, int stringLength) {

        for (int j = 0; j < stringLength; j++){
            countingSortByCharacter(S, j);
        }
    }

    /**
     * @param A The array to count swaps in
     */

    public static int countSwaps(int[] A) {
        // TODO
        return 0;
    }

}
