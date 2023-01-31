package com.github.bitfexl.nonogram.gui;

import com.github.bitfexl.nonogram.Nonogram;

import javax.swing.*;
import java.awt.*;

public class NonogramPanel extends JPanel {
    private Nonogram nonogram;

    public NonogramPanel(Nonogram nonogram) {
        this.nonogram = nonogram;
    }



    @Override
    protected void paintComponent(Graphics g) {
        final Graphics2D g2d = (Graphics2D) g;
        for (int c=0; c<nonogram.getWidth(); c++) {
            for (int r=0; r<nonogram.getHeight(); r++) {

            }
        }
    }

    public Nonogram getNonogram() {
        return nonogram;
    }

    public void setNonogram(Nonogram nonogram) {
        this.nonogram = nonogram;
    }
}
