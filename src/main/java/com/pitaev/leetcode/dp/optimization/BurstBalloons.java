package com.pitaev.leetcode.dp.optimization;

/**
 * This class implements a solution for a leetcode problem <code>312</code>:
 * <a href="https://leetcode.com/problems/burst-balloons/description//>Burst balloons</a>
 * This solution uses matrix multiplication approach
 * <p>Time complexity: O(n^3)
 * <p>Space complexity: O(n^2)
 */
public class BurstBalloons {
    public int maxCoins(int[] nums) {
        //update nums array to add 1's
        double[] full = new int[nums.length + 2];
        full[0] = 1;
        int i;
        for (i = 1; i <= nums.length; i++) {
            full[i] = nums[i - 1];
        }
        full[i] = 1;
        // split array in two sub-arrays (can be seen as two subtrees) i..k and k..j
        // so that balloon k is going to be burst
        // calculate f(i,k)
        // calculate f(k,j)
        // f(i,j) = max(f(i,j), f(i,k) + f(k, j) + full[i]* f[k]*f[j])
        // dp table
        int[][] table = new int[full.length][full.length];
        // we start row from n - 2, we need at list 2 elements on the right
        for (int row = full.length - 2; row >= 0; row--) {
            // we start column as the right subtree, it should be at least at index row + 1
            // the root of the tree lies at the position row + 1
            for (int col = row + 2; col < full.length; col++) {
                // k index - can be seen as a root of the tree, it is a balloon we try to burst
                for (int k = row + 1; k <= col - 1; k++) {
                    table[row][col] = Math.max(table[row][col], table[row][k] + table[k][col] + full[row] * full[k] * full[col]);
                }

            }
        }
        return table[0][full.length - 1];
    }
}
