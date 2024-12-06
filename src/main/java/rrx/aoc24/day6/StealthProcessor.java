package rrx.aoc24.day6;

import rrx.ChristmasException;
import rrx.utils.Direction;

import java.util.HashSet;
import java.util.Set;

class StealthProcessor {

    private final char[][] grid;
    private final Guard guard = new Guard();
    private final int[] startPos;

    public StealthProcessor(char[][] grid) {
        this.grid = grid;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                char c = grid[y][x];
                if (c != '.' && c != '#') {
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
                print(grid, guard);
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

                char[][] tempGrid = cloneGrid(grid);
                tempGrid[y][x] = '#';

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
                print(tempGrid, tempGuard);
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }
        return false;
    }

    private void print(char[][] printGrid, Guard guard) {
//        PrintUtil.gridZoom(printGrid, guard.x, guard.y);
    }

    private char[][] cloneGrid(char[][] og) {
        char[][] clone = new char[og.length][og[0].length];
        for (int i = 0; i < og.length; i++) {
            System.arraycopy(og[i], 0, clone[i], 0, og[i].length);
        }
        return clone;
    }

    private char moveGuard(char[][] currGrid, Guard currGuard) {
        char next = currGrid[currGuard.nextY()][currGuard.nextX()];
        while (next == '#') {
            currGuard.turnRight();
            next = currGrid[currGuard.nextY()][currGuard.nextX()];
            if (next != '#') {
                currGrid[currGuard.y][currGuard.x] = '+';
            }
        }
        currGuard.move(next);
        return next;
    }
}
