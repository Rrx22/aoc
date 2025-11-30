package rrx.aoc25.day17;

class Something {

    private final char[][] grid;
    private boolean changedItUp;

    public Something(char[][] grid) {
        this.grid = grid;
    }

    public long doSomething() {
        return changedItUp
                ? doItDifferently()
                : doIt();
    }

    private long doIt() {
        return 0L;
    }

    private long doItDifferently() {
        return 0L;
    }

    public void changeItUp() {
        changedItUp = true;
    }
}
