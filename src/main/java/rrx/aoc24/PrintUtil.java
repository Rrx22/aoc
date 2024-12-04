package rrx.aoc24;

public class PrintUtil {

    private PrintUtil() {}

    public static void grid(char[][] grid) {
        StringBuilder sb = new StringBuilder();
        for (char[] chars : grid) {
            for (char c : chars) {
                sb.append(c).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
