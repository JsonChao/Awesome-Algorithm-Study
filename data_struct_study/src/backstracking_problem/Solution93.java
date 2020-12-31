package backstracking_problem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


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

