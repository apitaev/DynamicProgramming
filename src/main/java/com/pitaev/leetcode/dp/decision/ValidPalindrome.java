package com.pitaev.leetcode.dp.decision;

/**
 * This class implements a solution for a leetcode problem <code>125</code>:
 * <a href="https://leetcode.com/problems/valid-palindrome/description/">Valid Palindrome</a>
 * <p>Time complexity: O(n)
 * <p>Space complexity: O(1)
 */
public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        // edge case: empty string
        if (s.length() == 0) return true;
        int left = 0;
        int right = s.length() - 1;
        while(left < right) {
            // non-alphanumeric character found on the left - skip it
            if (!Character.isLetter(s.charAt(left)) && !Character.isDigit(s.charAt(left))) {
                left++;
            } else if (!Character.isLetter(s.charAt(right)) && !Character.isDigit(s.charAt(right))) {
                // non-alphanumeric character found on the right - skip it
                right--;
            } else {
                // both characters are alpha-numeric
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    // not matching use case
                    return false;
                } else {
                    left++;
                    right--;
                }
            }
        }
        return true;
    }
}
