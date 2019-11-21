package com.github.danieltex.grid;

import java.awt.Color;
import java.util.Random;

public class DeadEndColoredGrid extends Grid {
    private float color = new Random().nextFloat();

    public DeadEndColoredGrid(int rows, int columns) {
        super(rows, columns);
    }

    @Override
    protected Color backgroundColorFor(Cell cell) {
        final int links = cell.links().size();
        final int maximum = 4;
        float intensity = (float) links / (maximum);
        return Color.getHSBColor(color, intensity, intensity);
    }
}