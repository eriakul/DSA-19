import java.util.*;

public class Problems {

    public static class Node {
        int val;
        Node next;

        Node(int d) {
            this.val = d;
            next = null;
        }
    }
    public static List<Integer> removeKDigitsHelper(List list, int index, int k) {
        if (k == 0){
            return list;
        }
        if (index + k <= list.size() - 1){

            int min_index = index;
            for (int i = 1; i <= k; i++){
                if ((int) list.get(index +i) < (int) list.get(min_index)){
                    min_index = index + i;
                }
            }
            if (min_index == index){
                return removeKDigitsHelper(list, index+1,k);
            }
            else{
                for (int i = index; i<min_index; i++){
                    list.remove(index);
                    k--;
                }
                return removeKDigitsHelper(list, index, k);
            }
        }
        else{
            return list.subList(0, index);
        }

    }
//    public static List<Integer> removeKDigitsHelper(List list, int index, int k) {
//        if (k == 0){
//            return list;
//        }
//        if (index + k <= list.size() - 1){
//            if ((int) list.get(index) <= (int) list.get(index+k)){
//                return removeKDigitsHelper(list, index + 1, k);
//            }
//            else if ((int) list.get(index) > (int) list.get(index+k)){
//                list.remove(index);
//                return removeKDigitsHelper(list, index, k-1);
//            }
//            else{
//                System.out.println("Weird case encountered.");
//                return list;
//            }
//        }
//        else{
//            return list.subList(0, index);
//        }
//
//    }

//    public static List<Integer> removeKDigitsHelper(List list, int k) {
//        if (k == 0){
//            return list;
//        }
//        if ((int) list.get(0) >= (int) list.get(k)){
//            list.remove(0);
//            return removeKDigitsHelper(list, k - 1);
//        }
//        else if ((int) list.get(0) < (int) list.get(k)){
//            list.remove(k);
//            return removeKDigitsHelper(list, k-1);
//        }
//        else{
//            System.out.println("Weird case encountered.");
//            return list;
//        }
//
//    }




    public static List<Integer> removeKDigits(int[] A, int k) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i : A)
        {
            list.add(i);
        }
        list = removeKDigitsHelper(list,0, k);
        return list;
    }

    public static boolean isPalindrome(Node n) {
        // TODO: your code here
        return false;
    }

    public static String infixToPostfix(String s) {
        // TODO
        return null;
    }

}
