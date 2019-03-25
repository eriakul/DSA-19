package divide_and_conquer;

import java.util.Arrays;

public class PeakFinding {
    static Boolean XAXIS = true;
    static Boolean YAXIS = false;
    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakOneD(int i, int[] nums) {
        if (i > 0 && nums[i] < nums[i - 1])
            return -1;
        if (i < nums.length - 1 && nums[i] < nums[i + 1])
            return 1;
        return 0;
    }

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakX(int x, int y, int[][] nums) {
        if (x > 0 && nums[y][x] < nums[y][x - 1])
            return -1;
        if (x < nums[0].length - 1 && nums[y][x] < nums[y][x + 1])
            return 1;
        return 0;
    }

    // Return -1 if up is higher, 1 if down is higher, 0 if peak
    private static int peakY(int x, int y, int[][] nums) {
        if (y > 0 && nums[y][x] < nums[y - 1][x])
            return -1;
        if (y < nums.length - 1 && nums[y][x] < nums[y + 1][x])
            return 1;
        return 0;
    }

    // These two functions return the index of the highest value along the X or Y axis, with the given
    // value for the other axis. Searches between hi (exclusive) and lo (inclusive)
    private static int maxXIndex(int y, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int x = lo; x < hi; x++) {
            if (maxIndex == -1 || nums[y][x] > nums[y][maxIndex])
                maxIndex = x;
        }
        return maxIndex;
    }

    private static int maxYIndex(int x, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int y = lo; y < hi; y++) {
            if (maxIndex == -1 || nums[y][x] > nums[maxIndex][x])
                maxIndex = y;
        }
        return maxIndex;
    }


    public static int findOneDPeak(int[] nums) {
        int peak = findOneDPeak(nums, 0, nums.length - 1);
        return peak;
    }

    public static int findOneDPeak(int[] nums, int begin,  int end) {
        if (begin == end){
            return begin;
        }

        int middle = (begin+end)/2;

        int side = peakOneD(middle, nums);

        if (side == 0){
            return middle;
        }
        else if (side == -1){
            return findOneDPeak(nums, begin, middle -1);
        }
        else if (side == 1){
            return findOneDPeak(nums, middle + 1, end);
        }

        return 0;
    }

    public static int[] findTwoDPeak(int[][] nums) {
        int[] peak = findTwoDPeak(nums, 0, nums.length, 0, nums[0].length, XAXIS);
        System.out.println(Arrays.toString(peak));
        return peak;
    }

    public static int[] findTwoDPeak(int[][] nums, int x1, int x2, int y1, int y2, Boolean readjustingAxis) {
        System.out.println(Arrays.toString(new int[] {x1, x2, y1, y2}));
        if (x1==x2){
            return new int[] {x1, maxYIndex(x1, y1, y2, nums)};
        }
        else if (y1 == y2){
            return new int[] {maxXIndex(y1, x1, x2,nums), y1};
        }


        if (readjustingAxis == XAXIS){
            int max_index = maxXIndex(y1, x1, x2, nums);

            int peak = peakY(max_index, y1, nums);

            if (peak == 0){
                System.out.println("HERE");
                return new int[] {y1, max_index};
            }

            else if ( max_index < (x1+x2)/2){
                return findTwoDPeak(nums, x1, (x1+x2)/2 -1, y1, y2, YAXIS);
            }
            else if ( max_index > (x1+x2)/2){
                return findTwoDPeak(nums, (x1+x2)/2 +1, x2, y1, y2, YAXIS);
            }
            else {
                return findTwoDPeak(nums, (x1+x2)/2, x2, y1, y2, YAXIS);
            }
        }
        else{
            int max_index = maxYIndex(x1,y1,y2, nums);

            int peak = peakX(x1, max_index, nums);

            if (peak == 0){
                System.out.println("HERE 2");
                return new int[] {x1, max_index};
            }
            else if (max_index < (y1+y2)/2){
                return findTwoDPeak(nums, x1, x2, y1, (y1+y2)/2 - 1, XAXIS);
            }
            else if (max_index > (y1+y2)/2){
                return findTwoDPeak(nums, x1, x2, (y1+y2)/2 + 1, y2, XAXIS);
            }
            else {
                return findTwoDPeak(nums, x1, x2, (y1+y2)/2, y2, XAXIS);
            }
        }
    }

}
