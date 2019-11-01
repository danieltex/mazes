package com.github.danieltex.grid;

import java.awt.Color;

public class ColoredGrid extends Grid {

    private Distances distances;
    private Cell farthest;

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
        int dark = Math.round(255 * intensity);
        int bright = 128 + Math.round(127 * intensity);
        return new Color(dark, bright, dark);        
    }
}