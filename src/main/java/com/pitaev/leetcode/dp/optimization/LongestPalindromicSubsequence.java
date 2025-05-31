package com.pitaev.leetcode.dp.optimization;

/**
 * This class implements a solution for a leetcode problem <code>516</code>:
 * <a href="https://leetcode.com/problems/longest-palindromic-subsequence/description//">Longest Palindromic Subsequence</a>
 * <p>Time complexity: O(n ^ 2)
 * <p>Space complexity: O (n ^ 2), can be improved by using only two rows (to O(n))
 */
public class LongestPalindromicSubsequence {
    public int longestPalindromeSubseq(String s) {
        // optimal substructure: if we found a LPS, if we cut the outer pair, the remaining substring is also LPS
        // we can apply the DP approach for an optimization problem
        // we use bidirectional decrease and conquer approach
        //  given a substring from i to j find the LPS
        // unique subproblems nc2 = O(n ^ 2)
        // f(i, j) = { f (i + 1, j - 1) + 2, if s[i] = s[j]
        //           max of   f(i + 1, j), ignore s[i] if s[i] != s[j]
        //                    f(i, j - 1), ignore s[j] if s[i] != s[j]
        //            }
        int [][] table = new int[s.length()][s.length()];
        // base case: subproblem size = 1
        // f(i, i) = 1
        for (int i = 0; i < s.length(); i++) {
            table[i][i] = 1;
        }
        // base case: subproblem size = 0
        // if (i > j) f(i, j) = 0
        // Recursive case:
        // we iterate through the table bottom up left to right
        // topological sort order: row by row from bottom to top and column by column from left to right starting with row + 1
        for (int i = s.length() - 1; i >= 0; i--) {
            // j is bigger than i
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    // recursive case: outer characters are equal
                    table[i][j] = table[i + 1][j - 1] + 2;
                } else {
                    // recursive chase: outer characters are not equal
                    // exclude left or right character
                    table[i][j] = Math.max(table[i + 1][j], table[i][j - 1]);
                }
            }
        }
        return table[0][s.length() - 1];
    }
}
