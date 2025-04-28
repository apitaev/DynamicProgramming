package com.pitaev.leetcode.dp;

import static  java.lang.Math.min;
/**
 * This class implements a solution for a leetcode problem <code>64</code>:
 * <a href="https://leetcode.com/problems/minimum-path-sum/">Minimum Path Sum</a>
 * <p>Time complexity: O(m x n)
 * <p>Space complexity: O(1)
 */
public class MinPathsSum {
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        int [][] table = new int[2][columns];
        // initialize the cell 0,0
        table[0][0] = grid[0][0];
        // initialize the first row
        for (int i = 1; i < columns; i++) {
            table[0][i] = table[0][i - 1] + grid[0][i];
        }
        // calculate the remaining table
        for (int i = 1; i < rows; i++) {
            // initialize the first column
            table[i % 2][0] = table[(i - 1) % 2][0] + grid[i][0];
            for (int j = 1; j < columns; j++) {
                table[i % 2][j] = Math.min(table[i % 2][j - 1], table[(i - 1) % 2][j]) + grid[i][j];
            }
        }
        return table[(rows - 1) % 2][columns - 1];
    }
}
