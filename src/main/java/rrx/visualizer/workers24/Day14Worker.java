package rrx.visualizer.workers24;

import rrx.aoc24.day14.RAT;

import javax.swing.SwingWorker;

public class Day14Worker extends SwingWorker<Void, Void> {

    private final RAT rat;

    public Day14Worker(RAT rat) {
        this.rat = rat;
    }

    @Override
    protected Void doInBackground() throws InterruptedException {
        rat.moveRobots(Long.MAX_VALUE);
        return null;
    }

    @Override
    protected void done() {
        System.out.println("Robot movements finished.");
    }
}
