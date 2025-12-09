package rrx.aoc25.day7;

import rrx.ChristmasException;
import rrx.utils.PrintUtil;
import rrx.visualizer.constant.Visualisable;

import javax.swing.JPanel;
import java.util.ArrayDeque;
import java.util.Deque;

public class TeleportationDevice implements Visualisable {

    private JPanel gridPanel;
    private final char[][] grid;
    private Coord startCoord;

    Boolean useGridUI = true;

    public TeleportationDevice(char[][] grid) {
        this.grid = grid;
        for (int y = 0; y < grid.length; y++) {
            if (startCoord != null) break;
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] == 'S') {
                    startCoord = new Coord(y, x);
                    break;
                }
            }
        }
        if (startCoord == null) {
            startCoord = new Coord(-1, -1);
            throw new ChristmasException("Why on earth was there no S found???");
        }
    }

    public long fixTachyonManifolds() {
        long splitCounter = 0L;

        Deque<Coord> tachyonBeams = new ArrayDeque<>();
        tachyonBeams.push(startCoord);

        while (!tachyonBeams.isEmpty()) {
            var tb = tachyonBeams.pop();
//            System.out.println("🎅🎅🎅 NEXT 🎅🎅🎅 " + tb);
            if (grid[tb.y][tb.x] == '.') {
                grid[tb.y][tb.x] = '|';
            }
            draw();

            int i = 0;
            boolean outOfBounds = false;
            char nextChar = 'x';
            do {
                i++;
                if (tb.y + i >= grid.length) {
                    outOfBounds = true;
                    break;
                }
                nextChar = grid[tb.y + i][tb.x];
                if (nextChar == '.') {
                    grid[tb.y + i][tb.x] = '|';
                } else if (nextChar == '|') {
                    break;
                }
                draw();
            } while (nextChar != '^');
            if (outOfBounds || nextChar == '|') {
                continue;
            }
            splitCounter++;
            if (tb.x - 1 >= 0) {
                Coord left = new Coord(tb.y + i, tb.x - 1);
                tachyonBeams.push(left);
            }
            if (tb.x + 1 < grid[0].length) {
                Coord right = new Coord(tb.y + i, tb.x + 1);
                tachyonBeams.push(right);
            }
//            System.out.println(tachyonBeams.size());
//            tachyonBeams.forEach(System.out::println);
        }

        return splitCounter;
    }

    private void draw() {
        int millis = 50;
        if (useGridUI == null) {
            return;
        } else if (useGridUI) {
            repaint(millis);
            return;
        } else {
            try {
                PrintUtil.grid(grid);
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
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
