# 排序算法（必备）

## 1、选择

```
/**
 * 从数组中选择最小的元素，将它与数组的第一个元素交换位置。再从数组剩下的元素中
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
 * 从左到右不断交换相邻逆序的元素，在一轮循环之后，可以让未排序的最大元素上浮到右侧。
 * 优化：在一轮循环中，如果没有发生交换，那么说明数组已经是有序的，此时可以直接退出。
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

    // 归并方法：归并方法将数组中两个已经排序的部分归并成一个
    protected void merge(T[] nums, int l, int m, int h) {

        int i = l, j = m + 1;

        // 将数据复制到辅助数组
        for (int k = l; k <= h; k++) {
            aux[k] = nums[k]; 
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
        // 1、自顶向下归并排序
        // 将一个大数组分成两个小数组去求解。
        // 因为每次都将问题对半分成两个子问题，这种对半分的算法复杂度一般为 O(NlogN)。
        aux = (T[]) new Comparable[nums.length];
        sort(nums, 0, nums.length - 1);

        // 2、自底向上归并排序
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
 * 实现思路：归并排序将数组分为两个子数组分别排序，并将有序的子数组归并使得整个数组有序；
 * 快速排序通过一个切分元素将数组分为两个子数组，左子数组小于等于切分元素，
 * 右子数组大于等于切分元素，将这两个子数组排序也就将整个数组排序了。
 *
 * 快速排序是原地排序，不需要辅助数组，但是递归调用需要辅助栈。
 * 快速排序最好的情况下是每次都正好将数组对半分，这样递归调用次数才是最少的。
 * 这种情况下比较次数为 CN=2CN/2+N，复杂度为 O(NlogN)。
 *
 * 最坏的情况下，第一次从最小的元素切分，第二次从第二小的元素切分，如此这般。
 * 因此最坏的情况下需要比较 N2/2。
 * 为了防止数组最开始就是有序的，需要在进行快速排序时随机打乱数组。
 */
public class QuickSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] nums) {
        shuffle(nums);
        sort(nums, 0, nums.length - 1);
    }

    private void sort(T[] nums, int l, int h) {
        if (h <= l) {
            return;
        }
        
        int j = partition(nums, l, h);
        sort(nums, l, j - 1);
        sort(nums, j + 1, h);
    }

    private void shuffle(T[] nums) {
        List<Comparable> list = Arrays.asList(nums);
        Collections.shuffle(list);
        list.toArray(nums);
    }

    // 取 nums[l] 作为切分元素，然后从数组的左端向右扫描直到找到第一个
    // 大于等于它的元素，再从数组的右端向左扫描找到第一个小于它的元素，
    // 交换这两个元素。不断进行这个过程，就可以保证左指针 i 的左侧元素
    // 都不大于切分元素，右指针 j 的右侧元素都不小于切分元素。当两个
    // 指针相遇时，将切分元素 nums[l] 和 nums[j] 交换位置。此时的j就是切分元素
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
 * 2、三数取中：
 * 最好的情况下是每次都能取数组的中位数作为切分元素，但是计算中位数
 * 的代价很高。一种折中方法是取 3 个元素，并将大小居中的元素作为切分元素。
 *
 * 3、三向切分：
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
 * 实现思路：把最大元素和当前堆中数组的最后一个元素交换位置，并且不删除它，
 * 那么就可以得到一个从尾到头的递减序列，从正向来看就是一个
 * 递增序列，这就是堆排序。
 *
 * 大顶堆实现堆排序：
 * 1、构建初始堆，先将待排序列构成一个大顶堆：序列对应一个完全二叉树，从最后一个分支结点（n/2）开始，
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
 * 4、如果当前节点的位置k小于下沉后的位置j时，则交换k与j的值，完成这一次的下沉操作。
 * 5、更新当前节点的位置为j，如果当前节点还有左子节点则又会进入while循环体进行上述的下沉操作。
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
        for (int k = N / 2; k >= 1; k--) {
            sink(nums, k, N);
        }

        while (N > 1) {
            // 2、将堆顶元素与堆尾元素交换，并从待排序列中移除堆尾元素。
            swap(nums, 1, N--);
            // 3、使用下沉操作下沉堆顶元素以重新构建大顶堆。
            sink(nums, 1, N);
        }
    }

    // 当当前节点有左子节点就进行下沉操作：首先如果当前节点有右子节点且左子节点小于右子节点时，
    // 下沉后的位置取右子节点的位置，然后如果当前节点值小于下沉后的值时，则两两交换，
    // 完成这一次的下沉操作，最后更新当前节点位置为下沉后的位置，如此反复即可。
    private void sink(T[] nums, int k, int N) {
        // 1）、仅当当前节点有左子节点时进入while循环体。
        while (2 * k <= N) {
            // 2）、设立下沉后的位置为j，默认为左子节点。
            int j = 2 * k;
            
            // 3）、如果当前节点有右子节点且左子节点小于右子节点时，下沉后的位置j取右子节点的位置（j++）。
            if (j < N && less(nums, j, j + 1)) {
                j++;
            }
            
            // 4）、如果当前节点值小于下沉后的值时，交换k与j的值，完成这一次的下沉操作。
            if (!less(nums, k, j)) {
                break;
            }
            swap(nums, k, j);
            
            // 5）、更新当前节点的位置为j，如果当前节点还有左子节点则又会进入while循环体进行上述的下沉操作。
            k = j;
        }
    }

    private boolean less(T[] nums, int i, int j) {
        return nums[i].compareTo(nums[j]) < 0;
    }
}
```


# 高频算法（必备）

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
 * 
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

    // 时间复杂度为 O(len(s))，空间复杂度为 O(len(charset))
    public int lengthOfLongestSubstring(String s) {

        // 1、初始化一个存储ascill字符集的数组
        int[] freq = new int[256];

        // 2、初始化滑动窗口：[0, -1]，窗口大小为0 & 记录滑动窗口最大值的变量
        int l = 0, r = -1;
        int res = 0;

        // 3、当左边界小于字符串长度时，维护滑动窗口
        while (l < s.length()) {
            // 1）、如果右边界+1小于字符串长度 & 右边界+1的出现频次为0，
            // 则将右边界+1,同时将右边界的出现频次+1
            if (r + 1 < s.length() && freq[s.charAt(r + 1)] == 0) {
                freq[s.charAt(++r)]++;
            } else {
                // 2）、否则，将左边界的出现频次-1，同时左边界+1
                freq[s.charAt(l++)]--;
            }

            // 3）、更新维护滑动窗口的大小
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

    // 时间复杂度：O(len(s))，空间复杂度：O(charset)
    public int lengthOfLongestSubstring(String s) {

        // 1、初始化一个存储ascill字符集的数组用来记录元素的出现频次
        int[] freq = new int[256];

        // 2、初始化滑动窗口：[0, -1] & 记录窗口最大值的变量
        int l = 0, r = -1;
        int res = 0;

        // 3、维护滑动窗口：左边界小于数组长度 || 右边界+1小于数组长度
//        while (l < s.length())
        while (r + 1 < s.length()) {
            // 1)、如果右边界+1小于数组长度 & 右边界+1的出现频次为0，
            // 则将右边界+1，并将其出现频次+1
            if (r + 1 < s.length() && freq[s.charAt(r + 1)] == 0) {
                freq[s.charAt(++r)]++;
            } else {
                // 2）、否则，将左边界的出现频次-1，并将左边界+1
                freq[s.charAt(l++)]--;
            }

            // 3）、更新滑动窗口的最大值
            res = Math.max(res, r - l + 1);
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println((new Solution3_2()).lengthOfLongestSubstring( "abcabcbb" ));
    }

}
```

```
/**
 * l 每次可以向前飞跃，而不仅仅是 +1，以确保滑动窗口中不存在重复的字符串，
 * 但为了获得这个飞跃值，每次都要遍历一次当前滑动窗口的大小。
 *
 * 时间换空间：
 *      时间复杂度：O(r-l+1) * len(s))
 *      空间复杂度：O(1)
 */
public class Solution3_3 {

    // 时间换空间的解法：时间复杂度：O(len(s)) * O(r-l+1)，空间复杂度：O(1)
    public int lengthOfLongestSubstring(String s) {

        // 1、初始化滑动窗口：[0，0) 由于后面要使用 s.charAt(r)，所以 r 需要从 0 开始 & 记录窗口最大值的变量
        int l = 0, r = 0;
        int res = 0;

        // 2、注意：这里的r不需要+1了
        while (r < s.length()) {

            // 1)、当前滑动窗口右边界的元素是否在滑动窗口中存在重复元素，
            // 有则返回离左边界最近的重复元素下标
            int index = findInDuplicate(s, l, r);

            // 2）、如果存在重复元素，则更新滑动窗口左边界为重复元素下标+1，以减少可能的冗余遍历（核心）
            if (index != -1) {
                l = index + 1;
            }

            // 3）、更新滑动窗口最大值
            res = Math.max(res, r - l + 1);

            // 4）、滑动窗口右边界+1
            r++;
        }

        return res;
    }

    /**
     * 判读当前窗口新的右边界是否已现在滑动窗口中，有则返回离左边界最近的下标（即飞跃值）
     */
    private int findInDuplicate(String s, int l, int r) {
        for (int i = l; i < r; i++) {
            if (s.charAt(i) == s.charAt(r)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println((new Solution3_3()).lengthOfLongestSubstring( "abcabcbb" ));
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

    // 滑动窗口 + 优化：使用记录上一次滑动窗口右边界下标的数组，以实现找到重复元素时左边界的飞跃
    // 时间复杂度：O(len(s)) 空间复杂度：O(len(charset))
    public int lengthOfLongestSubstring(String s) {

        // 1、初始化记录上一次的滑动窗口右边界下标，以实现找到重复元素时左边界的飞跃
        int[] last = new int[256];
        Arrays.fill(last, -1);

        // 2、初始化滑动窗口：[0, -1] & 记录窗口最大值的变量
        int l = 0, r = -1;
        int res = 0;

        // 3、维护滑动窗口
        while (r + 1 < s.length()) {
            // 1）、右边界+1
            r++;

            // 2）、如果当前右边界的元素已经出现过，则更新左边界为 左边界 与 右边界元素上次出现的下标+1 两者中的最大值
            if (last[s.charAt(r)] != -1) {
                l = Math.max(l, last[s.charAt(r)] + 1);
            }

            // 3）、维护滑动窗口的大小
            res = Math.max(res, r - l + 1);

            // 4）、保存右边界的下标
            last[s.charAt(r)] = r;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println((new Solution3_4()).lengthOfLongestSubstring( "abcabcbb" ));
    }

}
```


## 3、盛最多水的容器

```
/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，
 * 容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 * 示例：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 */
public class Solution11 {

    public int maxArea(int[] height) {

        // 1、初始化记录窗口左右边界的位置 & 最大面积
        int l = 0, r = height.length - 1;
        int max = 0;

        // 2、当左边界小于右边界时
        while (l < r) {
            // 1）、统计当前窗口的面积大小：左右边界中较低的高 * 窗口大小
            int cur = Math.min(height[l], height[r]) * (r - l);

            // 2）、维护窗口最大面积
            max = Math.max(max, cur);

            // 3）、如果当前窗口左边界的高度小于右边界的高度，则左边界往里移动1步，否则右边界往里移动一步
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,8,6,2,5,4,8,3,7};
        System.out.println(new Solution11().maxArea(arr));
    }
}
```


## 4、合并区间

```
/**
 * 首先根据左端点排序，这样只需比较每一次合并后区间的右端点和一个区间的左端点即可。
 *
 * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 */
public class Solution56 {

    public int[][] merge(int[][] intervals) {

        // 1、异常边界处理
        if (intervals == null || intervals.length == 0) {
            return new int[0][];
        }

        // 2、初始化一个记录区间的列表
        List<int[]> list = new ArrayList<>();

        // 3、根据每一个区间的左边界排序
        Arrays.sort(intervals, (o1, o2) -> o1[0]-o2[0]);

        // 4、遍历每一个区间
        int i = 0;
        while (i < intervals.length) {
            // 1）、拿到当前区间的左右边界
            int l = intervals[i][0], r = intervals[i][1];

            // 2）、当下一个区间存在 & 下一个区间的左边界小于当前区间的右边界，
            // 则更新当前区间右边界为 当前区间右边界与下一个区间右边界 两者中的最大值
            i++;
            while (i < intervals.length && intervals[i][0] <= r){
                r  = Math.max(r, intervals[i][1]);
                i++;
            }

            // 3）、添加合并后的区间到列表
            list.add(new int[]{l, r});
        }

        return list.toArray(new int[0][]);
    }
    
}
```


## 5、合并两个有序数组

```
/**
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * 说明: 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *  
 * 示例:
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * 输出: [1,2,2,3,5,6]
 */
public class Solution88 {

    // 双指针：为防止元素被覆盖，双指针需从后往前遍历
    // 时间复杂度：O(m + n)，空间复杂度：O（1）
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        // 1、初始化数组1、2的指针：指向数组有效尾部 & 合并数组指针指向合并数组有效尾部
        int index1 = m - 1, index2 = n - 1;
        int indexMerge = m + n - 1;

        // 2、当数组1或2的指针有效，即大于等于0时
        while (index1 >= 0 || index2 >= 0) {
            if (index1 < 0) {
                // 3）、如果数组1处的指针小于0，则更新数组1合并指针位置的值为数组2指针处的值，
                // 并将数组2的指针和合并指针都-1
                nums1[indexMerge--] = nums2[index2--];
            } else if (index2 < 0) {
                // 4）、否则，更新数组1合并指针位置的值为数组1处指针的值，并将数组1的指针和合并指针都-1
                nums1[indexMerge--] = nums1[index1--];
            } else if (nums1[index1] > nums2[index2]) {
                // 1）、如果数组1的值大于数组2的值，则更新数组1合并指针位置的值为数组1指针处的值，
                // 并将数组1的指针和合并指针都-1
                nums1[indexMerge--] = nums1[index1--];
            } else {
                // 2）、否则，更新数组1合并指针位置的值为数组2指针处的值，并将数组2的指针和合并指针都-1
                nums1[indexMerge--] = nums2[index2--];
            }
        }
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] arr2 = new int[]{2, 5, 6};
        new Solution88().merge(arr1, 3, arr2, arr2.length);
    }

}
```


## 6、找第K个最大的元素

```
/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后
 * 的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 *
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 * 1、排序
 * O(nlogn)
 * O(1)
 * 2、堆排序：空间换时间
 * O(nlogk)
 * O(k)
 * 3、利用快排 partition 中，将 pivot 放置在了正确的位置上的性质。
 * O(n)
 * O(1)
 */
public class Solution215 {


    public int findKthLargest(int[] nums, int k) {

        // 1、取k为第k个最小的元素 & 初始化窗口左右边界
        k = nums.length - k;
        int l = 0, r = nums.length - 1;

        // 2、当窗口的长度有效，即左边界小于右边界时
        while(l < r) {
            // 3、利用快排的partition函数找到切分元素对应的位置j，也就是第j个最小的元素
            int j = partition(nums, l, r);
            // 4、如果找到的第j个最小元素的下标就是目标元素的下标，则break返回元素即可
            if (j == k) {
                break;
            } else if (j < k) {
                // 如果小于，则更新窗口左边界为j+1,否则更新窗口右边界为j-1
                l = j + 1;
            } else {
                r = j - 1;
            }
        }

        return nums[k];
    }

    private int partition(int[] nums, int l, int r) {

        // 1）、初始化窗口的左右边界，注意右边界要+1，便于后面复用通用的代码逻辑
        int i = l, j = r + 1;

        // 2）、取第一个元素为切分元素
        int v = nums[l];

        // 3）、开启一个while无限循环来找切分元素
        while (true) {
            // 4）、从左到右找到第一个比切分元素大的元素 & 这个元素小于右边界
            while (nums[++i] < v && i < r) ;
            // 5）、从右到左找到第一个比切分元素小的元素 & 这个元素大于左边界
            while (nums[--j] > v && j > l) ;
            // 6）、如果比切分元素大的元素与比切分元素小的元素相遇了，则break
            if (i >= j) {
                break;
            }

            // 7）、否则，交互i和j的值
            swap(nums, i, j);
        }

        // 8）、最后，交换l和j的值
        swap(nums, l, j);

        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public static void main(String[] args) {
        // [3,2,1,5,6,4] 和 k = 2
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        System.out.println(new Solution215().findKthLargest(nums, 2));
    }

}
```


## 7、反转字符串

```
/**
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 * 不要创建另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 *
 * 示例 1：
 * 输入：["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 * 
 * 示例 2：
 * 输入：["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 */
public class Solution344 {

    // 1、递归解法会占用递归栈空间
    public String reverseString(String s) {
        // 1、初始化数组长度 & 当长度<=1说明不需要反转
        int len = s.length();
        if (len <=1 ) {
            return s;
        }

        // 2、在递归的过程中不断反转当前字符串的左子字符串与右子字符串，注意右在前，左在后，就是为了实现反转的效果
        String leftStr = s.substring(0, len / 2);
        String rightStr = s.substring(len / 2, len);
        return reverseString(rightStr) + reverseString(leftStr);
    }

    // 2、双指针解法，不断交换数组当前的首元素与尾元素，一个for循环搞定
    // 时间复杂度：O(n)，空间复杂度：O(1)
    public void reverseString(char[] nums) {
        // 1、初始化数组长度
        int len = nums.length;

        // 2、在for循环中不断交换当前的首元素与尾元素
        for (int l = 0, r = len - 1; l < r; ++l, --r) {
            char t = nums[l];
            nums[l] = nums[r];
            nums[r] = t;
        }
    }

    public static void main(String[] args) {
        // ["h","e","l","l","o"]
        String s = "hello";
        System.out.println(new Solution344().reverseString(s));
    }
}
```


## 8、数组中只出现一次的数字

```
/**
 * 数组中只出现一次的数字：一个整型数组里除了两个数字之外，
 * 其他的数字都出现了两次，找出这两个数。
 */
public class Solution_1 {

    // 1、哈希法：遍历数组，用map记录所有元素出现的次数，然后再遍历数组，找出只出现一次的数。
    // 时间复杂度：O(n), 空间复杂度：O(n)
    // 2、位运算：两个不相等的元素在位级表示上必定会有一位存在不同，将数组的所有元素异或得到的结果
    // 为不存在重复的两个元素异或的结果。
    // diff &= -diff 得出 diff 最低位的1，也就是不存在重复的两个元素在位级表示上最右侧不同
    // 的那一位，利用这一位就可以将两个元素区分开来。
    // 时间复杂度：O(n), 空间复杂度：O(1)
    public void FindNumsAppearOnce(int[] nums, int num1[], int num2[]) {

        // 1、利用 0 ^ x = x 和 x ^ y = 1、x ^ x = 0 的性质来遍历数组，
        // 最后得到的值就是两个不同元素异或的值
        int diff = 0;
        for(int num:nums) {
            diff ^= num;
        }

        // 2、利用 x & -x 得到x最低位的1
        diff &= -diff;

        // 3、遍历数组：利用 最低位1 & 当前元素是否为0来分离两个不同的元素
        for(int num:nums) {
            // 注意这里加括号
            if ((diff & num) == 0) {
                num1[0] ^= num;
            } else {
                num2[0] ^= num;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 2, 1, 4};
        int[] num1 = new int[]{0};
        int[] num2 = new int[]{0};
        new Solution_1().FindNumsAppearOnce(nums, num1, num2);
    }
}
```


## 9、数组中出现次数超过一半的数字

```
/**
 * 多数投票问题，可以利用 Boyer-Moore 投票算法
 * 来解决这个问题，使得时间复杂度为 O(n)。
 *
 * 使用 cnt 来统计一个元素出现的次数，当遍历到的元素和统计元素相等时，
 * 令 cnt++，否则令 cnt--。如果前面查找了 i 个元素，且 cnt == 0，
 * 说明前 i 个元素没有 majority，或者有 majority，但是出现的次数
 * 少于 i / 2 ，因为如果多于 i / 2 的话 cnt 就一定不会为 0 。
 * 此时剩下的 n - i 个元素中，majority 的数目依然多于 (n - i) / 2，
 * 因此继续查找就能找出 majority。
 */
public class Solution_2 {

    // 1、哈希表：用hashMap统计所有元素出现的次数，时间复杂度O(n), 空间复杂度O(n)
    // 2、排序：如果将数组 nums 中的所有元素按照单调递增或单调递减的顺序排序，
    // 那么下标为n/2的元素一定是众数。时间复杂度O(nlog(n))，空间复杂度O(logn)(自己实现堆排序O(1))
    // 3、Boyer Moore投票算法：时间复杂度O(n), 空间复杂度O(1)
    public int majorityElement(int[] nums) {

        // 1、初始化出现次数和候选众数
        int count = 0;
        Integer candidate = null;

        // 2、遍历数组
        for (int num : nums) {
            // 3、如果出现次数为0，则更新当前的候选众数
            if (count == 0) {
                candidate = num;
            }
            // 4、然后更新当前候选众数的出现次数：当前元素与候选众数相等，则+1，否则-1
            count += (num == candidate) ? 1 : -1;
        }

        // 5、最后的候选众数就是真正的众数
        // 为什么？如果候选众数不是真正的众数，则真正众数会和其它非候选人一起反对，此时候选众数就会下台（count==0），
        // 如果候选众数是真正的众数，则真正的众数就会支持，其它非获选人会一起反对，此时候选众数最后一定会当选。
        return candidate;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,2,1,1,1,2,2};
        System.out.println(new Solution_2().majorityElement(nums));
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
```


## 11、两数之和

```
/**
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 *
 * 说明:
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

    // 1、双层遍历每一种可能的组合：时间复杂度 O(n^2)。（没有利用有序）
    public int[] twoSum(int[] arr, int target) {
        // 1、有效性判断
        int n = arr.length;
        if (n < 2) {
            throw new IllegalArgumentException("length of arr is illegal");
        }

        // 2、双层for循环查找每一种可能的组合
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] + arr[j] == target) {
                    int[] res = {i + 1, j + 1};
                    return res;
                }
            }
        }

        throw new IllegalArgumentException("no target!");
    }

    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        System.out.println(Arrays.toString(new Solution167().twoSum(arr, 9)));
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

    // 2、循环 + 二分查找：在有序数组中查找 target - arr[i], 时间复杂度O(nlogn)
    public int[] twoSum(int[] arr, int target) {
        // 1、异常边界处理
        int n = arr.length;
        if (n < 2) {
            throw new IllegalArgumentException("len of arr is illegal!");
        }

        // 2、循环 + 二分查找：在有序数组中查找target-nums[i]
        for (int i = 0; i < n; i++) {
            int j = binarySearch(arr, i + 1, n - 1, target - arr[i]);
            if (j != -1) {
                int[] res = {i + 1, j + 1};
                return res;
            }
        }

        // 3、如果没有找到，则抛出异常
        throw new IllegalArgumentException("no target!");
    }

    private int binarySearch(int[] arr, int l, int r, int target) {
        // 1、左右边界异常处理
        int n = arr.length;
        if (l < 0 || l > n - 1) {
            throw new IllegalArgumentException("l is illegal!");
        }

        if (r < 0 || r > n - 1) {
            throw new IllegalArgumentException("r is illegal!");
        }

        // 2、二分搜索
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr[m] == target) {
                return m;
            } else if (arr[m] < target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        System.out.println(Arrays.toString(new Solution167().twoSum(arr, 9)));
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

    // 3、对撞指针：使用左右边界索引，使用这2个索引不断往中间靠拢，时间复杂度O(n), 空间复杂度O(1)
    public int[] twoSum(int[] arr, int target) {
        // 1、异常边界处理
        int n = arr.length;
        if (n < 2) {
            throw new IllegalArgumentException("len of arr is illegal!");
        }

        // 2、对撞指针
        int l = 0, r = n - 1;
        while (l < r) {
            // 1）、如果两者之和与target相等，则返回
            if (arr[l] + arr[r] == target) {
                int[] res = {l + 1, r + 1};
                return res;
            } else if (arr[l] + arr[r] < target) {
                // 2）、如果两者之和小于target，则要增大左边界对应的值，即使l+1
                l ++;
            } else {
                // 3）、否则要减小右边界对应的值，即使r-1
                r --;
            }
        }

        throw new IllegalArgumentException("no target!");
    }

    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        System.out.println(Arrays.toString(new Solution167_3().twoSum(arr, 9)));
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
public class Solution1_2 {

    // 数组无序时：查找表(map)，将所有元素放入查找表，之后对每一个元素 a，
    // 查找 target - a 是否存在, 时间复杂度O(n)
    public int[] twoSum(int[] arr, int target) {
        // 1、边界异常处理
        int n = arr.length;
        if (n < 2) {
            throw new IllegalArgumentException("len of arr is illegal");
        }

        // 2、使用map记录每一个元素对应的值与下标
        HashMap<Integer, Integer> record = new HashMap<>();
        for (int i = 0; i < n; i++) {
            record.put(arr[i], i);
        }

        // 3、遍历数组：当map中包含target-nums[i]这个key &
        // 当前这个key的值不是当前元素的下标时说明已找到
        for (int i = 0; i < n; i++) {
            int j = target - arr[i];
            if (record.containsKey(j) && record.get(j) != i) {
                int[] res = {i, record.get(j)};
                return res;
            }
        }

        throw new IllegalArgumentException("no target!");
    }

    public static void main(String[] args) {
        int[] arr = {2, 7 , 11, 15};
        System.out.println(Arrays.toString(new Solution1_2().twoSum(arr, 9)));
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

    // 层次遍历类似于BFS，在while循环中使用队列 + 每层单独一个list
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 1、创建一个双层列表用于保存结果, 如果root为null，则直接返回
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        // 2、使用队列先进先出的特性：创建一个队列, 并首先将根节点入队
        LinkedList<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.addLast(new Pair<>(root, 0));

        // 3、当队列不为空时
        while (!queue.isEmpty()) {
            // 1）、取出队首元素，如果当前元素层次等于res的层次，则说明还有新的子节点要添加，
            // 此时需要添加新的列表到res中
            Pair<TreeNode, Integer> pair = queue.removeFirst();
            TreeNode node = pair.fst;
            int level = pair.snd;

            if (level == res.size()) {
                res.add(new ArrayList<>());
            }

            // 2）、异常边界处理：确保res的大小大于当前元素的层次
            assert res.size() > level;

            // 3）、添加当前元素节点值到res对应层次中
            res.get(level).add(node.val);

            // 4）、如果当前节点还有左右子节点，则添加到队列尾部
            if (node.left != null) {
                queue.addLast(new Pair<>(node.left, level + 1));
            }

            if (node.right != null) {
                queue.addLast(new Pair<>(node.right, level + 1));
            }
        }

        return res;
    }

    public static void main(String[] args) {

    }

}
```


## 13、二叉树的左视图

```
/**
 * 题目描述：给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 一、BFS
 * 思路： 利用 BFS 进行层次遍历，记录下每层的最后一个元素。
 * 时间复杂度： O(N)，每个节点都入队出队了 1 次。
 * 空间复杂度： O(N)，使用了额外的队列空间。
 *
 * 二、DFS （时间100%）
 * 思路： 我们按照 「根结点 -> 右子树 -> 左子树」 的顺序访问，
 * 就可以保证每层都是最先访问最右边的节点的。
 *
 * （与先序遍历 「根结点 -> 左子树 -> 右子树」 正好相反，先序
 * 遍历每层最先访问的是最左边的节点）
 *
 * 时间复杂度： O(N)，每个节点都访问了 1 次。
 * 空间复杂度： O(N)，因为这不是一棵平衡二叉树，二叉树的深度
 * 最少是logN, 最坏的情况下会退化成一条链表，深度就是 N，
 * 因此递归时使用的栈空间是 O(N) 的。
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

    // 1、BFS: 利用 BFS 进行层次遍历，记录下每层的最后一个元素。
    // 时间复杂度： O(N)，每个节点都入队出队了 1 次。
    // 空间复杂度： O(N)，使用了额外的队列空间。
    public List<Integer> rightSideView(TreeNode root) {
        // 1、创建一个列表用于保存返回值，如果root等于null，则返回
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        // 2、使用队列先进先出的特性：创建一个队列（入队出队每一层的所有节点），并将根节点入队
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // 3、当队列不为空时
        while (!queue.isEmpty()) {

            // 1）、遍历当前层次对应的队列，如果当前节点有左右节点则入队，如果遍历的当前节点
            // 是当前层的最后一个节点则放入结果列表
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }

                if (i == size - 1) {
                    // 只将当前层的最后一个节点放入结果列表
                    res.add(node.val);
                }
            }
        }
        return res;
    }

    // 1、创建一个全局返回列表，便于操作
    List<Integer> res = new ArrayList<>();

    // 2、DFS：我们按照 「根结点 -> 右子树 -> 左子树」 的顺序访问，
    // 就可以保证每层都是最先访问最右边的节点的。
    // 时间复杂度： O(N)，每个节点都访问了 1 次。
    // 空间复杂度： O(N)，因为这不是一棵平衡二叉树，二叉树的深度
    // 最少是logN, 最坏的情况下会退化成一条链表，深度就是 N，
    // 因此递归时使用的栈空间是 O(N) 的。
    public List<Integer> rightSideView2(TreeNode root) {
        dfs(root, 0); // 从根节点开始访问，根节点深度是0
        return res;
    }

    private void dfs(TreeNode root, int depth) {
        // 1）、异常处理，如果根节点为空，直接返回
        if (root == null) {
            return;
        }

        // 2）、记录下当前层的访问节点
        if (depth == res.size()) {
            res.add(root.val);
        }

        // 3）、深度+1，保证每一层只记录一个节点
        depth++;

        // 4）、保证最先访问右子节点
        dfs(root.right, depth);
        dfs(root.left, depth);
    }

}
```

## 14、括号匹配

```
/**
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
public class Solution20 {

    public boolean isValid(String s) {
        // 1、创建一个栈，注意 stack 中存的是字符：Character
        Stack<Character> stack = new Stack<>();

        // 2、遍历字符串
        for (int i = 0; i < s.length(); i++) {
            // 1）、在 Java 中字符以 '' 标记，如果当前字符是左括号则放入栈中
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                // 2）、如果当前字符是右括号而栈中没有左括号则匹配失败,
                // 这里依次判断三种括号匹配的情况即可
                if (stack.isEmpty()) {
                    return false;
                }

                Character topChar = stack.pop();
                if (c == ')' && topChar != '(') {
                    return false;
                } else if (c == ']' && topChar != '[') {
                    return false;
                } else if (c == '}' && topChar != '{') {
                    return false;
                }
            }
        }

        // 3、栈中没有元素才算匹配成功
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new Solution20().isValid("(({[{}]}))"));
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
        // 1、创建一个返回列表
        List<Integer> res = new ArrayList<>();
        inorderTraversal(root, res);
        return res;
    }

    // 2、递归实现中序遍历：左根右
    private void inorderTraversal(TreeNode root, List<Integer> res) {
        if (root != null) {
            inorderTraversal(root.left, res);
            res.add(root.val);
            inorderTraversal(root.right, res);
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
        // 1、创建一个返回列表，如果根节点为null，直接返回
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        // 2、创建一个Stack<Command>对象模拟系统栈实现非递归二叉树的中序遍历：倒过来看为左根右
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
        // 1、创建一个返回列表
        ArrayList<Integer> res = new ArrayList<>();
        preorderTraversal(root, res);
        return res;
    }

    // 2、递归实现前序遍历：根左右
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
        // 1、创建一个返回列表，如果根节点为空，则直接返回
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        // 2、使用Stack<Command>对象模拟系统栈实现二叉树的非递归前序遍历：
        // 倒过来看为根左右，为什么？因为栈是先进后出的
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
        // 1、创建一个返回列表
        ArrayList<Integer> res = new ArrayList<>();
        postorderTraversal(root, res);
        return res;
    }

    // 2、递归实现二叉树的后续遍历：左右根
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

    class Command {
        String val;
        TreeNode node;
        public Command(String s, TreeNode node) {
            this.val = s;
            this.node = node;
        }
    }

    public List<Integer> postOrderTraversal(TreeNode root) {
        // 1、创建一个返回列表，如果根节点为null，则直接返回
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        // 2、使用栈Stack<Command>模拟系统栈实现二叉树的非递归遍历：倒过来看为左右根，为什么？因为栈是先进后出的
        Stack<Command> stack = new Stack<>();
        stack.push(new Command("go", root));
        while (!stack.isEmpty()) {
            Command command = stack.pop();

            if (command.val.equals("print")) {
                res.add(command.node.val);
            } else if (command.val.equals("go")) {

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
/**
 * 使用双栈（数据栈+最小值栈）+min实现最小值栈
 */
public class Solution155 {

    private Stack<Integer> mDataStack;
    private Stack<Integer> mMinStack;
    private int min;

    public Solution155() {
        // 1、创建一个数据栈保存数据，最小值栈的栈顶维护最小值，min变量维护最小值
        mDataStack = new Stack<>();
        mMinStack = new Stack<>();
        min = Integer.MAX_VALUE;
    }

    // 2、放入元素时保持栈顶的元素为最小值
    public void push(int e) {
        mDataStack.push(e);
        min = Math.min(min, e);
        mMinStack.push(min);
    }

    // 3、弹出元素时保持min记录的为最小值
    public void pop() {
        mDataStack.pop();
        mMinStack.pop();
        min = mMinStack.isEmpty() ? Integer.MAX_VALUE : mMinStack.peek();
    }

    // 4、查看最小值
    public int getMin() {
        return mMinStack.peek();
    }

    // 5、查看栈顶值
    public int top() {
        return mDataStack.peek();
    }

}
```


## 19、两个栈实现一个队列

```
/**
 * 用两个栈来实现一个队列，完成队列的 Push 和 Pop 操作。
 *
 * 1、in 栈用来处理入栈操作，out 栈用来处理出栈操作。
 * 一个元素进入 in 栈之后，出栈的顺序被反转。当元素要出栈时，
 * 需要先进入 out 栈，此时元素出栈顺序再一次被反转，因此出栈顺序
 * 就和最开始入栈顺序是相同的，先进入的元素先退出，这就是队列的顺序。
 *
 * 2、在in栈中写入，要输出时，将所有in栈数据移到out栈，然后只要out栈数据不为空，
 * 就可以一直取出，一旦为空，则再将in栈数据移到out栈中即可。
 */
public class Solution_1 {

    Stack<Integer> in = new Stack<>();
    Stack<Integer> out = new Stack<>();

    public void push(int node) {
        in.push(node);
    }

    // 1、要确保每次只有当out栈为空时才从in栈中pop值到out栈中，以防止来回倒
    public int pop() throws Exception {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }

        if (out.isEmpty()) {
            throw new Exception("queue is empty");
        }

        return out.pop();
    }

}
```


## 20、表达示求值

```
/**
 * 题目描述：请写一个整数计算器，支持加减乘三种运算和括号。
 *
 * (2*(3-4))*5：不断递归，先计算最里面括号的值，并保存在栈中，然后再计算外面括号的。
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

    // (2*(3-4))*5：不断递归，先计算最里面括号的值，并保存在栈中，然后再计算外面括号的
    public int solve(String s) {
        // 1、创建一个栈保存各部分计算的和
        Stack<Integer> stack = new Stack<>();

        // 2、创建sum记录当前部分的和，number记录当前数字，sign记录运算符号，默认为+号
        int sum = 0, number = 0;
        char sign = '+';

        // 3、遍历字符数组
        char[] charArray = s.toCharArray();
        for (int i = 0, n = charArray.length; i < n; i++) {

            // 1）、如果当前字符是左括号时使用递归去掉最外面括号，并求这个括号里面表示式的值
            char c = charArray[i];
            if (c == '(') {
                // 1）、初始化变量j记录右括号的下一个位置与括号对变量counterPar
                int j = i + 1;
                int counterPar = 1;
                // 2）、当括号对数大于0时，如果当前位置元素为左括号则括号对数+1，否则-1，然后移动变量j的位置
                while (counterPar > 0) {
                    if (charArray[j] == '(') {
                        counterPar++;
                    }
                    if (charArray[j] == ')') {
                        counterPar--;
                    }
                    j++;
                }
                // 3）、递归计算括号里面表达式的值
                number = solve(s.substring(i + 1, j - 1));
                // 4）、更新当前遍历的下标i：直接定位到右括号位置j-1处
                i = j - 1;
            }

            // 2）、如果当前字符是数字，则使用number记录当前数字
            if (Character.isDigit(c)) {
                number = number * 10 + c - '0';
            }

            // 3）、如果当前字符是运算符或者到表达式末尾时，就去计算上一个运算符，
            // 并把计算结果push进栈，然后保存新的运算符
            if (!Character.isDigit(c) || i == n - 1) {
                // 1）、如果是+，不要计算，push进去
                if (sign == '+') {
                    stack.push(number);
                } else if (sign == '-') {
                    // 2）、如果是-，push进去负的当前数
                    stack.push(-1 * number);
                } else if (sign == '*') {
                    // 3）、如果是×、÷，pop出一个运算数和当前数作计算
                    stack.push(stack.pop() * number);
                } else if (sign == '/') {
                    stack.push(stack.pop() / number);
                }
                // 4）、重置number为0，sign为c
                number = 0;
                sign = c;
            }
        }

        // 4、最后把栈中的结果求和即可
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_2().solve("(2*(3-4))*5"));
    }

}
```


## 21、删除倒数第N个节点

```
/**
 * 19：
 *      1、n从0计还是从1计。
 *      2、n不合法，负数或者大于链表长度如何处理（保证n合法）。
 *
 * O(n)
 * O(1)
 */
public class Solution19 {

    // 1、先遍历一遍计算链表长度；再遍历一遍得到待删除节点的前一个节点并删除待删除节点
    public ListNode removeNthFromEnd(ListNode head, int n) {

        // 1、使用虚拟头结点
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        // 2、先遍历一遍计算链表的长度
        int len = 0;
        for (ListNode cur = dummyHead.next; cur != null; cur = cur.next) {
            len++;
        }

        // 3、获取待删除节点的前一个节点cur
        int k = len - n;
        assert k >= 0;
        ListNode cur = dummyHead;
        for (int i = 0; i < k; i++) {
            cur = cur.next;
        }

        // 4、删除指定节点：前一个节点指向待删除节点的后一个节点
        cur.next = cur.next.next;

        return dummyHead.next;
    }

}
```

```
/**
 * 19：
 *      1、n从0计还是从1计。
 *      2、n不合法，负数或者大于链表长度如何处理（保证n合法）。
 *
 * O(n)
 * O(1)
 */
public class Solution19_2 {

    // 2、双指针：设立两个指针 p、q，他们的距离为n，当 q 遍历到 null 时，p.next 即为 delNode
    public ListNode removeNthFromEnd(ListNode head, int n) {

        // 1、设立虚拟头结点，便于后续循环的计算
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        // 2、创建双指针p、q
        ListNode p = dummyHead;
        ListNode q = dummyHead;

        // 3、先移动q：为后续获取待删除的前一个节点，需执行 n + 1 次遍历，而不是 n 次
        for (int i = 0; i < n + 1; i++) {
            assert q != null;
            q = q.next;
        }

        // 4、再同时移动q、p：当 q == null 时，p 即为待删除的前一个节点
        while (q != null) {
            q = q.next;
            p = p.next;
        }

        // 5、将待删除的前一个节点的指针指向后一个节点
        p.next = p.next.next;

        return dummyHead.next;
    }

}
```


## 22、合并两个有序链表

```
/**
 * 合并两个有序链表为一个有序链表：设立链表的虚拟头结点
 */
public class Solution21 {

    // 时间复杂度：O(n+m)，函数 mergeTwoList 至多只会递归调用每个节点一次。
    // 因此，时间复杂度取决于合并后的链表长度。
    // 空间复杂度：O(n+m)，递归调用 mergeTwoLists 函数时需要消耗栈空间，栈空间的大小
    // 取决于递归调用的深度。结束递归调用时 mergeTwoLists 函数最多调用 n+m 次。
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        // 1、当l1或l2为空时的直接返回另一个链表
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        // 2、判断 l1 和 l2 哪一个链表的头节点的值更小，然后递归地决定
        // 下一个添加到结果里的节点。如果两个链表有一个为空，递归结束
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
 * 题目描述：给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 */
public class Solution23 {

    // 1、优先队列：用容量为K的最小堆优先队列，把链表的头结点都放进去，然后出队当前
    // 优先队列中最小的，挂上链表，然后让 出队的那个节点指向的下一个节点 入队，
    // 如此反复，再出队当前优先队列中最小的，直到优先队列为空。
    // 时间复杂度:O(n*log(k))，n是所有链表中元素的总和，k是链表个数。
    public ListNode mergeKLists(ListNode[] lists) {

        // 1、异常处理：如果链表数组为null或长度为0，则直接返回null
        if (lists == null || lists.length == 0) {
            return null;
        }

        // 2、创建一个虚拟头结点与最小堆优先队列（o1.val - o2.val 为升序排序）
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);

        // 3、遍历链表数组：如果链表不为null，则添加到优先队列中
        for (ListNode list : lists) {
            if (list == null) {
                continue;
            }
            pq.add(list);
        }

        // 4、当优先队列不为空时
        while (!pq.isEmpty()) {
            // 1）、取出队首的最小节点，并将cur节点指向它，然后移动cur节点到下一个位置
            ListNode nextNode = pq.poll();
            cur.next = nextNode;
            cur = cur.next;

            // 2）、如果队首的最小节点的下一个节点不为null，则添加其下一节点到优先队列中
            if (nextNode.next != null) {
                pq.add(nextNode.next);
            }
        }

        return dummyHead.next;
    }

    // 2、分支法：分而治之，链表两两合并。时间复杂度:O(n*log(k))
    public ListNode mergeKLists2(ListNode[] lists) {
        // 1、异常处理：如果链表数组为null或长度为0，则直接返回null
        if (lists == null || lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {

        // 1、如果合并到左右指针都相等，则直接返回左指针处的链表
        if (left == right) {
            return lists[left];
        }

        // 2、分而治之：最后会递归得到2个单个链表，不断返回两两合并后的链表即可
        int mid = left + (right - left) / 2;
        ListNode l1 = merge(lists, left, mid);
        ListNode l2 = merge(lists, mid + 1, right);
        return mergeTwoLists(l1, l2);
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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
 *
 * 题目描述：给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序
 *
 * 1、链表分为已翻转部分+待翻转部分+未翻转部分
 * 2、每次翻转前，通过 k 次循环来确定翻转链表的范围
 * 3、需记录翻转链表前驱和后继，方便翻转完成后把已翻转部分和未翻转部分连接起来
 * 4、初始需要两个变量 pre 和 end，pre 代表待翻转链表的前驱，end 代表待翻转链表的末尾
 * 5、经过k次循环，end 到达末尾，记录待翻转链表的后继 next = end.next
 * 6、翻转链表，然后将三部分链表连接起来，然后重置 pre 和 end 指针，然后进入下一次循环
 * 7、特殊情况，当翻转部分长度不足 k 时，在定位 end 完成后，end==null，已经到达末尾，说明题目已完成，直接返回即可
 *
 * 时间复杂度：O(n*k)，最好的情况为O(n)，最差的情况为 O(n^2)
 * 空间复杂度：O(1)，除了几个必须的节点指针外，我们并没有占用其他空间
 */
public class Solution25 {

    // 时间复杂度：O(n*k)，最好情况为O(n), 最差情况为O(n^2)
    // 空间复杂度：O(1)
    public ListNode reverseKGroup(ListNode head, int k) {

        // 1、异常处理：如果头结点为null或下一个节点为null，则直接返回头结点
        if (head == null || head.next == null){
            return head;
        }

        // 2、创建一个虚拟头结点，并将其指向头结点
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        // 3、初始化pre和end都指向虚拟头结点，
        // pre表示前驱节点：每次待翻转链表的头结点的上一个节点，
        // end指每次待翻转的链表的尾节点
        ListNode pre = dummyHead;
        ListNode end = dummyHead;

        // 4、当待翻转链表的尾节点的下一个节点不为null时
        while (end.next != null) {
            // 1）、必须要循环k次循环找到待翻转链表的结尾，这里每次循环要判断end是否等于空，
            // 因为如果为空，end.next会报空指针异常。
            // 例子：dummyHead->1->2->3->4->5 若k为2，循环2次，end指向2
            for (int i = 0; i < k && end != null; i++){
                end = end.next;
            }

            // 2）、如果end等于null，即表示待翻转链表的节点数小于k，不进行翻转。
            if (end == null){
                break;
            }

            // 3）、创建后继节点next（方便后面链接链表），然后断开链表，
            // 再创建待翻转链表头部节点
            ListNode next = end.next;
            end.next = null;
            ListNode start = pre.next;

            // 4）、翻转链表，pre.next指向翻转后的链表
            // 例子：1->2 变成2->1。 dummyHead->2->1
            pre.next = reverse(start);

            // 5）、翻转后头节点变到最后，通过头结点的next指针把断开的链表重新链接。
            start.next = next;

            // 6）、更新pre和end为下次待翻转链表头结点的上一个节点，即start
            pre = start;
            end = start;
        }

        return dummyHead.next;
    }

    // 链表翻转
    public ListNode reverse(ListNode head) {
        // 1、异常处理：如果头结点为null或下一个节点为null，则直接返回头结点
        if (head == null || head.next == null){
            return head;
        }

        // 2、初始化前一个节点与当前节点，并定义下一个节点
        ListNode preNode = null;
        ListNode curNode = head;
        ListNode nextNode;

        // 3、当当前节点不为null时进行链表翻转
        while (curNode != null){
            // 1）、初始化nextNode
            nextNode = curNode.next;

            // 2）、将当前节点的next指针指向前一个节点
            curNode.next = preNode;

            // 3）、将preNode和curNode指针都向后移动
            preNode = curNode;
            curNode = nextNode;
        }

        // 4、否则，当前节点为null，直接返回preNode即可
        return preNode;
    }

}
```


## 25、删除链表中重复的节点

```
/**
 * 删除链表中重复的节点
 */
public class Solution83 {

    public ListNode deleteDuplicates(ListNode head) {
        // 1、如果当前节点为null或下一个节点为null时直接返回
        if (head == null || head.next == null) {
            return head;
        }

        // 2、递归删除重复节点：如果当前节点与下一个节点值相等，则直接链接下一个节点
        head.next = deleteDuplicates(head.next);
        return head.val == head.next.val ? head.next : head;
    }

}

```


## 26、判断链表是否有环

```
/**
 * 判断链表是否有环
 */
public class Solution141 {

    // 双指针：时间复杂度O(n), 空间复杂度O(1)
    public boolean hasCycle(ListNode head) {

        // 1、异常处理：如果头结点为null，则返回false
        // 为什么不判断head.next为null？因为1个节点也可以构成环形链表，即自己指向自己
        if (head == null) {
            return false;
        }

        // 2、创建两个指针节点，l1为head，l2为head的下一个节点
        ListNode l1 = head, l2 = head.next;

        // 3、当l1、l2、l2.next都不为空，则移动两个链表来判断是否有环
        while (l1 != null && l2 != null && l2.next != null) {
            // 1）、两个指针相遇，则说明有环
            if (l1 == l2) {
                return true;
            }

            // 2）、l1指针移动1步，l2指针移动2步
            l1 = l1.next;
            l2 = l2.next.next;
        }

        // 4、如果遍历到链表尾部，则说明没环
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

    // 1、fast走的步数是slow步数的2倍：f=2s、fast比slow多走了n个环的长度：f=s+nb => s = nb
    // 2、走a+nb步一定是在环的入口
    // 3、slow再走a = 入口 = head走到入口 = a
    // 4、由3得出，起始距离入口 = 第一次相遇位置 + a，所以，此时再次相遇时的节点即为环的入口节点
    public ListNode detectCycle(ListNode head) {

        // 1、创建快慢指针
        ListNode fast = head;
        ListNode slow = head;

        // 2、当fast不为null且fast.next不为null时，则移动fast两步、slow一步，
        // 当fast与slow相遇时，即得到第一次相遇的节点位置
        while (fast != null && fast.next != null) {

            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                break;
            }
        }

        // 3、异常处理：如果fast为null或者fast.next为null，说明没有环，直接返回null
        if (fast == null || fast.next == null) {
            return null;
        }

        // 4、将fast定位到head，再同时移动fast和slow，当第二次相遇时，相遇的节点即为环的入口节点
        fast = head;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

}
```


## 28、反转链表

```
/**
 * 1、pre、cur、next + 循环：
 *
 * 在遍历列表时，将当前节点的 next 指针改为指向前一个元素。
 * 由于节点没有引用其上一个节点，因此必须事先存储其前一个元素。在更改引用之前，还
 * 需要另一个指针来存储下一个节点。不要忘记在最后返回新的头引用！
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
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

    // 1、pre、cur、next + 循环
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
 * 2、pre、cur、next + 递归
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
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

    // 2、pre、cur、next + 递归
    public ListNode reverseList(ListNode head) {

        // 1、递归终止条件：如果头结点和其下一个节点为null，则直接返回
        if (head == null || head.next == null) {
            return head;
        }

        ListNode p = reverseList(head.next);

        // 2、递归到底，从后向前反转链表：5的next指向4，4的next指向null，即断开4的next，便于后续操作
        // 5->4->null => 5->4->3->null => 5->4->3->2->1->null
        head.next.next = head;
        head.next = null;

        return p;
    }

}
```


## 29、链表元素相加

```
/**
 * 题目描述：给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。
 * 它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *
 * 进阶：如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 *
 * 示例：
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7
 *
 * 1、如果不允许修改输入的链表呢？数字之外是否有前置的0？除0以外，没有前置0、负数？不是。
 * 2、使用辅助的数据结构。
 */
public class Solution445 {

    // 本题的主要难点在于链表中数位的顺序与我们做加法的顺序是相反的，
    // 为了逆序处理所有数位，我们可以使用栈：把所有数字压入栈中，
    // 再依次取出相加。计算过程中需要注意进位的情况。
    // 时间复杂度：O(max(m， n)), 其中 m 与 n 分别为两个链表的长度。我们需要遍历每个链表。
    // 空间复杂度：O(m+n), 我们把链表内容放入栈中所用的空间。
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // 1、使用 stack 存储链表数据，以便从后往前进行计算
        Stack<Integer> stack1 = buildStack(l1);
        Stack<Integer> stack2 = buildStack(l2);

        // 2、创建虚拟头结点便于后面计算和进位值变量
        ListNode dummyHead = new ListNode(-1);
        int c = 0;

        // 3、当stack1或stack2或c不为空时
        // 例子：dummyHead->null => dummyHead->7->null => dummyHead->0->7->null => dummyHead->8->0->7->null => dummyHead->7->8->0->7->null
        while (!stack1.isEmpty() || !stack2.isEmpty() || c != 0) {
            // 1、计算当前链表值
            int x = stack1.isEmpty() ? 0 : stack1.pop();
            int y = stack2.isEmpty() ? 0 : stack2.pop();
            int sum = x + y + c;

            // 2、根据当前值创建对应的链表
            ListNode node = new ListNode(sum % 10);

            // 3、将当前节点插入dummyHead后面：当前节点指向dummyHead的下一个节点，
            // 然后重置dummyHead到链表头部位置
            node.next = dummyHead.next;
            dummyHead.next = node;

            // 4、计算进位c
            c = sum / 10;
        }

        return dummyHead.next;
    }

    private Stack<Integer> buildStack(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
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
 * 两个链表长度分别为L1+C、L2+C， C为公共部分的长度，第一个人走了L1+C步后，
 * 回到第二个人起点走L2步；第2个人走了L2+C步后，回到第一个人起点走L1步。
 * 当两个人走的步数都为L1+L2+C时这两个家伙就相爱了。
 *
 * 时间复杂度：O(L1+L2+C)
 * 空间复杂度：O(1)
 */
public class Solution_1 {

    public ListNode findFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        // 1、定义双指针
        ListNode l1 = pHead1, l2 = pHead2;

        // 2、当l1和l2没有相遇时：同时移动l1和l2，当有一方到达尾部时，
        // 就会从另一端开始，当两者相遇时，此时的位置就是相交的位置了
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
 * 单链表的选择排序：
 *
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 */
public class Solution_2 {

    // 根据选择排序的思想寻找最小的元素，并利用头插法插入到节点
    public ListNode sortInList (ListNode head) {
        // 1、创建一个虚拟头结点并将它指向真正的头结点，再赋值给已排序的链表
        ListNode dummyHead = new ListNode(Integer.MAX_VALUE);
        dummyHead.next = head;
        ListNode sorted = dummyHead;

        // 2、当已排序链表还有下一个元素时，即移动到链表尾部时已经排好序
        while (sorted.next != null) {
            // 1）、创建pre、cur、pre_min、min便于后续的插入操作
            ListNode pre = sorted;
            ListNode cur = sorted.next;
            ListNode pre_min = null;
            ListNode min = null;

            // 2）、当cur不等于null时：寻找最小的数min与pre_min
            while (cur != null) {
                if (min == null || cur.val < min.val) {
                    min = cur;
                    pre_min = pre;
                }

                // 1）、更新cur和pre：继续向后移动指针
                cur = cur.next;
                pre = pre.next;
            }

            // 3）、将当前min从未排序链表部分中移除，并利用头插法插入已排序链表部分
            // 例子：dummyHead->head => dummyHead->min->head => dummyHead->新min->min->head
            pre_min.next = min.next;
            min.next = sorted.next;
            sorted.next = min;

            // 4）、哨兵节点往后移动（重置为未排序链表部分头部的前一个位置）
            sorted = min;
        }

        return dummyHead.next;
    }

}
```


## 32、链表中倒数第K个节点

```
/**
 * 题目描述：输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，
 * 本题从1开始计数，即链表的尾节点是倒数第1个节点。例如，一个链表有6个节点，
 * 从头节点开始，它们的值依次是1、2、3、4、5、6。
 * 这个链表的倒数第3个节点是值为4的节点。
 *
 * 设链表的长度为 N。设置两个指针 P1 和 P2，先让 P1 移动 K 个节点，
 * 则还有 N - K 个节点可以移动。此时让 P1 和 P2 同时移动，
 * 可以知道当 P1 移动到链表结尾时，P2 移动到第 N - K 个节点处，
 * 该位置就是倒数第 K 个节点。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
public class Solution_3 {

    public ListNode findKthToTail(ListNode head, int k) {
        // 1、异常处理：如果head==null，直接返回
        if (head == null) {
            return null;
        }

        // 2、初始化第一个指针P1，然后移动P1 k步
        ListNode P1 = head;
        while (P1 != null && k-- > 0) {
            P1 = P1.next;
        }

        // 3、异常处理：如果还没走完k步就到链表尾部了，直接返回null
        if (k > 0) {
            return null;
        }

        // 4、初始化第二个指针P2，当P1不等于null时，则同时移动P1、P2，
        // 当P1等于null时，P2就是倒数第k个节点
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
 * 判断链表是否是一个回文结构
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
public class Solution_4 {

    // 1、Java双端队列解法：利用Deque的pollFirst和pollLast方法来比较，
    // 时间复杂度：O(n), 空间复杂度：O(n)
    public boolean isPail(ListNode head) {
        // 1、如果链表节点数为0或1，则返回true
        if (head == null || head.next == null) {
            return true;
        }

        // 2、将链表的节点都放入双端队列中
        Deque<Integer> deque = new ArrayDeque<>();
        ListNode cur = head;
        while (cur != null) {
            deque.add(cur.val);
            cur = cur.next;
        }

        // 3、当双端队列大于1时：利用Java双端队列的pollFirst和pollLast方法来判断
        while (deque.size() > 1) {
            if (!deque.pollFirst().equals(deque.pollLast())) {
                return false;
            }
        }
        return true;
    }
}
```

```
/**
 * 判断链表是否是一个回文结构
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
public class Solution_4_2 {

    // 2、动态数组（ArrayList) + 双指针解法
    public boolean isPail(ListNode head) {
        // 1、创建一个动态数组，并将链表中的节点都放入里面
        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }

        // 2、初始化一头一尾两个双指针，并同时往中间移动进行比较
        int first = 0;
        int last = list.size() - 1;
        while (first < last) {
            if (!list.get(first).equals(list.get(last))) {
                return false;
            }

            first++;
            last--;
        }
        return true;
    }

}
```

```
/**
 * 判断链表是否是一个回文结构
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
public class Solution_4_3 {

    private ListNode frontPointer;

    // 3、递归：currentNode 指针是先到尾节点，由于递归的特性再从后往前进行比较。
    // frontPointer 是递归函数外的指针。若 currentNode.val != frontPointer.val
    // 则返回 false。反之，frontPointer 向前移动并返回 true。
    public boolean isPail(ListNode head) {
        // 初始化frontPointer
        frontPointer = head;
        return recursivelyCheck(head);
    }

    private boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode != null) {
            // 1、递归到底时执行前后指针的判断
            if (!recursivelyCheck(currentNode.next)) {
                return false;
            }
            if (currentNode.val != frontPointer.val) {
                return false;
            }
            // 2、更新frontPointer
            frontPointer = frontPointer.next;
        }
        return true;
    }

}
```

```
/**
 * 快慢指针:
 * 时间复杂度：O(n), 空间复杂度：O(1)
 */
public class Solution_4_4 {

    // 4、快慢指针解法：先使用快慢指针找到前半个链表的尾节点，然后翻转后半个部分链表，
    // 最后再使用双指针依次比较对应元素，如果不同则返回false，记得返回前需要还原链表
    // 时间复杂度O(n), 空间复杂度O(1)
    public boolean isPail(ListNode head) {
        // 1、如果只有0或1个节点，则说明是回文结构
        if (head == null || head.next == null) {
            return true;
        }

        // 2、通过快慢指针找到前半部分链表的尾节点：若链表有奇数个节点，则中间的节点应该看作是前半部分；
        // 然后反转后半部分链表
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // 3、判断是否回文：当后半部分的指针P2到达末尾时则比较完成
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 4、还原链表并返回结果
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;

            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

}
````


## 34、三数之和

```
/**
 * 题目描述：给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *      1、不同的三元组？
 *      2、如果有多个解，解的顺序？
 *      3、如果没有解。
 *
 * 三数之和：需要尤其注意去重，有些繁琐。
 */
public class Solution15 {

    // 排序 + 双指针：时间复杂度O(n ^ 2)，空间复杂度O(n)
    public List<List<Integer>> threeSum(int[] nums) {

        // 1、创建一个返回链表 & 排序nums数组
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);

        // 2、遍历数组
        for (int i = 0; i < nums.length; i++) {
            // 1）、初始化左右指针l、r
            int l = i + 1, r = nums.length - 1;

            // 2）、异常处理：如果第一个数大于0，后面的数肯定比它大，直接break
            if (nums[i] > 0) {
                break;
            }

            // 3、去重1：如果不是第一个数，需要判断和上次枚举的数不同
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 4、创建一个用于计算三数之和的变量sum, 当左右指针没相遇时才进行处理
            int sum = -1;
            while (l < r){
                // 1）、计算sum
                // [0, 1, 9]
                sum = nums[i] + nums[l] + nums[r];
                // 2）、如果sum大于0，则减小右指针，小于0则增加左指针
                if(sum > 0) {
                    r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    // 3）、当sum等于0时，将当前三元组添加到返回链表，
                    // 去重2：当左指针小于右指针时，左右指针需要判断和下次枚举的数不同，
                    // 最后再更新左右指针
                    list.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    while (l < r && nums[r] == nums[r - 1]) {
                        r--;
                    }

                    l++;
                    r--;
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
 * 二叉树的最大深度
 * 时间复杂度：O(n)
 * 空间复杂度：O(h)
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
        // 1、如果根节点为null，则最大深度为0
        if (root == null) {
            return 0;
        }

        // 2、1 + 递归取左右子树深度的最大值
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

}
```


## 36、二叉树的最低公共祖先

```
/**
 * 题目描述：给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，
 * 最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x
 * 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 二叉搜索树的最近公共祖先
 * 时间复杂度：O(lgn)
 * 空间复杂度：O(n)
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
        // 1、异常处理：如果p或q为null，则抛出异常
        if (p == null || q == null) {
            throw new IllegalArgumentException("q or p is not null!");
        }

        // 2、异常处理：如果根节点为null，则直接返回
        if (root == null) {
            return null;
        }

        // 3、如果p和q都在左边，则需要继续递归左子树查找最近公共祖先
        // 如果p和q都在右边，则需要继续递归右子树查找最近公共祖先
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }

        // 4、异常处理：当p或q为当前根节点或p和q不在同一个子树中才返回当前的最近公共祖先
        assert p.val == root.val || q.val == root.val ||
                (root.val - p.val) * (root.val - q.val) < 0;

        return root;
    }

}
```


```
/**
 * 二叉树的最近公共祖先
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
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
        // 1、如果根节点为null或q或p，则根节点就是最近的公共祖先
        if (root == null || root == p || root == q) {
            return root;
        }

        // 2、往左右子树递归到底得到左右子树节点, 如果左节点为null，则公共祖先为右节点，
        // 左节点不为null，并且右节点为null，则左节点为公共祖先，如果两者都不为null，则
        // 公共祖先为当前的根节点
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }

}
```


## 37、二叉树根节点到所有叶子节点的路径之和

```
/**
 * 二叉树根节点到所有叶子节点的路径之和：
 * 先序遍历的思想(根左右)+数字求和(每一层都比上层和*10+当前根节点的值)
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
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
        // 1、创建一个路径和变量sum，如果root为null，则返回0，最后按照前序遍历的思想计算路径和
        int sum = 0;
        if (root == null) {
            return sum;
        }
        return preOrderSumNumbers(root, sum);
    }

    public int preOrderSumNumbers(TreeNode root, int sum) {
        // 1）、如果root节点为null则返回路径0
        if (root == null) {
            return 0;
        }

        // 2）、计算路径和：每一层都是上一层 * 10 + 当前节点值
        sum = sum * 10 + root.val;

        // 3）、如果遍历到左右子节点都为null时，则说明已经是叶子节点，返回sum即可
        if (root.left == null && root.right == null) {
            return sum;
        }

        // 4、使用先序遍历的思想去递归计算总的路径和
        return preOrderSumNumbers(root.left, sum) + preOrderSumNumbers(root.right, sum);
    }

}
```


## 38、二叉树的之字形遍历

```
/**
 * 题目描述：请实现一个函数按照之字形打印二叉树，即第一行按照
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

    // 队列 + 反转变量reverse记录当前行的打印顺序，
    // 时间复杂度：O(n), 空间复杂度：O(n)
    public ArrayList<ArrayList<Integer>> print(TreeNode pRoot) {
        // 1、创建一个返回链表 & 添加根节点的队列
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);

        // 2、创建一个反转变量记录当前层是否需要反转，默认第一层不需要反转
        // 当队列不为空时
        boolean reverse = false;
        while (!queue.isEmpty()) {
            // 1）、创建一个列表添加当前层次的值
            ArrayList<Integer> list = new ArrayList<>();

            // 2）、当队列数量大于0时，取出队头节点，如果节点不为空，
            // 则添加值到列表中，并且把左右节点添加到队列中
            int cnt = queue.size();
            while (cnt-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                list.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }

            // 3）、偶数层数，反转变量为true，此时将列表反转，并将reverse变量置反
            if (reverse) {
                Collections.reverse(list);
            }
            reverse = !reverse;

            // 4）、如果列表大小不等于0，则添加到返回列表中
            if (list.size() != 0) {
                ret.add(list);
            }
        }
        return ret;
    }

}
```


## 39、前K个出现频率最高的元素


```
/**
 * 1、最简单的思路：扫描一遍统计概率，排序找到前k个出现频率最高的元素。时间复杂度：O(nlogn)
 * 2、使用优先队列不停地维护我们能找到的前k个出现频率最高的元素。时间复杂度：O(nlogk)
 * 
 * Java 的 PriorityQueue 内部是最小堆
 * d 叉堆：d-ary heap
 * 常规堆的缺陷：只能看到堆首的元素。
 * 索引堆：保存元素与之对应的索引。
 * 二项堆
 * 斐波那契堆
 * 普通队列、优先队列、广义队列
 * 栈，也可以理解成是一个队列。
 */
public class Solution2 {

    // 时间复杂度：O(nlogk), 空间复杂度：O(n)
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
/**
 * 排列问题
 * 时间复杂度：O(n*n!)
 * 空间复杂度：O(n)
 */
public class Solution46 {

    public List<List<Integer>> permute(int[] nums) {

        // 1、创建一个组合嵌套列表 & 组合列表 & 记录已访问元素的数组
        List<List<Integer>> permutes = new ArrayList<>();
        List<Integer> permuteList = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];

        // 2、回溯
        backtracking(permuteList, permutes, visited, nums);

        return permutes;
    }

    // 1,2,3 => 移除3,2 => 1,3,2 => 移除 2,3,1 => 2,1,3 => ...
    private void backtracking(List<Integer> permuteList, List<List<Integer>> permutes, boolean[] visited, int[] nums) {
        // 1、如果当前组合等于目标数组长度，则添加到嵌套列表中并返回到上一层
        if (permuteList.size() == nums.length) {
            permutes.add(new ArrayList<>(permuteList));
            return;
        }

        // 2、遍历已访问数组，如果当前元素没有被访问过，则添加到记录组合列表中，并继续下一层回溯，
        // 回溯到底添加完组合列表后，则删除 当前 组合列表中最后一个元素
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

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        new Solution46().permute(nums);
    }
}
```


## 41、全排列（去重）

```
/**
 * 排列问题
 * 时间复杂度：O(n*n!)
 * 空间复杂度：O(n)
 */
public class Solution47 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        // 1、创建一个嵌套排列列表 & 排列列表 & 记录已访问元素的数组
        List<List<Integer>> permutes = new ArrayList<>();
        List<Integer> permuteList = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];

        // 2、排序数组：保证相同的数字都相邻，然后每次填入的数一定是这个数
        // 所在重复数集合中「从左往右第一个未被填过的数字」，
        // 为了在后面回溯的时候便于排除重复元素
        Arrays.sort(nums);

        // 3、回溯
        backtracking(permuteList, permutes, visited, nums);

        return permutes;
    }

    private void backtracking(List<Integer> permuteList, List<List<Integer>> permutes, boolean[] visited, int[] nums) {
        // 1、如果当前的排列列表达到nums的长度，则添加并返回至上一层
        if (permuteList.size() == nums.length) {
            permutes.add(permuteList);
            return;
        }

        // 2、遍历记录已访问的数组：不同于组合问题，排列问题首先需要去重，
        // 如果当前元素没被访问过，则将其添加到列表中，并继续访问下一层，
        // 访问到达添加后排列列表后，则删除当前排列列表中的最后一个元素
        for (int i = 0; i < visited.length; i++) {
            // 有序数组的去重处理：如果当前元素和前一个元素相等 & 前一个元素没被访问过
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

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 2};
        new Solution47().permuteUnique(nums);
    }

}
```


## 42、复原IP地址

```
/**
 * 递归与回溯
 *
 * 题目描述：给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * 有效的 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），
 * 整数之间用 '.' 分隔。
 *
 * 时间复杂度：O(3 ^ SEG_COUNT * ∣s∣)
 * 空间复杂度：O(SEG_COUNT)
 */
class Solution93 {

    // 1、初始化分段数量 & 返回列表
    static final int SEG_COUNT = 4;
    List<String> ans = new ArrayList<>();
    int[] segments;

    public List<String> restoreIpAddresses(String s) {
        // 2、初始化分段数组并进行深度优先遍历
        segments = new int[SEG_COUNT];
        dfs(s, 0, 0);
        return ans;
    }

    public void dfs(String s, int segId, int segStart) {
        // 1）、如果找到了 4 段 IP 地址并且遍历完了字符串，那么就是一种答案
        if (segId == SEG_COUNT) {
            if (segStart == s.length()) {
                StringBuilder ipAddr = new StringBuilder();
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

        // 2）、如果还没有找到 4 段 IP 地址就已经遍历完了字符串，那么提前回溯
        if (segStart == s.length()) {
            return;
        }

        // 3）、异常处理：由于不能有前导零，如果当前数字为 0，那么这一段 IP 地址只能为 0
        if (s.charAt(segStart) == '0') {
            segments[segId] = 0;
            dfs(s, segId + 1, segStart + 1);
        }

        // 4）、一般情况，枚举每一种可能性并递归
        int addr = 0;
        for (int segEnd = segStart; segEnd < s.length(); ++segEnd) {
            addr = addr * 10 + (s.charAt(segEnd) - '0');
            if (addr > 0 && addr <= 0xFF) {
                segments[segId] = addr;
                dfs(s, segId + 1, segEnd + 1);
            } else {
                // 当前情况不满足，直接break回到上一层
                break;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution93().restoreIpAddresses("25525511135"));
    }
}
```


## 43、爬楼梯

```
/**
 * 1、记忆化搜索 + 递归（自上而下）
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
public class Solution70 {

    private int[] memo;

    // 1、记忆化搜索 + 递归（自上而下）
    // 时间复杂度：O(n)，空间复杂度：O(n)
    public int climbStairs(int n) {
        // 1、创建记忆数组避免重复运算
        memo = new int[n + 1];
        Arrays.fill(memo, -1);

        // 2、递归计算
        return calcWays(n);
    }

    private int calcWays(int n) {
        // 1）、如果楼梯为0或1层，则只有1种方式
        if (n == 0 || n == 1) {
            return 1;
        }

        // 2）、如果记忆数组中没有存储当前楼梯爬的方式个数，
        // 则使用动态转移方程计算并保存在记忆数组中
        if (memo[n] == -1) {
            memo[n] = calcWays(n - 1) + calcWays(n - 2);
        }

        return memo[n];
    }

}
```

```
/**
 * 2、记忆化搜索 + DP（自下而上）
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
public class Solution70_2 {

    // 2、记忆化搜索 + DP（自下而上）
    // 时间复杂度：O(n), 空间复杂度：O(n)
    public int climbStairs(int n) {
        // 1、创建记忆数组并全部填充为-1
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);

        // 2、第0或1层的爬楼梯方式都为1种
        memo[0] = 1;
        memo[1] = 1;

        // 3、使用DP自下而上的方式计算目前楼层的爬楼梯方式
        for (int i = 2; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n];
    }

}
```

```
/**
 * 3、Dp + 循环只需记录前两个数的值 => 矩阵快速幂 or 推导公式可以优化时间到 O(logn)
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
public class Solution70_3 {

    // 3、DP + 循环计算只依赖前两个值即可
    // 时间复杂度：O(n), 空间复杂度：O(1)
    public int climbStairs(int n) {
        // 1、异常处理：n小于0抛出异常
        if (n < 0) {
            throw new IllegalArgumentException("illegal argument");
        }

        // 2、如果爬的楼梯是0或1层，则只有1种方式
        if (n == 0 || n == 1) {
            return 1;
        }

        // 3、DP + 循环计算只依赖前两个值
        int pre = 1, cur = 1;
        int next;
        for (int i = 2; i <= n; i++) {
            next = pre + cur;
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
 * 题目描述：
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），
 * 设计一个算法来计算你所能获取的最大利润。
 *
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

    // 时间复杂度：O(n), 空间复杂度：O(1)
    public int maxProfit_k_1(int[] prices) {
        // 1、初始化第i天未持有为0，第i天持有为负无穷
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;

        // 2、遍历价格数组
        for (int price : prices) {
            // 1）、当天未持有等于 前一天未持有和前天持有+当天价格 中的最大值
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + price);

            // 2）、当天持有等于 前一天持有和第一天未持有（0）-当前价格 中的最大值
            dp_i_1 = Math.max(dp_i_1, -price);
        }

        // 3、返回最后一天未持有的价格
        return dp_i_0;
    }

}
```


## 45、第K天股票的最大价格

```
/**
 * 题目描述：给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 如果 k 为正无穷，那么就可以认为 k 和 k - 1 是一样的:
 * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
 * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
 *             = max(dp[i-1][k][1], dp[i-1][k][0] - prices[i])
 *
 * 我们发现数组中的 k 已经不会改变了，也就是说不需要记录 k 这个状态了：
 * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
 * dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
 *
 * 第三题，k = +infinity with cooldown
 *
 * 每次 sell 之后要等一天才能继续交易。只要把这个特点融入上一题的状态转移方程即可：
 * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
 * dp[i][1] = max(dp[i-1][1], dp[i-2][0] - prices[i])
 * 解释：第 i 天选择 buy 的时候，要从 i-2 的状态转移，而不是 i-1 。
 *
 * 第四题，k = +infinity with fee
 *
 * 每次交易要支付手续费，只要把手续费从利润中减去即可。改写方程：
 *
 * 第五题，k = 2
 *
 * 这里 k 取值范围比较小，所以可以不用 for 循环，直接把 k = 1 和 2 的情况全部列举出来也可以：
 * dp[i][2][0] = max(dp[i-1][2][0], dp[i-1][2][1] + prices[i])
 * dp[i][2][1] = max(dp[i-1][2][1], dp[i-1][1][0] - prices[i])
 * dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
 * dp[i][1][1] = max(dp[i-1][1][1], -prices[i])
 *
 * 第六题，k = any integer
 *
 * 一次交易由买入和卖出构成，至少需要两天。所以说有效的限制 k 应该不超过 n/2，如果超过，就没有约束作用了，相当于 k = +infinity。
 *
 * 关键就在于列举出所有可能的「状态」，然后想想怎么穷举更新这些「状态」。一般用一个多维 dp 数组储存这些状态，从 base case 开始向后推进，推进到最后的状态，就是我们想要的答案。想想这个过程，你是不是有点理解「动态规划」这个名词的意义了呢？
 *
 */
public class Solution122 {

    // 时间复杂度：O(n), 空间复杂度：O(1)
    public int maxProfit_k_inf(int[] prices) {
        // 1、初始化第i天未持有为0，第i天持有为负无穷
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;

        // 2、遍历价格数组
        for (int price : prices) {
            // 1）、初始化temp变量记录前一天未持有的价格
            int temp = dp_i_0;

            // 2）、当天未持有等于 前一天未持有和前天持有+当天价格 中的最大值
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + price);

            // 3）、当天持有等于 前一天持有和temp-当天价格 中的最大值
            dp_i_1 = Math.max(dp_i_1, temp - price);
        }

        // 3、返回最后一天未持有的价格
        return dp_i_0;
    }

    int maxProfit_with_cool(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        int dp_pre_0 = 0; // 代表 dp[i-2][0]
        for (int price : prices) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + price);
            dp_i_1 = Math.max(dp_i_1, dp_pre_0 - price);
            dp_pre_0 = temp;
        }
        return dp_i_0;
    }

    int maxProfit_with_fee(int[] prices, int fee) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int price : prices) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + price);
            dp_i_1 = Math.max(dp_i_1, temp - price - fee);
        }
        return dp_i_0;
    }

    int maxProfit_k_2(int[] prices) {
        int dp_i10 = 0, dp_i11 = Integer.MIN_VALUE;
        int dp_i20 = 0, dp_i21 = Integer.MIN_VALUE;
        for (int price : prices) {
            dp_i20 = Math.max(dp_i20, dp_i21 + price);
            dp_i21 = Math.max(dp_i21, dp_i10 - price);
            dp_i10 = Math.max(dp_i10, dp_i11 + price);
            dp_i11 = Math.max(dp_i11, -price);
        }
        return dp_i20;
    }

    int maxProfit_k_any(int max_k, int[] prices) {
        int n = prices.length;
        if (max_k > n / 2)
            return maxProfit_k_inf(prices);

        int[][][] dp = new int[n][max_k + 1][2];
        for (int i = 0; i < n; i++)
            for (int k = max_k; k >= 1; k--) {
                if (i - 1 == -1) { /* 处理 base case */ }
                dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
            }
        return dp[n - 1][max_k][0];
    }
    
}
```


## 46、最长上升子序列

```
/**
 * 题目描述：给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 * 最长上升子序列:
 *      1、暴力解法：选择所有的子序列进行判断。O((2^n)*n)
 *      2、LIS(i) 表示[0...i]的范围内，选择数字nums[i]可以获得的最长上升子序列的长度。
 *      LIS(i) = max(1+LIS(j) if nums[i] > nums[j])
 *      LIS(i) 表示以第i个数字为结尾的最长上升子序列的长度。O(n^2）
 *      3、O(nlogn)
 *
 * 记忆化搜索
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(n)
 */
public class Solution300 {

    private int[] memo;

    public int lengthOfLIS(int[] nums) {
        // 1、异常处理：如果数组长度等于0，则返回0
        if (nums.length == 0) {
            return 0;
        }

        // 2、创建记忆数组并全部填充为-1
        memo = new int[nums.length];
        Arrays.fill(memo, -1);

        // 3、创建返回变量res，然后在for循环中不断计算当前位置的LIS
        int res = 1;
        for (int i = 0; i < nums.length; i++) {
            res = Math.max(res, getMaxLength(nums, i));
        }
        return res;
    }

    // 4、记录以 nums[index] 为结尾的最长上升子序列的长度
    private int getMaxLength(int[] nums, int index) {
        // 1）、如果记忆数组中的当前位置不等于-1，则返回记忆值
        if (memo[index] != -1) {
            return memo[index];
        }

        // 2）、创建返回变量res，然后遍历到index-1处的位置：
        // 如果index处的值大于前面位置i的值，则重新计算当前的res为
        // res 与 1+i处位置的LIS 中的最大值
        int res = 1;
        for (int i = 0; i <= index - 1; i++) {
            if (nums[index] > nums[i]) {
                res = Math.max(res, 1 + getMaxLength(nums, i));
            }
        }

        // 3）、保存index处的LIS并返回
        return memo[index] = res;
    }

}
```

```
/**
 * 记忆化搜索 + DP
 * 时间复杂度：O(n ^ 2)
 * 空间复杂度：O(n)
 */
public class Solution300_2 {

    public int lengthOfLIS(int[] nums) {
        // 1、异常处理：如果nums为0，则返回0
        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        // 2、创建记忆数组并都初始化为-1
        int[] memo = new int[n];
        Arrays.fill(memo, -1);

        // 3、双层遍历：如果下标i处的值大于前面j处的值，则更新memo[i]
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    memo[i] = Math.max(memo[i], 1 + memo[j]);
                }
            }
        }

        // 4、再遍历一次计算所有下标处的最大LIS
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
/**
 * 题目描述：给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的
 * 情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
 * 若这两个字符串没有公共子序列，则返回 0。
 *
 * 1、记忆化数组 + 递归（自上而下）
 * 时间复杂度：O((len(s1) * len(s2))
 * 空间复杂度：O((len(s1) * len(s2))
 */
public class Solution1143 {

    private int[][] memo;

    // 1、记忆化数组 + 递归（自上而下）
    public int longestCommonSubsequence(String s1, String s2) {
        // 1、如果s1或s2等于null，则抛出异常，等于0则直接返回0
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("illegal arguments");
        }

        int n1 = s1.length();
        int n2 = s2.length();

        if (n1 == 0 || n2 == 0) {
            return 0;
        }

        // 2、创建记忆数组并初始化第一维数组下标都为-1
        memo = new int[n1][n2];
        for (int i = 0; i < n1; i++) {
            Arrays.fill(memo[i], -1);
        }

        // 3、递归计算 [0...m] 与 [0...n] 的最长公共子序列的长度
        return lcs(s1, s2, n1 - 1, n2 - 1);
    }

    private int lcs(String s1, String s2, int m, int n) {
        // 1）、如果m或n小于0，则返回0
        if (m < 0 || n < 0) {
            return 0;
        }

        // 2）、如果memo当前位置不等于-1表明已计算过，则直接返回
        if (memo[m][n] != - 1) {
            return memo[m][n];
        }

        // 3）、创建返回变量res，如果s1的m位置字符等于s2的n位置字符，
        // 则res加1并继续递归计算m与n低一层级的lcs，
        // 否则，取m低一层级与n低一层级中递归lcs的最大值
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
/**
 * 2、记忆化数组 + DP（自下而上）：躲避边界条件
 * 时间复杂度：O((len(s1) * len(s2))
 * 空间复杂度：O((len(s1) * len(s2))
 */
public class Solution1143_2 {

    // 2、记忆化数组 + DP（自下而上）：躲避边界条件
    public int longestCommonSubsequence(String s1, String s2) {
        // 1、如果s1或s2等于null，则抛出异常
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("illegal argument!");
        }

        // 2、初始化记忆数组，注意这里的容量为len+1，
        // 为了便于计算，后面的统计位置从下标1开始
        int m = s1.length();
        int n = s2.length();
        int[][] memo = new int[m + 1][n + 1];

        // 3、双层for循环：都从下标1开始，如果s1的i-1位置字符等于s2的j-1位置字符，
        // 则当前位置memo取1+低一层级memo值，
        // 否则，取m低一层级与n低一层级memo中的最大值
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
/**
 * LRU Cache 146：
 *
 * 运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；
 * 如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，
 * 它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 * 最简单的方式是直接继承LinkedHashMap，并重写getOrDefault、put、removeEldestEntry方法。
 * 时间复杂度：put/get都是O(1)
 * 空间复杂度：O(capacity)
 */
class Solution146 {

    int capacity;
    // 初始化LinkedHashMap作为cache
    LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();

    public Solution146(int capacity) {
        // 初始化容量
        this.capacity = capacity;
    }

    public int get(int key) {
        // 1、如果cache中不包含key，则返回-1
        if (!cache.containsKey(key)) {
            return -1;
        }
        // 2、否则将 key 变为最近使用，然后再从cache中获取对应值
        makeRecently(key);
        return cache.get(key);
    }

    public void put(int key, int val) {
        // 1、如果cache中包含key，则覆盖key的值，并将key变为最近使用的
        if (cache.containsKey(key)) {
            cache.put(key, val);
            makeRecently(key);
            return;
        }

        // 2、如果cache大小大于等于容量，则移除链表头部那个最近最久未使用的值，
        // 然后再将新值添加到链表尾部
        if (cache.size() >= this.capacity) {
            int oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);
        }
        cache.put(key, val);
    }

    // 将value变为最近使用的：删除 key，重新插入到链表尾部
    private void makeRecently(int key) {
        int val = cache.get(key);
        cache.remove(key);
        cache.put(key, val);
    }

}
```


## 49、反转数字

```
/**
 * 题目描述：将给出的32位整数x翻转。
 * 例1:x=123，返回321
 * 例2:x=-123，返回-321
 * 你有注意到翻转后的整数可能溢出吗？因为给出的是32位整数，则其数值范围为[−2^{31}, 2^{31} − 1]。
 * 翻转可能会导致溢出，如果反转后的结果会溢出就返回0。
 *
 * 关键点是如何判断溢出。
 * 推荐解答用的是用long类型存储结果，如果结果大于0x7fffffff或者小于0x80000000就溢出
 * 我的解法是每次计算新的结果时，再用逆运算判断与上一次循环的结果是否相同，不同就溢出
 */
public class Solution_1 {

    public int reverse(int x) {
        // 1、创建一个返回变量res
        int res = 0;

        // 2、当x不等于0时，进行反转操作
        while (x != 0){
            // 1）、初始化tail记录当前最后一位（-123%10==-3），newRes记录中间反转值
            int tail = x % 10;
            int newRes = res * 10 + tail;

            // 2）、异常处理：如果 (newRes-tail)/10!=res 说明产生了溢出，
            // 为什么？因为当newRes的值超过最大位数时，系统会将超过
            // 最大位数的高位二进制数扔掉，从而导致了newRes!=res*10+tail。
            // 此时计算的(newRes-tail)/10自然也就不等于res了。
            if ((newRes - tail) / 10 != res) {
                return 0;
            }

            // 3）、更新res与x
            res = newRes;
            x = x / 10;
        }

        // 3、直到x等于0才返回res
        return res;
    }

}
```


## 50、生产者消费者

```
/**
 * 生产者消费者
 */
public class Solution_2 {

    // 1、定义一个Message类
    public class Message {

        private int code;
        private String msg;
//        Handler target;

        public Message() {
        }

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

    // 2、定义一个IMessageQueue接口：包含next与enqueueMessage抽象方法
    public interface IMessageQueue {

        Message next() throws InterruptedException;

        void enqueueMessage(Message message) throws InterruptedException;
    }

    // 3、阻塞队列LinkedBlockingQueue实现阻塞式生产者消费者模式：take/put 是阻塞方法
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

    // 4、链表 + synchronized + wait/notifyAll + volatile变量 + 对象锁实现非阻塞式的生产者消费者模式
    public class MessageQueue1 implements IMessageQueue {

        private Queue<Message> queue;
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

## 51、接雨水

```
/**
 * 每一个柱子的高度方向可以接的雨水的数量 =
 * min(从当前柱子向左看的最高柱子高度, 从当前柱子向右看的最高柱子高度) - 当前柱子高度
 * 1）两个数组left、right分别保存：从左往右遍历时下标i最高柱子高度，和从右往左遍历时下标i最高柱子高度。
 * 2）再遍历一遍每个位置，只有当height[i]的高度，比left[i]和right[i]都要小的时候才能接住雨水（否则总有一边会漏，接不到雨水）
 * 3）将所有可以接到雨水的柱子的数量加起来
 */
public class Solution42 {

    public int trap(int[] height) {
        int length = height.length;
        int[] left = new int[length];//保存从左往右遍历时，每一个下标位置当前的最高柱子高度
        int[] right = new int[length];//保存从右往左遍历时，每一个下标位置当前的最高柱子高度
        int leftMax = 0;
        int rightMax = 0;
        int sum = 0;

        //计算left和right数组
        for (int i = 0; i < length; i++) {
            if (height[i] > leftMax) {
                leftMax = height[i];
            }
            left[i] = leftMax;
            if (height[length-1-i] > rightMax) {
                rightMax = height[length-1-i];
            }
            right[length-1-i] = rightMax;
        }

        //遍历，只有当前柱子往左看、往右看的最高柱子都比当前柱子高，才能接住雨水
        for (int j = 0; j < length; j++) {
            if (height[j] < left[j] && height[j] < right[j]) {
                sum = sum + Math.min(left[j], right[j]) - height[j];
            }
        }
        return sum;
    }

}
```



