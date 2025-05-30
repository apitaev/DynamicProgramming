package com.pitaev.leetcode.dp.optimization;

/**
 * This class implements a solution for a leetcode problem <code>11143</code>:
 * <a href="https://leetcode.com/problems/longest-common-subsequence/description/">Longest Common Subsequence</a>
 * <p>Time complexity: O (m * n)
 * <p>Space complexity: O( m * n), can be reduces by using two rows.
 */
public class LongestCommonSubsequenceOptimized {
    public int longestCommonSubsequence(String text1, String text2) {
        // f(i, j) is the LCS for x1..xi and y1..yj
        // f(i, j) is the max of insertion, deletion, mismatch and match
        // Reward for match 1, reward for mismatch, insertion, deletion is 0
        // f(i, j) = Max (f(i - 1, j) # deletion)
        //                f(i, j - 1) # insertion
        //                f(i - 1, j - 1) + s # s == 1 if match, 0 if mismatch
        //

        int [][] table = new int[text1.length() + 1][text2.length() + 1];
        // base cases:
        // 1. Convert empty string to text2 by insertion, table first row will be 0 by default
        // 2. Convert text1 to the empth string by deletion, table first column will be 0 by default
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                // calculate reward for match
                int s = 0;
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    s++;
                    // if the match, always choose the match.
                    table[i][j] = table[i - 1][j - 1] + s;
                } else {
                    // max of the insertion or deletion
                    table[i][j] = Math.max(table[i - 1][j],table[i][j - 1]);
                }
            }
        }

        // return the most bottom right element in the table
        return table[text1.length()][text2.length()];
    }
}
