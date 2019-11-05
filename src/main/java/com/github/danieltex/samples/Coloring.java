package com.github.danieltex.samples;

import javax.imageio.ImageIO;

import com.github.danieltex.algorithms.BinaryTree;
import com.github.danieltex.grid.Cell;
import com.github.danieltex.grid.ColoredGrid;
import com.github.danieltex.grid.Distances;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Coloring {
    public static void main(String[] args) {
        final int rows = 50;
        final int columns = 50;
        final ColoredGrid grid = new ColoredGrid(rows, columns);
        BinaryTree.on(grid);
        
        final Cell start = grid.cellAt(rows / 2, columns / 2);
        final Distances distances = start.distances();
        grid.setDistances(distances);

        final BufferedImage img = grid.toPng();
        
        final String filename = "colorized.png";
        try {
            ImageIO.write(img, "png", new File(filename));
            System.out.println("Saved to " + filename);
        } catch(IOException ex) {
            System.out.println("Failed to save to " + filename);
            ex.printStackTrace();
        }
        
    }
}