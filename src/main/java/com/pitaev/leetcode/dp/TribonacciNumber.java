package com.pitaev.leetcode.dp;

/**
 * This class implements a solution for a leetcode problem <code>1137</code>:
 * <a href="https://leetcode.com/problems/n-th-tribonacci-number/description/">Tribonacci Number</a>
 * <p>Time complexity: O(n)
 * <p>Space complexity: O(1), we only use three previous columns
 */
public class TribonacciNumber {
    public int tribonacci(int n) {
        if (n == 0 || n == 1) return n;
        if (n == 2) return 1;
        int [] table = new int[3];
        table[1] = 1;
        table[2] = 1;
        for (int i = 3; i <= n; i++) {
            table[i % 3] = table[(i - 1) % 3] + table[(i - 2) % 3] + table[(i - 3) % 3];
        }
        return table[n % 3];
    }
}
