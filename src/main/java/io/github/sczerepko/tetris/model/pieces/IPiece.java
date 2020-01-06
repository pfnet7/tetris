package io.github.sczerepko.tetris.model.pieces;

class IPiece extends Piece {

    private static final CircularList<int[][]> rotations = CircularList.of(
            new int[][] {
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0}
            },
            new int[][]{
                    {0, 0, 0, 0},
                    {1, 1, 1, 1},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
            }
    );

    @Override
    protected int[][] getRotation() {
        return rotations.get(currentRotationIndex);
    }

}
