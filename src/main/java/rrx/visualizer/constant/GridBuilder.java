package rrx.visualizer.constant;

import rrx.ChristmasException;
import rrx.visualizer.PanelFactory;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;

public class GridBuilder extends JFrame {

    public static final int TILE_SIZE = 7; // Adjust size as needed
    public static final int BORDER = 20;
    public final int maxX;
    public final int maxY;

    public final char[][] grid;
    private JPanel gridPanel;

    public GridBuilder(char[][] grid) {
        super("Grid Builder");

        this.grid = grid;
        this.maxY = grid.length;
        this.maxX = grid[0].length;

        setSize(BORDER * 2 + TILE_SIZE * (maxX + 5), BORDER * 2 + TILE_SIZE * (maxY + 10));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        createGui();
        setVisible(true);
    }

    public void createGui() {
        var factory = new PanelFactory(this);
        setLayout(new BorderLayout());
        gridPanel = factory.constructGrid();
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBorder(new LineBorder(Color.BLACK, BORDER));
        wrapperPanel.add(gridPanel, BorderLayout.CENTER);

        add(wrapperPanel, BorderLayout.CENTER);

        validate();
        repaint();
    }

    public void start(VisualisableImpl visualisable) {
        try {
            visualisable.setGridPanel(gridPanel);
            var worker = new Worker(visualisable);
            worker.execute();
        } catch (Exception e) {
            throw new ChristmasException(e.getMessage());
        }
    }
}
