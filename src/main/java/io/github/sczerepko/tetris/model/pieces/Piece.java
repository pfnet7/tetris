package io.github.sczerepko.tetris.model.pieces;

import io.github.sczerepko.tetris.model.Translation;
import javafx.scene.paint.Color;

public class Piece {

    private int x;
    private int y;
    private PieceRepresentation pieceRepresentation;

    public Piece(PieceRepresentation pieceRepresentation) {
        this.x = pieceRepresentation.getStartingXIndex();
        this.y = pieceRepresentation.getStartingYIndex();
        this.pieceRepresentation = pieceRepresentation;
    }

    public void translate(Translation translation) {
        this.x += translation.getX();
        this.y += translation.getY();
    }

    public Color[][] getPieceMatrix() {
        return pieceRepresentation.getRotations().current();
    }

    public Color[][] peekRotation() {
        return pieceRepresentation.getRotations().peek();
    }

    public Color[][] rotate() {
        return pieceRepresentation.getRotations().next();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
