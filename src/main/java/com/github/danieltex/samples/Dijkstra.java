package com.github.danieltex.samples;

import com.github.danieltex.algorithms.SideWinder;
import com.github.danieltex.grid.Cell;
import com.github.danieltex.grid.Distances;
import com.github.danieltex.grid.DistanceGrid;

public class Dijkstra {
    public static void main( String[] args ) {
        int rows = 6;
        int columns = 6;
        DistanceGrid grid = new DistanceGrid(rows, columns);
        SideWinder.on(grid);

        // all distance from northwestest cell
        Cell start = grid.cellAt(0, 0);
        Distances distances = start.distances();
        grid.setDistances(distances);
        System.out.println(grid);

        // print dijkstra from northwestest to southwestest
        Cell goal = grid.cellAt(rows - 1, 0);
        Distances goalDistances = distances.pathTo(goal);
        grid.setDistances(goalDistances);
        System.out.println(String.format("Path to (%d, %d)", goal.getRow(), goal.getColumn()));
        System.out.println(grid);
    }
}
