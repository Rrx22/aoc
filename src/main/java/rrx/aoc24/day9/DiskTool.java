package rrx.aoc24.day9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DiskTool {

    private DiskTool() {

    }

    private static boolean changedItUp;
    private static final Map<Integer, Integer> ID_SIZE = new HashMap<>();

    public static long doSomething(String input) {
        List<Integer> disk = mapDisk(input);
        return changedItUp
                ? doItDifferently(disk)
                : doIt(disk);
    }

    private static List<Integer> mapDisk(String input) {
        List<Integer> disk = new ArrayList<>();
        int ID = 0;

        for (int i = 0; i < input.length(); i++) {
            int repeat = Character.getNumericValue(input.charAt(i));
            if (i % 2 == 0) {
                if (changedItUp) ID_SIZE.put(ID, repeat);
                for (int j = 0; j < repeat; j++) {
                    disk.add(ID);
                }
                ID++;
            } else {
                if (repeat > 0) {
                    for (int j = 0; j < repeat; j++) {
                        disk.add(-1);
                    }
                }
            }
        }
        return disk;
    }

    private static long doIt(List<Integer> disk) {
        List<Integer> defragmentedDisk = defragment(disk);
        return checksum(defragmentedDisk);
    }

    private static long doItDifferently(List<Integer> disk) {
        List<Integer> defragmentedFiles = sortFiles(disk);
        return checksum(defragmentedFiles);
    }

    private static List<Integer> defragment(List<Integer> disk) {
        List<Integer> defragmentedDisk = new ArrayList<>(disk);
        for (int i = defragmentedDisk.size()-1; i > 0; i--) {
            int freeIdx = defragmentedDisk.indexOf(-1);
            if (defragmentedDisk.get(i) != -1 && freeIdx < i) {
                defragmentedDisk.set(freeIdx, defragmentedDisk.get(i));
                defragmentedDisk.set(i, -1);
            }
        }
        return defragmentedDisk;
    }

    private static long checksum(List<Integer> disk) {
        long checksum = 0L;
        for (int i = 0; i < disk.size(); i++) {
            if (disk.get(i) == -1) continue;
            checksum += (i * disk.get(i));
        }
        return checksum;
    }

    private static List<Integer> sortFiles(List<Integer> disk) {
        List<Integer> sortedFiles = new ArrayList<>(disk);
        for (int i = sortedFiles.size()-1; i > 0; i--) {
            int id = sortedFiles.get(i);
            if (id != -1) {
                int idLen = ID_SIZE.get(id);
                int count = 0;

                for (int j = sortedFiles.indexOf(-1); j < i; j++) {
                    count++;
                    if (sortedFiles.get(j) != -1) {
                        count = 0;
                    }

                    if (count == idLen) {
                        for (int k = 0; k < idLen; k++) {
                            sortedFiles.set(j-k, id);
                            sortedFiles.set(i-k, -1);
                        }
                        i = i - idLen + 1;
                        break;
                    }
                }
            }

        }
        return sortedFiles;
    }

    public static void changeItUp() {
        changedItUp = true;
    }
}
