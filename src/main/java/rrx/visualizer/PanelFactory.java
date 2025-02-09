package rrx.visualizer;

import rrx.visualizer.constant.GridBuilder;

import javax.swing.*;
import java.awt.*;

import static rrx.visualizer.constant.GridBuilder.TILE_SIZE;

public class PanelFactory {
    private final GridBuilder gb;

    public PanelFactory(GridBuilder gb) {
        this.gb = gb;
    }

    public JPanel constructGrid() {
        return new GridPanel(gb);
    }

    private static class GridPanel extends JPanel {
        private final GridBuilder gb;

        public GridPanel(GridBuilder gb) {
            this.gb = gb;
            setPreferredSize(new Dimension(gb.maxX * TILE_SIZE, gb.maxY * TILE_SIZE));
            setBackground(Color.BLACK);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            char[][] grid = gb.grid;

            for (int y = 0; y < gb.maxY; y++) {
                for (int x = 0; x < gb.maxX; x++) {
                    // Draw cell background
                    char c = switch (grid[y][x]) {
                        case '.' -> ' ';
                        case '#' -> '■';
                        case '1', '2', '3', '4', '5', '6', '7', '8', '9' -> '*';
                        case '+' -> '✚';
                        case '-' -> '─';
                        case '|' -> '│';
                        case 'O', '0' -> '⚪';
                        case '@' -> '⚙';
                        default -> grid[y][x];
                    };

                    Color color = switch (c) {
                        case '[', ']',' ', '.' -> Color.LIGHT_GRAY;
                        case '⚙' -> Color.RED;
                        case 'S', 'E' -> Color.GREEN;
                        case 'v', '^', '>', '<' -> Color.WHITE;
                        case '#', '■' -> Color.DARK_GRAY;
                        default -> Color.ORANGE;
                    };
                    // Draw character
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

