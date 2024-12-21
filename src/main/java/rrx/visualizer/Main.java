package rrx.visualizer;

import rrx.aoc24.day14.RAT;
import rrx.utils.FileUtil;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {


        /*
            AOC 2024
         */
        // Day 14
        SwingUtilities.invokeLater(() -> {
            RAT rat = new RAT();
            rat.init(FileUtil.readFile("24/d14p1"), 103, 101);

            GridBuilder gridBuilder = new GridBuilder(rat.getGrid());
            gridBuilder.createGui();
            gridBuilder.setVisible(true);
            gridBuilder.startDay14Part2(rat);
        });


        // Day 6
//        SwingUtilities.invokeLater(() -> {
//            char[][] grid = FileUtil.readToGrid("24/d06p1");
//            GridBuilder gridBuilder = new GridBuilder(grid);
//            gridBuilder.createGui();
//            gridBuilder.setVisible(true);
//            gridBuilder.startDay6Part1();
//
//            gridBuilder.grid = FileUtil.readToGrid("24/d06p1");
//            gridBuilder.startDay6Part2();
//        });


    }
}
