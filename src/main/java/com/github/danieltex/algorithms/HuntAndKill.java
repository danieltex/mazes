package com.github.danieltex.algorithms;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Random;

import com.github.danieltex.grid.Cell;
import com.github.danieltex.grid.Grid;

public class HuntAndKill {
    private static Random rand;
    public static Grid on(Grid grid) {
        rand = new Random();
        Cell current = grid.randomCell();

        while (current != null) {
            final List<Cell> unvisitedNeighbors = current.neighbors().stream()
                .filter(cell -> cell.links().isEmpty())
                .collect(toList());
            if (!unvisitedNeighbors.isEmpty()) {
                final Cell neighbor = sample(unvisitedNeighbors);
                current.link(neighbor);
                current = neighbor;
            } else {
                current = null;

                // hunt for unvisited cell with visited neighbors
                for (Cell cell : grid.allCells()) {
                    final List<Cell> visitedNeighbors = cell.neighbors().stream()
                        .filter(neighbor -> !neighbor.links().isEmpty())
                        .collect(toList());
                    if (cell.links().isEmpty() && !visitedNeighbors.isEmpty()) {
                        current = cell;
                        final Cell neighbor = sample(visitedNeighbors);
                        current.link(neighbor);
                        break;
                    }                    
                }
            }
        }

        return grid;
    }

    private static Cell sample(List<Cell> cells) {
        int index = rand.nextInt(cells.size());
        return cells.get(index);
    }
}