package ships;

public class Water extends Ship{
    
    public Water(int posX, int posY){
        super("Water", 1, posX, posY, false);
    }
    
    
    @Override
    public boolean hit() {
        return false;
    }
}