package io.github.sczerepko.tetris.model.pieces;

class SquarePiece extends Piece {

    private static final int[][] matrix = new int[][]{
            {0, 0, 0, 0},
            {0, 4, 4, 0},
            {0, 4, 4, 0},
            {0, 0, 0, 0}
    };

    @Override
    public int[][] rotate() {
        return copy(matrix);
    }

    @Override
    public int[][] getMatrix() {
        return copy(matrix);
    }

    @Override
    protected int[][] getRotation() {
        return copy(matrix);
    }

}
