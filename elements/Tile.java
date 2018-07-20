package elements;

import ships.*;

public class Tile {
    private Ship inTile;
    
    public Tile(Ship inTile){
        this.inTile = inTile;
    }
    
    public boolean hit() {
        return this.inTile.hit();
    }
    
}