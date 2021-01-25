# 1、最长回文子串

```
/**
 * 注意要分奇偶情况分别讨论。
 */
public class Solution5 {

    public String longestPalindrome(String s) {
        if (s==null||s.length()<1) return "";
        int maxLen=0, id=-1;
        for(int i=0; i<s.length(); i++){
            int len1=expand(s,i,i);
            int len2=expand(s,i,i+1);
            int len=Math.max(len1,len2);
            if (len>=maxLen){
                maxLen=len;id=i;
            }
        }
        return s.substring(id-(maxLen-1)/2+1, id+maxLen/2);
    }

    int expand(String s, int l, int r){
        while(l>=0&&r<s.length()&&s.charAt(l)==s.charAt(r)){
            l--;r++;
        }
        return r-l+1;
    }
}
```

# 2、搜索旋转排序数组

```
/**
 * 先二分查找旋转点（迭代或者递推），再二分查找目标（递推）。二分查找可以用递归/循环，
 * 是很常见的写法。需注意分段和索引范围问题（没有必要为了排除某个别索引把分段弄得特别严谨）。
 */
public class Solution33 {

    public int search(int[] nums, int target) {
        int len = nums.length;
        if (len==0) return -1;
        if (len==1) return nums[0]==target? 0:-1;

        int r = findRotation(nums,0,len-1);
        if (r==-1) return findTarget(nums,0,len-1, target);
        if (nums[0]>target) return findTarget(nums,r,len-1,target);
        else if (nums[0]<target) return findTarget(nums,0,r-1,target);
        else return 0;
    }

    private int findRotation(int[] nums, int a, int b)
    {
        if (b == a) return -1;

        // iteration is also okay here
        int m = (a+b)/2;
        if (nums[m]>nums[m+1]) return m+1;
        else return (Math.max(findRotation(nums,a,m),findRotation(nums,m+1,b)));
    }

    private int findTarget(int[] nums, int a, int b, int target)
    {
        if (a==b) return nums[a]==target? a:-1;
        if (target>nums[b]||target<nums[a]) return -1;

        int m = (a+b)/2;
        if (nums[m]>target) return findTarget(nums,a,m,target);
        else if (nums[m]<target) return findTarget(nums,m+1,b,target);
        else return m;
    }
}
```

# 3、颜色分类

```
/**
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 *
 *  1、计数排序：分别统计0、1、2元素的个数。
 *  2、三路快排。
 *
 * 计数排序思路，对整个数组遍历了两遍
 * O(n)
 * O(K)，k为元素的取值范围
 */
public class Solution75 {

    public void sortColors(int[] nums) {

        // 使用一个数组统计每个值出现的频率
        int[] count = {0, 0, 0};
        for (int i = 0; i < nums.length; i++) {
            assert nums[i] >= 0 && nums[i] <= 2;
            count[nums[i]] ++;
        }

        // 拼装成 0、1、2 排序的新数组
        int k = 0;
        for (int i = 0; i < count[0]; i++) {
            nums[k++] = 0;
        }
        for (int i = 0; i < count[1]; i++) {
            nums[k++] = 1;
        }
        for (int i = 0; i < count[2]; i++) {
            nums[k++] = 2;
        }
    }
}

/**
 * 三路快排的思想
 * O(n)
 * O(1)
 */
public class Solution75_2 {

    public void sortColors(int[] nums) {

        // 1、[0, zero] 存储0
        int zero = -1;
        // 2、[two, nums.length -1] 存储2
        int two = nums.length;

        // 3、0,2指针往中间靠，最后中间剩下的就是1
        // 注意 i < two，遍历的区间会随着尾部2的增多而减小
        for (int i = 0; i < two; ) {
            if (nums[i] == 1) {
                i++;
            } else if (nums[i] == 2) {
                swap(nums, --two, i);
            } else if (nums[i] == 0) {
                swap(nums, ++zero, i++);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```

# 4、最长连续序列  

```
public int longestConsecutive(int[] nums) {
    // 1、创建 值:频率 哈希表
    HashMap<Integer, Integer> countForNum = new HashMap<>();
    for (int num:nums) {
        countForNum.put(num, 1);
    }

    // 2、求出哈希表中每一个值对应的最大序列长度
    for (int num:nums) {
        forward(countForNum, num);
    }

    // 3、统计哈希表中存储的最大序列长度
    return maxLen(countForNum);
}

private int forward(HashMap<Integer, Integer> countForNum, int num) {
    if (!countForNum.containsKey(num)) {
        return 0;
    }

    int cnt = countForNum.get(num);
    if (cnt > 1) {
        return cnt;
    }

    cnt = forward(countForNum, num + 1) + 1;
    countForNum.put(num, cnt);
    return cnt;
}

private int maxLen(HashMap<Integer, Integer> countForNum) {
    int max = 0;
    for (int num:countForNum.keySet()) {
        max = Math.max(max, countForNum.get(num));
    }

    return max;
}
```

# 5、除自身以外数组的乘积

```
public int[] productExceptSelf(int[] nums) {

    int n = nums.length;
    int[] product = new int[n];
    Arrays.fill(product, 1);

    int left = 1;
    for (int i = 1; i < n; i++) {
        left *= nums[i - 1];
        product[i] *= left;
    }

    int right = 1;
    for (int i = n - 2; i >= 0; i--) {
        right *= nums[i + 1];
        product[i] *= right;
    }

    return product;
}
```

# 6、移动零

```
/**
* O(n)
* O(1)
*/
public void moveZeroes(int[] nums) {

       int k = 0;

       for (int i = 0; i < nums.length; i++) {
           if (nums[i] != 0) {
               if (i != k) {
                   swap(nums, k++, i);
               } else {
                   k++;
               }
           }
       }
   }

private void swap(int[] nums, int k, int i) {
    int temp = nums[k];
    nums[k] = nums[i];
    nums[i] = temp;
}
```

# 7、反转字符串中的元音字母

```
/**
 * （元音aeiou）
 */
public class Solution345 {

    private final static HashSet<Character> vowels = new HashSet<>(Arrays.asList(
            'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'
    ));

    public String reverseVowels(String s) {

        if (s == null) {
            return null;
        }

        int i = 0, j = s.length() - 1;
        char[] res = new char[s.length()];
        while (i <= j) {
            char ci = s.charAt(i);
            char cj = s.charAt(j);
            if (!vowels.contains(ci)) {
                res[i++] = ci;
            } else if (!vowels.contains(cj)) {
                res[j--] = cj;
            } else {
                res[i++] = cj;
                res[j--] = ci;
            }
        }
        return new String(res);
    }

}
```

# 8、找到字符串中所有字母异位词

```
/**
 * 找到字符串中所有字母异位词：【滑动窗口】。
 */
public class Solution438 {

    public List<Integer> findAnagrams(String s, String p) {
        if(p==null||s==null||p.length()==0||s.length()==0
                ||p.length()>s.length())
            return new ArrayList<Integer>();
        int[] record=new int[26];
        List<Integer> list=new ArrayList<>();
        int sum=0;
        for(int i=0; i<p.length(); i++){
            char c=p.charAt(i);
            record[c-'a']++; sum++;
        }
        for(int i=0; i<p.length(); i++){
            char c=s.charAt(i);
            record[c-'a']--;
            if (record[c-'a']>=0) sum--;
        }
        if (sum==0) list.add(0);
        for (int i=p.length(); i<s.length(); i++){
            char c=s.charAt(i-p.length());
            record[c-'a']++;
            if (record[c-'a']>0) sum++;
            c=s.charAt(i);
            record[c-'a']--;
            if (record[c-'a']>=0) sum--;
            if (sum==0) list.add(i-p.length()+1);
        }
        return list;
    }
}
```

# 9、省份数量  

```
public class Solution547 {

    private int n;

    public int findCircleNNum(int[][] M) {

        n = M.length;
        boolean[] visited = new boolean[n];

        int max = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(M, i, visited);
                max++;
            }
        }

        return max;
    }

    private void dfs(int[][] M, int i, boolean[] visited) {
        visited[i] = true;
        for (int k = 0; k < n; k++) {
            if (M[i][k] == 1 && !visited[k]) {
                dfs(M, k, visited);
            }
        }
    }

}
```

# 10、最大岛屿的数量

```
public class Solution695 {

    private int m, n;
    private int[][] d = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int maxAreaOfIsland(int[][] grid) {

        if (grid == null || grid.length == 0) {
            return 0;
        }
        m = grid.length;
        n = grid[0].length;

        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxArea = Math.max(maxArea, dfs(grid, i, j));
            }
        }

        return maxArea;
    }

    private int dfs(int[][] grid, int i, int j) {

        if (i < 0 || i >= m || j < 0 || j >= m || grid[i][j] == 0) {
            return 0;
        }

        // 已经搜索过的设为0，防止重复被搜索
        grid[i][j] = 0;
        int res = 1;
        for (int[] d : d) {
            res += dfs(grid, i + d[0], j + d[1]);
        }
        return res;
    }

}
```

# 11、完全平方数

```
/**
 * 图：无权图的最短路径。
 * 1、建模成图论问题：从n到0，每个数字表示一个节点，如果两个数字x到y相差一个完全平方数，则连接一条边。
 * 我们得到了一个无权图。原问题转换为，求这个无权图中从n到0的最短路径。
 *
 * 1、暴力求解
 * O(2^n）
 * O(2^n)
 * 2、使用 visited 数组，记录每一个入队元素
 * O(n)
 * O(n)
 */
public int numSquares(int n) {

    if (n == 0) {
        return 0;
    }

    LinkedList<Pair<Integer, Integer>> queue = new LinkedList<Pair<Integer, Integer>>();
    queue.addLast(new Pair<>(n, 0));

    boolean[] visited = new boolean[n + 1];
    visited[n] = true;

    while (!queue.isEmpty()) {

        Pair<Integer, Integer> pair = queue.removeFirst();
        int sum = pair.fst;
        int step = pair.snd;

        if (sum == 0) {
            return step;
        }

        for (int i = 1; sum - i * i >= 0; i++) {
            int a = sum - i * i;
            if (!visited[a]) {
                if (a == 0) {
                    return step + 1;
                }
                queue.addLast(new Pair<>(a, step + 1));
                visited[a] = true;
            }
        }
    }

    throw new IllegalArgumentException("no this num squares!");
}
```

# 12、两数相加

```
/**
 *  1、数字之外是否有前置的0。（除0以外，没有前置0）
 *  2、负数（不是）。
 *  prev ListNode
 */
public class Solution2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode prev=new ListNode(0), l=prev;
        int carry=0;
        while(l1!=null||l2!=null){
            int x=l1==null?0:l1.val;
            int y=l2==null?0:l2.val;
            int sum=x+y+carry;
            carry=sum/10;
            prev.next=new ListNode(sum%10);
            prev=prev.next;
            if(l1!=null) l1=l1.next;
            if(l2!=null) l2=l2.next;
        }
        if (carry>0) prev.next=new ListNode(carry);
        return l.next;
    }
}
```

# 13、两两交换链表中的节点  

```
/**
 * （p、node1、node2、（next） => 复杂的穿针引线）
 * O(n)
 * O(1)
 */
public class Solution24 {

    public ListNode swapPairs(ListNode head) {

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode p = dummyHead;
        while (p.next != null && p.next.next != null) {
            ListNode node1 = p.next;
            ListNode node2 = node1.next;
            ListNode next = node2.next;
            node2.next = node1;
            node1.next = next;
            p.next = node2;
            p = node1;
        }

        return dummyHead.next;
    }

}
```

# 14、排序链表

```
/**
 * 排序链表：【merge sort + Floyd】
 */
public class Solution148 {

    public ListNode sortList(ListNode head){
        if (head==null||head.next==null) return head;
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode curr = slow.next;
        slow.next = null;
        head = sortList(head);
        curr = sortList(curr);

        ListNode pre = new ListNode(0);
        slow = pre;
        while (head!=null&&curr!=null){
            if (head.val<curr.val){
                pre.next = head; pre = pre.next; head = head.next;
            }
            else{
                pre.next = curr; pre = pre.next; curr = curr.next;
            }
        }
        pre.next = head==null? curr:head;
        return slow.next;
    }
}
```

# 15、相交链表

```
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    ListNode l1 = headA, l2 = headB;
    while (l1 != l2) {
        l1 = (l1 == null) ? headB : l1.next;
        l2 = (l2 == null) ? headA : l2.next;
    }

    return l1;
}
```

# 16、移除链表元素  

```
/**
 * 使用虚拟头结点
 * O(n)
 * O(1)
 */
public class Solution203 {

    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeElements(ListNode head, int val) {

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode cur = dummyHead;
        while (cur.next != null) {
            if (cur.next.val == val) {
                ListNode delNode = cur.next;
                cur.next = delNode.next;
            } else {
                cur = cur.next;
            }
        }

        return dummyHead.next;
    }

}
```

# 17、回文链表

```
/**
 * （1、遍历存到数组、判断数组。2、双指针)
 * 回文链表: 快慢指针（整除器），把剩下的一半变成逆序，再进行比较。注意奇偶情况讨论。
 */
class Solution234 {
    public boolean isPalindrome(ListNode head) {
        if(head==null) return true;
        ListNode prev=null;
        ListNode slow=head, fast=head;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }

        if (fast!=null) slow=slow.next;
        while(slow!=null){
            ListNode tmp=slow;
            slow=slow.next;
            tmp.next=prev;
            prev=tmp;
        }
        slow=prev;fast=head;
        while(slow!=null){
            if(slow.val==fast.val){slow=slow.next; fast=fast.next;}
            else return false;
        }
        return true;
    }
}
```

# 18、删除链表中的节点  

```
/**
 * O(1)
 * O(1)
 */
public class Solution237 {

    public void deleteNode(ListNode node) {

        if (node == null || node.next == null) {
            throw new IllegalArgumentException("node must not null and node must not tail node!");
        }

        node.val = node.next.val;
        node.next = node.next.next;
    }

}
```

# 19、奇偶链表  

```
public ListNode oddEvenList(ListNode head) {

    if (head == null) {
        return null;
    }

    ListNode odd = head, even = head.next, evenHead = even;
    while (even != null && even.next != null) {
        odd.next = odd.next.next;
        odd = odd.next;
        even.next = even.next.next;
        even = even.next;
    }

    odd.next = evenHead;
    return head;
}
```

# 20、字符串中的第一个唯一字符  

```
public int firstUniqChar(String s) {

    int[] hashTable = new int[26];
    for (int i = 0; i < s.length(); i++) {
        hashTable[s.charAt(i) - 'a']++;
    }

    for (int i = 0; i < s.length(); i++) {
        if (hashTable[s.charAt(i) - 'a'] == 1) {
            return i;
        }
    }

    return -1;
}
```

# 21、字母异位词分组  

```
/**
* 1、字符集
* 2、大小写敏感
*/
public List<List<String>> groupAnagrams(String[] strs) {
    HashMap<String, List<String>> map=new HashMap<>();
    for (String str:strs){
        char[] c=str.toCharArray();
        Arrays.sort(c);
        String s=String.valueOf(c);
        if(map.containsKey(s))
            map.get(s).add(str);
        else
            map.put(s,new ArrayList(Arrays.asList(str)));
    }
    return new ArrayList(map.values());
}
```

# 22、二叉搜索树中的顺序后继  

```
/**
 * 1、字符集
 * 2、空串
 * 3、一个字符是否可以映射到自己
 *
 * 记录一个字符上次出现的位置，如果两个字符串中的字符上次出现的位置一样，那么就属于同构。
 */
public class Solution205 {

    public boolean isIsomorphic(String s, String t) {
        int[] preIndexOfS = new int[256];
        int[] preIndexOfT = new int[256];
        for (int i = 0; i < s.length(); i++) {
            char cs = s.charAt(i), ct = t.charAt(i);
            if (preIndexOfS[cs] != preIndexOfT[ct]) {
                return false;
            }
            preIndexOfS[cs] = i + 1;
            preIndexOfT[ct] = i + 1;
        }

        return true;
    }

}
```

# 23、存在重复元素  

```
/**
 * 219、217（简化版219）：
 *      1、暴力枚举法：O(n^2)
 *      2、滑动窗口 + 查找表：O(n) 空间 O(k)
 */
public class Solution217 {

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> hashSet = new HashSet<>();
        for (int num:nums) {
            hashSet.add(num);
        }
        return hashSet.size() < nums.length;
    }

}
```

# 24、存在重复元素 II  

```
/**
 * 219、217（简化版219）：
 *      1、暴力枚举法：O(n^2)
 *      2、滑动窗口 + 查找表：O(n) 空间 O(k)
 *
 * O(n)
 * O(k)
 */
public class Solution219 {

    public boolean containsNearbyDuplicate(int[] nums, int k) {

        if (nums == null || nums.length <= 1) {
            return false;
        }

        if (k <= 0) {
            return false;
        }

        HashSet<Integer> record = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            // 1、在滑动窗口中只要有相同元素就是 true
            if (record.contains(nums[i])) {
                return true;
            }

            record.add(nums[i]);
            // 2、维护滑动窗口的大小：移除滑动窗口最左边的元素
            if (record.size() == k + 1) {
                record.remove(nums[i - k]);
            }
        }

        return false;
    }

}
```

# 25、存在重复元素 III  

```
/**
 * 220：
 *      1、滑动窗口 + set.lower_bound，注意使用 long 运算，避免整形溢出。
 *
 * O(nlog(k)
 * O(k)
 */
public class Solution220 {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

        // 1、异常处理
        if (nums == null || nums.length <= 1) {
            return false;
        }

        if (k <= 0 || t < 0) {
            return false;
        }

        TreeSet<Long> record = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {

            // 2、treeSet.lower_bound
            if (record.ceiling((long)nums[i] - (long)t) != null &&
                    record.ceiling((long)nums[i] - (long)t) <= ((long)nums[i] + (long)t)) {
                return true;
            }

            // 3、滑动窗口
            record.add((long) nums[i]);
            if (record.size() == k + 1) {
                record.remove((long)nums[i - k]);
            }
        }

        return false;
    }

}
```

# 26、有效的字母异位词  

```
/**
 * 242：
 *      1、空集
 *      2、字符集
 *
 * 可以用 HashMap 来映射字符与出现次数，然后比较两个字符串出现的字符数量是否相同。
 * 由于本题的字符串只包含 26 个小写字符，因此可以使用长度为 26 的整型数组
 * 对字符串出现的字符进行统计，不再使用 HashMap。
 */
public class Solution242 {

    public boolean isAnagram(String s, String t) {
        int[] countForNum = new int[26];
        for(char c:s.toCharArray()) {
            countForNum[c - 'a']++;
        }

        for (char c:t.toCharArray()) {
            countForNum[c - 'a']--;
        }

        for (int count:countForNum) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

}
```

# 27、两个数组的交集  

```
/**
 * 349（set）、350（map）：
 *      1、C++ 中 map find 不到元素时输出 0
 *
 * set 和 map 不同底层实现的区别
 *                  普通数组实现  顺序数组实现  二分搜索树（平衡）   哈希表
 *         插入       O(1)        O(n)        O(logn)         O(1)
 *         查找       O(n)        O(logn)     O(logn)         O(1)
 *         删除       O(n)        O(n)        O(logn)         O(1)
 *
 * 哈希表失去了数据的顺序性，顺序性即为：
 *       1、数据集中的最大值和最小值。
 *       2、某个元素的前驱和后继。
 *       3、某个元素的 floor 和 ceil。
 *       4、某个元素的排位 rank。
 *       5、选择某个排位的元素 select。
 * C++ 中 map 和 set 的底层实现为平衡二叉树，unordered_map 和 unordered_set 的底层实现为哈希表。
 *
 * O(len(nums1) + len(nums2))
 * O(len(nums1))
 */
public class Solution_349_2 {

    public int[] intersection(int[] nums1, int[] nums2) {

        HashSet<Integer> record = new HashSet<>();
        for (Integer item:nums1) {
            record.add(item);
        }

        HashSet<Integer> result = new HashSet<>();
        for (Integer item:nums2) {
            if (record.contains(item)) {
                result.add(item);
            }
        }

        int[] res = new int[result.size()];
        int i = 0;
        for (Integer item:result) {
            res[i++] = item;
        }

        return res;
    }

}
```

# 28、两个数组的交集 II  

```
/**
 * O(len(nums1) + len(nums2))
 * O(len(nums1))
 */
public class Solution350_2 {

    public int[] intersect(int[] nums1, int[] nums2) {

        HashMap<Integer, Integer> record = new HashMap<>();
        for (Integer item:nums1) {
            if (!record.containsKey(item)) {
                record.put(item, 1);
            } else {
                record.put(item, record.get(item) + 1);
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (Integer item:nums2) {
            if (record.containsKey(item) && record.get(item) > 0) {
                result.add(item);
                record.put(item, record.get(item) - 1);
            }
        }

        int[] res = new int[result.size()];
        int i = 0;
        for (Integer item:result) {
            res[i++] = item;
        }

        return res;
    }

}
```

# 29、回旋镖的数量  

```
/**
 * 1、枚举法（暴力法）：O(n^3)
 *   2、查找表，求距离的平方，避免浮点型运算（注意避免整形移除，当然，这个题不需要处理）：O(n^2)，空间 O(n)
 *
 * O(n^2)
 * O(n)
 */
public class Solution447 {

    public int numberOfBoomerangs(int[][] points) {

        int res = 0;
        for (int i = 0; i < points.length; i++) {

            // 1、hashMap 存储 dis:频次
            HashMap<Integer, Integer> record = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                // 2、计算距离时不开根号，避免整形溢出
                Integer dis = dis(points[i], points[j]);
                if (record.containsKey(dis)) {
                    record.put(dis, record.get(dis) + 1);
                } else {
                    record.put(dis, 1);
                }
            }

            // 3、？
            for (Integer dis:record.keySet()) {
                res += record.get(dis) * (record.get(dis) - 1);
            }
        }

        return res;
    }

    private Integer dis(int[] point1, int[] point2) {
        return (point1[0] - point2[0]) * (point1[0] - point2[0]) +
                (point1[1] - point2[1]) * (point1[1] - point2[1]);
    }

}
```

# 30、根据字符出现频率排序  

```
public class Solution451 {

    public String frequencySort(String s) {

        // 1、创建 HashMap 字符：频率
        HashMap<Character, Integer> freqCharMap = new HashMap<>();
        for (Character c:s.toCharArray()) {
            freqCharMap.put(c, freqCharMap.getOrDefault(c, 0) + 1);
        }

        // 2、创建桶 ArrayList[] 频率：字符列表
        List<Character>[] freqCharList = new ArrayList[s.length() + 1];
        for (char c:freqCharMap.keySet()) {
            int f = freqCharMap.get(c);
            if (freqCharList[f] == null) {
                freqCharList[f] = new ArrayList<>();
            }
            freqCharList[f].add(c);
        }

        // 3、拼装按频率高-低的字符串
        StringBuilder sb = new StringBuilder();
        for (int i = freqCharList.length - 1; i >= 0; i--) {
            if (freqCharList[i] != null) {
                for (char c:freqCharList[i]) {
                    for (int j = 0; j < i; j++) {
                        sb.append(c);
                    }
                }
            }
        }

        return sb.toString();
    }

}
```

# 31、四数相加 II  

```
/**
 * 454：
 *      1、暴力解法：O(n^4)
 *      2、将 D 中的元素放入查找表：O(n^3)
 *      3、将 C + D 的每一种可能放入查找表中：O(n^2)
 *
 * O(n^2)
 * O(n^2)
 */
public class Solution454_2 {

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {

        if (A == null || B == null || C == null || D == null) {
            throw new IllegalArgumentException("illegal argument!");
        }

        HashMap<Integer, Integer> mapAB = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int sum = A[i] + B[j];
                if (mapAB.containsKey(sum)) {
                    mapAB.put(sum, mapAB.get(sum) + 1);
                } else {
                    mapAB.put(sum, 1);
                }
            }
        }

        HashMap<Integer, Integer> mapCD = new HashMap<>();
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                int sum = C[i] + D[j];
                if (mapCD.containsKey(sum)) {
                    mapCD.put(sum, mapCD.get(sum) + 1);
                } else {
                    mapCD.put(sum, 1);
                }
            }
        }

        int res = 0;
        for (Integer sumAB:mapAB.keySet()) {
            if (mapCD.containsKey(-sumAB)) {
                res += mapAB.get(sumAB) * mapCD.get(-sumAB);
            }
        }

        return res;
    }

}
```

# 32、验证二叉搜索树

```
/**
 * 验证二叉搜索树：【递归】，又用到了树的经典划分：树=根+左子树+右子树。注意
 * 这里左子树和右子树是包含于原树的范围的； 避免 Integer. MIN_VALUE
 * 和 Integer.MAX_VALUE，否则一些特殊的测试用例就会挂掉--这也是我们
 * 使用Integer 而不是int作为上下限变量的原因。
 */
public class Solution98 {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isValidBST(TreeNode root){
        return helper(root,null,null);
    }

    private boolean helper(TreeNode node, Integer a, Integer b){
        if (node == null) return true;

        if(a!=null&&node.val<=a) return false;
        if(b!=null&&node.val>=b) return false;

        if (!helper(node.left,a,node.val)) return false;
        if (!helper(node.right,node.val,b)) return false;

        return true;
    }
}
```

# 33、对称二叉树  

```
public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public boolean isSymmetric(TreeNode root) {
    if (root == null) {
        return true;
    }
    return isSymmetric(root.left, root.right);
}

private boolean isSymmetric(TreeNode l1, TreeNode l2) {
    if (l1 == null && l2 == null) {
        return true;
    }
    if (l1 == null || l2 == null) {
        return false;
    }
    if (l1.val != l2.val) {
        return false;
    }
    return isSymmetric(l1.left, l2.right) && isSymmetric(l1.right, l2.left);
}
```

# 34、将有序数组转换为二叉搜索树  

```
public class Solution108 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return toBST(nums, 0, nums.length - 1);
    }

    private TreeNode toBST(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }

        // 1、根节点为中间的值
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        // 2、中序遍历思想构造二叉搜索树
        root.left = toBST(nums, start, mid - 1);
        root.right = toBST(nums, mid + 1, end);
        return root;
    }

}
```

# 35、平衡二叉树  

```
public class Solution110 {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private boolean result = true;

    public boolean isBalanced(TreeNode root) {
        maxDepth(root);
        return result;
    }

    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int l = maxDepth(root.left);
        int r = maxDepth(root.right);

        if (Math.abs(l - r) > 1) {
            result = false;
        }
        return 1 + Math.max(l , r);
    }

}
```

# 36、二叉树的最小深度  

```
public class Solution111 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = minDepth(root.left);
        int right = minDepth(root.right);

        if (left == 0 || right == 0) {
            return left + right + 1;
        }
        return Math.min(left, right) + 1;
    }

}
```

# 37、路径总和  

```
/**
 * 注意递归的终止条件
 */
public class Solution112 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean hasPathSum(TreeNode root, int sum) {

        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return root.val == sum;
        }

        return hasPathSum(root.left, sum - root.val) ||
                hasPathSum(root.right, sum - root.val);
    }

}
```

# 38、打家劫舍 

```
/**
 * DP：改变状态定义，优化转移方程
 * O(n)
 * O(n)
 */
public class Solution198_8 {

    public int rob(int[] nums) {

        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        // 考虑抢劫 [0..n) 房子得到的最大价值
        int[] memo = new int[n];
        memo[0] = nums[0];
        for (int i = 1; i < n; i++) {
            memo[i] = Math.max(memo[i - 1], nums[i] + (i - 2 >= 0 ? memo[i - 2] : 0));
        }
        return memo[n - 1];
    }

}
```

# 39、翻转二叉树  

```
/**
 * O(n)
 * O(h)
 */
public class Solution226 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode invertTree(TreeNode root) {

        if (root == null) {
            return null;
        }

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;

        return root;
    }

}
```

# 40、二叉搜索树中第K小的元素  

```
public class Solution230 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private int cnt = 0;
    private int val;

    public int kthSmallest(TreeNode root, int k) {
        inOrder(root, k);
        return val;
    }

    private void inOrder(TreeNode root, int k) {

        if (root == null) {
            return;
        }
        // 1、遍历到左边，由最小的值开始
        inOrder(root.left, k);
        cnt++;
        if (cnt == k) {
            val = root.val;
            return;
        }
        // 2、然后遍历右边，得到次小的值
        inOrder(root.right, k);
    }

}
```

# 41、二叉树的所有路径  

```
/**
 * 定义递归问题
 * O(n)
 * O(h)
 */
public class Solution257 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<String> binaryTreePaths(TreeNode root) {

        ArrayList<String> res = new ArrayList<>();
        
        if (root == null) {
            return res;
        }
        
        if (root.left == null && root.right == null) {
            res.add(Integer.toString(root.val));
            return res;
        }

        List<String> leftPaths = binaryTreePaths(root.left);
        for (String s:leftPaths) {
            StringBuilder sb = new StringBuilder(Integer.toString(root.val));
            sb.append("->");
            sb.append(s);
            res.add(sb.toString());
        }

        List<String> rightPaths = binaryTreePaths(root.right);
        for (String s:rightPaths) {
            StringBuilder sb = new StringBuilder(Integer.toString(root.val));
            sb.append("->");
            sb.append(s);
            res.add(sb.toString());
        }

        return res;
    }

}
```

# 42、左叶子之和  

```
public class Solution404 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (isLeaf(root.left)) {
            return root.left.val + sumOfLeftLeaves(root.right);
        }
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }

    private boolean isLeaf(TreeNode node) {
        if (node == null) {
            return false;
        }
        return node.left == null && node.right == null;
    }

}
```

# 43、路径总和 III  

```
/**
 * 更复杂的递归逻辑
 *      1、node 在路径的情况 && 在 node 的左右子树中去查找它们的和是否为 sum。
 *      2、node 在路径的情况需要处理为负数的情况，并且不一定需要计算到叶子节点。
 * O(n)
 * O(h)
 */
public class Solution437 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // 在以root为根节点的二叉树中,寻找和为sum的路径,返回这样的路径个数
    public int pathSum(TreeNode root, int sum) {

        if (root == null) {
            return 0;
        }

        return findPath(root, sum) + pathSum(root.left, sum)
                + pathSum(root.right, sum);
    }

    // 在以 root 为根节点的二叉树中，统计和为 sum 的节点的个数
    private int findPath(TreeNode root, int sum) {

        if (root == null) {
            return 0;
        }

        int res = 0;
        if (root.val == sum) {
            res += 1;
        }

        res += findPath(root.left, sum - root.val);
        res += findPath(root.right, sum - root.val);

        return res;
    }

}
```

# 44、移除链表元素  

```
/**
 *  Leetcode 203: Remove Linked List Elements
 *      删除多个节点时使用 while 循环。
 *      1、Solution：注意内存释放 + 不使用虚拟头结点。
 *      2、Solution2：不使用虚拟头结点。
 *      3、Solution3：使用虚拟头结点。
 */
public class Solution3 {

    public ListNode removeElements(ListNode head, int val) {

        // 给真实的头结点前面添加一个虚拟的头结点
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode pre = dummyHead;
        while (pre.next != null) {
            if (pre.next.val == val) {
                pre.next = pre.next.next;
            } else {
                pre = pre.next;
            }
        }

        return dummyHead.next;
    }


}

/**
* 递归
**/
public class Solution4 {

    public ListNode removeElements(ListNode head, int val) {

        if (head == null) {
            return null;
        }

        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }

}
```

# 45、电话号码的字母组合  

```
/**
 * 递归与回溯
 * 树形问题 17：
 *      1、字符串的合法性
 *      2、空字符串（null）
 *      3、多个解的顺序（无）
 *      4、digits 是数字字符串，s(digits) 是digits所能代表的字母字符串，
 *      s(digital[0...n-1])
 *          = letter(digital[0]) + letter(digital[1...n-1])
 *          = letter(digital[0]) + letter(digital[1]) + letter(digital[2...n-1])
 *          = ...
 *      5、递归调用的一个重要特征：要返回——回溯，它是暴力解法的一个主要实现手段。
 *      6、3^n = O(2^n)
 * O(2^len(s))
 * O(len(s)
 */
public class Solution17 {

    private String letterMap[] = {
            " ",    //0
            "",     //1
            "abc",  //2
            "def",  //3
            "ghi",  //4
            "jkl",  //5
            "mno",  //6
            "pqrs", //7
            "tuv",  //8
            "wxyz"  //9
    };

    private ArrayList<String> res;

    public List<String> letterCombinations(String digits) {

        res = new ArrayList<>();

        if (digits == null || digits.equals("")) {
            return res;
        }

        findCombinations(digits, 0, "");
        return res;
    }

    private void findCombinations(String digits, int index, String s) {

        if (digits.length() == index) {
            res.add(s);
            return;
        }

        Character c = digits.charAt(index);
        assert c.compareTo('0') >= 0 &&
                c.compareTo('9') <= 0 &&
                c.compareTo('1') != 0;
        String letter = letterMap[c - '0'];
        for (int i = 0; i < letter.length(); i++) {
            findCombinations(digits, index + 1, s + letter.charAt(i));
        }
    }

}
```

# 46、括号生成

```
/**
 * 括号生成：【递归】，分只能添加“（”，只能添加“）”，和有可以添加
 * “（”又能添加“）”三种情况。注意“）”的个数时时刻刻不能超过“（”。
 */
public class Solution22 {

    public List<String> generateParenthesis(int n) {
        add("", 0, 0, n);
        return list;
    }
    List<String> list=new ArrayList<>();
    void add(String s, int count1, int count2, int n){
        if(count1==count2&&count1<n){
            s+="(";
            add(new String(s), count1+1, count2, n);
        }else if (count1==n){
            while (count2<n){
                s+=")"; count2++;
            }
            list.add(new String(s));
        }else{
            add(new String(s+"("), count1+1, count2, n);
            add(new String(s+")"), count1, count2+1, n);
        }
    }
}
```

# 47、组合总和 

```
public class Solution39 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> combinations = new ArrayList<>();
        backtracking(new ArrayList<>(), combinations, 0, target, candidates);
        return combinations;
    }

    private void backtracking(ArrayList<Integer> tempCombination, List<List<Integer>> combinations, int start, int target, int[] candidates) {

        if (target == 0) {
            combinations.add(new ArrayList<>(tempCombination));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] <= target) {
                tempCombination.add(candidates[i]);
                backtracking(tempCombination, combinations, i, target - candidates[i], candidates);
                tempCombination.remove(tempCombination.size() - 1);
            }
        }
    }

}
```

# 48、组合总和 II 

```
public class Solution40 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        List<List<Integer>> combinations = new ArrayList<>();
        List<Integer> tempCombination = new ArrayList<>();
        boolean[] visited = new boolean[candidates.length];
        Arrays.sort(candidates);
        backtracking(tempCombination, combinations, visited, 0, target, candidates);

        return combinations;
    }

    private void backtracking(List<Integer> tempCombination, List<List<Integer>> combinations, boolean[] visited, int start, int target, int[] candidates) {

        if (target == 0) {
            combinations.add(new ArrayList<>(tempCombination));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // 去重处理
            if (i != 0 && candidates[i] == candidates[i - 1] && !visited[i - 1]) {
                continue;
            }
            if (candidates[i] <= target) {
                visited[i] = true;
                tempCombination.add(candidates[i]);
                backtracking(tempCombination, combinations, visited, i + 1, target - candidates[i], candidates);
                tempCombination.remove(tempCombination.size() - 1);
                visited[i] = false;
            }
        }
    }

}
```

# 49、N 皇后  

```
/**
 * 回溯法是经典人工智能的基础
 *      1、剪枝
 *      2、快速判断不合法的情况：
 *          竖向：col[i] 表示第i列被占用
 *          对角线1：dia1[i] 表示第i对角线1被占用—— 2*n-1个 i+j。对角线相加
 *          对角线2：dia2[i] 表示第i对角线2被占用—— 2*n-1个 i-j+n-1。对角线相减
 * O(n * n)
 * O(n)
 */
public class Solution51 {

    private boolean[] col;
    private boolean[] dia1;
    private boolean[] dia2;
    private List<List<String>> res;

    public List<List<String>> solveNQueens(int n) {

        col = new boolean[n];
        dia1 = new boolean[2 * n - 1];
        dia2 = new boolean[2 * n - 1];
        res = new ArrayList<>();

        LinkedList<Integer> row = new LinkedList<>();
        putQueue(n, 0, row);
        return res;
    }

    // 摆放第 index 行的皇后
    private void putQueue(int n, int index, LinkedList<Integer> row) {

        if (index == n) {
            res.add(generateBroad(n, row));
            return;
        }

        // 尝试将 index 行的皇后摆放在第 i 列
        for (int i = 0; i < n; i++) {
            if (!col[i] && !dia1[index + i] && !dia2[index - i + n - 1]) {
                row.addLast(i);
                col[i] = true;
                dia1[index + i] = true;
                dia2[index - i + n - 1] = true;
                putQueue(n , index + 1, row);
                col[i] = false;
                dia1[index + i] = false;
                dia2[index - i + n - 1] = false;
                row.removeLast();
            }
        }
    }

    private List<String> generateBroad(int n, LinkedList<Integer> row) {

        assert row.size() == n;

        ArrayList<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] charArray = new char[n];
            Arrays.fill(charArray, '.');
            charArray[row.get(i)] = 'Q';
            board.add(new String(charArray));
        }
        return board;
    }

}
```

# 50、组合

```
/**
 * 组合问题
 *      1、回溯法解决组合问题的优化：剪枝，避免最后的重复运算。
 * O(n^k)
 * O(k)
 */
public class Solution77_2 {

    private ArrayList<List<Integer>> res;

    public List<List<Integer>> combine(int n, int k) {

        res = new ArrayList<>();
        if (n <= 0 || k <= 0 || n < k) {
            return res;
        }

        LinkedList<Integer> c = new LinkedList<>();
        generateCombination(n, k, 1, c);
        return res;
    }

    private void generateCombination(int n, int k, int start, LinkedList<Integer> c) {

        if (k == c.size()) {
            res.add((List<Integer>) c.clone());
            return;
        }

        for (int i = start; i <= n - (k - c.size()) + 1; i++) {
            c.addLast(i);
            generateCombination(n, k, i + 1, c);
            c.removeLast();
        }
    }

}
```

# 51、子集

```
public class Solution78 {

    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> subsets = new ArrayList<>();
        List<Integer> tempSubset = new ArrayList<>();
        // 统计不同子集的大小
        for (int i = 0; i <= nums.length; i++) {
           backTracing(0, i, tempSubset, subsets, nums);
        }

        return subsets;
    }

    private void backTracing(int start, int size, List<Integer> tempSubset, List<List<Integer>> subsets, int[] nums) {

        if (tempSubset.size() == size) {
            subsets.add(new ArrayList<>(tempSubset));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            tempSubset.add(nums[i]);
            backTracing(i + 1, size, tempSubset, subsets, nums);
            tempSubset.remove(tempSubset.size() - 1);
        }
    }

}
```

# 51、单词搜索  

```
/**
 * 二维平面上的回溯法
 * O(m*n*m*n)
 * O(m*n)
 */
public class Solution79 {

    private int m;
    private int n;
    private int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private boolean[][] visited;

    public boolean exist(char[][] board, String word) {

        if (board == null || word == null) {
            throw new IllegalArgumentException("board and word is null!");
        }

        m = board.length;
        if (m == 0) {
            throw new IllegalArgumentException("board length is illegal argument");
        }

        n = board[0].length;
        if (n == 0) {
            throw new IllegalArgumentException("board length is illegal argument");
        }

        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (searchWord(board, word, 0, i, j)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean searchWord(char[][] board, String word, int index, int startX, int startY) {

        if (index == word.length() - 1) {
            return word.charAt(index) == board[startX][startY];
        }

        if (word.charAt(index) == board[startX][startY]) {
            visited[startX][startY] = true;
            for (int i = 0; i < 4; i++) {
                int newX = startX + d[i][0];
                int newY = startY + d[i][1];
                if (isAccess(newX, newY) && !visited[newX][newY] &&
                            searchWord(board, word, index + 1, newX, newY)) {
                    return true;
                }
            }
            visited[startX][startY] = false;
        }

        return false;
    }

    private boolean isAccess(int newX, int newY) {
        return newX >= 0 && newX < m && newY >= 0 && newY < n;
    }

}
```

# 52、子集 II  

```
public class Solution90 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {

        List<List<Integer>> subsets = new ArrayList<>();
        List<Integer> tempSubset = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        // 统计不同大小的子集
        for (int i = 0; i <= nums.length; i++) {
            backTracing(0, i, visited, tempSubset, subsets, nums);
        }

        return subsets;
    }

    private void backTracing(int start, int size, boolean[] visited, List<Integer> tempSubset, List<List<Integer>> subsets, int[] nums) {

        if (tempSubset.size() == size) {
            subsets.add(new ArrayList<>(tempSubset));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            // 防重处理
            if (i != 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }
            visited[i] = true;
            tempSubset.add(nums[i]);
            backTracing(i + 1, size, visited, tempSubset, subsets, nums);
            tempSubset.remove(tempSubset.size() - 1);
            visited[i] = false;
        }
    }

}
```

# 53、复原IP地址

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

# 54、被围绕的区域  

```
/**
 * floodfill 洪水填充算法——深度优先遍历
 */
public class Solution130 {

    int[][] d = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int m , n;

    public void solve(char[][] board) {

        if (board == null || board.length == 0) {
            return;
        }

        m = board.length;
        n = board[0].length;

        for (int i = 0; i < m; i++) {
            dfs(board, i, 0);
            dfs(board, i, n - 1);
        }

        for (int i = 0; i < n; i++) {
            dfs(board, 0, i);
            dfs(board, m - 1, i);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'T') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'O') {
            return;
        }
        board[i][j] = 'T';
        for (int[] d : d) {
            dfs(board, i + d[0], j + d[1]);
        }
    }

}
```

# 55、分割回文串  

```
/**
 * 递归与回溯
 */
public class Solution131 {

    public List<List<String>> partition(String s) {

        List<List<String>> partitions = new ArrayList<>();
        List<String> tempPartition = new ArrayList<>();
        backTracing(s, tempPartition, partitions);

        return partitions;
    }

    private void backTracing(String s, List<String> tempPartition, List<List<String>> partitions) {

        if (s.length() == 0) {
            partitions.add(new ArrayList<>(tempPartition));
            return;
        }

        for (int i = 0; i < s.length(); i++) {
            if (isPalindrome(s, 0, i)) {
                tempPartition.add(s.substring(0, i + 1));
                backTracing(s.substring(i + 1), tempPartition, partitions);
                tempPartition.remove(tempPartition.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int start, int end) {

        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }

        return true;
    }

}
```

# 56、岛屿数量  

```
/**
 * O(m * n)
 * O(m * n)
 * 岛屿数量: 求连通分支个数【dfs】和 【union find】。使用unionfind的原理为：
 * 顶点数-最小生成树连线数=连通分支个数。
 */
public class Solution200 {

    private int[][] d = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int m, n;
    private boolean[][] visited;

    public int numIslands(char[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int res = 0;
        m = grid.length;
        n = grid[0].length;

        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    dfs(grid, i, j);
                    res++;
                }
            }
        }

        return res;
    }

    // 深度优先遍历当前岛屿占用区域
    private void dfs(char[][] grid, int x, int y) {

        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int newX = x + d[i][0];
            int newY = y + d[i][1];
            if (inArea(newX, newY) && !visited[newX][newY] &&
                    grid[newX][newY] == '1') {
                dfs(grid, newX, newY);
            }
        }
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

}
```

# 57、组合总和 III  

```
public class Solution216 {

    public List<List<Integer>> combinationSum3(int k, int n) {

        List<List<Integer>> combinations = new ArrayList<>();
        List<Integer> tempCombination = new ArrayList<>();
        backtracking(k, n, 1, tempCombination, combinations);

        return combinations;
    }

    private void backtracking(int k, int n, int start, List<Integer> tempCombination, List<List<Integer>> combinations) {

        if (k == 0 && n == 0) {
            combinations.add(new ArrayList<>(tempCombination));
            return;
        }

        if (k == 0 || n == 0) {
            return;
        }

        for (int i = start; i <= 9; i++) {
            tempCombination.add(i);
            backtracking(k - 1, n - i, i + 1, tempCombination, combinations);
            tempCombination.remove(tempCombination.size() - 1);
        }
    }

}
```

# 58、最大子序和  

```
class Solution53 {

    // 一次循环，maxEndingHere储存以该点结尾的子序和，maxSoFar储存到该点的最大子序和。
    public int maxSubArray(int[] nums) {
        if (nums.length==0) return 0;
        if (nums.length==1) return nums[0];

        int maxEndingHere = nums[0];
        int maxSoFar = nums[0];
        for (int i = 1; i<nums.length; i++){
            maxEndingHere = Math.max(nums[i],nums[i]+maxEndingHere);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }
}
```

# 59、不同路径

```
/**
 * 不同路径：要么【用数学递推公式】，X要么就根据定义【直接计算】。直接计算时需要注意
 * Java “/”的整除性质，溢出，乘除顺序。
 */
public class Solution62 {

    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[n - 1];
    }

}
```

# 60、最小路径和

```
/**
 * 最小路径和：其实到达某点的最小路径，只与该点数值、到达该点左边
 * 的最小路径、到达该点上边的最小路径有关。所以只要找到
 * 【正确循环顺序】，就可以避免所有的中间储存，两层循环即可。
 */
public class Solution64 {

    public int minPathSum(int[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    // 只能从上侧走到该位置
                    dp[j] = dp[j];
                } else if (i == 0) {
                    // 只能从左侧走到该位置
                    dp[j] = dp[j - 1];
                } else {
                    dp[j] = Math.min(dp[j], dp[j - 1]);
                }
                dp[j] += grid[i][j];
            }
        }

        return dp[n - 1];
    }

}
```

# 61、编辑距离  

```
/**
 * 动态规划给出具体解：反向求出具体的解，甚至是所有的解。
 */
public class Solution72 {

    public int minDistance(String word1, String word2) {

        // 1、创建 dp 数组并赋值上边界和左边界
        if (word1 == null || word2 == null) {
            return 0;
        }

        int m = word1.length();
        int n = word2.length();

        // 注意
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i <= n; i++) {
            dp[0][i] = i;
        }

        // 2、处理编辑距离
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j],
                            dp[i][j - 1])) + 1;
                }
            }
        }

        return dp[m][n];
    }

}
```

# 62、解码方法  

```
public class Solution91 {

    public int numDecodings(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        // 1、预先处理 dp[0] 与 dp[1]
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for (int i = 2; i <= n; i++) {
            // 2、累加前一个位数计算得到的值
            int one = Integer.parseInt(s.substring(i - 1, i));
            if (one != 0) {
                dp[i] += dp[i - 1];
            }

            if (s.charAt(i - 2) == '0') {
                continue;
            }

            // 3、计算当前位数需累加的值
            int two = Integer.parseInt(s.substring(i - 2, i));
            if (two <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

}
```

# 63、单词拆分  

```
public class Solution139 {

    public boolean wordBreak(String s, List<String> wordDict) {

        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for(String word:wordDict) {
                int len = word.length();
                if (len <= i && word.equals(s.substring(i - len, i))) {
                    dp[i] = dp[i] || dp[i - len];
                }
            }
        }

        return dp[n];
    }

}
```

# 64、打家劫舍

```
/**
* 状态的定义和状态转移
*      1、暴力解法：检查所有房子的组合，对每一个组合，检查是否有相邻的房子，如果没有，记录其价值。找最大值。O((2^n)*n)
*      2、注意其中对状态的定义：
*      函数的定义：考虑偷取[x...n-1]范围内的房子。
*      根据对状态的定义，决定状态转移方程：
*      f(0) = max{v(0) + f(2), v(1) + f(3) , ... , v(n-3) + f(n-1) ,v(n-2),v(n-1)}
*   记忆化搜索：改变状态定义，优化转移方程 O(n)
*/
public class Solution198_7 {

    private int[] memo;

    public int rob(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return tryRob(nums, nums.length - 1);
    }

    private int tryRob(int[] nums, int index) {

        if (index < 0) {
            return 0;
        }

        if (memo[index] != -1) {
            return memo[index];
        }

        // 放弃当前房子考虑抢劫下一个房子 或 抢劫当前房子考虑下下一个房子
        return memo[index] = Math.max(tryRob(nums, index + 1),
                nums[index] + tryRob(nums, index + 2));
    }

}
```

# 65、打家劫舍 II  

```
public class Solution213 {

    public int rob(int[] nums) {


        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }

        return Math.max(tryRob(nums, 0, n - 2), tryRob(nums, 1, n - 1));
    }

    private int tryRob(int[] nums, int first, int last) {

        int pre2 = 0, pre1 = 0;
        for (int i = first; i <= last; i++) {
            int cur = Math.max(pre1, pre2 + nums[i]);
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }

}
```

# 66、最大正方形

```
/**
 * 最大正方形：可以用动态规划，以某点为右下角的最大正方形边长 只与
 * 以该点上面、左边、左上相邻点为右下角的最大正方形边长有关，
 * 取最小+1的关系。用二维数组储存结果需要补充上边和左边的
 * 数组 2d dp，也可以用一位数组储存结果，更节约空间 1d dp。
 */
public class Solution221 {

    public int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        int col = matrix.length==0? 0:matrix[0].length;
        int[] dp = new int[col+1];
        int ans = 0;
        for (int i = 1; i < row+1; i++){
            int prev = 0;
            for (int j = 1; j < col+1; j++){
                int temp = dp[j];
                if (matrix[i-1][j-1]=='1'){
                    dp[j] = Math.min(Math.min(dp[j-1],dp[j]),prev)+1;
                    ans = Math.max(ans,dp[j]);
                } else {dp[j] = 0;}
                prev = temp;
            }
        }
        return ans*ans;
    }
}
```

# 67、滑动窗口最大值  

```
public class Solution239 {

public ArrayList<Integer> maxInWindows(int[] num, int size) {
    ArrayList<Integer> ret = new ArrayList<>();
    if (size > num.length || size < 1)
        return ret;
    PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);  /* 大顶堆 */
    for (int i = 0; i < size; i++)
        heap.add(num[i]);
    ret.add(heap.peek());
    for (int i = 0, j = i + size; j < num.length; i++, j++) {            /* 维护一个大小为 size 的大顶堆 */
        heap.remove(num[i]);
        heap.add(num[j]);
        ret.add(heap.peek());
    }
    return ret;
}
```

# 68、完全平方数  

```
public class Solution279 {

    public int numSquare(int n) {
        List<Integer> squareList = generateSquareList(n);
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int square:squareList) {
                if (i >= square) {
                    min = Math.max(min, dp[i - square] + 1);
                }
            }
            dp[i] = min;
        }

        return dp[n];
    }

    private List<Integer> generateSquareList(int n) {

        List<Integer> squareList = new ArrayList<>();
        int square = 1;
        int diff = 3;
        while (square <= n) {
            squareList.add(square);
            square += diff;
            diff += 2;
        }
        return squareList;
    }

}
```

# 69、最佳买卖股票时机含冷冻期

```
/**
 * 最佳买卖股票时机含冷冻期: 重点在于【状态划分】，我们要确保这样
 * 的状态划分保证每一笔交易都有冷冻期。
 *
 * sold：在当天卖出股票，必须由hold状态而来。 hold：当天持有股票，
 * 可能是当天买入，或者之前买入的。可以由rest或者hold状态而来。
 * rest：当天不持有股票，可能是前一天卖出的，也可能是更早卖出的。
 * 可以由sold或者rest状态而来。 这样的状态划分，我们可以看到，
 * 要从sold状态进入hold状态必须经过至少一次的rest，这就满足了
 * 冷冻期的要求。需要注意的是初始值的选取，可以通过对第一天情况
 * 代入来选取。这里sold选取的是0，但实际上只要取一个非负数就好。
 */
public class Solution309 {

    public int maxProfit(int[] prices) {
        int hold = Integer.MIN_VALUE;
        int sold = 0;
        int rest = 0;
        for (int price : prices){
            int pre = sold;
            sold = hold + price;
            hold = Math.max(hold,rest-price);
            rest = Math.max(pre,rest);
        }
        return Math.max(sold,rest);
    }
}
```

# 70、零钱兑换  

```
public class Solution322 {

    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

}
```

# 71、打家劫舍 III  

```
public class Solution337 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 1、计算第 1、3...层节点的总值
        int val1 = root.val;
        if (root.left != null) {
            val1 += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            val1 += rob(root.right.left) + rob(root.right.right);
        }

        int val2 = rob(root.left) + rob(root.right);
        return Math.max(val1, val2);
    }

}
```

# 72、整数拆分  

```
/**
* 发现重叠的子问题
*      1、暴力解法：回溯遍历将一个树做分割的所有可能性。
*      2、记忆化搜索
*      3、dp + 记忆化搜索
* O(n ^ 2)
* O(n)
*/
public class Solution343_3 {

    private int[] memo;

    public int integerBreak(int n) {

        if (n < 1) {
            throw new IllegalArgumentException("n is illegal argument!");
        }

        memo = new int[n + 1];
        Arrays.fill(memo, -1);

        memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            // 求解 memo[i]
            for (int j = 1; j <= i - 1; j++) {
                memo[i] = max3(memo[i], j * (i - j), j * memo[i - j]);
            }
        }
        return memo[n];
    }

    private int max3(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

}
```

# 73、摆动序列  

```
/**
 * 最长公共子序列 LCS
 *      1、LCS(m, n) S1[0...m] 和 S2[0...n] 的最长公共子序列的长度。
 *      S1[m] == S2[n]: LCS(m, n) = 1 + LCS(m-1, n-1)
 *      S1[m] != S2[n]: LCS(m, n) = max( LCS(m-1, n), LCS(m, n-1))
 *
 * dijkstra 单源最短路径算法也是动态规划
 *      1、shortestPath(i) 为从start到i的最短路径长度。
 *      shortestPath(x) = min(shortestPath(a) + w(a->x))
 */
public class Solution376 {

    public int wiggleMaxLength(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 设立一个 up 和 down 变量
        int up = 1, down = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            } else if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }

}
```

# 74、分割等和子集  

```
/**
 * 0-1背包问题
 *      1、暴力解法：每一件物品都可以放进背包，也可以不放进背包。O((2^n)*n)
 *      2、动态规范——状态转移方程：F(n, C) 考虑将n个物品放进容量为C的背包，使得价值最大。
 *      F(n, C) = max(F(i-1, c), v(i) + F(i-1, c - w(i))
 *      O(n*C) O(n*C)
 *      3、优化：根据状态转移方程，第i行元素只依赖于第i-1行元素。理论上，只需要保持两行元素。
 *      空间复杂度：O(2*C） = O(c)
 *      4、优化加强：只使用一行大小为C的数组完成动态规划？
 *          从后向前刷新当前 memo 中元素。
 * 0-1背包问题的更多变种
 *      1、完全背包问题：每个物品可以无限使用。
 *      2、多重背包问题：每个物品不止1个，有num(i)个。
 *      3、多维费用背包问题：要考虑物品的体积和重量两个维度？
 *      4、物品间加入更多约束：物品间可以相互排斥或依赖。
 *
 * 416：
 *      1、F(n, C) 考虑将n个物品填满容量为C的背包。
 *      2、F(i, c) = F(i-1, c) || F(i-1, c-w(i))
 *      3、时间复杂度：O(n*sum/2) = O(n*sum)
 *      4、空间复杂度：O(n * sum)
 */
public class Solution416_2 {

    public boolean canPartition(int[] nums) {

        // 1、判断 nums 数组的和是否为偶数
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) {
                throw new IllegalArgumentException("illegal argument!");
            }
            sum += nums[i];
        }

        if (sum % 2 == 1) {
            return false;
        }

        int C = sum / 2;

        // 2、创建 memo 记忆化数组，并同步 nums 数组的第一个元素的状态为 true
        boolean[] memo = new boolean[C + 1];
        for (int i = 0; i <= C; i++) {
            memo[i] = (nums[0] == i);
        }

        // 3、使用背包状态转移方程
        for (int i = 1; i < n; i++) {
            for (int j = C; j >= nums[i]; j--) {
                memo[j] = memo[j] || memo[j - nums[i]];
            }
        }

        return memo[C];
    }

}
```

# 75、目标和

```
/**
 * 目标和：一个朴素的想法是用【暴力破解】 ，枚举所有的正负组合方式，
 * 对每一次结果进行筛选，但是这样就超时了。这样的暴力破解法也可以
 * 写成【递归】 的形式（会导致一部分不可避免的重复运算）。
 * 但其实呢，我们可以把所有的数分为两部分，第一部分用加号，
 * 第二部分用减号，由此可以得出用加号部分的和。问题就被转
 * 化成了 416. 分割等和子集 ，即寻找子集使得和为某一特定
 * 数字，每一个数字仅能用1次。
 */
public class Solution494 {

    public int findTargetSumWays(int[] nums, int S) {

        // 1、计算数组和
        int sum = countSum(nums);

        if (sum < S || (sum + S) % 2 == 1) {
            return 0;
        }

        int W = (sum + S) / 2;
        int[] dp = new int[W + 1];
        dp[0] = 1;
        for (int num:nums) {
            for (int i = W; i >= num; i--) {
                dp[i] = dp[i] +  dp[i - num];
            }
        }

        return dp[W];
    }

    private int countSum(int[] nums) {
        int res = 0;
        for (int i:nums) {
            res += i;
        }
        return res;
    }

}

/**
* 递归
**/
public class Solution494_2 {

    public int findTargetSumWays(int[] nums, int S) {
        return findTargetSumWays(nums, 0, S);
    }

    private int findTargetSumWays(int[] nums, int start, int s) {
        if (start == nums.length) {
            return s == 0 ? 1 : 0;
        }

        return findTargetSumWays(nums, start + 1, s + nums[start]) +
                findTargetSumWays(nums, start + 1, s - nums[start]);
    }

}
```

# 76、斐波那契数  

```
/**
 * 动态规划（DP）+ 记忆化搜索
 */
public class Solution509_3 {

    public int fib(int n) {

        if (n == 0) {
            return 0;
        }

        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);

        memo[0] = 0;
        memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }

        return memo[n];
    }

}
```

# 77、判断子序列  

```
public class Solution392 {

    public boolean isSubsequence(String s, String t) {
        int index = -1;
        for (char c:s.toCharArray()) {
            index = t.indexOf(c, index + 1);
            if (index == -1) {
                return false;
            }
        }
        return true;
    }
}
```

# 78、无重叠区间  

```
/**
 * 贪心算法与动态规划的关系 435：
 *      1、最多保留多少个区间。
 *      2、暴力解法：找出所有子区间的组合，之后判断它不重叠。O((2^n)*n)
 *      3、排序后，动态规划——最常上升子序列模型。
 *      4、注意：每次选择中，每个区间的结尾很重要，结尾越小，留给后面的空间越大，所以后面能容纳更多区间。
 *      ——设计出贪心算法：按照区间的结尾排序，每次选择结尾最早的，且和前一个区间不重叠的区间。
 *      5、贪心选择性质的证明：1）、举出反例。2）、反证法：贪心算法为A，最优算法为O，发现A完全能替代O，
 *      且不影响求出最优解。
 * 设计贪心算法：按照区间的结尾排序，每次选择区间结尾最早的，且和前一个区间不重叠额的区间。
 * O(n)
 * O(n)
 */
public class Solution435_2 {

    // Definition for an interval.
    public static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    public int eraseOverlapIntervals(Interval[] intervals) {

        // 1、按照区间的结尾排序
        if (intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.end != o2.end) {
                    return o1.end - o2.end;
                }
                return o1.start - o2.start;
            }
        });

        // 2、贪心算法：每次选择区间结尾最早的，且和前一个区间不重叠的区间
        int res = 1;
        int pre = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start >= intervals[pre].end) {
                res++;
                pre = i;
            }
        }

        return intervals.length - res;
    }

}
```

# 79、分发饼干  

```
/**
 * 先尝试将最大的饼干分配给最贪心的小朋友
 * O(nlogn)
 * O(1)
 */
public class Solution455 {

    public int findContentChildren(int[] g, int[] s) {

        // 1、排序
        Arrays.sort(g);
        Arrays.sort(s);

        // 2、先尝试将最大的饼干分配给最贪心的小朋友
        int gi = g.length - 1;
        int si = s.length - 1;
        int res = 0;
        while (gi >= 0 && si >= 0) {
            if (s[si] >= g[gi]) {
                res++;
                si--;
            }
            gi--;
        }
        return res;
    }

}

/**
 * 先尝试满足最不贪心的小朋友
 * O(nlogn)
 * O(1)
 */
public class Solution455_2 {

    public int findContentChildren(int[] g, int[] s) {

        // 1、排序
        Arrays.sort(g);
        Arrays.sort(s);

        // 2、先尝试满足最不贪心的小朋友
        int gi = 0;
        int si = 0;
        int res = 0;
        while (gi < g.length && si < s.length) {
            if (s[si] >= g[gi]) {
                res++;
                gi++;
            }
            si++;
        }

        return res;
    }

}
```

# 80、回文数  

```
/**
 * 要求不能使用额外空间，也就不能将整数转换为字符串进行判断。
 *
 * 将整数分成左右两部分，右边那部分需要转置，然后判断这两部分是否相等。
 */
public class Solution9 {

    public boolean isPalindrome(int x) {
        if (x == 0) {
            return true;
        }
        if (x < 0 || x % 10 == 0) {
            return false;
        }

        int right = 0;
        while (right < x) {
            right = right * 10 + x % 10;
            x /= 10;
        }
        return x == right || x == right / 10;
    }

}
```

# 81、x 的平方根

```
public class Solution69 {

    public int mySqrt(int x) {

        if (x <= 1) {
            return x;
        }

        int l = 0, h = x;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            int sqrt = x / mid;
            if (mid == sqrt) {
                return mid;
            } else if (mid > sqrt) {
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return h;
    }

}
```

# 82、只出现一次的数字  

```
/**
 * 两个相同的数异或的结果为 0，对所有数进行异或操作，最后的结果就是单独出现的那个数。
 */
public class Solution136 {

    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num:nums) {
            res = res ^ num;
        }
        return res;
    }

}
```

# 83、2的幂  

```
/**
 * 二进制表示只有一个 1 存在。
 */
public class Solution231 {

    public boolean isPowerOfTwo(int n) {
        return n > 0 && Integer.bitCount(n) == 1;
    }

}

/**
 * 利用 1000 & 0111 == 0
 */
public class Solution231_2 {

    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

}
```






