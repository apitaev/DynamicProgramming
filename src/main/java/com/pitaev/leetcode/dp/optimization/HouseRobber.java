package com.pitaev.leetcode.dp.optimization;

/**
 * This class implements a solution for a leetcode problem <code>198</code>:
 * <a href="https://leetcode.com/problems/house-robber/"/>House Robber</a>
 * <p>Time complexity: O(n)
 * <p>Space complexity: O(n)
 */
public class HouseRobber {
    public int rob(int[] nums) {
        // table[i] is the max amount you can get from i houses
        int [] table = new int [nums.length];
        if (nums.length == 1) return nums[0];
        table[0] = nums[0];
        table[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            // ith house can be excluded, max amount table[i - 1]
            // or it can be included, max amount table[i - 2] + nums[i]
            table[i] = Math.max(table[i - 2] + nums[i], table[i - 1]);
        }
        return table[nums.length - 1];
    }
}
