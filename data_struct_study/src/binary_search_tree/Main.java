package binary_search_tree;

import java.util.ArrayList;
import java.util.Random;


public class Main {

    public static void main(String[] args) {
//        int[] arr = {5, 3, 8, 2, 9, 7};
//        BST<Integer> bst = new BST<>();
//        for (int value : arr) {
//            bst.add(value);
//        }
//
//        bst.levelOrder();

//        bst.preOrder();
//
//        System.out.println();
//
//        bst.preOrderNR();

//        bst.inOrder();
//
//        System.out.println();
//
//        bst.postOrder();

//        System.out.println();
//        System.out.println(bst);

        BST<Integer> bst = new BST<>();
        Random random = new Random();

        int n = 1000;

        // test removeMin
        for(int i = 0 ; i < n ; i ++)
            bst.add(random.nextInt(10000));

        ArrayList<Integer> nums = new ArrayList<>();
        while(!bst.isEmpty())
            nums.add(bst.removeMinValue());

        System.out.println(nums);
        for(int i = 1 ; i < nums.size() ; i ++)
            if(nums.get(i - 1) > nums.get(i))
                throw new IllegalArgumentException("Error!");
        System.out.println("removeMin test completed.");


        // test removeMax
        for(int i = 0 ; i < n ; i ++)
            bst.add(random.nextInt(10000));

        nums = new ArrayList<>();
        while(!bst.isEmpty())
            nums.add(bst.removeMaxValue());

        System.out.println(nums);
        for(int i = 1 ; i < nums.size() ; i ++)
            if(nums.get(i - 1) < nums.get(i))
                throw new IllegalArgumentException("Error!");
        System.out.println("removeMax test completed.");
    }
}
