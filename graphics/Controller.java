package graphics;

import elements.*;
import javafx.scene.control.Button;

public class Controller {
    private Player player1, player2;
    private boolean isP2Human;
    private int gameState; // 0 = P1 posicionando navios, 1 = P2 posicionando navios
                           // 2 = P1 jogando, 3 = P2 jogando
    private Button[][] P1Place, P1Shot, P2Place, P2Shot;
    
    public Controller(boolean isP2Human) {
        AlertBoxes.text("","","");
        P1Place = new Button [10][10];
        P1Shot  = new Button [10][10];
        P2Place = new Button [10][10];
        P2Shot  = new Button [10][10];
        
        setGrid(P1Place);
        setGrid(P1Shot);
        setGrid(P2Place);
        setGrid(P2Shot);
        
        player1 = new PlayerHuman(AlertBoxes.text("Seu nome", "Jogador 1", "Digite seu nome"), 1);
        if(isP2Human) {
            player2 = new PlayerHuman(AlertBoxes.text("Seu nome", "Jogador 2", "Digite seu nome"), 2);
        } else {
            player2 = new PlayerIA(P2Place, P2Shot);
        }
        this.isP2Human = isP2Human;
        this.gameState = 0;
    }
    
    public Button[][] getPlaceGrid(int choice){
        if(choice == 1) {
            return P1Place;
        } else {
            return P2Place;
        }
    }
    
    public Button[][] getShotGrid(int choice){
        if(choice == 1) {
            return P1Shot;
        } else {
            return P2Shot;
        }
    }
    
    private void setGrid(Button[][] table){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                table[i][j] = new Button(j + "," + i);
            }
        }
    }
    
    public void setGameState(int gameState) {
        this.gameState = gameState;
    }
    
    public void increaseGameState() {
        this.gameState++;
    }
    
    public int getGameState() {
        return this.gameState;
    }
    
    public Player getPlayer1() {
        return this.player1;
    }
    
    public Player getPlayer2() {
        return this.player2;
    }
}
