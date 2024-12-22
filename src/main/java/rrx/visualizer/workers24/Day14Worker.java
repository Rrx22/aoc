package rrx.visualizer.workers24;

import rrx.aoc24.day14.RAT;
import rrx.visualizer.Visualisable;

import javax.swing.SwingWorker;

public class Day14Worker extends SwingWorker<Void, Void> {

    private final RAT rat;

    public Day14Worker(Visualisable rat) {
        this.rat = (RAT) rat;
    }

    @Override
    protected Void doInBackground() {
        rat.moveRobots(Long.MAX_VALUE);
        return null;
    }

    @Override
    protected void done() {
        System.out.println("Robot movements finished.");
    }
}
