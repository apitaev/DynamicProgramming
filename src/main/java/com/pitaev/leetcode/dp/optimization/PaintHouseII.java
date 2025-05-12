package com.pitaev.leetcode.dp.optimization;

import java.util.Arrays;

/**
 * This class implements a solution for a leetcode problem <code>265</code>:
 * <a href="https://leetcode.com/problems/paint-house-ii/description/">Paint House II</a>
 * <p>Time complexity: O(n * k)
 * <p>Space complexity: O(k).
 */
public class PaintHouseII {
    public int minCostII(int[][] costs) {
        // edge case: if number of houses is 1, return min of c1..ck
        if (costs.length == 1) return Arrays.stream(costs[0]).min().getAsInt();
        // f(i, c) min cost to paint 1..i houses ending with color c
        int [][] table = new int [2][costs[0].length];
        // min cost to paint the Ith house
        int minI = Integer.MAX_VALUE;
        // second min cost to paint the Ith house
        int secondMinI = Integer.MAX_VALUE;
        //base case: paint the first house
        for (int i = 0; i < costs[0].length; i++) {
            table[0][i] = costs[0][i];
            if (costs[0][i] < minI) {
                // set second min value to the absolute min value
                secondMinI = minI;
                // update absolute / first min value;
                minI = costs[0][i];
            } else if (costs[0][i] < secondMinI) {
                secondMinI = costs[0][i];
            }
        }
        // recursive case: paint ith house with color cith, provided the house (i - 1)th is already painted
        // f(i, ci) = costs[i][ci] + min(costs[i - 1][c1], ..,costs[i - 1][ck]), so that c1..ck != ci
        for (int i = 1; i < costs.length; i++) {
            // min cost to paint current house (ith house)
            int minCurrent = Integer.MAX_VALUE;
            // second min cost to paint current house (ith house)
            int secondMinCurrent = Integer.MAX_VALUE;
            for (int j = 0; j < costs[0].length; j++) {
                // if min value is of the same color as my current cell use the second min value
                int minCostForLastHouse = table[(i - 1) % 2][j] == minI ? secondMinI : minI;
                table[i % 2][j] = (table[(i - 1) % 2][j] == minI ? secondMinI : minI) + costs[i][j];
                if (table[i % 2][j] < minCurrent) {
                    // update second minimum to the first minimum
                    secondMinCurrent = minCurrent;
                    // update the first minimum
                    minCurrent = table[i % 2][j];
                } else if (table[i % 2][j] < secondMinCurrent) {
                    // update the second minimum value
                    secondMinCurrent = table[i % 2][j];
                }
            }
            minI = minCurrent;
            secondMinI = secondMinCurrent;
        }
        // return the min for the last houses costs
        return minI;
    }
}

