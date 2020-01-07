package com.github.danieltex.grid;

import java.util.Arrays;
import java.util.Random;

public class Mask {
    private final int rows;
    private final int columns;
    private final boolean[][] bits;
    private final Random rand = new Random();

    public Mask(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        bits = new boolean[rows][columns];
        for (boolean[] bs : bits) {
            Arrays.fill(bs, true);
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public boolean get(int row, int column) {
        if (row < 0 || row >= rows || column < 0 || column >= columns) {
            return false;
        }
        return bits[row][column];
    }

    public boolean set(int row, int column, boolean isOn) {
        return bits[row][column] = isOn;
    }

    public int count() {
        int result = 0;
        for (boolean[] row : bits) {
            for (boolean cell : row) {
                if (cell) result++;
            }
        }
        return result;
    }

    public int[] randomLocation() {
        while (true) {
            int row = rand.nextInt(rows);
            int col = rand.nextInt(columns);
            if (get(row, col)) 
                return new int[] {row, col};
        }
    }
}