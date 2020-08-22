package sort_problem;

/**
 * 待排序的元素需要实现 Java 的 Comparable 接口，该接口有 compareTo() 方法，可以用它来判断两个元素的大小关系。
 * 使用辅助函数 less() 和 swap() 来进行比较和交换的操作，使得代码的可读性和可移植性更好。
 * 排序算法的成本模型是比较和交换的次数。
 *
 * 算法	稳定性	时间复杂度	空间复杂度	备注
 * 选择排序	×	N2	1
 * 冒泡排序	√	N2	1
 * 插入排序	√	N ~ N2	1	时间复杂度和初始顺序有关
 * 希尔排序	×	N 的若干倍乘于递增序列的长度	1	改进版插入排序
 * 快速排序	×	NlogN	logN
 * 三向切分快速排序	×	N ~ NlogN	logN	适用于有大量重复主键
 * 归并排序	√	NlogN	N
 * 堆排序	×	NlogN	1	无法利用局部性原理
 *
 * 快速排序是最快的通用排序算法，它的内循环的指令很少，而且它还能利用缓存，
 * 因为它总是顺序地访问数据。它的运行时间近似为 ~cNlogN，这里的 c
 * 比其它线性对数级别的排序算法都要小。
 *
 * 使用三向切分快速排序，实际应用中可能出现的某些分布的输入能够达到线性级别，
 * 而其它排序算法仍然需要线性对数时间。
 *
 * Java 主要排序方法为 java.util.Arrays.sort()，对于原始数据类型使用
 * 三向切分的快速排序，对于引用类型使用归并排序。
 */
public abstract class Sort<T extends Comparable<T>> {

    public abstract void sort(T[] nums);

    protected boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    protected void swap(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}

