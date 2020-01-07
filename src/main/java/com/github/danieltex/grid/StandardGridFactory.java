package com.github.danieltex.grid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StandardGridFactory implements GridFactory {

    private final int rows;
    private final int columns;

    public StandardGridFactory(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public int rows() {
        return rows;
    }

    @Override
    public int columns() {
        return columns;
    }

    @Override
    public List<List<Cell>> makeGrid() {
        List<List<Cell>> rowList = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<Cell> row = new ArrayList<>(columns);
            for (int j = 0; j < columns; j++) {
                row.add(new Cell(i, j));
            }
            rowList.add(Collections.unmodifiableList(row));
        }
        return Collections.unmodifiableList(rowList);
    }
}