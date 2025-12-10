package rrx.utils;

import rrx.ChristmasException;

public class GridUtil {

    private GridUtil() {

    }

    public static boolean isWithinBounds(int x, int y, Object grid) {
        return switch (grid) {
            case char[][] arr -> isWithinBounds(x, y, arr.length, arr[0].length);
            case int[][] arr -> isWithinBounds(x, y, arr.length, arr[0].length);
            default -> throw new ChristmasException("Go implement this unsupported array type! 🎅 " + grid.getClass().getSimpleName());
        };
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
