package dynamic_problem;

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
