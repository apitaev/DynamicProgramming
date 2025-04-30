package com.pitaev.leetcode.dp.counting;

/**
 * This class implements a solution for a leetcode problem <code>935</code>:
 * <a href="https://leetcode.com/problems/knight-dialer/">Knight dialer</a></a>
 * <p>Time complexity: O(n)
 * <p>Space complexity: O(1)
 */
public class KnightDialerTwoRows {
    public int knightDialer(int n) {
        // edge case
        if (n == 1) return 10;
        // f(n, j) count of distinct combinations if knight ends in the nth move at cell j
        // f(n, j) = f(n - 1, j1) + f(n - 1, j2) + .. f(n - 1, jk), where j1..jk neighbors of j
        int [][] table = new int[2][10];
        // base cases
        // first row all, cells are 0
        // second row
        for (int i = 0; i < 10; i++) {
            table[1][i] = 1;
        }
        // fill rows 2 - n
        for (int i = 2; i <= n; i++) {
            table[i % 2][0] = (table[(i - 1) % 2][4] + table[(i - 1) % 2][6]) % 1000000007;
            table[i % 2][1] = (table[(i - 1) % 2][6] + table[(i - 1) % 2][8]) % 1000000007;
            table[i % 2][2] = (table[(i - 1) % 2][7] + table[(i - 1) % 2][9]) % 1000000007;
            table[i % 2][3] = (table[(i - 1) % 2][4] + table[(i - 1) % 2][8]) % 1000000007;
            table[i % 2][4] = ((table[(i - 1) % 2][0] + table[(i - 1) % 2][9]) % 1000000007 + table[(i - 1) % 2][3]) % 1000000007;
            // override the cell for 5
            table[i % 2][5] = 0;
            table[i % 2][6] = ((table[(i - 1) % 2][1] + table[(i - 1) % 2][7]) % 1000000007 + table[(i - 1) % 2][0]) % 1000000007;
            table[i % 2][7] = (table[(i - 1) % 2][2] + table[(i - 1) % 2][6]) % 1000000007;
            table[i % 2][8] = (table[(i - 1) % 2][1] + table[(i - 1) % 2][3]) % 1000000007;
            table[i % 2][9] = (table[(i - 1) % 2][2] + table[(i - 1) % 2][4]) % 1000000007;
        }
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += table[n % 2][i];
            sum %= 1000000007;
        }
        return sum;
    }
}
