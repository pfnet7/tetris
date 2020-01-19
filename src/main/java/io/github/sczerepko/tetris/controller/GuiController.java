package io.github.sczerepko.tetris.controller;

import io.github.sczerepko.tetris.model.Board;
import io.github.sczerepko.tetris.model.pieces.CurrentPiece;
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

public class GuiController implements Initializable {

    private static final int PIECE_SIZE = 30;

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
    public BorderPane pausePane;

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
                    refresh(inputListener.onDownEvent());
                    event.consume();
                    break;
                case RIGHT:
                    refresh(inputListener.onRightEvent());
                    event.consume();
                    break;
                case LEFT:
                    refresh(inputListener.onLeftEvent());
                    event.consume();
                    break;
                case UP:
                    refresh(inputListener.onRotateEvent());
                    event.consume();
                    break;
                case P:
                    pause.setValue(!pause.getValue());
                    break;
                case N:
                    startNewGame();
                    break;
            }
        });
    }

    public void initializeView(Board board) {
        CurrentPiece piece = board.getCurrentPiece();
        initGridPaneBackingMatrix(board);

        Color[][] pieceMatrix = piece.getPieceMatrix();
        initBrickPanelBackingMatrix(pieceMatrix);

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

    private void initGridPaneBackingMatrix(Board board) {
        Color[][] boardMatrix = board.getBoardMatrix();
        gridPaneBackingMatrix = new Rectangle[boardMatrix.length][boardMatrix[0].length];
        for (int i = 0; i < boardMatrix.length; i++) {
            for (int j = 0; j < boardMatrix[i].length; j++) {
                Rectangle rectangle = new Rectangle(PIECE_SIZE, PIECE_SIZE);
                rectangle.setFill(Color.TRANSPARENT);
                gridPaneBackingMatrix[i][j] = rectangle;
                gridPane.add(rectangle, i, j);
            }
        }
    }

    private void initBrickPanelBackingMatrix(Color[][] pieceMatrix) {
        brickPanelBackingMatrix = new Rectangle[pieceMatrix.length][pieceMatrix[0].length];
        for (int i = 0; i < pieceMatrix.length; i++) {
            for (int j = 0; j < pieceMatrix[i].length; j++) {
                Rectangle rectangle = new Rectangle(PIECE_SIZE, PIECE_SIZE);
                setRectangleStyle(rectangle, pieceMatrix[i][j]);
                brickPanelBackingMatrix[i][j] = rectangle;
                brickPanel.add(rectangle, i, j);
            }
        }
    }

    private void moveBrickPanel(CurrentPiece piece) {
        brickPanel.setLayoutX(gridPane.getLayoutX() + piece.getX() * (brickPanel.getVgap() + PIECE_SIZE));
        brickPanel.setLayoutY(gridPane.getLayoutY() + piece.getY() * (brickPanel.getHgap() + PIECE_SIZE));
    }

    private void moveCurrentPieceDown() {
        refresh(inputListener.onDownEvent());
        gridPane.requestFocus();
    }

    private void refresh(CurrentPiece piece) {
        moveBrickPanel(piece);
        Color[][] pieceMatrix = piece.getPieceMatrix();
        for (int i = 0; i < pieceMatrix.length; i++) {
            for (int j = 0; j < pieceMatrix[i].length; j++) {
                setRectangleStyle(brickPanelBackingMatrix[i][j], pieceMatrix[i][j]);
            }
        }
    }

    private void setRectangleStyle(Rectangle rectangle, Color color) {
        rectangle.setFill(color == null ? Color.TRANSPARENT : color);
        rectangle.setArcHeight(10);
        rectangle.setArcWidth(10);
    }

    public void bindScore(IntegerProperty scoreProperty) {
        score.textProperty().bind(scoreProperty.asString());
    }

    public void setInputListener(InputListener inputListener) {
        this.inputListener = inputListener;
    }

    public void refreshGameBackground(Color[][] boardMatrix) {
        for (int i = 0; i < boardMatrix.length; i++) {
            for (int j = 0; j < boardMatrix[i].length; j++) {
                setRectangleStyle(gridPaneBackingMatrix[i][j], boardMatrix[i][j]);
            }
        }
    }

    public void gameOver() {
        gameOver.setValue(true);
        timeline.stop();
        gameOverPane.setVisible(true);
    }

    public void startNewGame() {
        gameOver.setValue(false);
        pause.setValue(false);
        gameOverPane.setVisible(false);
        inputListener.startNewGame();
        gridPane.requestFocus();
        timeline.play();
    }

    public void renderNextPiecePane(CurrentPiece nextPiece) {
        nextPiecePane.getChildren().clear();
        Color[][] pieceMatrix = nextPiece.getPieceMatrix();
        for (int i = 0; i < pieceMatrix.length; i++) {
            for (int j = 0; j < pieceMatrix[i].length; j++) {
                Rectangle rectangle = new Rectangle(PIECE_SIZE, PIECE_SIZE);
                setRectangleStyle(rectangle, pieceMatrix[i][j]);
                if (pieceMatrix[i][j] != null) {
                    nextPiecePane.add(rectangle, j, i);
                }
            }
        }
    }

}
