package com.pitaev.leetcode.dp;

import  static java.lang.Math.min;


/**
 * This class implements a solution for a leetcode problem <code>72</code>:
 * <a href="https://leetcode.com/problems/edit-distance/description/">Edit distance</a>
 * <p>Time complexity: O(m * n), where m is a length of the word1 and n is a length of the word 2
 * <p>Space complexity: O(m * n ), size of the table
 */
 public class EditDistance {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int [][] table = new int[m + 1][n + 1];
        // base cases
        // fill 0 column
        for (int i = 0; i <= m; i++) {
            table[i][0] = i;
        }
        // fill 0 row
        for (int i = 0; i <= n; i++) {
            table[0][i] = i;
        }
        // Recursive case
        // dependency graph has 3 neighbors
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <=  n; j++) {
                // Min of 3 options: insertion, deletion, match / mismatch
                // cost of the insertion is 1, deletion is 1, match is 0, mismatch, is 0
                //            insertion (from the left)
                table[i][j] = Math.min(table[i][j - 1] + 1, Math.min(
                        // deletion (go down)
                        table[i - 1][j] + 1,
                        // diagonaly
                        table[i - 1][j - 1] + getDistance(word1, word2, i - 1, j - 1)));
            }
        }

        return table[m][n];
    }
    private static int getDistance(String word1, String word2, int i, int j) {
        if (word1.substring(i, i + 1).equals(word2.substring(j, j + 1))) return 0;
        return 1;
    }
}
