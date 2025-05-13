package com.pitaev.leetcode.dp.counting;

/**
 * This class implements a solution for a leetcode problem <code>357</code>:
 * <a href="https://leetcode.com/problems/count-numbers-with-unique-digits/description/">Count numbers with unique digits</a>
 * <p>Time complexity: O(n)
 * <p>Space complexity: O(1)
 */
public class CountNumbersWithUniqueDigits {
    public int countNumbersWithUniqueDigits(int n) {
        // edge case: n = 0, x = 1
        if (n == 0) return 1;
        // count of numbers with unique digits of length <= n
        int global;
        // count of numbers with unique digits of length n
        int local;
        // base case n = 1
        // 1, 2, 3, 4, 5, 6, 7, 8, 9
        local = 9;
        global = 10;
        for (int i = 2; i <= n; i++) {
            // assume n - 1 blanks are already filled
            // count of ways to fill the nth blank is:
            // c(n) = c(n - 1) * (10 - (n - 1))
            // where 10 - (n - 1) are remaining ways to fill the nth blank
            local = local * (11 - i);
            global += local;
        }
        return global;
    }
}
