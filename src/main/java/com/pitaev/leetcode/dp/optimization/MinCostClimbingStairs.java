package com.pitaev.leetcode.dp.optimization;

import static java.lang.Math.min;
/**
 * This class implements a solution for a leetcode problem <code>746</code>:
 * <a href="https://leetcode.com/problems/min-cost-climbing-stairs/>Min cost climbing stairs</a>
 * <p>Time complexity: O(n)
 * <p>Space complexity: O(1)
 */
public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        int numberOfStairs = cost.length + 1;
        int [] table = new int[2];
        // base cases
        table[0] = cost[0];
        table[1] = cost[1];
        for (int i = 2; i < numberOfStairs; i++) {
            if (i < cost.length) {
                table[i % 2] = Math.min(table[(i - 1) % 2], table[(i - 2) % 2]) + cost[i];
            } else {
                // top of the floor is free of charge
                table[i % 2] = Math.min(table[(i - 1) % 2], table[(i - 2) % 2]);
            }
        }
        return table[(numberOfStairs - 1) % 2];

    }
}
