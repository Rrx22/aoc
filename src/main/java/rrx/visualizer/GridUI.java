package rrx.visualizer;

import rrx.aoc24.day14.RAT;
import rrx.aoc24.day15.EnlargedWarehouseManager;
import rrx.aoc24.day16.MazeBolter;
import rrx.aoc24.day6.StealthProcessor;
import rrx.utils.FileUtil;
import rrx.visualizer.constant.GridBuilder;
import rrx.visualizer.constant.Visualisable;

import javax.swing.SwingUtilities;

/**
 * 📺 Will easily visualizes any char[][] 2d array 📺
 */
public class GridUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Visualisable visualisable = AOC2024.day16();
            GridBuilder gridBuilder = new GridBuilder(visualisable.getGrid());
            gridBuilder.start(visualisable);
        });
    }

    private static final class AOC2024 {

        private static Visualisable day16() {
            return new MazeBolter(FileUtil.readToGrid("24/d16p1"));
        }

        private static Visualisable day15() {
            return new EnlargedWarehouseManager(FileUtil.readFile("24/d15p1"));
        }

        private static Visualisable day14() {
            RAT rat = new RAT();
            rat.init(FileUtil.readFile("24/d14p1"), 103, 101);
            return rat;
        }

        private static Visualisable day6() {
            return new StealthProcessor(FileUtil.readToGrid("24/d06p1"));
        }
    }
}
