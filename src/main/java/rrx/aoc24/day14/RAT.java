package rrx.aoc24.day14;

import rrx.ChristmasException;
import rrx.utils.PrintUtil;
import rrx.visualizer.Visualisable;

import javax.swing.JPanel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Robot Avoidance Tool
 */
public class RAT implements Visualisable {


    private JPanel gridPanel;
    private char[][] grid;
    private boolean isEasterEggFound = false;
    private List<Robot> robots;

    public long moveRobots(long moves) {

        for (long i = 0; i < moves; i++) {
            robots.forEach(r -> r.teleport(grid));
            updateGrid();

            if (i % 500 == 0) System.out.println("MILESTONE: " + i);
            if (isEasterEggFound) {
                return i + 1;
            }
        }

        return computeQuadrants();
    }

    private void updateGrid() {
        Map<String, Character> robotLocations = new HashMap<>();
        for (Robot robot : robots) {
            robotLocations.compute(robot.currLoc(), (_, v) -> v == null ? '1' :  (char) (v + 1));
        }

        for (var entry : robotLocations.entrySet()) {
            var coords = Arrays.stream(entry.getKey().split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            grid[coords[1]][coords[0]] = entry.getValue();
        }
        identifyEasterEgg();
        if (gridPanel == null) {
            PrintUtil.grid(grid);
        } else {
            repaint(2);
        }
    }

    private void identifyEasterEgg() {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                count = ('.' == grid[i][j]) ? 0 : count + 1;
                if (count > 10 && isChristmasTree(i)) {
                    isEasterEggFound = true;
                    return;
                }
            }
        }
    }

    private boolean isChristmasTree(int foundRow) {
        return analyseChristmasTreeRow(foundRow++, "1111111111111111111111111111111") &&
                analyseChristmasTreeRow(foundRow++, "1.............................1") &&
                analyseChristmasTreeRow(foundRow++, "1.............................1") &&
                analyseChristmasTreeRow(foundRow++, "1.............................1") &&
                analyseChristmasTreeRow(foundRow++, "1.............................1") &&
                analyseChristmasTreeRow(foundRow++, "1..............1..............1") &&
                analyseChristmasTreeRow(foundRow++, "1.............111.............1") &&
                analyseChristmasTreeRow(foundRow++, "1............11111............1") &&
                analyseChristmasTreeRow(foundRow++, "1...........1111111...........1") &&
                analyseChristmasTreeRow(foundRow++, "1..........111111111..........1") &&
                analyseChristmasTreeRow(foundRow++, "1............11111............1") &&
                analyseChristmasTreeRow(foundRow++, "1...........1111111...........1") &&
                analyseChristmasTreeRow(foundRow++, "1..........111111111..........1") &&
                analyseChristmasTreeRow(foundRow++, "1.........11111111111.........1") &&
                analyseChristmasTreeRow(foundRow++, "1........1111111111111........1") &&
                analyseChristmasTreeRow(foundRow++, "1..........111111111..........1") &&
                analyseChristmasTreeRow(foundRow++, "1.........11111111111.........1") &&
                analyseChristmasTreeRow(foundRow++, "1........1111111111111........1") &&
                analyseChristmasTreeRow(foundRow++, "1.......111111111111111.......1") &&
                analyseChristmasTreeRow(foundRow++, "1......11111111111111111......1") &&
                analyseChristmasTreeRow(foundRow++, "1........1111111111111........1") &&
                analyseChristmasTreeRow(foundRow++, "1.......111111111111111.......1") &&
                analyseChristmasTreeRow(foundRow++, "1......11111111111111111......1") &&
                analyseChristmasTreeRow(foundRow++, "1.....1111111111111111111.....1") &&
                analyseChristmasTreeRow(foundRow++, "1....111111111111111111111....1") &&
                analyseChristmasTreeRow(foundRow++, "1.............111.............1") &&
                analyseChristmasTreeRow(foundRow++, "1.............111.............1") &&
                analyseChristmasTreeRow(foundRow++, "1.............111.............1") &&
                analyseChristmasTreeRow(foundRow++, "1.............................1") &&
                analyseChristmasTreeRow(foundRow++, "1.............................1") &&
                analyseChristmasTreeRow(foundRow++, "1.............................1") &&
                analyseChristmasTreeRow(foundRow++, "1.............................1") &&
                analyseChristmasTreeRow(foundRow++, "1111111111111111111111111111111");
    }

    private long computeQuadrants() {
        int middleX = grid[0].length / 2;
        int middleY = grid.length / 2;
        long q1 = 0L, q2 = 0L, q3 = 0L, q4 = 0L;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (row == middleY || col == middleX) {
                    grid[row][col] = ' ';
                }
                char curr = grid[row][col];
                if (curr == '.' || curr == ' ') {
                    continue;
                }
                if (row < middleY && col < middleX) q1 += Character.getNumericValue(curr);
                if (row < middleY && col > middleX) q2 += Character.getNumericValue(curr);
                if (row > middleY && col < middleX) q3 += Character.getNumericValue(curr);
                if (row > middleY && col > middleX) q4 += Character.getNumericValue(curr);
            }
        }

        PrintUtil.grid(grid);
        return q1 * q2 * q3 * q4;
    }


    private boolean analyseChristmasTreeRow(int row, String expected) {
        StringBuilder sb = new StringBuilder();
        for (char s : grid[row]) {
            sb.append(s);
        }
        return sb.toString().contains(expected);
    }

    public void init(List<String> data, int height, int width) {
        this.robots = data.stream()
                .map(line -> {
                    Matcher m = Pattern.compile("p=(\\d+),(\\d+) v=([-\\d]+),([-\\d]+)").matcher(line);
                    if (!m.find()) throw new ChristmasException("not found for " + line);
                    return new Robot(
                            new int[]{Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2))},
                            Integer.parseInt(m.group(3)),
                            Integer.parseInt(m.group(4)));
                })
                .toList();

        this.grid = new char[height][width];
        initGrid();
    }

    private void initGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = '.';
            }
        }
        updateGrid();
    }

    @Override
    public char[][] getGrid() {
        return grid;
    }

    @Override
    public JPanel getGridPanel() {
        return gridPanel;
    }

    @Override
    public void setGridPanel(JPanel gridPanel) {
        this.gridPanel = gridPanel;
    }

    record Robot(int[] p, int vx, int vy) {
        public String currLoc() {
            return p[0] + "," + p[1];
        }

        public void teleport(char[][] grid) {
            grid[p[1]][p[0]] = '.';
            p[0] = determineNewLocation(p[0], vx, grid[0].length);
            p[1] = determineNewLocation(p[1], vy, grid.length);
        }

        private int determineNewLocation(int p, int v, int max) {
            int newP = p + v;
            if (newP < 0) { // out of bounds under min
                newP = max + newP;
            } else if (newP >= max) { // out of bounds over max
                newP = newP - max;
            }
            return newP;
        }
    }
}

