package com.pitaev.leetcode.dp.decision;

/**
 * This class implements a solution for a leetcode problem <code>416</code>:
 * <a href="https://leetcode.com/problems/partition-equal-subset-sum/description/">PartitionEqualSubsetString</a>
 * <p>Time complexity: O(n * m) where n is a length of nums and m is a target
 * <p>Space complexity: O (n * m), can optimize use tables with 2 rows
 */
public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        // combination problem with include / exclude decision
        // edge case: if the sum of nums is odd return false
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) return false;
        int target = sum / 2;
        // Overall question: is there a subset that sums to k
        boolean [][] table = new boolean[nums.length + 1][target + 1];
        // empty set can be built from the empty set
        table[0][0] = true;
        // base cases:
        // first row: build sum from empty set, all cells are false by default
        // fist column: buld 0 sum from any subset, always true (just exclude all elements)
        for (int i = 1; i <= nums.length; i++) {
            table[i][0] = true;
        }
        // recursive case: f(n, k), where nth digit in nums, k target
        // Exclude the last number
        // Include the last number
        // f(n, k) = f(n - 1, k) or f(n - 1, k - nth element)
        // f(n, k) = f(n - 1, k - nth) || f(n - 1, k)
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= target; j++) {
                if (j >= nums[i - 1]) {
                    // include case + exclude case
                    table[i][j] = table[i - 1][j - nums[i - 1]] || table[i - 1][j];
                } else {
                    // exclude case only
                    table[i][j] = table[i - 1][j];
                }
            }

        }
        return table[nums.length][target];

    }
}
