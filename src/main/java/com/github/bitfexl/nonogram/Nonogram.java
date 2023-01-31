package com.github.bitfexl.nonogram;

public class Nonogram implements NonogramBoard {
    private final int[][] colConstraints;
    private final int[][] rowConstraints;

    private final BitField board;
    private final BitField markedEmpty;

    public Nonogram(int width, int height) {
        this.colConstraints = new int[width][];
        this.rowConstraints = new int[height][];

        for (int i=0; i<width; i++) {
            colConstraints[i] = new int[] { 0 };
        }
        for (int i=0; i<height; i++) {
            rowConstraints[i] = new int[] { 0 };
        }

        this.board = new BitField(width, height);
        this.markedEmpty = new BitField(width, height);
    }

    @Override
    public int getWidth() {
        return board.getWidth();
    }

    @Override
    public int getHeight() {
        return board.getHeight();
    }

    @Override
    public Cell getCell(int x, int y) {
        return board.get(x, y) ? Cell.FILLED : markedEmpty.get(x, y) ? Cell.MARKED_EMPTY : Cell.EMPTY;
    }

    @Override
    public void setCell(int x, int y, Cell cell) {
        if (cell == Cell.MARKED_EMPTY) {
            board.set(x, y, false);
            markedEmpty.set(x, y, true);
        } else {
            board.set(x, y, cell == Cell.FILLED);
            markedEmpty.set(x, y, false);
        }
    }

    @Override
    public int[] getRowConstraints(int row) {
        return rowConstraints[row];
    }

    @Override
    public int[] getColConstraints(int col) {
        return colConstraints[col];
    }

    @Override
    public void setRowConstraints(int row, int[] constraints) {
        rowConstraints[row] = constraints;
    }

    @Override
    public void setColConstraints(int col, int[] constraints) {
        colConstraints[col] = constraints;
    }

    @Override
    public boolean solve() {
        boolean solved = true;

        for (int row = 0; row < board.getWidth(); row++) {
            solved = solved && checkRow(board, row);
        }

        for (int col = 0; col < board.getHeight(); col++) {
            solved = solved && checkCol(board, col);
        }

        return solved;
    }

    /**
     * Check if a row of the given board satisfies all constraints.
     */
    private boolean checkRow(BitField board, int row) {
        int constraintIndex = 0;
        int filledCount = 0;

        for (int i=0; i<board.getWidth(); i++) {
            if (board.get(i, row)) {
                // count squares
                filledCount++;
            } else if (filledCount > 0) {
                // squares counted
                if (constraintIndex >= rowConstraints[row].length || rowConstraints[row][constraintIndex++] != filledCount) {
                    // no constraint left or constraint not satisfied
                    return false;
                }
                filledCount = 0;
            }
        }

        boolean lastConstraintChecked = filledCount == 0 || (constraintIndex < rowConstraints[row].length && rowConstraints[row][constraintIndex++] == filledCount);

        return lastConstraintChecked && (constraintIndex == rowConstraints[row].length || rowConstraints[row][0] == 0);
    }

    /**
     * Check if a column of the given board satisfies all constraints.
     */
    private boolean checkCol(BitField board, int col) {
        int constraintIndex = 0;
        int filledCount = 0;

        for (int i=0; i<board.getHeight(); i++) {
            if (board.get(col, i)) {
                // count squares
                filledCount++;
            } else if (filledCount > 0) {
                // squares counted
                if (constraintIndex >= colConstraints[col].length || colConstraints[col][constraintIndex++] != filledCount) {
                    // no constraint left or constraint not satisfied
                    return false;
                }
                filledCount = 0;
            }
        }

        boolean lastConstraintChecked = filledCount == 0 || (constraintIndex < colConstraints[col].length && colConstraints[col][constraintIndex++] == filledCount);

        return lastConstraintChecked && (constraintIndex == colConstraints[col].length || colConstraints[col][0] == 0);
    }
}
