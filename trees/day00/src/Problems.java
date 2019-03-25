import java.util.Collections;
import java.util.List;

public class Problems {

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        Collections.sort(values);
        System.out.println("Values: "+ values);
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        return minimalHeightHelper(0, values.size()-1, values, tree);


    }

    static BinarySearchTree<Integer> minimalHeightHelper(int start_index, int end_index, List<Integer> sorted_array, BinarySearchTree<Integer> tree){
        System.out.println("Start: " + start_index + "  Stop: "+ end_index);
        System.out.println(tree.inOrderTraversal());
        if (end_index <= start_index){
            tree.add(sorted_array.get(start_index));
            return tree;
        }
        int center_index = (end_index+start_index)/2;
        tree.add(sorted_array.get(center_index));
        tree = minimalHeightHelper(start_index, center_index-1, sorted_array, tree);
        return minimalHeightHelper(center_index+1, end_index, sorted_array, tree);




    }

    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {
        // TODO
        return false;
    }
}
