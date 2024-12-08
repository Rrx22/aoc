package rrx.aoc24.day8;

import rrx.utils.GridUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class FrequencySpy {

    private final char[][] grid;
    private final Map<Character, List<int[]>> allAntennas;
    private final Set<String> visitedNodes;
    private boolean alsoCheckHarmonics;

    public FrequencySpy(char[][] grid) {
        this.grid = grid;
        this.visitedNodes = new HashSet<>();
        this.allAntennas = new HashMap<>();

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                char c = grid[y][x];
                if (c == '.') continue;
                allAntennas
                        .compute(c, (_, val) -> val == null ? new ArrayList<>() : val)
                        .add(new int[]{x, y});
            }
        }
    }

    public long mapAntiNodes() {
        return allAntennas.values()
                .stream()
                .mapToLong(this::loopOverAntennaNodes)
                .sum();
    }

    private long loopOverAntennaNodes(List<int[]> antennas) {
        long antiNodes = 0L;
        for (int i = 0; i < antennas.size(); i++) {
            int[] iNode = antennas.get(i);
            for (int j = 0; j < antennas.size(); j++) {
                if (i == j) continue;
                int[] jNode = antennas.get(j);
                antiNodes += findAntiNodes(iNode, jNode);
            }
        }
        return antiNodes;
    }

    private long findAntiNodes(int[] iNode, int[] jNode) {
        long result = 0L;
        int xDiff = iNode[0] - jNode[0];
        int yDiff = iNode[1] - jNode[1];
        int[] antiNode1 = Arrays.copyOf(alsoCheckHarmonics ? jNode : iNode, 2);
        int[] antiNode2 = Arrays.copyOf(alsoCheckHarmonics ? iNode : jNode, 2);

        while (GridUtil.isWithinBounds(antiNode1[0], antiNode1[1], grid[0].length, grid.length)) {
            antiNode1[0] += xDiff;
            antiNode1[1] += yDiff;
            result += processAntiNode(antiNode1[0], antiNode1[1]);
            if (!alsoCheckHarmonics) break;
        }
        while (GridUtil.isWithinBounds(antiNode2[0], antiNode2[1], grid[0].length, grid.length)) {
            antiNode2[0] -= xDiff;
            antiNode2[1] -= yDiff;
            result += processAntiNode(antiNode2[0], antiNode2[1]);
            if (!alsoCheckHarmonics) break;
        }
        return result;
    }

    private long processAntiNode(int x, int y) {
        String node = y + "," + x;
        if (GridUtil.isWithinBounds(x, y, grid[0].length, grid.length) && visitedNodes.add(node)) {
//            if (alsoCheckHarmonics && grid[y][x] == '.') grid[y][x] = '#';
            return 1L;
        }
        return 0L;
    }

    public void resetAndEnableCheckingHarmonics() {
        this.visitedNodes.clear();
        this.alsoCheckHarmonics = true;
    }
}
