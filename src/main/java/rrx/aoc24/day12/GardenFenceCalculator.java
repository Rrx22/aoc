package rrx.aoc24.day12;

import rrx.utils.Direction;
import rrx.utils.GridUtil;
import rrx.utils.Pair;

import java.util.ArrayDeque;
import java.util.Deque;

class GardenFenceCalculator {

    private GardenFenceCalculator() {
    }

    public static Pair<Long, Long> calculateFencePrice(char[][] gardenMap) {
        boolean[][] visited = new boolean[gardenMap.length][gardenMap[0].length];
        long totalPrice = 0;
        long totalDiscountPrice = 0;

        for (int i = 0; i < gardenMap.length; i++) {
            for (int j = 0; j < gardenMap[0].length; j++) {
                if (!visited[i][j]) {
                    char plantType = gardenMap[i][j];
                    Plot plot = floodFill(gardenMap, visited, i, j, plantType);
                    totalPrice += plot.area * plot.perimeter;
                    totalDiscountPrice += plot.area * plot.sides;
                }
            }
        }

        return new Pair<>(totalPrice, totalDiscountPrice);
    }

    private static Plot floodFill(char[][] gardenMap, boolean[][] visited, int startRow, int startCol, char plantType) {
        long area = 0;
        long perimeter = 0;
        long sides = 0;

        Deque<int[]> camino = new ArrayDeque<>();
        camino.push(new int[]{startRow, startCol});

        while (!camino.isEmpty()) {
            int[] current = camino.pop();
            int x = current[0];
            int y = current[1];

            if (visited[x][y]) {
                continue;
            }
            visited[x][y] = true;
            area++;
            sides += checkSides(x, y, gardenMap);

            for (var dir : Direction.valuesRLDU()) {
                int nx = x + dir.x;
                int ny = y + dir.y;

                if (GridUtil.isWithinBounds(nx, ny, gardenMap)) {
                    if (gardenMap[nx][ny] == plantType && !visited[nx][ny]) {
                        camino.push(new int[]{nx, ny});
                    } else if (gardenMap[nx][ny] != plantType) {
                        perimeter++;
                    }
                } else {
                    perimeter++;
                }
            }
        }

        return new Plot(plantType, area, perimeter, sides);
    }

    private static long checkSides(int x, int y, char[][] gardenMap) {
        long sides = 0;
        boolean rightPossible = checkSide(Direction.RIGHT, x, y, gardenMap);
        boolean leftPossible = checkSide(Direction.LEFT, x, y, gardenMap);
        boolean upPossible = checkSide(Direction.UP, x, y, gardenMap);
        boolean downPossible = checkSide(Direction.DOWN, x, y, gardenMap);
        boolean upLeftPossible = checkSide(Direction.UP_LEFT, x, y, gardenMap);
        boolean upRightPossible = checkSide(Direction.UP_RIGHT, x, y, gardenMap);
        boolean downLeftPossible = checkSide(Direction.DOWN_LEFT, x, y, gardenMap);
        boolean downRightPossible = checkSide(Direction.DOWN_RIGHT, x, y, gardenMap);

        if (rightPossible && upPossible) sides++;
        if (rightPossible && downPossible) sides++;
        if (leftPossible && upPossible) sides++;
        if (leftPossible && downPossible) sides++;
        if (upLeftPossible && !upPossible && !leftPossible) sides++;
        if (upRightPossible && !upPossible && !rightPossible) sides++;
        if (downLeftPossible && !downPossible && !leftPossible) sides++;
        if (downRightPossible && !downPossible && !rightPossible) sides++;

        return sides;
    }

    private static boolean checkSide(Direction direction, int x, int y, char[][] gardenMap) {
        int nx = x + direction.x;
        int ny = y + direction.y;
        boolean withinBounds = GridUtil.isWithinBounds(nx, ny, gardenMap);
        if (direction.isDiagonal()) {
            return withinBounds && gardenMap[x][y] != gardenMap[nx][ny];
        }
        return !withinBounds || gardenMap[x][y] != gardenMap[nx][ny];
    }

    record Plot(char plantType, long area, long perimeter, long sides) {
    }
}
