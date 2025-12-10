package rrx.visualizer;

import rrx.aoc24.day14.RAT;
import rrx.aoc24.day15.EnlargedWarehouseManager;
import rrx.aoc24.day16.MazeBolter;
import rrx.aoc24.day6.StealthProcessor;
import rrx.aoc25.day4.PrintingDepartment;
import rrx.aoc25.day7.TeleportationDevice;
import rrx.utils.FileUtil;
import rrx.visualizer.constant.VisualisableImpl;
import rrx.visualizer.constant.GridBuilder;

import javax.swing.SwingUtilities;

/**
 * 📺 Will easily visualizes any char[][] 2d array 📺
 */
public class GridUI {

    static void main() {
        SwingUtilities.invokeLater(() -> {
            VisualisableImpl visualisable = AOC2025.day7();
            GridBuilder gridBuilder = new GridBuilder(visualisable);
            gridBuilder.start(visualisable);
        });
    }

    private static final class AOC2025 {
        private static VisualisableImpl day7() {
            return new TeleportationDevice(FileUtil.readToGrid("25/d07p1"));
        }

        private static VisualisableImpl day4() {
            return new PrintingDepartment(FileUtil.readToGrid("25/d04p1"));
        }
    }

    private static final class AOC2024 {

        private static VisualisableImpl day16() {
            return new MazeBolter(FileUtil.readToGrid("24/d16p1"));
        }

        private static VisualisableImpl day15() {
            return new EnlargedWarehouseManager(FileUtil.readFile("24/d15p1"));
        }

        private static VisualisableImpl day14() {
            RAT rat = new RAT();
            rat.init(FileUtil.readFile("24/d14p1"), 103, 101);
            return rat;
        }

        private static VisualisableImpl day6() {
            return new StealthProcessor(FileUtil.readToGrid("24/d06p1"));
        }
    }
}
