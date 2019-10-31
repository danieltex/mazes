package com.github.danieltex.grid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cell {
    final int row;
    final int column;

    Cell north;
    Cell south;
    Cell west;
    Cell east;

    final Set<Cell> links = new HashSet<>();

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Cell north() { return north; }
    public Cell south() { return south; }
    public Cell west()  { return west;  }
    public Cell east()  { return east;  }

    public Cell link(Cell cell) {
        return link(cell, true);
    }

    public Cell link(Cell cell, boolean bidirectional) {
        links.add(cell);
        if (bidirectional) {
            cell.link(this, false);
        }
        return this;
    }

    public Cell unlink(Cell cell) {
        return unlink(cell, true);
    }

    public Cell unlink(Cell cell, boolean bidirectional) {
        links.remove(cell);
        if (bidirectional) {
            cell.unlink(this, false);
        }
        return this;
    }

    public Set<Cell> links() {
        return Collections.unmodifiableSet(links);
    }

    public boolean isLinked(Cell cell) {
        return links.contains(cell);
    }

    public List<Cell> neighbors() {
        List<Cell> neighbors = new ArrayList<>();
        if (north != null) neighbors.add(north);
        if (south != null) neighbors.add(south);
        if (east  != null) neighbors.add(east);
        if (west  != null) neighbors.add(west);
        return neighbors;
    }


    /**
     *  Dijkstra's algorithm
     * @return the distances to this cell
     */
    public Distances distances() {
        Distances distances = new Distances(this);

        List<Cell> frontier = new ArrayList<>();
        frontier.add(this);

        while (!frontier.isEmpty()) {
            List<Cell> newFrontier = new ArrayList<>();
            for (Cell cell : frontier) {
                for (Cell neighbor : cell.links()) {
                    if (distances.get(neighbor) != null) continue;
                    int neighborDistance = distances.get(cell) + 1;
                    distances.put(neighbor, neighborDistance);
                    newFrontier.add(neighbor);
                }
            }
            frontier = newFrontier;
        }

        return distances;
    }
}

