package io.github.sczerepko.tetris.model.pieces;

import java.util.Arrays;

public abstract class Piece {

    private static final int initialX = 3;
    private static final int initialY = 0;

    private int x;
    private int y;

    protected int currentRotationIndex;

    protected abstract int[][] getRotation();

    public Piece() {
        this.x = initialX;
        this.y = initialY;
        currentRotationIndex = 0;
    }

    public int[][] rotate() {
        currentRotationIndex = currentRotationIndex + 1;
        return copy(getRotation());
    }

    public int[][] getMatrix() {
        return copy(getRotation());
    }

    protected int[][] copy(int[][] matrix) {
        return Arrays.stream(matrix)
                     .map(int[]::clone)
                     .toArray(int[][]::new);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
