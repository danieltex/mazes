package com.github.danieltex.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.danieltex.grid.Cell;
import com.github.danieltex.grid.Grid;

class Wilsons implements MazeCarver {
    private Random rand = new Random();

    public Grid on(Grid grid) {
        List<Cell> unvisited = new ArrayList<>(grid.allCells());
        Cell first = sample(unvisited);
        unvisited.remove(first);

        while (!unvisited.isEmpty()) {
            Cell cell = sample(unvisited);
            List<Cell> path = new ArrayList<>();
            path.add(cell);

            while (unvisited.contains(cell)) {
                cell = sample(cell.neighbors());
                int position = path.indexOf(cell);
                if (position >= 0) {
                    path.subList(position + 1, path.size()).clear();
                } else {
                    path.add(cell);
                }
            }

            for (int i = 0; i < path.size() - 1; i++) {
                path.get(i).link(path.get(i+1));
                unvisited.remove(path.get(i));
            }
        }

        return grid;
    }

    private Cell sample(List<Cell> cells) {
        int index = rand.nextInt(cells.size());
        return cells.get(index);
    }
}