package rrx.aoc25.day4;

import rrx.utils.Direction;
import rrx.utils.Pair;
import rrx.visualizer.constant.Visualisable;

import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

public class PrintingDepartment implements Visualisable {

    private final char[][] grid;
    private JPanel gridPanel;
    private List<Pair<Integer, Integer>> clearCoordList;

    public PrintingDepartment(char[][] grid) {
        this.grid = grid;
    }

    public long clearOutRolls() {
        long rollsClearedOut = 0L;
        repaint(2000);

        long rollsThatCanBeCleared = countAccessibleRolls();
        while (rollsThatCanBeCleared > 0) {
            int currIt = 0;
            for (var coords : clearCoordList) {
                grid[coords.first()][coords.second()] = '.';
                currIt++;
            }
            repaint(100);
            rollsClearedOut += currIt;
            rollsThatCanBeCleared = countAccessibleRolls();
        }
        return rollsClearedOut;
    }

    private long countAccessibleRolls() {
        long count = 0L;
        clearCoordList = new ArrayList<>();
        repaint(200);

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] != '@') continue;
                int adjacantRollsOfPaper = 0;
                for (var direction : Direction.values()) {
                    try {
                        var x = grid[row + direction.y][col + direction.x];
                        if (x != '.') {
                            adjacantRollsOfPaper++;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // ignore hihi hoho
                    }
                }
                if (adjacantRollsOfPaper < 4) {
                    count++;
                    grid[row][col] = 'x';
                    clearCoordList.add(new Pair<>(row, col));
                }
            }
        }
        repaint(100);
        return count;
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
        clearOutRolls();
    }
}
