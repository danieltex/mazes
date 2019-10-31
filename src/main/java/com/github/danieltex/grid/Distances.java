package com.github.danieltex.grid;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Distances {
    private final Cell root;
    private final Map<Cell, Integer> cells = new HashMap<>();

    public Distances(Cell root) {
        this.root = root;
        put(root, 0);
    }

    public Integer get(Cell cell) {
        return cells.get(cell);
    }

    public void put(Cell cell, int distance) {
        cells.put(cell, distance);
    }

    public Set<Cell> cells() {
        return cells.keySet();
    }
}