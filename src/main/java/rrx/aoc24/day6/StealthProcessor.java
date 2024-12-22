package rrx.aoc24.day6;

import rrx.ChristmasException;
import rrx.utils.Direction;
import rrx.utils.GridUtil;
import rrx.visualizer.Visualisable;

import javax.swing.JPanel;
import java.util.HashSet;
import java.util.Set;

public class StealthProcessor implements Visualisable {

    private final char[][] grid;
    private final char[][] printGrid;
    private JPanel gridPanel;
    private final Guard guard = new Guard();
    private final int[] startPos;

    public StealthProcessor(char[][] grid) {
        this.grid = grid;
        this.printGrid = GridUtil.cloneGrid(grid);
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                char c = grid[y][x];
                if (c != '.' && c != '#' && c != 'O') {
                    guard.x = x;
                    guard.y = y;
                    guard.direction = switch (c) {
                        case '>' -> Direction.RIGHT;
                        case '<' -> Direction.LEFT;
                        case '^' -> Direction.UP;
                        case 'v' -> Direction.DOWN;
                        default -> throw new ChristmasException("No No No!");
                    };
                    break;
                }
            }
        }
        startPos = new int[]{guard.x, guard.y};
    }

    public long sneakPastGuard() {
        while (true) {
            try {
                char next = moveGuard(grid, guard);
                if (next == '.') {
                    grid[guard.y][guard.x] = guard.direction.isHorizontal() ? '-' : '|';
                } else if ((guard.direction.isHorizontal() && next == '|') || (guard.direction.isVertical() && next == '-')) {
                    grid[guard.y][guard.x] = '+';
                }
                show(grid, guard);
            } catch (ArrayIndexOutOfBoundsException e) {
                return guard.travelCount;
            }
        }
    }

    public long makeGuardsLifeHell() {
        long validObstructions = 0;

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] != '.' || (x == startPos[0] && y == startPos[1])) {
                    continue;
                }

                char[][] tempGrid = GridUtil.cloneGrid(grid);
                tempGrid[y][x] = 'O';
                if (causesLoop(tempGrid)) {
                    validObstructions++;
                }
            }
        }
        return validObstructions;
    }

    private boolean causesLoop(char[][] tempGrid) {
        Guard tempGuard = guard.clone();
        Set<String> visitedNodes = new HashSet<>();

        while (true) {
            String state = tempGuard.toString();
            if (!visitedNodes.add(state)) {
                return true;
            }

            try {
                char next = moveGuard(tempGrid, tempGuard);
                if (next == '.') {
                    tempGrid[tempGuard.y][tempGuard.x] = tempGuard.direction.isHorizontal() ? '-' : '|';
                } else if ((tempGuard.direction.isHorizontal() && next == '|') || (tempGuard.direction.isVertical() && next == '-')) {
                    tempGrid[tempGuard.y][tempGuard.x] = '+';
                }
                show(tempGrid, guard);
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }
        return false;
    }

    private void show(char[][] grid, Guard guard)  {
        if (gridPanel == null) {
//            PrintUtil.gridZoom(grid, guard.x, guard.y);
        } else {
            System.arraycopy(grid, 0, printGrid, 0, grid.length);
            repaint(2);
        }
    }

    private char moveGuard(char[][] currGrid, Guard currGuard) {
        char next = currGrid[currGuard.nextY()][currGuard.nextX()];
        while (next == '#' || next == 'O') {
            currGuard.turnRight();
            next = currGrid[currGuard.nextY()][currGuard.nextX()];
            if (next != '#' && next != 'O') {
                currGrid[currGuard.y][currGuard.x] = '+';
            }
        }
        currGuard.move(next);
        return next;
    }

    @Override
    public JPanel getGridPanel() {
        return gridPanel;
    }

    @Override
    public void setGridPanel(JPanel gridPanel) {
        this.gridPanel = gridPanel;
    }

    @Override
    public char[][] getGrid() {
        return printGrid;
    }
}
