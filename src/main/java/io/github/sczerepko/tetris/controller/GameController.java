package io.github.sczerepko.tetris.controller;

import io.github.sczerepko.tetris.model.Board;
import io.github.sczerepko.tetris.model.Translation;
import io.github.sczerepko.tetris.model.pieces.Piece;
import io.github.sczerepko.tetris.model.pieces.RandomPieceGenerator;

/**
 * Game Controller that is in charge of orchestrating the game flow.
 *
 * @see InputListener
 */
public class GameController implements InputListener {

    private GuiController guiController;
    private RandomPieceGenerator randomPieceGenerator;
    private Board board;

    /**
     * Instantiates a new Game controller.
     * In the process it initializes the view using the guiController.
     *
     * @param guiController        the gui controller
     * @param board                the board
     * @param randomPieceGenerator the piece generator that provides new pieces
     */
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
        guiController.startNewGame();
    }

    @Override
    public void onDownEvent() {
        if (board.canPieceMove(Translation.DOWN)) {
            Piece currentPiece = board.moveCurrentPiece(Translation.DOWN);
            guiController.refresh(currentPiece);
            return;
        }
        board.handlePieceEmbed();
        Piece nextPiece = randomPieceGenerator.generate();
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
        Piece currentPiece = board.moveCurrentPiece(Translation.RIGHT);
        guiController.refresh(currentPiece);
    }

    @Override
    public void onLeftEvent() {
        Piece currentPiece = board.moveCurrentPiece(Translation.LEFT);
        guiController.refresh(currentPiece);
    }

    @Override
    public void onRotateEvent() {
        Piece currentPiece = board.rotateCurrentPiece();
        guiController.refresh(currentPiece);
    }

}
