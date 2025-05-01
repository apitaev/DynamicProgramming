package com.pitaev.leetcode.dp.counting;


/**
 * This class implements a solution for a leetcode problem <code>276</code>:
 * <a href="https://leetcode.com/problems/paint-fence/">Paint Fence</a>
 * <p>Time complexity: O(n)
 * <p>Space complexity: O(n)
 */

public class PaintFence {
    public int numWays(int n, int k) {
        if (n == 1) return k;
        // same(i - 1) = number of ways to paint houses 1..i - 1 so that two last colors are equal
        int [] same = new int[n + 1];
        // different(i - 1) = number of ways to paint houses 1..i - 1 so that two last colors are different
        int [] different = new int[n + 1];
        // total(i) = total number ways to paint posts 1..i
        int [] total = new int[n + 1];
        // base cases: (if we have single post)
        same[0] = 0;
        // single posts can be paint using k colors -> k ways to paint the single post using different colors
        different[0] = k;
        total[0] = k;
        // same(i) = different(i - 1), we can only use the same color, if the previous two were different
        // different(i) = (k - 1) * total(i - 1), we need to exclude the same colors for the ith and (i - 1)th post
        // total(i) = same(i) + different(i)
        for (int i = 1; i < n; i++) {
            same[i] = different[i - 1];
            different[i] = (k - 1) * total[i - 1];
            total[i] = same[i] + different[i];
        }
        return total[n - 1];

    }
}
