package com.github.bitfexl.nonogram;

import java.util.*;

public class NonogramPrinter {
    private final GridCellPrinter gridPrinter = new GridCellPrinter();

    public String print(NonogramBoard board) {
        StringBuilder sb = new StringBuilder();

        String[] rowConstraints = new String[board.getHeight()];
        int maxRowConstraintsLength = 0;

        for (int r = 0; r < board.getHeight(); r++) {
            for (int rowConstraint : board.getRowConstraints(r)) {
                sb.append(rowConstraint).append("  ").append(sb.length() % 2 != 0 ? " " : "");
            }
            maxRowConstraintsLength = Math.max(maxRowConstraintsLength, sb.length());
            rowConstraints[r] = sb.toString();
            sb.setLength(0);
        }

        Stack<String> colConstraintsReversed = new Stack<>();
        boolean colConstraintsLeft = true;

        for (int i = 0; colConstraintsLeft; i++) {
            colConstraintsLeft = false;

            for (int c = 0; c < board.getWidth(); c++) {
                int[] colConstraint = board.getColConstraints(c);
                if (colConstraint.length > i) {
                    sb.append("   ")
                            .append(colConstraint[colConstraint.length - (i + 1)])
                            .append(colConstraint[colConstraint.length - (i + 1)] < 10 ? " " : "")
                            .append(" ");
                    colConstraintsLeft |= colConstraint.length > i + 1;
                } else {
                    sb.append("      ");
                }
            }

            colConstraintsReversed.push(sb.toString());
            sb.setLength(0);
        }

        String colConstraintsPrefix = " ".repeat(maxRowConstraintsLength);
        while (!colConstraintsReversed.empty()) {
            sb.append(colConstraintsPrefix).append(colConstraintsReversed.pop()).append("\n");
            sb.append("\n");
        }

        for (int i = 0; i < rowConstraints.length; i++) {
            sb.append(" ".repeat(maxRowConstraintsLength - rowConstraints[i].length())).append(rowConstraints[i]);
            // todo: board
            sb.append("\n\n");
        }

        return sb.toString();
    }
}
