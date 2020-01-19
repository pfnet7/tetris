package io.github.sczerepko.tetris.model;


/**
 * The enum Translation that is used to represent change of the coordinates in the given directions.
 */
public enum Translation {
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    NONE(0, 0);

    private int x;
    private int y;

    Translation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
