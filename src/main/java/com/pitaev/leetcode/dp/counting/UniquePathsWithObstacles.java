package com.pitaev.leetcode.dp.counting;

/**
 * This class implements a solution for a leetcode problem <code>63</code>:
 * <a href="https://leetcode.com/problems/unique-paths-ii/">Unique Path II </a>
 * <p>Time complexity: O(n*m) - number of subproblems to solve
 * <p>Space complexity: O(n*m)
 */
public class UniquePathsWithObstacles {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            // number of rows in a grid
            int m = obstacleGrid.length;
            // number of columns in a grid
            int n = obstacleGrid[0].length;
            // table[i][j] number of max number paths from the cell 0,0 to the cell i,j
            int[][] table = new int[m][n];
            // compute max number of paths for the top left corner
            // if obstacle found in the top left corner, return 0
            if (obstacleGrid[0][0] == 1) return 0;
            table[0][0] = 1;
            // compute max  number of paths for the first row
            for (int i = 1; i < n; i++) {
                if (obstacleGrid[0][i] == 1) break;
                table[0][i] = 1;
            }
            // compoute max number of paths for the first column
            for (int i = 1; i < m; i++) {
                if (obstacleGrid[i][0] == 1) break;
                table[i][0] = 1;
            }
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    // if cell is obstacle, number of paths is 0
                    if (obstacleGrid[i][j] == 1) continue;
                    // if cell is free, you can reach it from left and from top
                    table[i][j] = table[i][j - 1] + table[i - 1][j];
                }
            }
            return table[m - 1][n - 1];
        }
}