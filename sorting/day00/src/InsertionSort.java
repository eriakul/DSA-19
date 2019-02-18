
public class InsertionSort extends SortAlgorithm {
    /**
     * Use the insertion sort algorithm to sort the array
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
        if (array.length <=1){
            return array;
        }
        int scan_pointer = 0;
        int index_pointer = 1;

        while (index_pointer < array.length){
            while (scan_pointer > 0){
                if (array[scan_pointer] < array[scan_pointer-1]){
                    array = swappy(array, scan_pointer, scan_pointer-1);
                    scan_pointer--;
                }
                else{
                    break;
                }
            }
            index_pointer++;
            scan_pointer = index_pointer;
        }
        return array;
    }

    public int[] swappy(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        return  array;
    }

}
