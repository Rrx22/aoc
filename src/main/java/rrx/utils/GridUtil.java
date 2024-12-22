package rrx.utils;

import rrx.ChristmasException;

public class GridUtil {

    private GridUtil() {

    }

    public static boolean isWithinBounds(int x, int y, Object grid) {
        if (grid instanceof char[][] arr) {
            return isWithinBounds(x, y, arr.length, arr[0].length);
        }
        if (grid instanceof int[][] arr) {
            return isWithinBounds(x, y, arr.length, arr[0].length);
        }
        throw new ChristmasException("Go implement this unsupported array type!! ");
    }
    private static boolean isWithinBounds(int x, int y, int xLen, int yLen) {
        return x >= 0 && x < xLen && y >= 0 && y < yLen;
    }

    public static char[][] cloneGrid(char[][] og) {
        char[][] clone = new char[og.length][og[0].length];
        for (int i = 0; i < og.length; i++) {
            System.arraycopy(og[i], 0, clone[i], 0, og[i].length);
        }
        return clone;
    }

}
