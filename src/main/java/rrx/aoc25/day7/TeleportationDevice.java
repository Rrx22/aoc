package rrx.aoc25.day7;

import rrx.utils.Direction;
import rrx.visualizer.constant.Visualisable;

import javax.swing.JPanel;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeleportationDevice implements Visualisable {

    private JPanel gridPanel;
    private final char[][] grid;

    public TeleportationDevice(char[][] grid) {
        this.grid = grid;
    }

    public long fixTachyonManifolds() {
        long splitCounter = 0L;
        Deque<Coord> tachyonBeams = new ArrayDeque<>();
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] == 'S') {
                    tachyonBeams.add(new Coord(y, x));
                    break;
                }
            }
        }// find start coord

        while (!tachyonBeams.isEmpty()) {
            var tb = tachyonBeams.pop();
            if (grid[tb.y][tb.x] == '.') {
                grid[tb.y][tb.x] = '|';
            }
            draw();

            int i = 0;
            char nextChar = '0';
            do {
                i++;
                if (tb.y + i >= grid.length) break; // out of bounds
                nextChar = grid[tb.y + i][tb.x];
                if (nextChar == '.') {
                    grid[tb.y + i][tb.x] = '|';
                }
                draw();
            } while (nextChar != '^' && nextChar != '|');
            if (nextChar != '^') {
                continue; // either hit the bottom OR an already seen split
            }
            Coord left = new Coord(tb.y + i, tb.x - 1);
            Coord right = new Coord(tb.y + i, tb.x + 1);
            if (left.x >= 0) {
                tachyonBeams.add(left);
            }
            if (right.x < grid[0].length) {
                tachyonBeams.add(right);
            }
            splitCounter++;
        }
        return splitCounter;
    }

    public long fixQuantumTachyonManifolds() {
        long result = 0L;

        Map<Coord, Long> foundOptions = new HashMap<>();
        for (int y = grid.length - 1; y >= 0; y--) { // start scanning from the bottom
            for (int x = 0; x < grid[0].length; x++) {
                char curr = grid[y][x];
                if (curr == 'S') {
                    break;
                } else if (curr == '.') {
                    continue;
                }
                Coord currCoord = new Coord(y, x);
                long currOptions = 0L;

                for (Direction direction : List.of(Direction.LEFT, Direction.RIGHT)) {
                    int i = 0;
                    char lrChar = '0';
                    do {
                        i++;
                        if (y + i >= grid.length) break; // out of bounds
                        lrChar = grid[y + i][x + direction.x];
                    } while (lrChar != '^');
                    if (lrChar == '^') {
                        Coord nextHitSplit = new Coord(y + i, x + direction.x);
                        currOptions += foundOptions.get(nextHitSplit);
                    } else {
                        currOptions += 1L; // hits the bottom of the grid, so only 1 outcome
                    }
                }
                foundOptions.put(currCoord, currOptions);
                result = currOptions;
            }
        }
        return result;
    }

    private void draw() {
        repaint(1);
    }

    @Override
    public char[][] getGrid() {
        return grid;
    }

    @Override
    public void setGridPanel(JPanel gridPanel) {
        this.gridPanel = gridPanel;
    }

    @Override
    public JPanel getGridPanel() {
        return gridPanel;
    }

    @Override
    public void executeTask() {
        this.fixTachyonManifolds();
    }

    private record Coord(int y, int x) {
    }
}
