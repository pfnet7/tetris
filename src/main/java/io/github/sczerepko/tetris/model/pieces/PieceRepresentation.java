package io.github.sczerepko.tetris.model.pieces;

import static io.github.sczerepko.tetris.model.Board.BOARD_WIDTH;

public interface PieceRepresentation {

    int DEFAULT_STARTING_X_INDEX = BOARD_WIDTH / 2 - 1;
    int DEFAULT_STARTING_Y_INDEX = 0;

    PieceRotations getRotations();

    default int getStartingXIndex() {
        return DEFAULT_STARTING_X_INDEX;
    }

    default int getStartingYIndex() {
        return DEFAULT_STARTING_Y_INDEX;
    }

}
