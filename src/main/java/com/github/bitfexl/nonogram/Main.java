package com.github.bitfexl.nonogram;

public class Main {
    public static void main(String[] args) {
        Nonogram nonogram = new Nonogram(5, 5);
        nonogram.setRowConstraints(0, new int[] {1, 1});
        nonogram.setColConstraints(0, new int[] {1});
        nonogram.setColConstraints(2, new int[] {1});
        nonogram.setCell(0, 0, NonogramBoard.Cell.FILLED);
        nonogram.setCell(2, 0, NonogramBoard.Cell.FILLED);
        System.out.println(nonogram.solve());
    }

//    public static void main(String[] args) {
//        BitField bitField = new BitField(20, 10);
//        bitField.set(0, 4, true);
//        bitField.set(3, 6, true);
//        bitField.set(2, 7, true);
//        bitField.set(6, 8, true);
//        bitField.set(8, 3, true);
//
//        for (int r = 0; r < bitField.getWidth(); r++) {
//            for (int c = 0; c < bitField.getHeight(); c++) {
//                System.out.print(bitField.get(c, r) ? "1 " : "0 ");
//            }
//            System.out.println();
//        }
//    }
}