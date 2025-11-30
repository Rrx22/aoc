#!/usr/bin/env bash
set -euo pipefail

# Create resource files
for d in $(seq -w 1 25); do
  dir="src/main/resources/25"
  mkdir -p "$dir"
  echo "" > "$dir/d${d}p1"
done

# Create java packages and class files
for d in $(seq 1 25); do
  pkgdir="src/main/java/rrx/aoc25/day${d}"
  mkdir -p "$pkgdir"

  cat > "$pkgdir/Main.java" << EOF
package rrx.aoc25.day${d};

import rrx.utils.FileUtil;
import rrx.ChristmasAssert;

class Main {
    public static void main(String[] args) {
        var x = FileUtil.readToGrid("25/d$(printf "%02d" ${d})p1");

        Something y = new Something(x);
        ChristmasAssert.test(y.doSomething(), 0L);

        y.changeItUp();
        ChristmasAssert.test(y.doSomething(), 0L);
    }
}
EOF

  cat > "$pkgdir/Something.java" << EOF
package rrx.aoc25.day${d};

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
EOF

done

echo "Done generating Advent of Code 2025 scaffolding."
