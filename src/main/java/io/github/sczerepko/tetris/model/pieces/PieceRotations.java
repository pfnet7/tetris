package io.github.sczerepko.tetris.model.pieces;

import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.List;

class PieceRotations {

    public static final int REPRESENTATION_SIZE = 4;

    private final List<Color[][]> rotations;
    private final int size;

    private int currentRotation = 0;

    PieceRotations(Color[][]... elements) {
        if (Arrays.stream(elements).anyMatch(this::hasWrongDimensions)) {
            throw new IllegalArgumentException("Piece Rotations need to be " + REPRESENTATION_SIZE + "x" + REPRESENTATION_SIZE);
        }
        this.rotations = List.of(elements);
        size = rotations.size();
    }

    private boolean hasWrongDimensions(Color[][] representation) {
        return representation.length != REPRESENTATION_SIZE || representation[0].length != REPRESENTATION_SIZE;
    }

    Color[][] current() {
        return copy(getModulo(currentRotation));
    }

    Color[][] next() {
        currentRotation++;
        return copy(getModulo(currentRotation));
    }

    Color[][] peek() {
        int peekIndex = currentRotation + 1;
        return copy(getModulo(peekIndex));
    }

    private Color[][] getModulo(int rotation) {
        return rotations.get(rotation % size);
    }

    private Color[][] copy(Color[][] matrix) {
        return Arrays.stream(matrix)
                     .map(Color[]::clone)
                     .toArray(Color[][]::new);
    }

}
