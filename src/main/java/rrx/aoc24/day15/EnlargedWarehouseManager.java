package rrx.aoc24.day15;

import rrx.ChristmasException;
import rrx.utils.Direction;
import rrx.utils.PrintUtil;
import rrx.visualizer.constant.Visualisable;

import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

public class EnlargedWarehouseManager implements Visualisable {

    private final char[][] grid;
    private final List<Direction> directions = new ArrayList<>();

    private final List<Wall> walls = new ArrayList<>();
    private final List<Box> boxes = new ArrayList<>();
    private final Robot robot;
    private JPanel gridPanel;

    public EnlargedWarehouseManager(List<String> input) {
        int split = input.indexOf("");
        this.grid = new char[split][input.getFirst().length() * 2];
        int[] robotcoords = new int[0];

        for (int i = 0; i < input.size(); i++) {
            char[] charArray = input.get(i).toCharArray();
            int newColCount = 0;
            for (char c : charArray) {
                switch (c) {
                    case '#' -> {
                        walls.add(new Wall(newColCount, i));
                        walls.add(new Wall(newColCount + 1, i));
                    }
                    case 'O' -> boxes.add(new Box(newColCount, i, newColCount + 1, i));
                    case '@' -> robotcoords = new int[]{newColCount, i};
                    case '>' -> directions.add(Direction.RIGHT);
                    case '<' -> directions.add(Direction.LEFT);
                    case 'v' -> directions.add(Direction.DOWN);
                    case '^' -> directions.add(Direction.UP);
                }
                newColCount += 2;
            }
        }
        robot = new Robot('@', robotcoords);
        updateGrid();
    }

    public long moveRobot() {
        for (var dir : directions) {
            robot.move(dir, boxes, walls);
            updateGrid();
        }
        return sumBoxGPSCoords();
    }

    private long sumBoxGPSCoords() {
        return boxes.stream()
                .mapToLong(b -> b.y1 * 100L + b.x1)
                .sum();
    }

    private void updateGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int x = j;
                int y = i;
                char c = '.';
                if (walls.stream().anyMatch(w -> w.collides(x, y))) {
                    c = '#';
                } else if (boxes.stream().anyMatch(w -> w.isLeftCollide(x, y))) {
                    c = '[';
                } else if (boxes.stream().anyMatch(w -> w.isRightCollide(x, y))) {
                    c = ']';
                } else if (robot.coords[0] == x && robot.coords[1] == y) {
                    c = '@';
                }
                grid[i][j] = c;
            }
        }
        if (gridPanel != null) {
            repaint(5);
        } else {
            PrintUtil.gridZoom(grid, robot.coords[0], robot.coords[1]);
        }
    }

    @Override
    public void setGridPanel(JPanel gridPanel) {
        this.gridPanel = gridPanel;
    }

    @Override
    public JPanel getGridPanel() {
        return gridPanel;
    }

    @Override
    public void executeTask() {
        moveRobot();
    }

    @Override
    public char[][] getGrid() {
        return grid;
    }

    record Robot(char c, int[] coords) {
        public void move(Direction dir, List<Box> boxes, List<Wall> walls) {
            int nextX = coords[0];
            int nextY = coords[1];

            try {
                while (true) {
                    nextX += dir.x;
                    nextY += dir.y;
                    final int finalX = nextX;
                    final int finalY = nextY;
                    if (walls.stream().anyMatch(w -> w.collides(finalX, finalY))) return; // hit a wall
                    boolean isEmptySpace = true;
                    for (Box box : boxes) {
                        if (box.collides(nextX, nextY)) {
                            box.affect(dir, boxes);
                            isEmptySpace = false;
                        }
                    }
                    if (isEmptySpace) break; // get moving
                }

                boxes.stream()
                        .filter(b -> b.isAffected)
                        .forEach(b -> b.move(dir));
                coords[0] += dir.x;
                coords[1] += dir.y;
            } catch (ChristmasException e) {
                System.out.println(e.getMessage());
            }

            boxes.forEach(b -> b.isAffected = false);
        }
    }

    record Wall(int x, int y) {
        public boolean collides(int x, int y) {
            return this.x == x && this.y == y;
        }
    }

    class Box {
        public int x1, y1, x2, y2;
        public boolean isAffected;

        public Box(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }

        public boolean collides(int x, int y) {
            return isLeftCollide(x, y) || isRightCollide(x, y);
        }

        private boolean isRightCollide(int x, int y) {
            return this.x2 == x && this.y2 == y;
        }

        private boolean isLeftCollide(int x, int y) {
            return this.x1 == x && this.y1 == y;
        }

        public void affect(Direction dir, List<Box> boxes) {
            if (walls.stream().anyMatch(w -> w.collides(x1 + dir.x, y1 + dir.y) || w.collides(x2 + dir.x, y2 + dir.y))) {
                throw new ChristmasException("We hit a wall!! Abort! ABORT!!!");
            }
            isAffected = true;
            boxes.stream()
                    .filter(b -> !b.isAffected && (b.collides(x1 + dir.x, y1 + dir.y) || b.collides(x2 + dir.x, y2 + dir.y)))
                    .forEach(b -> b.affect(dir, boxes));
        }

        public void move(Direction dir) {
            x1 += dir.x;
            x2 += dir.x;
            y1 += dir.y;
            y2 += dir.y;
        }
    }
}
