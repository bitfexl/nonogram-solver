package com.github.bitfexl.nonogram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Nonogram {
    enum Space {
        EMPTY, FILLED, MARKED_EMPTY
    }

    public record Cord(int x, int y) { }

    private final int width;
    private final int height;

    private final List<List<Integer>> columnSquares = new ArrayList<>();
    private final List<List<Integer>> rowSquares = new ArrayList<>();

    private final Map<Cord, Space> nonogram = new HashMap<>();

    public Nonogram(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Space get(Cord cord) {
        checkCord(cord);
        Space space = nonogram.get(cord);
        return space != null ? space : Space.EMPTY;
    }

    public void set(Cord cord, Space space) {
        checkCord(cord);
        nonogram.put(cord, space);
    }

    private void checkCord(Cord cord) {
        if (cord.x < 0 || cord.x >= width || cord.y < 0 || cord.y >= height) {
            throw new IllegalArgumentException("Cord is out of bounds.");
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
