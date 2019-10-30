package com.github.danieltex.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.danieltex.grid.Cell;
import com.github.danieltex.grid.Grid;

public class SideWinder {
    private static Random rand;

    public static Grid on(Grid grid) {
        rand = new Random();
        for (List<Cell> row : grid.rows()) {            
            List<Cell> run = new ArrayList<>();
            for (Cell cell : row) {
                run.add(cell);
                boolean atNorthBoundary = cell.north() == null;
                boolean atEastBoundary = cell.east() == null;
                boolean shouldCloseOut = atEastBoundary || (!atNorthBoundary && rand.nextInt(2) == 0);

                if (shouldCloseOut) {
                    Cell member = rand(run);
                    if (!atNorthBoundary) member.link(member.north());
                    run.clear();
                } else {
                    cell.link(cell.east());
                }
            }
        }
        return grid;
    }

    private static Cell rand(List<Cell> cells) {
        int index = rand.nextInt(cells.size());
        return cells.get(index);
    }
}