package elements;

import ships.*;

public class Player {
    private boolean human;
    private boolean inTurn;
    private boolean alive;
    private int shipAdding = 1; // 1 = Submarine, .. 5 = carrier
    private boolean orientationAdding = true; // n sei pq, mas se comecar com falso buga
    private Board playerBoard = new Board();
    
    private int nSubmarinesToAdd = 2;
    private int nDestroyersToAdd = 2;
    private int nCruisersToAdd = 1;
    private int nBattleshipsToAdd = 1;
    private int nCarriersToAdd = 1;
    private int nShipsToAddToAdd = 7;

    public int getnShipsToAddToAdd() {
        return nShipsToAddToAdd;
    }
    
    public Player(boolean human, int playerNumber) {
        this.human = human;
        this.playerBoard = new Board();
    }
    
    public boolean isAlive() {
        return alive;
    }
    
    public int getShipAdding() {
        return this.shipAdding;
    }
    
    public void increaseShipAdding(){
        this.shipAdding++;
    }
    
    public boolean getOrientationAdding(){
        return this.orientationAdding;
    }
    
    public String getTextOrientation(){
        return orientationAdding ? "Horizontal":"Vertical";
    }
    
    public void changeOrientationAdding() {
        orientationAdding = !orientationAdding;
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
    
    public boolean addShip(Ship ship) {
        
        boolean isOk = playerBoard.addShip(ship);
        if(isOk){
            if(ship instanceof Submarine) {
                nSubmarinesToAdd--;
            }

            if(ship instanceof Destroyer) {
                nDestroyersToAdd--;
            }

            if(ship instanceof Cruiser) {
                nCruisersToAdd--;
            }

            if(ship instanceof Battleship) {
                nBattleshipsToAdd--;
            }

            if(ship instanceof Carrier) {
                nCarriersToAdd--;
            }

            nShipsToAddToAdd--;
        }
        return isOk;
    }
    
    public int getShipsToAdd(Ship ship) {
        if(ship instanceof Submarine) {
            return nSubmarinesToAdd;
        }
        
        if(ship instanceof Destroyer) {
            return nDestroyersToAdd;
        }
        
        if(ship instanceof Cruiser) {
            return nCruisersToAdd;
        }
        
        if(ship instanceof Battleship) {
            return nBattleshipsToAdd;
        }
        
        if(ship instanceof Carrier) {
            return nCarriersToAdd;
        }
        
        return 0;
    }

    public String getTextShipAdding() {
        int k = shipAdding % 5;
        if(nShipsToAddToAdd > 0) {
            switch(k) {
                case 1:
                    if(nSubmarinesToAdd > 0) {
                        return "Submarine (1)";
                    } else {
                        shipAdding++;
                        return getTextShipAdding();
                    }
                case 2:
                    if(nDestroyersToAdd > 0) {
                        return "Destroyer (2)";
                    } else {
                        shipAdding++;
                        return getTextShipAdding();
                    }

                case 3:
                    if(nCruisersToAdd > 0) {
                        return "Cruiser (3)";
                    } else {
                        shipAdding++;
                        return getTextShipAdding();
                    }
                case 4:
                    if(nBattleshipsToAdd > 0) {
                        return "Battleship (4)";
                    } else {
                        shipAdding++;
                        return getTextShipAdding();
                    }
                case 0:
                    if(nCarriersToAdd > 0) {
                        return "Carrier (5)";
                    } else {
                        shipAdding++;
                        return getTextShipAdding();
                    }
            }
        }
        return "";
    }
    
    
}