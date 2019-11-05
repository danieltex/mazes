package com.github.danieltex;

import java.io.File;
import java.io.IOException;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import com.github.danieltex.algorithms.BinaryTree;
import com.github.danieltex.algorithms.SideWinder;
import com.github.danieltex.grid.Cell;
import com.github.danieltex.grid.Distances;
import com.github.danieltex.grid.DistanceGrid;

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

        // all distance from northwestest cell
        Cell start = grid.cellAt(0, 0);
        Distances distances = start.distances();
        grid.setDistances(start.distances());
        System.out.println(grid);

        // print dijkstra from northwestest to southwestest
        Cell goal = grid.cellAt(rows - 1, 0);
        Distances goalDistances = distances.pathTo(goal);
        grid.setDistances(goalDistances);
        System.out.println(String.format("Path to (%d, %d)", rows-1, cols-1));
        System.out.println(grid);
        BufferedImage img = grid.toPng(30);

        // max path
        Cell maxStart = goalDistances.max();
        Distances maxStartDistances = maxStart.distances();

        Cell maxEnd = maxStartDistances.max();
        Distances maxStartEndDistances = maxStartDistances.pathTo(maxEnd);

        grid.setDistances(maxStartEndDistances);

        System.out.println("Max path:");
        System.out.println(grid);

        try {
            ImageIO.write(img, "png", new File(filepath));
            System.out.println("Saved to " + filepath);
        } catch (IOException e) {
            System.out.println("Failed to save to " + filepath);
            e.printStackTrace();
        }
    }
}
