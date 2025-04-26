package com.pitaev.leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class implements a solution for a leetcode problem <code>118</code>:
 * <a href="https://leetcode.com/problems/pascals-triangle/description/">r</a>
 * <p>Time complexity: O(n ^ 2)
 * <p>Space complexity: O(n ^ 2)
 * <p>Some observations about Pascal's triangle:</p>
 * <ul>
 *     <li>Sum of the n-th row is 2^n</li>
 *     <li>C(n,k) = C(n-1,k) + C(n-1,k-1)</li>
 *     <li>Symmetry: C(n,k) = C(n,n-k)</li>
 * </ul>
 */
public class PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> table = new ArrayList<>();
        table.add(new ArrayList<>(Arrays.asList(1)));
        for (int i = 1; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            // first column
            row.add(1);
            // middle columns
            for (int j = 1; j < i; j++) {
                row.add(table.get(i - 1).get(j - 1) + table.get(i - 1).get(j));
            }
            // last column
            row.add(1);
            table.add(row);
        }

        return table;
    }
}
