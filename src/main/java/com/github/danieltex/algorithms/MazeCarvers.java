package com.github.danieltex.algorithms;

import com.github.danieltex.grid.Grid;

public enum MazeCarvers implements MazeCarver {
    ALDOUS_BRODER(new AldousBroder()),
    BINARY_TREE(new BinaryTree()),
    HUNT_AND_KILL(new HuntAndKill()),
    SIDE_WINDER(new SideWinder()),
    WILSONS(new Wilsons()),
    BACKTRACKER(new RecursiveBacktracker()),
    ;

    private final MazeCarver mazeCarver;

    MazeCarvers(MazeCarver mazeCarver) {
        this.mazeCarver = mazeCarver;
    }

    public Grid on(Grid grid) {
        return mazeCarver.on(grid);
    }

    @Override
    public String toString() {
        return mazeCarver.getClass().getSimpleName();
    }
}