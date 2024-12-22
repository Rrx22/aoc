package rrx.visualizer;

import rrx.ChristmasException;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public interface Visualisable {

    char[][] getGrid();
    void setGridPanel(JPanel gridPanel);
    JPanel getGridPanel();

    /**
     * 📺 Call this function at the moment you want to update the UI
     * 📺 Make sure the correct grid is set and being updated
     */
    default void repaint(int millis) {
        try {
            SwingUtilities.invokeLater(getGridPanel()::repaint);
            Thread.sleep(millis);
        } catch (InterruptedException | NullPointerException e) {
            throw new ChristmasException(e.getMessage());
        }
    }
}
