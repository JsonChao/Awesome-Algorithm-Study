package heap_and_priority_queue;


import java.util.Arrays;
import java.util.Random;

/**
 * 普通队列：先进先出，后进后出
 * 优先队列：出队顺序和入队顺序无关，和优先级相关
 *
 * 应用场景：操作系统的任务调度，动态 选择优先级最高的任务进行处理。医生和患者之间的关系。
 *
 * 优先队列底层实现       入队        出队
 *   普通线性结构      O(1)        O(n)
 *   顺序线性结构      O(n)        O(n)
 *      堆           O(logn)     O(logn)
 *
 * 堆的基本结构
 *  二叉堆：满足特殊性质的二叉树。
 *      1、二叉堆是一颗完全二叉树，完全二叉树即把元素顺序一层一层地排列成树的形状。
 *      2、堆中每一个元素的值都大于等于它的孩子节点。
 *
 *  用数组存储二叉树：
 *      parent = (i - 1) / 2
 *      leftNode = 2 * i + 1
 *      rightNode = 2 * i + 2
 *
 */
public class Main {

    public static void main(String[] args) {
//        int n = 1000000;
//
//        MaxHeap<Integer> maxHeap = new MaxHeap<>();
//        Random random = new Random();
//        for(int i = 0 ; i < n ; i ++)
//            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
//
//        int[] arr = new int[n];
//        for(int i = 0 ; i < n ; i ++)
//            arr[i] = maxHeap.extractMax();
//
//        for(int i = 1 ; i < n ; i ++)
//            if(arr[i-1] < arr[i])
//                throw new IllegalArgumentException("Error");
//
//        System.out.println("Test MaxHeap completed.");

        // heapify test
        int n = 1000000;

        Random random = new Random();
        Integer[] testData1 = new Integer[n];
        for(int i = 0 ; i < n ; i ++)
            testData1[i] = random.nextInt(Integer.MAX_VALUE);

        Integer[] testData2 = Arrays.copyOf(testData1, n);

        double time1 = testHeap(testData1, false);
        System.out.println("Without heapify: " + time1 + " s");

        double time2 = testHeap(testData2, true);
        System.out.println("With heapify: " + time2 + " s");
    }

    private static double testHeap(Integer[] testData, boolean isHeapify){

        long startTime = System.nanoTime();

        MaxHeap<Integer> maxHeap;
        if(isHeapify)
            maxHeap = new MaxHeap<>(testData);
        else{
            maxHeap = new MaxHeap<>(testData.length);
            for(int num: testData)
                maxHeap.add(num);
        }

        int[] arr = new int[testData.length];
        for(int i = 0 ; i < testData.length ; i ++)
            arr[i] = maxHeap.extractMax();

        for(int i = 1 ; i < testData.length ; i ++)
            if(arr[i-1] < arr[i])
                throw new IllegalArgumentException("Error");
        System.out.println("Test MaxHeap completed.");

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

}
