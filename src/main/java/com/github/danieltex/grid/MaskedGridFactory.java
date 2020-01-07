package com.github.danieltex.grid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaskedGridFactory implements GridFactory {

    private final Mask mask;

    public MaskedGridFactory(Mask mask) {
        this.mask = mask;
    }

    @Override
    public int rows() {
        return mask.getRows();
    }

    @Override
    public int columns() {
        return mask.getColumns();
    }

    @Override
    public List<List<Cell>> makeGrid() {
        List<List<Cell>> rowList = new ArrayList<>();
        for (int i = 0; i < rows(); i++) {
            List<Cell> row = new ArrayList<>(columns());
            for (int j = 0; j < columns(); j++) {             
                Cell cell = (mask.get(i, j)) ? new Cell(i, j) : null;
                row.add(cell);
            }
            rowList.add(Collections.unmodifiableList(row));
        }
        return Collections.unmodifiableList(rowList);
    }
}