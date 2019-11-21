package com.github.danieltex.samples;

import static com.github.danieltex.algorithms.MazeCarvers.*;

import javax.imageio.ImageIO;

import com.github.danieltex.grid.DeadEndColoredGrid;
import com.github.danieltex.grid.Grid;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ColoringLinks {
    public static void main(String[] args) {
        final int rows = 50;
        final int columns = 50;
        final Grid grid = new DeadEndColoredGrid(rows, columns);
        HUNT_AND_KILL.on(grid);
        
        final BufferedImage img = grid.toPng();
        
        final String filename = "colorizedLinks.png";
        try {
            ImageIO.write(img, "png", new File(filename));
            System.out.println("Saved to " + filename);
        } catch(IOException ex) {
            System.out.println("Failed to save to " + filename);
            ex.printStackTrace();
        }
        
    }
}