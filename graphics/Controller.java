package graphics;

import elements.*;

public class Controller {
    private Player player1, player2;
    private boolean isP2Human;
    private int gameState; // 0 = P1 posicionando navios, 1 = P2 posicionando navios
                           // 2 = P1 jogando, 3 = P2 jogando
    public Controller(boolean isP2Human) {
        AlertBoxes.text("","","");
        player1 = new Player(true, 1, AlertBoxes.text("Seu nome", "Jogador 1", "Digite seu nome"));
        if(isP2Human) {
            player2 = new Player(isP2Human, 2, AlertBoxes.text("Seu nome", "Jogador 2", "Digite seu nome"));
        } else {
            player2 = new Player(isP2Human, 2, "BOT");
        }
        this.isP2Human = isP2Human;
        this.gameState = 0;
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
