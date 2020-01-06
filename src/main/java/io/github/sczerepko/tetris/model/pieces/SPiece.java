package io.github.sczerepko.tetris.model.pieces;

class SPiece extends Piece {

    private static final CircularList<int[][]> rotations = CircularList.of(
            new int[][] {
                    {0, 0, 0, 0},
                    {0, 3, 3, 0},
                    {3, 3, 0, 0},
                    {0, 0, 0, 0}
            },
            new int[][]{
                    {3, 0, 0, 0},
                    {3, 3, 0, 0},
                    {0, 3, 0, 0},
                    {0, 0, 0, 0}
            }
    );

    @Override
    protected int[][] getRotation() {
        return rotations.get(currentRotationIndex);
    }

}
