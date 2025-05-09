package com.pitaev.leetcode.dp.optimization;

/**
 * This class implements a solution for a leetcode problem <code>583</code>:
 * <a href="https://leetcode.com/problems/delete-operation-for-two-strings">Delete operations for two strings</a>
 * <p>Time complexity: O (m * n)
 * <p>Space complexity: O( m * n), can be reduces by using two rows.
 */
public class DeleteOperationsForTwoStrings {
    public int minDistance(String word1, String word2) {
        // edit distance approach, but we give a substitution the weight = 3, to exclude substitution
        // f(i, j) = minimum number of steps to convert x1..xi to y1...yj
        // f(i, j) = min (f(i - 1, j) + 1  # delete first
        //                f(i, j - 1) + 1 # delete the second
        //                f(i - 1, j - 1) + s, where s = 0 (for match) and s == 3 for the mismatch
        int [][] table = new int[word1.length() + 1][word2.length() + 1];
        // build base cases:
        // build word2 from the empty string using insertions
        for (int i = 0; i <= word2.length(); i++) {
            table[0][i] = i;
        }
        // build empth string from word1 using deletions
        for (int i = 0; i <= word1.length(); i++) {
            table[i][0] = i;
        }

        // calculate recursive cases
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                // calculate match
                // we set cost for 3 in case of mismatch, to give a priority to the deletion operation
                int s = 3;
                // if match, cost is 0
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    s = 0;
                }
                table[i][j] = Math.min(table[i - 1][j] + 1, Math.min(table[i][j - 1] + 1, table[i - 1][j - 1] + s));
            }
        }
        // return the bottom right cell in the table.
        return table[word1.length()][word2.length()];
    }
}
