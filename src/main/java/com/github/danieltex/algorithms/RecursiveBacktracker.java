package com.github.danieltex.algorithms;

import static java.util.stream.Collectors.toList;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Random;

import com.github.danieltex.grid.Cell;
import com.github.danieltex.grid.Grid;

class RecursiveBacktracker implements MazeCarver {
    private Random random = new Random();

    public Grid on(Grid grid) {
        final Deque<Cell> stack = new ArrayDeque<>();
        Cell start = grid.randomCell();
        stack.addFirst(start);

        while (!stack.isEmpty()) {
            Cell cell = stack.peekFirst();
            List<Cell> neighbors = cell.neighbors().stream()
                .filter(n -> n.links().isEmpty())
                .collect(toList());
            if (neighbors.isEmpty()) {
                stack.removeFirst();
            } else {
                Cell neighbor = sample(neighbors);
                cell.link(neighbor);
                stack.addFirst(neighbor);
            }
        }

        return grid;
    }

    private Cell sample(List<Cell> cells) {
        int index = random.nextInt(cells.size());
        return cells.get(index);
    }

}