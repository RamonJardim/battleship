package elements;

public class Player {
    boolean human;
    boolean inTurn;
    boolean alive;
    Board playerBoard;
    
    public Player(boolean human) {
        this.human = human;
        this.playerBoard = new Board();
    }
    
    public boolean isAlive() {
        return alive;
    }
    
    public boolean shot(int posX, int posY) { // retorna true se acertou uma embarcacao
        boolean hitShip = playerBoard.hit(posX, posY);
        updateAlive();
        return hitShip;
    }
    
    public boolean isDestroyed(int posX, int posY) {
        return playerBoard.isDestroyed(posX, posY);
    }
        
    private void updateAlive() {
        alive = playerBoard.isAlive();
    }
    
    
}