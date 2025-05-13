package com.pitaev.leetcode.dp.decision;

/**
 * This class implements a solution for a leetcode problem <code>97</code>:
 * <a href="https://leetcode.com/problems/interleaving-string/">Interleaving String</a>
 * <p>Time complexity: O(n * m ), where n, m lengths of s1 and s2 accordingly
 * <p>Space complexity: O(n * m), can be optimized using two rows only.
 */
public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        // check the length
        if (s1.length() + s2.length() != s3.length()) return false;
        boolean [][] table = new boolean[s1.length() + 1][s2.length() + 1];
        table[0][0] = true;
        // base cases:
        // build interleave using s2 only
        for (int i = 1; i <= s2.length(); i++) {
            table[0][i] = table[0][i - 1] && (s2.charAt(i - 1) == s3.charAt(i - 1));
        }
        // build interleave using s1 only
        for (int i = 1; i <= s1.length(); i++) {
            table[i][0] = table[i - 1][0] && (s1.charAt(i - 1) == s3.charAt(i - 1));
        }
        // recursive case
        // build interleave using s1 and s2
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                // character s3 (i + j - 1) matches s1(i) or s2(j)
                // f(i,j) = f(i, j - 1) && s2[j - 1] == s3[i + j - 1] or f(i - 1, j) && s1[i - 1] == s3(i + j - 1)
                table[i][j] = table[i][j - 1] && (s2.charAt(j - 1) == s3.charAt(i + j - 1)) ||
                        table[i - 1][j] && (s1.charAt(i - 1) == s3.charAt(i + j - 1));
            }
        }
        // return value of the bottom right cell
        return table[s1.length()][s2.length()];
    }
}
