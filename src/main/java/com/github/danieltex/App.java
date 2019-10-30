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
        Grid grid = new Grid(4, 4);
        System.out.println(BinaryTree.on(grid));
    }
}
