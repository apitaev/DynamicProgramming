package com.pitaev.leetcode.dp;

/**
 * This class implements a solution for a leetcode problem <code>63</code>:
 * <a href="https://leetcode.com/problems/unique-paths-ii/">Unique Paths II</a>
 * <p>The table has two rows only</p>
 * <p>Time complexity: O(n) - number of problems to solve
 * <p>Space complexity: O(n*m)
 */
public class UniquePathsWithObstaclesUsingTwoRowTable {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // number of rows in a grid
        int m = obstacleGrid.length;
        // number of columns in a grid
        int n = obstacleGrid[0].length;
        // table[i][j] number of max number paths from the cell 0,0 to the cell i,j
        int [][] table = new int[2][n];
        // compute max number of paths for the top left corner
        // if obstacle found in the top left corner, return 0
        if (obstacleGrid[0][0] == 1) return 0;
        table[0][0] = 1;
        // compute max  number of paths for the first row
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[0][i] == 1) break;
            table[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // copy a previous row for the column 0, if there is no obstacle
                if (j == 0 && obstacleGrid[i][j] == 0) {
                    table[i % 2][0] = table[(i - 1) % 2][0];
                } else if (obstacleGrid[i][j] == 1) {  // if cell is obstacle, number of paths is 0
                    table[i % 2][j] = 0;
                } else {
                    // if cell is free, you can reach it from left and from top
                    table[i % 2][j] = table[i % 2][j - 1] + table[(i - 1) % 2][j];
                }
            }
        }
        return table[(m - 1) % 2][n - 1];
    }
}