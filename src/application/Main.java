package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Main extends Application      
{ 
  StackPane root = new StackPane();
  Stage mainStage = new Stage();
  Scene scene = new Scene(root,420,620);
  @Override
  public void start(Stage primaryStage) {
   // Button button = new Button();
   // mainStage.show();
    
   
    GameScene gameScene = new GameScene();
    gameScene.sceneCreator();
    
   // root.getChildren().addAll();
  } 
  public static void main(String[] args) {
    launch(args);
  }
}