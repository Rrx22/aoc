package rrx.utils;

public class PrintUtil {

    private PrintUtil() {
    }

    public static void grid(char[][] grid) {
        StringBuilder sb = new StringBuilder();
        for (char[] chars : grid) {
            for (char c : chars) {
                sb.append(c);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void gridZoom(char[][] tempGrid, int x, int y) {
        int maxHeight = 26;
        int maxWidth = tempGrid[0].length;
        char[][] smallGrid = new char[maxHeight][maxWidth];
        for (int i = 0; i < maxHeight; i++) {
            for (int j = 0; j < maxWidth; j++) {
                try {
                    smallGrid[i][j] = tempGrid[y - maxHeight/2 + i][x - maxWidth/2 + j];
                } catch (ArrayIndexOutOfBoundsException e) {
                    smallGrid[i][j] = ' ';
                }
            }
        }
        grid(smallGrid);
    }
}
