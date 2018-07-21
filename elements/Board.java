package elements;

import ships.*;

public class Board {
    Tile[][] board = new Tile [10][10];
    private int shipsAlive = 7;
    
    public Board() {
        initializeBoard();
    }
    
    private void initializeBoard() {                     // enche o tabuleiro de agua
        for(int i = 0 ; i < board.length ; i++) {
            for(int j = 0 ; j < board[0].length ; j++) {
                board[i][j] = new Tile (new Water(j, i));
            }
        }
    }
    
    public void hit(int posX, int posY) {
        boolean destroyed = board[posY][posX].hit();
        if(destroyed) {
            shipsAlive--;
        }
    }
    
    public boolean isAlive () {
        return shipsAlive > 0;
    }
    
    public void addShip (Ship ship) { // talvez deva lancar excecao
        int x = ship.getPosX();
        int y = ship.getPosY();
        int size = ship.getSize();
        boolean vertical = ship.getOrientation();
        
        if(vertical) {
            addShipVertical(x, y, ship, size);
        } else {
            addShipHorizontal(x, y, ship, size);
        }
    }
    
    private void addShipVertical(int x, int y, Ship ship, int size) {
        for(int i = y ; i < (y+size) ; i++) {
            board[i][x].addShip(ship);
        }
    }

    private void addShipHorizontal(int x, int y, Ship ship, int size) {
        for(int i = x ; i < (x+size) ; i++) {
            board[y][i].addShip(ship);
        }
    }
    
}