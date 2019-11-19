package com.github.danieltex.grid;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Grid implements Iterable<Cell> {
    public static final int DEFAULT_CELL_SIZE = 10;
    public static final int DEFAULT_WALL_WIDTH = 1;
    
    private static final String BACKGROUNDS = "backgrounds";
    private static final String WALLS = "walls";
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private static final Color WALL_COLOR = Color.BLACK;

    public final int rows;
    public final int columns;

    private final List<List<Cell>> grid;
    
    private final Random rand = new Random();

    public Grid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = prepareGrid();
        configureCells();
    }

    /**
     * cell size includes wall width
     * interior of the cell is (cell size - wall width)
     * So for a cell of size 30 and a wall of width 5
     * we will have the interior equals to 25
     * @param cellSize
     * @param wallWidth
     * @return
     */
    public BufferedImage toPng(int cellSize, int wallWidth) {
        final int border = wallWidth;
        final int margin = wallWidth;
        final int imgWidth = cellSize * columns + wallWidth + margin * 2;
        final int imgHeight = cellSize * rows + wallWidth + margin * 2;
        
        BufferedImage img = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, imgWidth, imgHeight);

        for (String mode : Arrays.asList(BACKGROUNDS, WALLS)) {
            for (Cell cell : allCells()) {
                int x1 = cell.column * cellSize + border;
                int y1 = cell.row * cellSize + border;
                int x2 = (cell.column + 1) * cellSize + border;
                int y2 = (cell.row + 1) * cellSize + border;

                if (mode == BACKGROUNDS) {
                    // draw content
                    Color color = backgroundColorFor(cell);
                    if (color != null) {
                        g.setColor(color);
                        g.fillRect(x1, y1, x2 - x1, y2 - y1);
                    }
                } else {
                    // draw walls
                    g.setColor(WALL_COLOR);
                    if (cell.north == null)         g.fillRect(x1, y1, cellSize + border, border);
                    if (cell.west  == null)         g.fillRect(x1, y1, border, cellSize + border);
                    if (!cell.isLinked(cell.east))  g.fillRect(x2, y1, border, cellSize + border);
                    if (!cell.isLinked(cell.south)) g.fillRect(x1, y2, cellSize + border, border);
                }
            }
        }

        g.dispose();
        return img;
    }

    public BufferedImage toPng(int cellSize) {
        return toPng(cellSize, DEFAULT_WALL_WIDTH);
    }

    public BufferedImage toPng() {
        return toPng(DEFAULT_CELL_SIZE, DEFAULT_WALL_WIDTH);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        output.append('+');
        for (int i = 0; i < columns; i++) {
            output.append("---+");
        }
        output.append('\n');

        for (List<Cell> row : grid) {
            StringBuilder top = new StringBuilder().append('|');
            StringBuilder bottom = new StringBuilder().append('+');
            for (Cell cell : row) {
                if (cell == null) cell = new Cell(-1, -1);
                String body = String.format(" %s ", contentsOf(cell));
                top.append(body);
                top.append(cell.isLinked(cell.east) ? ' ': '|');                
                bottom.append(cell.isLinked(cell.south) ? "   ": "---");
                bottom.append('+');
            }            
            output.append(top).append('\n');
            output.append(bottom).append('\n');
        }

        return output.toString();
    }

    @Override
    public Iterator<Cell> iterator() {
        return allCells().iterator();
    }

    public List<List<Cell>> rows() {
        return grid;
    }

    public List<Cell> allCells() {
        return grid.stream()
          .flatMap(row -> row.stream())
          .collect(toList());
    }

    public Cell cellAt(int row, int column) {
        if (row < 0 || row >= rows)
            return null;
        if (column < 0 || column >= grid.get(row).size())
            return null;

        return grid.get(row).get(column);
    }

    public Cell randomCell() {
        int row = rand.nextInt(rows);
        int column = rand.nextInt(grid.get(row).size());
        return cellAt(row, column);
    }

    public int size() {
        return rows * columns;
    }

    public List<Cell> deadEnds() {
        return allCells().stream()
            .filter(cell -> cell.links().size() == 1)
            .collect(toList());
    }

    private List<List<Cell>> prepareGrid() {
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

    private void configureCells() {
        for (Cell cell : allCells()) {
            cell.north = cellAt(cell.row - 1, cell.column);
            cell.south = cellAt(cell.row + 1, cell.column);
            cell.west = cellAt(cell.row, cell.column - 1);
            cell.east = cellAt(cell.row, cell.column + 1);
        }
    }

    protected String contentsOf(Cell cell) {
        return " ";
    }

    protected Color backgroundColorFor(Cell cell) {
        return null;
    }
}
