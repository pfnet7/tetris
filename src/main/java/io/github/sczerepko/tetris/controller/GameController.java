package io.github.sczerepko.tetris.controller;

import io.github.sczerepko.tetris.model.Board;
import io.github.sczerepko.tetris.model.pieces.RandomPieceGenerator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class GameController {

    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGHT = 20;

    private Board board = new Board(BOARD_WIDTH, BOARD_HEIGHT);

    private GuiController guiController;

    private Timeline timeline;

    public GameController(GuiController guiController) {
        this.guiController = guiController;
        board.setFallingPiece(RandomPieceGenerator.generate());
        guiController.initializeBoardGrid(board);

        timeline = new Timeline(new KeyFrame(
                Duration.millis(500),
                event -> moveDown()
        ));
    }

    private void moveDown() {
        if (!board.canPieceMoveDown()) {
            board.embedPiece();
        }

    }

}
