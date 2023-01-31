package com.github.bitfexl.nonogram;

public class BitField {
    private final int width;

    private final int height;

    private final byte[] field;

    public BitField(int width, int height) {
        this.width = width;
        this.height = height;
        this.field = new byte[(width * height) / 8 + ((width * height) % 8 != 0 ? 1 : 0)];
    }

    public boolean get(int x, int y) {
        check(x, y);
        int i = (y * width) + x;
        return ((field[i / 8] >>> i % 8) & 1) == 1;
    }

    public void set(int x, int y, boolean b) {
        check(x, y);
        int i = (y * width) + x;

        if (b) {
            field[i / 8] |= 1 << i % 8;
        } else {
            field[i / 8] &= ~(1 << i % 8);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void check(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IndexOutOfBoundsException("x or y out of bounds (x: " + x + ", y: " + y + ", width: " + width + ", height: " + height + ").");
        }
    }
}
