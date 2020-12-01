package array_problem;

import java.util.HashSet;

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
