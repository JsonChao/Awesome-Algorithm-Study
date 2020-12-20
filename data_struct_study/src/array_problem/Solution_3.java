package array_problem;

import java.util.HashSet;

/**
 * 原地哈希，把数组中取值在1到n的数放到对应的位置，
 * 比如1放到0的位置，2放到1的位置，……n放到n-1的位置，
 * 然后遍历重置后的数组，若i下标位置不是i+1，则i+1就是
 * 那个最小的正整数，若1到n位置都对的上，说明最小的正整数是n+1。
 *
 * 所谓原地哈希就是对数组中的元素，直接将其映射（也就是交换）到哈希函数计算出来的数组位置上。
 * 本题要找的是第一个缺失的正整数，那么可以使用hash(i) = a[i] - 1这样一个哈希函数，
 * 比如对于数组2，3，1，5，-1，原地哈希过程如下：
 * 把a[0] = 2交换到第1个位置： 3，2，1，5，-1
 * 把a[0] = 3交换到第2个位置：1，2，3，5，-1
 * 此时a[0] = 1, a[1] = 2, a[2] = 3，这三个元素都已经映射到了正确的位置上
 * 把a[3] = 5交换到第4个位置：1，2，3，-1，5
 * 此时a[3] = -1，哈希映射的数组位置（-2）在数组中不存在，因此直接跳过
 * 最后a[4] = 5也映射到了正确的位置上，原地哈希完成
 * 然后再遍历一遍数组，发现a[3] != 4，也就是说4是第一个缺失的正整数，返回4。
 * 对于比较特殊的情况，比如原地哈希后得到的序列是1,2,3,4,5，数组中的元素都
 * 满足a[i] = i + 1，那么直接返回len + 1 = 6即可。
 * 这样做的时间复杂度是O(n)吗，因为在for循环中存在while循环，所以会有这样的疑问。
 * 根据上面的例子我们注意到在a[0]位置完成2次交换后，在a[1]和a[2]位置就不需要进行交换了，
 * 也就是说本方法的均摊时间复杂度的确是O(n)。
 */
public class Solution_3 {

    /**
     * 1、哈希法：时间复杂度O(n)，空间复杂度O(n)
     */
    public int minNumberdisappered(int[] arr) {
        // 1、初始化最小正整数变量为1
        int min = 1;

        // 2、遍历数组：添加当前元素到set中，当set中包含min时，则min+1
        HashSet<Integer> set = new HashSet<>();
        for(int value : arr) {
            set.add(value);
            while (set.contains(min)) {
                min++;
            }
        }
        return min;
    }

    /**
     * return the min number
     * @param arr int整型一维数组 the array
     * @return int整型
     */
    public int minNumberdisappered2(int[] arr) {
        // 1、原地哈希：遍历数组，只要当前元素的值在数组长度范围内 & 当
        // 前元素的值不等于当前元素值-1的下标的值时交换这两个下标元素的值
        int n = arr.length;
        for(int i = 0; i < n; i++) {
            while(arr[i] >= 1 && arr[i] <= n && arr[arr[i] - 1] != arr[i]){
                swap(arr, arr[i]-1, i);
            }
        }

        // 2、再遍历一遍数组，如果下标为i的元素的值不是i+1，则说明i+1就是未出现的最小正整数
        for(int i = 0; i < n; i++){
            if (arr[i] != i + 1){
                return i + 1;
            }
        }

        // 3、特殊情况处理，遍历中没有找到，则说明最小正整数就是n+1
        return n + 1;
    }

    private void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 1, 5, -1};
        System.out.println(new Solution_3().minNumberdisappered2(arr));
    }

}
