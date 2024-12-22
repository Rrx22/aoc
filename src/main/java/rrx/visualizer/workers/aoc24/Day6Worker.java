package rrx.visualizer.workers.aoc24;

import rrx.aoc24.day6.StealthProcessor;
import rrx.visualizer.Visualisable;

import javax.swing.SwingWorker;

public class Day6Worker extends SwingWorker<Void, Void> {

    private final StealthProcessor stealthProcessor;

    public Day6Worker(Visualisable stealthProcessor) {
        this.stealthProcessor = (StealthProcessor) stealthProcessor;
    }

    @Override
    protected Void doInBackground() {
        stealthProcessor.makeGuardsLifeHell();
        return null;
    }

    @Override
    protected void done() {
        System.out.println("Guard logic finished.");
    }
}
