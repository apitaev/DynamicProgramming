package com.pitaev.leetcode.dp.counting;

/**
 * This class implements a solution for a leetcode problem <code>377</code>:
 * <a href="https://leetcode.com/problems/combination-sum-iv/description/">Combination sum iv</a>
 * <p>Time complexity: O(nums.length * target)
 * <p>Space complexity: O(target)
 */
public class CombinationSumIV {
    public int combinationSum4(int[] nums, int target) {
        // f(t) = # ways to construct t using integers in the nums array
        // f(t) = Sum(f(t - nums[i])), where i in 0..n
        int [] table = new int[target + 1];
        // empty set can be used to construct 0 value
        table[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i - num >=  0) {
                    table[i] += table[i - num];
                }
            }
        }
        return table[target];
    }
}
