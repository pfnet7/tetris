package io.github.sczerepko.tetris;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import io.github.sczerepko.tetris.controller.GuiController;
import io.github.sczerepko.tetris.controller.GameController;

public class Main extends Application {

    public static final int SCENE_WIDTH = 500;
    public static final int SCENE_HEIGHT = 620;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        Parent root = fxmlLoader.load();
        GuiController guiController = fxmlLoader.getController();

        primaryStage.setTitle("Tetris game");
        primaryStage.setScene(new Scene(root, SCENE_WIDTH, SCENE_HEIGHT));
        primaryStage.show();
        new GameController(guiController);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
