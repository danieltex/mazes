package com.github.danieltex.samples;

import static com.github.danieltex.algorithms.MazeCarvers.BACKTRACKER;

import com.github.danieltex.grid.Mask;
import com.github.danieltex.grid.MaskedGrid;

public class SimpleMask {
    public static void main(String[] args) {
        Mask mask = new Mask(5, 5);
        mask.set(0, 0, false);
        mask.set(2, 2, false);
        mask.set(4, 4, false);
        MaskedGrid grid = new MaskedGrid(mask);
        BACKTRACKER.on(grid);

        System.out.println(grid);
    }
}