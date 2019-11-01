package com.github.danieltex.grid;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

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

    public Distances pathTo(Cell goal) {
        Cell current = goal;
        Distances breadcrumbs = new Distances(root);
        breadcrumbs.put(current, cells.get(current));

        while (current != root) {
            for (Cell neighbor : current.links()) {
                if (cells.get(neighbor) < cells.get(current)) {
                    breadcrumbs.put(neighbor, cells.get(neighbor));
                    current = neighbor;
                    break;
                }
            }
        }
        return breadcrumbs;
    }

    public Cell max() {
        int maxDistance = -1;
        Cell maxCell = null;
        for (Entry<Cell, Integer> pair : cells.entrySet()) {
            if (pair.getValue() > maxDistance) {
                maxDistance = pair.getValue();
                maxCell = pair.getKey();
            }
        }
        return maxCell;
    }
}