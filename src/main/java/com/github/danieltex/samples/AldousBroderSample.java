package com.github.danieltex.samples;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.danieltex.algorithms.AldousBroder;
import com.github.danieltex.grid.Grid;

public class AldousBroderSample {
    public static void main(String[] args) throws IOException {
        Grid grid = new Grid(20, 20);
        AldousBroder.on(grid);
        ImageIO.write(grid.toPng(), "png", new File("aldouBroder.png"));
    }
}