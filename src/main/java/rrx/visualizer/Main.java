package rrx.visualizer;

import rrx.aoc24.day14.RAT;
import rrx.aoc24.day15.EnlargedWarehouseManager;
import rrx.aoc24.day6.StealthProcessor;
import rrx.utils.FileUtil;
import rrx.visualizer.constant.GridBuilder;
import rrx.visualizer.constant.Visualisable;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Visualisable visualisable = AOC2024.day6();
            GridBuilder gridBuilder = new GridBuilder(visualisable.getGrid());
            gridBuilder.start(visualisable);
        });
    }

    /**
     * 📺 Will easily visualizes any char[][] 2d array 📺
     * @param visualisable Interface with some functions to make it possible to visualize your grid.
     * @param worker Simply pass the class of your worker (i.e. DayXXWorker.class)
     */
    record DayInfo(Visualisable visualisable, Class<? extends SwingWorker<Void, Void>> worker) {
    }

    private static final class AOC2024 {

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
