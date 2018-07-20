public abstract class Ship {
    String type;
    int size;
    int destroyedBlocks = 0;
    boolean destroyed = false;
    
    public Ship(String type, int size) {
        this.size = size;
        this.type = type.clone();
    }
    
    public void hit(){
        destroyedBlocks++;
        if(destroyedBlocks >= size) {
            destroyed = true;
        }
    }    
    
}