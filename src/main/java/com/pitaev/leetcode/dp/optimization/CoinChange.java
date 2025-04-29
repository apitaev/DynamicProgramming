package com.pitaev.leetcode.dp.optimization;

/**
 * This class implements a solution for a leetcode problem <code>322</code>:
 * <a href="https://leetcode.com/problems/coin-change/description/>Coin change</a>
 * <p>Time complexity: O(n)
 * <p>Space complexity: O(n)
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        Integer [] table = new Integer[amount + 1];
        // base case
        table[0] = 0;
        for (int i = 1; i <= amount; i++) {
            Integer minValue = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i - coin >= 0) {
                    minValue = Math.min(minValue, table[i - coin]);
                }
            }
            table[i] = minValue.equals(Integer.MAX_VALUE) ? minValue : minValue + 1;
        }
        Integer result = table[amount].equals(Integer.MAX_VALUE) ? -1 : table[amount];
        return result;
    }
}
