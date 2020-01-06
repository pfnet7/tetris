package io.github.sczerepko.tetris.model.pieces;

class LPiece extends Piece {

    private static final CircularList<int[][]> rotations = CircularList.of(
            new int[][]{
                    {0, 2, 0, 0},
                    {0, 2, 0, 0},
                    {0, 2, 2, 0},
                    {0, 0, 0, 0}
            },
            new int[][]{
                    {0, 0, 0, 0},
                    {0, 2, 2, 2},
                    {0, 2, 0, 0},
                    {0, 0, 0, 0}
            },
            new int[][]{
                    {0, 0, 0, 0},
                    {0, 2, 2, 0},
                    {0, 0, 2, 0},
                    {0, 0, 2, 0}
            },
            new int[][]{
                    {0, 0, 0, 0},
                    {0, 0, 2, 0},
                    {2, 2, 2, 0},
                    {0, 0, 0, 0}
            }
    );

    @Override
    protected int[][] getRotation() {
        return rotations.get(currentRotationIndex);
    }

}
