import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LargestSubArray {
    static int[] largestSubarray(int[] nums) {
        int count = 0;
        for (int num:nums){
            if (num == 0){
                count--;
            }
            else{
                count++;
            }
        }

        System.out.println("COUNT: " + count);
        int pointerFront = 0;
        int frontScore = 0;
        int endScore = 0;
        int pointerEnd = nums.length -1;
        Map frontMap = new HashMap();
        Map endMap = new HashMap();
        while (pointerEnd>=0){

            if (! frontMap.containsKey(frontScore)){
                frontMap.put(frontScore, pointerFront);
            }
            if (! endMap.containsKey(endScore)){
                endMap.put(endScore, pointerEnd);
            }
            if (frontMap.containsKey(count - endScore) && (int)frontMap.get(count - endScore)<pointerEnd){
                System.out.print((int)frontMap.get(count - endScore));
                System.out.println(pointerEnd);
                System.out.println(frontMap.toString());
                System.out.println(endMap.toString());
                return new int[]{(int)frontMap.get(count - endScore), pointerEnd};
            }
            if (endMap.containsKey(count - frontScore)&& pointerFront<(int)endMap.get(count - frontScore)){
                System.out.println("FOUND");
                System.out.print(pointerFront);
                System.out.println((int)frontMap.get(count - frontScore));
                return new int[]{pointerFront, (int)endMap.get(count - frontScore)};
            }

            if (nums[pointerFront] == 0){
                frontScore--; }
            else{
                frontScore++; }

            pointerFront++;

            if (nums[pointerEnd] == 0){
                endScore--; }
            else{
                endScore++; }
            pointerEnd--;


        }
        return new int[]{0,0};
    }
}
