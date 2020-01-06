package io.github.sczerepko.tetris.model;

import io.github.sczerepko.tetris.model.pieces.Piece;

public class Board {

    private int width;
    private int height;
    private int[][] board;
    private Piece fallingPiece;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        board = new int[width][height];
    }

    public boolean canPieceMoveDown() {
        int[][] pieceMatrix = fallingPiece.getMatrix();
        int x = fallingPiece.getX();
        int y = fallingPiece.getY();
        for (int i = 0; i < pieceMatrix.length; i++) {
            for (int j = 0; j < pieceMatrix[i].length; j++) {
                int newX = x + i;
                int newY = y + j;
                if (isOutOfBounds(board, newX, newY) || pieceMatrix[i][j] != 0 && board[newY][newX] != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isOutOfBounds(int[][] pieceMatrix, int x, int y) {
        return x >= pieceMatrix.length || y >= pieceMatrix[0].length;
    }

    public void embedPiece() {
        int[][] pieceMatrix = fallingPiece.getMatrix();
        for (int i = 0; i < pieceMatrix.length; i++) {
            for (int j = 0; j < pieceMatrix[i].length; j++) {
                int newX = fallingPiece.getX() + i;
                int newY = fallingPiece.getY() + j;
                if (pieceMatrix[i][j] != 0) {
                    board[newX][newY] = pieceMatrix[i][j];
                }
            }
        }
    }

    public void setFallingPiece(Piece piece) {
        fallingPiece = piece;
    }

    public int[][] getBoardMatrix() {
        return board;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
