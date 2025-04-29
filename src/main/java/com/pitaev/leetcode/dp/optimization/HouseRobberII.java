package com.pitaev.leetcode.dp.optimization;

import java.util.Arrays;

/**
 * This class implements a solution for a leetcode problem <code>213</code>:
 * <a href="https://leetcode.com/problems/house-robber-ii/description"/>House Robber II</a>
 * <p>Time complexity: O(n)
 * <p>Space complexity: O(n)
 */
public class HouseRobberII {
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        int n = nums.length;
        // let's break a cycle and apply straight line solution on partial problems
        // Partial problem 1, house 0 is included, house 1, is excluded, house n - 1 is excluded
        int [] nums1 = Arrays.copyOfRange(nums, 2, n - 1);
        // Partial problem 2, house 0 is excluded, remaining array 1, n - 1
        int [] nums2 = Arrays.copyOfRange(nums, 1, n);
        return Math.max(nums[0] + getNumsInStraightLine(nums1), getNumsInStraightLine(nums2));
    }

    private static int getNumsInStraightLine(int [] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        // table[i] the maximum amount that can be robbed if you have i + 1 houses
        int [] table = new int[nums.length];
        table[0] = nums[0];
        table[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            //1.  house i included: nums[i] + table[i - 2]
            //2.  or house i excluded: table[i - 1]
            // table [i] is max of 1 and 2.
            table[i] = Math.max(nums[i] + table[i - 2], table[i - 1]);
        }
        return table[nums.length - 1];
    }
}
