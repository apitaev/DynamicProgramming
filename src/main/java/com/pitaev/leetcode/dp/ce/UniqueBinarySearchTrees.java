package com.pitaev.leetcode.dp.ce;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a solution for a leetcode problem <code>95</code>:
 * <a href="https://leetcode.com/problems/unique-binary-search-trees-ii/">Unique BST</a>
 * <p>Time complexity:
 * f(n) =  Sum (T(i - 1) + T (n - (i + 1) + 1)) =  Sum (T(i - 1) * T (n - i)) where i 1..n
 * So T(n) will be exponential
 * <p>Space complexity: size of the result nth catlan number exp in n
 * Each tree has a size of n nodes.
 */
public class UniqueBinarySearchTrees {
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
            return helper(1, n);
        }

        /**
         * List of Trees for the range start..end
         */
        private List<TreeNode> helper(int start, int end) {
            List<TreeNode> result = new ArrayList<>();
            // base case (start > end) - subproblem size 0, return a list with empty subtree
            if (start > end) {
                result.add(null);
                return result;
            }
            // base case (start == end) - subproblem of size 1
            if (start == end) {
                result.add(new TreeNode(start));
                return result;
            }

            // recursive case, subproblem size is more than 1. Each of the numbers from start to end are choices to be a root
            for (int i = start; i <= end; i++) {
                // pick i as a root.
                List<TreeNode> leftSubtrees =  helper(start, i - 1);
                List<TreeNode> rightSubtrees = helper(i + 1, end);
                for (TreeNode leftSubtree : leftSubtrees) {
                    for (TreeNode rightSubtree : rightSubtrees) {
                        TreeNode root = new TreeNode(i);
                        root.left = leftSubtree;
                        root.right = rightSubtree;
                        result.add(root);

                    }
                }
            }
            return result;
        }
    }
}
