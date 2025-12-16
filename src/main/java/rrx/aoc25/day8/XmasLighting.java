package rrx.aoc25.day8;

import rrx.utils.MathUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

class XmasLighting {

    public final int MAX;
    private boolean tryHarder;
    private final List<Map.Entry<JunctionBoxPair, Double>> sortedEntries;

    public XmasLighting(List<String> input) {
        MAX = input.size();
        long[][] junctionBoxes = new long[MAX][3];
        for (int i = 0; i < input.size(); i++) {
            var xyz = input.get(i).split(",");
            junctionBoxes[i][0] = Long.parseLong(xyz[0]);
            junctionBoxes[i][1] = Long.parseLong(xyz[1]);
            junctionBoxes[i][2] = Long.parseLong(xyz[2]);
        }

        Map<JunctionBoxPair, Double> distances = new HashMap<>();
        for (int i = 0; i < junctionBoxes.length; i++) {
            for (int j = i + 1; j < junctionBoxes.length; j++) {
                JunctionBoxPair pair = new JunctionBoxPair(junctionBoxes[i], junctionBoxes[j]);
                distances.put(pair, MathUtil.euclideanDistance(pair.p1, pair.p2));
            }
        }
        sortedEntries = distances.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .toList();
    }

    public long hangTheLights() {
        return tryHarder
                ? part2()
                : part1();
    }

    private long part1() {
        List<Set<long[]>> finalCircuits = new ArrayList<>();
        int connectionsMade = 0;
        for (var entry : sortedEntries) {
            if (connectionsMade >= MAX) break;
            List<Set<long[]>> matchingCircuits = new ArrayList<>();
            for (Set<long[]> circuit : finalCircuits) {
                if (circuit.contains(entry.getKey().p1) || circuit.contains(entry.getKey().p2)) {
                    matchingCircuits.add(circuit);
                }
            }
            addUpdateOrMergeCircuit(entry.getKey(), matchingCircuits, finalCircuits);
            connectionsMade++;
        }
        return finalCircuits.stream()
                .mapToLong(Set::size) // map to set sizes
                .boxed()// ignore
                .sorted(Comparator.reverseOrder()) // sort by size desc
                .limit(3) // take only the first three
                .reduce(1L, (a, b) -> a * b); // multiply
    }

    private long part2() {
        List<Set<long[]>> finalCircuits = new ArrayList<>();
        JunctionBoxPair currentPair = new JunctionBoxPair(new long[]{-1, -1, -1}, new long[]{-1, -1, -1});
        for (var entry : sortedEntries) {
            currentPair = entry.getKey();
            List<Set<long[]>> matchingCircuits = new ArrayList<>();
            for (Set<long[]> circuit : finalCircuits) {
                if (circuit.contains(currentPair.p1) || circuit.contains(currentPair.p2)) {
                    matchingCircuits.add(circuit);
                }
            }
            addUpdateOrMergeCircuit(currentPair, matchingCircuits, finalCircuits);
            if (finalCircuits.size() == 1 && finalCircuits.getFirst().size() == MAX) {
                break;
            }
        }
        return currentPair.p1[0] * currentPair.p2[0];
    }

    private static void addUpdateOrMergeCircuit(JunctionBoxPair pair, List<Set<long[]>> matchingCircuits, List<Set<long[]>> finalCircuits) {
        Set<long[]> targetCircuit;
        if (matchingCircuits.isEmpty()) { // ADD new circuit
            targetCircuit = new HashSet<>();
            finalCircuits.add(targetCircuit);
        } else if (matchingCircuits.size() == 1) { // UPDATE existing circuit
            targetCircuit = matchingCircuits.getFirst();
        } else { // MERGE multiple circuits
            targetCircuit = new HashSet<>();
            finalCircuits.add(targetCircuit);
            for (Set<long[]> circuit : matchingCircuits) {
                targetCircuit.addAll(circuit);
                finalCircuits.remove(circuit);
            }
        }
        targetCircuit.add(pair.p1);
        targetCircuit.add(pair.p2);
    }

    public void tryHarder() {
        tryHarder = true;
    }

    private record JunctionBoxPair(long[] p1, long[] p2) {
        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            JunctionBoxPair that = (JunctionBoxPair) o;
            return (Objects.deepEquals(p1, that.p1) && Objects.deepEquals(p2, that.p2))
                    || (Objects.deepEquals(p1, that.p2) && Objects.deepEquals(p2, that.p1));
        }
    }
}
