package rrx.aoc24.day15;

import rrx.ChristmasException;
import rrx.utils.Direction;
import rrx.utils.PrintUtil;

import java.util.ArrayList;
import java.util.List;

class WarehouseManager {

    private final char[][] grid;
    private final List<Direction> directions = new ArrayList<>();
    private final Robot robot;

    public WarehouseManager(List<String> input) {
        int split = input.indexOf("");
        this.grid = new char[input.getFirst().length()][split];
        int[] robotcoords = new int[0];

        for (int i = 0; i < input.size(); i++) {
            char[] charArray = input.get(i).toCharArray();
            for (int j = 0; j < charArray.length; j++) {
                char c = charArray[j];
                if (i < split) {
                    grid[i][j] = c;
                    if (c == '@') {
                        robotcoords = new int[]{j, i};
                    }
                } else if (i > split) {
                    directions.add(switch (c) {
                        case '>' -> Direction.RIGHT;
                        case '<' -> Direction.LEFT;
                        case 'v' -> Direction.DOWN;
                        case '^' -> Direction.UP;
                        default -> throw new ChristmasException("Nope");
                    });
                }
            }
        }
        robot = new Robot('@', robotcoords);
    }

    public long moveRobot() {
        for (var dir : directions) {
            robot.move(dir, grid);
        }
        PrintUtil.grid(grid);
        return sumBoxGPSCoords();
    }

    private long sumBoxGPSCoords() {
        long sum = 0L;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 'O') {
                    sum += i * 100L + j;
                }
            }
        }
        return sum;
    }

    record Robot(char c, int[] coords) {
        public void move(Direction dir, char[][] grid) {
            int nextX = coords[0];
            int nextY = coords[1];
            while (true) {
                nextX += dir.x;
                nextY += dir.y;
                char c = grid[nextY][nextX];
                if (c == '#') return;
                if (c == '.') break;
            }

            while (!(nextX == coords[0] && nextY == coords[1])) {
                int currX = nextX;
                int currY = nextY;
                nextX -= dir.x;
                nextY -= dir.y;
                grid[currY][currX] = grid[nextY][nextX];
            }
            grid[coords[1]][coords[0]] = '.';
            coords[0] += dir.x;
            coords[1] += dir.y;
        }
    }
}
