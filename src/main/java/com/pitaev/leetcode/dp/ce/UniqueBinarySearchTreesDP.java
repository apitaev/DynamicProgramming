package com.pitaev.leetcode.dp.ce;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a solution for a leetcode problem <code>95</code>:
 * <a href="https://leetcode.com/problems/unique-binary-search-trees-ii/">Unique BST</a>
 * <p>Time complexity:
 * <p> Space complexity
 */
public class UniqueBinarySearchTreesDP {
    /**
     * Definition for a binary tree node.
     *
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
        public List<TreeNode> generateTrees(int n) {
            List<TreeNode> [][] dp = new List[n + 1][n + 1];
            return helper(1, n, dp);
        }

        /**
         * List of Trees for the range start..end
         */
        private List<TreeNode> helper(int start, int end, List<TreeNode> [][] dp) {
            List<TreeNode> result = new ArrayList<>();
            // base case (start > end) - subproblem size 0, return a list with empty subtree
            if (start > end) {
                result.add(null);
                return result;
            }

            // recursive case, subproblem size is more than 1. Each of the numbers from start to end are choices to be a root
            if (dp[start][end] != null) return dp[start][end];
            for (int i = start; i <= end; i++) {
                // pick i as a root.
                List<TreeNode> leftSubtrees =  helper(start, i - 1, dp);
                List<TreeNode> rightSubtrees = helper(i + 1, end, dp);
                for (TreeNode leftSubtree : leftSubtrees) {
                    for (TreeNode rightSubtree : rightSubtrees) {
                        TreeNode root = new TreeNode(i);
                        root.left = leftSubtree;
                        root.right = rightSubtree;
                        result.add(root);
                    }
                }
            }
            return dp[start][end] = result;
        }
    }
}
