package com.github.bitfexl.nonogram;

public class GridCellPrinter {
    public static void main(String[] args) {
        System.out.println(new GridCellPrinter().print(
                new String[][] {
                        {"a", "b", "c", "d"},
                        {"ab", "bb", "cb", "db"},
                        {"a", "b", "cc", "d"},
                        {"a", "bbb", "c", "dddd"},

                }
        ));
    }

    private final int MAX_CELL_WIDTH = 5;

    public String print(String[][] grid) {
        check(grid);

        StringBuilder sb = new StringBuilder();

        String separator = "+-----".repeat(grid[0].length) + "+\n";

        sb.append(separator);

        for (String[] row : grid) {
            for (String cell : row) {
                int pad = (MAX_CELL_WIDTH - cell.length()) / 2;
                sb.append("|")
                .append(" ".repeat(pad + (MAX_CELL_WIDTH - pad * 2 - cell.length())))
                .append(cell)
                .append(" ".repeat(pad));

            }
            sb.append("|").append("\n");
            sb.append(separator);
        }

        return sb.toString();
    }

    private void check(String[][] grid) {
        if (grid.length == 0) {
            throw new IllegalArgumentException("Cannot print a grid with no size.");
        }

        int width = grid[0].length;

        for (String[] row : grid) {
            if (row.length != width) {
                throw new IllegalArgumentException("All rows must have the same width.");
            }

            for (String cell : row) {
                if (cell.length() >= MAX_CELL_WIDTH) {
                    throw new IllegalArgumentException("Max cell size (string length) is 5.");
                }
            }
        }
    }
}
