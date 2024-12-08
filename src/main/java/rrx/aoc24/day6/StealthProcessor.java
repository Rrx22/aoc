package rrx.aoc24.day6;

import rrx.ChristmasException;
import rrx.utils.Direction;
import rrx.utils.GridUtil;
import rrx.visualizer.GridBuilder;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.util.HashSet;
import java.util.Set;

public class StealthProcessor {

    private final char[][] grid;
    private JPanel gridPanel;
    private final Guard guard = new Guard();
    private final int[] startPos;

    public StealthProcessor(char[][] grid, JPanel gridPanel) {
        this(grid);
        this.gridPanel = gridPanel;
    }

    public StealthProcessor(char[][] grid) {
        this.grid = grid;
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
                repaint(grid, guard);
            } catch (ArrayIndexOutOfBoundsException | InterruptedException e) {
                return guard.travelCount;
            }
        }
    }

    public long makeGuardsLifeHell(GridBuilder gb) {
        long validObstructions = 0;

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] != '.' || (x == startPos[0] && y == startPos[1])) {
                    continue;
                }

                char[][] tempGrid = GridUtil.cloneGrid(grid);
                if (gb != null) gb.grid = tempGrid;
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
                repaint(tempGrid, tempGuard);
            } catch (ArrayIndexOutOfBoundsException | InterruptedException e) {
                break;
            }
        }
        return false;
    }

    private void repaint(char[][] grid, Guard guard) throws InterruptedException {
        if (gridPanel == null) {
            print(grid, guard);
        } else {
            SwingUtilities.invokeLater(gridPanel::repaint);
            Thread.sleep(2);
        }
    }

    private void print(char[][] printGrid, Guard guard) {
//        PrintUtil.gridZoom(printGrid, guard.x, guard.y);
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
}
