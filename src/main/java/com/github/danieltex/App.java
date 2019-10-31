package com.github.danieltex;

import java.io.File;
import java.io.IOException;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import com.github.danieltex.algorithms.BinaryTree;
import com.github.danieltex.algorithms.SideWinder;
import com.github.danieltex.grid.Cell;
import com.github.danieltex.grid.DistanceGrid;
import com.github.danieltex.grid.Grid;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        String algorithm = args[0].toUpperCase();
        int rows = Integer.parseInt(args[1]);
        int cols = Integer.parseInt(args[2]);
        String filepath = args[3];

        DistanceGrid grid = new DistanceGrid(rows, cols);

        switch(algorithm) {
            case "SIDEWINDER":
                SideWinder.on(grid);
                break;
            case "BINARYTREE":
                BinaryTree.on(grid);
                break;
            default:
                System.out.println("Unknow option: " + args[0]);
                break;
        }

        Cell start = grid.cellAt(0, 0);
        grid.setDistances(start.distances());
        System.out.println(grid);

        BufferedImage img = grid.toPng(30);

        try {
            ImageIO.write(img, "png", new File(filepath));
        } catch (IOException e) {
            System.out.println("Failed to write file to " + filepath);
            e.printStackTrace();
        }
    }
}
