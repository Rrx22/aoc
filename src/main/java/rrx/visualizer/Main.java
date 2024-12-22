package rrx.visualizer;

import rrx.aoc24.day14.RAT;
import rrx.aoc24.day15.EnlargedWarehouseManager;
import rrx.aoc24.day6.StealthProcessor;
import rrx.utils.FileUtil;
import rrx.visualizer.workers24.Day14Worker;
import rrx.visualizer.workers24.Day15Worker;
import rrx.visualizer.workers24.Day6Worker;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            var dayInfo = AOC2024.startY24D06();
            GridBuilder gridBuilder = new GridBuilder(dayInfo.visualisable.getGrid());
            gridBuilder.start(dayInfo.visualisable, dayInfo.worker);
        });
    }

    record DayInfo(Visualisable visualisable, Class<? extends SwingWorker<Void, Void>> worker) {
    }

    private static final class AOC2024 {

        private static DayInfo startY24D15() {
            EnlargedWarehouseManager warehouseManager = new EnlargedWarehouseManager(FileUtil.readFile("24/d15p1"));
            return new DayInfo(warehouseManager, Day15Worker.class);
        }

        private static DayInfo startY24D14() {
            RAT rat = new RAT();
            rat.init(FileUtil.readFile("24/d14p1"), 103, 101);
            return new DayInfo(rat, Day14Worker.class);
        }

        private static DayInfo startY24D06() {
            StealthProcessor stealthProcessor = new StealthProcessor(FileUtil.readToGrid("24/d06p1"));
            return new DayInfo(stealthProcessor, Day6Worker.class);
        }
    }
}
