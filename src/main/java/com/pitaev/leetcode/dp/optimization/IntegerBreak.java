package com.pitaev.leetcode.dp.optimization;

/**
 * This class implements a solution for a leetcode problem <code>343</code>:
 * <a href="https://leetcode.com/problems/integer-break/"/>Integer Break</a>
 * <p>Time complexity: O(n ^ 2)
 * <p>Space complexity: O(n)
 */
public class IntegerBreak {
    public int integerBreak(int n) {
        // f(n) - maximum product, that can be obtained from the 1..n
        // f(i) - maximum product, that can be obtained from the 1..i
        // in general the rode needs to be cut at least once,
        // but smaller peaces don't need to be cut

        // edge case, split two in 1 x 1
        if (n == 2) return 1;
        int [] table = new int[n + 1];
        table[0] = 0;
        table[1] = 1;
        // as partial solution table[i] does not need to be split
        table[2] = 2;
        int best;
        for (int i = 3; i <= n; i++) {
            // if i == n, we assume we split at n - 1, so that the product will be (n - 1) * 1
            if (i == n) {
                best = n - 1;
            } else {
                // we don't cut, if it is a partial solution
                best = i;
            }

            // if we cut at position j, f(i): f(j) * f(i - j)
            for (int j = 1; j < i; j++) {
                best = Math.max(best, table[j] * table[i - j]);
            }
            table[i] = best;
        }
        return table[n];
    }
}
