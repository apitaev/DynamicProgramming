package com.pitaev.leetcode.dp.optimization;

import java.util.Arrays;

/**
 * This class implements a solution for a leetcode problem <code>265</code>:
 * <a href="https://leetcode.com/problems/paint-house-ii/description/">Paint House II</a>
 * <p>Time complexity: O(n * k)
 * <p>Space complexity: O(n * K), can be improved by using two rows.
 */
public class PaintHouseIIOrderNK {
    public int minCostII(int[][] costs) {
        // f(i, c) min cost to paint 1..i houses ending with color c
        int [][] table = new int [costs.length][costs[0].length];
        // edge case: if number of houses is 1, return min of c1..ck
        if (costs.length == 1) return Arrays.stream(costs[0]).min().getAsInt();
        //base case: paint the first house
        for (int i = 0; i < costs[0].length; i++) {
            table[0][i] = costs[0][i];
        }
        // second table to keep min and second min value for every house i
        // minValues[i][0] keeps the absolute min value for house i - 1, minValue[i][1] keeps the second min value for house i - 1
        // the second min values is used if the min value has the same color as the current cell.
        int [][] minValues = new int [costs.length][2];
        // fill the min values for the first house
        minValues[0][0] = Integer.MAX_VALUE;
        for (int i = 0; i < costs[0].length; i++) {
            if (costs[0][i] < minValues[0][0]) {
                // set second min value to the absolute min value
                minValues[0][1] = minValues[0][0];
                // update absolute / first min value;
                minValues[0][0] = costs[0][i];
            } else if (costs[0][i] < minValues[0][1]) {
                minValues[0][1] = costs[0][i];
            }
        }
        // recursive case: paint ith house with color cith, provided the house (i - 1)th is already painted
        // f(i, ci) = costs[i][ci] + min(costs[i - 1][c1], ..,costs[i - 1][ck]), so that c1..ck != ci
        for (int i = 1; i < costs.length; i++) {
            minValues[i][0] = Integer.MAX_VALUE;
            for (int j = 0; j < costs[0].length; j++) {
                // if min value is of the same color as my current cell use the second min value
                int min = table[i - 1][j] == minValues[i - 1][0] ? minValues[i - 1][1] : minValues[i - 1][0];
                table[i][j] = costs[i][j] + min;
                if (table[i][j] < minValues[i][0]) {
                    // update second minimum to the first minimum
                    minValues[i][1] = minValues[i][0];
                    // update the first minimum
                    minValues[i][0] = table[i][j];
                } else if (table[i][j] < minValues[i][1]) {
                    // update the second minimum value
                    minValues[i][1] = table[i][j];
                }
            }
        }
        // return the min for the last houses costs
        return minValues[costs.length - 1][0];
    }
}
