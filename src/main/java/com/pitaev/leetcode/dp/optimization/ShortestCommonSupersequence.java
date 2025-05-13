package com.pitaev.leetcode.dp.optimization;

/**
 * This class implements a solution for a leetcode problem <code>1092</code>:
 * <a href="https://leetcode.com/problems/shortest-common-supersequence/description/">Shortest Common Supersequence </a>
 * <p>Time complexity: O(m * n)
 * <p>Space complexity: O(m * n)
 */
public class ShortestCommonSupersequence {
    public String shortestCommonSupersequence(String str1, String str2) {
        // edge case
        if (str1.equals(str2)) return str1;
        // find lcs
        int m = str1.length();
        int n = str2.length();
        int [][] table = new int[m + 1][n + 1];
        // f(i, j) = min (f(i - 1, j), f(i, j - 1), f(i - 1, j - 1) + s), where s = 1 for match, s = 0, for mismatch
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    table[i][j] = table[i - 1][j - 1] + 1;
                }  else {
                    // if not match, f(i, j) is minimum of deletion and insertion
                    table[i][j] = Math.max(table[i - 1][j], table[i][j - 1]);
                }

            }
        }
        // trace back from the cell [str1.length][str2.length] to [0][0]
        StringBuilder result = new StringBuilder();
        int i = m;
        int j = n;
        while (i > 0 && j > 0) {
            // previous step: deletion from the first string
            if (table[i][j] == table[i - 1][j]) {
                result.append(str1.charAt(i - 1));
                i--;
            } else if (table[i][j] == table[i][j - 1]) {
                // previous step: insertion into the second string
                result.append(str2.charAt(j - 1));
                j--;
            } else {
                // previous step match, append only 1 character
                result.append(str1.charAt(i - 1));
                i--;
                j--;
            }
        }
        while (i > 0) {
            result.append(str1.charAt(i - 1));
            i--;
        }
        while (j > 0) {
            result.append(str2.charAt(j - 1));
            j--;
        }

        // reverse StringBuilder
        result.reverse();
        return result.toString();
    }
}
