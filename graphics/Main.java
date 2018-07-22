package graphics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.Rectangle;

import elements.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import ships.*;

public class Main extends Application{
    
    Button[][] table = new Button[10][10];
    
    public static void main(String[] args) {
<<<<<<< HEAD
        
=======

>>>>>>> 6b38c4542d3d7c8cf00e0bdda76b0b816751ea24
        launch(args);
        
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Battleship");
        GridPane gridPane = new GridPane();
        
        for (int j = 0; j < table.length; j++) {
            for (int i = 0; i < table[0].length; i++) {
                table[i][j] = new Button (j +","+i);
                table[i][j].setPrefSize(35, 35);
                GridPane.setConstraints(table[i][j], i, j);
                gridPane.getChildren().add(table[i][j]);
            }
        }
        
        Scene scene = new Scene(gridPane, 750,350);
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }

}
