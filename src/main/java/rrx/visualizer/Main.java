package rrx.visualizer;

import rrx.aoc24.day14.RAT;
import rrx.aoc24.day15.EnlargedWarehouseManager;
import rrx.aoc24.day6.StealthProcessor;
import rrx.utils.FileUtil;
import rrx.visualizer.workers.aoc24.Day14Worker;
import rrx.visualizer.workers.aoc24.Day15Worker;
import rrx.visualizer.workers.aoc24.Day6Worker;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            var dayInfo = AOC2024.day15();
            GridBuilder gridBuilder = new GridBuilder(dayInfo.visualisable.getGrid());
            gridBuilder.start(dayInfo.visualisable, dayInfo.worker);
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

        private static DayInfo day15() {
            EnlargedWarehouseManager warehouseManager = new EnlargedWarehouseManager(FileUtil.readFile("24/d15p1"));
            return new DayInfo(warehouseManager, Day15Worker.class);
        }

        private static DayInfo day14() {
            RAT rat = new RAT();
            rat.init(FileUtil.readFile("24/d14p1"), 103, 101);
            return new DayInfo(rat, Day14Worker.class);
        }

        private static DayInfo day6() {
            StealthProcessor stealthProcessor = new StealthProcessor(FileUtil.readToGrid("24/d06p1"));
            return new DayInfo(stealthProcessor, Day6Worker.class);
        }
    }
}
