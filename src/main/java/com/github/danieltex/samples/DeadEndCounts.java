package com.github.danieltex.samples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.danieltex.algorithms.MazeCarvers;
import com.github.danieltex.grid.Grid;

public class DeadEndCounts {
    public static void main(String[] args) {
        final int tries = 100;
        final int size = 20;

        final Map<MazeCarvers, Double> averages = new HashMap<>();
        final MazeCarvers[] carvers = MazeCarvers.values();
        for (MazeCarvers carver : carvers) {
            System.out.println("Running algorithm " + carver);
            List<Integer> deadEndCounts = new ArrayList<>();
            for (int i = 0; i < tries; i++) {
                Grid grid = new Grid(size, size);
                carver.on(grid);
                deadEndCounts.add(grid.deadEnds().size());
            }
            double average = deadEndCounts.stream()
                .mapToInt(t -> t)
                .average()
                .getAsDouble();
            averages.put(carver, average);
        }
        final int totalCells = size * size;
        System.out.printf("%nAverage dead-ends per %dx%d maze (%d cells):%n%n", size, size, totalCells);
        
        MazeCarvers[] sortedCarvers = carvers.clone();
        Arrays.sort(sortedCarvers, 
            (a, b) -> Double.compare(averages.get(b), averages.get(a))
        );

        for (MazeCarvers carver : sortedCarvers) {
            double percentage = averages.get(carver) * 100. / (totalCells);
            System.out.printf("%20s : %3.0f/%3d (%.0f%%)%n",
                carver, averages.get(carver), totalCells, percentage);
        }
    }
}