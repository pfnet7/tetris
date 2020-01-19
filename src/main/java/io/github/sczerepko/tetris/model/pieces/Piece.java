package io.github.sczerepko.tetris.model.pieces;

public interface Piece {

    int DEFAULT_STARTING_X = 4;
    int DEFAULT_STARTING_Y = 0;

    PieceRotations getRotations();

    default int getStartingX() {
        return DEFAULT_STARTING_X;
    }

    default int getStartingY() {
        return DEFAULT_STARTING_Y;
    }

}
