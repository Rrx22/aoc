package rrx.visualizer.constant;

import rrx.ChristmasException;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public abstract class VisualisableImpl implements Visualisable {

    private JPanel gridPanel;

    public void setGridPanel(JPanel gridPanel) {
        this.gridPanel = gridPanel;
    }

    public JPanel getGridPanel() {
        return gridPanel;
    }

    /**
     * 📺 Call this function at the moment you want to update the UI
     * 📺 Make sure the correct grid is set and being updated
     */
    public void repaint(int millis) {
        if (getGridPanel() == null) {
            System.out.println("WARN: Gridpanel is null");
            return;
        }
        try {
            SwingUtilities.invokeLater(getGridPanel()::repaint);
            Thread.sleep(millis);
        } catch (InterruptedException | NullPointerException e) {
            throw new ChristmasException(e.getMessage());
        }
    }
}
