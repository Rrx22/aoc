package rrx.utils;

import rrx.ChristmasException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileUtil {
    private FileUtil() {
    }

    public static List<String> readFile(String fileName) {
        try {
            var resourcePath = Paths.get(ClassLoader.getSystemResource(fileName).toURI());
            return Files.readAllLines(resourcePath);
        } catch (URISyntaxException | IOException e) {
            throw new ChristmasException(e.getMessage(), e);
        }
    }

    public static char[][] readToGrid(String fileName) {
        try {
            var path = Paths.get(ClassLoader.getSystemResource(fileName).toURI());
            try (var lines = Files.lines(path)) {
                return lines
                        .map(String::toCharArray)
                        .toArray(char[][]::new);
            }
        } catch (Exception e) {
            throw new ChristmasException(e.getMessage(), e);
        }
    }

    public static int[][] readToIntArr(String fileName, String divider) {
        try {
            var path = Paths.get(ClassLoader.getSystemResource(fileName).toURI());
            try (var lines = Files.lines(path)) {
                return lines
                        .map(line -> Arrays.stream(line.split(divider))
                                .mapToInt(Integer::parseInt)
                                .toArray())
                        .toArray(int[][]::new);
            }
        } catch (Exception e) {
            throw new ChristmasException(e.getMessage(), e);
        }
    }
}
