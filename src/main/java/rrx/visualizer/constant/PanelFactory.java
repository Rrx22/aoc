package rrx.visualizer.constant;

import javax.swing.*;
import java.awt.*;

import static rrx.visualizer.constant.GridBuilder.TILE_SIZE;

public class PanelFactory {
    private final GridBuilder gb;
    private final Visualisable visualisable;

    public PanelFactory(GridBuilder gb, VisualisableImpl visualisable) {
        this.gb = gb;
        this.visualisable = visualisable;
    }

    public JPanel constructGrid() {
        return new GridPanel(gb, visualisable);
    }

    private static class GridPanel extends JPanel {
        private final GridBuilder gb;
        private final Visualisable visualisable;

        public GridPanel(GridBuilder gb, Visualisable visualisable) {
            this.gb = gb;
            this.visualisable = visualisable;
            setPreferredSize(new Dimension(gb.maxX * TILE_SIZE, gb.maxY * TILE_SIZE));
            setBackground(Color.BLACK);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            char[][] grid = gb.grid;

            for (int y = 0; y < gb.maxY; y++) {
                for (int x = 0; x < gb.maxX; x++) {
                    // Draw character
                    char c = this.visualisable.paintCharacter(grid[y][x]);
                    Color color = this.visualisable.paintColor(grid[y][x]);
                    g.setColor(color);
                    g.setFont(new Font("Monospaced", Font.BOLD, 14));

                    FontMetrics fm = g.getFontMetrics();
                    int charWidth = fm.charWidth(c);  // Get width of character
                    int charHeight = fm.getHeight();   // Get height of character

                    // Draw the character centered in the tile
                    int xPos = x * TILE_SIZE + (TILE_SIZE - charWidth) / 2;  // Center horizontally
                    int yPos = y * TILE_SIZE + (TILE_SIZE + charHeight) / 2 - 2; // Center vertically (slight adjustment)

                    g.drawString(String.valueOf(c), xPos, yPos);
                }
            }
        }
    }
}

