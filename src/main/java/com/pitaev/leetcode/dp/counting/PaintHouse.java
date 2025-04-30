package com.pitaev.leetcode.dp.counting;

import static java.lang.Math.min;
/**
 * This class implements a solution for a leetcode problem <code>256</code>:
 * <a href="https://leetcode.com/problems/paint-house/">Paint House</a>
 * <p>Time complexity: O(n)
 * <p>Space complexity: O(n), the space can be optimized to O(1), we only use previous column
 */
public class PaintHouse {
    public int minCost(int[][] costs) {

        // f(i, c) min cost to paint 1..i houses ending with color c
        // f(i, red) = costs[i][red] + min(costs[i - 1][green], costs[i - 1][blue])
        // our table is 2D array with 3 rows(for every color) and n columns()
        int [][] table = new int[costs.length][3];
        table[0][0] = costs[0][0];
        table[0][1] = costs[0][1];
        table[0][2] = costs[0][2];
        for (int i = 1; i < costs.length; i++) {
            // ending with red
            table[i][0] = costs[i][0] + Math.min(table[i - 1][1], table[i - 1][2]);
            // ending with blue
            table[i][1] = costs[i][1] + Math.min(table[i - 1][0], table[i - 1][2]);
            // ending with green
            table[i][2] = costs[i][2] + Math.min(table[i - 1][0], table[i - 1][1]);
        }
        // return the minimum value for the last house
        return Math.min(table[costs.length - 1][0], Math.min(table[costs.length - 1][1], table[costs.length - 1][2]));
    }
}
