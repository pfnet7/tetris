package io.github.sczerepko.tetris.model.pieces;

import javafx.scene.paint.Color;

class PieceOfShapeS implements PieceRepresentation {

    private static final Color COLOR = Color.GREEN;

    private static final PieceRotations ROTATIONS = new PieceRotations(
            new Color[][]{
                    {COLOR, null,  null, null},
                    {COLOR, COLOR, null, null},
                    {null,  COLOR, null, null},
                    {null,  null,  null, null}
            },
            new Color[][]{
                    {null,  COLOR, COLOR, null},
                    {COLOR, COLOR, null,  null},
                    {null,  null,  null,  null},
                    {null,  null,  null,  null}
            }
    );

    private static final PieceOfShapeS INSTANCE = new PieceOfShapeS();

    static PieceOfShapeS getInstance() {
        return INSTANCE;
    }

    private PieceOfShapeS() {
    }

    @Override
    public PieceRotations getRotations() {
        return ROTATIONS;
    }

}
