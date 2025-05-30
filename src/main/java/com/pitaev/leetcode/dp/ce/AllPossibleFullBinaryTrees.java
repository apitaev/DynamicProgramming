package com.pitaev.leetcode.dp.ce;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a solution for a leetcode problem <code>894</code>:
 * <a href="https://leetcode.com/problems/all-possible-full-binary-trees/">All Possible Full Binary Trees</a>
 * Time complexity:
 * Number of full binary tree must be odd - e.g. 2n + 1
 * f(n) = Sum f(i) * f(n - i) for i = 1..2n - 1
 * Space complexity:
 */
public class AllPossibleFullBinaryTrees {
    /**
     * Definition for a binary tree node.
     */
     public class TreeNode {
       int val;
        TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
      }
    class Solution {
        public List<TreeNode> allPossibleFBT(int n) {
            List<TreeNode> [][] dp = new List[n + 1][n + 1];
            // no solution for any even number of nodes.
            if (n % 2 == 0) return new ArrayList<>();
            return helper(1, n, dp);
        }

        private List<TreeNode> helper(int start, int end, List<TreeNode>[][] dp) {
            List<TreeNode> result = new ArrayList<>();
            // base case: start == end, subproblem size 1
            if (start == end) {
                TreeNode root = new TreeNode(0);
                result.add(root);
                return result;
            }
            // recursive case: root node should have left and right child, so root will start with start + 1 and end at end - 1
            if (dp[start][end] != null) return dp[start][end];
            // if a size of a subproblem is even, return empty liest
            if ((end - start - 1) % 2 == 0) return result;
            for (int r = start + 1; r < end; r++) {
                List<TreeNode> leftTrees = helper(start, r - 1, dp);
                List<TreeNode> rightTrees = helper(r + 1, end, dp);
                for (TreeNode leftTree : leftTrees) {
                    for (TreeNode rightTree : rightTrees) {
                        TreeNode root = new TreeNode(0);
                        root.left = leftTree;
                        root.right = rightTree;
                        result.add(root);
                    }
                }
            }
            return dp[start][end] = result;
        }
    }
}
