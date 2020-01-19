package io.github.sczerepko.tetris.model.pieces;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;

class PieceRotations {

    private final ArrayList<Color[][]> list;
    private final int size;

    private int currentRotation;

    PieceRotations(Color[][]... elements) {
        this.list = new ArrayList<>(Arrays.asList(elements));
        size = list.size();
    }

    Color[][] current() {
        return copy(list.get(currentRotation % size));
    }

    Color[][] next() {
        currentRotation++;
        return copy(list.get(currentRotation % size));
    }

    Color[][] peek() {
        int peekIndex = currentRotation + 1;
        return copy(list.get(peekIndex % size));
    }

    private Color[][] copy(Color[][] matrix) {
        return Arrays.stream(matrix)
                     .map(Color[]::clone)
                     .toArray(Color[][]::new);
    }

}
