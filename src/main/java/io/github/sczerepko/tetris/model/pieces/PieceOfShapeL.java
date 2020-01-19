package io.github.sczerepko.tetris.model.pieces;

import javafx.scene.paint.Color;

class PieceOfShapeL implements PieceRepresentation {

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

    private static final PieceOfShapeL INSTANCE = new PieceOfShapeL();

    static PieceOfShapeL getInstance() {
        return INSTANCE;
    }

    private PieceOfShapeL() {
    }

    @Override
    public PieceRotations getRotations() {
        return ROTATIONS;
    }

}
