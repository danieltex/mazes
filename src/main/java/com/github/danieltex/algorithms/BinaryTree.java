package com.github.danieltex.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.danieltex.grid.Cell;
import com.github.danieltex.grid.Grid;

class BinaryTree implements MazeCarver {
    private Random random = new Random();

    public Grid on(Grid grid) {
        for (Cell cell : grid) {
            List<Cell> neighbors = neightbors(cell);
            if (neighbors.isEmpty()) continue;
            Cell neighbor = sample(neighbors);
            cell.link(neighbor);
        }
        return grid;
    }

    private List<Cell> neightbors(Cell cell) {
        List<Cell> neighbors = new ArrayList<>();
        if (cell.north() != null) neighbors.add(cell.north());
        if (cell.east() != null)  neighbors.add(cell.east());
        return neighbors;
    }

    private Cell sample(List<Cell> cells) {
        int index = random.nextInt(cells.size());
        return cells.get(index);
    }
}
