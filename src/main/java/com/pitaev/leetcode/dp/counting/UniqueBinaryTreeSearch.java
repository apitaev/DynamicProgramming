package com.pitaev.leetcode.dp.counting;

/**
 * This class implements a solution for a leetcode problem <code>96</code>:
 * <a href="https://leetcode.com/problems/unique-binary-search-trees/">Unique BST</a>
 * <p>Time complexity: O(n ^ 2)
 * <p>Space complexity: O(n)
 */
public class UniqueBinaryTreeSearch {
    public int numTrees(int n) {
        // f(1) = 1
        // f(2) = 2
        // Decrease and conquer - take responsibility only for the root:
        // take 1...n as a root
        // arbitary tree: root i, left subtree 1..i - 1, right subtree: i + 1..n
        // f(i) = f(i - 1) * f(n - i), for i 1..n, so we need to build a sum
        // f(n) = sum(f(i - 1) * f(n - i)), for i 1..n
        int [] table = new int [n + 1];
        table[0] = 1;
        table[1] = 1;
        for (int p = 2; p <= n; p++) {
            for (int i = 1; i <= p ; i++) {
                table[p] += table[i - 1] * table[p - i];
            }
        }
        return table[n];
    }
}
