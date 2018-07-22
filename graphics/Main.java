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
       /* JFrame window = new JFrame();
        window.setSize(1366,768);
        window.setTitle("Battleship");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        
        DrawingComponent DC = new DrawingComponent();
        window.add(DC);
         
        
        
        JButton[][] tabuleiro = new JButton[10][10];
        
        for(int i = 0 ; i < 10 ; i++) {
            for(int j = 0 ; j < 10 ; j++) {
                tabuleiro[i][j] = new JButton (i +","+j);
                tabuleiro[i][j].setBounds(50*(i+1), 50*(j+1), 50, 50);
                window.add(tabuleiro[i][j]);
            }
        } */
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
