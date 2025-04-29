package com.pitaev.leetcode.dp.optimization;

import java.util.List;

/**
 * This class implements a solution for a leetcode problem <code>120</code>:
 * <a href="https://leetcode.com/problems/triangle/description/">Triangle</a>
 * <p>Time complexity: O(r^2) - number of problems to solve
 * <p>Space complexity: O(r), if r is a number of rows
 */
public class MinPathInTriangleTwoRows {
    public int minimumTotal(List<List<Integer>> triangle) {
        int rows = triangle.size();
        int [][] table = new int[2][rows];
        table[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < rows; i++) {
            // first column
            table[i % 2][0] = triangle.get(i).get(0) + table[(i - 1) % 2][0];
            // middle columns
            for (int j = 1; j < i; j++) {
                table[i % 2][j] = triangle.get(i).get(j) + Math.min(table[(i - 1) % 2][j - 1], table[(i - 1) % 2][j]);
            }
            // last column
            table[i % 2][i] = triangle.get(i).get(i) + table[(i - 1) % 2][i - 1];
        }

        // find min element in the table[rows - 1]
        int result = Integer.MAX_VALUE;
        int rowNumber = (rows - 1) % 2;
        for (int i = 0; i < rows; i++) {
            if (table[rowNumber][i] < result) {
                result = table[rowNumber][i];
            }
        }
        return result;
    }
}
