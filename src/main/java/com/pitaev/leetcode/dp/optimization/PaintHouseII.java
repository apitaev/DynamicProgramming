package com.pitaev.leetcode.dp.optimization;

import java.util.Arrays;

/**
 * This class implements a solution for a leetcode problem <code>265</code>:
 * <a href="https://leetcode.com/problems/paint-house-ii/description/">Paint House II</a>
 * <p>Time complexity: O(n * k ^ 2)
 * <p>Space complexity: O(n * K), can be improved by using two rows.
 */
public class PaintHouseII {
    public int minCostII(int[][] costs) {
        // f(i, c) min cost to paint ith houses ending with color c
        int [][] table = new int [costs.length][costs[0].length];
        // edge case: if number of houses is 1, return min of c1..ck
        if (costs.length == 1) return Arrays.stream(costs[0]).min().getAsInt();
        //base case: paint the first house
        for (int i = 0; i < costs[0].length; i++) {
            table[0][i] = costs[0][i];
        }
        // recursive case: paint ith house with color ci, provided the house (i - 1)th is already painted
        // f(i, ci) = costs[i][ci] + min(costs[i - 1][c1], ..,costs[i - 1][ck]), so that c1..ck != ci
        for (int i = 1; i < costs.length; i++) {
            for (int j = 0; j < costs[0].length; j++) {
                table[i][j] = costs[i][j] + getMin(table, i - 1, j);
            }
        }
        // return the min for the last houses costs
        Integer min = Integer.MAX_VALUE;
        for (int i = 0; i < costs[0].length; i++) {
            if (table[costs.length - 1][i] < min) {
                min = table[costs.length - 1][i];
            }
        }
        return min;
    }

    private static Integer getMin(int [][] table, int row, int columnToExclude) {
        Integer min = Integer.MAX_VALUE;
        for (int i = 0; i < table[0].length; i++) {
            if (i == columnToExclude) continue;
            if (table[row][i] < min) {
                min = table[row][i];
            }
        }
        return min;
    }

}
