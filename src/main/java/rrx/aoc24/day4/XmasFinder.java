package rrx.aoc24.day4;

interface XmasFinder {

    int edgeSkipper();
    boolean notInteresting(int i, int j);
    long findXmas(int i, int j);
}
