package rrx.visualizer.workers.aoc24;

import rrx.aoc24.day15.EnlargedWarehouseManager;
import rrx.visualizer.Visualisable;

import javax.swing.SwingWorker;

public class Day15Worker extends SwingWorker<Void, Void> {

    private final EnlargedWarehouseManager warehouseManager;

    public Day15Worker(Visualisable warehouseManager) {
        this.warehouseManager = (EnlargedWarehouseManager) warehouseManager;
    }

    @Override
    protected Void doInBackground() {
        warehouseManager.moveRobot();
        return null;
    }

    @Override
    protected void done() {
        System.out.println("Robot movements finished.");
    }
}
