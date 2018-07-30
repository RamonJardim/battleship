package elements;

import ships.*;

class Board {
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
    
    public boolean hit(int posX, int posY) {
        Tile tile = board[posY][posX];
        boolean isShip = tile.hit();
        if(isShip) {
            if(tile.isDestroyed()){
                shipsAlive--;
            }
        }
        return isShip;
    }
    
    boolean isAlive () {
        return shipsAlive > 0;
    }
    
    private boolean verifySpaces(Ship ship) {
        int x = ship.getPosX();
        int y = ship.getPosY();
        int size = ship.getSize();
        boolean vertical = ship.getOrientation();
        
        if(vertical){
            for (int i = y; i < (y+size); i++) {
                if(!(board[i][x].getShip() instanceof Water)){
                    return false;
                }
            }
        } else {
            for (int i = x; i < (x+size); i++) {
                if(!(board[y][i].getShip() instanceof Water)){
                    return false;
                }
            }
        }
        
        return true;
        
    }
    
    private boolean verifyAdd(Ship ship) {
        int x = ship.getPosX();
        int y = ship.getPosY();
        int size = ship.getSize();
        boolean vertical = ship.getOrientation();
        
        if(vertical) {
            if((y + size) - 1 < 10) {
                return verifySpaces(ship);
            } else {
                return false;
            }
        } else {
            if((x + size) - 1 < 10) {
                return verifySpaces(ship);
            } else {
                return false;
            }
        }
    }
    
    public boolean addShip (Ship ship) { // talvez deva lancar excecao
        int x = ship.getPosX();
        int y = ship.getPosY();
        int size = ship.getSize();
        boolean vertical = ship.getOrientation();
        
        if(vertical && verifyAdd(ship)) {
            addShipVertical(x, y, ship, size);
            return true;
        } else if(verifyAdd(ship)){
            addShipHorizontal(x, y, ship, size);
            return true;
        }
        return false;
    }
    
    public boolean isDestroyed(int posX, int posY) {
        return board[posY][posX].isDestroyed();
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