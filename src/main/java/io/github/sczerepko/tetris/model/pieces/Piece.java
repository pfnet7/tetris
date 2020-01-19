package io.github.sczerepko.tetris.model.pieces;

import io.github.sczerepko.tetris.model.Translation;
import javafx.scene.paint.Color;

/**
 * The Piece type that represents the shape and the coordinates of a piece on the board.
 */
public class Piece {

    private int x;
    private int y;
    private PieceRepresentation pieceRepresentation;

    /**
     * Instantiates a new Piece.
     *
     * @param pieceRepresentation the piece representation
     */
    public Piece(PieceRepresentation pieceRepresentation) {
        this.x = pieceRepresentation.getStartingXIndex();
        this.y = pieceRepresentation.getStartingYIndex();
        this.pieceRepresentation = pieceRepresentation;
    }

    /**
     * Translates the piece alone the given translation.
     *
     * @param translation the translation
     */
    public void translate(Translation translation) {
        this.x += translation.getX();
        this.y += translation.getY();
    }

    public Color[][] getPieceMatrix() {
        return pieceRepresentation.getRotations().current();
    }

    /**
     * Gives access to the next piece rotation without modifying the current state.
     *
     * @return the next piece rotation
     */
    public Color[][] peekRotation() {
        return pieceRepresentation.getRotations().peek();
    }

    /**
     * Rotates the piece.
     *
     * @return the next piece rotation representation
     */
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
