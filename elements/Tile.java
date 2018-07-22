package elements;

import ships.*;

class Tile {
    private boolean hit = false;
    private Ship shipInTile;
    
    Tile(Ship ship){
        addShip(ship);
    }
    
    boolean hit() {
        this.hit = true;
        return this.shipInTile.hit();
    }
    
    void addShip(Ship ship) {
        this.shipInTile = ship;
    }
    
    boolean isDestroyed(){
        return shipInTile.getDestroyed();
    }
}