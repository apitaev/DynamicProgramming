package com.pitaev.leetcode.dp.counting;

/**
 * This class implements a solution for a leetcode problem <code>62</code>:
 * <a href="https://leetcode.com/problems/unique-paths/">Unique Paths</a>
 * <p>Time complexity: O(m * n)
 * <p>Space complexity: O(n)
 */
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int [][] table = new int[2][n];
        // fill the first row
        for (int i = 0; i < n; i++) {
            table[0][i] = 1;
        }
        // fill the first column
        table[1][0] = 1;
        // fill the remaining cells
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // visit cell from the neighbor above and from the left neighbor
                table[i % 2][j] = table[(i - 1) % 2][j] + table[i % 2][j - 1];
            }
        }
        return table[(m - 1) % 2][n - 1];
    }
}
