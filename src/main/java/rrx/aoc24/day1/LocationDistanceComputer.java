package rrx.aoc24.day1;

import rrx.ChristmasAssert;
import rrx.ChristmasException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.PriorityQueue;
import java.util.Queue;

public class LocationDistanceComputer {

    public static void main(String[] args) throws URISyntaxException {
        var locationQs = readToLocationQs();
        ChristmasAssert.test(locationQs.calculateDistance(), 1223326L);
    }

    static LocationQs readToLocationQs() throws URISyntaxException {
        LocationQs lq = new LocationQs(new PriorityQueue<>(), new PriorityQueue<>());
        var resourcePath = Paths.get(ClassLoader.getSystemResource("24/d1p1").toURI());

        try (var br = Files.newBufferedReader(resourcePath)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] x = line.split(" +");
                lq.first().add(Long.parseLong(x[0]));
                lq.second().add(Long.parseLong(x[1]));
            }
        } catch (IOException e) {
            throw new ChristmasException(e.getMessage(), e);
        }
        return lq;
    }

    record LocationQs(Queue<Long> first, Queue<Long> second) {
        long calculateDistance() {
            long distance = 0;
            while (!first.isEmpty() && !second.isEmpty()) {
                distance += Math.abs(first.poll() - second.poll());
            }
            return distance;
        }
    }
}
