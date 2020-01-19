package io.github.sczerepko.tetris.model;

import io.github.sczerepko.tetris.model.pieces.Piece;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.paint.Color;

import java.util.Set;

/**
 * The Board representation.
 * Represents the current game state - the board matrix, current score and current falling piece.
 */
public class Board {

    /**
     * Number of rows needed for tetris.
     */
    public static final int TETRIS_ROW_NUMBER = 4;
    /**
     * Number of points for tetris clear.
     */
    public static final int TETRIS_POINTS = 800;
    /**
     * Number of points for regular clear of single row.
     */
    public static final int REGULAR_CLEAR_POINTS = 100;

    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGHT = 20;

    private IntegerProperty score;
    private Color[][] boardMatrix;
    private Piece currentPiece;

    public Board() {
        boardMatrix = new Color[BOARD_WIDTH][BOARD_HEIGHT];
        score = new SimpleIntegerProperty(0);
    }

    /**
     * Moves current piece along the given transaltion if possible.
     *
     * @param translation the translation to move the piece along
     * @return the current piece
     */
    public Piece moveCurrentPiece(Translation translation) {
        if (canPieceMove(translation)) {
            currentPiece.translate(translation);
        }
        return currentPiece;
    }

    /**
     * Checks if the current piece can move along the given translation
     *
     * @param translation the translation to move the piece along
     * @return true if the piece can move, false otherwise
     */
    public boolean canPieceMove(Translation translation) {
        int x = currentPiece.getX() + translation.getX();
        int y = currentPiece.getY() + translation.getY();
        return MatrixOperations.isMoveValid(boardMatrix, currentPiece.getPieceMatrix(), x, y);
    }

    /**
     * Rotates the current piece. If the piece is next to the left or the right border of the board and cannot be rotated,
     * it is first moved to the right or to the left respectively and then the attempt of rotation is taken.
     *
     * @return the current piece
     */
    public Piece rotateCurrentPiece() {
        if (!canPieceRotate()) {
            if (currentPiece.getX() == 0) {
                wallKick(Translation.RIGHT);
            } else if (currentPiece.getX() == 9 || currentPiece.getX() == 8) {
                wallKick(Translation.LEFT);
            }
        }
        if (canPieceRotate()) {
            currentPiece.rotate();
        }
        return currentPiece;
    }

    /**
     * Moves the current piece away from the wall.
     *
     * @param translation the translation to move the current piece along
     */
    public void wallKick(Translation translation) {
        currentPiece.translate(translation);
    }

    /**
     * Checks if the current piece can rotate.
     *
     * @return true if the piece can rotate, false otherwise.
     */
    public boolean canPieceRotate() {
        return MatrixOperations.isMoveValid(boardMatrix, currentPiece.peekRotation(), currentPiece.getX(), currentPiece.getY());
    }

    /**
     * Handles the situation when the current piece cannot move anymore and has to be embedded into the board.
     * If the piece caused any rows to become full, this method clears those rows and grants points appropriately.
     */
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

    /**
     * Adds points to the current score.
     *
     * @param points the points to be added
     */
    public void addPoints(int points) {
        score.setValue(score.intValue() + points);
    }

    /**
     * Checks if the piece is blocked and cannot move anymore.
     *
     * @param nextPiece the next piece
     * @return true if the piece is blocked, false otherwise
     */
    public boolean isPieceBlocked(Piece nextPiece) {
        return !MatrixOperations.isMoveValid(boardMatrix, nextPiece.getPieceMatrix(), nextPiece.getX(), nextPiece.getY());
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public void setCurrentPiece(Piece piece) {
        currentPiece = piece;
    }

    public Color[][] getBoardMatrix() {
        return boardMatrix;
    }

    public IntegerProperty scoreProperty() {
        return score;
    }

}
