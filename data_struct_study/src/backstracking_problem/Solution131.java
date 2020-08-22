package backstracking_problem;

import java.util.ArrayList;
import java.util.List;

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
