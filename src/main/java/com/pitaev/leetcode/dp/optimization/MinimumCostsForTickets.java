package com.pitaev.leetcode.dp.optimization;

/**
 * This class implements a solution for a leetcode problem <code>983</code>:
 * <a href="https://leetcode.com/problems/minimum-cost-for-tickets/description/">Minimum Costs for Tickets</a>
 * <p>Time complexity: O(n ^ 2) - we compute table for n indexes, findIndex is O(n) -> O(n^2)
 * <p>Space complexity: O(n)
 */
public class MinimumCostsForTickets {
    public int mincostTickets(int[] days, int[] costs) {
        // f(n) is min amount of dollars spent for days[0..n]
        // last day may uses 1 day ticket, 7 day ticket, 30 days ticket
        // f(n) = min(f(n - 1) + costs[0], f(n - x) + costs[1], f(n - y) + costs[2])
        int [] table = new int[days.length];
        table[0] = Math.min(costs[0], Math.min(costs[1], costs[2]));
        for (int i = 1; i < table.length; i++) {
            // last ticket is one day ticket
            int case1 = table[i - 1] + costs[0];
            // last ticket is 7 day ticket
            int currentDay = days[i];
            int startDay = currentDay - 6;
            int previousIndex = 0;
            previousIndex = findIndex(days, i, startDay);
            int case2 = 0;
            if (previousIndex >= 0) {
                case2 = table[previousIndex] + costs[1];
            } else {
                // there is no such previous day
                case2  = costs[1];
            }
            // last ticket is 30 day ticket
            startDay = currentDay - 29;
            int case3 = 0;
            previousIndex = findIndex(days, i, startDay);
            if (previousIndex >= 0) {
                case3  = table[previousIndex] + costs[2];
            } else {
                // there is no such previous day
                case3 =  costs[2];
            }
            table[i] = Math.min(case1, Math.min(case2, case3));

        }
        return table[table.length - 1];
    }

    private static int findIndex(int [] days, int index, int startDay) {
        int result = 0;
        // search in the days array for startDay or bigger starting from index - 1
        // 1, 2, 5, 10, 12, 16
        // currentDay = 16
        // startDay = 16 - 7 = 9
        // 10, 12, 16 will be covered by 7-day pass
        // return index of 5 here;
        int i = index - 1;
        while (i >= 0 && days[i] >= startDay) {
            i--;
        }
        return i;
    }
}
