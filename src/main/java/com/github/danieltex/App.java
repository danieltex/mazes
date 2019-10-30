package com.github.danieltex;

import com.github.danieltex.algorithms.BinaryTree;
import com.github.danieltex.algorithms.SideWinder;
import com.github.danieltex.grid.Grid;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        int rows = Integer.parseInt(args[1]);
        int cols = Integer.parseInt(args[2]);

        Grid grid = new Grid(rows, cols);

        switch(args[0].toUpperCase()) {
            case "SIDEWINDER":
                System.out.println(SideWinder.on(grid));
                break;
            case "BINARYTREE":
                System.out.println(BinaryTree.on(grid));
                break;
            default:
                System.out.println("Unknow option: " + args[0]);
                break;
        }
    }
}
