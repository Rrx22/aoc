package rrx.aoc24.day1;

import rrx.ChristmasAssert;
import rrx.ChristmasException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

class LocationSimilarityComputer {

    static void main() throws URISyntaxException {
        var locationLists = readToLocationMaps();
        ChristmasAssert.test(locationLists.calculateSimilarity(), 21070419L);
    }

    static LocationMaps readToLocationMaps() throws URISyntaxException {
        LocationMaps lm = new LocationMaps(new HashMap<>(), new HashMap<>());
        var resourcePath = Paths.get(ClassLoader.getSystemResource("24/d01p1").toURI());

        try (var br = Files.newBufferedReader(resourcePath)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] x = line.split(" +");
                lm.appendVal(true, Long.parseLong(x[0]));
                lm.appendVal(false, Long.parseLong(x[1]));
            }
        } catch (IOException e) {
            throw new ChristmasException(e.getMessage(), e);
        }
        return lm;
    }

    record LocationMaps(HashMap<Long, Long> first, HashMap<Long, Long> second) {
        void appendVal(boolean isFirst, long key) {
            HashMap<Long, Long> mapToCompute = isFirst ? first : second;
            mapToCompute.compute(key, (_, count) -> count == null ? 1L : count + 1L);
        }

        long calculateSimilarity() {
            return first.keySet().stream()
                    .mapToLong(key -> key * first.get(key) * second.getOrDefault(key, 0L))
                    .sum();
        }
    }
}
