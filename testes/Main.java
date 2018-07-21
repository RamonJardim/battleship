package testes;

import javafx.application.Application;
import javax.swing.JFrame;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javafx.stage.Stage;

import elements.*;
import ships.*;

public class Main extends Application{
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
    public void start(Stage window) {
        window.setTitle("HEY");
        System.out.println("HEY");
        window.show();
    }

}
