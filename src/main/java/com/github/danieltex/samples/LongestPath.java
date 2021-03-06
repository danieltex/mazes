package com.github.danieltex.samples;


import static com.github.danieltex.algorithms.MazeCarvers.BINARY_TREE;

import com.github.danieltex.grid.Cell;
import com.github.danieltex.grid.Distances;
import com.github.danieltex.grid.DistanceGrid;

public class LongestPath 
{
    public static void main( String[] args ) {
        int rows = 30;
        int columns = 30;
        DistanceGrid grid = new DistanceGrid(rows, columns);
        BINARY_TREE.on(grid);

        // all distance from northwestest cell
        Cell start = grid.cellAt(0, 0);
        Distances distances = start.distances();
        grid.setDistances(start.distances());
        

        // new start -> farthest from start
        Cell newStart = distances.max();
        Distances newDistances = newStart.distances();

        // goal -> absolute farthest
        Cell goal = newDistances.max();
        grid.setDistances(newDistances.pathTo(goal));

        System.out.println(grid);

        System.out.println("Dead ends: " + grid.deadEnds().size());
    }
}
