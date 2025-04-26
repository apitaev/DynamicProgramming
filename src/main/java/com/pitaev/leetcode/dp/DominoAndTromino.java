package com.pitaev.leetcode.dp;

/**
 * This class implements a solution for a leetcode problem <code>790</code>:
 * <a href="https://leetcode.com/problems/domino-and-tromino-tiling/description/">r</a>
 * <p>Time complexity: O(n)
 * <p>Space complexity: O(n)
 * Observations:
 * <ul>
 *     <li>This solution uses 3 tables</li>
 *     <li>We need one table to model a shape with extra low square</li>
 *     <li>We need one table to model a shape with extra upper square</li>
 * </ul>
 */
public class DominoAndTromino {
    public int numTilings(int n) {
        if (n == 1) return 1;
        // fTable defines number of Tilings for 2 X n shape
        long [] fTable = new long[n + 1];
        // lTable defines a number of Tilings for a shape 2 X n with extra low squre
        long [] lTable = new long[n + 1];
        // uTable defines a number of Tilieng for a shape 2 x n with extra high squre
        long [] uTable = new long[n + 1];
        // let's define the functions
        // f(n) = f(n - 1) + // last cell can be a domino
        //        f(n - 2) + // last cell can be two laying dominos
        //        u(n - 2) + // last cell can be a laying tromino
        //        l(n - 2) + // last cell can be a hanging tromino

        // l(n) = f(n - 1) + u(n - 1) // last cell can be a tromino or one laying domino
        // u(n) = f(n - 1) + l(n - 1) // last cell can be a troming or one laying domino

        // Now let's define the base cases for all 3 functions:
        fTable[1] = 1; // one domino
        fTable[2] = 2; // two laying or standing dominos
        lTable[1] = 1; // one tromino
        lTable[2] = 2; // standing domino + tromino or laying domin + tromino
        uTable[1] = 1;
        uTable[2] = 2;
        for (int i = 3; i <= n; i++) {
            fTable[i] = (fTable[i - 1] + fTable[i - 2] + uTable[i - 2] + lTable[i - 2]) % 1000000007;
            lTable[i] = (fTable[i - 1] + uTable[i - 1]) %  1000000007;
            uTable[i] = (fTable[i - 1] + lTable[i - 1]) % 1000000007;
        }
        return Long.valueOf(fTable[n]).intValue();
    }
}
