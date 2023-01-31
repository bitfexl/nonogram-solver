package com.github.bitfexl.nonogram;

public interface NonogramBoard {
    enum Cell {
        EMPTY, FILLED, MARKED_EMPTY
    }

    /**
     * Get the width of the nonogram.
     * @return The width (max x + 1).
     */
    int getWidth();

    /**
     * Get the height of the nonogram.
     * @return The height (max y + 1).
     */
    int getHeight();

    /**
     * Get a cell of the nonogram board.
     * 0, 0 is top left.
     * @param x The x coordinate (horizontal).
     * @param y The y coordinate (vertical)
     * @return The state of the cell.
     */
    Cell getCell(int x, int y);

    /**
     * Set a cell on the nonogram board.
     * 0, 0 is top left.
     * @param x The x coordinate (horizontal).
     * @param y The y coordinate (vertical)
     * @param cell The state of the cell.
     */
    void setCell(int x, int y, Cell cell);

    /**
     * Get the constraints for a row (left to right).
     * @param row The row to get.
     * @return The count of adjacent filled squares.
     */
    int[] getRowConstraints(int row);

    /**
     * Get the constraints for a column (left to right).
     * @param col The column to get.
     * @return The count of adjacent filled squares.
     */
    int[] getColConstraints(int col);

    /**
     * Set the constraints for a row (left to right).
     * @param row The row to set.
     * @param constraints The count of adjacent filled squares.
     */
    void setRowConstraints(int row, int[] constraints);

    /**
     * Set the constraints for a column (left to right).
     * @param col The column to set.
     * @param constraints The count of adjacent filled squares.
     */
    void setColConstraints(int col, int[] constraints);

    /**
     * Solve the nonogram. After solving getCell should
     * satisfy all constraints for all cells.
     * Solving should only modify empty cells.
     * @return true: solved, false: not solved (unsolvable or other error).
     */
    boolean solve();
}
