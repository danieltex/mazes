package com.github.danieltex.grid;

public class DistanceGrid extends Grid {
    private Distances distances = null;

    public DistanceGrid(int rows, int columns) {
        super(rows, columns);
    }

    /**
     * @param distances the distances to set
     */
    public void setDistances(Distances distances) {
        this.distances = distances;
    }

    @Override
    protected String contentsOf(Cell cell) {
        if (distances != null && distances.get(cell) != null) {
            return Integer.toString(distances.get(cell), 36);
        }
        return super.contentsOf(cell);
    }
}