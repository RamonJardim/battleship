package elements;

import ships.*;

public class Tile {
    private boolean hit = false;
    private Ship shipInTile;
    
    public Tile(Ship ship){
        addShip(ship);
    }
    
    boolean hit() {
        this.hit = true;
        return this.shipInTile.hit();
    }
    
    void addShip(Ship ship) {
        this.shipInTile = ship;
    }
    
}