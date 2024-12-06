package rrx.visualizer;

import rrx.aoc24.day6.StealthProcessor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;

public class GridBuilder extends JFrame {

    public static final int TILE_SIZE = 7; // Adjust size as needed
    public static final int BORDER = 20;
    public final int maxX;
    public final int maxY;

    public char[][] grid;
    private JPanel gridPanel;

    public GridBuilder(char[][] grid) {
        super("Grid Builder");

        this.grid = grid;
        this.maxY = grid.length;
        this.maxX = grid[0].length;

        setSize(BORDER * 2 + TILE_SIZE * maxX, BORDER * 2 + TILE_SIZE * maxY);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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

    public void startPart1() {
        StealthProcessor stealthProcessor = new StealthProcessor(grid, gridPanel);
        Worker worker = new Worker(stealthProcessor, this, 1);
        worker.execute(); // start background task
    }
    public void startPart2() {
        StealthProcessor stealthProcessor = new StealthProcessor(grid, gridPanel);
        Worker worker = new Worker(stealthProcessor, this, 2);
        worker.execute(); // start background task
    }
}
