package com.pitaev.leetcode.dp.counting;

/**
 * This class implements a solution for a leetcode problem <code>91</code>:
 * <a href="https://leetcode.com/problems/decode-ways/description/">Decode Ways</a>
 * <p>Time complexity: O(n), where n is a length of string
 * <p>Space complexity: O(1), size of the table
 */
public class DecodeWays {
    public int numDecodings(String s) {
        // f(n) count of valid decodings for the string of length n
        // f(n) = f(n - 1), if last digit 1..9 (digit encode by itselt) s(n - 1) = 1..9
        //     +  f(n - 2) if penaltimate digit is 1 s(n - 2) = 1
        //                 if penalitmate digit is 2 s(n - 2) = 2 and s(n - 1) = 0..6

        int [] table = new int[3];
        // base cases:
        // string of size 0 --> there is one way to decode the empty string, empty string is encoding of empty string
        table[0] = 1;
        // string of size one can be decoded in max one way
        if (s.charAt(0) == '0') {
            table[1] = 0;
        } else {
            table[1] = 1;
        }
        for (int i = 2; i <= s.length(); i++) {
            int  current = s.charAt(i - 1) - '0';
            // clean-up the cell first
            table[i % 3] = 0;
            if (current >= 1 && current <= 9) {
                table[i % 3] += table[(i - 1) % 3];
            }
            int previous = s.charAt(i - 2) - '0';
            if (previous == 1) {
                table[i % 3] += table[(i - 2) % 3];
            } else if (previous == 2 && current >= 0 && current <= 6) {
                table[i % 3] += table[(i - 2) % 3];
            }

        }
        return table[s.length() % 3];
    }
}
