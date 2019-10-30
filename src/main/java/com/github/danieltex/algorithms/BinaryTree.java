package com.github.danieltex.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.danieltex.grid.Cell;
import com.github.danieltex.grid.Grid;

public class BinaryTree {
    private static Random rand;

    public static Grid on(Grid grid) {
        rand = new Random();
        for (Cell cell : grid) {
            List<Cell> neighbors = neightbors(cell);
            if (neighbors.isEmpty()) continue;
            Cell neighbor = rand(neighbors);
            cell.link(neighbor);
        }
        return grid;
    }

    private static List<Cell> neightbors(Cell cell) {
        List<Cell> neighbors = new ArrayList<>();
        if (cell.north() != null) neighbors.add(cell.north());
        if (cell.east() != null)  neighbors.add(cell.east());
        return neighbors;
    }

    private static Cell rand(List<Cell> cells) {
        int index = rand.nextInt(cells.size());
        return cells.get(index);
    }
}
