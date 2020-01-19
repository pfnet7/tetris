package io.github.sczerepko.tetris.model.pieces;

import static io.github.sczerepko.tetris.model.Board.BOARD_WIDTH;

/**
 * The interface Piece representation that is the base for every implementation.
 */
public interface PieceRepresentation {

    /**
     * The starting point of the new pieces along the x axis.
     */
    int DEFAULT_STARTING_X_INDEX = BOARD_WIDTH / 2 - 1;
    /**
     * The starting point of the new pieces along the y axis.
     */
    int DEFAULT_STARTING_Y_INDEX = 0;

    /**
     * Gets all rotations.
     *
     * @return the rotations in the format 4x4
     */
    PieceRotations getRotations();

    /**
     * Gets starting x index.
     *
     * @return the starting x index
     */
    default int getStartingXIndex() {
        return DEFAULT_STARTING_X_INDEX;
    }

    /**
     * Gets starting y index.
     *
     * @return the starting y index
     */
    default int getStartingYIndex() {
        return DEFAULT_STARTING_Y_INDEX;
    }

}
