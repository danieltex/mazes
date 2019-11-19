package com.github.danieltex.algorithms;

import java.util.Random;

import com.github.danieltex.grid.Cell;
import com.github.danieltex.grid.Grid;

class AldousBroder implements MazeCarver {
    private Random rand = new Random();

    public Grid on(Grid grid) {
        int unvisited = grid.size() - 1;
        Cell cell = grid.randomCell();
        while (unvisited > 0) {
            int neighborSize = cell.neighbors().size();
            int index = rand.nextInt(neighborSize);
            Cell neighbor = cell.neighbors().get(index);
            if (neighbor.links().isEmpty()) {
                cell.link(neighbor);
                unvisited--;
            }
            cell = neighbor;
        }
        return grid;
    }
}