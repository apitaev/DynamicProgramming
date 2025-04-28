package com.pitaev.leetcode.dp.counting;

import java.util.List;

/**
 * This class implements a solution for a leetcode problem <code>139</code>:
 * <a href="https://leetcode.com/problems/word-break/description/">Word Break</a>
 * <p>Time complexity: O(n^2), for every step i you need O(i) to fill the table
 * <p>Space complexity: O(n), size of the string
 */
public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        // f(i) is a boolean answer to a question: Can a string c1c2..ci be segmented in a valid way
        boolean [] table = new boolean[s.length() + 1];
        table[0] = true;
        // current character from 1 to n
        for (int i = 1; i <= s.length(); i++) {
            // last word size
            // subordinate looking for all characters befor i
            for (int wordLength = 1; wordLength <= i; wordLength++) {
                // look at ci-lastwordLength+1..ci characters
                // e.g. worldLength = 2 ci-1ci
                // wordLength = 3 ci-2ci-1ci
                //etc. ci-lasterwordLenth+1..ci
                // if this substring is in the dictionary and the subordinates substring is also true
                // then table[i] = trye:
                // My word can be segmented and remaining part of the word(returned from subordinate) is true
                if (wordDict.contains(s.substring(i - wordLength, i)) && table[i - wordLength]) {
                    table[i] = true;
                }
            }
        }
        // table is filled in here
        return table[s.length()];
    }
}
