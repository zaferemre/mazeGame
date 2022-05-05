package application;

import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import java.util.Random;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import java.lang.Thread;

public class GameScene{
  Random rand = new Random();
  int startX= rand.nextInt(40) + 1;
  int startY = rand.nextInt(40) + 1;
  int targetX = rand.nextInt(40) + 1;
  int targetY = rand.nextInt(40) + 1;

  int level = 1;
  int color = 80;
  Rectangle[][] gameBoard = new Rectangle[42][42];
  int[][] playBoard = new int[42][42];
  
  public void sceneCreator(){
    TilePane root = new TilePane();
    Scene scene = new Scene(root,840,840);
    Stage stage = new Stage();
    root.setHgap(1);
    root.setVgap(1);
    
    for(int i = 0 ; i < 42 ; i++){
      for(int j = 0 ; j < 42 ; j++){
      Rectangle rectangle = new Rectangle();
      rectangle.setHeight(19);
      rectangle.setWidth(19);
      rectangle.setFill(Color.LIGHTGRAY);
      gameBoard[i][j] = rectangle;
      playBoard[i][j] = 0;  
        
        if(i == 0 || i == 41 || j == 0 || j ==41) {
          rectangle.setFill(Color.DARKGOLDENROD);
          gameBoard[i][j] = rectangle;
          playBoard[i][j] = -1;
          }   
        
      root.getChildren().add(rectangle);
        }
      }
    scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
          if(event.getX() < 820 && event.getX() >20 && event.getY() < 820 && event.getY() >20){
          double i = event.getY() / 20;
          double j = event.getX() / 20;
          gameBoard[(int) i ][(int)j].setFill(Color.BLACK);
          playBoard[(int) i][(int) j] = -1;
        }
      }
    });
scene.setOnKeyPressed(e -> {
    if (e.getCode() == KeyCode.Q) {
     // Main restart = new Main();
    }
    else  searchTarget();
      
    });
    
    startEnd();
  //  searchTarget();
    stage.setScene(scene);
    stage.show();
  }

  public void startEnd(){

    playBoard[startX][startY] = 1;
    gameBoard[startX][startY].setFill(Color.GREENYELLOW);
    System.out.println(startX);
    System.out.println(startY);
    playBoard[targetX][targetY] = -2;
    gameBoard[targetX][targetY].setFill(Color.RED);
    
  }
  public void searchTarget(){
   if(playBoard[targetX][targetY]==-2){
    for(int i = 1 ; i<40 ; i++){
      for(int j = 1 ; j<40 ; j++){
        if(playBoard[i][j] == level){
          //System.out.println(i);
          //System.out.println(j);
          for(int k=-1 ; k<=1 ; k+=2){
          if(playBoard[i+k][j] <= 0 && playBoard[i+k][j] != -1)playBoard[i+k][j] = level+1;
          if(playBoard[i][j+k] <=0 &&playBoard[i][j+k] != -1)playBoard[i][j+k] = level+1;
          searchPainter(i,j);
            }
        }
      }
    }
      level++;
    if(color <=250) color+=2;
    
    }
    else {
      int steps = playBoard[targetX][targetY];
      for(int i = -1 ; i<=1 ; i+=2){
        if(playBoard[targetX+i][targetY] == steps-1 /* && targetX != startX */){
          gameBoard[targetX+i][targetY].setFill(Color.PURPLE);
          targetX += i;
        }
        else if(playBoard[targetX][targetY+i] == steps-1 /* && targetY!=startY */){
          gameBoard[targetX][targetY+i].setFill(Color.PURPLE);
          targetY += i;
      }
    }
  }
}

  public void searchPainter(int i,int j){
    if(i==1) i++;
    if(j==1) j++;
    for(int k = -1 ; k<=1 ; k+=1){
      for(int l =-1 ; l<=1 ; l+=1){
        if(playBoard[i+k][j+l]!=-1){
        gameBoard[i+k][j+l].setFill(Color.rgb(27, color, 114));
        
        if(k!=0 && l!= 0) gameBoard[i+k][j+l].setFill(Color.rgb(27, color-70, 114));
          }
      }
    }
  }
  //waiter wait(ms)
  public static void wait(int ms)
{
    try
    {
        Thread.sleep(ms);
    }
    catch(InterruptedException ex)
    {
        Thread.currentThread().interrupt();
    }
}
}