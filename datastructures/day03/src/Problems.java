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


    public static List<Integer> removeKDigits(int[] A, int k) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i : A)
        {
            list.add(i);
        }
        list = removeKDigitsHelper(list,0, k);
        return list;
    }

    public static boolean isPalindromeHelper(Node node, boolean forward, String string, Node last_node) {
        if (node == null){
            return true;
        }

        if (node.next == null && !forward){
            return true;
        }
        if (node.next == null && forward){
            string = string + node.val;
            if (Integer.parseInt(string.substring(0,1)) != node.val){
                return false;
            }
            else {
                return isPalindromeHelper(last_node, false, string.substring(1), null);
            }
        }
        if (forward){
            Node next_node = node.next;
            node.next = last_node;
            if (last_node == null){
            node.next = null;
            }
            return isPalindromeHelper(next_node, forward, string + node.val, node);
        }
        else{
            if (string == ""){
                return true;
            }
            if (Integer.parseInt(string.substring(0,1)) != node.val){
                return false;
            }
            else {
                return isPalindromeHelper(node.next, forward, string.substring(1), null);
            }
        }
    }



    public static boolean isPalindrome(Node n) {
        return isPalindromeHelper(n, true, "", null);
    }

    public static String infixToPostfixHelper(int pf, int pe, String string) {
        pf += 2;
        pe -= 2;
        if (string.charAt(pf) == '(' || string.charAt(pf) == ')'){
            char num = string.charAt(pe );
            char op = string.charAt(pe-2);
            return infixToPostfixHelper(pf, pe-4, string) + String.format(" %c %c", num, op);
        }
        else if (string.charAt(pe) == '(' || string.charAt(pe) == ')'){
            char num = string.charAt(pf);
            char op = string.charAt(pf+2);
            return String.format("%c %s %c", num, infixToPostfixHelper(pf+4, pe, string), op);
        }
        else{
            char num1 = string.charAt(pf);
            char op = string.charAt(pf+2);
            char num2 = string.charAt(pf+4);
            return String.format("%c %c %c", num1, num2, op);
        }
    }

    public static String infixToPostfix(String s) {

        return infixToPostfixHelper(0, s.length()-1, s);
    }

}
