package rrx.visualizer;

import rrx.aoc24.day6.StealthProcessor;

import javax.swing.SwingWorker;

public class Worker extends SwingWorker<Void, Void> {

    private final StealthProcessor stealthProcessor;
    private final GridBuilder gb;
    private final int part;

    public Worker(StealthProcessor stealthProcessor, GridBuilder gb, int part) {
        this.stealthProcessor = stealthProcessor;
        this.gb = gb;
        this.part = part;
    }

    @Override
    protected Void doInBackground() {
        if (part == 1) {
            stealthProcessor.sneakPastGuard();
        } else if (part == 2) {
            stealthProcessor.makeGuardsLifeHell(gb);
        }
        return null;
    }

    @Override
    protected void done() {
        System.out.println("Guard logic finished.");
    }
}
