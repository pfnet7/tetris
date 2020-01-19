package io.github.sczerepko.tetris.model.pieces;

import javafx.scene.paint.Color;

class PieceOfShapeI implements PieceRepresentation {

    private static final Color COLOR = Color.RED;

    private static final PieceRotations ROTATIONS = new PieceRotations(
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

    private static final PieceOfShapeI INSTANCE = new PieceOfShapeI();

    static PieceOfShapeI getInstance() {
        return INSTANCE;
    }

    private PieceOfShapeI() {
    }

    @Override
    public PieceRotations getRotations() {
        return ROTATIONS;
    }

    @Override
    public int getStartingXIndex() {
        return 3;
    }

}
