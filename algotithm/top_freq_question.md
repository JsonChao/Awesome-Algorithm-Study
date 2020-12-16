# 排序

## 1、选择

```
/**
 * 从数组中选择最小元素，将它与数组的第一个元素交换位置。再从数组剩下的元素中
 * 选择出最小的元素，将它与数组的第二个元素交换位置。不断进行这样的操作，直到将整个数组排序。
 *
 * 选择排序需要 ~N2/2 次比较和 ~N 次交换，它的运行时间与输入无关，这个特点
 * 使得它对一个已经排序的数组也需要这么多的比较和交换操作。
 */
public class Selection<T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] nums) {
        int N = nums.length;
        for (int i = 0; i < N - 1; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(nums[j], nums[min])) {
                    min = j;
                }
            }
            swap(nums, i, min);
        }
    }
}
```


## 2、冒泡

```
/**
 * 从左到右不断交换相邻逆序的元素，在一轮的循环之后，可以让未排序的最大元素上浮到右侧。
 * 在一轮循环中，如果没有发生交换，那么说明数组已经是有序的，此时可以直接退出。
 */
public class Bubble<T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] nums) {
        int N = nums.length;
        boolean isSorted = false;
        for (int i = N - 1; i > 0 && !isSorted; i--) {
            isSorted = true;
            for (int j = 0; j < i; j++) {
                if (less(nums[j + 1], nums[j])) {
                    isSorted = false;
                    swap(nums, j, j + 1);
                }
            }
        }
    }
}
```


## 3、插入

```
/**
 * 每次都将当前元素插入到左侧已经排序的数组中，使得插入之后左侧数组依然有序。
 *
 * 对于数组 {3, 5, 2, 4, 1}，它具有以下逆序：(3, 2), (3, 1), (5, 2),
 * (5, 4), (5, 1), (2, 1), (4, 1)，插入排序每次只能交换相邻元素，
 * 令逆序数量减少 1，因此插入排序需要交换的次数为逆序数量。
 *
 * 插入排序的时间复杂度取决于数组的初始顺序，如果数组已经部分有序了，
 * 那么逆序较少，需要的交换次数也就较少，时间复杂度较低。
 *
 * 平均情况下插入排序需要 ~N2/4 比较以及 ~N2/4 次交换；
 * 最坏的情况下需要 ~N2/2 比较以及 ~N2/2 次交换，最坏的情况是数组是倒序的；
 * 最好的情况下需要 N-1 次比较和 0 次交换，最好的情况就是数组已经有序了。
 */
public class Insertion<T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] nums) {
        int N = nums.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(nums[j], nums[j - 1]); j--) {
                swap(nums, j, j - 1);
            }
        }
    }
}
```


## 4、希尔

```
/**
 * 对于大规模的数组，插入排序很慢，因为它只能交换相邻的元素，每次只能
 * 将逆序数量减少 1。希尔排序的出现就是为了解决插入排序的这种局限性，
 * 它通过交换不相邻的元素，每次可以将逆序数量减少大于 1。
 *
 * 希尔排序使用插入排序对间隔 h 的序列进行排序。通过不断减小 h，最后
 * 令 h=1，就可以使得整个数组是有序的。
 *
 * 希尔排序的运行时间达不到平方级别，使用递增序列 1, 4, 13, 40, ...
 * 的希尔排序所需要的比较次数不会超过 N 的若干倍乘于递增序列的长度。
 * 高级排序算法只会比希尔排序快两倍左右。
 */
public class Shell<T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] nums) {

        int N = nums.length;
        int h = 1;

        while (h < N / 3) {
            h = 3 * h + 1; // 1, 4, 13, 40, ...
        }

        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(nums[j], nums[j - h]); j -= h) {
                    swap(nums, j, j - h);
                }
            }
            h = h / 3;
        }
    }
}
```


## 5、归并

```
/**
 * 归并排序的思想是将数组分成两部分，分别进行排序，然后归并起来。
 */
public abstract class MergeSort<T extends Comparable<T>> extends Sort<T> {

    protected T[] aux;

    // 1. 归并方法：归并方法将数组中两个已经排序的部分归并成一个。
    protected void merge(T[] nums, int l, int m, int h) {

        int i = l, j = m + 1;

        for (int k = l; k <= h; k++) {
            aux[k] = nums[k]; // 将数据复制到辅助数组
        }

        for (int k = l; k <= h; k++) {
            if (i > m) {
                nums[k] = aux[j++];

            } else if (j > h) {
                nums[k] = aux[i++];

            } else if (aux[i].compareTo(aux[j]) <= 0) {
                nums[k] = aux[i++]; // 先进行这一步，保证稳定性

            } else {
                nums[k] = aux[j++];
            }
        }
    }

    @Override
    public void sort(T[] nums) {
        // 2. 自顶向下归并排序
        // 将一个大数组分成两个小数组去求解。
        // 因为每次都将问题对半分成两个子问题，这种对半分的算法复杂度一般为 O(NlogN)。
        aux = (T[]) new Comparable[nums.length];
        sort(nums, 0, nums.length - 1);

        // 3. 自底向上归并排序
        // 先归并那些微型数组，然后成对归并得到的微型数组。
//        int N = nums.length;
//        aux = (T[]) new Comparable[N];
//
//        for (int sz = 1; sz < N; sz += sz) {
//            for (int lo = 0; lo < N - sz; lo += sz + sz) {
//                merge(nums, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
//            }
//        }
    }

    private void sort(T[] nums, int l, int h) {
        if (h <= l) {
            return;
        }
        int mid = l + (h - l) / 2;
        sort(nums, l, mid);
        sort(nums, mid + 1, h);
        merge(nums, l, mid, h);
    }

}
````


## 6、快排

```
/**
 * 归并排序将数组分为两个子数组分别排序，并将有序的子数组归并使得整个数组排序；
 * 快速排序通过一个切分元素将数组分为两个子数组，左子数组小于等于切分元素，
 * 右子数组大于等于切分元素，将这两个子数组排序也就将整个数组排序了。
 *
 * 快速排序是原地排序，不需要辅助数组，但是递归调用需要辅助栈。
 * 快速排序最好的情况下是每次都正好将数组对半分，这样递归调用次数才是最少的。
 * 这种情况下比较次数为 CN=2CN/2+N，复杂度为 O(NlogN)。
 *
 * 最坏的情况下，第一次从最小的元素切分，第二次从第二小的元素切分，如此这般。
 * 因此最坏的情况下需要比较 N2/2。为了防止数组最开始就是有序的，
 * 在进行快速排序时需要随机打乱数组。
 */
public class QuickSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] nums) {
        shuffle(nums);
        sort(nums, 0, nums.length - 1);
    }

    private void sort(T[] nums, int l, int h) {
        if (h <= l)
            return;
        int j = partition(nums, l, h);
        sort(nums, l, j - 1);
        sort(nums, j + 1, h);
    }

    private void shuffle(T[] nums) {
        List<Comparable> list = Arrays.asList(nums);
        Collections.shuffle(list);
        list.toArray(nums);
    }

    // 取 a[l] 作为切分元素，然后从数组的左端向右扫描直到找到第一个
    // 大于等于它的元素，再从数组的右端向左扫描找到第一个小于它的元素，
    // 交换这两个元素。不断进行这个过程，就可以保证左指针 i 的左侧元素
    // 都不大于切分元素，右指针 j 的右侧元素都不小于切分元素。当两个
    // 指针相遇时，将切分元素 a[l] 和 a[j] 交换位置。
    private int partition(T[] nums, int l, int h) {
        int i = l, j = h + 1;
        T v = nums[l];
        while (true) {
            while (less(nums[++i], v) && i != h) ;
            while (less(v, nums[--j]) && j != l) ;
            if (i >= j)
                break;
            swap(nums, i, j);
        }
        swap(nums, l, j);
        return j;
    }

}
```


## 7、三向切分快排

```
/**
 * 快排改进：
 * 1、切换到插入排序：
 * 因为快速排序在小数组中也会递归调用自己，对于小数组，插入排序比
 * 快速排序的性能更好，因此在小数组中可以切换到插入排序。
 *
 * 2、三数取中
 * 最好的情况下是每次都能取数组的中位数作为切分元素，但是计算中位数
 * 的代价很高。一种折中方法是取 3 个元素，并将大小居中的元素作为切分元素。
 *
 * 3、三向切分
 * 对于有大量重复元素的数组，可以将数组切分为三部分，分别对应小于、等于和大于切分元素。
 * 三向切分快速排序对于有大量重复元素的随机数组可以在线性时间内完成排序。
 */
public class ThreeWayQuickSort<T extends Comparable<T>> extends QuickSort<T> {

//    @Override
    protected void sort(T[] nums, int l, int h) {
        if (h <= l) {
            return;
        }
        int lt = l, i = l + 1, gt = h;
        T v = nums[l];
        while (i <= gt) {
            int cmp = nums[i].compareTo(v);
            if (cmp < 0) {
                swap(nums, lt++, i++);
            } else if (cmp > 0) {
                swap(nums, i, gt--);
            } else {
                i++;
            }
        }
        sort(nums, l, lt - 1);
        sort(nums, gt + 1, h);
    }
}
```


## 8、堆

```
/**
 * 大顶堆实现堆排序：
 * 1、构建初始堆，将待排序列构成一个大顶堆：序列对应一个完全二叉树；从最后一个分支结点（n/2）开始，
 * 到根（1）为止，依次对每个分支结点进行下沉，以便形成以每个分支结点为根的堆，当最后对树根结点进行
 * 调整后，整个树就变成了一个堆。
 * 2、将堆顶元素与堆尾元素交换，并从待排序列中移除堆尾元素。
 * 3、使用下沉操作下沉堆顶元素以重新构建大顶堆。
 * 4、重复2~3，直到待排序列中只剩下一个元素(即堆顶元素)。
 *
 * 下沉操作：
 * 1、仅当当前节点有左子节点时进入while循环体。
 * 2、设立下沉后的位置为j，默认为左子节点的位置。
 * 3、如果当前节点有右子节点且左子节点小于右子节点时，下沉后的位置j取右子节点的位置（j++）。
 * 4、如果当前节点的位置k小于下沉后的位置j时，交换k与j的值，完成这一次的下沉操作。
 * 5、更新当前节点的位置为j，如果当前节点还有左子节点则又会进入while循环体进行上述的下沉操作。
 *
 * 把最大元素和当前堆中数组的最后一个元素交换位置，并且不删除它，
 * 那么就可以得到一个从尾到头的递减序列，从正向来看就是一个
 * 递增序列，这就是堆排序。
 *
 * 一个堆的高度为 logN，因此在堆中插入元素和删除最大元素的复杂度都为 logN。
 * 对于堆排序，由于要对 N 个节点进行下沉操作，因此复杂度为 NlogN。
 * 堆排序是一种原地排序，没有利用额外的空间。
 * 现代操作系统很少使用堆排序，因为它无法利用局部性原理进行缓存，
 * 也就是数组元素很少和相邻的元素进行比较和交换。
 */
public class HeapSort<T extends Comparable<T>> extends Sort<T> {

    /**
     * 数组第 0 个位置不能有元素
     */
    @Override
    public void sort(T[] nums) {
        int N = nums.length - 1;
        // 1、构建初始堆，将待排序列构成一个大顶堆：
        // 序列对应一个完全二叉树；从最后一个分支结点（n/2）开始，
        // 到根（1）为止，依次对每个分支结点进行调整（下沉），
        // 以便形成以每个分支结点为根的堆，当最后对树根结点进行
        // 调整后，整个树就变成了一个堆。
        for (int k = N / 2; k >= 1; k--)
            sink(nums, k, N);

        while (N > 1) {
            // 2、将堆顶元素与堆尾元素交换，并从待排序列中移除堆尾元素。
            swap(nums, 1, N--);
            // 3、使用下沉操作下沉堆顶元素以重新构建大顶堆。
            sink(nums, 1, N);
        }
    }

    private void sink(T[] nums, int k, int N) {
        // 1、仅当当前节点有左子节点时进入while循环体。
        while (2 * k <= N) {
            // 2、设立下沉后的位置为j，默认为左子节点。
            int j = 2 * k;
            // 3、如果当前节点有右子节点且左子节点小于右子节点时，下沉后的位置j取右子节点的位置（j++）。
            if (j < N && less(nums, j, j + 1))
                j++;
            // 4、如果当前节点的位置k小于下沉后的位置j时，交换k与j的值，完成这一次的下沉操作。
            if (!less(nums, k, j))
                break;
            swap(nums, k, j);
            // 5、更新当前节点的位置为j，如果当前节点还有左子节点则又会进入while循环体进行上述的下沉操作。
            k = j;
        }
    }

    private boolean less(T[] nums, int i, int j) {
        return nums[i].compareTo(nums[j]) < 0;
    }
}
```


# 高频算法

## 1、二分搜索

```
/**
 * 二分搜索：[0, n-1] 区间搜索版
 */
public class BinarySearch {

    public static int binarySearch(Comparable[] arr, int n, Comparable target){

        int l = 0;
        int r = n - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid].compareTo(target) == 0) {
                return mid;
            }
            if (arr[mid].compareTo(target) < 0) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return -1;
    }

}
```

```
/**
 * 对于有序数列，才能使用二分查找法（排序的作用）。
 * 100 万的数据量只需零点几秒。
 * 注意搜索开闭区间的设定，例如 [0, n - 1] 或 [0, n)。
 * 1962 才意识到的 bug：mid = (l + r) / 2 可能会产生整形溢出，推荐使用减法：l + (r-l)/2。
 * 如何写出正确的程序？
 *      1、明确变量的含义。
 *      2、循环不变量。
 *      3、小数据量调试。
 *      4、大数据量测试。
 */
public class BinarySearch2 {

    public static int binarySearch(Comparable[] arr, int n, Comparable target) {

        int l = 0, r = n;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (target.compareTo(arr[mid]) == 0) {
                return mid;
            }
            if (target.compareTo(arr[mid]) > 0) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return -1;
    }

}
```

## 2、字符串中最长的无重复子串

```
/**
 * 3:
 *      1、字符集？只有字母？数字+字母？ASCLL？
 *      2、大小写是否敏感？
 *
 * 滑动窗口
 * 时间复杂度：O(len(s))
 * 空间复杂度：O(len(charset))
 */
public class Solution3 {

    public int lengthOfLongestSubstring(String s) {

        // 1)、初始化一个存储 ascill 字符集的数组
        int[] freq = new int[256];

        // 2）、起始的滑动窗口为 [0， -1]
        int l = 0, r = -1;

        int res = 0;

        // 3）、维护滑动窗口
        while (l < s.length()) {
            if (r + 1 < s.length() && freq[s.charAt(r + 1)] == 0) {
                freq[s.charAt(++r)] ++;
            } else {
                freq[s.charAt(l++)] --;
            }

            res = Math.max(res, r - l + 1);
        }

        return res;
    }

    public static void main(String[] args) {

        System.out.println((new Solution3()).lengthOfLongestSubstring( "abcabcbb" ));
        System.out.println((new Solution3()).lengthOfLongestSubstring( "bbbbb" ));
        System.out.println((new Solution3()).lengthOfLongestSubstring( "pwwkew" ));
        System.out.println((new Solution3()).lengthOfLongestSubstring( "" ));
    }
}
```

```
/**
 * 3:
 *      1、字符集？只有字母？数字+字母？ASCLL？
 *      2、大小写是否敏感？
 *
 * 滑动窗口
 * 时间复杂度：O(len(s))
 * 空间复杂度：O(len(charset))
 */
public class Solution3_2 {

    public int lengthOfLongestSubstring(String s) {

        int freq[] = new int[256];

        int l = 0, r = -1;
        int res = 0;

        // 除了可以使用 while (l < s.length()) 之外，也可以使用 while (r + 1 < s.length())
        while (r + 1 < s.length()) {

            if (r + 1 < s.length() && freq[s.charAt(r + 1)] == 0) {
                freq[s.charAt(++r)] ++;
            } else {
                freq[s.charAt(l++)] --;
            }

            res = Math.max(res, r - l + 1);
        }

        return res;
    }

}
```

```
/**
 * l 每次可以向前飞跃，而不仅仅是 +1，以确保滑动窗口中不存在重复的字符串，
 * 但为了获得这个飞跃值，选要每次都遍历一次当前滑动窗口的大小。
 *
 * 时间复杂度：O(len(r-l+1) * len(len(s)))
 * 空间复杂度：O(1)
 */
public class Solution3_3 {

    public int lengthOfLongestSubstring(String s) {

        // 后面要使用 s.charAt(r)，所以 r 需要从 0 开始
        int l = 0, r = 0;
        int res = 0;

        // 注意这里是 r < s.length()
        while (r < s.length()) {

            int index = findInDuplicate(s, l, r);

            if (index != - 1) {
                l = index + 1;
            }

            res = Math.max(res, r - l + 1);

            r++;
        }

        return res;
    }

    /**
     * 找到当前窗口 r 是否重复出现在滑动窗口中，有则返回其下标
     * @param s
     * @param l
     * @param r
     * @return
     */
    private int findInDuplicate(String s, int l, int r) {
        for (int i = l; i < r; i++) {
            if (s.charAt(i) == s.charAt(r)) {
                return i;
            }
        }
        return -1;
    }
}
```

```
/**
 * 3:
 *      1、字符集？只有字母？数字+字母？ASCLL？
 *      2、大小写是否敏感？
 *
 * 滑动窗口
 * 时间复杂度：O(len(s))
 * 空间复杂度：O(len(charset))
 */
public class Solution3_4 {

    public int lengthOfLongestSubstring(String s) {

        // 用于记录字符 s.charAt(r) 上一次出现的位置，以实现
        // 一次遍历 s 就移动 l 到 上一次出现位置 + 1 的位置
        int[] last = new int[256];
        Arrays.fill(last, -1);

        int l = 0, r = -1;
        int res = 0;

        while (r + 1 < s.length()) {

            r++;
            if (last[s.charAt(r)] != -1) {
                l = Math.max(l, last[s.charAt(r)] + 1);
            }

            res = Math.max(res, r - l + 1);

            last[s.charAt(r)] = r;
        }

        return res;
    }

}
```


## 3、盛最多水的容器

```
/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 *  
 *
 *
 *
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 *  
 *
 * 示例：
 *
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 *
 */
public class Solution11 {

    public int maxArea(int[] height) {
        int l=0, r=height.length-1;
        int max=0;
        while (l<r){
            int curr=Math.min(height[l],height[r])*(r-l);
            max=Math.max(curr,max);
            if (height[l]<height[r]) l++;
            else r--;
        }
        return max;
    }
}
```


## 4、合并区间

```
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 首先根据左端点排序，这样只需比较每一次合并后区间的右端点和起始的左端点即可。
 */
public class Solution56 {

    public int[][] merge(int[][] intervals) {
        if(intervals==null||intervals.length==0) return new int[0][];
        List<int[]> list=new ArrayList<>();
        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[0]-o2[0];
            }
        });
        int i=0;
        while (i<intervals.length){
            int l=intervals[i][0], r=intervals[i][1];
            i++;
            while (i<intervals.length&&intervals[i][0]<=r){
                r=Math.max(r,intervals[i][1]); i++;
            }
            list.add(new int[]{l,r});
        }
        return list.toArray(new int[0][]);
    }
}
```


## 5、合并两个有序数组

```
/**
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 *
 *  
 *
 * 说明:
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *  
 *
 * 示例:
 *
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 *
 */
public class Solution88 {

    // 为防止元素被覆盖，双指针需从后往前遍历
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index1 = m - 1, index2 = n - 1;
        int indexMerge = m + n - 1;
        while (index1 >= 0 || index2 >= 0) {
            if (index1 < 0) {
                nums1[indexMerge--] = nums2[index2--];
            } else if (index2 < 0) {
                nums1[indexMerge--] = nums1[index1--];
            } else if (nums1[index1] > nums2[index2]) {
                nums1[indexMerge--] = nums1[index1--];
            } else {
                nums1[indexMerge--] = nums2[index2--];
            }
        }
    }

}
```


## 6、找第K个最大的元素

```
/**
 * 1、排序
 * O(nlogn)
 * O(1)
 * 2、堆排序
 * O(nlogk)
 * O(k)
 * 3、利用快排 partition 中，将 pivot 放置在了正确的位置上的性质。
 * O(n)
 * O(1)
 */
public class Solution215 {

    public int findKthLargest(int[] nums, int k) {
        k = nums.length - k;
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int j = partition(nums, l, h);
            if (j == k) {
                break;
            } else if (j < k) {
                l = j + 1;
            } else {
                h = j - 1;
            }
        }
        return nums[k];
    }

    private int partition(int[] nums, int l, int h) {
        int i = l, j = h + 1;
        while (true) {
            while (nums[++i] < nums[l] && i < h);
            while (nums[--j] > nums[l] && j > l);
            if (i >= j) {
                break;
            }
            swap(nums, i, j);
        }
        swap(nums, l, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

}
```


## 7、反转字符串

```
/**
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 *
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 *
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 * 示例 2：
 *
 * 输入：["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 *
 *
 */
public class Solution344 {

    public static String reverseRecursive(String s){
        int length = s.length();
        if(length<=1){
            return s;

        }
        String left  = s.substring(0,length/2);
        String right = s.substring(length/2 ,length);
        String afterReverse = reverseRecursive(right)+reverseRecursive(left);//此处是递归的方法调用
        return afterReverse;
    }
}
```


## 8、数组中只出现一次的数字

```
/**
 * 数组中只出现一次的数字：一个整型数组里除了两个数字之外，
 * 其他的数字都出现了两次，找出这两个数。
 *
 * 两个不相等的元素在位级表示上必定会有一位存在不同，将数组的所有
 * 元素异或得到的结果为不存在重复的两个元素异或的结果。
 *
 * diff &= -diff 得到出 diff 最右侧不为 0 的位，也就是不存在
 * 重复的两个元素在位级表示上最右侧不同的那一位，利用这一位就可以
 * 将两个元素区分开来。
 */
public class Solution_1 {

    public void FindNumsAppearOnce(int[] nums, int num1[], int num2[]) {
        int diff = 0;
        for (int num : nums)
            diff ^= num;
        diff &= -diff;
        for (int num : nums) {
            if ((num & diff) == 0)
                num1[0] ^= num;
            else
                num2[0] ^= num;
        }
    }
}
```


## 9、数组中出现次数超过一半的数字

```
**
 * 多数投票问题，可以利用 Boyer-Moore Majority Vote Algorithm
 * 来解决这个问题，使得时间复杂度为 O(N)。
 *
 * 使用 cnt 来统计一个元素出现的次数，当遍历到的元素和统计元素相等时，
 * 令 cnt++，否则令 cnt--。如果前面查找了 i 个元素，且 cnt == 0，
 * 说明前 i 个元素没有 majority，或者有 majority，但是出现的次数
 * 少于 i / 2 ，因为如果多于 i / 2 的话 cnt 就一定不会为 0 。
 * 此时剩下的 n - i 个元素中，majority 的数目依然多于 (n - i) / 2，
 * 因此继续查找就能找出 majority。
 */
public class Solution_2 {

    public int MoreThanHalfNum_Solution(int[] nums) {
        int majority = nums[0];
        for (int i = 1, cnt = 1; i < nums.length; i++) {
            cnt = nums[i] == majority ? cnt + 1 : cnt - 1;
            if (cnt == 0) {
                majority = nums[i];
                cnt = 1;
            }
        }
        int cnt = 0;
        for (int val : nums)
            if (val == majority)
                cnt++;
        return cnt > nums.length / 2 ? majority : 0;
    }
}
```


## 10、数组中未出现的最小正整数

```
/**
 * 原地哈希，把数组中取值在1到n的数放到对应的位置，
 * 比如1放到0的位置，2放到1的位置，……n放到n-1的位置，
 * 然后遍历重置后的数组，若i下标位置不是i+1，则i+1就是
 * 那个最小的正整数，若1到n位置都对的上，说明最小的正整数是n+1。
 */
public class Solution_3 {

    /**
     * return the min number
     * @param arr int整型一维数组 the array
     * @return int整型
     */
    public int minNumberdisappered (int[] arr) {
        int n=arr.length;
        for(int i=0;i<n;i++){
            while(arr[i]>=1&&arr[i]<=n&&arr[arr[i]-1]!=arr[i]){
                swap(arr,arr[i]-1,i);
            }
        }
        for(int i=0;i<n;i++){
            if(arr[i]!=i+1){
                return i+1;
            }
        }
        return n+1;
    }
    private void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    /**
     * 在加一个时间复杂度o（n），空间复杂度o（n）的解法，不满足题目
     * 空间复杂度，要求，但更容易想到（利用哈希表）
     * @param arr
     * @return
     */
    public int minNumberdisappered2(int[] arr) {
        int min=1;
        HashSet<Integer> set=new HashSet<>();
        for(int i=0;i<arr.length;i++){
            set.add(arr[i]);
            while(set.contains(min)){
                min++;
            }
        }
        return min;
    }
}
```


## 11、两数之和

```
/**
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 *
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 *
 * 说明:
 *
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 *
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 *
 * 1、如果没有解？保证有解。
 * 2、如果有多个解？返回任意解。
 * 3、双层遍历 O(n^2)。（没有利用有序）
 * 4、循环 + 二分查找 O(nlogn)：在有序数组中寻找 target - nums[i]。
 * 5、对撞指针：使用两个索引，两个索引在中间的位置靠近。
 *
 * O(n ^ 2)
 * O(1)
 */
public class Solution167 {

    public int[] twoSum(int[] numbers, int target) {

        if (numbers.length < 2) {
            throw new IllegalArgumentException("length of numbers is illegal!");
        }

        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    int[] res = {i + 1, j + 1};
                    return res;
                }
            }
        }

        throw new IllegalArgumentException("no target!");
    }
}
```

```
/**
 * 二分查找
 * O(nlogn)
 * O(1)
 */
public class Solution167_2 {

    public int[] twoSum(int[] numbers, int target) {

        if (numbers.length < 2) {
            throw new IllegalArgumentException("length of number is illegal!");
        }

        for (int i = 0; i < numbers.length; i++) {
            int j = binarySearch(numbers, i + 1, numbers.length - 1, target - numbers[i]);
            if (j != -1) {
                int[] res = {i + 1, j + 1};
                return res;
            }
        }

        throw new IllegalArgumentException("no two sum!");
    }

    private int binarySearch(int[] numbers, int l, int r, int target) {

        if (l < 0 || l > numbers.length - 1) {
            throw new IllegalArgumentException("l is out of bound~");
        }

        if (r < 0 || r > numbers.length - 1) {
            throw new IllegalArgumentException("r is out of bound~");
        }

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (numbers[mid] == target) {
                return mid;
            } else if (numbers[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return -1;
    }
}
```

```
/**
 * 对撞指针
 * O(n)
 * O(1)
 */
public class Solution167_3 {

    public int[] twoSum(int[] numbers, int target) {

        if (numbers.length < 2) {
            throw new IllegalArgumentException("length of number is illegal!");
        }

        int l = 0, r = numbers.length - 1;
        while (l < r) {
            if (numbers[l] + numbers[r] == target) {
                int[] res = {l + 1, r + 1};
                return res;
            } else if (numbers[l] + numbers[r] < target) {
                l ++;
            } else {
                r --;
            }
        }

        throw new IllegalArgumentException("no tow sum!");
    }
}
```

```
/**
 * Two Sum 系列——1:
 *      1、索引从0开始计算还是从1开始计算？
 *      2、没有解怎么办？
 *      3、有多个解怎么办？保证有唯一解。
 *      4、暴力解法 O(n^2)
 *      5、排序后，使用双索引对撞：O(nlogn) + O(n) = O(nlogn)
 *      6、查找表(map)，将所有元素放入查找表，之后对每一个元素 a，查找 target - a 是否存在。O(n)
 */
public class Solution1 {

    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> record = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {

            int complement = target - nums[i];
            if (record.containsKey(complement)) {
                int[] res = {record.get(complement), i};
                return res;
            }

            record.put(nums[i], i);
        }

        throw new IllegalArgumentException("no this array exist!");
    }

}
```

```
/**
 * O(n)
 * O(n)
 */
public class Solution1_2 {

    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> record = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            record.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            if (record.containsKey(target - nums[i])) {
                if (record.get(target - nums[i]) != i) {
                    int[] res = {i, record.get(target - nums[i])};
                    return res;
                }
            }
        }

        throw new IllegalArgumentException("no exists this array!");
    }

}
```


## 12、二叉树的层序遍历

```
/**
 * 树：层序遍历。102、107、103、199
 * O(n)
 * O(n)
 * 二叉树的层次遍历：层次遍历和广度优先遍历其实是很像的，
 * 在循环中使用队列就好。唯一的不同是每一层单独一个list，
 * 因此我们就需要想想办法让每层分隔开，我使用的办法就是
 * 每次塞进去一个root作为分隔。
 */
public class Solution102 {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {

        ArrayList<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        // 使用队列先进先出的特性
        LinkedList<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(root, 0));
        while (!queue.isEmpty()) {

            Pair<TreeNode, Integer> pair = queue.removeFirst();
            TreeNode node = pair.fst;
            int level = pair.snd;

            if (level == res.size()) {
                res.add(new ArrayList<>());
            }

            assert level < res.size();

            res.get(level).add(node.val);
            if (node.left != null) {
                queue.addLast(new Pair<>(node.left, level + 1));
            }
            if (node.right != null) {
                queue.addLast(new Pair<>(node.right, level + 1));
            }
        }

        return res;
    }

}
```


## 13、二叉树的左视图

```
import binary_search_tree_problem.Solution_1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 一、BFS
 * 思路： 利用 BFS 进行层次遍历，记录下每层的最后一个元素。
 * 时间复杂度： O(N)O(N)，每个节点都入队出队了 1 次。
 * 空间复杂度： O(N)O(N)，使用了额外的队列空间。
 *
 * 二、DFS （时间100%）
 * 思路： 我们按照 「根结点 -> 右子树 -> 左子树」 的顺序访问，
 * 就可以保证每层都是最先访问最右边的节点的。
 *
 * （与先序遍历 「根结点 -> 左子树 -> 右子树」 正好相反，先序
 * 遍历每层最先访问的是最左边的节点）
 *
 * 时间复杂度： O(N)O(N)，每个节点都访问了 1 次。
 * 空间复杂度： O(N)O(N)，因为这不是一棵平衡二叉树，二叉树的深度
 * 最少是logNlogN, 最坏的情况下会退化成一条链表，深度就是 NN，
 * 因此递归时使用的栈空间是 O(N)O(N) 的。
 */
public class Solution199 {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (i == size - 1) {  //将当前层的最后一个节点放入结果列表
                    res.add(node.val);
                }
            }
        }
        return res;
    }

    List<Integer> res = new ArrayList<>();

    public List<Integer> rightSideView2(TreeNode root) {
        dfs(root, 0); // 从根节点开始访问，根节点深度是0
        return res;
    }

    private void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        // 先访问 当前节点，再递归地访问 右子树 和 左子树。
        if (depth == res.size()) {
            // 如果当前节点所在深度还没有出现在res里，说明在该深度
            // 下当前节点是第一个被访问的节点，因此将当前节点加入res中。
            res.add(root.val);
        }
        depth++;
        dfs(root.right, depth);
        dfs(root.left, depth);
    }

}
```

## 14、括号匹配

```
import java.util.Stack;

/**
 * O(n)
 * O(n)
 */
public class Solution20 {

    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {

                if (stack.size() == 0) {
                    return false;
                }

                Character cur = stack.pop();
                Character match = null;
                if (c == ')') {
                    match = '(';
                } else if (c == ']') {
                    match = '[';
                } else if (c == '}') {
                    match = '{';
                }

                if (cur != match) {
                    return false;
                }
            }
        }

        return stack.size() == 0;
    }

}
```


## 15、二叉树的中序遍历

```
/**
 * O(n)：树中的节点个数。
 * O(h)：树的高度。
 */
public class Solution94 {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {

        ArrayList<Integer> res = new ArrayList<>();
        inorderTraversal(root, res);
        return res;
    }

    private void inorderTraversal(TreeNode node, ArrayList<Integer> res) {
        if (node != null) {
            inorderTraversal(node.left, res);
            res.add(node.val);
            inorderTraversal(node.right, res);
        }
    }


}
```

```
/**
 * 模拟系统栈，非递归二叉树的中序遍历
 * O(n)
 * O(h)
 */
public class Solution94_2 {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Command {
        String s;
        TreeNode node;
        public Command(String s, TreeNode node) {
            this.s = s;
            this.node = node;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {

        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<Command> stack = new Stack<>();
        stack.push(new Command("go", root));
        while (!stack.isEmpty()) {
            Command command = stack.pop();

            if (command.s.equals("print")) {
                res.add(command.node.val);
            } else if (command.s.equals("go")) {

                if (command.node.right != null) {
                    stack.push(new Command("go", command.node.right));
                }

                stack.push(new Command("print", command.node));

                if (command.node.left != null) {
                    stack.push(new Command("go", command.node.left));
                }
            }
        }

        return res;
    }

}
```

## 16、二叉树的前序遍历

```
/**
 * 144、94、145：
 *      1、递归实现。
 *      2、循环实现：使用栈模拟系统栈，写出非递归程序。
 * O(n)
 * O(h)
 */
public class Solution144 {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        preorderTraversal(root, res);
        return res;
    }

    private void preorderTraversal(TreeNode node, ArrayList<Integer> res) {
        if (node != null) {
            res.add(node.val);
            preorderTraversal(node.left, res);
            preorderTraversal(node.right, res);
        }
    }
}
```

```
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * O(n)
 * O(h)
 */
public class Solution144_2 {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Command {
        String s;
        TreeNode node;
        public Command(String s, TreeNode node) {
            this.s = s;
            this.node = node;
        }
    }

    public List<Integer> preorderTraversal(TreeNode root) {

        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<Command> stack = new Stack<>();
        stack.push(new Command("go", root));
        while (!stack.isEmpty()) {
            Command command = stack.pop();

            if (command.s.equals("print")) {
                res.add(command.node.val);
            } else if (command.s.equals("go")) {
                if (command.node.right != null) {
                    stack.push(new Command("go", command.node.right));
                }

                if (command.node.left != null) {
                    stack.push(new Command("go", command.node.left));
                }

                // 注意栈是先进后出的，不同于递归写法，这里顺序需要反过来
                stack.push(new Command("print", command.node));
            }
        }

        return res;
    }

}
```


## 17、二叉树的后序遍历

```
import java.util.ArrayList;
import java.util.List;

/**
 * O(n)
 * O(h)
 */
public class Solution145 {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        postorderTraversal(root, res);
        return res;
    }

    private void postorderTraversal(TreeNode node, ArrayList<Integer> res) {
        if (node != null) {
            postorderTraversal(node.left, res);
            postorderTraversal(node.right, res);
            res.add(node.val);
        }
    }

}
```

```
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * O(n)
 * O(h)
 */
public class Solution145_2 {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private class Command {
        String val;
        TreeNode node;
        public Command(String val, TreeNode node) {
            this.val = val;
            this.node = node;
        }
    }

    public List<Integer> postorderTraversal(TreeNode root) {

        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<Command> stack = new Stack<>();
        stack.push(new Command("go", root));
        while (!stack.isEmpty()) {

            Command command = stack.pop();

            if (command.val.equals("print")) {
                res.add(command.node.val);
            } else if (command.val.equals("go")) {

                // 不同于递归写法，栈是先进后出的，所以需要反过来
                stack.push(new Command("print", command.node));

                if (command.node.right != null) {
                    stack.push(new Command("go", command.node.right));
                }

                if (command.node.left != null) {
                    stack.push(new Command("go", command.node.left));
                }
            }
        }

        return res;
    }

}
```


## 18、最小值栈

```
import java.util.Stack;

/**
 * 最小值栈
 */
public class Solution155 {

    private Stack<Integer> mDataStack;
    private Stack<Integer> mMinStack;
    private int min;

    public Solution155() {
        mDataStack = new Stack<>();
        mMinStack = new Stack<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int e) {
        mDataStack.push(e);
        min = Math.min(min, e);
        mMinStack.push(min);
    }

    public void pop() {
        mDataStack.pop();
        mMinStack.pop();
        min = mMinStack.isEmpty() ? Integer.MAX_VALUE : mMinStack.peek();
    }

    public int getMin() {
        return mMinStack.peek();
    }

    public int top() {
        return mDataStack.peek();
    }

}
```


## 19、两个队列实现一个栈

```
import java.util.Stack;

/**
 * 用两个栈来实现一个队列，完成队列的 Push 和 Pop 操作。
 *
 * 1、in 栈用来处理入栈（push）操作，out 栈用来处理出栈（pop）操作。
 * 一个元素进入 in 栈之后，出栈的顺序被反转。当元素要出栈时，
 * 需要先进入 out 栈，此时元素出栈顺序再一次被反转，因此出栈顺序
 * 就和最开始入栈顺序是相同的，先进入的元素先退出，这就是队列的顺序。
 * 2、再s1中写入，要输出时，将所有s1数据移到s2，然后只要s2数据不为空，
 * 就可以一直取出，一旦为空，则再将s1数据移到s2中即可。
 */
public class Solution_1 {

    Stack<Integer> in = new Stack<>();
    Stack<Integer> out = new Stack<>();

    public void push(int node) {
        in.push(node);
    }

    public int pop() throws Exception {
        if (out.isEmpty())
            while (!in.isEmpty())
                out.push(in.pop());

        if (out.isEmpty())
            throw new Exception("queue is empty");

        return out.pop();
    }
}
```


## 20、表示求值

```
/**
 * 1.用栈保存各部分计算的和
 * 2.遍历表达式
 * 3.遇到数字时继续遍历求这个完整的数字的值
 * 4.遇到左括号时递归求这个括号里面的表达式的值
 * 5.遇到运算符时或者到表达式末尾时，就去计算上一个运算符并把计算结果push进栈，然后保存新的运算符
 *      如果是+，不要计算，push进去
 *      如果是-，push进去负的当前数
 *      如果是×、÷，pop出一个运算数和当前数作计算
 * 6.最后把栈中的结果求和即可
 */
public class Solution_2 {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 返回表达式的值
     * @param s string字符串 待计算的表达式
     * @return int整型
     */
    public int solve(String s) {
        Stack<Integer> stack = new Stack<>();
        int sum = 0, number = 0;
        char sign = '+';
        char[] charArray = s.toCharArray();
        for (int i = 0, n = charArray.length; i < n; i++) {
            char c = charArray[i];
            if (c == '(') {
                int j = i + 1;
                int counterPar = 1;
                while (counterPar > 0) {
                    if (charArray[j] == '(') {
                        counterPar++;
                    }
                    if (charArray[j] == ')') {
                        counterPar--;
                    }
                    j++;
                }
                number = solve(s.substring(i + 1, j - 1));
                i = j - 1;
            }
            if (Character.isDigit(c)) {
                number = number * 10 + c - '0';
            }
            if (!Character.isDigit(c) || i == n - 1) {
                if (sign == '+') {
                    stack.push(number);
                } else if (sign == '-') {
                    stack.push(-1 * number);
                } else if (sign == '*') {
                    stack.push(stack.pop() * number);
                } else if (sign == '/') {
                    stack.push(stack.pop() / number);
                }
                number = 0;
                sign = c;
            }
        }
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }
}
```


## 21、删除倒数第N个节点

```
/**
 * 19：
 *      1、n从0计还是从1计。
 *      2、n不合法，负数或者大于链表长度如何处理（保证n合法）。
 *      3、先遍历一遍计算链表长度；再遍历一遍删除倒数第n个节点。
 *      4、设立两个指针 p、q，他们的距离为n，当 q 遍历到 Null 时，p.next 即为 delNode。
 *
 * O(n)
 * O(1)
 */
public class Solution19 {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        // 1、计算链表的长度
        int len = 0;
        for (ListNode cur = dummyHead.next; cur != null; cur = cur.next) {
            len++;
        }

        // 2、获取待删除节点的前一个节点
        int k = len - n;
        assert k >= 0;
        ListNode cur = dummyHead;
        for (int i = 0; i < k; i++) {
            cur = cur.next;
        }

        // 3、删除指定节点
        cur.next = cur.next.next;

        return dummyHead.next;
    }

}
```

```
 * 19：
 *      1、n从0计还是从1计。
 *      2、n不合法，负数或者大于链表长度如何处理（保证n合法）。
 *      3、先遍历一遍计算链表长度；再遍历一遍删除倒数第n个节点。
 *      4、设立两个指针 p、q，他们的距离为n，当 q 遍历到 Null 时，p.next 即为 delNode。
 *
 * O(n)
 * O(1)
 */
public class Solution19_2 {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode p = dummyHead;
        ListNode q = dummyHead;
        // 1、为后续获取待删除前一个节点，需执行 n + 1 次遍历，而不是 n 次
        for (int i = 0; i < n + 1; i++) {
            assert q != null;
            q = q.next;
        }

        // 2、当 q == null 时，p 即为待删除的前一个节点
        while (q != null) {
            q = q.next;
            p = p.next;
        }

        p.next = p.next.next;

        return dummyHead.next;
    }

}
```


## 22、合并两个有序链表

```
/**
 * 设立链表的虚拟头结点
 */
public class Solution21 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

}
```


## 23、合并K个有序链表

```
/**
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 优先队列：用容量为K的最小堆优先队列，把链表的头结点都放进去，然后出队当前
 * 优先队列中最小的，挂上链表，，然后让出队的那个节点的下一个入队，
 * 再出队当前优先队列中最小的，直到优先队列为空。
 * 时间复杂度:O(n*log(k))，n是所有链表中元素的总和，k是链表个数。
 *
 * 分支法：分而治之，链表两两合并。时间复杂度:O(n*log(k))
 */
public class Solution23 {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }

        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);

        for (ListNode list : lists) {
            if (list == null) {
                continue;
            }
            pq.add(list);
        }

        while (!pq.isEmpty()) {
            ListNode nextNode = pq.poll();
            curr.next = nextNode;
            curr = curr.next;
            if (nextNode.next != null) {
                pq.add(nextNode.next);
            }
        }
        return dummyHead.next;
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        int mid = left + (right - left) / 2;
        ListNode l1 = merge(lists, left, mid);
        ListNode l2 = merge(lists, mid + 1, right);
        return mergeTwoLists(l1, l2);
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1,l2.next);
            return l2;
        }
    }

}
```


## 24、K个一组反转链表

```
/**
 * K 个一组翻转链表：不仅仅是穿针引线
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺
 *
 * 1、链表分区为已翻转部分+待翻转部分+未翻转部分
 * 2、每次翻转前，要确定翻转链表的范围，这个必须通过 k 此循环来确定
 * 3、需记录翻转链表前驱和后继，方便翻转完成后把已翻转部分和未翻转部分连接起来
 * 4、初始需要两个变量 pre 和 end，pre 代表待翻转链表的前驱，end 代表待翻转链表的末尾
 * 5、经过k此循环，end 到达末尾，记录待翻转链表的后继 next = end.next
 * 6、翻转链表，然后将三部分链表连接起来，然后重置 pre 和 end 指针，然后进入下一次循环
 * 7、特殊情况，当翻转部分长度不足 k 时，在定位 end 完成后，end==null，已经到达末尾，说明题目已完成，直接返回即可
 * 8、时间复杂度为 O(n*K)最好的情况为 O(n)O 最差的情况未 O(n^2)
 * 9、空间复杂度为 O(1)除了几个必须的节点指针外，我们并没有占用其他空间
 */
public class Solution25 {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null){
            return head;
        }
        //定义一个假的节点。
        ListNode dummy=new ListNode(0);
        //假节点的next指向head。
        // dummy->1->2->3->4->5
        dummy.next=head;
        //初始化pre和end都指向dummy。pre指每次要翻转的链表的头结点的上一个节点。end指每次要翻转的链表的尾节点
        ListNode pre=dummy;
        ListNode end=dummy;

        while(end.next!=null){
            //循环k次，找到需要翻转的链表的结尾,这里每次循环要判断end是否等于空,因为如果为空，end.next会报空指针异常。
            //dummy->1->2->3->4->5 若k为2，循环2次，end指向2
            for(int i=0;i<k&&end != null;i++){
                end=end.next;
            }
            //如果end==null，即需要翻转的链表的节点数小于k，不执行翻转。
            if(end==null){
                break;
            }
            //先记录下end.next,方便后面链接链表
            ListNode next=end.next;
            //然后断开链表
            end.next=null;
            //记录下要翻转链表的头节点
            ListNode start=pre.next;
            //翻转链表,pre.next指向翻转后的链表。1->2 变成2->1。 dummy->2->1
            pre.next=reverse(start);
            //翻转后头节点变到最后。通过.next把断开的链表重新链接。
            start.next=next;
            //将pre换成下次要翻转的链表的头结点的上一个节点。即start
            pre=start;
            //翻转结束，将end置为下次要翻转的链表的头结点的上一个节点。即start
            end=start;
        }
        return dummy.next;


    }

    //链表翻转
    // 例子：   head： 1->2->3->4
    public ListNode reverse(ListNode head) {
        //单链表为空或只有一个节点，直接返回原单链表
        if (head == null || head.next == null){
            return head;
        }
        //前一个节点指针
        ListNode preNode = null;
        //当前节点指针
        ListNode curNode = head;
        //下一个节点指针
        ListNode nextNode = null;
        while (curNode != null){
            nextNode = curNode.next;//nextNode 指向下一个节点,保存当前节点后面的链表。
            curNode.next=preNode;//将当前节点next域指向前一个节点   null<-1<-2<-3<-4
            preNode = curNode;//preNode 指针向后移动。preNode指向当前节点。
            curNode = nextNode;//curNode指针向后移动。下一个节点变成当前节点
        }
        return preNode;
    }
}
```


## 25、删除链表中重复的节点

```
public class Solution83 {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        head.next = deleteDuplicates(head.next);
        return head.val == head.next.val ? head.next : head;
    }

}
```


## 26、判断链表是否有环

```
public class Solution141 {

    public boolean hasCycle(ListNode head) {

        if (head == null) {
            return false;
        }

        ListNode l1 = head, l2 = head.next;
        while (l1 != null && l2 != null && l2.next != null) {
            if (l1 == l2) {
                return true;
            }

            l1 = l1.next;
            l2 = l2.next.next;
        }
        return false;
    }

}
```


## 27、识别出环形链表

```
/**
 * 环形链表 II：快慢指针+Floyd算法。
 */
public class Solution142 {

    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        if (fast == null || fast.next == null) return null;
        slow = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
```


## 28、反转链表

```
/**
 *（pre、cur、next）
 * O(n)
 * O(1)
 */
public class Solution206 {

    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseList(ListNode head) {

        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

}
```

```
/**
 * （pre、cur、next）
 * O(n)
 * O(1)
 */
public class Solution206_2 {

    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseList(ListNode head) {

        // 1、递归终止条件
        if (head == null || head.next == null) {
            return head;
        }

        ListNode rhead = reverseList(head.next);

        // 2、从后向前反转链表
        head.next.next = head;
        head.next = null;

        return rhead;
    }

}
```


## 29、链表元素相加

```
/**
 * 445：
 *      1、如果不允许修改输入的链表呢？
 *      2、使用辅助的数据结构。
 */
public class Solution445 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // 1、使用 stack 存储链表数据，以便从后往前进行计算
        Stack<Integer> stack1 = buildStack(l1);
        Stack<Integer> stack2 = buildStack(l2);

        // 2、创建头结点和进位值变量
        ListNode head = new ListNode(-1);
        int c = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || c != 0) {
            int x = stack1.isEmpty() ? 0 : stack1.pop();
            int y = stack2.isEmpty() ? 0 : stack2.pop();
            int sum = x + y + c;
            ListNode node = new ListNode(sum % 10);
            node.next = head.next;
            head.next = node;
            c = sum / 10;
        }

        return head.next;
    }

    private Stack<Integer> buildStack(ListNode listNode) {
        Stack<Integer> stack = new Stack<>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        return stack;
    }

}
```


## 30、两个链表的公共节点

```
/**
 * 两个链表的公共节点
 *
 * 设 A 的长度为 a + c，B 的长度为 b + c，其中 c 为尾部公共部分长度，
 * 可知 a + c + b = b + c + a。
 *
 * 当访问链表 A 的指针访问到链表尾部时，令它从链表 B 的头部重新开始访问
 * 链表 B；同样地，当访问链表 B 的指针访问到链表尾部时，令它从链表 A 的
 * 头部重新开始访问链表 A。这样就能控制访问 A 和 B 两个链表的指针能同时
 * 访问到交点。
 */
public class Solution_1 {

    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode l1 = pHead1, l2 = pHead2;
        while (l1 != l2) {
            l1 = (l1 == null) ? pHead2 : l1.next;
            l2 = (l2 == null) ? pHead1 : l2.next;
        }
        return l1;
    }
}
```


## 31、单链表的选择排序

```
/**
 * 1. 在链表头前边建立哨兵，开始时，已经排好序的链表为空，
 *    因此已经排序的链表的尾部指针指向刚建立的哨兵；
 * 2. 根据选择排序的思想，我们需要每次从未排序的列表中选择最小的一个节点，
 *    利用头插法将其插入到已经排序好的链表的后面；
 * 3. 接着，将已经排序好的链表的尾部指针指向刚插入的元素；
 * 4. 重复步骤2和3，直到所有的元素都已经排好序，即已经排序的链表的尾部指针移动到了链表的尾部。
 */

/*
 * public class ListNode {
 *   int val;
 *   ListNode next = null;
 * }
 */

public class Solution_2 {
    /**
     *
     * @param head ListNode类 the head node
     * @return ListNode类
     */
    public ListNode sortInList (ListNode head) {
        // 寻找最小的元素，并利用头插法插入到节点
        ListNode dummy = new ListNode(Integer.MAX_VALUE);
        dummy.next = head;
        ListNode sorted = dummy;

        while (sorted.next != null) {
            ListNode pre = sorted;
            ListNode cur = sorted.next;
            ListNode pre_min = null;
            ListNode min = null;

            // 寻找最小的数
            while (cur != null) {
                if (min == null || cur.val < min.val) {
                    min = cur;
                    pre_min = pre;
                }
                // 继续向后移动指针
                cur = cur.next;
                pre = pre.next;
            }

            // 利用头插法插入
            pre_min.next = min.next;
            min.next = sorted.next;
            sorted.next = min;

            // 哨兵节点往后移动
            sorted = min;
        }

        return dummy.next;
    }
}
```


## 32、链表中倒数第K个节点

```
/**
 * 设链表的长度为 N。设置两个指针 P1 和 P2，先让 P1 移动 K 个节点，
 * 则还有 N - K 个节点可以移动。此时让 P1 和 P2 同时移动，
 * 可以知道当 P1 移动到链表结尾时，P2 移动到第 N - K 个节点处，
 * 该位置就是倒数第 K 个节点。
 */
public class Solution_3 {

    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null)
            return null;
        ListNode P1 = head;
        while (P1 != null && k-- > 0)
            P1 = P1.next;
        if (k > 0)
            return null;
        ListNode P2 = head;
        while (P1 != null) {
            P1 = P1.next;
            P2 = P2.next;
        }
        return P2;
    }
}
```


## 33、判断链表是否是一个回文结构

```
/**
 * Java双端队列解法
 */
public class Solution_4 {

    public boolean isPail (ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        Deque<Integer> deque = new ArrayDeque<>();
        while (head != null) {
            deque.addLast(head.val);
            head = head.next;
        }
        while (deque.size() > 1) {
            if (!deque.pollFirst().equals(deque.pollLast())) {
                return false;
            }
        }
        return true;
    }
}
```


## 34、三数之和

```
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15:
 *      1、不同的三元组？
 *      2、如果有多个解，解的顺序？
 *      3、如果没有解。
 *
 * 三数之和：需要尤其注意去重，有些繁琐。
 */
public class Solution15 {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list=new ArrayList<>();
        Arrays.sort(nums);
        for (int i=0; i<nums.length; i++){
            int l=i+1, r=nums.length-1;
            if(nums[i]>0) break;
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            int sum=-1;
            while(l<r){
                sum=nums[i]+nums[l]+nums[r];
                if(sum>0) r--;
                else if (sum<0) l++;
                else{
                    list.add(new ArrayList<Integer>(Arrays.asList(nums[i],nums[l],nums[r])));
                    while(l<r&&nums[l]==nums[l+1]) l++;
                    while(l<r&&nums[r]==nums[r-1]) r--;
                    l++;r--;
                }
            }
        }
        return list;
    }
}
```


## 35、二叉树的最大深度

```
/**
 * O(n)
 * O(h)
 */
public class Solution104 {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int maxDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }


}
```


## 36、二叉树的最低公共祖先

```
/**
 * O(lgn)
 * O(h)
 */
public class Solution235 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (p == null || q == null) {
            throw new IllegalArgumentException("q or p is not null!");
        }

        if (root == null) {
            return null;
        }

        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }

        assert p.val == root.val || q.val == root.val ||
                (root.val - p.val) * (root.val - q.val) < 0;

        return root;
    }

}
```


```
public class Solution236 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }

}
```


## 37、二叉树根节点到所有叶子节点的路径之和

```
/**
 * 先序遍历的思想(根左右)+数字求和(每一层都比上层和*10+当前根节点的值)
 */
public class Solution_1 {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int sumNumbers(TreeNode root) {
        int sum = 0;
        if (root == null) {
            return sum;
        }
        return preorderSumNumbers(root, sum);
    }

    public int preorderSumNumbers(TreeNode root, int sum) {
        if (root == null)
            return 0;
        sum = sum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        }
        return preorderSumNumbers(root.left, sum) + preorderSumNumbers(root.right, sum);
    }
}
```


## 38、二叉树的之字形遍历

```
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 请实现一个函数按照之字形打印二叉树，即第一行按照
 * 从左到右的顺序打印，第二层按照从右至左的顺序打印，
 * 第三行按照从左到右的顺序打印，其他行以此类推。
 */
public class Solution_2 {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        boolean reverse = false;
        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            int cnt = queue.size();
            while (cnt-- > 0) {
                TreeNode node = queue.poll();
                if (node == null)
                    continue;
                list.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }
            if (reverse)
                Collections.reverse(list);
            reverse = !reverse;
            if (list.size() != 0)
                ret.add(list);
        }
        return ret;
    }
}
```


## 39、前K个出现频率最高的元素


```
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Java 的 PriorityQueue 内部是最小堆
 *
 * d 叉堆：d-ary heap
 *
 * 常规堆的缺陷：只能看到堆首的元素。
 *
 * 索引堆：保存元素与之对应的索引。
 * 二项堆
 * 斐波那契堆
 *
 * 普通队列、优先队列、广义队列
 *
 * 栈，也可以理解成是一个队列。
 */
public class Solution2 {

    public int[] topKFrequent(int[] nums, int k) {

        // 1、将所有 元素:出现频次 添加到 TreeMap 中
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num:nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        // 2、使用优先队列维护前 Top k 个元素
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> map.get(o1) - map.get(o2));
        for (int key:map.keySet()) {
            if (pq.size() < k) {
                pq.add(key);
            } else if (map.get(key) > map.get(pq.peek())) {
                pq.remove();
                pq.add(key);
            }
        }

        // 3、返回保存 Top k 个元素的数组
        int[] values = new int[k];
        for (int i = values.length - 1; i >= 0; i--) {
            values[i] = pq.remove();
        }
        return values;
    }

}
```


## 40、全排列

```
import java.util.ArrayList;
import java.util.List;

/**
 * 排列问题
 */
public class Solution46 {

    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> permutes = new ArrayList<>();
        List<Integer> permuteList = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        backtracking(permuteList, permutes, visited, nums);

        return permutes;
    }

    private void backtracking(List<Integer> permuteList, List<List<Integer>> permutes, boolean[] visited, int[] nums) {

        if (permuteList.size() == nums.length) {
            permutes.add(new ArrayList<>(permuteList));
            return;
        }

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                permuteList.add(nums[i]);
                backtracking(permuteList, permutes, visited, nums);
                permuteList.remove(permuteList.size() - 1);
                visited[i] = false;
            }
        }
    }
}
```


## 41、全排列（去重）

```
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 排列问题
 */
public class Solution47 {

    public List<List<Integer>> permuteUnique(int[] nums) {

        List<List<Integer>> permutes = new ArrayList<>();
        List<Integer> permuteList = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        backtracking(permuteList, permutes, visited, nums);

        return permutes;
    }

    private void backtracking(List<Integer> permuteList, List<List<Integer>> permutes, boolean[] visited, int[] nums) {

        if (permuteList.size() == nums.length) {
            permutes.add(new ArrayList<>(permuteList));
            return;
        }

        for (int i = 0; i < visited.length; i++) {
            // 去重处理
            if (i != 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }
            if (!visited[i]) {
                visited[i] = true;
                permuteList.add(nums[i]);
                backtracking(permuteList, permutes, visited, nums);
                permuteList.remove(permuteList.size() - 1);
                visited[i] = false;
            }
        }
    }

}
```


## 42、复原IP地址

```
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 递归与回溯
 *
 * 设题目中给出的字符串为 ss。我们用递归函数 \textit{dfs}(\textit{segId},
 * \textit{segStart})dfs(segId,segStart) 表示我们正在从 s[\textit{segStart}]s[segStart]
 * 的位置开始，搜索 IP 地址中的第 \textit{segId}segId 段，
 * 其中 \textit{segId} \in \{0, 1, 2, 3\}segId∈{0,1,2,3}。
 * 由于 IP 地址的每一段必须是 [0, 255][0,255] 中的整数，
 * 因此我们从 \textit{segStart}segStart 开始，从小到大依次枚举当前这一段
 * IP 地址的结束位置 \textit{segEnd}segEnd。如果满足要求，就递归地进行下一段搜索，
 * 调用递归函数 \textit{dfs}(\textit{segId} + 1, \textit{segEnd} + 1)dfs(segId+1,segEnd+1)。
 * 特别地，由于 IP 地址的每一段不能有前导零，因此如果 s[\textit{segStart}]s[segStart] 等于
 * 字符 00，那么 IP 地址的第 \textit{segId}segId 段只能为 00，需要作为特殊情况进行考虑。
 * 在递归搜索的过程中，如果我们已经得到了全部的 44 段 IP 地址（即 \textit{segId} = 4segId=4），
 * 并且遍历完了整个字符串（即 \textit{segStart} = |s|segStart=∣s∣，
 * 其中 |s|∣s∣ 表示字符串 ss 的长度），那么就复原出了一种满足题目要求的
 * IP 地址，我们将其加入答案。在其它的时刻，如果提前遍历完了整个字符串，
 * 那么我们需要结束搜索，回溯到上一步。
 *
 */
class Solution93 {

    static final int SEG_COUNT = 4;
    List<String> ans = new ArrayList<String>();
    int[] segments = new int[SEG_COUNT];

    public List<String> restoreIpAddresses(String s) {
        segments = new int[SEG_COUNT];
        dfs(s, 0, 0);
        return ans;
    }

    public void dfs(String s, int segId, int segStart) {
        // 如果找到了 4 段 IP 地址并且遍历完了字符串，那么就是一种答案
        if (segId == SEG_COUNT) {
            if (segStart == s.length()) {
                StringBuffer ipAddr = new StringBuffer();
                for (int i = 0; i < SEG_COUNT; ++i) {
                    ipAddr.append(segments[i]);
                    if (i != SEG_COUNT - 1) {
                        ipAddr.append('.');
                    }
                }
                ans.add(ipAddr.toString());
            }
            return;
        }

        // 如果还没有找到 4 段 IP 地址就已经遍历完了字符串，那么提前回溯
        if (segStart == s.length()) {
            return;
        }

        // 由于不能有前导零，如果当前数字为 0，那么这一段 IP 地址只能为 0
        if (s.charAt(segStart) == '0') {
            segments[segId] = 0;
            dfs(s, segId + 1, segStart + 1);
        }

        // 一般情况，枚举每一种可能性并递归
        int addr = 0;
        for (int segEnd = segStart; segEnd < s.length(); ++segEnd) {
            addr = addr * 10 + (s.charAt(segEnd) - '0');
            if (addr > 0 && addr <= 0xFF) {
                segments[segId] = addr;
                dfs(s, segId + 1, segEnd + 1);
            } else {
                break;
            }
        }
    }
}
```


## 43、爬楼梯

```
/**
 * 记忆化搜索 + 递归
 */
public class Solution70 {

    private int[] memo;

    public int climbStairs(int n) {

        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return calcWays(n);
    }

    private int calcWays(int n) {

        if (n == 0 || n == 1) {
            return 1;
        }

        if (memo[n] == -1) {
            memo[n] = calcWays(n - 1) + calcWays(n - 2);
        }

        return memo[n];
    }

}
```

```
/**
 * DP + 记忆化搜索
 * O(n)
 * O(n)
 */
public class Solution70_2 {

    public int climbStairs(int n) {

        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        memo[0] = 1;
        memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n];
    }

}
```

```
/**
 * Dp => 循环只需记录前两个数的值即可 => 矩阵 or 推导公式 优化
 * 空间 O(n) => 空间 O(1) => 时间 O(logn)
 */
public class Solution70_3 {

    public int climbStairs(int n) {

        if (n <= 0) {
            throw new IllegalArgumentException("illegal argument");
        }

        if (n == 1) {
            return 1;
        }

        int pre = 1, cur = 1;
        for (int i = 2; i <= n; i++) {
            int next = pre + cur;
            pre = cur;
            cur = next;
        }

        return cur;
    }

}
```


## 44、第一天股票的最大价格

```
/**
 * 1、穷举框架-状态与选择：
 * for 状态1 in 状态1的所有取值：
 *     for 状态2 in 状态2的所有取值：
 *         for ...
 *             dp[状态1][状态2][...] = 择优(选择1，选择2...)
 *  这里每天都有三种「选择」：买入、卖出、无操作。
 *  「状态」有三个，第一个是天数，第二个是允许交易的最大次数，第三个是当前的持有状态（0、1）
 * 2、状态转移框架与 base case：
 * 状态转移方程：
 * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
 * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
 *
 * base case：
 * dp[-1][k][0] = dp[i][0][0] = 0
 * dp[-1][k][1] = dp[i][0][1] = -infinity
 */
public class Solution121 {

    // k == 1
    int maxProfit_k_1(int[] prices) {
        int n = prices.length;
        // base case: dp[-1][0] = 0, dp[-1][1] = -infinity
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            // dp[i][1] = max(dp[i-1][1], -prices[i])
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }
        return dp_i_0;
    }

}
```


## 45、第K天股票的最大价格

```
/**
 * 1、穷举框架-状态与选择：
 * for 状态1 in 状态1的所有取值：
 *     for 状态2 in 状态2的所有取值：
 *         for ...
 *             dp[状态1][状态2][...] = 择优(选择1，选择2...)
 *  这里每天都有三种「选择」：买入、卖出、无操作。
 *  「状态」有三个，第一个是天数，第二个是允许交易的最大次数，第三个是当前的持有状态（0、1）
 * 2、状态转移框架与 base case：
 * 状态转移方程：
 * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
 * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
 *
 * base case：
 * dp[-1][k][0] = dp[i][0][0] = 0
 * dp[-1][k][1] = dp[i][0][1] = -infinity
 */
public class Solution121 {

    // k == 1
    int maxProfit_k_1(int[] prices) {
        int n = prices.length;
        // base case: dp[-1][0] = 0, dp[-1][1] = -infinity
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            // dp[i][1] = max(dp[i-1][1], -prices[i])
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }
        return dp_i_0;
    }

}
```


## 46、最长上升子序列

```
mport java.util.Arrays;

/**
 * 最长上升子序列:
 *      1、暴力解法：选择所有的子序列进行判断。O((2^n)*n)
 *      2、LIS(i) 表示[0...i]的范围内，选择数字nums[i]可以获得的最长上升子序列的长度。
 *      LIS(i) = max(1+LIS(j) if nums[i] > nums[j])
 *      LIS(i) 表示以第i个数字为结尾的最长上升子序列的长度。O(n^2）
 *      3、O(nlogn)
 *
 * 记忆化搜索
 * O(n^2)
 * O(n)
 */
public class Solution300 {

    private int[] memo;

    public int lengthOfLIS(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }

        memo = new int[nums.length];
        Arrays.fill(memo, -1);

        int res = 1;
        for (int i = 0; i < nums.length; i++) {
            res = Math.max(res, getMaxLength(nums, i));
        }
        return res;
    }

    // 记录以 nums[index] 为结尾的最长上升子序列的长度
    private int getMaxLength(int[] nums, int index) {

        if (memo[index] != -1) {
            return memo[index];
        }

        int res = 1;
        for (int i = 0; i <= index - 1; i++) {
            if (nums[index] > nums[i]) {
                res = Math.max(res, 1 + getMaxLength(nums, i));
            }
        }

        return memo[index] = res;
    }

}
```

```
/**
 * DP
 * O(n ^ 2)
 * O(n)
 */
public class Solution300_2 {

    public int lengthOfLIS(int[] nums) {

        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    memo[i] = Math.max(memo[i], 1 + memo[j]);
                }
            }
        }

        int res = memo[0];
        for (int i = 1; i < n; i++) {
            res = Math.max(res, memo[i]);
        }

        return res;
    }

}
```


## 47、最长公共子序列

```
import java.util.Arrays;

/**
 * DP
 * O((len(s1) * len(s2))
 * O((len(s1) * len(s2))
 */
public class Solution1143 {

    private int[][] memo;

    public int longestCommonSubsequence(String s1, String s2) {

        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("illegal arguments");
        }

        int n1 = s1.length();
        int n2 = s2.length();

        if (n1 == 0 || n2 == 0) {
            return 0;
        }

        memo = new int[n1][n2];
        for (int i = 0; i < n1; i++) {
            Arrays.fill(memo[i], -1);
        }

        return lcs(s1, s2, n1 - 1, n2 - 1);
    }

    // 统计 [0...m] 与 [0...n] 的最长公共子序列的长度
    private int lcs(String s1, String s2, int m, int n) {

        if (m < 0 || n < 0) {
            return 0;
        }

        if (memo[m][n] != - 1) {
            return memo[m][n];
        }

        int res;
        if (s1.charAt(m) == s2.charAt(n)) {
            res = 1 + lcs(s1, s2, m - 1, n - 1);
        } else {
            res = Math.max(lcs(s1, s2, m - 1, n),
                    lcs(s1, s2, m , n - 1));
        }

        return memo[m][n] = res;
    }

}
```

```
// DP：躲避边界条件
public class Solution1143_2 {

    public int longestCommonSubsequence(String s1, String s2) {

        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("illegal argument!");
        }

        int m = s1.length();
        int n = s2.length();

        int[][] memo = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    memo[i][j] = 1 + memo[i - 1][j - 1];
                } else {
                    memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
                }
            }
        }

        return memo[m][n];
    }

}
```


## 48、LRU Cache

```
import java.util.LinkedHashMap;

/**
 * LRU Cache 146：
 */
class Solution146 {

    int cap;
    LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();

    public Solution146(int capacity) {
        this.cap = capacity;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        // 将 key 变为最近使用
        makeRecently(key);
        return cache.get(key);
    }

    public void put(int key, int val) {
        if (cache.containsKey(key)) {
            // 修改 key 的值
            cache.put(key, val);
            // 将 key 变为最近使用
            makeRecently(key);
            return;
        }

        if (cache.size() >= this.cap) {
            // 链表头部就是最久未使用的 key
            int oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);
        }
        // 将新的 key 添加链表尾部
        cache.put(key, val);
    }

    private void makeRecently(int key) {
        int val = cache.get(key);
        // 删除 key，重新插入到队尾
        cache.remove(key);
        cache.put(key, val);
    }
}
```


## 49、反转数字

```
/**
 * 关键点是如何判断溢出。
 * 推荐解答用的是用long类型存储结果，如果结果大于0x7fffffff或者小于0x80000000就溢出
 * 我的解法是每次计算新的结果时，再用逆运算判断与上一次循环的结果是否相同，不同就溢出
 */
public class Solution_1 {

    public int reverse(int x) {
        int res=0;
        while(x!=0){
            //最后一位
            int tail=x%10;
            int newRes=res*10+tail;
            //如果newRes-tail)/10!=res说明产生了溢出
            if((newRes-tail)/10!=res)
                return 0;
            res=newRes;
            x=x/10;
        }
        return res;
    }
}
```


## 50、生产者消费者

```
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution_2 {

    public class Message {
        private int code;
        private String msg;
//        Handler target;

        public Message() { }
        public Message(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
        public int getCode() {
            return code;
        }
        public void setCode(int code) {
            this.code = code;
        }
        public String getMsg() {
            return msg;
        }
        public void setMsg(String msg) {
            this.msg = msg;
        }
    }


    public interface IMessageQueue {
        Message next() throws InterruptedException;
        void enqueueMessage(Message message) throws InterruptedException;
    }

    public class MessageQueue implements IMessageQueue {
        private final BlockingQueue<Message> queue;
        public MessageQueue(int cap) {
            this.queue = new LinkedBlockingQueue<>(cap);
        }
        public Message next() throws InterruptedException {
            return queue.take();
        }
        public void enqueueMessage(Message message) {
            try {
                queue.put(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class MessageQueue1 implements IMessageQueue {

        private Queue<Message> queue;
        private final AtomicInteger integer = new AtomicInteger(0);
        private volatile int count;
        private final Object BUFFER_LOCK = new Object();
        public MessageQueue1(int cap) {
            this.count = cap;
            queue = new LinkedList<>();
        }

        @Override
        public Message next() throws InterruptedException {
            synchronized (BUFFER_LOCK) {
                while (queue.size() == 0) {
                    BUFFER_LOCK.wait();
                }
                Message message = queue.poll();
                BUFFER_LOCK.notifyAll();
                return message;
            }
        }

        @Override
        public void enqueueMessage(Message message) throws InterruptedException {
            synchronized (BUFFER_LOCK) {
                while (queue.size() == count) {
                    BUFFER_LOCK.wait();
                }
                queue.offer(message);
                BUFFER_LOCK.notifyAll();
            }
        }
    }

}
```
