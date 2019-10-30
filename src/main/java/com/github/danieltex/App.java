package com.github.danieltex;

import com.github.danieltex.algorithms.BinaryTree;
import com.github.danieltex.grid.Grid;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        int rows = Integer.parseInt(args[0]);
        int cols = Integer.parseInt(args[1]);

        Grid grid = new Grid(rows, cols);
        System.out.println(BinaryTree.on(grid));
    }
}
