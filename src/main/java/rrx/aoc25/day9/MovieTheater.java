package rrx.aoc25.day9;

import java.util.ArrayList;
import java.util.List;

public class MovieTheater {

    private final List<int[]> redTileCoords;

    public MovieTheater(List<String> input) {
        redTileCoords = new ArrayList<>();
        for (String line : input) {
            int splitIdx = line.indexOf(',');
            int x = Integer.parseInt(line.substring(0, splitIdx));
            int y = Integer.parseInt(line.substring(splitIdx + 1));
            redTileCoords.add(new int[] { x, y });
        }
    }

    long findLargestRedTileRectangle() {
        long maxResult = 0L;
        for (int i = 0; i < redTileCoords.size(); i++) {
            for (int j = i + 1; j < redTileCoords.size(); j++) {
                long x = Math.abs(redTileCoords.get(i)[0] - redTileCoords.get(j)[0]) + 1;
                long y = Math.abs(redTileCoords.get(i)[1] - redTileCoords.get(j)[1]) + 1;
                long result = x * y;
                if (result > maxResult) maxResult = result;
            }
        }
        return maxResult;
    }
}
