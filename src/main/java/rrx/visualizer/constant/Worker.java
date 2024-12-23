package rrx.visualizer.constant;

import javax.swing.SwingWorker;

public class Worker extends SwingWorker<Void, Void> {

    private final Visualisable visualisable;

    public Worker(Visualisable visualisable) {
        this.visualisable = visualisable;
    }

    @Override
    protected Void doInBackground() {
        visualisable.executTask();
        return null;
    }

    @Override
    protected void done() {
        System.out.println("Guard logic finished.");
    }
}
