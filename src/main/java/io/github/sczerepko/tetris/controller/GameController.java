package io.github.sczerepko.tetris.controller;

import io.github.sczerepko.tetris.model.Board;
import io.github.sczerepko.tetris.model.Translation;
import io.github.sczerepko.tetris.model.pieces.CurrentPiece;
import io.github.sczerepko.tetris.model.pieces.RandomPieceGenerator;

public class GameController implements InputListener {

    private GuiController guiController;
    private RandomPieceGenerator randomPieceGenerator;
    private Board board;

    public GameController(GuiController guiController, Board board, RandomPieceGenerator randomPieceGenerator) {
        this.guiController = guiController;
        this.randomPieceGenerator = randomPieceGenerator;
        this.board = board;
        board.setCurrentPiece(randomPieceGenerator.generate());
        guiController.setInputListener(this);
        guiController.initializeView(board);
        guiController.bindScore(board.scoreProperty());
        guiController.renderNextPiecePane(randomPieceGenerator.peek());
    }

    @Override
    public void startNewGame() {
        board = new Board();
        board.setCurrentPiece(randomPieceGenerator.generate());
        guiController.bindScore(board.scoreProperty());
        board.scoreProperty().setValue(0);
        guiController.refreshGameBackground(board.getBoardMatrix());
    }

    @Override
    public void onDownEvent() {
        if (board.canPieceMove(Translation.DOWN)) {
            CurrentPiece currentPiece = board.moveCurrentPiece(Translation.DOWN);
            guiController.refresh(currentPiece);
            return;
        }
        board.handlePieceEmbed();
        CurrentPiece nextPiece = randomPieceGenerator.generate();
        if (board.isPieceBlocked(nextPiece)) {
            guiController.gameOver();
        }
        board.setCurrentPiece(nextPiece);
        guiController.refreshGameBackground(board.getBoardMatrix());
        guiController.renderNextPiecePane(randomPieceGenerator.peek());
        guiController.refresh(board.getCurrentPiece());
    }

    @Override
    public void onRightEvent() {
        CurrentPiece currentPiece = board.moveCurrentPiece(Translation.RIGHT);
        guiController.refresh(currentPiece);
    }

    @Override
    public void onLeftEvent() {
        CurrentPiece currentPiece = board.moveCurrentPiece(Translation.LEFT);
        guiController.refresh(currentPiece);
    }

    @Override
    public void onRotateEvent() {
        CurrentPiece currentPiece = board.rotateCurrentPiece();
        guiController.refresh(currentPiece);
    }

}
