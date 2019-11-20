package com.github.danieltex.grid;

import java.awt.Color;
import java.util.Random;

public class ColoredGrid extends Grid {

    private Distances distances;
    private Cell farthest;
    private float color = new Random().nextFloat();
    public ColoredGrid(int rows, int columns) {
        super(rows, columns);
    }

    /**
     * @param distances the distances to set
     */
    public void setDistances(Distances distances) {
        this.distances = distances;
        this.farthest = distances.max();
    }

    @Override
    protected Color backgroundColorFor(Cell cell) {
        if (distances == null || distances.get(cell) == null) return Color.GRAY;
        Integer distance = distances.get(cell);
        int maximum = distances.get(farthest);
        float intensity = (float) (maximum - distance) / maximum;
        return Color.getHSBColor(color, intensity, intensity);
    }
}