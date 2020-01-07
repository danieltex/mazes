package com.github.danieltex.samples;

import com.github.danieltex.grid.Grid;
import static com.github.danieltex.algorithms.MazeCarvers.BACKTRACKER;

public class KillingCells {
    public static void main(String[] args) {
        Grid grid = new Grid(5, 5);
        
        // orphan the cell in the northwest corner...
        grid.cellAt(0, 0).east().setWest(null);
        grid.cellAt(0, 0).south().setNorth(null);

        // and the one in the southeast corner
        grid.cellAt(4, 4).west().setEast(null);
        grid.cellAt(4, 4).north().setSouth(null);

        BACKTRACKER.on(grid);

        System.out.println(grid);
    }
}