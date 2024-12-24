package rrx.aoc24.day16;

import rrx.ChristmasException;
import rrx.utils.Direction;
import rrx.utils.GridUtil;
import rrx.visualizer.constant.Visualisable;

import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import static rrx.utils.Direction.DOWN;
import static rrx.utils.Direction.LEFT;
import static rrx.utils.Direction.RIGHT;
import static rrx.utils.Direction.UP;

public class MazeBolter implements Visualisable {

    private final char[][] maze;
    private final char[][] printMaze;
    private JPanel gridPanel;

    public MazeBolter(char[][] maze) {
        this.maze = maze;
        this.printMaze = GridUtil.cloneGrid(maze);
    }

    public long solveMaze() {
        int startX = 0, startY = 0, endX = 0, endY = 0;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 'S') {
                    startX = i;
                    startY = j;
                } else if (maze[i][j] == 'E') {
                    endX = i;
                    endY = j;
                }
            }
        }

        Queue<State> queue = new PriorityQueue<>(); // priority queue using the comparible interface to always pop the State with the lowest cost first
        queue.add(new State(startX, startY, RIGHT, 0)); // starts facing east so no cost in this initial move
        queue.add(new State(startX, startY, UP, 1000));
        queue.add(new State(startX, startY, DOWN, 1000));
        queue.add(new State(startX, startY, LEFT, 1000));

        boolean[][][] visited = new boolean[maze.length][maze[0].length][4];
        Map<State, List<State>> predecessors = new HashMap<>();
        List<State> endStates = new ArrayList<>();

        while (!queue.isEmpty()) {
            updateGrid(visited, startX, startY);
            State curr = queue.poll();
            if (visited[curr.x][curr.y][curr.dirKey()]) continue;
            visited[curr.x][curr.y][curr.dirKey()] = true;

            if (curr.x == endX && curr.y == endY) { // the end
                endStates.add(curr);
            }

            // move it
            int newX = curr.x + curr.direction.x;
            int newY = curr.y + curr.direction.y;
            if (isValidMove(newX, newY) && !visited[newX][newY][curr.dirKey()]) {
                State next = new State(newX, newY, curr.direction(), curr.cost() + 1);
                queue.add(next);
                predecessors.computeIfAbsent(next, _ -> new ArrayList<>()).add(curr);
            }

            // rotate 90 degrees
            Direction clockwise = curr.turnClockWise(false);
            if (!visited[curr.x][curr.y()][clockwise.ordinal()]) {
                State cwRotated = new State(curr.x(), curr.y(), clockwise, curr.cost() + 1000);
                queue.add(cwRotated);
                predecessors.computeIfAbsent(cwRotated, _ -> new ArrayList<>()).add(curr);

            }
            Direction counterClockwise = curr.turnClockWise(true);
            if (!visited[curr.x][curr.y()][counterClockwise.ordinal()]) {
                State ccwRotated = new State(curr.x(), curr.y(), counterClockwise, curr.cost() + 1000);
                queue.add(ccwRotated);
                predecessors.computeIfAbsent(ccwRotated, _ -> new ArrayList<>()).add(curr);
            }
        }

        // backtrack to find the optimal path
        List<List<State>> allPaths = new ArrayList<>();
        for (State endState : endStates) {
            List<State> path = new ArrayList<>();
            reconstructPaths(predecessors, endState, path, allPaths);
        }

        // filter out paths that seemed right but were too long after all
        Map<Long, List<List<State>>> map = new HashMap<>();
        for (var path : allPaths) {
            var max = path.stream()
                    .mapToLong(State::cost)
                    .max().getAsLong();
            map.computeIfAbsent(max, v -> new ArrayList<>()).add(path);
        }
        long lowestKey = map.keySet().stream().mapToLong(Long::longValue).min().getAsLong();
        allPaths = map.get(lowestKey);

        resetGrid();

        Set<String> visitedTiles = new HashSet<>();
        long countNodesWhichAreOnOneOrMoreOptimalPaths = 0;
        long charCounter = 0;
        for (List<State> allPath : allPaths) {
            char c = (char) ('0' + charCounter);
            boolean charUsed = false;
            for (State state : allPath) {
                String key = state.x + "," + state.y;
                if (!visitedTiles.add(key)) continue;
                countNodesWhichAreOnOneOrMoreOptimalPaths++;
                // print logic
                printMaze[state.x][state.y] = c;
                repaint(25);
                charUsed = true;
            }
            if (charUsed) charCounter++;
        }

        return countNodesWhichAreOnOneOrMoreOptimalPaths;
//        return allPaths.stream()
//                .flatMap(Collection::stream)
//                .collect(Collectors.toSet())
//                .size();
    }

    private void reconstructPaths(Map<State, List<State>> predecessors, State current, List<State> path, List<List<State>> allPaths) {
        path.add(current);
        if (!predecessors.containsKey(current)) {
            List<State> completePath = new ArrayList<>(path);
            Collections.reverse(completePath);
            allPaths.add(completePath);
            return; // reached the end of the path
        }
        for (State predecessor : predecessors.get(current)) {
            reconstructPaths(predecessors, predecessor, path, allPaths);
        }
        path.removeLast(); // backtrack
    }

    private void updateGrid(boolean[][][] visited, int startX, int startY) {
        if (gridPanel == null) {
            return;
        }
        System.arraycopy(maze, 0, printMaze, 0, maze.length);
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (i == startX && j == startY) continue;
                for (int k = 0; k < 4; k++) {
                    if (visited[i][j][k]) {
                        printMaze[i][j] = switch (k) {
                            case 0 -> '>';
                            case 1 -> 'v';
                            case 2 -> '<';
                            case 3 -> '^';
                            default -> throw new ChristmasException("Nope");
                        };
                    }
                }
            }
        }
        repaint(0);
    }

    private void resetGrid() {
        repaint(250);
        for (int i = 0; i < printMaze.length; i++) {
            for (int j = 0; j < printMaze[0].length; j++) {
                char c = printMaze[i][j];
                if (c == '<' || c == '>' || c == '^' || c == 'v') printMaze[i][j] = '.';
            }
        }
    }

    private boolean isValidMove(int x, int y) {
        return GridUtil.isWithinBounds(x, y, maze)
                && maze[x][y] != '#';
    }

    @Override
    public char[][] getGrid() {
        return printMaze;
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
    public void executTask() {
        solveMaze();
    }

    record State(int x, int y, Direction direction, int cost) implements Comparable<State> {
        public int dirKey() {
            return direction.ordinal();
        }

        public Direction turnClockWise(boolean counter) {
            return switch (direction) {
                case UP -> counter ? LEFT : RIGHT;
                case RIGHT -> counter ? UP : DOWN;
                case DOWN -> counter ? RIGHT : LEFT;
                case LEFT -> counter ? UP : DOWN;
                default -> throw new ChristmasException("Unsupported value: " + direction);
            };
        }

        @Override
        public int compareTo(State other) {
            return Integer.compare(this.cost, other.cost);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof State other)) return false;
            return x == other.x && y == other.y && cost == other.cost && direction == other.direction;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, direction, cost);
        }
    }
}
