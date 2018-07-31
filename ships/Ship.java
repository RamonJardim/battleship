package ships;

import elements.Coordinates;

public abstract class Ship {

    private String type;
    private int size;
    private int DestroyedBlocks = 0;
    private boolean isDestroyed = false;
    private boolean vertical;
    private int posX, posY; // Posicao no tabuleiro
    private Coordinates<Integer, Integer> partsPositions[];

    public Ship(String type, int size, int posX, int posY, boolean vertical) {
        partsPositions = new Coordinates[size];
        this.size = size;
        this.type = type;
        this.posX = posX;
        this.posY = posY;
        this.vertical = vertical;
    }

    public void setPartsPositions(Coordinates[] partsPositions) {
        this.partsPositions = partsPositions;
    }

    public Coordinates[] getPartsPositions() {
        return this.partsPositions;
    }

    public boolean hit() { // Retorna true se nao acertou agua, eh sobrescrito na classe Water
        DestroyedBlocks++;
        if (DestroyedBlocks >= size) {
            isDestroyed = true;
        }
        return true;
    }

    public int getSize() {
        return this.size;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public boolean getOrientation() {
        return this.vertical;
    }

    public boolean getDestroyed() {
        return this.isDestroyed;
    }

    public String getType() {
        return this.type;
    }
}
