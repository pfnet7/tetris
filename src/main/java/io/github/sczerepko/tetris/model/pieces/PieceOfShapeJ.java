package io.github.sczerepko.tetris.model.pieces;

import javafx.scene.paint.Color;

class PieceOfShapeJ implements Piece {

    private static final Color COLOR = Color.BLUE;

    private static final PieceRotations ROTATIONS = new PieceRotations(
            new Color[][]{
                    {COLOR, COLOR, COLOR, null},
                    {null,  null,  COLOR, null},
                    {null,  null,  null,  null},
                    {null,  null,  null,  null}
            },
            new Color[][]{
                    {COLOR, COLOR, null, null},
                    {COLOR, null,  null, null},
                    {COLOR, null,  null, null},
                    {null,  null,  null, null}
            },
            new Color[][]{
                    {COLOR, null,  null,  null},
                    {COLOR, COLOR, COLOR, null},
                    {null,  null,  null,  null},
                    {null,  null,  null,  null}
            },
            new Color[][]{
                    {null,  COLOR, null, null},
                    {null,  COLOR, null, null},
                    {COLOR, COLOR, null, null},
                    {null,  null,  null, null}
            }
    );

    @Override
    public PieceRotations getRotations() {
        return ROTATIONS;
    }


}
