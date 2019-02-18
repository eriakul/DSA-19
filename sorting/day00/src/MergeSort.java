import java.util.Arrays;

public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * TODO
     * Best-case runtime:
     * Worst-case runtime:
     * Average-case runtime:
     *
     * Space-complexity:
     */
    @Override
    public int[] sort(int[] array) {

        if (array.length <= 1){
            return array;
        }
        int [] left = Arrays.copyOfRange(array, 0, array.length/2 + array.length%2);
        int [] right = Arrays.copyOfRange(array,array.length/2 + array.length%2, array.length);

        int [] sorted_left = sort(left);

        int [] sorted_right = sort(right);

        return merge(sorted_left, sorted_right);
    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     */
    public int[] merge(int[] a, int[] b) {
        int pa = 0;
        int pb =0;
        int [] temp_array = new int[a.length + b.length];
        int temp_array_size = 0;
        while (temp_array_size < a.length + b.length){
            if (pa == a.length){
                temp_array[temp_array_size] = b[pb];
                pb++;
                temp_array_size++;
            }
            else if (pb == b.length){
                temp_array[temp_array_size] = a[pa];
                pa++;
                temp_array_size++;
            }
            else if(a[pa] <= b[pb]){
                temp_array[temp_array_size] = a[pa];
                pa++;
                temp_array_size++;
            }
            else{
                temp_array[temp_array_size] = b[pb];
                pb++;
                temp_array_size++;
            }
        }

        return temp_array;
    }

}
