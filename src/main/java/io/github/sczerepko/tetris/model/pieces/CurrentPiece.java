package io.github.sczerepko.tetris.model.pieces;

import io.github.sczerepko.tetris.model.Translation;
import javafx.scene.paint.Color;

public class CurrentPiece {

    private int x;
    private int y;
    private Piece piece;

    public CurrentPiece(Piece piece) {
        this.x = piece.getStartingX();
        this.y = piece.getStartingY();
        this.piece = piece;
    }

    public void translate(Translation translation) {
        this.x += translation.getX();
        this.y += translation.getY();
    }

    public Color[][] getPieceMatrix() {
        return piece.getRotations().current();
    }

    public Color[][] peekRotation() {
        return piece.getRotations().peek();
    }

    public Color[][] rotate() {
        return piece.getRotations().next();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
