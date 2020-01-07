package com.github.danieltex.grid;

import java.util.List;

public interface GridFactory {
    List<List<Cell>> makeGrid();
    int rows();
    int columns();
}