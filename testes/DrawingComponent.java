package testes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import elements.*;
import ships.*;


public class DrawingComponent extends JComponent {
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        
        Rectangle[][] tabuleiro = new Rectangle[10][10];
        
        for(int i = 0 ; i < 10 ; i++) {
            for(int j = 0 ; j < 10 ; j++) {
                tabuleiro[i][j] = new Rectangle(50*(i+1), 50*(j+1), 50, 50);
                g2.draw(tabuleiro[i][j]);
            }
        }
    }
}