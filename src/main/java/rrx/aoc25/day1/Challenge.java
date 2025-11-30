package rrx.aoc25.day1;

import rrx.visualizer.constant.Visualisable;

import javax.swing.JPanel;

public class Challenge implements Visualisable {

    private final char[][] grid;
    private JPanel gridPanel;
    private boolean changedItUp;

    public Challenge(char[][] grid) {
        this.grid = grid;
    }

    public long doSomething() {
        repaint(0);
        return changedItUp
                ? doItDifferently()
                : doIt();
    }

    private long doIt() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.println(grid[i][j] + ": " + i + " - " + j);
                grid[i][j] = 'Y';
                repaint(200);
            }
        }
        return 0L;
    }

    private long doItDifferently() {
        return 0L;
    }

    public void changeItUp() {
        changedItUp = true;
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
        doSomething();
    }
}
