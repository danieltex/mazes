package com.github.danieltex.samples;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.danieltex.algorithms.Wilsons;
import com.github.danieltex.grid.Cell;
import com.github.danieltex.grid.ColoredGrid;
import com.github.danieltex.grid.Distances;

public class WilsonsSample {
    public static void main(String[] args) {
        final int rows = 20;
        final int columns = 20;
        ColoredGrid grid = new ColoredGrid(rows, columns);
        Wilsons.on(grid);

        Cell start = grid.cellAt(rows/2, columns/2);
        Distances d = start.distances();
        grid.setDistances(d);
        final String pathname = "wilsons.png";
        try {
            ImageIO.write(grid.toPng(30), "png", new File(pathname));
            System.out.println("Saved to " + pathname);
        } catch (IOException e) {
            System.out.println("Failed to save to " + pathname);
            e.printStackTrace();
        }
    }
}