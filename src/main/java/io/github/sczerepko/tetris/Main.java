package io.github.sczerepko.tetris;

import io.github.sczerepko.tetris.controller.GameController;
import io.github.sczerepko.tetris.controller.GuiController;
import io.github.sczerepko.tetris.model.Board;
import io.github.sczerepko.tetris.model.pieces.RandomPieceGenerator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int SCENE_WIDTH = 500;
    private static final int SCENE_HEIGHT = 620;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main.fxml"));
        Parent root = fxmlLoader.load();
        GuiController guiController = fxmlLoader.getController();

        primaryStage.setTitle("Tetris game");
        primaryStage.setScene(new Scene(root, SCENE_WIDTH, SCENE_HEIGHT));
        primaryStage.show();
        new GameController(guiController, new Board(), new RandomPieceGenerator());
    }

}
