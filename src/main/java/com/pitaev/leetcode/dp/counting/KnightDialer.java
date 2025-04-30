package com.pitaev.leetcode.dp.counting;

/**
 * This class implements a solution for a leetcode problem <code>935</code>:
 * <a href="https://leetcode.com/problems/knight-dialer/">Knight dialer</a></a>
 * <p>Time complexity: O(n)
 * <p>Space complexity: O(n)
 */
public class KnightDialer {
    public int knightDialer(int n) {
        // edge case
        if (n == 1) return 10;
        // f(n, j) count of distinct combinations if knight ends in the nth move at cell j
        // f(n, j) = f(n - 1, j1) + f(n - 1, j2) + .. f(n - 1, jk), where j1..jk neighbors of j
        int [][] table = new int[n + 1][10];
        // base cases
        // first row all, cells are 0
        // second row
        for (int i = 0; i < 10; i++) {
            table[1][i] = 1;
        }
        // fill rows 2 - n
        for (int i = 2; i <= n; i++) {
            table[i][0] = (table[i - 1][4] + table[i - 1][6]) % 1000000007;
            table[i][1] = (table[i - 1][6] + table[i - 1][8]) % 1000000007;
            table[i][2] = (table[i - 1][7] + table[i - 1][9]) % 1000000007;
            table[i][3] = (table[i - 1][4] + table[i - 1][8]) % 1000000007;
            table[i][4] = ((table[i - 1][0] + table[i - 1][9]) % 1000000007 + table[i - 1][3]) % 1000000007;
            table[i][6] = ((table[i - 1][1] + table[i - 1][7]) % 1000000007 + table[i - 1][0]) % 1000000007;
            table[i][7] = (table[i - 1][2] + table[i - 1][6]) % 1000000007;
            table[i][8] = (table[i - 1][1] + table[i - 1][3]) % 1000000007;
            table[i][9] = (table[i - 1][2] + table[i - 1][4]) % 1000000007;
        }
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += table[n][i];
            sum %= 1000000007;
        }
        return sum;
    }
}
