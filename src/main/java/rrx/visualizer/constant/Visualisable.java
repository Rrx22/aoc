package rrx.visualizer.constant;

import java.awt.Color;

interface Visualisable {

    // 🎅The grid being visualized🎅
    char[][] getGrid();

    // 🔥To kick off the actual task of the AOC challenge🔥
    void executeTask();

    // 🔤Override to determine your own character translations🔠
    default char paintCharacter(char c) {
        return switch (c) {
            case '.' -> ' ';
            case '#', '@' -> '■';
            case '1', '2', '3', '4', '5', '6', '7', '8', '9' -> '*';
            case '+' -> '✚';
            case '-' -> '─';
            case '|' -> '│';
            case 'O', '0' -> '⚪';
            default -> c;
        };
    }

    // 🎨Override to determine your own colors for specific characters🎨
    default Color paintColor(char c) {
        return switch (c) {
            case '[', ']', ' ', '.', '+' -> Color.LIGHT_GRAY;
            case 'v', '>', '<', '^' -> Color.WHITE;
            case '#', '@' -> Color.DARK_GRAY;
            default -> Color.ORANGE;
        };
    }
}
