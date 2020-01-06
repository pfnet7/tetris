package io.github.sczerepko.tetris.controller;

import io.github.sczerepko.tetris.model.Board;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class GuiController implements Initializable {

    private static final int PIECE_SIZE = 30;

    @FXML
    private GridPane gridPane;
    @FXML
    private Text score;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gridPane.requestFocus();
        gridPane.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                case RIGHT:
                case DOWN:
                case LEFT:
            }
        });
    }

    void initializeBoardGrid(Board board) {
        for (int i = 0; i < board.getHeight(); i++) {
            gridPane.addRow(i, createGridRow(board.getWidth()));
        }
    }

    private Node[] createGridRow(int length) {
        return Stream.generate(() -> new Rectangle(PIECE_SIZE, PIECE_SIZE, Color.WHITE))
                     .limit(length)
                     .toArray(Node[]::new);
    }
}
