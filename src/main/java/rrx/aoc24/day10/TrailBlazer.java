package rrx.aoc24.day10;

import rrx.utils.GridUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static rrx.utils.Direction.DOWN;
import static rrx.utils.Direction.LEFT;
import static rrx.utils.Direction.RIGHT;
import static rrx.utils.Direction.UP;

class TrailBlazer {

    private char[][] grid;
    private Map<String, Map<String, Integer>> trails;

    public long totalSets() {
        return trails.values().stream()
                .mapToInt(Map::size)
                .sum();
    }

    public long totalTrails() {
        return trails.values().stream()
                .flatMap(s -> s.values().stream())
                .mapToInt(Integer::intValue)
                .sum();
    }

    public TrailBlazer withGrid(char[][] grid) {
        this.grid = grid;
        return this;
    }

    public TrailBlazer withPopulatedTrailHeads() {
        this.trails = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '0') {
                    trails.put(String.format("%d,%d", i, j), new HashMap<>());
                }
            }
        }
        return this;
    }

    public TrailBlazer withPopulateRoutes() {
        for (var trailHead : trails.keySet()) {
            var coords = Arrays.stream(trailHead.split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            populateRoute(0, trailHead, coords[0], coords[1]);
        }
        return this;
    }

    private void populateRoute(int expectedVal, String trailHead, int row, int col) {
        if (!GridUtil.isWithinBounds(row, col, grid)) {
            return; // exit; out of bounds
        }

        int currVal = Character.getNumericValue(grid[row][col]);
        if (currVal != expectedVal) {
            return; // exit; wrong value
        }

        if (currVal == 9) {
            String coords = String.format("%d,%d", row, col);
            trails.get(trailHead)
                    .compute(coords, (_, v) -> (v == null) ? 1 : v + 1);
            return; // exit; successful find
        }

        populateRoute(currVal + 1, trailHead, row + RIGHT.y, col + RIGHT.x);
        populateRoute(currVal + 1, trailHead, row + LEFT.y, col + LEFT.x);
        populateRoute(currVal + 1, trailHead, row + UP.y, col + UP.x);
        populateRoute(currVal + 1, trailHead, row + DOWN.y, col + DOWN.x);
    }
}
