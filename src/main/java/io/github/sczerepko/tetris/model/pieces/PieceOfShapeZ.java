package io.github.sczerepko.tetris.model.pieces;

import javafx.scene.paint.Color;

class PieceOfShapeZ implements PieceRepresentation {

    private static final Color COLOR = Color.CYAN;

    private static final PieceRotations ROTATIONS = new PieceRotations(
            new Color[][]{
                    {COLOR, COLOR, null,  null},
                    {null,  COLOR, COLOR, null},
                    {null,  null,  null,  null},
                    {null,  null,  null,  null}
            },
            new Color[][]{
                    {null,  COLOR, null, null},
                    {COLOR, COLOR, null, null},
                    {COLOR, null,  null, null},
                    {null,  null,  null, null}
            }
    );

    @Override
    public PieceRotations getRotations() {
        return ROTATIONS;
    }

}
