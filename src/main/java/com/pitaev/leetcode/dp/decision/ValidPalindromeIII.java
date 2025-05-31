package com.pitaev.leetcode.dp.decision;

/**
 * This class implements a solution for a leetcode problem <code>1216</code>:
 * <a href="https://leetcode.com/problems/valid-palindrome-iii/description/">Valid Palindrome III</a>
 * <p>Time complexity: O(n ^ 2)
 * <p>Space complexity: O (n ^ 2)
 */
public class ValidPalindromeIII {
    public boolean isValidPalindrome(String s, int k) {
        // find the size of the longest palindromic subsequences
        // if k = s.length() - size of lps, return true.
        // else return false;
        int n = getLPS(s);
        if (s.length() - n <= k) return true;
        return false;
    }

    private static int getLPS(String s) {
        // using dp approach:
        // for a substring s from i to j:
        // f(i, j) = f(i + 1, j - 1) + 2, if s(i) == s(j)
        //          max (f(i, j - 1), f(i + 1, j)) ignore left or right character

        int [][] table = new int[s.length()][s.length()];
        // base case1: i == j, f(i,i) = 1
        for (int i = 0; i < s.length(); i++) {
            table[i][i] = 1;
        }
        // base case2: i < j f(i, j) = 0, default

        // recursive case: process the table in the topological sort order:
        // from bottom to the top from left to the right
        for (int i = s.length() - 1; i >= 0; i--) {
            // right character j is always bigger than a left character i
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    table[i][j] = table[i + 1][j - 1] + 2;
                } else {
                    table[i][j] = Math.max(table[i][j - 1], table[i + 1][j]);
                }
            }
        }
        return table[0][s.length() - 1];
    }
}

