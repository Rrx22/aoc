package rrx.visualizer.constant;

import rrx.ChristmasException;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public interface Visualisable {

    char[][] getGrid();
    void setGridPanel(JPanel gridPanel);
    JPanel getGridPanel();

    // 🔥To kick off the actual task of the AOC challenge🔥
    void executTask();

    /**
     * 📺 Call this function at the moment you want to update the UI
     * 📺 Make sure the correct grid is set and being updated
     */
    default void repaint(int millis) {
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
