package com.pitaev.leetcode.dp.ce;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a solution for a leetcode problem <code>2414</code>:
 * <a href="leetcode.com/problems/different-ways-to-add-parentheses/">Different Ways to Add Parentheses</a>
 * Time complexity: nth catlan number (exponential in n)
 * Space complexity: nth catlan number (exponential in n)
 */
public class DifferentWaysToAddParentheses {
    public List<Integer> diffWaysToCompute(String expression) {
        // This problem can be reduced to return all different parse tree for the same expression
        // if n operators
        // f(i) = f(i - 1) * f(n - i), if i is a root of a tree (number of parse trees with a root in i)
        // f(n) =  (i - 1) * f(n - 1) for i 1..n --> f(n) is nth catlan number
        // That means, number of parse trees is exponential in n
        // All parse trees will be full binary trees with operators (+, -, *) as internal nodes with two chidlren
        // and numbers as leaves with 0 children
        // n - nodes (operators) and (n + 1) leaves - numbers --> 2n + 1 nodes in total
        List<Integer> [][] dp = new List[expression.length()][expression.length()];
        return helper(expression, 0, expression.length() - 1, dp);

    }

    private List<Integer> helper(String s, int i, int j, List<Integer> [][] dp) {
        List<Integer> result = new ArrayList<>();
        // build and evaluate results of all possible parse trees and return result list
        // base case:  No operators

        if (isDigit(s, i, j)) {
            // return numeric value
            result.add(Integer.valueOf(s.substring(i, j + 1)));
            return result;
        }
        if (dp[i][j] != null) return dp[i][j];
        // recursive case, build parse tree with root in the range i to j
        for (int r = i; r <= j; r++) {
            if (!Character.isDigit(s.charAt(r))) {
                // build results for the left and right subtrees
                List<Integer> leftResults = helper(s, i, r - 1, dp);
                List<Integer> rightResults = helper(s, r + 1, j, dp);
                // now combine results
                for (Integer leftResult : leftResults) {
                    for (Integer rightResult : rightResults) {
                        if (s.charAt(r) == '+') {
                            result.add(leftResult + rightResult);
                        } else if (s.charAt(r) == '-') {
                            result.add(leftResult - rightResult);
                        } else if (s.charAt(r) == '*') {
                            result.add(leftResult * rightResult);
                        }

                    }
                }
            }
        }
        return dp[i][j] = result;

    }

    private boolean isDigit(String s, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (! Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }
}
