package graphics;

import elements.*;

public class Controller {
    private Player player1, player2;
    private boolean isP2Human;
    private int gameState; // 0 = P1 posicionando navios, 1 = P2 posicionando navios
                           // 2 = P1 jogando, 3 = P2 jogando
    public Controller(boolean isP2Human) {
        player1 = new Player(true, 1);
        player2 = new Player(isP2Human, 2);
        this.isP2Human = isP2Human;
    }
    
    public void setGameState(int gameState) {
        this.gameState = gameState;
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
