package com.pitaev.leetcode.dp;
/**
 * This class implements a solution for a leetcode problem <code>70</code>:
 * <a href="https://leetcode.com/problems/climbing-stairs/description/">Climbing Stairs</a>
 * <p>Time complexity: O(n)
 * <p>Space complexity: O(1), we only use two previous columns
 */
public class ClimbingStairs {
    public int climbStairs(int n) {
        if (n == 0 || n == 1 || n == 2) return n;
        int [] table = new int[2];
        table[1] = 1;
        table[0] = 2;
        for (int i = 3; i <= n; i++) {
            table[i % 2] = table[(i - 1) % 2] + table[(i - 2) % 2];
        }
        return table[n % 2];
    }
}
