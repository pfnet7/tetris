package io.github.sczerepko.tetris.controller;

import io.github.sczerepko.tetris.model.Board;
import io.github.sczerepko.tetris.model.pieces.Piece;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.BiConsumer;

/**
 * The Gui Controller that is in charge of updating the fxml gui.
 *
 * @see Initializable
 */
public class GuiController implements Initializable {

    private static final int RECTANGLE_SIZE = 30;

    @FXML
    private GridPane brickPanel;
    @FXML
    private GridPane gridPane;
    @FXML
    private Text score;
    @FXML
    private GridPane nextPiecePane;
    @FXML
    private BorderPane gameOverPane;
    @FXML
    private BorderPane pausePane;

    private Rectangle[][] brickPanelBackingMatrix;
    private Rectangle[][] gridPaneBackingMatrix;
    private InputListener inputListener;
    private Timeline timeline;

    private final BooleanProperty pause = new SimpleBooleanProperty();
    private final BooleanProperty gameOver = new SimpleBooleanProperty();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gridPane.requestFocus();
        gridPane.setOnKeyPressed(event -> {
            if (gameOver.getValue() == Boolean.TRUE && event.getCode() != KeyCode.N) {
                return;
            }
            if (pause.getValue() == Boolean.TRUE && event.getCode() != KeyCode.P) {
                return;
            }
            switch (event.getCode()) {
                case DOWN:
                    inputListener.onDownEvent();
                    event.consume();
                    break;
                case RIGHT:
                    inputListener.onRightEvent();
                    event.consume();
                    break;
                case LEFT:
                    inputListener.onLeftEvent();
                    event.consume();
                    break;
                case UP:
                    inputListener.onRotateEvent();
                    event.consume();
                    break;
                case P:
                    pause.setValue(!pause.getValue());
                    break;
                case N:
                    inputListener.startNewGame();
                    break;
            }
        });
    }

    /**
     * Initializes the gridPane and brickPanel backing matrices, and timeline that executes every given interval.
     *
     * @param board the board
     */
    public void initializeView(Board board) {
        Color[][] boardMatrix = board.getBoardMatrix();
        gridPaneBackingMatrix = new Rectangle[boardMatrix.length][boardMatrix[0].length];
        initGridPaneBackingMatrix(gridPane, gridPaneBackingMatrix, boardMatrix, (rectangle, _ignored) -> rectangle.setFill(Color.TRANSPARENT));

        Piece piece = board.getCurrentPiece();
        Color[][] pieceMatrix = piece.getPieceMatrix();
        brickPanelBackingMatrix = new Rectangle[pieceMatrix.length][pieceMatrix[0].length];
        initGridPaneBackingMatrix(brickPanel, brickPanelBackingMatrix, pieceMatrix, this::setRectangleStyle);

        moveBrickPanel(piece);

        timeline = new Timeline(new KeyFrame(
                Duration.millis(500),
                event -> moveCurrentPieceDown()
        ));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        pause.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                timeline.stop();
                pausePane.setVisible(true);
            } else {
                timeline.play();
                pausePane.setVisible(false);
            }
        });
    }

    private void initGridPaneBackingMatrix(GridPane gridPane, Rectangle[][] backingMatrix, Color[][] sourceMatrix,
                                           BiConsumer<Rectangle, Color> rectangleStyler) {
        for (int i = 0; i < sourceMatrix.length; i++) {
            for (int j = 0; j < sourceMatrix[i].length; j++) {
                Rectangle rectangle = new Rectangle(RECTANGLE_SIZE, RECTANGLE_SIZE);
                rectangleStyler.accept(rectangle, sourceMatrix[i][j]);
                backingMatrix[i][j] = rectangle;
                gridPane.add(rectangle, i, j);
            }
        }
    }

    private void setRectangleStyle(Rectangle rectangle, Color color) {
        rectangle.setFill(color == null ? Color.TRANSPARENT : color);
        rectangle.setArcHeight(10);
        rectangle.setArcWidth(10);
    }

    private void moveBrickPanel(Piece piece) {
        brickPanel.setLayoutX(gridPane.getLayoutX() + piece.getX() * (brickPanel.getVgap() + RECTANGLE_SIZE));
        brickPanel.setLayoutY(gridPane.getLayoutY() + piece.getY() * (brickPanel.getHgap() + RECTANGLE_SIZE));
    }

    private void moveCurrentPieceDown() {
        inputListener.onDownEvent();
        gridPane.requestFocus();
    }

    /**
     * Refreshes the styling of the view based on the given piece.
     *
     * @param piece the piece
     */
    public void refresh(Piece piece) {
        moveBrickPanel(piece);
        Color[][] pieceMatrix = piece.getPieceMatrix();
        for (int i = 0; i < pieceMatrix.length; i++) {
            for (int j = 0; j < pieceMatrix[i].length; j++) {
                setRectangleStyle(brickPanelBackingMatrix[i][j], pieceMatrix[i][j]);
            }
        }
    }

    /**
     * Binds score to the given property.
     *
     * @param scoreProperty the property to bind
     */
    public void bindScore(IntegerProperty scoreProperty) {
        score.textProperty().bind(scoreProperty.asString());
    }

    public void setInputListener(InputListener inputListener) {
        this.inputListener = inputListener;
    }

    /**
     * Refreshes the game background styling based on the given board matrix.
     *
     * @param boardMatrix the board matrix
     */
    public void refreshGameBackground(Color[][] boardMatrix) {
        for (int i = 0; i < boardMatrix.length; i++) {
            for (int j = 0; j < boardMatrix[i].length; j++) {
                setRectangleStyle(gridPaneBackingMatrix[i][j], boardMatrix[i][j]);
            }
        }
    }

    /**
     * Executes the game over procedure. It stops the timeline and shows the game over panel.
     */
    public void gameOver() {
        gameOver.setValue(true);
        timeline.stop();
        gameOverPane.setVisible(true);
    }

    /**
     * Starts new game. It hides the game over panel, request focus and starts timeline.
     */
    public void startNewGame() {
        gameOver.setValue(false);
        pause.setValue(false);
        gameOverPane.setVisible(false);
        gridPane.requestFocus();
        timeline.play();
    }

    /**
     * Renders next piece pane.
     *
     * @param nextPiece the next piece
     */
    public void renderNextPiecePane(Piece nextPiece) {
        nextPiecePane.getChildren().clear();
        Color[][] pieceMatrix = nextPiece.getPieceMatrix();
        for (int i = 0; i < pieceMatrix.length; i++) {
            for (int j = 0; j < pieceMatrix[i].length; j++) {
                Rectangle rectangle = new Rectangle(RECTANGLE_SIZE, RECTANGLE_SIZE);
                setRectangleStyle(rectangle, pieceMatrix[i][j]);
                if (pieceMatrix[i][j] != null) {
                    nextPiecePane.add(rectangle, i, j);
                }
            }
        }
    }

}
