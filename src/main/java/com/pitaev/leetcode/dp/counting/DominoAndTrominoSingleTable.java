package com.pitaev.leetcode.dp.counting;


/**
 * This class implements a solution for a leetcode problem <code>790</code>:
 * <a href="https://leetcode.com/problems/domino-and-tromino-tiling/description/">r</a>
 * <p>Time complexity: O(n)
 * <p>Space complexity: O(n)
 */
public class DominoAndTrominoSingleTable {
    public int numTilings(int n) {
        if (n == 1 || n == 2) return n;
        if (n == 3) return 5;
        // fTable defines number of Tilings for 2 X n shape
        long [] fTable = new long[n + 1];
        // lTable defines a number of Tilings for a shape 2 X n with extra low squre
        // uTable defines a number of Tilieng for a shape 2 x n with extra high squre
        // let's define the functions
        // f(n) is a number of tilings for 2 x n board
        // How can be the last column be tiled
        // f(n) = f(n - 1) + // last cell can be a domino
        //        f(n - 2) + // last cell can be two laying dominos
        //        u(n - 2) + // last cell can be a laying tromino
        //        l(n - 2) + // last cell can be a hanging tromino

        // l(n) = f(n - 1) + u(n - 1) // last cell can be a tromino or one laying domino
        // u(n) = f(n - 1) + l(n - 1) // last cell can be a tromin0 or one laying domino
        // using substitution we get:
        // f(n) = 2 * f(n - 1) + f (n - 3)
        // Now let's 3 base cases
        fTable[1] = 1; // one domino
        fTable[2] = 2; // two laying or standing dominos
        fTable[3] = 5; // one tromino
        for (int i = 4; i <= n; i++) {
            // note: alternative approach here to work with substitution and get f(n) = 2 * f(n - 1) + f(n - 3)
            fTable[i] = (2 * fTable[i - 1] + fTable[i - 3]) % 1000000007;

        }
        return Long.valueOf(fTable[n]).intValue();
    }
}

