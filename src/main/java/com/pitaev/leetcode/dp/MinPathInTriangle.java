package com.pitaev.leetcode.dp;

import java.util.List;
/**
 * This class implements a solution for a leetcode problem <code>120</code>:
 * <a href="https://leetcode.com/problems/triangle/description/">Triangle</a>
 * <p>Time complexity: O(r^2) - number of problems to solve
 * <p>Space complexity: O(r^2), if r is a number of rows
 */
public class MinPathInTriangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int numberOfRows = triangle.size();
        // in row 0 - 1 column, in row 1 - 2 columns, etc
        int numberOfColumns = triangle.size();
        // build a table
        int [][] table = new int[numberOfRows][numberOfColumns];
        // initialize table with the base cases
        table[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < numberOfRows; i++) {
            // initialize first column for every row
            table[i][0] = table[i - 1][0] + triangle.get(i).get(0);
            // initialize the last column for every row
            table[i][i] = table[i - 1][i - 1] + triangle.get(i).get(i);
        }
        // defind the max path for the remaining cells
        for (int i = 2; i < numberOfRows; i++) {
            // in row 0 - 1 column, in row 1 - 2 columns, etc
            // last column value is already set as a base case
            for (int j = 1; j < i; j++) {
                table[i][j] = Math.min(table[i - 1][j - 1], table[i - 1][j]) + triangle.get(i).get(j);
            }
        }
        // find the elemenent with the min path in the last row
        int minPath = Integer.MAX_VALUE;
        for (int i = 0; i < numberOfColumns; i++) {
            if (table[numberOfRows - 1][i] < minPath) {
                minPath = table[numberOfRows - 1][i];
            }
        }
        return minPath;

    }
}
