package io.github.sczerepko.tetris.model.pieces;

import javafx.scene.paint.Color;

class PieceOfShapeL implements Piece {

    private static final Color COLOR = Color.ORANGE;

    private final PieceRotations ROTATIONS = new PieceRotations(
            new Color[][]{
                    {null,  null,  COLOR, null},
                    {COLOR, COLOR, COLOR, null},
                    {null,  null,  null,  null},
                    {null,  null,  null,  null}
            },
            new Color[][]{
                    {COLOR, COLOR, null, null},
                    {null,  COLOR, null, null},
                    {null,  COLOR, null, null},
                    {null,  null,  null, null}
            },
            new Color[][]{
                    {COLOR, COLOR, COLOR, null},
                    {COLOR, null,  null,  null},
                    {null,  null,  null,  null},
                    {null,  null,  null,  null}
            },
            new Color[][]{
                    {COLOR, null,  null, null},
                    {COLOR, null,  null, null},
                    {COLOR, COLOR, null, null},
                    {null,  null,  null, null}
            }
    );

    @Override
    public PieceRotations getRotations() {
        return ROTATIONS;
    }

}
