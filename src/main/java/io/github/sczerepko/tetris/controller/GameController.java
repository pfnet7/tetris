package io.github.sczerepko.tetris.controller;

import io.github.sczerepko.tetris.model.Board;
import io.github.sczerepko.tetris.model.Translation;
import io.github.sczerepko.tetris.model.pieces.CurrentPiece;
import io.github.sczerepko.tetris.model.pieces.RandomPieceGenerator;

public class GameController implements InputListener {

    private static final int BOARD_WIDTH = 10;
    private static final int BOARD_HEIGHT = 20;

    private GuiController guiController;
    private RandomPieceGenerator randomPieceGenerator;
    private Board board;

    public GameController(GuiController guiController) {
        this.guiController = guiController;
        randomPieceGenerator = new RandomPieceGenerator();
        board = new Board(BOARD_WIDTH, BOARD_HEIGHT);
        board.setCurrentPiece(randomPieceGenerator.generate());
        guiController.setInputListener(this);
        guiController.initializeView(board);
        guiController.bindScore(board.scoreProperty());
        guiController.renderNextPiecePane(randomPieceGenerator.peek());
    }

    @Override
    public void startNewGame() {
        board = new Board(BOARD_WIDTH, BOARD_HEIGHT);
        board.setCurrentPiece(randomPieceGenerator.generate());
        guiController.bindScore(board.scoreProperty());
        board.scoreProperty().setValue(0);
        guiController.refreshGameBackground(board.getBoardMatrix());
    }

    @Override
    public CurrentPiece onDownEvent() {
        if (!board.canPieceMove(Translation.DOWN)) {
            board.handlePieceEmbed();
            CurrentPiece nextPiece = randomPieceGenerator.generate();
            if (board.isPieceBlocked(nextPiece)) {
                guiController.gameOver();
            }
            board.setCurrentPiece(nextPiece);
            guiController.refreshGameBackground(board.getBoardMatrix());
            guiController.renderNextPiecePane(randomPieceGenerator.peek());
            return board.getCurrentPiece();
        } else {
            return board.moveCurrentPiece(Translation.DOWN);
        }
    }

    @Override
    public CurrentPiece onRightEvent() {
        return board.moveCurrentPiece(Translation.RIGHT);
    }

    @Override
    public CurrentPiece onLeftEvent() {
        return board.moveCurrentPiece(Translation.LEFT);
    }

    @Override
    public CurrentPiece onRotateEvent() {
        return board.rotateCurrentPiece();
    }

}
