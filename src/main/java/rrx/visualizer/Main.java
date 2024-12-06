package rrx.visualizer;

import rrx.utils.FileUtil;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            char[][] grid = FileUtil.readToGrid("24/d06p1");
            GridBuilder gridBuilder = new GridBuilder(grid);
            gridBuilder.createGui();
            gridBuilder.setVisible(true);
            gridBuilder.startPart1();

            gridBuilder.grid = FileUtil.readToGrid("24/d06p1");
            gridBuilder.startPart2();
        });



    }
}
