package ships;

public abstract class Ship {
    private String type;
    private int size;
    private int destroyedBlocks = 0;
    private boolean destroyed = false;
    private int posX, posY; // Posicao no tabuleiro
    
    public Ship(String type, int size, int posX, int posY) {
        this.size = size;
        this.type = type;
        this.posX = posX;
        this.posY = posY;
    }
    
    public boolean hit(){ // Retorna o estado do navio (true = destruido)
        destroyedBlocks++;
        if(destroyedBlocks >= size) {
            destroyed = true;
        }
        return destroyed;
    }    
    
}