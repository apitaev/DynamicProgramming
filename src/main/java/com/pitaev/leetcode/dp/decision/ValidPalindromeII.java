package com.pitaev.leetcode.dp.decision;

/**
 * This class implements a solution for a leetcode problem <code>680</code>:
 * <a href="https://leetcode.com/problems/valid-palindrome-ii//">Valid Palindrome II</a>
 * <p>Time complexity: O(n)
 * <p>Space complexity: O(1)
 */
public class ValidPalindromeII {
    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                // exclude the left character  or exclude right character
                return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
            } else {
                left++;
                right--;
            }
        }
        return true;

    }

    private boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
            } else {
                return false;
            }
        }
        return true;
    }
}
