package com.pitaev.leetcode.dp.counting;

/**
 * This class implements a solution for a leetcode problem <code>509</code>:
 * <a href="https://leetcode.com/problems/fibonacci-number/description/">Fibonacci Number</a>
 * <p>Time complexity: O(n)
 * <p>Space complexity: O(1), we only use two previous columns
 */
public class FibonacciNumber {
    public int fib(int n) {
        if (n == 0 || n == 1) return n;
        int [] table = new int [2];
        table[0] = 0;
        table[1] = 1;
        for (int i = 2; i <= n; i++) {
            table[i % 2] = table[(i - 1) % 2] + table[(i - 2) % 2];
        }
        return table[n % 2];
    }
}
