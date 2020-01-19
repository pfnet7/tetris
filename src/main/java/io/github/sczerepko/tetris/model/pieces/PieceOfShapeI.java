package io.github.sczerepko.tetris.model.pieces;

import javafx.scene.paint.Color;

class PieceOfShapeI implements Piece {

    private static final Color COLOR = Color.RED;

    private final PieceRotations ROTATIONS = new PieceRotations(
            new Color[][]{
                    {null,  null,  null,  null},
                    {null,  null,  null,  null},
                    {COLOR, COLOR, COLOR, COLOR},
                    {null,  null,  null,  null}
            },
            new Color[][]{
                    {null, COLOR, null, null},
                    {null, COLOR, null, null},
                    {null, COLOR, null, null},
                    {null, COLOR, null, null}
            }
    );

    @Override
    public PieceRotations getRotations() {
        return ROTATIONS;
    }

    @Override
    public int getStartingX() {
        return 3;
    }

}
