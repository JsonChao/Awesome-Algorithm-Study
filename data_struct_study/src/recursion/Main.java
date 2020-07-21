package recursion;


/**
 * 递归：本质就是将原来的问题转换为更小的问题。
 *
 * 1）、注意递归函数的宏观语义。
 * 2）、递归函数就是一个普通的函数，仅完成一个功能而已。
 *
 * 递归算法通常分为两步：
 *
 * 1）、求解基本问题。
 * 2）、把原问题转化为更小的问题。
 *
 * 递归调用是有代价的：函数调用 + 系统栈空间
 *
 * 其它常见的链表类型：
 * 1）、双向链表，每一个 ListNode 同时具有 pre、next 指针
 * 2）、双向循环链表：能够更进一步地封装很多便利的操作，Java 中的 LinkedList 的本质就是双向循环链表。
 * 3）、数组链表：如果明确知道要存储元素的总数，使用数组链表会更加方便，
 * 数组中存储一个个的 Node，Node 包含元素 e 与 int 型的 next 指针。
 *
 */
public class Main {

    /**
     * 给用户调用的函数
     *
     * @param arr 数组
     * @return 数组的和
     */
    public static int sum(int[] arr) {
        return sum(arr, 0);
    }

    /**
     * 递归函数，求解 [l~n] 的和
     *
     * @param arr 数组
     * @param l 起始值
     * @return 数组的和
     */
    private static int sum(int[] arr, int l) {

        // 1、求解基本问题
        if (l == arr.length) {
            return 0;
        }

        // 2、把原问题转化为更小的问题
        return arr[l] + sum(arr, l + 1);
    }
}
