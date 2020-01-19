package io.github.sczerepko.tetris.model;

import io.github.sczerepko.tetris.model.pieces.CurrentPiece;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.paint.Color;

import java.util.Set;

public class Board {

    public static final int TETRIS_ROW_NUMBER = 4;
    public static final int TETRIS_POINTS = 800;
    public static final int REGULAR_CLEAR_POINTS = 100;

    private static final int BOARD_WIDTH = 10;
    private static final int BOARD_HEIGHT = 20;

    private IntegerProperty score;
    private Color[][] boardMatrix;
    private CurrentPiece currentPiece;

    public Board() {
        boardMatrix = new Color[BOARD_WIDTH][BOARD_HEIGHT];
        score = new SimpleIntegerProperty(0);
    }

    public CurrentPiece moveCurrentPiece(Translation translation) {
        if (canPieceMove(translation)) {
            currentPiece.translate(translation);
        }
        return currentPiece;
    }

    public boolean canPieceMove(Translation translation) {
        int x = currentPiece.getX() + translation.getX();
        int y = currentPiece.getY() + translation.getY();
        return MatrixOperations.isMoveValid(boardMatrix, currentPiece.getPieceMatrix(), x, y);
    }

    public CurrentPiece rotateCurrentPiece() {
        if (!canPieceRotate()) {
            if (currentPiece.getX() == 0) {
                wallKick(Translation.RIGHT);
            } else if (currentPiece.getX() == boardMatrix.length - 1) {
                wallKick(Translation.LEFT);
            }
        }
        if (canPieceRotate()) {
            currentPiece.rotate();
        }
        return currentPiece;
    }

    public void wallKick(Translation translation) {
        currentPiece.translate(translation);
    }

    public boolean canPieceRotate() {
        return MatrixOperations.isMoveValid(boardMatrix, currentPiece.peekRotation(), currentPiece.getX(), currentPiece.getY());
    }

    public void handlePieceEmbed() {
        MatrixOperations.embedPiece(boardMatrix, currentPiece.getPieceMatrix(), currentPiece.getX(), currentPiece.getY());
        Set<Integer> fullRowsIndexes = MatrixOperations.calculateFullRowsIndexes(boardMatrix);
        if (fullRowsIndexes.size() > 0) {
            boardMatrix = MatrixOperations.deleteFullRows(fullRowsIndexes, boardMatrix);
        }
        int gainedPoints = calculatePoints(fullRowsIndexes.size());
        addPoints(gainedPoints);
    }

    private int calculatePoints(int numberOfFullRows) {
        return numberOfFullRows == TETRIS_ROW_NUMBER
                ? TETRIS_POINTS
                : REGULAR_CLEAR_POINTS * numberOfFullRows;
    }

    public void addPoints(int points) {
        score.setValue(score.intValue() + points);
    }

    public boolean isPieceBlocked(CurrentPiece nextPiece) {
        return !MatrixOperations.isMoveValid(boardMatrix, nextPiece.getPieceMatrix(), nextPiece.getX(), nextPiece.getY());
    }

    public CurrentPiece getCurrentPiece() {
        return currentPiece;
    }

    public void setCurrentPiece(CurrentPiece piece) {
        currentPiece = piece;
    }

    public Color[][] getBoardMatrix() {
        return boardMatrix;
    }

    public IntegerProperty scoreProperty() {
        return score;
    }

}
